package com.example.bai8_intent_call_sms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SendSMSActivity extends AppCompatActivity {
    EditText edtsms;
    Button btnback2;
    ImageButton btnsendsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_smsactivity);

        edtsms = findViewById(R.id.edtsms);
        btnback2 = findViewById(R.id.btnback2);
        btnsendsms = findViewById(R.id.btnsms);

        btnsendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = edtsms.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    Intent callintent = new Intent(Intent.ACTION_SENDTO,
                            Uri.parse("smsto:" + phoneNumber));
                    startActivity(callintent);
                } else {
                    edtsms.setError("Please enter a phone number");
                }
            }
        });

        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
