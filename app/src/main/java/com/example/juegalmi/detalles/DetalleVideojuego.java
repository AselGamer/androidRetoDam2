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

import java.util.List;

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

        if(getArguments().containsKey("articulo"))
        {
            Articulo articulo = (Articulo) getArguments().getSerializable("articulo");
            Call<List<Producto>> call = ApiAdaptador.getApiService().getProducto(articulo.getIdarticulo());
            call.enqueue(new Callback<List<Producto>>() {
                @Override
                public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                    if(response.isSuccessful()){
                        //Casting de List<Producto> a List<DispositivoMovil>
                        /*List<DispositivoMovil> lr = response.body()
                                .stream()
                                .filter(DispositivoMovil.class::isInstance)
                                .map(DispositivoMovil.class::cast)
                                .collect(Collectors.toList());*/

                        Videojuego videojuego = (Videojuego) response.body();

                        txtTitulo.setText(articulo.getArticulonombre());
                        txtPlataforma.setText(videojuego.getIdplataforma().getNombre());
                        String etiquetas = "";
                        for(int i=0; i<videojuego.getEtiquetas().length; i++){
                            if(i==0){
                                etiquetas = videojuego.getEtiquetas()[i].getNombre();
                            }else{
                                etiquetas = etiquetas + ", " + videojuego.getEtiquetas()[i].getNombre();
                            }
                        }
                        txtCategoria.setText(etiquetas);
                        //txtPrecioAlquiler.setText(articulo.getArticulonombre());  //falta precio alquiler
                        txtPrecioCompra.setText(articulo.getPrecio() + "€");
                    }else{
                        Toast.makeText(getContext(), "No se ha podido obtener el videojuego", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Producto>> call, Throwable t) {
                    Log.d("calll", String.valueOf(call.request()));
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}