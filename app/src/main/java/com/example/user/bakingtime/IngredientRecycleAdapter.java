package com.example.user.bakingtime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 6/17/2017.
 */

public class IngredientRecycleAdapter extends RecyclerView.Adapter<IngredientRecycleAdapter.IngredientHolder> {
Context context;
    ArrayList<String> Ingredient_list;

    IngredientRecycleAdapter(Context context,ArrayList<String> Ingre){
        this.context=context;
        Ingredient_list=Ingre;
    }

    class IngredientHolder extends RecyclerView.ViewHolder{
TextView textView;
        public IngredientHolder(View itemView) {

            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.Ingre_text);

        }
    }
    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View V= LayoutInflater.from(context).inflate(R.layout.ingredient,parent,false);
        IngredientHolder holder=new IngredientHolder(V);
        return holder;
    }

    @Override
    public void onBindViewHolder(IngredientHolder holder, int position) {
holder.textView.setText(Ingredient_list.get(position));
    }

    @Override
    public int getItemCount() {
        return Ingredient_list.size();
    }
}
