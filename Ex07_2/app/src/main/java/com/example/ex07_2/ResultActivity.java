package com.example.ex07_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView txtKq;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtKq = findViewById(R.id.txtKq);
        btnBack = findViewById(R.id.btnBack);
        Intent yourintent = getIntent();
        Bundle yourbundle = yourintent.getBundleExtra("mybackage");
        int a = yourbundle.getInt("soa");
        int b = yourbundle.getInt("sob");
        String kq="";
        if(a==0 && b==0)
        {
            kq="Vô số nghiệm";
        }
        else if(a==0 && b!=0)
        {
            kq="Vô nghiệm";
        }
        else
        {
            DecimalFormat dcf=new DecimalFormat("0.##");
            kq=dcf.format(-b*1.0/a);
        }
        txtKq.setText(kq);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}