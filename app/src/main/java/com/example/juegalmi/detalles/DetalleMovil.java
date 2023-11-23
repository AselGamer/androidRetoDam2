package com.example.juegalmi.detalles;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.juegalmi.Cesta;
import com.example.juegalmi.R;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Producto;

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
    private TextView txtTitulo, txtMarca, txtPantalla, txtPrecio, txtAlmacenamiento, txtRam;
    private ImageView imgFoto;
    private Button btnAnadir;
    private IControlFragmentos activity;
    private Bundle bundle = new Bundle();

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_detalle_movil, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        txtTitulo = vista.findViewById(R.id.txtTitulo);
        txtMarca = vista.findViewById(R.id.txtMarca);
        txtPantalla = vista.findViewById(R.id.txtPantalla);
        txtPrecio = vista.findViewById(R.id.txtPrecio);
        txtAlmacenamiento = vista.findViewById(R.id.txtAlmacenamiento);
        txtRam = vista.findViewById(R.id.txtRam);
        imgFoto = vista.findViewById(R.id.imgFoto);
        btnAnadir = vista.findViewById(R.id.btnAnadir);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(activity.obtenerSesion() == null){
            btnAnadir.setEnabled(false);
            btnAnadir.setBackgroundColor(Color.rgb(218, 231, 255));
        }else{
            btnAnadir.setEnabled(true);
            btnAnadir.setBackgroundColor(Color.rgb(143, 178, 241));
        }

        if(getArguments().containsKey("articulo"))
        {
            Articulo articulo = (Articulo) getArguments().getSerializable("articulo");
            Call<List<Producto>> call = ApiAdaptador.getApiService().getProducto(articulo.getIdarticulo());
            call.enqueue(new Callback<List<Producto>>() {
                @Override
                public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                    if(response.isSuccessful()){
                        //Casting de List<Producto> a List<DispositivoMovil>
                        List<Producto> lr = response.body();
                        /*List<DispositivoMovil> lr = response.body()
                                .stream()
                                .filter(DispositivoMovil.class::isInstance)
                                .map(DispositivoMovil.class::cast)
                                .collect(Collectors.toList());*/

                        txtTitulo.setText(articulo.getArticulonombre());
                        txtMarca.setText(articulo.getIdmarca().getNombre());
                        txtPantalla.setText(lr.get(0).getTamanoPantalla() + "''");
                        txtPrecio.setText(articulo.getPrecio() + "€");
                        txtAlmacenamiento.setText(lr.get(0).getAlmacenamiento());
                        txtRam.setText(lr.get(0).getRam() + "GB");
                        Glide
                                .with(getContext())
                                .load("https://retoasel.duckdns.org/images/" + articulo.getFoto())
                                .into(imgFoto);

                        btnAnadir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bundle.putSerializable("articulo", articulo);
                                activity.cambiarFragmento(Cesta.newInstance(bundle));
                            }
                        });

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