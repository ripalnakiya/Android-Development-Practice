package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    MyReceiver myReceiver = new MyReceiver();
    Intent intent;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySharedPreference.createInstance(this);

        textView = findViewById(R.id.textView);
        Button addButton = findViewById(R.id.addButton);
        Button subButton = findViewById(R.id.subButton);

        // Updating the text view with last operation
        setTextView();

        // Registering the Broadcast Receiver
        registerCustomReceiver();

        // Intent to send (With two values to operate on)
        intent = new Intent();
        intent.putExtra("num1", 10);
        intent.putExtra("num2", 5);

        // `implement View.OnClickListener`
//        addButton.setOnClickListener(this);
//        subButton.setOnClickListener(this);
        addButton.setOnClickListener(onClickListener);
        subButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.addButton) {
                intent.setAction("com.example.demoapp.ADDITION");
            } else if (v.getId() == R.id.subButton) {
                intent.setAction("com.example.demoapp.SUBTRACTION");
            }
            sendBroadcast(intent);
        }
    };

    // For this we'll need to `implement View.OnClickListener` in MainActivity Declaration
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.addButton) {
//            intent.setAction("com.example.demoapp.ADDITION");
//        } else if (v.getId() == R.id.subButton) {
//            intent.setAction("com.example.demoapp.SUBTRACTION");
//        }
//        sendBroadcast(intent);
//    }

    private void setTextView() {
        String first = MySharedPreference.getValue("Operation");
        String last = MySharedPreference.getValue("Result");
        textView.setText(first + " : " + last);
    }

    private void registerCustomReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.demoapp.ADDITION");
        filter.addAction("com.example.demoapp.SUBTRACTION");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(myReceiver, filter, RECEIVER_EXPORTED);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregistering the Broadcast Receiver
        unregisterReceiver(myReceiver);
    }
}