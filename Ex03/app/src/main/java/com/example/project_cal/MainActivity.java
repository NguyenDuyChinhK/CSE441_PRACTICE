package com.example.project_cal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText_a,editText_b, editText_kq;
    Button btn_tong,btn_hieu,btn_tich,btn_thuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       editText_a = findViewById(R.id.editText_a);
       editText_b = findViewById(R.id.editText_b);
       editText_kq = findViewById(R.id.editText_kq);
       btn_tong = findViewById(R.id.btnTong);
       btn_hieu = findViewById(R.id.btnHieu);
       btn_tich = findViewById(R.id.btnTich);
       btn_thuong = findViewById(R.id.btnThuong);
       int a = Integer.parseInt("0"+editText_a.getText());
       int b = Integer.parseInt("0"+ editText_b.getText());

       btn_tong.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int a = Integer.parseInt("0"+editText_a.getText());
               int b = Integer.parseInt("0"+ editText_b.getText());
               editText_kq.setText("a + b = "+(a+b));
           }
       });
        btn_hieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0"+editText_a.getText());
                int b = Integer.parseInt("0"+ editText_b.getText());
                editText_kq.setText("a - b = "+ (a-b));
            }
        });
        btn_tich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0"+editText_a.getText());
                int b = Integer.parseInt("0"+ editText_b.getText());
                editText_kq.setText("a * b = " + (a*b));
            }
        });
        btn_thuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0"+editText_a.getText());
                int b = Integer.parseInt("0"+ editText_b.getText());
                if(b ==0){
                    editText_kq.setText("b phải là số khác không");
                }else{
                    editText_kq.setText("a / b = "+(a/b));
                }

            }
        });

    }
}