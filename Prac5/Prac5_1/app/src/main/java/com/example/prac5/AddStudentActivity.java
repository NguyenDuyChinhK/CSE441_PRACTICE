package com.example.prac5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etMssv, etHoten, etLop, etDiem;
    private Button btnSave;
    private TextView tvTitle;
    private ImageButton btnBack;
    private DatabaseReference databaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etMssv = findViewById(R.id.etMssv);
        etHoten = findViewById(R.id.etHoten);
        etLop = findViewById(R.id.etLop);
        etDiem = findViewById(R.id.etDiem);
        btnSave = findViewById(R.id.btnSave);

        btnBack = findViewById(R.id.btn_back);


        databaseStudents = FirebaseDatabase.getInstance().getReference("sinhvien");

        btnSave.setOnClickListener(view -> addStudent());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addStudent() {
        String mssv = etMssv.getText().toString().trim();
        String hoten = etHoten.getText().toString().trim();
        String lop = etLop.getText().toString().trim();
        String diemStr = etDiem.getText().toString().trim();


        if (mssv.isEmpty() || hoten.isEmpty() || lop.isEmpty() || diemStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double diem;
        try {
            diem = Double.parseDouble(diemStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Điểm phải là một số hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }


        if (diem < 0 || diem > 4) {
            Toast.makeText(this, "Điểm phải nằm trong khoảng từ 0 đến 4", Toast.LENGTH_SHORT).show();
            return;
        }


        databaseStudents.child(mssv).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {

                    Toast.makeText(AddStudentActivity.this, "ID sinh viên đã tồn tại. Vui lòng nhập ID khác.", Toast.LENGTH_SHORT).show();
                } else {

                    Student student = new Student(mssv, hoten, lop, diem);
                    databaseStudents.child(mssv).setValue(student)
                            .addOnCompleteListener(addTask -> {
                                if (addTask.isSuccessful()) {
                                    Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                                    setResult(RESULT_OK, new Intent());
                                    finish();
                                } else {
                                    Toast.makeText(AddStudentActivity.this, "Lỗi khi thêm sinh viên: " + addTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            } else {
                Toast.makeText(AddStudentActivity.this, "Lỗi khi kiểm tra ID sinh viên: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}