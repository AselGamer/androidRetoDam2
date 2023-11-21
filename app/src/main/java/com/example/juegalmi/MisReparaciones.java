package com.example.juegalmi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.juegalmi.adaptadores.TransaccionAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Reparacion;
import com.example.juegalmi.model.Transaccion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisReparaciones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisReparaciones extends Fragment {
    private ListView listReparaciones = null;
    private IControlFragmentos activity;

    public MisReparaciones() {
        // Required empty public constructor
    }

    public static MisReparaciones newInstance(String param1, String param2) {
        MisReparaciones fragment = new MisReparaciones();
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
        View vista = inflater.inflate(R.layout.fragment_mis_reparaciones, container, false);

        Call<List<Reparacion>> call = ApiAdaptador.getApiService().getReparaciones("Bearer " + activity.obtenerToken());
        call.enqueue(new Callback<List<Reparacion>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Reparacion>> call, Response<List<Reparacion>> response) {
                if(response.isSuccessful()){
                    List<Reparacion> lr = response.body();

                    TransaccionAdaptador transaccionAdaptador = new TransaccionAdaptador(vista.getContext(), lr, true);

                    listReparaciones = vista.findViewById(R.id.listReparaciones);
                    listReparaciones.setAdapter(transaccionAdaptador);
                }else{
                    Toast.makeText(getContext(), "No se han podido cargar las reparaciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Reparacion>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}