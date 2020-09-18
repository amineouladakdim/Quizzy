package com.ouladakdimamine.quizzproject.Presenteurs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.AddCategorieActivity;
import com.ouladakdimamine.quizzproject.R;

public class ResultActivity extends AppCompatActivity
{

    @Override
    public void onBackPressed()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView score_text=(TextView) findViewById(R.id.result_score);
        Button main_menu_button=(Button) findViewById(R.id.activityresult_mainmenu);
        Button replay_button=(Button) findViewById(R.id.replay_button);
        score_text.setText("Score : "+    ScoreController.getInstance().score.getValeur_score());

        replay_button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent startIntent = new Intent(ResultActivity.this,CategorieGameActivity.class);
                startActivity(startIntent);
            }
        });

        main_menu_button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                Intent myIntent = new Intent(ResultActivity.this, HomeActivity.class);
                ResultActivity.this.startActivity(myIntent);
            }
        });
    }
}
