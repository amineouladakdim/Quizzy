package com.ouladakdimamine.quizzproject.Presenteurs;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;
import com.ouladakdimamine.quizzproject.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SugarContext.init(this);


        ConstraintLayout play=findViewById(R.id.play2);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent myIntent = new Intent(HomeActivity.this, CategorieGameActivity.class);
                startActivity(myIntent);

            }});

        ConstraintLayout gestion=findViewById(R.id.gerer);

        gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent myIntent = new Intent(HomeActivity.this, GestionQuizzsActivity.class);
                startActivity(myIntent);

            }});

    }
}
