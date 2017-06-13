package com.example.user.bakingtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ListView listView;
    ProgressBar bar;
    ArrayList<Recepie> main_list;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences=getSharedPreferences("none",MODE_PRIVATE);
        editor=preferences.edit();
        bar=(ProgressBar) findViewById(R.id.bar);

        listView=(ListView) findViewById(R.id.main_grid);
            make_network_call(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String name=main_list.get(position).getName();
                editor.putString("current",String.valueOf(position+1));
                editor.apply();
                Intent intent=new Intent(MainActivity.this,SingleRecepieActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("id",String.valueOf(position+1));
                startActivity(intent);

            }
        });


    }



    public void make_network_call(Context context) {
        bar.setVisibility(View.VISIBLE);

        main_list=new ArrayList<>();
        final RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, NetworkUtils.RECIPIE_API, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                bar.setVisibility(View.INVISIBLE);
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String dish=object.getString("name");
                        String url=object.getString("image");
                        Recepie recepie=new Recepie(url,dish);

                        main_list.add(recepie);
                    }
                    listView.setAdapter(new RecipieAdapter(MainActivity.this,main_list));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                queue.stop();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();

                queue.stop();
            }
        });
        queue.add(request);
    }


}
