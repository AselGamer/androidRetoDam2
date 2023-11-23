package com.example.juegalmi.asynctask;

import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AsynctaskCargando extends AsyncTask<Void,Integer,Void> {
    private int numFoto = 0;
    private ProgressBar barra;
    private ImageView imagen;
    private TextView texto;
    private Cargando pantalla;
    private TypedArray imagenes;

    public AsynctaskCargando(Cargando actividad) {
        this.pantalla = actividad;
        barra = actividad.getBarra();
        imagen = actividad.getImagen();
        texto = actividad.getTexto();
        imagenes = actividad.getImagenes();
    }

    //Es la parte que se ejecuta en segundo plano en el hilo
    @Override
    protected Void doInBackground(Void... voids) {
        for(int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(100);  //ralentiza cada ejecucion 100 milisegundos
                if(numFoto >= 3)    //numero de fotos del loading: 4
                {
                    numFoto = 0;
                } else
                {
                    numFoto++;
                }
                publishProgress(numFoto, i); //recoge todas las variables y los mete en values
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    //Se utiliza para mostrar al usuario el progreso de lo que se está haciendo en la función doInBackground
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //values es el array recogido con todas las variables de PublishProgress
        barra.setProgress(values[1]);   //values[1] -> i (todos los i recogidos)
        texto.setText(values[1] + "%");
        Glide
                .with(pantalla)    //el activity donde va a aparecer
                .load(imagenes.getResourceId(values[0],-1)) //obtener la imagen con el id (values[0] -> numFoto (todos los numFoto recogidos)) del TypedArray
                .into(imagen);  //el ImageView
    }

    //Se ejecuta una vez terminado el hilo
    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        pantalla.finalizar();
    }

    //Se ejecuta antes de lanzar el hilo
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
