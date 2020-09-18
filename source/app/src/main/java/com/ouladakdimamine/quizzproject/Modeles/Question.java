package com.ouladakdimamine.quizzproject.Modeles;

import com.orm.SugarRecord;

import java.util.ArrayList;


public class Question   extends SugarRecord
{
    private int id_question;
    private int id_quiz;
    private String enonce;
    private int reponse;


    public Question()
    {

    }
    public Question(int id_question, int id_quiz, String enonce, int reponse) {
        this.id_question = id_question;
        this.id_quiz = id_quiz;
        this.enonce = enonce;

        this.reponse = reponse;

    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }



    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }


}
