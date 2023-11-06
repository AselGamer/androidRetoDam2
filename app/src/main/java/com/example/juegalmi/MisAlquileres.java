package com.example.juegalmi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.juegalmi.interfaces.IControlFragmentos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisAlquileres#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisAlquileres extends Fragment {

    private Button btnDetalle;
    private IControlFragmentos activity;

    public MisAlquileres() {
        // Required empty public constructor
    }

    public static MisAlquileres newInstance(String param1, String param2) {
        MisAlquileres fragment = new MisAlquileres();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_mis_alquileres, container, false);

        btnDetalle = vista.findViewById(R.id.btnDetalle);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Alquiler");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new Alquiler())
                        .commit();
            }
        });
    }
}