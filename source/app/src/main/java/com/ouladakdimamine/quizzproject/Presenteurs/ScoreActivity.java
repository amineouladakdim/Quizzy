package com.ouladakdimamine.quizzproject.Presenteurs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;


import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.ScoreAdapter;
import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.R;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private RecyclerView scorelist ;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button mainbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        List<Categorie> cats=new ArrayList<>();

        mainbutton=(Button)findViewById(R.id.activityscore_home);

        mainbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });
        scorelist=(RecyclerView)findViewById(R.id.listescore);
        scorelist.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        scorelist.setLayoutManager(layoutManager);

        cats = Categorie.listAll(Categorie.class);
        adapter = new ScoreAdapter(this, cats);
        scorelist.setAdapter(adapter);
    }
}
