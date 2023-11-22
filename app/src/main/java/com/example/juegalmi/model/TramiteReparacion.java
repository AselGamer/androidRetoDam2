package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TramiteReparacion implements Serializable {
    @SerializedName("problema")
    private String problema;

    public TramiteReparacion(String problema) {
        this.problema = problema;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }
}
