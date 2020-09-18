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

public class AddCategorieActivity extends AppCompatActivity {

    Button ajouter,annuler;
    EditText titre,description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categorie);

        ajouter = findViewById(R.id.add_add_categorie);
        annuler = findViewById(R.id.cancel_add_categorie);
        titre = findViewById(R.id.title_categorie_add);
        description = findViewById(R.id.desc_categorie_add);


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Categorie categorie=new Categorie(0,titre.getText().toString());
                categorie.save();

                Intent myIntent = new Intent(AddCategorieActivity.this, GestionQuizzsActivity.class);
                AddCategorieActivity.this.startActivity(myIntent);
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
