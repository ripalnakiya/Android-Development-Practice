package com.example.a30contactscontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asking for permission
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, 1);
        }

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS}, 2);
        }

        EditText editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button showButton = findViewById(R.id.showButton);

        ContentResolver contentResolver = getContentResolver();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whereClause = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " = '" + editText.getText().toString() + "'";
                contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, whereClause, null);
            }
        });
        
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mProjection = new String[] {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                };

                String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";
                String[] mSelectionArguments = new String[] {"Captain Jack"};
                String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;

                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, mProjection, null, null, null);

                if(cursor != null && cursor.getCount() > 0) {
                    while(cursor.moveToNext()) {
                        String contactId = cursor.getString(0);
                        String displayName = cursor.getString(1);
                        Log.d("Contacts.this", contactId + " : " + displayName);
                    }
                }

                Toast.makeText(MainActivity.this, "See Logs : Contacts.this", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}