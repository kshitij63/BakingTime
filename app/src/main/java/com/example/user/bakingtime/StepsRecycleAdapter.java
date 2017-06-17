package com.example.user.bakingtime;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.user.bakingtime.R.id.bar;

/**
 * Created by user on 6/17/2017.
 */

public class StepsRecycleAdapter extends RecyclerView.Adapter<StepsRecycleAdapter.StepsHolder> {
ArrayList<String> steps_list;
Context context;
    SingleRecepieFragment.StepSelected stepSelected;
    boolean from_tab;
    ProgressBar bar;
    String type;
    String videoURL;
    String description;
    String thumbnail;
    StepsRecycleAdapter(Context context, ArrayList<String> steps_list, boolean from_tab,SingleRecepieFragment.StepSelected stepSelected ,
                        String type,ProgressBar bar){
        this.steps_list=steps_list;
        this.context=context;
        this.from_tab=from_tab;
        this.stepSelected=stepSelected;
        this.type=type;
        //this.videoURL=videoURL;
        this.bar=bar;
        //this.description=description;
        //this.thumbnail=thumbnail;
    }

    class StepsHolder extends RecyclerView.ViewHolder{
     TextView textView;
    public StepsHolder(View itemView) {
        super(itemView);
        textView=(TextView) itemView.findViewById(R.id.steps);
    }
}
    @Override
    public StepsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V= LayoutInflater.from(context).inflate(R.layout.steps,parent,false);
        StepsHolder holder=new StepsHolder(V);


        return holder;
    }

    @Override
    public void onBindViewHolder(StepsHolder holder,final  int position) {

    holder.textView.setText(steps_list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(from_tab==true){
                  stepSelected.onstepselected(position);
}
else{
                  //Toast.makeText(context,"inelse " +position,Toast.LENGTH_LONG).show();
                makeNetworkcall2(position);


}


            }
        });
    }

    @Override
    public int getItemCount() {
        return steps_list.size();
    }
    public  void makeNetworkcall2(final int position){

        bar
                .setVisibility(View.VISIBLE);
        final RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, NetworkUtils.RECIPIE_API, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                bar.setVisibility(View.INVISIBLE);
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String id=String.valueOf(object.getLong("id"));
                        if(type.equals(id)){


                            JSONArray steps=object.getJSONArray("steps");
                                                       for(int j=0;j<steps.length();j++){
                                if(j==position) {


                                    JSONObject object1=steps.getJSONObject(position);
                                    videoURL=object1.getString("videoURL");
                                    description=object1.getString("description");
                                    thumbnail=object.getString("thumbnailURL");
                                }
                            }



                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(context,StepDetailActivity.class);

                intent.putExtra("videoURL",videoURL);
                intent.putExtra("description",description);
                intent.putExtra("thumb",thumbnail);
               context. startActivity(intent);

                queue.stop();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();

                queue.stop();
            }
        });
        queue.add(request);

    }



}
