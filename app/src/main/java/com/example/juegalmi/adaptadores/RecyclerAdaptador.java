package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.juegalmi.R;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.detalles.DetalleVideojuego;
import com.example.juegalmi.dialogs.DialogFoto;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.tabsArriba.Videojuegos;

import java.util.ArrayList;

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

        holder.txtJuego.setText(listaArticulos.get(position).getArticulonombre());
        holder.txtPrecio.setText(String.valueOf(listaArticulos.get(position).getPrecio()));
        holder.txtDesarrollador.setText(listaArticulos.get(position).getIdmarca().getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DE AQUI A DETALLE VIDEOJUEGO, CONSOLA, MOVIL...
                LayoutInflater inflater = LayoutInflater.from(context);  //Declaración del inflador

                //Carga de la vista. Parametros: 1.-Archivo XML, 2.-El linear layout
                View vista = inflater.inflate(R.layout.imagen_texto, holder.itemView.findViewById(R.id.layoutCard));
                ImageView imgImagen = vista.findViewById(R.id.imgImagen);

                activity.cambiarTitulo("Videojuego");

                activity.cambiarFragmento(new DetalleVideojuego()); //con Bundle?

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
        TextView txtJuego, txtPrecio, txtDesarrollador;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgImagen);
            txtJuego = itemView.findViewById(R.id.txtJuego);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtDesarrollador = itemView.findViewById(R.id.txtDesarrollador);
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
