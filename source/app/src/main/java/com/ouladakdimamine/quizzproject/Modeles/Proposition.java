package com.ouladakdimamine.quizzproject.Modeles;

import com.orm.SugarContext;
import com.orm.SugarRecord;


public class Proposition extends SugarRecord{

    private int id_proposition;
    private int id_question;
    private String texte_proposition;

    public Proposition() {
    }

    public Proposition(int id_proposition, int id_question, String texte_proposition) {
        this.id_proposition = id_proposition;
        this.id_question = id_question;
        this.texte_proposition = texte_proposition;
    }

    public int getId_proposition() {
        return id_proposition;
    }

    public void setId_proposition(int id_proposition) {
        this.id_proposition = id_proposition;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getTexte_proposition() {
        return texte_proposition;
    }

    public void setTexte_proposition(String texte_proposition) {
        this.texte_proposition = texte_proposition;
    }
}
