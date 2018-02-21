package com.example.kevin.pixelprueba.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kevin.pixelprueba.Clases.Productos;
import com.example.kevin.pixelprueba.Fragmentos.TabProductos;
import com.example.kevin.pixelprueba.R;

import java.util.ArrayList;

/**
 * Created by Jon on 23/01/2018.
 */

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProductos> {

    Context context;

    ArrayList<Productos> listaProductos;
    Bitmap imagen;

    public AdaptadorProductos(ArrayList<Productos> listaProductos, Context context) {
        this.listaProductos = listaProductos;
        this.context = context;
    }

    @Override
    public ViewHolderProductos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto, null, false);
        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderProductos holder, int position) {
        holder.name.setText(listaProductos.get(position).getName());
        holder.tipe.setText(listaProductos.get(position).getTipe());
        holder.price.setText(listaProductos.get(position).getPrice());
        holder.stock.setText(listaProductos.get(position).getStock());
        Glide.with(context)
                .load(listaProductos.get(position).getImg())
                .override(100, 100) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .into(holder.img);
        //holder.img.setText(listaProductos.get(position).getStock());
        //holder.img.setImageBitmap(Bitmap.createScaledBitmap(listaProductos.get(position).getImg(), 100,100, false));
        //holder.img.setImageBitmap(imagen);



    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder {

        TextView name, tipe, description, price, stock;
        ImageView img;

        public ViewHolderProductos(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            tipe = (TextView)itemView.findViewById(R.id.tipe);
            price = (TextView)itemView.findViewById(R.id.price);
            stock = (TextView)itemView.findViewById(R.id.stock);
            img = (ImageView) itemView.findViewById(R.id.img);
            //description = (TextView)itemView.findViewById(R.id.description);
        }
    }

}
