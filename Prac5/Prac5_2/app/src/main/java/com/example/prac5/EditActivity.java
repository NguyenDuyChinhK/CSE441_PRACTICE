package com.example.prac5;

import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;

public class EditActivity extends AppCompatActivity {
    private EditText etMssv, etHoten, etLop, etDiem;
    private Button btnSave;

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

        Intent intent = getIntent();
        String mssv = intent.getStringExtra("student_id");
        String hoten = intent.getStringExtra("student_name");
        String lop = intent.getStringExtra("student_class");
        double diem = intent.getDoubleExtra("student_gpa", 0.0);


        etMssv.setText(mssv);
        etHoten.setText(hoten);
        etLop.setText(lop);
        etDiem.setText(String.valueOf(diem));

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

        if (mssv.isEmpty() || hoten.isEmpty() || lop.isEmpty() || diemStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double diem = Double.parseDouble(diemStr);
        if (diem < 0 || diem > 4) {
            Toast.makeText(this, "Điểm GPA phải nằm trong khoảng từ 0 đến 4", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Kiểm tra xem mã sinh viên đã tồn tại chưa
        db.collection("sinhvien").document(mssv)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            // Nếu mã sinh viên đã tồn tại, thông báo và không cập nhật
                            Toast.makeText(this, "Mã sinh viên đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            // Nếu mã sinh viên chưa tồn tại, tiến hành thêm sinh viên mới
                            Student student = new Student(mssv, hoten, lop, diem);
                            db.collection("sinhvien").document(mssv)
                                    .set(student)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                                        finish(); // Quay lại MainActivity sau khi thêm thành công
                                    })
                                    .addOnFailureListener(e ->
                                            Toast.makeText(this, "Lỗi khi thêm sinh viên", Toast.LENGTH_SHORT).show()
                                    );
                        }
                    } else {
                        Toast.makeText(this, "Lỗi khi kiểm tra mã sinh viên", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
