package com.example.a30contactscontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = editText.getText().toString();

                // Create a new contact and insert it
                ContentValues values = new ContentValues();
                values.put(ContactsContract.RawContacts.ACCOUNT_NAME, (String) null);
                values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, (String) null);

                Uri rawContactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);

                long rawContactId = ContentUris.parseId(rawContactUri);

                // Add contact's name
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contactName);
                contentResolver.insert(ContactsContract.Data.CONTENT_URI, values);

                Toast.makeText(MainActivity.this, "Contact added: " + contactName, Toast.LENGTH_SHORT).show();
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = editText.getText().toString();

                String whereClause = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " = '" + contactName + "'";
                contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, whereClause, null);

                Toast.makeText(MainActivity.this, "Contact deleted: " + contactName, Toast.LENGTH_SHORT).show();

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