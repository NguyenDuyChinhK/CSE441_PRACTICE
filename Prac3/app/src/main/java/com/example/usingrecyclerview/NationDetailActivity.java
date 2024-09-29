package com.example.usingrecyclerview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;

import android.text.style.StyleSpan;

public class NationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation_detail);

        ImageView imgFlagDetail = findViewById(R.id.imgFlagDetail);
        TextView txtNationDetail = findViewById(R.id.txtNationDetail);
        ImageButton  buttonBack = findViewById(R.id.buttonBack);

        Nation nation = (Nation) getIntent().getSerializableExtra("nation");
        imgFlagDetail.setImageResource(nation.getFlagResource());
        String nationInfo = nation.toString();
        SpannableString spannable = new SpannableString(nationInfo);

        int start = 0;
        int end = nation.getName().length() + 8;
        spannable.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtNationDetail.setText(spannable);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NationDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}