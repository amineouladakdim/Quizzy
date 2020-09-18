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

import com.ouladakdimamine.quizzproject.Presenteurs.ModelesCRUD.CategorieCRUD;
import com.ouladakdimamine.quizzproject.Presenteurs.GestionQuizzsActivity;
import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.R;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.MyViewHolder> {

    private List<Categorie> categoriesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView nom_categorie;
        public Button show;


        public MyViewHolder(View view) {
            super(view);

            nom_categorie = (TextView) view.findViewById(R.id.nom_categorie);
            show = (Button) view.findViewById(R.id.show_categorie);


        }
    }


    public CategorieAdapter(List<Categorie> seriesListe) {
        this.categoriesList = seriesListe;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_ligne, parent, false);
        return new MyViewHolder(itemView);

    }


    public void remove(int position)
    {
        categoriesList.remove(categoriesList.get(position));
        notifyItemRemoved(position);
    }

    public void notifyChange()
    {
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Categorie categorie = categoriesList.get(position);

        holder.nom_categorie.setText(categorie.getValeur_categorie()+"");

        holder.show.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(final View v) {
                                               CategorieCRUD cdd = new CategorieCRUD((GestionQuizzsActivity)holder.itemView.getContext(),categorie.getId());

                                               //Affichage d'une boite de dialogue custom
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
        return categoriesList.size();
    }
}
