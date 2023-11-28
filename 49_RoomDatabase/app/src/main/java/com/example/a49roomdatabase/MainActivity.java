package com.example.a49roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText idText, titleText, amountText;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = findViewById(R.id.idText);
        titleText = findViewById(R.id.titleText);
        amountText = findViewById(R.id.amountText);
        Button insertButton = findViewById(R.id.insertButton);
        Button updateButton = findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button showbutton = findViewById(R.id.showButton);
        resultView = findViewById(R.id.resultView);

        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager(this);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString();
                String amount = amountText.getText().toString();
                databaseManager.expenseDao().addTransaction(new Expense(title, amount));
                Toast.makeText(MainActivity.this, "Record Added", Toast.LENGTH_SHORT).show();
                resetInputs();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(idText.getText().toString());
                String title = titleText.getText().toString();
                String amount = amountText.getText().toString();
                databaseManager.expenseDao().updateTransaction(new Expense(id, title, amount));
                Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                resetInputs();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(idText.getText().toString());
                String title = titleText.getText().toString();
                String amount = amountText.getText().toString();
                databaseManager.expenseDao().deleteTransaction(new Expense(id, title, amount));
                Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                resetInputs();
            }
        });

        showbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Expense> arrayList = (ArrayList<Expense>) databaseManager.expenseDao().getAllTransaction();
                StringBuilder sb = new StringBuilder();
                for(Expense expense : arrayList) {
                    sb.append("Id: ").append(expense.getId()).append(" Title: ").append(expense.getTitle()).append(" Amount: ").append(expense.getAmount()).append("\n");
                }
                resultView.setText(sb.toString());
            }
        });
    }

    private void resetInputs() {
        idText.setText("");
        titleText.setText("");
        amountText.setText("");
    }
}