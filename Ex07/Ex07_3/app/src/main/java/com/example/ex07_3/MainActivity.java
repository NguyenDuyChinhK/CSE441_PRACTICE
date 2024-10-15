package com.example.ex07_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtKq;
    Button btnKq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKq = findViewById(R.id.edtKq);
        btnKq = findViewById(R.id.btnrequest);

        btnKq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, sub.class);
                int a = Integer.parseInt("0"+edtA.getText().toString());
                int b = Integer.parseInt("0"+edtB.getText().toString());
                myIntent.putExtra("soa", a);
                myIntent.putExtra("sob", b);
                startActivityForResult(myIntent, 99);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 33 && data != null) {
            int sum = data.getIntExtra("kq", 0);
            edtKq.setText("Tổng 2 số là: " + sum);
        }
        if (requestCode == 99 && resultCode == 34 && data != null) {
            int sub = data.getIntExtra("kq", 0);
            edtKq.setText("Hiệu hai số là: " + sub);
        }
    }
}
