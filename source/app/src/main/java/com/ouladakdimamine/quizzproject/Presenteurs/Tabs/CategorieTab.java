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
import android.widget.Button;
import android.widget.Toast;

import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.CategorieAdapter;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.AddCategorieActivity;
import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.R;

import java.util.ArrayList;
import java.util.List;

public class CategorieTab extends Fragment {

    private List<Categorie> categories_array = new ArrayList<>();
    static RecyclerView categoriesList;
    static CategorieAdapter sAdapter;
    Button add;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.categorie_liste, container, false);

        add=rootView.findViewById(R.id.add_crud_categorie);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent myIntent = new Intent(getActivity(), AddCategorieActivity.class);
                startActivity(myIntent);

            }});

        categoriesList = (RecyclerView) rootView.findViewById(R.id.liste_categories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        categoriesList.setLayoutManager(mLayoutManager);
        categoriesList.setItemAnimator(new DefaultItemAnimator());



        try {
            categories_array = Categorie.listAll(Categorie.class);
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        sAdapter=new CategorieAdapter(categories_array);
        categoriesList.setAdapter(sAdapter);

        sAdapter.notifyDataSetChanged();
        return rootView;
    }




}

