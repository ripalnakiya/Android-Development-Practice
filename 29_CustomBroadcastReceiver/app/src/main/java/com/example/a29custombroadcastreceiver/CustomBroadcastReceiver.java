package com.example.a29custombroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    private TextView textView;
    public CustomBroadcastReceiver(TextView textView){
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.example.custombroadcast.ACTION_CUSTOM".equalsIgnoreCase(intent.getAction())) {
            String receivedText = intent.getStringExtra("com.example.custombroadcast.EXTRA_VALUE");
            if(!receivedText.equals("null"))
                textView.setText(receivedText);
            Toast.makeText(context, "Message Changed", Toast.LENGTH_LONG).show();
        }
    }
}
