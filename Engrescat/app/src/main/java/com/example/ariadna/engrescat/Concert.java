package com.example.ariadna.engrescat;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Ariadna on 2/12/2016.
 */

public class Concert {
    private long Id;
    private String Nom;
    private ArrayList<String> Grups;
    private String DataHora;
    private String Lloc;
    private String Adr;
    private String Pobl;
    private Float  Preu;
    private String Desc;

    public Concert(long id, String nom, ArrayList<String> grups, String dataHora, String lloc, String adr,String pobl, Float preu, String desc) {
        Id = id;
        Nom = nom;
        Grups = grups;
        DataHora = dataHora;
        Lloc = lloc;
        Adr = adr;
        Pobl = pobl;
        Preu = preu;
        Desc = desc;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

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
                '\n' + Desc+'\n';
    }
}
