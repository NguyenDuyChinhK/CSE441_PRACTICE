package com.example.prac5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends AppCompatActivity {
    private EditText etMssv, etHoten, etLop, etDiem;
    private Button btnSave;
    private TextView tvTitle;
    private ImageButton btnBack;
    private DatabaseReference databaseStudents;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etMssv = findViewById(R.id.etMssv);
        etHoten = findViewById(R.id.etHoten);
        etLop = findViewById(R.id.etLop);
        etDiem = findViewById(R.id.etDiem);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btn_back);

        databaseStudents = FirebaseDatabase.getInstance().getReference("sinhvien");

        studentId = getIntent().getStringExtra("student_id");
        if (studentId == null) {
            Toast.makeText(this, "MSSV không hợp lệ", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        getStudentDetails();

        btnSave.setOnClickListener(view -> updateStudent());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getStudentDetails() {
        databaseStudents.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Student student = snapshot.getValue(Student.class);
                    etMssv.setText(student.getMssv());
                    etHoten.setText(student.getHoten());
                    etLop.setText(student.getLop());
                    etDiem.setText(String.valueOf(student.getDiem()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditActivity.this, "Lỗi khi lấy thông tin sinh viên", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStudent() {
        String mssv = etMssv.getText().toString().trim();
        String hoten = etHoten.getText().toString().trim();
        String lop = etLop.getText().toString().trim();
        String diemStr = etDiem.getText().toString().trim();


        if (mssv.isEmpty() || !mssv.matches("\\d+")) {
            Toast.makeText(this, "MSSV phải là một chuỗi số hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }


        if (hoten.isEmpty() || hoten.length() < 3) {
            Toast.makeText(this, "Họ tên phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }


        if (lop.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền lớp học", Toast.LENGTH_SHORT).show();
            return;
        }


        double diem;
        try {
            diem = Double.parseDouble(diemStr);
            if (diem < 0 || diem > 4) {
                Toast.makeText(this, "Điểm phải nằm trong khoảng từ 0 đến 4", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Điểm phải là một số hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }


        databaseStudents.orderByKey().equalTo(mssv).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!snapshot.exists() || studentId.equals(mssv)) {
                    Student student = new Student(mssv, hoten, lop, diem);

                    databaseStudents.child(studentId).setValue(student).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditActivity.this, "Cập nhật sinh viên thành công", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(EditActivity.this, "Lỗi khi cập nhật sinh viên", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(EditActivity.this, "MSSV đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditActivity.this, "Lỗi khi kiểm tra MSSV", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
