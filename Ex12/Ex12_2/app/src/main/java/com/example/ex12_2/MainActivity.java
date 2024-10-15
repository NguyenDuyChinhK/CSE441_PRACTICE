package com.example.ex12_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.ListView1);
        edtwork = findViewById(R.id.edtwork);
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmi);
        txtdate = findViewById(R.id.txtdate);
        btnwork = findViewById(R.id.btnwork);

        sharedPreferences = getSharedPreferences("work_data", MODE_PRIVATE);
        Set<String> savedWorks = sharedPreferences.getStringSet("works", new HashSet<>());
        arraywork = new ArrayList<>(savedWorks);

        arrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);
        lv.setAdapter(arrAdapter);

        Date currentDate = Calendar.getInstance().getTime();
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm Nay: " + simpleDateFormat.format(currentDate));

        btnwork.setOnClickListener(v -> {
            if (edtwork.getText().toString().equals("") || edth.getText().toString().equals("") || edtm.getText().toString().equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Info missing");
                builder.setMessage("Please enter all information of the work");
                builder.setPositiveButton("Continue", (dialog, which) -> {
                });
                builder.show();
            } else {
                String str = edtwork.getText().toString() + " - " + edth.getText().toString() + ":" + edtm.getText().toString();
                arraywork.add(str);
                arrAdapter.notifyDataSetChanged();
                saveData();
                edth.setText("");
                edtm.setText("");
                edtwork.setText("");
            }
        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            arraywork.remove(position);
            arrAdapter.notifyDataSetChanged();
            saveData();
        });
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> workSet = new HashSet<>(arraywork);
        editor.putStringSet("works", workSet);
        editor.apply();
    }
}
