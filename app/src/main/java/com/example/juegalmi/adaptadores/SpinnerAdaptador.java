package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.juegalmi.R;

public class SpinnerAdaptador extends BaseAdapter {
    private Context context;
    private String[] datos;

    public SpinnerAdaptador(@NonNull Context context, String[] datos) {
        this.context = context;
        this.datos = datos;
    }

    //Devuelve el numero de vistas que se van a retornar
    @Override
    public int getCount() {
        return datos.length;   //+1 porque tambien está la vista "Selecciona dispositivo "
    }

    //Devuelve el dato correspondiente a la fila
    @Nullable
    @Override
    public String getItem(int position) {
        return datos[position];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //Vista
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getVistaPersonalizada(position, convertView, parent);
    }

    //Vista desplegable
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Se instancia el inflador
        LayoutInflater inflater = LayoutInflater.from(context);

        //Cargamos la vista personalizada: inflater.inflate(la vista a cargar, grupo de vistas del spinner_personalizado, false para que no de errores)
        View fila = inflater.inflate(R.layout.spinner_personalizado, parent, false);

        //Se accede a lo que quieras
        TextView texto = fila.findViewById(R.id.txtSpinner);
        texto.setText(datos[position]);

        return fila;
    }

    //Para personalizar la vista de cada uno de los elementos que compone el Spinner
    public View getVistaPersonalizada(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //Se instancia el inflador
        LayoutInflater inflater = LayoutInflater.from(context);

        //Cargamos la vista personalizada: inflater.inflate(la vista a cargar, grupo de vistas del spinner_personalizado, false para que no de errores)
        View fila = inflater.inflate(R.layout.spinner_personalizado, parent, false);

        //Se accede a lo que quieras
        TextView texto = fila.findViewById(R.id.txtSpinner);
        texto.setText("Selecciona dispositivo");
        texto.setTextColor(fila.getResources().getColor(R.color.colorTabSeleccionado, null));

        return fila;
    }
}
