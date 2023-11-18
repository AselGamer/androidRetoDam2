package com.example.juegalmi.io;

import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.DispositivoMovil;
import com.example.juegalmi.model.Etiqueta;
import com.example.juegalmi.model.Login;
import com.example.juegalmi.model.Producto;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.Transaccion;
import com.example.juegalmi.model.Usuario;
import com.example.juegalmi.model.Videojuego;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServicio {

    /*@GET("login")
    Call<ArrayList<Login>> getUsuario();*/

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<Respuesta> login(@Body Login login);

    @GET("usuario/data")
    Call<Usuario> getAutorizacion(@Header("Authorization") String authToken);

    @GET("articulos")
    Call<List<Articulo>> getArticulos();

    @GET("articulos/tipo/{tipoarticulo}")
    Call<List<Articulo>> getArticulos(@Path("tipoarticulo") String tipoarticulo);

    @GET("articulos/ver/{idarticulo}")
    Call<List<Producto>> getProducto(@Path("idarticulo") int idarticulo);

    @GET("articulos/tipos")
    Call<Map<String, List<Articulo>>> getAllByType();

    @GET("articulos/etiquetas")
    Call<Map<String, List<Articulo>>> getAllVideojuegos();

    @GET("articulos/marcas/tipo/Consola")
    Call<Map<String, List<Articulo>>> getAllConsolas();

    @GET("articulos/marcas/tipo/DispositivoMovil")
    Call<Map<String, List<Articulo>>> getAllMoviles();

    @GET("etiquetas")
    Call<List<Etiqueta>> getEtiquetas();

    @GET("transaccion/{tipoTransaccion}")
    Call<List<Transaccion>> getCompras(@Header("Authorization") String authToken, @Path("tipoTransaccion") String tipoTransaccion);

}
