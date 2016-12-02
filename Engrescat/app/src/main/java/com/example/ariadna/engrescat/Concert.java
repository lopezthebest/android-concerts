package com.example.ariadna.engrescat;

/**
 * Created by Ariadna on 2/12/2016.
 */

public class Concert {
    private String títol;
    private String lloc;

    public Concert(String títol, String lloc) {
        this.títol = títol;
        this.lloc = lloc;
    }

    public String getTítol() {
        return títol;
    }

    public void setTítol(String títol) {
        this.títol = títol;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    @Override
    public String toString() {
        return títol+"\n"+lloc;
    }
}
