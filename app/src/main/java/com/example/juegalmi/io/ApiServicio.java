package com.example.juegalmi.io;

import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;
import com.example.juegalmi.model.Login;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.Usuario;

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

    @GET("articulos/tipo")
    Call<Map<String, List<Articulo>>> getAllByType();

    @GET("etiquetas")
    Call<List<Etiqueta>> getEtiquetas();

}
