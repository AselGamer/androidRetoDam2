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
import com.example.juegalmi.IniciarSesion;
import com.example.juegalmi.MisAlquileres;
import com.example.juegalmi.R;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.DetallePedidoTransaccion;
import com.example.juegalmi.model.DetalleTransaccion;
import com.example.juegalmi.model.Producto;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.Transaccion;
import com.example.juegalmi.model.TransaccionPedido;
import com.example.juegalmi.model.Usuario;

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
    private TextView txtTitulo, txtMarca, txtPlataforma, txtCategoria, txtPrecioAlquiler, txtPrecioCompra;
    private ImageView imgVideojuego;
    private Button btnAnadir, btnAlquilar;
    private IControlFragmentos activity;
    private Bundle bundle = new Bundle();

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_detalle_videojuego, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        txtTitulo = vista.findViewById(R.id.txtTitulo);
        txtMarca = vista.findViewById(R.id.txtMarca);
        txtPlataforma = vista.findViewById(R.id.txtPlataforma);
        txtCategoria = vista.findViewById(R.id.txtCategoria);
        txtPrecioAlquiler = vista.findViewById(R.id.txtPrecioAlquiler);
        txtPrecioCompra = vista.findViewById(R.id.txtPrecioCompra);
        imgVideojuego = vista.findViewById(R.id.imgVideojuego);
        btnAnadir = vista.findViewById(R.id.btnAnadir);
        btnAlquilar = vista.findViewById(R.id.btnAlquilar);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(activity.obtenerSesion() == null){
            btnAnadir.setEnabled(false);
            btnAnadir.setBackgroundColor(Color.rgb(218, 231, 255));
            btnAlquilar.setEnabled(false);
            btnAlquilar.setBackgroundColor(Color.rgb(218, 231, 255));
        }else{
            btnAnadir.setEnabled(true);
            btnAnadir.setBackgroundColor(Color.rgb(143, 178, 241));
            btnAlquilar.setEnabled(true);
            btnAlquilar.setBackgroundColor(Color.rgb(143, 178, 241));
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

                        if(articulo.getArticulonombre() == null){
                            txtTitulo.setText(articulo.getNombre());
                        }else{
                            txtTitulo.setText(articulo.getArticulonombre());
                        }
                        txtMarca.setText(articulo.getIdmarca().getNombre());
                        txtPlataforma.setText(lr.get(0).getIdplataforma().getNombre());
                        String etiquetas = "";
                        for(int i=0; i<lr.get(0).getEtiquetas().length; i++){
                            if(i==0){
                                etiquetas = lr.get(0).getEtiquetas()[i].getNombre();
                            }else{
                                etiquetas = etiquetas + ", " + lr.get(0).getEtiquetas()[i].getNombre();
                            }
                        }
                        txtCategoria.setText(etiquetas);
                        txtPrecioAlquiler.setText(articulo.getPrecio()/2 + "€");    //ya que el precio de alquiler es siempre la mitad
                        txtPrecioCompra.setText(articulo.getPrecio() + "€");
                        Glide
                                .with(getContext())
                                .load("https://retoasel.duckdns.org/images/" + articulo.getFoto())
                                .into(imgVideojuego);

                        btnAnadir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bundle.putSerializable("articulo", articulo);
                                activity.cambiarFragmento(Cesta.newInstance(bundle));
                            }
                        });

                        btnAlquilar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //System.out.println(lr.get(0).getIdarticulo().getStockAlquiler());
                                DetallePedidoTransaccion detallePedidoTransaccion = new DetallePedidoTransaccion(lr.get(0).getIdarticulo().getIdarticulo());
                                DetallePedidoTransaccion[] detalles = new DetallePedidoTransaccion[1];
                                detalles[0] = detallePedidoTransaccion;
                                //System.out.println(detalles[0].getIdarticulo().getStockAlquiler());
                                TransaccionPedido transaccionPedido = new TransaccionPedido("30", "30", "Alquiler", detalles);

                                Call<Respuesta> call2 = ApiAdaptador.getApiService().tramitarTransaccion("Bearer " + activity.obtenerToken(), transaccionPedido);
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
                    }else{
                        Toast.makeText(getContext(), "No se ha podido obtener el videojuego", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Producto>> call, Throwable t) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}