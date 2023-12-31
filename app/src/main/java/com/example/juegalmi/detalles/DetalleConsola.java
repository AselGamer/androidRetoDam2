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
 * Use the {@link DetalleConsola#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleConsola extends Fragment {
    private TextView txtTitulo, txtDetalles, txtMarca, txtNumMandos, txtPlataformas, txtPrecio, txtAlmacenamiento;
    private ImageView imgFoto;
    private Button btnAnadir;
    private IControlFragmentos activity;
    private Bundle bundle = new Bundle();

    public DetalleConsola() {
        // Required empty public constructor
    }

    public static DetalleConsola newInstance(Bundle args) {
        DetalleConsola fragment = new DetalleConsola();

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
        View vista = inflater.inflate(R.layout.fragment_detalle_consola, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        txtTitulo = vista.findViewById(R.id.txtTitulo);
        txtMarca = vista.findViewById(R.id.txtMarca);
        txtNumMandos = vista.findViewById(R.id.txtNumMandos);
        txtPlataformas = vista.findViewById(R.id.txtPlataformas);
        txtPrecio = vista.findViewById(R.id.txtPrecio);
        txtAlmacenamiento = vista.findViewById(R.id.txtAlmacenamiento);
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
                        /*List<DispositivoMovil> lr = response.body()
                                .stream()
                                .filter(DispositivoMovil.class::isInstance)
                                .map(DispositivoMovil.class::cast)
                                .collect(Collectors.toList());*/

                        List<Producto> lr = response.body();

                        txtTitulo.setText(articulo.getArticulonombre());
                        txtMarca.setText(articulo.getIdmarca().getNombre());
                        txtNumMandos.setText(lr.get(0).getCantmandos());
                        txtAlmacenamiento.setText(lr.get(0).getAlmacenamiento());
                        String plataformas = "";
                        for(int i=0; i<lr.get(0).getPlataformas().length; i++){
                            if(i==0){
                                plataformas = lr.get(0).getPlataformas()[i].getNombre();
                            }else{
                                plataformas = plataformas + ", " + lr.get(0).getPlataformas()[i].getNombre();
                            }
                        }
                        txtPlataformas.setText(plataformas);
                        txtPrecio.setText(articulo.getPrecio() + "€");
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