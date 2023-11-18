package com.example.juegalmi.detalles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.R;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Producto;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.Videojuego;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleVideojuego#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleVideojuego extends Fragment {
    private TextView txtTitulo, txtPlataforma, txtCategoria, txtPrecioAlquiler, txtPrecioCompra;

    public DetalleVideojuego() {
        // Required empty public constructor
    }

    public static DetalleVideojuego newInstance(Bundle args) {
        DetalleVideojuego fragment = new DetalleVideojuego();

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
        View vista = inflater.inflate(R.layout.fragment_detalle_videojuego, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        txtTitulo = vista.findViewById(R.id.txtTitulo);
        txtPlataforma = vista.findViewById(R.id.txtPlataforma);
        txtCategoria = vista.findViewById(R.id.txtCategoria);
        txtPrecioAlquiler = vista.findViewById(R.id.txtPrecioAlquiler);
        txtPrecioCompra = vista.findViewById(R.id.txtPrecioCompra);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*if(getArguments().containsKey("texto1"))
        {
            txtTitulo.setText(getArguments().getString("texto1"));
        }
        if(getArguments().containsKey("texto2"))
        {
            txtTitulo.setText(getArguments().getString("texto2"));
        }
        if(getArguments().containsKey("texto3"))
        {
            txtTitulo.setText(getArguments().getString("texto3"));
        }*/
        if(getArguments().containsKey("articulo"))
        {
            /*Articulo articulo = (Articulo) getArguments().getSerializable("articulo");
            Call<Videojuego> call = ApiAdaptador.getApiService().getProducto(articulo.getIdarticulo());
            call.enqueue(new Callback<Videojuego>() {
                @Override
                public void onResponse(Call<Videojuego> call, Response<Videojuego> response) {
                    if(response.isSuccessful()){
                        Videojuego videojuego = (Videojuego) response.body();

                        txtTitulo.setText(articulo.getArticulonombre());
                        txtPlataforma.setText(videojuego.getIdplataforma().getNombre());
                        for(int i=0; i<videojuego.getEtiquetas().length; i++){
                            txtCategoria.setText(videojuego.getEtiquetas()[i].getNombre());
                        }
                        //txtPrecioAlquiler.setText(articulo.getArticulonombre());
                        txtPrecioCompra.setText(articulo.getPrecio() + "€");
                    }else{
                        Toast.makeText(getContext(), "No se ha podido obtener el videojuego", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Videojuego> call, Throwable t) {
                    Log.d("calll", String.valueOf(call.request()));
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}