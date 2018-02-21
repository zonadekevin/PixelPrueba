package com.example.kevin.pixelprueba.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jon on 30/01/2018.
 */

public class SQLite extends SQLiteOpenHelper{

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabla;

        tabla = "CREATE TABLE IF NOT EXISTS productos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, tipe TEXT, price TEXT, stock TEXT, description TEXT, url TEXT)";
        db.execSQL(tabla);

        tabla = "CREATE TABLE IF NOT EXISTS servicios (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, product TEXT, user TEXT, name TEXT, coments TEXT, date TEXT)";
        db.execSQL(tabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
