package com.example.a29custombroadcastreceiver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    CustomBroadcastReceiver customBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setText("Message");
        customBroadcastReceiver = new CustomBroadcastReceiver(textView);

        IntentFilter filter = new IntentFilter("com.example.custombroadcast.ACTION_CUSTOM");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(customBroadcastReceiver, filter, Context.RECEIVER_EXPORTED);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(customBroadcastReceiver);
    }
}