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
import android.widget.EditText;
import android.widget.TextView;

import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.interfaces.IControlFragmentos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IniciarSesion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IniciarSesion extends Fragment {

    private TextView txtRegistrate;
    private Button btnEnviar;
    private EditText edtEmail;
    private EditText edtContrasenya;
    private IControlFragmentos activity;

    public IniciarSesion() {
        // Required empty public constructor
    }

    public static IniciarSesion newInstance(String param1, String param2) {
        IniciarSesion fragment = new IniciarSesion();
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

    //Metodo para interactuar con la actividad principal
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    //Es el metodo para cargar el layout asociado al fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);

        txtRegistrate = vista.findViewById(R.id.txtRegistrate);
        btnEnviar = vista.findViewById(R.id.btnEnviar);
        edtEmail = vista.findViewById(R.id.edtEmail);
        edtContrasenya = vista.findViewById(R.id.edtContrasenya);

        return vista;
    }

    //Se ejecuta al cargar el fragmento
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Crear Cuenta");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new CrearCuenta())
                        .commit();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmail.getText().toString().equals("Almi") && edtContrasenya.getText().toString().equals("Almi123")){
                    activity.cambiarTitulo("Productos");
                    activity.cambiarSesion("Almi");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new Productos())
                            .commit();
                }else{

                }
            }
        });
    }
}