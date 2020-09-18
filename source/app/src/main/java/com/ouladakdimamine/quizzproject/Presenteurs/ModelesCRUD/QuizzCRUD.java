package com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.R;

public class QuizzCRUD extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public long quiz;

    public Button edit,remove;
    public ImageButton cancel;

    TextView title,desc;

    public QuizzCRUD(Activity a,long quiz) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.quiz=quiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quizz_crud);

        edit = findViewById(R.id.dialog_edit_button);
        remove = findViewById(R.id.dialog_remove_button);
        cancel = findViewById(R.id.dialog_cancel_button);

        edit.setOnClickListener(this);
        remove.setOnClickListener(this);
        cancel.setOnClickListener(this);

        Quiz q = Quiz.findById(Quiz.class, quiz);


        title = findViewById(R.id.title_dialog);
        desc = findViewById(R.id.desc_dialog);

        title.setText(q.getTitre_quiz());
        desc.setText(q.getDescription_quiz());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_edit_button:

                Intent myIntent = new Intent(getContext(), EditQuizActivity.class);
                myIntent.putExtra("id_quiz", quiz); //Optional parameters
                getContext().startActivity(myIntent);

                break;
            case R.id.dialog_remove_button:
                Quiz q = Quiz.findById(Quiz.class, quiz);
                q.delete();
                cancel();
                break;
            case R.id.dialog_cancel_button:
                hide();
                break;
            default:
                break;
        }

    }
}