package com.example.a41handlerthreadclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExampleHandlerThread handlerThread = new ExampleHandlerThread("HandlerThread");
    private ExampleRunnable1 runnable1 = new ExampleRunnable1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button doButton = findViewById(R.id.doButton);
        Button removeButton = findViewById(R.id.removeButton);

        doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoButton();
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveButton();
            }
        });

        handlerThread.start();
    }

    private void onDoButton() {
        // Post Runnable
        // We've used static class, so that it doesn't hold a reference to the MainActivity class, when MainActivity class is destroyed.
        handlerThread.getHandler().post(new ExampleRunnable1());

        // Send Message
        Message msg = Message.obtain();
        msg.what = 1;
        msg.arg1 = 23;
        msg.arg2 = 45;
        msg.obj = "Object String";
        handlerThread.getHandler().sendMessage(msg);

        // Alternate way to Send Message with only `what` field
//        handlerThread.getHandler().sendEmptyMessage(1);

        handlerThread.getHandler().post(runnable1);
    }

    private void onRemoveButton() {
        // Only that Handler can remove Messages/Runnables from MessageQueue, which sent those Messages/Runnables in the MessageQueue

        // To Remove a particular Runnable Object, we need to pass the same referece to Remove it from the MessageQueue
//        handlerThread.getHandler().removeCallbacks(runnable1);

        // To Remove a particular Message Object, we need to pass the value of `what` field
//        handlerThread.getHandler().removeMessages(1);

        // This will remove all the pending Messages/Runnables from the MessageQueue
//        handlerThread.getHandler().removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    static class ExampleRunnable1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "ExampleRunnable1: " + i);
                SystemClock.sleep(1000);
            }
        }
    }
}