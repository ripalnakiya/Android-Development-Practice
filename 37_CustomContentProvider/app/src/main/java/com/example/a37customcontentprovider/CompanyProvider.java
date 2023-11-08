package com.example.a37customcontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.Nullable;

public class CompanyProvider extends ContentProvider {

    SQLiteDatabase sqLiteDatabase;
    public CompanyProvider() {
    }

    private static final String AUTHORITY = "com.ripalnakiya.company.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/emp");
    private static final String DATABASE_NAME = "company";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_EMP = "emp";

    static int EMP = 1;
    static int EMP_ID = 2;

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "emp", EMP);
        uriMatcher.addURI(AUTHORITY, "emp/#", EMP_ID);
    }

    private class MyDBHelper extends SQLiteOpenHelper {

        public MyDBHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_EMP + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, emp_name text, dept text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMP);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int row = sqLiteDatabase.delete(TABLE_EMP, selection, selectionArgs);
        return row;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = sqLiteDatabase.insert(TABLE_EMP, null, values);
        if(row > 0) {
            uri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    @Override
    public boolean onCreate() {
        MyDBHelper dbHelper = new MyDBHelper(getContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        if(sqLiteDatabase != null)
            return true;
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_EMP, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}