package com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.R;

public class AddQuestionActivity extends AppCompatActivity {

    Button ajouter,annuler;
    EditText titre,description;
    int id_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        ajouter = findViewById(R.id.add_add_question);
        annuler = findViewById(R.id.cancel_add_question);
        titre = findViewById(R.id.title_question_add);
        description = findViewById(R.id.desc_question_add);


        Intent intent = getIntent();
        id_quiz = intent.getIntExtra("id_quiz",0);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Question question=new Question(0,id_quiz,titre.getText().toString(),-1);
                question.save();

                Intent myIntent = new Intent(AddQuestionActivity.this, GestionQuizzsActivity.class);
                AddQuestionActivity.this.startActivity(myIntent);
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
