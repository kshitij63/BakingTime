package com.example.user.bakingtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 6/17/2017.
 */

public class RecepieRecycleAdapter extends RecyclerView.Adapter<RecepieRecycleAdapter.RecepieHolder> {
ArrayList<Recepie> recepies;
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    RecepieRecycleAdapter(Context context, ArrayList<Recepie> recepies){
       this. recepies=recepies;
        this.context=context;
    }

    class RecepieHolder extends RecyclerView.ViewHolder{
TextView textView;
        ImageView imageView;
    public RecepieHolder(View itemView) {
        super(itemView);
        textView= (TextView)itemView.findViewById(R.id.text_re);
        imageView=(ImageView) itemView.findViewById(R.id.image_re);

    }

    }

    @Override
    public RecepieHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(context).inflate(R.layout.recepie,parent,false);
view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
        RecepieHolder holder=new RecepieHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecepieHolder holder,final  int position) {
        preferences=context.getSharedPreferences("none",MODE_PRIVATE);
        editor=preferences.edit();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=recepies.get(position).getName();
                editor.putString("current",String.valueOf(position+1));
                editor.apply();
                    Intent intent=new Intent(context,SingleRecepieActivity.class);
                  intent.putExtra("name",name);
                 intent.putExtra("id",String.valueOf(position+1));
                context.startActivity(intent);
            }
        });
        holder.textView.setText(recepies.get(position).getName());
        if(recepies.get(position).getUrl()==null) {
            Picasso.with(context).load(recepies.get(position).getUrl()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return recepies.size();
    }

}
