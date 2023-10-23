package com.ripalnakiya.a25fragmentlifecycle3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity.Test", "onCreate: Activity");

        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.home){
                    loadFragment(new FirstFragment(), flag);
                } else if(id == R.id.search){
                    loadFragment(new SecondFragment(), flag);
                } else {
                    loadFragment(new ThirdFragment(), flag);
                }
                    return true;
            }
        });
        navigation.setSelectedItemId(R.id.home);
    }
    public void loadFragment(Fragment fragment, boolean f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(flag){
            ft.add(R.id.container, fragment);
            flag = false;
        }
        else {
            ft.replace(R.id.container, fragment);
        }

        ft.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity.Test", "onStart: Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity.Test", "onResume: Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity.Test", "onPause: Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity.Test", "onStop: Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity.Test", "onRestart: Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity.Test", "onDestroy: Activity");
    }
}