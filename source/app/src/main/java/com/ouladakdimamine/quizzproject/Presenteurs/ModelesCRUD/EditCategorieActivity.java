package com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.R;

public class EditCategorieActivity extends AppCompatActivity {

    Button ajouter,annuler;
    EditText titre,description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_categorie);

        ajouter = findViewById(R.id.edit_edit_categorie);
        annuler = findViewById(R.id.cancel_edit_categorie);
        titre = findViewById(R.id.title_categorie_edit);
        description = findViewById(R.id.desc_categorie_edit);


        long id=0;
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            id = extras.getLong("id_categorie");
        }

        Categorie q=Categorie.findById(Categorie.class,id);


        titre.setText( q.getValeur_categorie());
        // description.setText(q.getDescription_categorie());

        final long finalId = id;
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                Categorie q=Categorie.findById(Categorie.class, finalId);

                q.setValeur_categorie(titre.getText().toString());
                q.save();
                Intent myIntent = new Intent(EditCategorieActivity.this, GestionQuizzsActivity.class);
                EditCategorieActivity.this.startActivity(myIntent);
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
