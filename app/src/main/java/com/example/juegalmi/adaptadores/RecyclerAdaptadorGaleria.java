package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.juegalmi.DialogFoto;
import com.example.juegalmi.R;
import com.example.juegalmi.model.FotoGaleria;

import java.util.ArrayList;

public class RecyclerAdaptadorGaleria extends RecyclerView.Adapter<RecyclerAdaptadorGaleria.MiViewHolder>{
    private Bundle bundle = new Bundle();
    private Context context;
    private TypedArray listaFotos;
    private String[] listaTextos;
    private DialogFoto dialog = null;

    public RecyclerAdaptadorGaleria(Context context, TypedArray listaFotos, String[] listaTextos) {
        this.context = context;
        this.listaFotos = listaFotos;
        this.listaTextos = listaTextos;
    }

    @NonNull
    @Override
    public RecyclerAdaptadorGaleria.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.foto_texto, parent, false);
        return new RecyclerAdaptadorGaleria.MiViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdaptadorGaleria.MiViewHolder holder, int position) {
        Glide
                .with(context)
                .load(listaFotos.getResourceId(position, -1))
                .into(holder.imagen);

        holder.txtFoto.setText(listaTextos[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TOAST
                /*final int duracion = Toast.LENGTH_LONG; //Declaración del tiempo del Toast
                LayoutInflater inflater = LayoutInflater.from(context);  //Declaración del inflador

                //Carga de la vista. Parametros: 1.-Archivo XML, 2.-El linear layout
                final View miToast = inflater.inflate(R.layout.toast_texto, holder.itemView.findViewById(R.id.layoutToast));  //layoutToast lo recogemos de toast_per.xml

                //Modificación del texto
                TextView miTexto = miToast.findViewById(R.id.txtToast);  //busca el texto mediante el id
                miTexto.setText(listaFotos.get(position).getTexto());    //cambiamos el texto

                //Declaración del Toast
                final Toast toast = new Toast(context);
                toast.setGravity(Gravity.BOTTOM, 0, 100);    //centrar la ventana emergente
                toast.setDuration(duracion);    //fijar la duración
                toast.setView(miToast); //seleccionar la vista cargada con el Inflater
                toast.show();   //ejecutar la función*/

                //DIALOG
                //bundle.putSerializable("foto", listaFotos.get(position));
                LayoutInflater inflater = LayoutInflater.from(context);  //Declaración del inflador

                //Carga de la vista. Parametros: 1.-Archivo XML, 2.-El linear layout
                View miDialog = inflater.inflate(R.layout.dialog_foto, holder.itemView.findViewById(R.id.layoutDialog));
                ImageView imgDialog = miDialog.findViewById(R.id.imgDialog);

                dialog = new DialogFoto(listaFotos.getResourceId(position, -1));
                dialog.show(((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager(), "imgFoto");  //Abrimos el fragmento
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaFotos.length();
    }

    class MiViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView txtFoto;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgImagen);
            txtFoto = itemView.findViewById(R.id.txtFoto);
        }
    }
}
