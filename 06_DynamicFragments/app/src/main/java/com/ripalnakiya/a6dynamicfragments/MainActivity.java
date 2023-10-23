package com.ripalnakiya.a6dynamicfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFrag1;
        Button btnFrag2;
        Button btnFrag3;

        btnFrag1 = findViewById(R.id.btnFrag1);
        btnFrag2 = findViewById(R.id.btnFrag2);
        btnFrag3 = findViewById(R.id.btnFrag3);

        // Load Initial Fragment
        loadFragment(new OneFragment(), true);

        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new OneFragment(), false);
            }
        });

        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new TwoFragment(), false);
            }
        });

        btnFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ThreeFragment(), false);
            }
        });
    }

    public void loadFragment(Fragment fragment, boolean flag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(flag){
            fragmentTransaction.add(R.id.container, fragment);
        }
        else{
            fragmentTransaction.replace(R.id.container, fragment);
        }
        fragmentTransaction.commit();
    }
}