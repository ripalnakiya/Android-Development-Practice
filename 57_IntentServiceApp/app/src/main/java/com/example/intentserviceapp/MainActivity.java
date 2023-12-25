package com.example.intentserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);

        button1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MyIntentService.class);
            intent.setAction("com.example.ADDITION");
            intent.putExtra("val1", 11);
//            startService(intent);

            AlarmManager alarmManager = getSystemService(AlarmManager.class);
            PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 1, intent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
        });
    }
}