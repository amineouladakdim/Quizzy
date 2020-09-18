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


import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.Presenteurs.QuizActivity;
import com.ouladakdimamine.quizzproject.R;

import java.util.List;


public class QuizGameAdapter extends RecyclerView.Adapter<QuizGameAdapter.ViewHolder> {

    private Context context;
    private List<Quiz> QuizList;


    public QuizGameAdapter(Context context, List<Quiz> myDataset) {
        QuizList = myDataset;
        context = context ;
    }

    public void add(int position, Quiz q) {
        QuizList.add(position, q);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        QuizList.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.quizz_entry, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    public Question getFirstQuestion(int id)
    {
        List<Question>  questions_array = Question.findWithQuery(Question.class, "Select * from QUESTION where idQuiz = ?", id+"");
        return questions_array.get(0);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Quiz quiz = QuizList.get(position);
        holder.quiz_label.setText(QuizList.get(position).getTitre_quiz());
        holder.quiz_label.setTag(QuizList.get(position).getId());
        holder.quiz_label.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), QuizActivity.class);
                Bundle b = new Bundle();

                int idquestion= getFirstQuestion( Integer.parseInt(v.getTag().toString())).getId().intValue();

                b.putInt("id_question",idquestion);
                intent.putExtras(b);
                v.getContext().startActivity(intent);


            }
        });




    }
    @Override
    public int getItemCount() {
        return QuizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quiz_label;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            quiz_label = (TextView) v.findViewById(R.id.quiztitle_txt);

        }
    }

}