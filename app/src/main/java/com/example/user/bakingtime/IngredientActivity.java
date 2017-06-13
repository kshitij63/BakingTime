package com.example.user.bakingtime;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import java.util.ArrayList;

public class IngredientActivity extends AppCompatActivity {
    ListView listView;
    String type;
    ProgressBar bar;
    ArrayList<String> Ingredient_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        bar=(ProgressBar) findViewById(R.id.bar);
        Bundle bundle=getIntent().getExtras();
        type=bundle.getString("id");
        Toast.makeText(this,type,Toast.LENGTH_SHORT).show();
        listView=(ListView) findViewById(R.id.ingredient_list);
        make_network_call(this);
    }



    public void make_network_call(final Context context) {
        Ingredient_List=new ArrayList<>();
        bar.setVisibility(View.VISIBLE);

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
                            JSONArray steps=object.getJSONArray("ingredients");
                                for(int j=0;j<steps.length();j++){
                                JSONObject object1=steps.getJSONObject(j);
                                String ingre=object1.getString("quantity") +" "+object1.getString("measure") +" of " +object1.getString("ingredient");

                                Ingredient_List.add(ingre);
                            }
                            listView.setAdapter(new IngredientAdapter(IngredientActivity.this,Ingredient_List));

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
