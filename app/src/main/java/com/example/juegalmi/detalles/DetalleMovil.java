package com.example.juegalmi.detalles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.juegalmi.R;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.DispositivoMovil;
import com.example.juegalmi.model.Videojuego;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleMovil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleMovil extends Fragment {
    private TextView txtTitulo, txtDetalles, txtMarca, txtProcesador, txtPantalla, txtPrecio;
    private ImageView imgFoto;

    public DetalleMovil() {
        // Required empty public constructor
    }

    public static DetalleMovil newInstance(Bundle args) {
        DetalleMovil fragment = new DetalleMovil();

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
        View vista = inflater.inflate(R.layout.fragment_detalle_movil, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        txtTitulo = vista.findViewById(R.id.txtTitulo);
        txtDetalles = vista.findViewById(R.id.txtDetalles);
        txtMarca = vista.findViewById(R.id.txtMarca);
        txtProcesador = vista.findViewById(R.id.txtProcesador);
        txtPantalla = vista.findViewById(R.id.txtPantalla);
        txtPrecio = vista.findViewById(R.id.txtPrecio);
        imgFoto = vista.findViewById(R.id.imgFoto);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments().containsKey("articulo"))
        {
            Articulo articulo = (Articulo) getArguments().getSerializable("articulo");
            Log.d("calll", String.valueOf(articulo.getIdarticulo()));
            Call<List<DispositivoMovil>> call = ApiAdaptador.getApiService().getProducto(articulo.getIdarticulo());
            call.enqueue(new Callback<List<DispositivoMovil>>() {
                @Override
                public void onResponse(Call<List<DispositivoMovil>> call, Response<List<DispositivoMovil>> response) {
                    if(response.isSuccessful()){
                        List<DispositivoMovil> lr = response.body();

                        txtTitulo.setText(articulo.getArticulonombre());
                        txtDetalles.setText(lr.get(0).getAlmacenamiento() + "GB, " + lr.get(0).getRam() + "GB RAM");  //falta el color///////////
                        txtMarca.setText(articulo.getIdmarca().getNombre());
                        //txtMarca.setText(articulo.getIdmarca().getNombre());  //falta el procesador//////////////////////////////////////////
                        txtPantalla.setText(lr.get(0).getTamanoPantalla() + "''");
                        txtPrecio.setText(articulo.getPrecio() + "€");
                        Glide
                                .with(getContext())
                                .load("https://retoasel.duckdns.org/images/" + articulo.getFoto())
                                .into(imgFoto);
                    }else{
                        Toast.makeText(getContext(), "No se ha podido obtener el videojuego", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<DispositivoMovil>> call, Throwable t) {
                    Log.d("calll", String.valueOf(call.request()));
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}