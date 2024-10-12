package com.example.dangkythongtincanhan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText_hoTen, editText_CMND, editText_ttBoSung;
    Button btn_gui;
    RadioGroup group;
    CheckBox chk_docBao, chk_docSach, chk_doccode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        editText_hoTen = findViewById(R.id.edt_hoTen);
        editText_CMND = findViewById(R.id.edt_CMND);
        editText_ttBoSung = findViewById(R.id.edt_ttBoSung);
        chk_doccode = findViewById(R.id.chk_docCoding);
        chk_docSach = findViewById(R.id.chk_docSach);
        chk_docBao = findViewById(R.id.chk_docBao);

        btn_gui = findViewById(R.id.btn_gui);

        btn_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowInformation();
            }
        });
    }

    public void doShowInformation() {
        String ten = editText_hoTen.getText().toString().trim();
        if (ten.length() < 3) {
            editText_hoTen.requestFocus();
            editText_hoTen.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        String cmnd = editText_CMND.getText().toString().trim();
        if (cmnd.length() != 9) {
            editText_CMND.requestFocus();
            editText_CMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String bang = "";
        group = findViewById(R.id.idgruop);
        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        bang = rad.getText().toString();

        String sothich = "";
        if (chk_docBao.isChecked())
            sothich += chk_docBao.getText() + "\n";
        if (chk_docSach.isChecked())
            sothich += chk_docSach.getText() + "\n";
        if (chk_doccode.isChecked())
            sothich += chk_doccode.getText() + "\n";
        String bosung = editText_ttBoSung.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        String msg = ten + "\n";
        msg += cmnd + "\n";
        msg += bang + "\n";
        msg += sothich;
        msg += "—————————–\n";
        msg += "Thông tin bổ sung:\n";
        msg += bosung + "\n";
        msg += "—————————–";
        builder.setMessage(msg);
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(R.drawable.inform);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}
