package com.ripalnakiya.a21activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d("MainActivity.Test", "onCreate: C");

        Button buttonA = findViewById(R.id.buttonA);
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity.Test", "onStart: C");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity.Test", "onResume: C");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity.Test", "onPause: C");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity.Test", "onStop: C");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity.Test", "onRestart: C");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity.Test", "onDestroy: C");
    }
}