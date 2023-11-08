package com.example.a36sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " + KEY_PHONE_NO + " TEXT " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    public void addContact(String name, String number) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE_NO, number);

        sqLiteDatabase.insert(TABLE_CONTACTS, null, values);
    }

    public ArrayList<ContactModel> fetchContacts() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);

        ArrayList<ContactModel> arrContacts = new ArrayList<>();

        while(cursor.moveToNext()) {
            ContactModel model = new ContactModel();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.number = cursor.getString(2);

            arrContacts.add(model);
        }

        return arrContacts;
    }

    public void updateContact(ContactModel model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHONE_NO, model.number);

        sqLiteDatabase.update(TABLE_CONTACTS, values, KEY_ID + " = " + model.id, null);
    }

    public void deleteContact(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
