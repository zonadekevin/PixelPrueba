package com.example.kevin.pixelprueba.Clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Jon on 23/01/2018.
 */

public class Productos implements Serializable{

    Context contexto;

    private String name, tipe, price, stock, description, img;

    public Productos(){

    }

    public Productos(String name, String tipe, String price, String stock, String description, String img) {
        this.name = name;
        this.tipe = tipe;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getTipe() {
        return tipe;
    }

    public String getPrice() {
        return price;
    }

    public String getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public String getImg(){
        return img;
    }

}
