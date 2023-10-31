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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asking for permission
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, 1);
        }

        ContentResolver contentResolver = getContentResolver();

        String[] mProjection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
        };

        String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME + " = ?";
        String[] mSelectionArguments = new String[] {"Captain Jack"};
        String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME;

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, mProjection, null, null, mOrderBy);

        if(cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                String contactId = cursor.getString(0);
                String displayName = cursor.getString(1);
                Log.d("Contacts.this", contactId + " : " + displayName);
            }
        }
    }
}