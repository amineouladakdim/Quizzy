package com.ouladakdimamine.quizzproject.Presenteurs.Adapters;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.QuestionCRUD;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private List<Question> questionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView nom_question;
        public Button show;


        public MyViewHolder(View view) {
            super(view);

            nom_question = (TextView) view.findViewById(R.id.nom_question);
            show = (Button) view.findViewById(R.id.show_question);


        }
    }


    public QuestionAdapter(List<Question> seriesListe) {
        this.questionsList = seriesListe;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_ligne, parent, false);




        return new MyViewHolder(itemView);





    }


    public void remove(int position)
    {
        questionsList.remove(questionsList.get(position));
        notifyItemRemoved(position);
        // notifyDataSetChanged();
    }

    public void notifyChange()
    {
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Question question = questionsList.get(position);

        holder.nom_question.setText(question.getEnonce()+"");

        holder.show.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(final View v) {
                                               QuestionCRUD cdd = new QuestionCRUD((GestionQuizzsActivity)holder.itemView.getContext(),question.getId());
                                               //  cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



                                               // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)

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



                                               //   cdd.show();
                                           }
                                       }
        );

/*
        final Serie serie = seriesListe.get(position);

        holder.nombre_serie.setText(serie.getNombre_serie()+"");
        holder.libelle.setText( serie.getLibelle());
        holder.nombre_repetition.setText(serie.getNombre_repetition()+" Reps");

        if(position==0)
            holder.repos.setText("Fini");
        else
            holder.repos.setText("oo");

        holder.repos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (holder.repos.getText().equals("Fini"))
                {
                    int remaining=serie.getRepos()*1000;
                    new CountDownTimer(remaining, 1000) {

                        public void onTick(long millisUntilFinished) {
                            holder.repos.setText((millisUntilFinished/1000)/60+"Min"+(millisUntilFinished/1000)%60);
                        }

                        public void onFinish() {
                            if(serie.getNombre_serie()>1) {
                                serie.setNombre_serie(serie.getNombre_serie() - 1);
                                holder.nombre_serie.setText(serie.getNombre_serie()+"");
                                holder.repos.setText("Fini");
                            }
                            else
                            {
                                remove(holder.getAdapterPosition());
                            }
                        }
                    }.start();
                }
            }
        });







        holder.modifier.setTag(seriesListe.get(position).getId()+"");
        holder.modifier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
               SerieTab.series_liste.setVisibility(View.INVISIBLE);
                ProgrammeTab.serie_add.setVisibility(View.GONE);
                ProgrammeTab.current_edit=seriesListe.get(position).getId();
                ProgrammeTab.serie_edit_form.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInDown)
                        .duration(400)
                        .repeat(0).playOn(ProgrammeTab.serie_edit_form);
            }

        });

        holder.supprimer.setTag(seriesListe.get(position).getId()+"");

        holder.supprimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    builder = new AlertDialog.Builder(v.getContext());
                }
                builder.setTitle("Suppression")
                        .setMessage("Vous êtes sûr de vouloir supprimer cette entrée?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                Serie serie = Serie.findById(Serie.class,  Integer.parseInt(v.getTag().toString()));
                                serie.delete();
                                remove(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });*/

    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }
}
