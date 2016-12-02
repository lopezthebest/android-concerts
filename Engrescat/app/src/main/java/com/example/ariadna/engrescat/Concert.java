package com.example.ariadna.engrescat;

/**
 * Created by Ariadna on 2/12/2016.
 */

public class Concert {
    private long Id;
    private String Nom;
    private String Desc;
    private String Lloc;
    private Long Data;
    private Long  Preu;

    public Concert(long id, String nom, String desc, String lloc, Long data, Long preu) {
        Id = id;
        Nom = nom;
        Desc = desc;
        Lloc = lloc;
        Data = data;
        Preu = preu;
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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getLloc() {
        return Lloc;
    }

    public void setLloc(String lloc) {
        Lloc = lloc;
    }

    public Long getData() {
        return Data;
    }

    public void setData(Long data) {
        Data = data;
    }

    public Long getPreu() {
        return Preu;
    }

    public void setPreu(Long preu) {
        Preu = preu;
    }

    @Override
    public String toString() {
        return Id + '\n' + Nom + '\n' + Desc + '\n' + Lloc + '\n' + Data + '\n' + Preu;
    }
}
