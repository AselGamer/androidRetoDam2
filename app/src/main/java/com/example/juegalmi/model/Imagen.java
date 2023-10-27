package com.example.juegalmi.model;

import java.io.Serializable;

public class Imagen implements Serializable {
    String url = "";
    String texto1 = "";
    String texto2 = "";
    String texto3 = "";

    public Imagen(String url, String texto1, String texto2, String texto3) {
        this.url = url;
        this.texto1 = texto1;
        this.texto2 = texto2;
        this.texto3 = texto3;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTexto1() {
        return texto1;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    public String getTexto2() {
        return texto2;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    public String getTexto3() {
        return texto3;
    }

    public void setTexto3(String texto3) {
        this.texto3 = texto3;
    }
}
