package com.example.a36sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper dbHelper = new MyDBHelper(this);

        Log.d("Contact_Info", "--------------------------------------------------------------------");

//        dbHelper.addContact("Jack", "987654321");
//        dbHelper.addContact("John", "897654321");
//        dbHelper.addContact("Jennet", "789654321");
//        dbHelper.addContact("Jstar", "678954321");

//        ContactModel contact = new ContactModel();
//        contact.id = 1;
//        contact.name = "Jack";
//        contact.number = "1234567890";
//        dbHelper.updateContact(contact);

        dbHelper.deleteContact(4);

        ArrayList<ContactModel> arrContacts = dbHelper.fetchContacts();

        for (ContactModel model : arrContacts) {
            Log.d("Contact_Info", "Name: " + model.name + "     Phone No: " + model.number);
        }

    }
}