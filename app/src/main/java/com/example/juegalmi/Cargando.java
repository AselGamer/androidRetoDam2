package com.example.juegalmi;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Cargando extends AppCompatActivity {
    private ProgressBar barra;
    private ImageView imagen;
    private TextView texto;
    private TypedArray imagenes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando);

        //Obtener los datos del XML Parametro: R.array porque es un array de Strings, que se convierte en un typed array
        barra = findViewById(R.id.pbAnimacion);

        //Se obtienen las referencias de la barra de carga, del ImageView y del TextView
        imagen = findViewById(R.id.imgCargando);
        texto = findViewById(R.id.tvAnimacion);
        imagenes = getResources().obtainTypedArray(R.array.loading);

        //Se configura la barra de carga
        barra.setMax(100);  //Valor máximo
        barra.setProgress(0);   //Inicialización
        barra.setBackgroundColor(Color.GRAY);   //Color de fondo

        AsynctaskCargando cargando = new AsynctaskCargando(this);
        cargando.execute();  //Ejecución del hilo
    }

    public ProgressBar getBarra() {
        return barra;
    }

    public void setBarra(ProgressBar barra) {
        this.barra = barra;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public TextView getTexto() {
        return texto;
    }

    public void setTexto(TextView texto) {
        this.texto = texto;
    }

    public TypedArray getImagenes() {
        return imagenes;
    }

    public void setImagenes(TypedArray imagenes) {
        this.imagenes = imagenes;
    }

    public void finalizar() {
        finish();
    }
}
