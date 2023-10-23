package com.ripalnakiya.a4intentpassing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonNext = findViewById(R.id.buttonNext);

        buttonNext = findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNext;
                // Constructor takes two parameters i.e. (source, desination)
                // iNext = new Intent(getApplicationContext(), SecondActivity.class);
                iNext = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(iNext);
            }
        });
    }
}