package com.example.juegalmi.botonesAbajo;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.MisAlquileres;
import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.SpinnerAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.DetallePedidoTransaccion;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.TramiteReparacion;
import com.example.juegalmi.model.TransaccionPedido;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reparaciones extends Fragment {

    private TextView tvSpinner;
    private Spinner sp;
    private int ver=0;
    private EditText edtProblema;
    private Button btnEnviar;
    private IControlFragmentos activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

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
        edtProblema = vista.findViewById(R.id.edtProblema);
        btnEnviar = vista.findViewById(R.id.btnEnviar);

        if(activity.obtenerSesion() == null){
            btnEnviar.setEnabled(false);
            btnEnviar.setBackgroundColor(Color.rgb(218, 231, 255));
        }else{
            btnEnviar.setEnabled(true);
            btnEnviar.setBackgroundColor(Color.rgb(143, 178, 241));
        }

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

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TramiteReparacion tramiteReparacion = new TramiteReparacion(edtProblema.getText().toString());

                Call<Respuesta> call2 = ApiAdaptador.getApiService().tramitarReparacion("Bearer " + activity.obtenerToken(), tramiteReparacion);
                call2.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call2, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            activity.cambiarTitulo("Mis Alquileres");
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.contenedor, new MisAlquileres())
                                    .commit();
                        }else{
                            Toast.makeText(getContext(), "El Token no es correcto", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Respuesta> call2, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return vista;
    }
}