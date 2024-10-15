package com.example.prac04;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView txtEmail, txtFullName, txtGender, txtMajor, txtGPA, txtBirthDay, txtAddress, txtYear;
    private Button btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtEmail = findViewById(R.id.txtEmail);
        txtFullName = findViewById(R.id.txtFullName);
        txtGender = findViewById(R.id.txtGender);
        txtMajor = findViewById(R.id.txtMajor);
        txtGPA = findViewById(R.id.txtGPA);
        txtBirthDay = findViewById(R.id.txtBirthDay);
        txtAddress = findViewById(R.id.txtAddress);
        txtYear = findViewById(R.id.txtYear);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        Student student = (Student) getIntent().getSerializableExtra("STUDENT");
        if (student != null) {
            txtEmail.setText(student.getEmail());
            txtFullName.setText(student.getFullName());
            txtGender.setText(student.getGender());
            txtMajor.setText(student.getMajor());
            txtGPA.setText(String.valueOf(student.getGpa()));
            txtBirthDay.setText(student.getBirthDay());
            txtAddress.setText(student.getAddress());
            txtYear.setText(String.valueOf(student.getYear()));
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("id", student.getId());
                returnIntent.putExtra("email", txtEmail.getText().toString());
                returnIntent.putExtra("fullName", txtFullName.getText().toString());
                returnIntent.putExtra("gender", txtGender.getText().toString());
                returnIntent.putExtra("major", txtMajor.getText().toString());
                returnIntent.putExtra("gpa", student.getGpa());
                returnIntent.putExtra("birthDay", txtBirthDay.getText().toString());
                returnIntent.putExtra("address", txtAddress.getText().toString());
                returnIntent.putExtra("year", student.getYear());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("id", student.getId()); // Gửi id của sinh viên để xóa
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }
}
