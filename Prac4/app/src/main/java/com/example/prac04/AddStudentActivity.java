package com.example.prac04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etId, etEmail, etFullName, etGender, etMajor, etGPA, etBirthDay, etAddress, etYear;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etId = findViewById(R.id.etId);
        etEmail = findViewById(R.id.etEmail);
        etFullName = findViewById(R.id.etFullName);
        etGender = findViewById(R.id.etGender);
        etMajor = findViewById(R.id.etMajor);
        etGPA = findViewById(R.id.etGPA);
        etBirthDay = findViewById(R.id.etBirthDay);
        etAddress = findViewById(R.id.etAddress);
        etYear = findViewById(R.id.etYear);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(view -> {
            String id = etId.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String fullName = etFullName.getText().toString().trim();
            String gender = etGender.getText().toString().trim();
            String major = etMajor.getText().toString().trim();
            String gpaString = etGPA.getText().toString().trim();
            String birthDay = etBirthDay.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String yearString = etYear.getText().toString().trim();

            // Xác thực dữ liệu
            if (id.isEmpty() || email.isEmpty() || fullName.isEmpty() || gender.isEmpty() ||
                    major.isEmpty() || gpaString.isEmpty() || birthDay.isEmpty() ||
                    address.isEmpty() || yearString.isEmpty()) {
                Toast.makeText(AddStudentActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            double gpa;
            int year;
            try {
                gpa = Double.parseDouble(gpaString);
                year = Integer.parseInt(yearString);
            } catch (NumberFormatException e) {
                Toast.makeText(AddStudentActivity.this, "GPA hoặc Năm nhập học không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Trả dữ liệu về MainActivity
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("email", email);
            intent.putExtra("fullName", fullName);
            intent.putExtra("gender", gender);
            intent.putExtra("major", major);
            intent.putExtra("gpa", gpa);
            intent.putExtra("birthDay", birthDay);
            intent.putExtra("address", address);
            intent.putExtra("year", year);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
