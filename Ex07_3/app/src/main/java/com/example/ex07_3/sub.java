package com.example.ex07_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class sub extends AppCompatActivity {
    EditText edtAA, edtBB;
    Button btnsendtong, btnsendhieu;
    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);


        edtAA = findViewById(R.id.edtAA);
        edtBB = findViewById(R.id.edtBB);
        btnsendtong = findViewById(R.id.btnsendtong);
        btnsendhieu = findViewById(R.id.btnsendhieu);
        myintent = getIntent();
        int a = myintent.getIntExtra("soa", 0);
        int b = myintent.getIntExtra("sob", 0);
        edtAA.setText(a + "");
        edtBB.setText(b + "");

      btnsendtong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int sum = a + b;

                        myintent.putExtra("kq", sum);
                        setResult(33, myintent);

                        finish();
                    }
                });


                btnsendhieu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int sub = a - b;
                        myintent.putExtra("kq", sub);
                        setResult(34, myintent);
                        finish();
                    }
                });
            }


    }

