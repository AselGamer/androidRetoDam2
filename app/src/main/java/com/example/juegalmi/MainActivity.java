package com.example.juegalmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.botonesAbajo.Galeria;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.botonesAbajo.Reparaciones;
import com.example.juegalmi.botonesAbajo.SobreNosotros;
import com.example.juegalmi.botonesAbajo.Ubicacion;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.io.ApiServicio;
import com.example.juegalmi.model.Imagen;
import com.example.juegalmi.model.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IControlFragmentos, SearchView.OnQueryTextListener {

    private BottomNavigationView bottomNavigationView;
    private TextView txtTitulo;
    private ImageButton imgUsuario, imgCesta;
    private String txtSesion = "";
    private Call<ArrayList<Login>> call;
    private SearchView buscador;
    private LinearLayout.LayoutParams params;
    private RecyclerAdaptador adaptador;
    private RecyclerView recyclerBuscador;
    private ArrayList<Imagen> listaImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Callback
        /*call = ApiAdaptador.getApiService().getUsuario();
        call.enqueue(this); //lo pone en cola*/

        /*Call<Usuario> call = ApiAdaptador.getApiService().getLogin("example@email.com", "Almi123");
        call.enqueue(this);*/

        listaImagenes = new ArrayList<>();
        rellenarFotos();

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        txtTitulo = findViewById(R.id.txtTitulo);
        buscador = findViewById(R.id.buscador);

        recyclerBuscador = findViewById(R.id.recyclerBuscador);
        recyclerBuscador.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adaptador = new RecyclerAdaptador(this, listaImagenes);
        recyclerBuscador.setAdapter(adaptador);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelectedListener);
        //Muestra el fragment inicial cuando la aplicación se inicia
        bottomNavigationView.setSelectedItemId(R.id.productos);

        imgUsuario = findViewById(R.id.imgUsuario);
        imgUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtSesion.equals("")){
                    txtTitulo.setText("Iniciar Sesión");
                    cambiarParametros();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new IniciarSesion())
                            .commit();
                }else{
                    txtTitulo.setText("Hola " + txtSesion);
                    cambiarParametros();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new MiEspacio())
                            .commit();
                }
            }
        });

        imgCesta = findViewById(R.id.imgCesta);
        imgCesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtSesion.equals("")){
                    txtTitulo.setText("Iniciar Sesión");
                    cambiarParametros();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new IniciarSesion())
                            .commit();
                }else{
                    txtTitulo.setText("Cesta");
                    cambiarParametros();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new Cesta())
                            .commit();
                }
            }
        });

        buscador.setOnQueryTextListener(this);
    }

    private boolean onItemSelectedListener(MenuItem item) {
        if(item.getItemId() == R.id.productos) {
            txtTitulo.setText("");
            cambiarParametrosBuscador();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Productos())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.reparaciones) {
            txtTitulo.setText("Reparaciones");
            cambiarParametros();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Reparaciones())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.ubicacion) {
            txtTitulo.setText("Ubicación");
            cambiarParametros();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Ubicacion())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.galeria) {
            txtTitulo.setText("Galería");
            cambiarParametros();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Galeria())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.nosotros) {
            txtTitulo.setText("Sobre Nosotros");
            cambiarParametros();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new SobreNosotros())
                    .commit();
            return true;
        }

        return false;
    }

    private void cambiarParametros(){
        params.height = 90;
        params.width = 700;
        params.setMargins(20, 50, 100, 0);
        txtTitulo.setLayoutParams(params);
        txtTitulo.setVisibility(View.VISIBLE);
        buscador.setVisibility(View.INVISIBLE);
    }

    private void cambiarParametrosBuscador(){
        params.height = 0;
        params.width = 0;
        params.setMargins(0, 0, 0, 0);
        txtTitulo.setLayoutParams(params);
        txtTitulo.setVisibility(View.INVISIBLE);
        buscador.setVisibility(View.VISIBLE);
    }

    @Override
    public void cambiarTitulo(String titulo) {
        txtTitulo.setText(titulo);
        if(txtSesion.equals("") && !titulo.equals("Crear Cuenta")){
            cambiarParametrosBuscador();
        }else{
            cambiarParametros();
        }
    }

    @Override
    public void cambiarSesion(String sesion) {
        txtSesion = sesion;
    }

    @Override
    public String obtenerSesion() {
        return txtSesion;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {    //cuando le damos a buscar
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {  //cada vez que escribamos una letra
        adaptador.filtrar(newText);
        recyclerBuscador.setAdapter(adaptador);
        return false;
    }
/*
    @Override
    public void onResponse(Call<ArrayList<Login>> call, Response<ArrayList<Login>> response) {
        if(response.isSuccessful()){
            ArrayList<Login> logins = response.body();
            Log.d("Dam2", "Tamaño de usuarios: " + logins.size());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Login>> call, Throwable t) {

    }*/

    private void rellenarFotos() {
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        /*listaImagenes.add(new Imagen("https://cdn-icons-png.flaticon.com/512/992/992651.png", "Ver Todo", "", ""));
        listaImagenes.add(new Imagen("https://cdn-icons-png.flaticon.com/512/992/992651.png", "Ver Todo", "", ""));*/
    }
}