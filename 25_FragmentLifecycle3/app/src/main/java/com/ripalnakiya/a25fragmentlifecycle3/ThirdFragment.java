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

public class ThirdFragment extends Fragment {

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("MainActivity.Test", "onAttach: Fragment C");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity.Test", "onCreate: Fragment C");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MainActivity.Test", "onCreateView: Fragment C");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("MainActivity.Test", "onViewCreated: Fragment C");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MainActivity.Test", "onStart: Fragment C");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MainActivity.Test", "onResume: Fragment C");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MainActivity.Test", "onPause: Fragment C");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MainActivity.Test", "onStop: Fragment C");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MainActivity.Test", "onDestroyView: Fragment C");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity.Test", "onDestroy: Fragment C");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MainActivity.Test", "onDetach: Fragment C");
    }
}