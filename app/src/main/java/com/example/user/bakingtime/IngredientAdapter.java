package com.example.user.bakingtime;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 6/11/2017.
 */

public class IngredientAdapter extends ArrayAdapter<String> {

    ArrayList<String> Ingredient_array_list;
    int size;

    public IngredientAdapter(@NonNull Context context, ArrayList<String> Ingredient_array_list,int size) {
        super(context, R.layout.ingredient);
        this.Ingredient_array_list=Ingredient_array_list;
        this.size=size;
//        this.bundle=bundle;
    }

    @Override
    public int getCount() {
        return size;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //bundle.putInt("position",position);
    View v=convertView;
        if(v==null){
           v= LayoutInflater.from(getContext()).inflate(R.layout.ingredient,parent,false);
        }
        TextView textView=(TextView) v.findViewById(R.id.Ingre_text);
        textView.setText(Ingredient_array_list.get(position));
        return v;
    }
}
