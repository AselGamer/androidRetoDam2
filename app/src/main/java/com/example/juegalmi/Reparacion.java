package com.example.juegalmi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reparacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reparacion extends Fragment {

    public Reparacion() {
        // Required empty public constructor
    }

    public static Reparacion newInstance(String param1, String param2) {
        Reparacion fragment = new Reparacion();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reparacion, container, false);
    }
}