package com.example.juegalmi.io;

import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;
import com.example.juegalmi.model.Login;
import com.example.juegalmi.model.Respuesta;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServicio {

    /*@GET("login")
    Call<ArrayList<Login>> getUsuario();*/

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<Respuesta> login(@Body Login login);

    /*@GET("articulos")
    Call<List<Articulo>> getAutorizacion(@Header("Authorization") String authToken);*/

    @GET("articulos")
    Call<List<Articulo>> getArticulos();

    @GET("etiquetas")
    Call<List<Etiqueta>> getEtiquetas();

    /*@GET("images")
    Call<List<Articulo>> getArticulos();*/

}
