package com.ouladakdimamine.quizzproject.Presenteurs.Adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ouladakdimamine.quizzproject.Modeles.Proposition;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.AddQuizActivity;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.PropositionCRUD;
import com.ouladakdimamine.quizzproject.R;

import java.util.List;


public class PropositionAdapter extends RecyclerView.Adapter<PropositionAdapter.MyViewHolder> {

    private List<Proposition> propositionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView nom_proposition;
        public Button show;
        public Button right;


        public MyViewHolder(View view) {
            super(view);

            nom_proposition = (TextView) view.findViewById(R.id.nom_proposition);
            show = (Button) view.findViewById(R.id.show_proposition);
            right = (Button) view.findViewById(R.id.right_prop);

        }
    }


    public PropositionAdapter(List<Proposition> seriesListe) {
        this.propositionsList = seriesListe;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.proposition_ligne, parent, false);

        return new MyViewHolder(itemView);
    }


    public void remove(int position)
    {
        propositionsList.remove(propositionsList.get(position));
        notifyItemRemoved(position);

    }

    public void notifyChange()
    {
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Proposition proposition = propositionsList.get(position);

        holder.nom_proposition.setText(proposition.getTexte_proposition()+"");


        Question q=Question.findById(Question.class, proposition.getId_question());
        if ( q.getReponse()  != -1)
        {
            if(q.getReponse() == proposition.getId().intValue())
                holder.nom_proposition.setTextColor(Color.GREEN);
            else
                holder.nom_proposition.setTextColor(Color.RED);
        }


        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Question q=Question.findById(Question.class, proposition.getId_question());

                q.setReponse(proposition.getId().intValue());
                q.save();


                Intent myIntent = new Intent(holder.itemView.getContext(), GestionQuizzsActivity.class);
                holder.itemView.getContext().startActivity(myIntent);

            }});


        holder.show.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(final View v) {
                                               PropositionCRUD cdd = new PropositionCRUD((GestionQuizzsActivity)holder.itemView.getContext(),proposition.getId());

                                               WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                               lp.copyFrom(cdd.getWindow().getAttributes());
                                               lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                                               lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                                               cdd.getWindow().setAttributes(lp);
                                               cdd.setCanceledOnTouchOutside(false);
                                               cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                               cdd.show();
                                               cdd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                                            @Override
                                                                            public void onDismiss(DialogInterface dialog) {

                                                                                remove(position);
                                                                            }


                                                                        }
                                               );

                                               cdd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                   @Override
                                                   public void onCancel(DialogInterface dialog) {

                                                   }

                                               }  );




                                           }
                                       }
        );



    }

    @Override
    public int getItemCount() {
        return propositionsList.size();
    }
}
