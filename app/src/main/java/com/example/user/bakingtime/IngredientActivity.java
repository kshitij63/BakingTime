package com.example.user.bakingtime;

import android.content.Context;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
    RecyclerView listView;
    LinearLayoutManager manager;
    String type;
    ProgressBar bar;
    int size;
    ArrayList<String> Ingredient_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);


        bar=(ProgressBar) findViewById(R.id.bar);
        Bundle bundle=getIntent().getExtras();
        type=bundle.getString("id");
       // Toast.makeText(this,type,Toast.LENGTH_SHORT).show();
        listView=(RecyclerView) findViewById(R.id.ingredient_list);
        manager=new LinearLayoutManager(this);
if(savedInstanceState!=null){
    Ingredient_List=savedInstanceState.getStringArrayList("list");
   // size=savedInstanceState.getStringArrayList("list").size();
    listView.setAdapter(new IngredientRecycleAdapter(this,savedInstanceState.getStringArrayList("list")));
    listView.setLayoutManager(manager);
    manager.scrollToPosition(savedInstanceState.getInt("index"));
}
else {
    Ingredient_List = new ArrayList<>();
    make_network_call(this);
}




    }



    public void make_network_call(final Context context) {

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
                            listView.setLayoutManager(manager);
                            listView.setAdapter(new IngredientRecycleAdapter(IngredientActivity.this,Ingredient_List));

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",manager.findFirstVisibleItemPosition());
        outState.putStringArrayList("list",Ingredient_List);
    }


}
