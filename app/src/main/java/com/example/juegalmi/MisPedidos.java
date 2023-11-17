package com.example.juegalmi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.adaptadores.TipoAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Transaccion;
import com.example.juegalmi.model.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisPedidos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisPedidos extends Fragment {
    private TextView txtNumTransaccion, fechaTransaccion, precioTransaccion;
    private Button btnDetalle;
    private IControlFragmentos activity;

    public MisPedidos() {
        // Required empty public constructor
    }

    public static MisPedidos newInstance(String param1, String param2) {
        MisPedidos fragment = new MisPedidos();
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
        View vista = inflater.inflate(R.layout.fragment_mis_pedidos, container, false);

        txtNumTransaccion = vista.findViewById(R.id.txtNumTransaccion);
        fechaTransaccion = vista.findViewById(R.id.fechaTransaccion);
        precioTransaccion = vista.findViewById(R.id.precioTransaccion);
        btnDetalle = vista.findViewById(R.id.btnDetalle);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Call<List<Transaccion>> call = ApiAdaptador.getApiService().getCompras("Bearer " + activity.obtenerToken(), "Compra");
        call.enqueue(new Callback<List<Transaccion>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Transaccion>> call, Response<List<Transaccion>> response) {
                if(response.isSuccessful()){
                    List<Transaccion> lr = response.body();

                    /*TipoAdaptador tipoAdaptador = new TipoAdaptador(vista.getContext(), lr);

                    listaTipos = vista.findViewById(R.id.listTipos);
                    listaTipos.setAdapter(tipoAdaptador);*/
                }else{
                    Toast.makeText(getContext(), "No se han podido cargar los articulos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Transaccion>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });


        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Pedido");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new Pedido())
                        .commit();
            }
        });
    }
}