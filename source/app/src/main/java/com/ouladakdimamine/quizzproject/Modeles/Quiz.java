package com.ouladakdimamine.quizzproject.Modeles;

import com.orm.SugarRecord;

import java.util.ArrayList;

public class Quiz extends  SugarRecord {

    private int id_quiz;
    private String titre_quiz;
    private String description_quiz;
    private int categorie_quiz;

public Quiz()
{

}

    public Quiz(int id_quiz, String titre_quiz, String description_quiz, int categorie_quiz) {
        this.id_quiz = id_quiz;
        this.titre_quiz = titre_quiz;
        this.description_quiz = description_quiz;
        this.categorie_quiz = categorie_quiz;


    }

    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getTitre_quiz() {
        return titre_quiz;
    }

    public void setTitre_quiz(String titre_quiz) {
        this.titre_quiz = titre_quiz;
    }

    public String getDescription_quiz() {
        return description_quiz;
    }

    public void setDescription_quiz(String description_quiz) {
        this.description_quiz = description_quiz;
    }

    public int getCategorie_quiz() {
        return categorie_quiz;
    }

    public void setCategorie_quiz(int categorie_quiz) {
        this.categorie_quiz = categorie_quiz;
    }






}

