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

import java.util.ArrayList;

public class Llista extends AppCompatActivity {

    private ArrayList<Concert>concerts;
    private ListView llc;
    private ConcertsAdapter adapter;


    public class ConcertsAdapter extends ArrayAdapter<Concert> {
        /*private final Activity context;
        private final String[] itemname;
        private final Bitmap integers;*/

        /*ConcertsAdapter(Activity context, String[] itemname, Bitmap integers) {
        super(Llista.this, R.layout.item_concert, concertsdb.loadConcerts());
        this.context=context;
        this.itemname=itemname;
        this.integers=integers;
    }*/
        ConcertsAdapter() {
            super(Llista.this, R.layout.item_concert,
                    concertsdb.loadConcerts());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = convertView;
            if (result == null) {
                LayoutInflater inflater = getLayoutInflater();
                result = inflater.inflate(R.layout.item_concert, parent, false);
            }
            // Obtener inflater.
            /*LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/

            // ¿Existe el view actual?
           /* if (null == convertView) {
                convertView = inflater.inflate(
                        R.layout.item_concert,
                        parent,
                        false);
            }*/

            // Referencias UI.

            Concert con =getItem(position);
            ImageView im=(ImageView)result.findViewById(R.id.imatge);
            im.setImageBitmap(con.getImg());
            TextView nom = (TextView) result.findViewById(R.id.nom_concert);
            nom.setText(con.getNom());
            TextView poble = (TextView)result.findViewById(R.id.poblacio);
            poble.setText(con.getPobl());
            TextView hora = (TextView) result.findViewById(R.id.hora);
            hora.setText(con.getDataHora().substring(con.getDataHora().indexOf(" ") + 1));

            // Lead actual.
            //concerts lead = getItem(position);

            /*avatar.setImageBitmap(integers[position]);
            nom.setText(itemname[position]);
            poble.setText(itemname[position]);
            hora.setText(itemname[position]);*/

            // Setup.
            /*Concert.with(getContext()).load(lead.getImage()).into(avatar);
            nom.setText(lead.getNom());
            poble.setText(lead.getPobl());
            hora.setText(lead.getDataHora());*/

            return convertView;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista);

        concertsdb.setContext(this);

        adapter = new ConcertsAdapter();

        concerts= new ArrayList<>();

        concerts=concertsdb.loadConcerts();

        llc=(ListView)findViewById(R.id.llc);
        llc.setAdapter(adapter);
    }
}