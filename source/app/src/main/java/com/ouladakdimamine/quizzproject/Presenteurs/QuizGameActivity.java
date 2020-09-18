package com.ouladakdimamine.quizzproject.Presenteurs;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;


import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.QuizGameAdapter;
import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.R;

import java.util.ArrayList;
import java.util.List;

public class QuizGameActivity extends AppCompatActivity {

    int idcat = -1;

    private RecyclerView quizlist;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_quiz_game);


        quizlist = (RecyclerView) findViewById(R.id.listequiz);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        quizlist.setLayoutManager(linearLayoutManager);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            idcat = b.getInt("id_categorie");
            List<Quiz> quizzs = new ArrayList<>();

            ScoreController.getInstance().initScore(idcat);

            quizlist.setHasFixedSize(true);
            layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            quizlist.setLayoutManager(layoutManager);

            try {
                quizzs = Quiz.findWithQuery(Quiz.class, "Select * from QUIZ where categorieQuiz = ?", idcat+"");
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

            adapter = new QuizGameAdapter(this, quizzs);
            quizlist.setAdapter(adapter);
        } else {

        }

    }
}