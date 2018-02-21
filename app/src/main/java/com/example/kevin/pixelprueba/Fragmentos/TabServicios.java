package com.example.kevin.pixelprueba.Fragmentos;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kevin.pixelprueba.Adaptadores.AdaptadorProductos;
import com.example.kevin.pixelprueba.Adaptadores.AdaptadorServicios;
import com.example.kevin.pixelprueba.Clases.MainActivity;
import com.example.kevin.pixelprueba.Clases.Productos;
import com.example.kevin.pixelprueba.Clases.Servicios;
import com.example.kevin.pixelprueba.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TabServicios extends Fragment {

    private OnFragmentInteractionListener mListener;

    Login lg = new Login();
    RecyclerView recyclerServicios;
    ArrayList<Servicios> listaServicios;
    MainActivity actividad = new MainActivity();
    TextView info;
    SQLiteCursor crServicios;

    private String url= "http://192.168.0.22:8000/ws/servicios";

    public TabServicios() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tab_servicios, container, false);

        listaServicios = new ArrayList<>();
        recyclerServicios = (RecyclerView) view.findViewById(R.id.recyclerServicios);
        recyclerServicios.setLayoutManager(new LinearLayoutManager(getContext()));
        info = (TextView)view.findViewById(R.id.info2);

        if(actividad.isInternet(getContext())==true &&  Login.pulsado==false) {

            recyclerServicios.setVisibility(View.VISIBLE);
            cargarServicios();

        }else{
            recyclerServicios.setVisibility(View.VISIBLE);
            cargarSQlite();
        }

        return view;
    }

    private void cargarSQlite() {
        crServicios = (SQLiteCursor)Login.dbPixel.rawQuery("SELECT * FROM servicios", null);

        if(crServicios.moveToFirst()){

            do{
                String product = crServicios.getString(crServicios.getColumnIndex("product"));
                String user = crServicios.getString(crServicios.getColumnIndex("user"));
                String name = crServicios.getString(crServicios.getColumnIndex("name"));
                String coments = crServicios.getString(crServicios.getColumnIndex("coments"));
                String date = crServicios.getString(crServicios.getColumnIndex("date"));

                Servicios servicio = new Servicios(product, user, name, coments, date);
                listaServicios.add(servicio);

            }while(crServicios.moveToNext());

            AdaptadorServicios adapter2 = new AdaptadorServicios(listaServicios);
            recyclerServicios.setAdapter(adapter2);


        }else{
            info.setVisibility(View.VISIBLE);
        }
    }

    private void cargarServicios() {

        JsonObjectRequest jsonPeticion;
        jsonPeticion = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray servicios;
                JSONObject servicio;

                try {

                    servicios = response.getJSONArray("servicios");

                    String sql="DELETE FROM servicios";
                    Login.dbPixel.execSQL(sql);

                    for(int i=0; i<servicios.length();i++){

                        servicio = servicios.getJSONObject(i);

                        String name_s = servicio.getString("name");
                        String usuario = servicio.getString("usuario");
                        String producto = servicio.getString("producto");
                        String coments = servicio.getString("coments");
                        String date = servicio.getString("date");

                        sql="INSERT INTO servicios(product, user, name, coments, date) VALUES('"+producto+"','"+usuario+"','"+name_s+"','"+coments+"','"+date+"')";
                        Login.dbPixel.execSQL(sql);

                    }

                    cargarSQlite();

                    Log.d("lista", listaServicios.get(0).getName_s());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
                Log.d("volley_error",error.toString());
            }
        });

        Volley.newRequestQueue(getContext()).add(jsonPeticion);


    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
