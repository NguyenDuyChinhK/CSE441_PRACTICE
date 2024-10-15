package com.example.intent_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        processReceive(context, intent);
    }

    private void processReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        StringBuilder message = new StringBuilder();
        if (extras != null) {
            Object[] smsEtra = (Object[]) extras.get("pdus");
            for (int i = 0; i < smsEtra.length; i++) {

                SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsEtra[i]);
                String body = sms.getMessageBody();
                String address = sms.getOriginatingAddress();


                message.append("Có 1 tin nhắn từ ").append(address).append("\n").append(body).append(" vừa gửi đến\n");
            }

            Toast.makeText(context, message.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
