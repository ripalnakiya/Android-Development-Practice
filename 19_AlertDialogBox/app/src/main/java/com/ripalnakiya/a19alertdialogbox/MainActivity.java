package com.ripalnakiya.a19alertdialogbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        // Single Button Alert Dialog Box
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

                alertDialog.setIcon(R.drawable.info);
                alertDialog.setTitle("Terms & Conditions");
                alertDialog.setMessage("Have you read all T&C ?");

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"I have read", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Single Button Complete", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });

        // Two Button Alert Dialog Box
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                alertDialog.setIcon(R.drawable.delete);
                alertDialog.setTitle("Delete ?");
                alertDialog.setMessage("Are you sure you want to delete");
                
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Deletion Canceled!", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        // Three Button Alert Box
        AlertDialog.Builder alretDialog = new AlertDialog.Builder(MainActivity.this);

        alretDialog.setIcon(R.drawable.exit);
        alretDialog.setTitle("Exit ?");
        alretDialog.setMessage("Are you sure you want to Exit");

        alretDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        });

        alretDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
            }
        });

        alretDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Exit Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        alretDialog.show();
    }
}