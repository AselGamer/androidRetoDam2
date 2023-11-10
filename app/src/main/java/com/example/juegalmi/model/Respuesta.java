package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

public class Respuesta {
    @SerializedName("token")
    private String token = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
