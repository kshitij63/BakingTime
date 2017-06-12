package com.example.user.bakingtime;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SingleRecepieActivity extends AppCompatActivity implements SingleRecepieFragment.StepSelected  {
int type;
    int position;
    Boolean from_tab;
    Bundle bundle;
    SingleRecepieFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         bundle = getIntent().getExtras();
        type = bundle.getInt("recepie");
        if(findViewById(R.id.container_tab_single_rec)!=null){
     //       Toast.makeText(SingleRecepieActivity.this,"ingg",Toast.LENGTH_SHORT).show();
            from_tab=true;
            SingleRecepieFragment singleRecepieFragment=new SingleRecepieFragment();
            Bundle bundle2=new Bundle();
            bundle2.putInt("type",type);
            bundle2.putBoolean("fromtab",from_tab);
            singleRecepieFragment.setArguments(bundle2);
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container_tab_single_rec,singleRecepieFragment);
            transaction.commit();
            /*try {
                if(type==101) {
                    JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                    JSONObject object1 = receipiearray.getJSONObject(0);
                    JSONArray arr=object1.getJSONArray("steps");
                    JSONObject object=arr.getJSONObject(position);

                    Bundle bundle1=new Bundle();
                    bundle1.putString("description",object.getString("description"));
                    bundle1.putString("videoURL",object.getString("videoURL"));
                    StepDetailFragment stepDetailFragment=new StepDetailFragment();
                    stepDetailFragment.setArguments(bundle);
                    FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
                    transaction1.add(R.id.step_det_tab_container,stepDetailFragment);
                    transaction1.commit();
//                    stepSelected.onstepselected(position);
  //                  intent.putExtra("description",object.getString("description"));
    //                intent.putExtra("videoURL",object.getString("videoURL"));
                    Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
                }
                if(type==102) {
                    JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                    JSONObject object1 = receipiearray.getJSONObject(2);
                    JSONArray arr=object1.getJSONArray("steps");
                    JSONObject object=arr.getJSONObject(position);
                    Bundle bundle1=new Bundle();
                    bundle1.putString("description",object.getString("description"));
                    bundle1.putString("videoURL",object.getString("videoURL"));
                    StepDetailFragment stepDetailFragment=new StepDetailFragment();
                    stepDetailFragment.setArguments(bundle);
                    FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();

                    transaction1.add(R.id.step_det_tab_container,stepDetailFragment);
                    transaction1.commit();

//                    stepSelected.onstepselected(position);
                    //                  intent.putExtra("description",object.getString("description"));
                    //                intent.putExtra("videoURL",object.getString("videoURL"));
                    Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
                }
                if(type==103) {
                    JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                    JSONObject object1 = receipiearray.getJSONObject(3);
                    JSONArray arr=object1.getJSONArray("steps");
                    JSONObject object=arr.getJSONObject(position);
                    Bundle bundle1=new Bundle();
                    bundle1.putString("description",object.getString("description"));
                    bundle1.putString("videoURL",object.getString("videoURL"));
                    StepDetailFragment stepDetailFragment=new StepDetailFragment();
                    stepDetailFragment.setArguments(bundle);
                    FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();

                    transaction1.add(R.id.step_det_tab_container,stepDetailFragment);
                    transaction1.commit();

//                    stepSelected.onstepselected(position);
                    //                  intent.putExtra("description",object.getString("description"));
                    //                intent.putExtra("videoURL",object.getString("videoURL"));
                    Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
                }
                if(type==104) {
                    JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                    JSONObject object1 = receipiearray.getJSONObject(4);
                    JSONArray arr=object1.getJSONArray("steps");
                    JSONObject object=arr.getJSONObject(position);
                    Bundle bundle1=new Bundle();
                    bundle1.putString("description",object.getString("description"));
                    bundle1.putString("videoURL",object.getString("videoURL"));
                    StepDetailFragment stepDetailFragment=new StepDetailFragment();
                    stepDetailFragment.setArguments(bundle);
                    FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();

                    transaction1.add(R.id.step_det_tab_container,stepDetailFragment);
                    transaction1.commit();

//                    stepSelected.onstepselected(position);
                    //                  intent.putExtra("description",object.getString("description"));
                    //                intent.putExtra("videoURL",object.getString("videoURL"));
                    Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }*/


            //   stepSelected.onstepselected(position);
                    //intent.putExtra("description",object.getString("description"));
                    //intent.putExtra("videoURL",object.getString("videoURL"));
                    //Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL

        }
        else {
            from_tab=false;

            Bundle mbundle = new Bundle();
            mbundle.putInt("type", type);
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
    //this.position=position;
        Toast.makeText(SingleRecepieActivity.this,"called" +position,Toast.LENGTH_SHORT).show();
        try {
            if(type==101) {
                JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                JSONObject object1 = receipiearray.getJSONObject(0);
                JSONArray arr=object1.getJSONArray("steps");
                JSONObject object=arr.getJSONObject(position);
                Bundle bundle1=new Bundle();
                bundle1.putString("description",object.getString("description"));
                bundle1.putString("videoURL",object.getString("videoURL"));
                StepDetailFragment stepDetailFragment=new StepDetailFragment();
                stepDetailFragment.setArguments(bundle1);
                FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.step_det_tab_container,stepDetailFragment);
                transaction1.commit();
//                    stepSelected.onstepselected(position);
                //                  intent.putExtra("description",object.getString("description"));
                //                intent.putExtra("videoURL",object.getString("videoURL"));
                Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
            }
            if(type==102) {
                JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                JSONObject object1 = receipiearray.getJSONObject(1);
                JSONArray arr=object1.getJSONArray("steps");
                JSONObject object=arr.getJSONObject(position);
                Bundle bundle1=new Bundle();
                bundle1.putString("description",object.getString("description"));
                bundle1.putString("videoURL",object.getString("videoURL"));
                StepDetailFragment stepDetailFragment=new StepDetailFragment();
                stepDetailFragment.setArguments(bundle1);
                FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();

                transaction1.replace(R.id.step_det_tab_container,stepDetailFragment);
                transaction1.commit();

//                    stepSelected.onstepselected(position);
                //                  intent.putExtra("description",object.getString("description"));
                //                intent.putExtra("videoURL",object.getString("videoURL"));
                Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
            }
            if(type==103) {
                JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                JSONObject object1 = receipiearray.getJSONObject(2);
                JSONArray arr=object1.getJSONArray("steps");
                JSONObject object=arr.getJSONObject(position);
                Bundle bundle1=new Bundle();
                bundle1.putString("description",object.getString("description"));
                bundle1.putString("videoURL",object.getString("videoURL"));
                StepDetailFragment stepDetailFragment=new StepDetailFragment();
                stepDetailFragment.setArguments(bundle1);
                FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();

                transaction1.replace(R.id.step_det_tab_container,stepDetailFragment);
                transaction1.commit();

//                    stepSelected.onstepselected(position);
                //                  intent.putExtra("description",object.getString("description"));
                //                intent.putExtra("videoURL",object.getString("videoURL"));
                Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
            }
            if(type==104) {
                JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                JSONObject object1 = receipiearray.getJSONObject(3);
                JSONArray arr=object1.getJSONArray("steps");
                JSONObject object=arr.getJSONObject(position);
                Bundle bundle1=new Bundle();
                bundle1.putString("description",object.getString("description"));
                bundle1.putString("videoURL",object.getString("videoURL"));
                StepDetailFragment stepDetailFragment=new StepDetailFragment();
                stepDetailFragment.setArguments(bundle1);
                FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();

                transaction1.replace(R.id.step_det_tab_container,stepDetailFragment);
                transaction1.commit();

//                    stepSelected.onstepselected(position);
                //                  intent.putExtra("description",object.getString("description"));
                //                intent.putExtra("videoURL",object.getString("videoURL"));
                Log.e("yeahhhh",object.getString("description") +" " +object.getString("videoURL"));
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("generated.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}




