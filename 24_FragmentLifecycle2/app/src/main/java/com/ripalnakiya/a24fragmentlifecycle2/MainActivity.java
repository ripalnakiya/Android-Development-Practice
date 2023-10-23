package com.ripalnakiya.a24fragmentlifecycle2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity.Test", "onCreate: Activity");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, BlankFragment.class, null);
        ft.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity.Test", "onStart: Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity.Test", "onResume: Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity.Test", "onPause: Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity.Test", "onStop: Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity.Test", "onRestart: Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity.Test", "onDestroy: Activity");
    }
}