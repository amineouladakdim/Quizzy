package com.ouladakdimamine.quizzproject.Modeles;

import com.orm.SugarRecord;

public class Score  extends SugarRecord {

    private int id_score;
    private int valeur_score;
    private int id_cat;

    public Score( int id_score,int valeur_score,int id_cat) {
        this.valeur_score = valeur_score;
        this.id_score = id_score;
        this.id_cat= id_cat;
    }

    public Score() {
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public int getId_score() {
        return id_score;
    }

    public void setId_score(int id_score) {
        this.id_score = id_score;
    }

    public int getValeur_score() {
        return valeur_score;
    }

    public void setValeur_score(int valeur_score) {
        this.valeur_score = valeur_score;
    }
}
