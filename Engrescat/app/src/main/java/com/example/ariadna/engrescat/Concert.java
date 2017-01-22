package com.example.ariadna.engrescat;

import android.graphics.Bitmap;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Ariadna on 2/12/2016.
 */

public class Concert {
    private long Id;
    private Bitmap Img;
    private String Nom;
    private ArrayList<String> Grups;
    private String DataHora;
    private String Lloc;
    private String Adr;
    private String Pobl;
    private Float  Preu;
    private String Desc;
    private Float Lat;
    private Float Lon;

    public Concert(long id, Bitmap img, String nom, ArrayList<String> grups, String dataHora, String lloc, String adr,String pobl, Float preu, String desc, Float lat, Float lon) {
        Id = id;
        Img=img;
        Nom = nom;
        Grups = grups;
        DataHora = dataHora;
        Lloc = lloc;
        Adr = adr;
        Pobl = pobl;
        Preu = preu;
        Desc = desc;
        Lat = lat;
        Lon = lon;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Bitmap getImg() { return Img; }

    public void setImg(Bitmap img) { Img = img; }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public ArrayList<String> getGrups() {
        return Grups;
    }

    public void setGrups(ArrayList<String> grups) {
        Grups = grups;
    }

    public String getDataHora() {
        return DataHora;
    }

    public void setDataHora(String dataHora) {
        DataHora = dataHora;
    }

    public String getLloc() {
        return Lloc;
    }

    public void setLloc(String lloc) {
        Lloc = lloc;
    }

    public String getAdr() {
        return Adr;
    }

    public void setAdr(String adr) {
        Adr = adr;
    }

    public String getPobl() {
        return Pobl;
    }

    public void setPobl(String pobl) {
        Pobl = pobl;
    }

    public Float getPreu() {
        return Preu;
    }

    public void setPreu(Float preu) {
        Preu = preu;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Float getLat() { return Lat;}

    public void setLat(Float lat) { Lat = lat; }

    public Float getLon() { return Lon; }

    public void setLon(Float lon) { Lon = lon; }
    

    @Override
    public String toString() {
        return Id +
                '\n' + Nom +
                '\n' + Grups +
                '\n' + DataHora +
                '\n' + Lloc +
                '\n' + Adr +
                '\n' + Pobl +
                '\n' + Preu +"€"+
                '\n' + Desc+
                '\n' + Lon+
                '\n'+ Lat+ '\n';
    }
}
