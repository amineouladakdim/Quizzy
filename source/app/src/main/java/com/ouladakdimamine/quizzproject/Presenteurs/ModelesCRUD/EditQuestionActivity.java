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

import java.util.ArrayList;
import java.util.List;

public class EditQuestionActivity extends AppCompatActivity {

    Button ajouter,annuler;
    EditText titre,description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        ajouter = findViewById(R.id.edit_edit_question);
        annuler = findViewById(R.id.cancel_edit_question);
        titre = findViewById(R.id.title_question_edit);
        description = findViewById(R.id.desc_question_edit);


        long id=0;
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            id = extras.getLong("id_question");
        }

        Question q=Question.findById(Question.class,id);

        titre.setText( q.getEnonce());
        // description.setText(q.getDescription_question());

        final long finalId = id;
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                Question q=Question.findById(Question.class, finalId);

                q.setEnonce(titre.getText().toString());
//q.setDescription_question(description.getText().toString());

                q.save();
                Intent myIntent = new Intent(EditQuestionActivity.this, GestionQuizzsActivity.class);
                EditQuestionActivity.this.startActivity(myIntent);
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
