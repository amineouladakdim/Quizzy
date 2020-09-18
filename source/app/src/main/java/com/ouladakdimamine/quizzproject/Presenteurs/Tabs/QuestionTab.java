package com.ouladakdimamine.quizzproject.Presenteurs.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ouladakdimamine.quizzproject.Config;
import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.QuestionAdapter;
import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.SpinQuizAdapter;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.AddQuestionActivity;
import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionTab extends Fragment {

    private List<Question> questions_array = new ArrayList<>();
    static RecyclerView questionsList;
    static QuestionAdapter sAdapter;
    Button add;
    int current_quiz;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question_liste, container, false);

        add=rootView.findViewById(R.id.add_crud_question);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent myIntent = new Intent(getActivity(), AddQuestionActivity.class);
                myIntent.putExtra("id_quiz", current_quiz); //Optional parameters
                startActivity(myIntent);

            }});

        questionsList = (RecyclerView) rootView.findViewById(R.id.liste_questions);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        questionsList.setLayoutManager(mLayoutManager);
        questionsList.setItemAnimator(new DefaultItemAnimator());

        List<Quiz> quizList=  new ArrayList<Quiz>();

        quizList =   Quiz.listAll(Quiz.class);

        SpinQuizAdapter adapter = new SpinQuizAdapter(rootView.getContext(),
                android.R.layout.simple_spinner_item,
                quizList);
        final Spinner mySpinner = (Spinner) rootView.findViewById(R.id.liste_quiz);
        mySpinner.setAdapter(adapter);



        int last_selection= Config.spinner_index;


        // You can create an anonymous listener to handle the event when is selected an spinner item
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position



                String selected2 = ((Quiz) adapterView.getItemAtPosition(position)).getId().toString();

                current_quiz=Integer.parseInt(selected2);



                try {
                    //    questions_array = Question.listAll(Question.class);
                    questions_array = Question.findWithQuery(Question.class, "Select * from QUESTION where idQuiz = ?", selected2);
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                sAdapter=new QuestionAdapter(questions_array);
                questionsList.setAdapter(sAdapter);

                sAdapter.notifyDataSetChanged();


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
        }
        });

        try {
            mySpinner.setSelection(last_selection, true);
        }
        catch (Exception e)
        {

        }
        return rootView;
    }
}

