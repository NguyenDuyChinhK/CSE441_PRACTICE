package com.example.prac1application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    Button btn_submit;
    EditText edt_ht, edt_gpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        btn_submit = findViewById(R.id.btn_submit);
        edt_ht = findViewById(R.id.edt_ht);
        edt_gpa = findViewById(R.id.edt_gpa);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = edt_ht.getText().toString();
                float diemTrungBinh = Float.parseFloat(edt_gpa.getText().toString());

                Intent returnIntent = new Intent();
                returnIntent.putExtra("hoTen", hoTen);
                returnIntent.putExtra("diemTrungBinh", diemTrungBinh);

                setResult(RESULT_OK, returnIntent); // Trả kết quả về cho Activity cha
                finish();
            }
        });

    }
}