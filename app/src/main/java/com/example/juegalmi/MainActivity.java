package com.example.juegalmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.botonesAbajo.Galeria;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.botonesAbajo.Reparaciones;
import com.example.juegalmi.botonesAbajo.SobreNosotros;
import com.example.juegalmi.botonesAbajo.Ubicacion;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Busqueda;
import com.example.juegalmi.model.Etiqueta;
import com.example.juegalmi.model.Login;
import com.example.juegalmi.model.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IControlFragmentos, SearchView.OnQueryTextListener {

    private BottomNavigationView bottomNavigationView;
    private TextView txtTitulo;
    private ImageButton imgUsuario, imgCesta;
    private String txtSesion = "";
    private Call<ArrayList<Login>> call;
    private SearchView buscador;
    private LinearLayout.LayoutParams params, paramsMP;
    private RecyclerAdaptador adaptador, adaptadorBuscador;
    private RecyclerView recyclerBuscador;
    private ArrayList<Articulo> listaArticulos;
    private ArrayList<Etiqueta> listaEtiquetas;
    private LinearLayout layRecycler, contenedor;
    private Usuario usuario;
    private String token = "";
    private int numBusqueda = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final Intent intent = new Intent(this, Cargando.class);
        startActivity(intent);*/

        listaArticulos = new ArrayList<>();
        listaEtiquetas = new ArrayList<>();

        //rellenarArticulos();
        //rellenarArticulosBuscador();

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsMP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        txtTitulo = findViewById(R.id.txtTitulo);
        buscador = findViewById(R.id.buscador);
        layRecycler = findViewById(R.id.layRecycler);
        contenedor = findViewById(R.id.contenedor);

        buscador.setOnQueryTextListener(this);

        recyclerBuscador = findViewById(R.id.recyclerBuscador);
        recyclerBuscador.setLayoutManager(new GridLayoutManager(this, 1));
        adaptadorBuscador = new RecyclerAdaptador(this, listaArticulos, true);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelectedListener);
        //Muestra el fragment inicial cuando la aplicación se inicia
        bottomNavigationView.setSelectedItemId(R.id.productos);

        imgUsuario = findViewById(R.id.imgUsuario);
        imgUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario == null){
                    txtTitulo.setText("Iniciar Sesión");
                    cambiarParametros();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new IniciarSesion())
                            .commit();
                }else{
                    txtTitulo.setText("Hola " + usuario.getNombre());
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
                if(usuario == null){
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

    private void cambiarParametros(){   //sin buscador
        params.height = 90;
        params.width = 700;
        params.setMargins(20, 50, 100, 0);
        txtTitulo.setLayoutParams(params);
        txtTitulo.setVisibility(View.VISIBLE);
        buscador.setVisibility(View.INVISIBLE);
    }

    private void cambiarParametrosBuscador(){   //con buscador
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
        if(usuario == null && !titulo.equals("Crear Cuenta")){
            cambiarParametrosBuscador();
        }else{
            cambiarParametros();
        }
    }

    @Override
    public void cambiarSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Usuario obtenerSesion() {
        return usuario;
    }

    @Override
    public void cambiarToken(String token) {
        this.token = token;
    }

    @Override
    public String obtenerToken() {
        return token;
    }

    @Override
    public ArrayList<Articulo> obtenerListaArticulos() {
        return listaArticulos;
    }

    @Override
    public ArrayList<Etiqueta> obtenerListaEtiquetas() {
        return listaEtiquetas;
    }

    @Override
    public void cambiarFragmento(Fragment fragmento) {
        cambiarParametros();
        recyclerBuscador.setVisibility(View.INVISIBLE);
        layRecycler.setLayoutParams(params);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, fragmento)
                .commit();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {    //cuando le damos a buscar
        recyclerBuscador.setAdapter(adaptadorBuscador);
        Busqueda busqueda = new Busqueda(query);
        rellenarArticulosBuscador(busqueda);
        //filtro(query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {  //cada vez que escribamos una letra
        if(newText.equals("")){
            recyclerBuscador.setAdapter(adaptadorBuscador);
            filtro(newText);
        }
        return false;
    }

    private void filtro(String newText) {
        ArrayList<Articulo> listaFiltro = new ArrayList<>();
        recyclerBuscador.setVisibility(View.VISIBLE);
        adaptadorBuscador.filtrar(listaFiltro);
        layRecycler.setLayoutParams(params);
        /*if(!newText.equals("")){
            for(Articulo item : listaArticulos){
                if(item.getArticulonombre().toLowerCase().contains(newText.toLowerCase())){
                    listaFiltro.add(item);
                }
            }
            if(listaFiltro.size() > 0){
                recyclerBuscador.setVisibility(View.VISIBLE);
                adaptadorBuscador.filtrar(listaFiltro);
                layRecycler.setLayoutParams(paramsMP);
            }else{
                recyclerBuscador.setVisibility(View.INVISIBLE);
                layRecycler.setLayoutParams(paramsMP);
                adaptadorBuscador.filtrar(listaArticulos);
            }
        }else{
            recyclerBuscador.setVisibility(View.VISIBLE);
            adaptadorBuscador.filtrar(listaFiltro);
            layRecycler.setLayoutParams(params);
        }*/
    }

    private void rellenarArticulosBuscador(Busqueda busqueda){
        if(numBusqueda%2 == 0){
            Call<List<Articulo>> call = ApiAdaptador.getApiService().buscar(busqueda);
            call.enqueue(new Callback<List<Articulo>>() {
                @Override
                public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                    if(response.isSuccessful()){
                        //ArrayList<Articulo> listaFiltro = new ArrayList<>();
                        listaArticulos.clear();
                        List<Articulo> lr = response.body();
                        for(int i=0; i<lr.size(); i++){
                            listaArticulos.add(new Articulo(lr.get(i).getIdarticulo(), lr.get(i).getArticulonombre(), lr.get(i).getNombre(),
                                    lr.get(i).getTipoarticulo(), lr.get(i).getPrecio(), lr.get(i).getStock(),
                                    lr.get(i).getFoto(), lr.get(i).getIdmarca(), lr.get(i).getIdtipoClase(), lr.get(i).getStockAlquiler()));
                        }
                        recyclerBuscador.setVisibility(View.VISIBLE);
                        adaptadorBuscador.filtrar(listaArticulos);
                        layRecycler.setLayoutParams(paramsMP);
                    }else{
                        Toast.makeText(getApplicationContext(), "No se han podido cargar los articulos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Articulo>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        numBusqueda++;
    }

}