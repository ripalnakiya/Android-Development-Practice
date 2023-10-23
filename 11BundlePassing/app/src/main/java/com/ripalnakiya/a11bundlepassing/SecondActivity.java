package com.ripalnakiya.a11bundlepassing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    TextView name;
    TextView rollno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.name);
        rollno = findViewById(R.id.rollno);

        Intent intent = getIntent();

        String tname = intent.getStringExtra("name");
        int trollno = intent.getIntExtra("rollno", 0);

        name.setText(tname);
        rollno.setText(String.valueOf(trollno));
    }
}