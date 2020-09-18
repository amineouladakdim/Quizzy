package com.ouladakdimamine.quizzproject.Modeles;

import com.orm.SugarRecord;


public class Categorie  extends SugarRecord
{

    private int id_categorie;
    private  String valeur_categorie;

    public Categorie() {
    }

    public Categorie(int id_categorie, String valeur_categorie) {
        this.id_categorie = id_categorie;
        this.valeur_categorie = valeur_categorie;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getValeur_categorie() {
        return valeur_categorie;
    }

    public void setValeur_categorie(String valeur_categorie) {
        this.valeur_categorie = valeur_categorie;
    }
}
