package com.example.prac1application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_main;
    TextView txt_ht, txt_gpa;
    private static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn_main = findViewById(R.id.btn_main);
        txt_ht = findViewById(R.id.txt_ht);
        txt_gpa = findViewById(R.id.txt_gpa);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            String hoTen = data.getStringExtra("hoTen");
            float diemTrungBinh = data.getFloatExtra("diemTrungBinh", 0);


            txt_ht.setText("Họ tên: " + hoTen);
            txt_gpa.setText("Điểm trung bình tích lũy: " + diemTrungBinh);
        }
        }
    }



