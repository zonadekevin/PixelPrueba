package com.example.kevin.pixelprueba.Fragmentos;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kevin.pixelprueba.Adaptadores.AdaptadorProductos;
import com.example.kevin.pixelprueba.Clases.MainActivity;
import com.example.kevin.pixelprueba.Clases.Productos;
import com.example.kevin.pixelprueba.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TabProductos extends Fragment {

    private OnFragmentInteractionListener mListener;

    Login lg = new Login();
    TextView info;
    RecyclerView recyclerProductos;
    ArrayList<Productos> listaProductos;
    MainActivity actividad = new MainActivity();
    SQLiteCursor crProductos;
    BigInteger bigInt;

    private String url= "http://192.168.0.22:8000/ws/productos";

    public TabProductos() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tab_productos, container, false);

        listaProductos = new ArrayList<>();
        recyclerProductos = (RecyclerView)view.findViewById(R.id.recyclerProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        info = (TextView)view.findViewById(R.id.info1);

        if(actividad.isInternet(getContext())==true && Login.pulsado==false){
            recyclerProductos.setVisibility(View.VISIBLE);

            cargarProductos();

        }
        else{
            cargarSQlite();
        }



        return view;
    }

    private void cargarSQlite() {
        crProductos = (SQLiteCursor)Login.dbPixel.rawQuery("SELECT * FROM productos", null);

        if(crProductos.moveToFirst()){
            recyclerProductos.setVisibility(View.VISIBLE);

            do{
                String name = crProductos.getString(crProductos.getColumnIndex("name"));
                String tipe = crProductos.getString(crProductos.getColumnIndex("tipe"));
                String price = crProductos.getString(crProductos.getColumnIndex("price"));
                String stock = crProductos.getString(crProductos.getColumnIndex("stock"));
                String description = crProductos.getString(crProductos.getColumnIndex("description"));
                String url = crProductos.getString(crProductos.getColumnIndex("url"));

                Productos pro = new Productos(name, tipe, price, stock, description, url);
                listaProductos.add(pro);

            }while(crProductos.moveToNext());

            AdaptadorProductos adapter2 = new AdaptadorProductos(listaProductos, getContext());
            recyclerProductos.setAdapter(adapter2);


        }else{
            info.setVisibility(View.VISIBLE);
        }
    }

    private void cargarProductos() {

        JsonObjectRequest jsonPeticion;
        jsonPeticion = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray productos;
                JSONObject producto;

                try {
                    productos = response.getJSONArray("productos");

                    String sql="DELETE FROM productos";
                    Login.dbPixel.execSQL(sql);

                    for(int i=0; i<productos.length();i++){

                        producto = productos.getJSONObject(i);

                        String name = producto.getString("name");
                        String tipe = producto.getString("tipe");
                        String price = producto.getString("price");
                        String stock = producto.getString("stock");
                        String description = producto.getString("description");
                        String url = producto.getString("url");

                        sql="INSERT INTO productos(name, tipe, price, stock, description, url) VALUES('"+name+"', '"+tipe+"', '"+price+"', '"+stock+"', '"+description+"', '"+url+"')";
                        Login.dbPixel.execSQL(sql);

                    }

                   cargarSQlite();

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
