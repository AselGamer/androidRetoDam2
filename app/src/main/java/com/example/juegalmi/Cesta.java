package com.example.juegalmi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.adaptadores.CestaAdaptador;
import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.DetallePedidoTransaccion;
import com.example.juegalmi.model.Producto;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.TransaccionPedido;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cesta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cesta extends Fragment {
    private ListView listCesta = null;
    private ArrayList<Articulo> listaArticulos;
    private ArrayList<Integer> listaCantidad;
    private LinearLayout layRecycler;
    private RecyclerView recyclerCesta;
    private CestaAdaptador cestaAdaptador;
    private IControlFragmentos activity;
    private TextView txtPrecioTotal;
    private Button btnComprar;

    public Cesta() {
        // Required empty public constructor
    }

    public static Cesta newInstance(Bundle args) {
        Cesta fragment = new Cesta();

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
        View vista = inflater.inflate(R.layout.fragment_cesta, container, false);

        layRecycler = vista.findViewById(R.id.layRecycler);

        recyclerCesta = vista.findViewById(R.id.recyclerBuscador);

        if(getArguments().containsKey("articulo"))
        {
            Articulo articulo = (Articulo) getArguments().getSerializable("articulo");

            listaArticulos = new ArrayList<>(activity.obtenerListaArticulosCesta());
            listaCantidad = new ArrayList<>(activity.obtenerListaCantidad());

            boolean encontrado = false;

            for(int i=0; i<listaArticulos.size(); i++){
                if(listaArticulos.get(i).getIdarticulo() == articulo.getIdarticulo()){
                    activity.cambiarTitulo("Cesta (" + listaArticulos.size() + ")");
                    listaCantidad.set(i, listaCantidad.get(i) + 1);
                    activity.cambiarListaCantidad(listaCantidad);
                    encontrado = true;
                    break;
                }
            }
            if(encontrado == false){
                listaArticulos.add(articulo);
                activity.cambiarTitulo("Cesta (" + listaArticulos.size() + ")");
                activity.cambiarListaArticulosCesta(articulo);
                listaCantidad.add(1);
                activity.cambiarListaCantidad(listaCantidad);
            }

            recyclerCesta.setLayoutManager(new LinearLayoutManager(vista.getContext()));
            cestaAdaptador = new CestaAdaptador(vista.getContext(), listaArticulos, listaCantidad);
            recyclerCesta.setAdapter(cestaAdaptador);
        }else if(getArguments().containsKey("listaArticulos")){
            listaArticulos = new ArrayList<>(activity.obtenerListaArticulosCesta());
            listaCantidad = new ArrayList<>(activity.obtenerListaCantidad());

            recyclerCesta.setLayoutManager(new LinearLayoutManager(vista.getContext()));
            cestaAdaptador = new CestaAdaptador(vista.getContext(), listaArticulos, listaCantidad);
            recyclerCesta.setAdapter(cestaAdaptador);
        }else if(getArguments().containsKey("cambiarTitulo")){
            listaArticulos = new ArrayList<>(activity.obtenerListaArticulosCesta());
            listaCantidad = new ArrayList<>(activity.obtenerListaCantidad());

            activity.cambiarTitulo("Cesta (" + listaArticulos.size() + ")");

            recyclerCesta.setLayoutManager(new LinearLayoutManager(vista.getContext()));
            cestaAdaptador = new CestaAdaptador(vista.getContext(), listaArticulos, listaCantidad);
            recyclerCesta.setAdapter(cestaAdaptador);
        }

        txtPrecioTotal = vista.findViewById(R.id.txtPrecioTotal);
        btnComprar = vista.findViewById(R.id.btnComprar);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        float precio = 0;
        /*for(int i=0; i<activity.obtenerListaArticulos().size(); i++){
            precio = precio + activity.obtenerListaArticulos().get(i).getPrecio() * activity.obtenerListaCantidad().get(i);
        }*/
        for(int i=0; i<listaArticulos.size(); i++){
            precio = precio + listaArticulos.get(i).getPrecio() * listaCantidad.get(i);
        }
        txtPrecioTotal.setText(precio + "€");

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetallePedidoTransaccion[] detalles = new DetallePedidoTransaccion[listaArticulos.size()];
                for(int i=0; i<listaArticulos.size(); i++){
                    DetallePedidoTransaccion detallePedidoTransaccion = new DetallePedidoTransaccion(listaArticulos.get(i).getIdarticulo());
                    detalles[i] = detallePedidoTransaccion;
                }

                TransaccionPedido transaccionPedido = new TransaccionPedido("30", "30", "Compra", detalles);

                for(int i=0; i<transaccionPedido.getDetalles().length; i++){
                    System.out.println(transaccionPedido.getDetalles()[i].getIdarticulo());
                }

                Call<Respuesta> call2 = ApiAdaptador.getApiService().tramitarTransaccion("Bearer " + activity.obtenerToken(), transaccionPedido);
                call2.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call2, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            activity.cambiarTitulo("Mis Pedidos");
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.contenedor, new MisPedidos())
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
    }
}