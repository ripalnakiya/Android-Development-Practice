package com.ripalnakiya.a23fragmentlifecycle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.window.OnBackInvokedDispatcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity.Test", "onCreate: Activity");

        Button button = findViewById(R.id.button);

        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.setReorderingAllowed(true);
                ft.add(R.id.container, FirstFragment.class, null);
                ft.commit();
            }
        });

        onBackPressedDispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment fragment = fm.findFragmentById(R.id.container);
                if(fragment != null) {
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.remove(fragment);
                    ft.commit();
                } else {
                    finish();
                }
                Log.d("MainActivity.Test", "handleOnBackPressed: ");
            }
        });

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