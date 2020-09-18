package com.ouladakdimamine.quizzproject.Presenteurs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;


import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.PropositionsGameAdapter;
import com.ouladakdimamine.quizzproject.Modeles.Proposition;
import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.R;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity
{

    private RecyclerView propositionslist ;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    int idquestion;


    @Override
    public  void onBackPressed()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        idquestion=-1;

        List<Proposition> propositions=new ArrayList<>();

        propositionslist=(RecyclerView)findViewById(R.id.proposition_container_table);
        TextView question_ennonce = (TextView) findViewById(R.id.question_ennonce);
        TextView score_text=(TextView) findViewById(R.id.score_txt);
        Bundle b = getIntent().getExtras();
        if(b != null)
            idquestion = b.getInt("id_question");

        score_text.setText("Score : "+ ScoreController.getInstance().score.getValeur_score());

        Question q= Question.findById(Question.class,idquestion);
        question_ennonce.setText(q.getEnonce());

        propositionslist.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        propositionslist.setLayoutManager(layoutManager);

        propositions =  Proposition.findWithQuery(Proposition.class, "Select * from PROPOSITION where idQuestion = ?", idquestion+"");

        adapter = new PropositionsGameAdapter(this, propositions);
        propositionslist.setAdapter(adapter);


    }
}


