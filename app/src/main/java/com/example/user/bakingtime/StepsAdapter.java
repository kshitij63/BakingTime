package com.example.user.bakingtime;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 6/11/2017.
 */

public class StepsAdapter extends ArrayAdapter<String> {
    ArrayList<String> steps_list;
    int type;
    String load_json;
    public StepsAdapter(@NonNull Context context, ArrayList<String> steps_list,int type,String load_json ) {
        super(context, R.layout.steps);
        this.steps_list=steps_list;
        this.type=type;
        this.load_json=load_json;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
        view=    LayoutInflater.from(getContext()).inflate(R.layout.steps,parent,false);
        }
     /*   view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        });*/
        TextView view1=(TextView) view.findViewById(R.id.steps);
        view1.setText(steps_list.get(position));
        return view;
    }

    @Override
    public int getCount() {
        return steps_list.size();
    }
}
