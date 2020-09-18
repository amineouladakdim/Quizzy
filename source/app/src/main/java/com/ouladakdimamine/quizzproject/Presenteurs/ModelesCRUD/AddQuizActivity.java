package com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.R;

public class AddQuizActivity extends AppCompatActivity {

    Button ajouter,annuler;
    EditText titre,description;
    long id_categorie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        ajouter = findViewById(R.id.add_add_quiz);
        annuler = findViewById(R.id.cancel_add_quiz);
        titre = findViewById(R.id.title_quiz_add);
        description = findViewById(R.id.desc_quiz_add);

        Intent intent = getIntent();
        id_categorie = intent.getIntExtra("id_cat",0);


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Quiz quiz=new Quiz(0,titre.getText().toString(),description.getText().toString(),(int)id_categorie);
                quiz.save();

                Intent myIntent = new Intent(AddQuizActivity.this, GestionQuizzsActivity.class);
                AddQuizActivity.this.startActivity(myIntent);
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(final View v) {
                                           onBackPressed();
                                       }
                                   }
        );


    }
}
