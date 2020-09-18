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
import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.QuizzAdapter;
import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.SpinCategorieAdapter;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.AddQuizActivity;
import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.R;

import java.util.ArrayList;
import java.util.List;

public class QuizzTab extends Fragment {

    private List<Quiz> quizzs_array = new ArrayList<>();
    static RecyclerView quizzsList;
    static QuizzAdapter sAdapter;
    Button add;
    int current_cat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.quizz_liste, container, false);

        add=rootView.findViewById(R.id.add_crud_quiz);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent myIntent = new Intent(getActivity(), AddQuizActivity.class);
                myIntent.putExtra("id_cat", current_cat);
                startActivity(myIntent);

            }});

        quizzsList = (RecyclerView) rootView.findViewById(R.id.liste_quizzs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        quizzsList.setLayoutManager(mLayoutManager);
        quizzsList.setItemAnimator(new DefaultItemAnimator());


        List<Categorie> catList=  new ArrayList<Categorie>();

        catList =   Categorie.listAll(Categorie.class);

        SpinCategorieAdapter adapter = new SpinCategorieAdapter(rootView.getContext(),
                android.R.layout.simple_spinner_item,
                catList);
        final Spinner mySpinner = (Spinner) rootView.findViewById(R.id.spinner);
        mySpinner.setAdapter(adapter);



        int last_selection= Config.spinner_index;


        // You can create an anonymous listener to handle the event when is selected an spinner item
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position



                String selected2 = ((Categorie) adapterView.getItemAtPosition(position)).getId().toString();

                current_cat =Integer.parseInt(selected2);



                try {

                    quizzs_array = Quiz.findWithQuery(Quiz.class, "Select * from QUIZ where categorieQuiz = ?", selected2);
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                sAdapter=new QuizzAdapter(quizzs_array);
                quizzsList.setAdapter(sAdapter);

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

