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
                        "id INTEGER PRIMARY KEY NOT NULL," +
                        "Nom TEXT," +
                        "DataHora TEXT NOT NULL,"+
                        "Lloc TEXT,"+
                        "Adr TEXT,"+
                        "Pobl INTEGER NOT NULL,"+
                        "Preu REAL NOT NULL,"+
                        "Desc TEXT," +
                        "FOREIGN KEY (Pobl) REFERENCES Poblacions(id)"+
                        ")";

        private static final String SQL_CREATE_TAULA_POBLACIONS =
                "CREATE TABLE Poblacions ("+
                        "id INTEGER PRIMARY KEY NOT NULL,"+
                        "Nom TEXT NOT NULL"+
                        ")";

        private static final String SQL_CREATE_TAULA_GRUPCONCERT =
                "CREATE TABLE GrupConcert ("+
                        "Concert INTEGER NOT NULL,"+
                        "Grup INTEGER NOT NULL,"+
                        "PRIMARY KEY (Concert, Grup),"+
                        "FOREIGN KEY (Concert) REFERENCES Concerts(id),"+
                        "FOREIGN KEY (Grup) REFERENCES Grups(id)"+
                        ")";
        private static final String SQL_CREATE_TAULA_GRUPS=
                "CREATE TABLE Grups ("+
                        "id INTEGER PRIMARY KEY NOT NULL,"+
                        "Nom TEXT NOT NULL"+
                        ")";


        private static final String SQL_INSERT_CONCERTS =
                "INSERT INTO Concerts Values(1, 'Hola', '2017-01-01 22:00', 'aqui', 'C/aquest', 1, 1.5, 'blabla')";

        private static final String SQL_INSERT_POBLACIONS=
                "INSERT INTO Poblacions Values(1,'POBLE')";

        private static final String SQL_INSERT_GRUPCONCERT=
                "INSERT INTO GrupConcert Values(1,1)";

        private static final String SQL_INSERT_GRUPS=
                "INSERT INTO Grups Values(1,'grup')";

        private static final String SQL_INSERT_GRUPCONCERT2=
                "INSERT INTO GrupConcert Values(1,2)";

        private static final String SQL_INSERT_GRUPS2=
                "INSERT INTO Grups Values(2,'grup2')";

        public concertsDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TAULA_CONCERTS);
            db.execSQL(SQL_CREATE_TAULA_POBLACIONS);
            db.execSQL(SQL_CREATE_TAULA_GRUPCONCERT);
            db.execSQL(SQL_CREATE_TAULA_GRUPS);
            db.execSQL(SQL_INSERT_CONCERTS);
            db.execSQL(SQL_INSERT_POBLACIONS);
            db.execSQL(SQL_INSERT_GRUPCONCERT);
            db.execSQL(SQL_INSERT_GRUPS);
            db.execSQL(SQL_INSERT_GRUPCONCERT2);
            db.execSQL(SQL_INSERT_GRUPS2);

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

        Cursor c = db.query("Concerts", null, null, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndexOrThrow("id"));
                String nom = c.getString(c.getColumnIndexOrThrow("Nom"));
                String select="SELECT Grups.Nom FROM Grups INNER JOIN GrupConcert ON Grups.id=GrupConcert.grup WHERE GrupConcert.concert="+id;
                Cursor a=db.rawQuery(select, null);
                ArrayList<String> grups=new ArrayList<>();
                if (a != null && a.getCount() > 0) {
                    while (a.moveToNext()) {
                        String grup = a.getString(a.getColumnIndexOrThrow("Grups.Nom"));
                        grups.add(grup);
                    }
                }
                String datahora =c.getString(c.getColumnIndexOrThrow("DataHora"));
                String lloc =c.getString(c.getColumnIndexOrThrow("Lloc"));
                String adr =c.getString(c.getColumnIndexOrThrow("Adr"));
                Long p=c.getLong(c.getColumnIndexOrThrow("Pobl"));
                String select2="SELECT Nom FROM Poblacions WHERE id="+p;
                Cursor b=db.rawQuery(select2, null);
                String pobl= "";
                if (b != null && b.getCount() > 0) {
                    while (b.moveToNext()) {
                        pobl = b.getString(b.getColumnIndexOrThrow("Nom"));
                    }
                }
                Float preu = c.getFloat(c.getColumnIndexOrThrow("Preu"));
                String desc  = c.getString(c.getColumnIndexOrThrow("Desc"));
                resultat.add(new Concert(id, nom, grups, datahora, lloc, adr, pobl, preu, desc));
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return resultat;
    }


}
