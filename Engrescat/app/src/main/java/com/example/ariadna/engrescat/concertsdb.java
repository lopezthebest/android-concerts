package com.example.ariadna.engrescat;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Ariadna on 2/12/2016.
 */

public class concertsdb {

    private static Context context;

    public static void setContext(Context context) {
        concertsdb.context = context;
    }

    public static void readConcertsList(SQLiteDatabase db) {
        ArrayList<String> concert = new ArrayList<>();
        try {
            InputStreamReader is= new InputStreamReader(context.getAssets().open("concerts.csv"));
            BufferedReader reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String a="INSERT INTO Concerts Values(" +
                        Long.parseLong(parts[0])+",'" +
                        parts[1] +"','" +
                        parts[2] +"','" +
                        parts[3] +"','" +
                        parts[4] +"'," +
                        Float.parseFloat(parts[5]) +",'" +
                        parts[6] +"','" +
                        parts[7]+"');";
                db.execSQL(a);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void readPoblacionsList(SQLiteDatabase db) {
        ArrayList<String> concert = new ArrayList<>();
        try {
            InputStreamReader is= new InputStreamReader(context.getAssets().open("poblacions.csv"));
            BufferedReader reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String a="INSERT INTO Poblacions Values(" +
                        Long.parseLong(parts[0])+",'" +
                        parts[1] +"');";
                db.execSQL(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readGrupConcertList(SQLiteDatabase db) {
        ArrayList<String> concert = new ArrayList<>();
        try {
            InputStreamReader is= new InputStreamReader(context.getAssets().open("grupconcert.csv"));
            BufferedReader reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String a="INSERT INTO GrupConcert Values(" +
                        Long.parseLong(parts[0])+"," +
                        Long.parseLong(parts[1]) +");";
                db.execSQL(a);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void readGrupsList(SQLiteDatabase db) {
        ArrayList<String> concert = new ArrayList<>();
        try {
            InputStreamReader is= new InputStreamReader(context.getAssets().open("grups.csv"));
            BufferedReader reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String a="INSERT INTO Grups Values(" +
                        Long.parseLong(parts[0])+",'" +
                        parts[1] +"');";
                db.execSQL(a);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

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

        public concertsDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TAULA_POBLACIONS);
            db.execSQL(SQL_CREATE_TAULA_CONCERTS);
            db.execSQL(SQL_CREATE_TAULA_GRUPS);
            db.execSQL(SQL_CREATE_TAULA_GRUPCONCERT);
            readPoblacionsList(db);
            readConcertsList(db);
            readGrupsList(db);
            readGrupConcertList(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    private static concertsDbHelper helper;

    public static ArrayList<Concert> loadConcerts(){
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
                Bitmap img = null;

                File file = new File(context.getExternalFilesDir(null), "concert-" + id + ".jpg");
                try{
                    FileInputStream fileInputStream = new FileInputStream(file);
                    img = BitmapFactory.decodeStream(fileInputStream);
                    Log.i("imatges", "He llegit: " + file.getAbsolutePath().toString());
                }catch (IOException io){
                    Log.w("imatges", "ERROR llegint: " + file.getAbsolutePath().toString());
                }
                resultat.add(new Concert(id, img, nom, grups, datahora, lloc, adr, pobl, preu, desc));
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        return resultat;
    }


}
