package com.ouladakdimamine.quizzproject.Presenteurs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ouladakdimamine.quizzproject.Modeles.Proposition;
import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.Presenteurs.QuizActivity;
import com.ouladakdimamine.quizzproject.Presenteurs.ResultActivity;
import com.ouladakdimamine.quizzproject.R;
import com.ouladakdimamine.quizzproject.Presenteurs.ScoreController;

import java.util.List;


public class PropositionsGameAdapter extends RecyclerView.Adapter<PropositionsGameAdapter.ViewHolder>
{

    private Context context;
    private List<Proposition> PropositionList;


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public Button reponse_button;
        public View layout;

        public ViewHolder(View v)
        {
            super(v);
            layout = v;
            reponse_button=(Button) v.findViewById(R.id.proposition_ennonce);
        }
    }

    public void add(int position, Proposition q)
    {
        PropositionList.add(position, q);
        notifyItemInserted(position);
    }

    public void remove(int position)
    {
        PropositionList.remove(position);
        notifyItemRemoved(position);
    }

    public PropositionsGameAdapter(Context context, List<Proposition> myDataset)
    {
        PropositionList = myDataset;
        context = context ;
    }

    //effectue l'inflation de la vue à partir d'un layout donné
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.proposition_entry, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    int id_cardview_reponse;
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        final Proposition proposition = PropositionList.get(position);

        //récupere la question de la proposition donné
        //et compare l'id de sa réponse avec l'id de
        //la proposition en question
        id_cardview_reponse=0;


        int reponse=   Question.findById(Question.class,proposition.getId_question()).getReponse();

        if(reponse==proposition.getId())
        {
            id_cardview_reponse=holder.getAdapterPosition();
        }



        holder.layout.setTag(PropositionList.get(position).getId_proposition()+"");
        //défini l'écouteur sur le boutton qui permet de choisir la réponse à une question
        holder.reponse_button.setText(proposition.getTexte_proposition());
        holder.reponse_button.setTag(proposition.getId_question());
        holder.reponse_button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {

                RecyclerView r = (RecyclerView)  holder.layout.getParent();

                int reponse=   Question.findById(Question.class,proposition.getId_question()).getReponse();
                //donne la couleur verte à la cardview si la réponse est correcte
                //sinon on lui donne la couleur rouge , et on colorie la bonne réponse
                //apres l'avoir récupérer de la base de donnée
                int x = ScoreController.getInstance().score.getValeur_score()+100;
                if(reponse==proposition.getId())
                {
                    holder.layout.setBackgroundColor(Color.GREEN);


                    //incrémente le score comme la réponse est juste
                    ScoreController.getInstance().score.setValeur_score (x);
                }
                else
                {
                    holder.layout.setBackgroundColor(Color.RED);
                    //  ScoreController.getInstance().score.setValeur_score (x);
                    r.getChildAt(id_cardview_reponse).setBackgroundColor(Color.GREEN);

                }


                LinearLayout l= (LinearLayout)r.getParent();

                //met à jour le score
                TextView scoretext= (TextView)l.findViewById(R.id.score_txt);
                scoretext.setText("Score : "+ ScoreController.getInstance().score.getValeur_score());

                //permet d'attendre un delai de 1.5 sec avant de passer à la question suivante
                //le temps de l'affichage de la correction
                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {


                        Question q=Question.findById(Question.class,Integer.parseInt(v.getTag().toString()));



                        Question qx= getNextQuestion(q.getId_quiz(),Integer.parseInt(v.getTag().toString()));

                        int idquestion=0;
                        if(qx!=null)
                            idquestion=qx.getId().intValue();
                        else
                            idquestion=Integer.parseInt(v.getTag().toString());

                        //appel l'activité de résultat si la question actuelle est la derniere question
                        //sinon appelle la meme activité avec l'id de la question suivante
                        if(idquestion==proposition.getId_question())
                        {

                            ScoreController.getInstance().saveScore(v.getContext());
                            Intent intent = new Intent(v.getContext(), ResultActivity.class);
                            v.getContext().startActivity(intent);

                        }
                        else
                        {
                            Intent intent = new Intent(v.getContext(), QuizActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("id_question", idquestion); //passe le numéro de la question suivante à l'activité cible
                            intent.putExtras(b);
                            v.getContext().startActivity(intent);
                        }

                    }
                }, 1500);
            }
        });
    }




    Question getNextQuestion(int id,int last_id)
    {
        List<Question>  questions_array = Question.findWithQuery(Question.class, "Select * from QUESTION where idQuiz = ?", id+"");

        for(int i=0;i<questions_array.size();i++)
        {
            if ( questions_array.get(i).getId() == last_id)
            {
                try
                {
                    return questions_array.get(i+1);
                }
                catch (Exception e)
                {
                    return questions_array.get(i);
                }

            }

        }

        return null;
    }


    @Override
    public int getItemCount()
    {
        return PropositionList.size();
    }

}