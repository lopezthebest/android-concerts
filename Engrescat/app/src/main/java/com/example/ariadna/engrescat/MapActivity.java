package com.example.ariadna.engrescat;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Concert> concerts;
    boolean isFilter=false;
    boolean antmapa=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        isFilter = getIntent().getBooleanExtra("isFilter", false);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        concerts=concertsdb.antllista(isFilter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng centre = new LatLng(41.918629, 2.254944);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centre));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 7) );

        for (Concert concert : concerts) {
            LatLng pos = new LatLng(concert.getLat(), concert.getLon());
            mMap.addMarker(new MarkerOptions().position(pos).title(concert.getNom()));
        }

    }

    public void obrellista(View view){
        Intent intent = new Intent(this,Llista.class);
        intent.putExtra("isFilter", isFilter);
        intent.putExtra("antmapa", antmapa);
        startActivity(intent);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress)
    {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;

    }
}