package com.example.a34viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
                setText();
            }
        });
    }

    void setText() {
        String text = String.valueOf(mainViewModel.counter);
        textView.setText(text);
    }

    void incrementValue() {
        mainViewModel.increment();
    }
}