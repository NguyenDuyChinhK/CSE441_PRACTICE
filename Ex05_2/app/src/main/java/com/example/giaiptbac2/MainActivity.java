package com.example.giaiptbac2;

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

public class MainActivity extends AppCompatActivity {
    EditText editText_a, editText_b, editText_c;
    TextView textView_kq;
    Button button_tiepTuc, button_giaiPT, button_thoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText_a = findViewById(R.id.edt_a);
        editText_b = findViewById(R.id.edt_b);
        editText_c = findViewById(R.id.edt_c);
        textView_kq = findViewById(R.id.txt_kq);
        button_thoat = findViewById(R.id.btn_thoat);
        button_giaiPT = findViewById(R.id.btn_giaiPT);
        button_tiepTuc = findViewById(R.id.btn_tiepTuc);

        button_tiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_a.setText("");
                editText_a.requestFocus();
                editText_b.setText("");
                editText_c.setText("");
            }
        });

        button_giaiPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(editText_a.getText()+"");
                int b = Integer.parseInt(editText_b.getText()+"");
                int c = Integer.parseInt(editText_c.getText()+"");

                String kq = "";
                DecimalFormat dcf = new DecimalFormat("0.00");
                if(a==0)
                {
                    if(b==0)
                    {

                        if(c==0)
                            kq="PT vô số nghiệm";
                        else
                            kq="PT vô nghiệm";
                    }
                    else
                    {
                        kq="Pt có 1 No, x="+dcf.format(-c/b);
                    }
                }
                else
                {
                    double delta=b*b-4*a*c;
                    if(delta<0)
                    {
                        kq="PT vô nghiệm";
                    }
                    else if(delta==0)
                    {
                        kq="Pt có No kép x1=x2="+dcf.format(-b/(2*a));
                    }
                    else
                    {
                        kq="Pt có 2 No: x1="+dcf.format((-b+Math.sqrt(delta))/(2*a))+"; x2="+dcf.format((-
                                b-Math.sqrt(delta))/(2*a));
                    }
                }
                textView_kq.setText(kq);
            }
        });

        button_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}