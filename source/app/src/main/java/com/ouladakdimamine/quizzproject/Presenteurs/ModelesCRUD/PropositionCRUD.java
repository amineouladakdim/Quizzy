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

import com.ouladakdimamine.quizzproject.Modeles.Proposition;
import com.ouladakdimamine.quizzproject.R;

public class PropositionCRUD extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public long proposition;

    public Button edit,remove;
    public ImageButton cancel;

    TextView title,desc;

    public PropositionCRUD(Activity a, long proposition) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.proposition=proposition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.proposition_crud);

        edit = findViewById(R.id.dialog_edit_button);
        remove = findViewById(R.id.dialog_remove_button);
        cancel = findViewById(R.id.dialog_cancel_button);

        edit.setOnClickListener(this);
        remove.setOnClickListener(this);
        cancel.setOnClickListener(this);

        Proposition q = Proposition.findById(Proposition.class, proposition);


        title = findViewById(R.id.title_dialog);
        desc = findViewById(R.id.desc_dialog);

        title.setText(q.getTexte_proposition());
        //s desc.setText(q.());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_edit_button:

                Intent myIntent = new Intent(getContext(), EditPropositionActivity.class);
                myIntent.putExtra("id_proposition", proposition); //Optional parameters
                getContext().startActivity(myIntent);

                break;
            case R.id.dialog_remove_button:
                Proposition q = Proposition.findById(Proposition.class, proposition);
                q.delete();
                //  proposition.save();
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