package com.example.juegalmi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juegalmi.adaptadores.DetalleTransaccionAdaptador;
import com.example.juegalmi.adaptadores.TransaccionAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.DetalleTransaccion;
import com.example.juegalmi.model.Reparacion;
import com.example.juegalmi.model.Transaccion;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pedido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pedido extends Fragment {
    private TextView txtNumPedido, txtTotal;
    private ListView listDetalleTransacciones = null;
    private IControlFragmentos activity;
    private Transaccion transaccion;
    private Reparacion reparacion;

    public Pedido() {
        // Required empty public constructor
    }

    public static Pedido newInstance(Bundle args) {
        Pedido fragment = new Pedido();

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
        View vista = inflater.inflate(R.layout.fragment_pedido, container, false);

        txtNumPedido = vista.findViewById(R.id.txtNumPedido);
        listDetalleTransacciones = vista.findViewById(R.id.listDetalleTransacciones);
        txtTotal = vista.findViewById(R.id.txtTotal);

        DetalleTransaccionAdaptador detalleTransaccionAdaptador;
        if(getArguments().containsKey("transaccion"))
        {
            transaccion = (Transaccion) getArguments().getSerializable("transaccion");

            if(transaccion.getFecha() == null){   //ya que Alquiler no tiene el campo fecha
                detalleTransaccionAdaptador = new DetalleTransaccionAdaptador(vista.getContext(), transaccion);
            }else{
                ArrayList<DetalleTransaccion> listaArticulos = new ArrayList();
                ArrayList<Integer> listaCantidad = new ArrayList();
                for(int i=0; i<transaccion.getDetalles().length; i++){
                    if(i==0){
                        listaArticulos.add(transaccion.getDetalles()[i]);
                        listaCantidad.add(1);
                    }else{
                        for(int j=0; j<listaArticulos.size(); j++){
                            if(transaccion.getDetalles()[i].getIdarticulo().getIdarticulo() == listaArticulos.get(j).getIdarticulo().getIdarticulo()){
                                listaCantidad.set(j, listaCantidad.get(j) + 1);
                            }else{
                                listaArticulos.add(transaccion.getDetalles()[i]);
                                listaCantidad.add(1);
                            }
                        }
                    }
                }

                detalleTransaccionAdaptador = new DetalleTransaccionAdaptador(vista.getContext(), listaArticulos, listaCantidad);
            }

            listDetalleTransacciones = vista.findViewById(R.id.listDetalleTransacciones);
            listDetalleTransacciones.setAdapter(detalleTransaccionAdaptador);
        }else if(getArguments().containsKey("reparacion")){
            reparacion = (Reparacion) getArguments().getSerializable("reparacion");

            detalleTransaccionAdaptador = new DetalleTransaccionAdaptador(vista.getContext(), reparacion);

            listDetalleTransacciones = vista.findViewById(R.id.listDetalleTransacciones);
            listDetalleTransacciones.setAdapter(detalleTransaccionAdaptador);
        }

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println(transaccion.getIdtransaccion());

        txtNumPedido.setText(String.valueOf(transaccion.getIdtransaccion()));

        if(transaccion.getFecha() == null){     //ya que Alquiler no tiene el campo fecha
            txtTotal.setText(transaccion.getPrecio() + "€");
        }else{
            float total = 0;
            for(int i=0; i<transaccion.getDetalles().length; i++){
                total = total + transaccion.getDetalles()[i].getPrecio_total();
            }
            txtTotal.setText(total + "€");
        }
    }
}