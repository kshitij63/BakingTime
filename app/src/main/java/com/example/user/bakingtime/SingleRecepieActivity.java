package com.example.user.bakingtime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SingleRecepieActivity extends AppCompatActivity implements SingleRecepieFragment.StepSelected  {
String type;
    String videoURL,description,thumbnail;
    String id,name;
    SharedPreferences preferences;
    Boolean from_tab;
    Bundle bundle;
    SingleRecepieFragment fragment;
    TextView name_of_recepie;
    @Nullable
    private RecepieIdlingResource mIdlingResource;
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new RecepieIdlingResource();
            Log.e("heyu",mIdlingResource.isIdleNow() +"given memory");
        }
        return mIdlingResource;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getIdlingResource();
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        preferences=getSharedPreferences("none",MODE_PRIVATE);
         bundle = getIntent().getExtras();
        type = bundle.getString("id");

        name=bundle.getString("name");
        name="Nutella Pie";



        if(findViewById(R.id.container_tab_single_rec)!=null){
            from_tab=true;
            SingleRecepieFragment singleRecepieFragment=new SingleRecepieFragment();
            Bundle bundle2=new Bundle();
            bundle2.putString("type",type);
            bundle2.putBoolean("fromtab",from_tab);
            singleRecepieFragment.setArguments(bundle2);
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container_tab_single_rec,singleRecepieFragment);
            transaction.commit();


        }
        else {
            from_tab=false;
            name_of_recepie=(TextView) findViewById(R.id.set_name_text);
            name_of_recepie.setText(name);
            Bundle mbundle = new Bundle();
            mbundle.putString("type", type);
            mbundle.putBoolean("from_tab",from_tab);
            fragment = new SingleRecepieFragment();
            fragment.setArguments(mbundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.container_fragment_single_recepie, fragment);
            transaction.commit();
        }
    }



    @Override
    public void onstepselected(int position) {

        makeNetworkcall2(position);}

    public  void makeNetworkcall2(final int position){



        final RequestQueue queue = Volley.newRequestQueue(SingleRecepieActivity.this);
        StringRequest request = new StringRequest(Request.Method.GET, NetworkUtils.RECIPIE_API, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {

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
                Bundle bundle=new Bundle();

                bundle.putString("videoURL",videoURL);
                bundle.putString("description",description);
                bundle.putString("thumb",thumbnail);
                StepDetailFragment fragment=new StepDetailFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.step_det_tab_container,fragment);
                transaction.commit();

                queue.stop();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                queue.stop();
            }
        });
        queue.add(request);

    }

    public RecepieIdlingResource getRecepieIdle(){
        return mIdlingResource;
    }



}




