package com.example.ariadna.engrescat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Llista extends AppCompatActivity {

    private ArrayList<Concert>concerts;
    private ListView llc;
    private ConcertsAdapter adapter;
    private String poble;
    private String data;
    private String grup;
    boolean isFilter = false;
    boolean antmapa =false;


    public class ConcertsAdapter extends ArrayAdapter<Concert> {
        ConcertsAdapter() {
            super(Llista.this, R.layout.item_concert,
                   concerts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = convertView;
            if (result == null) {
                LayoutInflater inflater = getLayoutInflater();
                result = inflater.inflate(R.layout.item_concert, parent, false);
            }

            // Referencias UI.

            Concert con =getItem(position);
            ImageView im=(ImageView)result.findViewById(R.id.imatge);
            im.setImageBitmap(con.getImg());
            TextView nom = (TextView) result.findViewById(R.id.nom_concert);
            nom.setText(con.getNom());
            TextView poble = (TextView)result.findViewById(R.id.poblacio);
            poble.setText(con.getPobl());
            TextView hora = (TextView) result.findViewById(R.id.hora);
            hora.setText(con.getDataHora());
            TextView preu = (TextView) result.findViewById(R.id.preu);
            preu.setText(con.getPreu()+"€");

            return result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista);

        demanaPermisosCarpeta();

        concertsdb.setContext(this);

        llc=(ListView)findViewById(R.id.llc);

        isFilter = getIntent().getBooleanExtra("isFilter", false);
        antmapa =getIntent().getBooleanExtra("antmapa", false);
        if (isFilter) {

            concerts=concertsdb.antllista(isFilter);
            adapter = new ConcertsAdapter();
        }
        else{
            concerts=concertsdb.loadConcerts();
            adapter = new ConcertsAdapter();
        }

        llc.setAdapter(adapter);
        llc.setClickable(true);
        llc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(getApplicationContext(), Informacio.class);

                intent.putExtra("EXTRA_IDCONCERT", (int)arg3);
                intent.putExtra("isFilter", isFilter);
                startActivity(intent);
            }
        });
    }

    public void filtra(View view){
        Intent intent = new Intent(this,activity_filtre.class);
        startActivity(intent);
    }

    public void obremapa(View view){
        Intent intent = new Intent(this,MapActivity.class);
        intent.putExtra("isFilter", isFilter);
        startActivity(intent);
    }

    private void demanaPermisosCarpeta() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1 // request code... ?
            );
        }
    }

}