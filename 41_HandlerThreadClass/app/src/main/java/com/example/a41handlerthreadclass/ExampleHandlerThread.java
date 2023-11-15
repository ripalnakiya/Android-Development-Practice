package com.example.a41handlerthreadclass;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;

import androidx.annotation.NonNull;

public class ExampleHandlerThread extends HandlerThread {
    private static final String TAG = "ExampleHandlerThread";
    private Handler handler;
    public ExampleHandlerThread(String name) {
        super(name, Process.THREAD_PRIORITY_BACKGROUND);
    }
    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
//        handler = new Handler();

        // Let us call MessageHandler class to handle the Message Objects.
        handler = new MessageHandler();
    }
    public Handler getHandler() {
        return handler;
    }
}
