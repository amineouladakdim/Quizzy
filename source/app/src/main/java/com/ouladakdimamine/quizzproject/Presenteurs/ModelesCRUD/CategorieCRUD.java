package com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.R;

public class CategorieCRUD extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public long categorie;

    public Button edit,remove;
    public ImageButton cancel;

    TextView title,desc;

    public CategorieCRUD(Activity a, long categorie) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.categorie=categorie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.categorie_crud);

        edit = findViewById(R.id.dialog_edit_button);
        remove = findViewById(R.id.dialog_remove_button);
        cancel = findViewById(R.id.dialog_cancel_button);

        edit.setOnClickListener(this);
        remove.setOnClickListener(this);
        cancel.setOnClickListener(this);

        Categorie q = Categorie.findById(Categorie.class, categorie);


        title = findViewById(R.id.title_dialog);
        desc = findViewById(R.id.desc_dialog);

        title.setText(q.getValeur_categorie());



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_edit_button:

                Intent myIntent = new Intent(getContext(), EditCategorieActivity.class);
                myIntent.putExtra("id_categorie", categorie); //Optional parameters
                getContext().startActivity(myIntent);

                break;
            case R.id.dialog_remove_button:

                Categorie q = Categorie.findById(Categorie.class, categorie);
                q.delete();


            /*    AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(v.getContext());
                }
                builder.setTitle("Suppression")
                        .setMessage("Vous êtes sûr de vouloir supprimer cette entrée?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

   Categorie q = Categorie.findById(Categorie.class, categorie);
                                q.delete();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();*/




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