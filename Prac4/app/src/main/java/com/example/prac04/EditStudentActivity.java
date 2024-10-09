package com.example.prac04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudentActivity extends AppCompatActivity {

    private EditText edtEmail, edtFullName, edtGender, edtMajor, edtGPA, edtBirthDay, edtAddress, edtYear;
    private Button btnSave;

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        // Kích hoạt nút back trên App Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chỉnh sửa sinh viên");
        }

        edtEmail = findViewById(R.id.edtEmail);
        edtFullName = findViewById(R.id.edtFullName);
        edtGender = findViewById(R.id.edtGender);
        edtMajor = findViewById(R.id.edtMajor);
        edtGPA = findViewById(R.id.edtGPA);
        edtBirthDay = findViewById(R.id.edtBirthDay);
        edtAddress = findViewById(R.id.edtAddress);
        edtYear = findViewById(R.id.edtYear);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("STUDENT")) {
            student = (Student) intent.getSerializableExtra("STUDENT");
            if (student != null) {
                edtEmail.setText(student.getEmail());
                edtFullName.setText(student.getFullName());
                edtGender.setText(student.getGender());
                edtMajor.setText(student.getMajor());
                edtGPA.setText(String.valueOf(student.getGpa()));
                edtBirthDay.setText(student.getBirthDay());
                edtAddress.setText(student.getAddress());
                edtYear.setText(String.valueOf(student.getYear()));
            }
        }

        btnSave.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String fullName = edtFullName.getText().toString().trim();
            String gender = edtGender.getText().toString().trim();
            String major = edtMajor.getText().toString().trim();
            double gpa;
            int year;

            // Kiểm tra và chuyển đổi GPA
            try {
                gpa = Double.parseDouble(edtGPA.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập điểm GPA hợp lệ.", Toast.LENGTH_SHORT).show();
                return;
            }

            String birthDay = edtBirthDay.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();

            // Kiểm tra và chuyển đổi năm học
            try {
                year = Integer.parseInt(edtYear.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập năm học hợp lệ.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật thông tin sinh viên
            Intent returnIntent = new Intent();
            returnIntent.putExtra("id", student.getId());
            returnIntent.putExtra("email", email);
            returnIntent.putExtra("fullName", fullName);
            returnIntent.putExtra("gender", gender);
            returnIntent.putExtra("major", major);
            returnIntent.putExtra("gpa", gpa);
            returnIntent.putExtra("birthDay", birthDay);
            returnIntent.putExtra("address", address);
            returnIntent.putExtra("year", year);
            setResult(RESULT_OK, returnIntent);
            Toast.makeText(this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Xử lý nút back trên App Bar
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
