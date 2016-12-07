package com.example.ariadna.engrescat;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ariadna on 2/12/2016.
 */

public class concertsdb {

    private static Context context;
    static class concertsDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "concerts.db";
        private static final int DATABASE_VERSION = 1;

        private static final String SQL_CREATE_TAULA_CONCERTS =
                "CREATE TABLE Concerts (" +
                        "id INTEGER PRIMARY KEY," +
                        "Nom TEXT," +
                        "Desc TEXT," +
                        "Lloc TEXT," +
                        "Dia INTEGER," +
                        "Preu INTEGER" +
                        ")";

        private static final String SQL_INSERT_1 =
                "INSERT INTO Concerts Values(1,'Hola','que tal', 'sqlite', 2,3)";

        public concertsDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TAULA_CONCERTS);
            db.execSQL(SQL_INSERT_1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    private static concertsDbHelper helper;

    public static ArrayList<Concert> loadConcerts(Context context){
        ArrayList<Concert> resultat= new ArrayList<>();
        if (helper == null) {
            helper = new concertsDbHelper(context);
        }

        SQLiteDatabase db =helper.getReadableDatabase();

        String[] columnes={"id", "Nom", "Desc", "Lloc", "Dia", "Preu"};

        Cursor c = db.query("Concerts", columnes, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndexOrThrow("id"));
                String nom = c.getString(c.getColumnIndexOrThrow("Nom"));
                String desc  = c.getString(c.getColumnIndexOrThrow("Desc"));
                String lloc =c.getString(c.getColumnIndexOrThrow("Lloc"));
                long dia = c.getLong(c.getColumnIndexOrThrow("Dia"));
                long preu = c.getLong(c.getColumnIndexOrThrow("Preu"));
                resultat.add(new Concert(id, nom, desc, lloc, dia, preu));
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return resultat;
    }


}
