package com.example.ex05_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlinx.coroutines.CancelHandler;

public class MainActivity extends AppCompatActivity {
    EditText editText_duongLich, editText_amLich;
    Button btn_chuyenDoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText_amLich = findViewById(R.id.edt_amLich);
        editText_duongLich = findViewById(R.id.edt_duongLich);
        btn_chuyenDoi = findViewById(R.id.btn_chuyenDoi);

        btn_chuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    int namDuong =  Integer.parseInt("0"+ editText_duongLich.getText());

                    int Can = namDuong % 10;
                    int Chi = namDuong %12;
                    String can= "", chi ="";


                    switch (Can){
                        case 0:
                            can = "Canh";
                            break;
                        case 1:
                            can = "Tân";
                            break;
                        case 2:
                            can = "Nhâm";
                            break;
                        case 3:
                            can = "Quý";
                            break;
                        case 4:
                            can = "Giáp";
                            break;
                        case 5 :
                            can = "Ất";
                            break;
                        case 6:
                            can = "Bính";
                            break;
                        case 7 :
                            can = "Dinh";
                            break;
                        case 8:
                            can = "Mậu";
                            break;
                        case 9:
                            can = "Kỷ";
                            break;



                    }

                    switch (Chi){
                        case 0:
                            chi = "Thân";
                            break;
                        case 1:
                            chi = "Mậu";
                            break;
                        case 2:
                            chi = "Tuất";
                            break;
                        case 3:
                            chi = "Hợi";
                            break;
                        case 4:
                            chi = "Tý";
                            break;
                        case 5 :
                            chi = "Sửu";
                            break;
                        case 6:
                            chi = "Dần";
                            break;
                        case 7 :
                            chi = "Mẹo";
                            break;
                        case 8:
                            chi = "Thìn";
                            break;
                        case 9:
                            chi = "Tỵ";
                            break;
                        case 10:
                            chi = "Ngọ";
                            break;
                        case 11:
                            chi = "Mùi";
                            break;




                    }

                    editText_amLich.setText(can+" " +chi);

                }catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this,"Năm dương lịch không hợp lệ!",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}