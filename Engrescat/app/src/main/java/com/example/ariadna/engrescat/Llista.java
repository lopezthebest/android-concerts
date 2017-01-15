package com.example.ariadna.engrescat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
    private ConcertsFilterAdapter adapterfilter;
    private String poble;
    private String data;
    private String grup;


    public class ConcertsAdapter extends ArrayAdapter<Concert> {
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

    public class ConcertsFilterAdapter extends ArrayAdapter<Concert> {
        ConcertsFilterAdapter() {
            super(Llista.this, R.layout.item_concert,
                    concertsdb.LoadFiltre(poble, data, grup));
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

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_llista, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_llista_button) {
            Intent intent = new Intent(getApplicationContext(), Filtre.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista);

        demanaPermisosCarpeta();

        concertsdb.setContext(this);

        adapter = new ConcertsAdapter();

        //concerts= new ArrayList<>();

        //concerts=concertsdb.loadConcerts();

        llc=(ListView)findViewById(R.id.llc);
        llc.setAdapter(adapter);

        boolean isFilter = getIntent().getBooleanExtra("isFilter", false);
        if (isFilter) {

            poble = getIntent().getStringExtra("EXTRA_POBLE");
            data = getIntent().getStringExtra("EXTRA_DATA");
            grup = getIntent().getStringExtra("EXTRA_GRUP");

            adapterfilter = new ConcertsFilterAdapter();
            llc.setAdapter(adapterfilter);
        }


        llc.setClickable(true);
        llc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(getApplicationContext(), Informacio.class);
                //intent.putExtra("EXTRA_IDCONCERT", adapter.getItem(position).getId());

                intent.putExtra("EXTRA_IDCONCERT", (int)arg3);
                startActivity(intent);
            }
        });
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