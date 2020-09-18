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

public class EditQuizActivity extends AppCompatActivity {

    Button modifier,annuler;
    EditText titre,description;
    long id_categorie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);

        modifier = findViewById(R.id.edit_edit_quiz);
        annuler = findViewById(R.id.cancel_edit_quiz);
        titre = findViewById(R.id.title_quiz_edit);
        description = findViewById(R.id.desc_quiz_edit);

        long id=0;
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            id = extras.getLong("id_quiz");
        }

        Quiz q=Quiz.findById(Quiz.class,id);


        titre.setText( q.getTitre_quiz());
        description.setText(q.getDescription_quiz());

        final long finalId = id;

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                Quiz q=Quiz.findById(Quiz.class, finalId);

                q.setTitre_quiz(titre.getText().toString());
                q.setDescription_quiz(description.getText().toString());
//q.setCategorie_quiz((int)id_categorie);

                q.save();
                Intent myIntent = new Intent(EditQuizActivity.this, GestionQuizzsActivity.class);
                EditQuizActivity.this.startActivity(myIntent);
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
