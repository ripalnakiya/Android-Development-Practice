package com.example.a30contactscontentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

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

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int READ_CONTACTS_PERMISSION_REQUEST = 1;
    private static final int WRITE_CONTACTS_PERMISSION_REQUEST = 2;

    ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button showButton = findViewById(R.id.showButton);

        checkAndRequestPermissions();

        contentResolver = getContentResolver();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = editText.getText().toString();
                addContact(contactName);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = editText.getText().toString();
                deleteContact(contactName);
            }
        });
        
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize the Loader to fetch contact data
                LoaderManager.getInstance(MainActivity.this).initLoader(1, null, MainActivity.this);
            }
        });

    }

    private void checkAndRequestPermissions() {
        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION_REQUEST);
        }
        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_CONTACTS}, WRITE_CONTACTS_PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == READ_CONTACTS_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, initialize the Loader
                // Initialize the Loader to fetch contact data
                LoaderManager.getInstance(this).initLoader(1, null, this);
            } else {
                Toast.makeText(this, "READ_CONTACTS permission denied. Cannot fetch contacts.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        // Define the projection and selection for the loader
        String[] mProjection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        };

        String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";
        String[] mSelectionArguments = new String[] {"Captain Jack"};
        String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;

        return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, mProjection, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if (data.getCount() > 0) {
                while (data.moveToNext()) {
                    String contactId = data.getString(0);
                    String displayName = data.getString(1);
                    Log.d("Contacts.this", contactId + " : " + displayName);
                }
            }
        }
        Toast.makeText(MainActivity.this, "See Logs : Contacts.this", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    private void addContact(String contactName) {
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

    private void deleteContact(String contactName) {
        String whereClause = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " = ?";
        String[] whereArgs = {contactName};
        contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, whereClause, whereArgs);

        Toast.makeText(MainActivity.this, "Contact deleted: " + contactName, Toast.LENGTH_SHORT).show();
    }
}