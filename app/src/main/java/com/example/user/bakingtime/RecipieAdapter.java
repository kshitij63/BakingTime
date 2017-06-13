package com.example.user.bakingtime;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 6/13/2017.
 */

public class RecipieAdapter extends ArrayAdapter<Recepie> {

    ArrayList<Recepie> recepies;
    public RecipieAdapter(@NonNull Context context, ArrayList<Recepie> recepies) {

        super(context,R.layout.recepie);
        this.recepies=recepies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            v= LayoutInflater.from(getContext()).inflate(R.layout.recepie,parent,false);

        }
        TextView textView=(TextView) v.findViewById(R.id.text_re);
        ImageView imageView=(ImageView) v.findViewById(R.id.image_re);
        Toast.makeText(getContext(),recepies.get(position).getName(),Toast.LENGTH_SHORT).show();
        textView.setText(recepies.get(position).getName());
        if(recepies.get(position).getUrl()==null) {
            Picasso.with(getContext()).load(recepies.get(position).getUrl()).into(imageView);
        }

        return v;
    }

    @Override
    public int getCount() {return recepies.size();
    }
}
