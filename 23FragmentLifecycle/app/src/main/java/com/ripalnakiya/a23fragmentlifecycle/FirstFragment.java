package com.ripalnakiya.a23fragmentlifecycle;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("MainActivity.Test", "onAttach: Fragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity.Test", "onCreate: Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MainActivity.Test", "onCreateView: Fragment");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("MainActivity.Test", "onViewCreated: Fragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MainActivity.Test", "onStart: Fragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MainActivity.Test", "onResume: Fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MainActivity.Test", "onPause: Fragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MainActivity.Test", "onStop: Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MainActivity.Test", "onDestroyView: Fragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity.Test", "onDestroy: Fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MainActivity.Test", "onDetach: Fragment");
    }
}