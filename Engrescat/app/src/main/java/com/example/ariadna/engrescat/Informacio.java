package com.example.ariadna.engrescat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;

public class Informacio extends AppCompatActivity {

    private ArrayList<Concert>concerts;
    private ListView llg;
    private int _IdConcert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacio);

        _IdConcert = getIntent().getIntExtra("EXTRA_IDCONCERT", 0);
        final boolean isFilter = getIntent().getBooleanExtra("isFilter", false);

        concertsdb.setContext(this);


        Concert con;
        if (isFilter){
            con=concertsdb.loadFInfo(_IdConcert);
        }

        else {
            con = concertsdb.loadInfo(_IdConcert);
        }

        TextView nom = (TextView)findViewById(R.id.nom_concert);
        nom.setText(con.getNom());
        TextView poble = (TextView)findViewById(R.id.poblacio);
        poble.setText(con.getPobl());
        TextView hora = (TextView)findViewById(R.id.hora);
        hora.setText(con.getDataHora());
        TextView preu = (TextView)findViewById(R.id.preu);
        preu.setText(con.getPreu()+"€");
        TextView lloc = (TextView)findViewById(R.id.lloc);
        lloc.setText(con.getLloc());
        TextView adreca = (TextView)findViewById(R.id.adreca);
        adreca.setText(con.getAdr());
        TextView descripcio = (TextView)findViewById(R.id.descripcio);
        descripcio.setText(con.getDesc());
        ImageView im=(ImageView)findViewById(R.id.imatge);
        im.setImageBitmap(con.getImg());

        ArrayAdapter<String> grupsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                con.getGrups() );

        llg=(ListView)findViewById(R.id.grups);
        llg.setAdapter(grupsAdapter);
    }
}