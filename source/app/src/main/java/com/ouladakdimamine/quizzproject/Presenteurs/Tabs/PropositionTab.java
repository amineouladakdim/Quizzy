package com.ouladakdimamine.quizzproject.Presenteurs.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ouladakdimamine.quizzproject.Config;
import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.PropositionAdapter;
import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.SpinQuestionAdapter;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.AddPropositionActivity;
import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.Modeles.Proposition;
import com.ouladakdimamine.quizzproject.R;

import java.util.ArrayList;
import java.util.List;

public class PropositionTab extends Fragment {

    private List<Proposition> propositions_array = new ArrayList<>();
    static RecyclerView propositionsList;
    static PropositionAdapter sAdapter;
    Button add;
    int id_question;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.proposition_liste, container, false);

        add=rootView.findViewById(R.id.add_crud_proposition);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent myIntent = new Intent(getActivity(), AddPropositionActivity.class);
                myIntent.putExtra("id_question", id_question); //Optional parameters
                startActivity(myIntent);

            }});

        propositionsList = (RecyclerView) rootView.findViewById(R.id.liste_propositions);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        propositionsList.setLayoutManager(mLayoutManager);
        propositionsList.setItemAnimator(new DefaultItemAnimator());
        List<Question> questList=  new ArrayList<Question>();

        questList =   Question.listAll(Question.class);

        SpinQuestionAdapter adapter = new SpinQuestionAdapter(rootView.getContext(),
                android.R.layout.simple_spinner_item,
                questList);
        final Spinner mySpinner = (Spinner) rootView.findViewById(R.id.liste_question);
        mySpinner.setAdapter(adapter);


        int last_selection=Config.spinner_index;


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Config.spinner_index=position;
                String selected2 = ((Question) adapterView.getItemAtPosition(position)).getId().toString();

                id_question=Integer.parseInt(selected2);


                try {

                    propositions_array = Proposition.findWithQuery(Proposition.class, "Select * from PROPOSITION where idQuestion = ?", selected2);
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                sAdapter=new PropositionAdapter(propositions_array);
                propositionsList.setAdapter(sAdapter);

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

