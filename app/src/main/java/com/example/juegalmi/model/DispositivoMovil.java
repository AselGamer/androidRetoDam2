package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DispositivoMovil extends Producto implements Serializable {
    @SerializedName("iddispositivomovil")
    int iddispositivomovil;
    @SerializedName("almacenamiento")
    String almacenamiento;
    @SerializedName("ram")
    String ram;
    @SerializedName("tamanoPantalla")
    String tamanoPantalla;

    public DispositivoMovil(int iddispositivomovil, String almacenamiento, String ram, String tamanoPantalla, Articulo idarticulo) {
        super(idarticulo);
        this.iddispositivomovil = iddispositivomovil;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
        this.tamanoPantalla = tamanoPantalla;
    }

    public int getIddispositivomovil() {
        return iddispositivomovil;
    }

    public void setIddispositivomovil(int iddispositivomovil) {
        this.iddispositivomovil = iddispositivomovil;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getTamanoPantalla() {
        return tamanoPantalla;
    }

    public void setTamanoPantalla(String tamanoPantalla) {
        this.tamanoPantalla = tamanoPantalla;
    }
}
