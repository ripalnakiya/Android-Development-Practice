package com.example.a40handlerandlooper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExampleLooperThread looperThread = new ExampleLooperThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button taskAButton = findViewById(R.id.taskAButton);
        Button taskBButton = findViewById(R.id.taskBButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartButton();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStopButton();
            }
        });
        taskAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTaskAButton();
            }
        });
        taskBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTaskBButton();
            }
        });
    }
    private void onStartButton() {
        looperThread.start();
    }
    private void onStopButton() {
        // Once, we've stopped the looper thread, It cannot start again (Just like normal thread)
        looperThread.looper.quit();

        // This can achieved alternatively (When we don't have access to Looper directly)
//        looperThread.handler.getLooper().quit();
    }
    private void onTaskAButton() {
//        looperThread.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5; i++) {
//                    Log.d(TAG, "run: " + i);
//                    SystemClock.sleep(1000);
//                }
//            }
//        });

        // Alternate method (When we don't have access to handler directly)
        Handler threadHandler = new Handler(looperThread.looper);
        // By default, Handler is initialized with the looper, In which the Handler is created
        // But if we pass a Looper to its constructor, then we can create a Handler for that particular Looper

        // new Runnable() creates a Non-static inner class, and it holds reference of MainActivity class
        // Hence it can create memory leaks
        // So, instead we can use static class that implements Runnable interface
        threadHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "run: " + i);
                    SystemClock.sleep(1000);
                }
            }
        });
    }
    private void onTaskBButton() {
        // Handler class can post Runnables and send Messages to the MessageQueue
        // To send a Message, we need to Implement the handling of Messages in the Handler class
        // By default, the only posting of Runnables is Implemented

        Message msg = Message.obtain();
        msg.what = 2;
        looperThread.handler.sendMessage(msg);
    }
}