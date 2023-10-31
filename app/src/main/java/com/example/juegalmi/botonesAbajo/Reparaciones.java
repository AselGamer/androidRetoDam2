package com.example.juegalmi.botonesAbajo;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.SpinnerAdaptador;

public class Reparaciones extends Fragment {

    private TextView tvSpinner;
    private Spinner sp;
    private int ver=0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflater
        View vista = inflater.inflate(R.layout.fragment_reparaciones, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        sp = vista.findViewById(R.id.spDispositivo);

        //Recogemos los datos de un array de recursos de XML
        String[] dispositivos = getResources().getStringArray(R.array.dispositivos); //El array viene de frames.xml

        //1er paso: Cogemos un adaptador sin modificar y le damos al Play
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.dispositivos, android.R.layout.simple_spinner_dropdown_item);
        //2º paso: Cogemos un adaptador que hemos hecho nosotros
        //El adapter
        SpinnerAdaptador adapter = new SpinnerAdaptador(getContext(), dispositivos);

        //Asignamos el adaptador
        sp.setAdapter(adapter);

        //sp.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

        //Generamos el listener al seleccionar elemento
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(ver == 1){
                    //2º paso: Para poder ver como el elemento seleccionado se selecciona se muestra un Toast con el texto
                    Toast.makeText(adapterView.getContext(), "Opción seleccionada: " + adapterView.getItemAtPosition(i - 1), Toast.LENGTH_SHORT).show();
                }
                ver = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return vista;
    }
}