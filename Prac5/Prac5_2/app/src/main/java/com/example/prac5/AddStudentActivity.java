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
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etMssv, etHoten, etLop, etDiem;
    private Button btnSave;

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

        double diem = Double.parseDouble(diemStr);
        if (diem < 0 || diem > 4) {
            Toast.makeText(this, "Điểm GPA phải nằm trong khoảng từ 0 đến 4", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("sinhvien")
                .document(mssv)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {

                        Toast.makeText(this, "Mã sinh viên đã tồn tại", Toast.LENGTH_SHORT).show();
                    } else {

                        Student student = new Student(mssv, hoten, lop, diem);
                        db.collection("sinhvien")
                                .document(mssv)
                                .set(student)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                })
                                .addOnFailureListener(e ->
                                        Toast.makeText(this, "Lỗi khi thêm sinh viên", Toast.LENGTH_SHORT).show()
                                );
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Lỗi khi kiểm tra mã sinh viên", Toast.LENGTH_SHORT).show()
                );
    }
}