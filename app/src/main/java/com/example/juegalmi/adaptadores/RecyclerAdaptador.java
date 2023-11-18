package com.example.juegalmi.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.juegalmi.R;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.detalles.DetalleConsola;
import com.example.juegalmi.detalles.DetalleMovil;
import com.example.juegalmi.detalles.DetalleVideojuego;
import com.example.juegalmi.dialogs.DialogFoto;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.tabsArriba.Consolas;
import com.example.juegalmi.tabsArriba.Moviles;
import com.example.juegalmi.tabsArriba.Videojuegos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdaptador extends RecyclerView.Adapter<RecyclerAdaptador.MiViewHolder>{

    private Bundle bundle = new Bundle();
    private Context context;
    private ArrayList<Articulo> listaArticulos;
    private boolean buscador;
    private int ver = 0;
    private IControlFragmentos activity;

    public RecyclerAdaptador(Context context, ArrayList<Articulo> listaArticulos, boolean buscador) {
        this.context = context;
        this.listaArticulos = listaArticulos;
        this.buscador = buscador;
    }

    @NonNull
    @Override
    public RecyclerAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista;
        if(buscador == true){
            vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen_texto_buscador, parent, false);
        }else{
            vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen_texto, parent, false);
        }
        //txtSubtitulo = vista.findViewById(R.id.txtSubtitulo);
        return new MiViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdaptador.MiViewHolder holder, int position) {

        Glide
                .with(context)
                .load("https://retoasel.duckdns.org/images/" + listaArticulos.get(position).getFoto())
                .into(holder.imagen);

        holder.txtTexto1.setText(listaArticulos.get(position).getArticulonombre());
        holder.txtTexto2.setText(String.valueOf(listaArticulos.get(position).getPrecio()));
        holder.txtTexto3.setText(listaArticulos.get(position).getIdmarca().getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DE AQUI A DETALLE VIDEOJUEGO, CONSOLA, MOVIL...
                LayoutInflater inflater = LayoutInflater.from(context);  //Declaración del inflador

                //Carga de la vista. Parametros: 1.-Archivo XML, 2.-El linear layout
                View vista;
                if(buscador == true){
                    vista = inflater.inflate(R.layout.imagen_texto, holder.itemView.findViewById(R.id.layoutCard));
                }else{
                    vista = inflater.inflate(R.layout.imagen_texto_buscador, holder.itemView.findViewById(R.id.layoutCardBuscador));
                }

                //ImageView imgImagen = vista.findViewById(R.id.imgImagen);

                Articulo articulo = listaArticulos.get(position);

                activity.cambiarTitulo(listaArticulos.get(position).getTipoarticulo());

                /*bundle.putString("texto1", holder.txtTexto1.getText().toString());
                bundle.putString("texto2", holder.txtTexto2.getText().toString());
                bundle.putString("texto3", holder.txtTexto3.getText().toString());*/
                bundle.putSerializable("articulo", articulo);

                if(listaArticulos.get(position).getTipoarticulo().equals("Videojuego")){
                    activity.cambiarFragmento(DetalleVideojuego.newInstance(bundle));
                }else if(listaArticulos.get(position).getTipoarticulo().equals("Consola")){
                    activity.cambiarFragmento(DetalleConsola.newInstance(bundle));
                }else if(listaArticulos.get(position).getTipoarticulo().equals("DispositivoMovil")){
                    activity.cambiarFragmento(DetalleMovil.newInstance(bundle));
                }

                //vista.getContext().get

                /*dialog = new DialogFoto(listaFotos.getResourceId(position, -1));
                dialog.show(((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager(), "imgFoto");*/  //Abrimos el fragmento
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        activity = (IControlFragmentos) context;
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    class MiViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView txtTexto1, txtTexto2, txtTexto3;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgImagen);
            txtTexto1 = itemView.findViewById(R.id.txtTexto1);
            txtTexto2 = itemView.findViewById(R.id.txtTexto2);
            txtTexto3 = itemView.findViewById(R.id.txtTexto3);
        }
    }

    public void filtrar(ArrayList<Articulo> filtro){
        listaArticulos = filtro;
        notifyDataSetChanged();
    }

    public void setListaArticulos(ArrayList<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }
}
