package com.example.a37customcontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,department;
    ContentValues values = new ContentValues();

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        department = findViewById(R.id.department);
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);
        ListView listView = findViewById(R.id.listView);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButton();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadButton();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);
            }
        });

        // To delete a specific Record
//        int row = getContentResolver().delete(CompanyProvider.CONTENT_URI, "_id = ?", new String[]{"2"});
//        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
    }

    private void onSaveButton() {
        values.put("emp_name", name.getText().toString());
        values.put("dept", department.getText().toString());
        Uri uri = getContentResolver().insert(CompanyProvider.CONTENT_URI, values);
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }

    private void onLoadButton() {
        arrayList.clear();
        Cursor cursor = getContentResolver().query(CompanyProvider.CONTENT_URI, null, null, null, null);
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
    }
}