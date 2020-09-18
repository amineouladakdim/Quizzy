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

import java.util.ArrayList;
import java.util.List;

public class EditPropositionActivity extends AppCompatActivity {

    Button ajouter,annuler;
    EditText titre,description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_proposition);

        ajouter = findViewById(R.id.edit_edit_proposition);
        annuler = findViewById(R.id.cancel_edit_proposition);
        titre = findViewById(R.id.title_proposition_edit);
        description = findViewById(R.id.desc_proposition_edit);


        long id=0;
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            id = extras.getLong("id_proposition");
        }
        Proposition q=Proposition.findById(Proposition.class,id);

        titre.setText( q.getTexte_proposition());
        // description.setText(q.getDescription_proposition());

        final long finalId = id;
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                Proposition q=Proposition.findById(Proposition.class, finalId);

                q.setTexte_proposition(titre.getText().toString());
                //q.setDescription_proposition(description.getText().toString());

                q.save();
                Intent myIntent = new Intent(EditPropositionActivity.this, GestionQuizzsActivity.class);
                EditPropositionActivity.this.startActivity(myIntent);
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
