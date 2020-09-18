package com.ouladakdimamine.quizzproject.Presenteurs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.Presenteurs.QuizGameActivity;
import com.ouladakdimamine.quizzproject.R;

import java.util.List;


public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder>
{

    private Context context;
    private List<Categorie> CatList;

    public ScoreAdapter(Context context, List<Categorie> myDataset)
    {
        CatList = myDataset;
        context = context ;
    }

    public void add(int position, Categorie q)
    {
        CatList.add(position, q);
        notifyItemInserted(position);
    }

    public void remove(int position)
    {
        CatList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.score_entry, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {

        int score = 0;
        holder.score_label.setText(score+"");


        holder.cat_label.setText(CatList.get(position).getValeur_categorie()+"  :   ");
        holder.cat_label.setTag(CatList.get(position).getId_categorie());
        holder.cat_label.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), QuizGameActivity.class);
                Bundle b = new Bundle();
                b.putInt("id_categorie",Integer.parseInt(v.getTag().toString()));
                intent.putExtras(b);
                v.getContext().startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount()
    {
        return CatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView cat_label;
        public TextView score_label;
        public View layout;

        public ViewHolder(View v)
        {
            super(v);
            layout = v;
            cat_label = (TextView) v.findViewById(R.id.categorielabel_scoreactivity);
            score_label = (TextView) v.findViewById(R.id.scorelabel_scoreactivity);

        }
    }

}