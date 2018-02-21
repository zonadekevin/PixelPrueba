package com.example.kevin.pixelprueba.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevin.pixelprueba.Clases.Servicios;
import com.example.kevin.pixelprueba.R;

import java.util.ArrayList;

/**
 * Created by Jon on 24/01/2018.
 */

public class AdaptadorServicios extends RecyclerView.Adapter<AdaptadorServicios.ViewHolderServicios>{

    ArrayList<Servicios> listaServicios;

    public AdaptadorServicios(ArrayList<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }

    @Override
    public ViewHolderServicios onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicio, null, false);
        return new ViewHolderServicios(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderServicios holder, int position) {

        holder.name_s.setText(listaServicios.get(position).getName_s());
        holder.usuario.setText(listaServicios.get(position).getUsuario());
        holder.producto.setText(listaServicios.get(position).getProducto());
        holder.fecha.setText(listaServicios.get(position).getDate());
        //holder.comentario.setText(listaServicios.get(position).getComents());


    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public class ViewHolderServicios extends RecyclerView.ViewHolder {

        TextView usuario, name_s, producto, fecha, comentario;

        public ViewHolderServicios(View itemView) {
            super(itemView);

            usuario = (TextView)itemView.findViewById(R.id.usuario);
            name_s = (TextView)itemView.findViewById(R.id.name_s);
            producto = (TextView)itemView.findViewById(R.id.producto);
            fecha = (TextView)itemView.findViewById(R.id.fecha);
            //comentario = (TextView)itemView.findViewById(R.id.comentario);
        }
    }
}
