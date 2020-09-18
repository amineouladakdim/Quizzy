package com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ouladakdimamine.quizzproject.Modeles.Proposition;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.R;

public class AddPropositionActivity extends AppCompatActivity {

    Button ajouter,annuler;
    EditText titre,description;
    int id_question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proposition);

        ajouter = findViewById(R.id.add_add_proposition);
        annuler = findViewById(R.id.cancel_add_proposition);
        titre = findViewById(R.id.title_proposition_add);
        description = findViewById(R.id.desc_proposition_add);

        Intent intent = getIntent();
        id_question = intent.getIntExtra("id_question",0);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Proposition proposition=new Proposition(0,id_question,titre.getText().toString());
                proposition.save();

                Intent myIntent = new Intent(AddPropositionActivity.this, GestionQuizzsActivity.class);
                AddPropositionActivity.this.startActivity(myIntent);
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
