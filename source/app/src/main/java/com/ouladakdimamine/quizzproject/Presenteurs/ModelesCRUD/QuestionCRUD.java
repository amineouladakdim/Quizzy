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

import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.R;

public class QuestionCRUD extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public long question;

    public Button edit,remove;
    public ImageButton cancel;

    TextView title,desc;

    public QuestionCRUD(Activity a, long question) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.question=question;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.question_crud);

        edit = findViewById(R.id.dialog_edit_button);
        remove = findViewById(R.id.dialog_remove_button);
        cancel = findViewById(R.id.dialog_cancel_button);

        edit.setOnClickListener(this);
        remove.setOnClickListener(this);
        cancel.setOnClickListener(this);

        Question q = Question.findById(Question.class, question);


        title = findViewById(R.id.title_dialog);
        desc = findViewById(R.id.desc_dialog);

        title.setText(q.getEnonce());
        desc.setText(q.getReponse()+"");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_edit_button:

                Intent myIntent = new Intent(getContext(), EditQuestionActivity.class);
                myIntent.putExtra("id_question", question); //Optional parameters
                getContext().startActivity(myIntent);

                break;
            case R.id.dialog_remove_button:
                Question q = Question.findById(Question.class, question);
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