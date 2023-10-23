package com.ripalnakiya.a25fragmentlifecycle3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        Log.d("MainActivity.Test", "onAttach: Fragment A");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity.Test", "onCreate: Fragment A");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MainActivity.Test", "onCreateView: Fragment A");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("MainActivity.Test", "onViewCreated: Fragment A");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MainActivity.Test", "onStart: Fragment A");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MainActivity.Test", "onResume: Fragment A");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MainActivity.Test", "onPause: Fragment A");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MainActivity.Test", "onStop: Fragment A");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MainActivity.Test", "onDestroyView: Fragment A");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity.Test", "onDestroy: Fragment A");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MainActivity.Test", "onDetach: Fragment A");
    }
}