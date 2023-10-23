package com.ripalnakiya.a21activitylifecycle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity.Test", "onCreate: A");

        Button buttonB = findViewById(R.id.buttonB);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity.Test", "onStart: A");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity.Test", "onResume: A");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity.Test", "onPause: A");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity.Test", "onStop: A");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity.Test", "onRestart: A");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity.Test", "onDestroy: A");
    }
}