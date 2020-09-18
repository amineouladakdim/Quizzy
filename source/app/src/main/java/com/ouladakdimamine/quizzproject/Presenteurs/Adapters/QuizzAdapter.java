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

import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.QuizzCRUD;
import com.ouladakdimamine.quizzproject.R;

import java.util.List;


public class QuizzAdapter extends RecyclerView.Adapter<QuizzAdapter.MyViewHolder> {

    private List<Quiz> quizList;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView nom_quizz;
        public Button show;


        public MyViewHolder(View view) {
            super(view);

            nom_quizz = (TextView) view.findViewById(R.id.nom_quizz);
            show = (Button) view.findViewById(R.id.show_quizz);


        }
    }


    public QuizzAdapter(List<Quiz> seriesListe) {
        this.quizList = seriesListe;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quizz_ligne, parent, false);
        return new MyViewHolder(itemView);
    }


    public void remove(int position)
    {
        quizList.remove(quizList.get(position));
        notifyItemRemoved(position);
    }

    public void notifyChange()
    {
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Quiz quiz = quizList.get(position);

        holder.nom_quizz.setText(quiz.getTitre_quiz()+"");

        holder.show.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(final View v) {
                                               QuizzCRUD cdd = new QuizzCRUD((GestionQuizzsActivity)holder.itemView.getContext(),quiz.getId());

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
        return quizList.size();
    }
}
