package com.ouladakdimamine.quizzproject.Presenteurs.XMLParser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ouladakdimamine.quizzproject.R;

import java.util.List;


public class XMLAdapter extends RecyclerView.Adapter<XMLAdapter.ViewHolder> {

    private Context context;
    private List<Item> QuizList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView quiz_label;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            quiz_label = (TextView) v.findViewById(R.id.quiztitle_txt);
        }
    }

    public void add(int position, Item q) {
        QuizList.add(position, q);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        QuizList.remove(position);
        notifyItemRemoved(position);
    }

     public XMLAdapter(Context context, List<Item> myDataset) {
        QuizList = myDataset;
        context = context ;
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

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.quiz_label.setText(QuizList.get(position).getTitle());
    }
     @Override
    public int getItemCount() {
        return QuizList.size();
    }

}