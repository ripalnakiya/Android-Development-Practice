package com.example.a47apikeys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String KEY1 = BuildConfig.API_KEY;

    public static final String  KEY2 = new ApiKeyManager().getApiKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView key1 = findViewById(R.id.key1);
        TextView key2 = findViewById(R.id.key2);

        key1.setText(KEY1);
        key2.setText(KEY2);
    }
}