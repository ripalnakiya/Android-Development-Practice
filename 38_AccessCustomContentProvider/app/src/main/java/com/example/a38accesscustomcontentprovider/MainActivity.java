package com.example.a38accesscustomcontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.ripalnakiya.company.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/emp");
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loadButton = findViewById(R.id.loadButton);
        ListView listView = findViewById(R.id.listView);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    Toast.makeText(MainActivity.this, "I'm Cursor", Toast.LENGTH_SHORT).show();
                    do {
                        StringBuilder sb = new StringBuilder();
                        sb.append(cursor.getString(1));
                        sb.append(" : ");
                        sb.append(cursor.getString(2));
                        arrayList.add(sb.toString());
                    } while (cursor.moveToNext());
                } else {
                    arrayList.add("No Records Found");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);
            }
        });
    }
}