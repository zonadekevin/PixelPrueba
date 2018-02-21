package com.example.kevin.pixelprueba.Clases;

import java.io.Serializable;

/**
 * Created by Jon on 24/01/2018.
 */

public class Servicios implements Serializable{

    private String producto, usuario, name_s, coments, date;


    public Servicios() {
    }

    public Servicios(String producto, String usuario, String name_s, String coments, String date) {
        this.producto = producto;
        this.usuario = usuario;
        this.name_s = name_s;
        this.coments = coments;
        this.date = date;
    }

    public String getProducto() {
        return producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getName_s() {
        return name_s;
    }

    public String getComents() {
        return coments;
    }

    public String getDate() {
        return date;
    }
}
