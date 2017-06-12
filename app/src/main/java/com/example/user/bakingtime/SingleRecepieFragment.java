package com.example.user.bakingtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by user on 6/12/2017.
 */

public class SingleRecepieFragment extends Fragment {



    ListView listView;
    boolean from_tab;
    int type;
    StepsAdapter adapter;
    ArrayList<String> steps_list;
    ArrayList<String> Ingredeint_list;
    TextView textView;
    StepSelected stepSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.single_recepie_fragment,container,false);
        type=getArguments().getInt("type");
        from_tab=getArguments().getBoolean("fromtab");
        //Toast.makeText(getActivity(), from_tab +"",Toast.LENGTH_SHORT).show();
        steps_list=new ArrayList<>();
        Ingredeint_list=new ArrayList<>();
        listView=(ListView) v.findViewById(R.id.step_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //        Toast.makeText(getActivity(),"yeah",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),StepDetailActivity.class);

                try {
                    if(type==101) {
                        JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                        JSONObject object1 = receipiearray.getJSONObject(0);
                        JSONArray arr=object1.getJSONArray("steps");
                        JSONObject object=arr.getJSONObject(position);
                        if(from_tab)
                        stepSelected.onstepselected(position);
                        else {
          //                  Toast.makeText(getActivity(),"`inelse",Toast.LENGTH_SHORT).show();
                            intent.putExtra("description", object.getString("description"));
                            intent.putExtra("videoURL", object.getString("videoURL"));
                            getContext().startActivity(intent);

                            Log.e("yeahhhh", object.getString("description") + " " + object.getString("videoURL"));
                        }
                    }
                    else if(type==102) {
                        JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                        JSONObject object1 = receipiearray.getJSONObject(1);
                        JSONArray arr=object1.getJSONArray("steps");
                        JSONObject object=arr.getJSONObject(position);

                        if (from_tab)
                        stepSelected.onstepselected(position);
else {
                            intent.putExtra("description", object.getString("description"));
                            intent.putExtra("videoURL", object.getString("videoURL"));
                            getContext().startActivity(intent);

                            Log.e("yeahhhh", object.getString("description") + " " + object.getString("videoURL"));
                        }
                    }
                    else if(type==103) {
                        JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                        JSONObject object1 = receipiearray.getJSONObject(2);
                        JSONArray arr=object1.getJSONArray("steps");
                        JSONObject object=arr.getJSONObject(position);
if(from_tab)
                        stepSelected.onstepselected(position);
            else {
    intent.putExtra("description", object.getString("description"));
    intent.putExtra("videoURL", object.getString("videoURL"));
    getContext().startActivity(intent);

    Log.e("yeahhhh", object.getString("description") + " " + object.getString("videoURL"));
}
                    }
                    else if(type==104) {
                        JSONArray receipiearray = new JSONArray(loadJSONFromAsset());
                        JSONObject object1 = receipiearray.getJSONObject(3);
                        JSONArray arr=object1.getJSONArray("steps");
                        JSONObject object=arr.getJSONObject(position);
if(from_tab)
                        stepSelected.onstepselected(position);
else {
    intent.putExtra("description", object.getString("description"));
    intent.putExtra("videoURL", object.getString("videoURL"));
    getContext().startActivity(intent);

    Log.e("yeahhhh", object.getString("description") + " " + object.getString("videoURL"));
}
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
        textView=(TextView) v.findViewById(R.id.ingre_textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),IngredientActivity.class);
                try {
                    JSONArray receipiearray=new JSONArray(loadJSONFromAsset());
                    if(type==101){
                        JSONObject object1 = receipiearray.getJSONObject(0);

                        JSONArray steps_array = object1.getJSONArray("ingredients");
                        for(int i=0;i<steps_array.length();i++){
                            JSONObject object=steps_array.getJSONObject(i);
                            String ingre=object.getString("quantity") +" "+object.getString("measure") +" of " +object.getString("ingredient");
                            Ingredeint_list.add(ingre);
                        }
                        intent.putStringArrayListExtra("list",Ingredeint_list);
                        startActivity(intent);

                    }
                    else if(type==102){
                        JSONObject object1 = receipiearray.getJSONObject(1);

                        JSONArray steps_array = object1.getJSONArray("ingredients");
                        for(int i=0;i<steps_array.length();i++){
                            JSONObject object=steps_array.getJSONObject(i);
                            String ingre=object.getString("quantity") +" "+object.getString("measure") +" of " +object.getString("ingredient");
                            Ingredeint_list.add(ingre);
                        }
                        intent.putStringArrayListExtra("list",Ingredeint_list);
                        startActivity(intent);

                    }
                    else if(type==103){
                        JSONObject object1 = receipiearray.getJSONObject(2);

                        JSONArray steps_array = object1.getJSONArray("ingredients");
                        for(int i=0;i<steps_array.length();i++){
                            JSONObject object=steps_array.getJSONObject(i);
                            String ingre=object.getString("quantity") +" "+object.getString("measure") +" of " +object.getString("ingredient");
                            Ingredeint_list.add(ingre);
                        }
                        intent.putStringArrayListExtra("list",Ingredeint_list);
                        startActivity(intent);

                    }
                    else if(type==104){
                        JSONObject object1 = receipiearray.getJSONObject(3);

                        JSONArray steps_array = object1.getJSONArray("ingredients");
                        for(int i=0;i<steps_array.length();i++){
                            JSONObject object=steps_array.getJSONObject(i);
                            String ingre=object.getString("quantity") +" "+object.getString("measure") +" of " +object.getString("ingredient");
                            Ingredeint_list.add(ingre);
                        }
                        intent.putStringArrayListExtra("list",Ingredeint_list);
                        startActivity(intent);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        try {
            JSONArray receipiearray=new JSONArray(loadJSONFromAsset());
            //int lenght=receipiearray.length();
            if(type==101) {
                JSONObject object1 = receipiearray.getJSONObject(0);
                JSONArray steps_array = object1.getJSONArray("steps");
                for (int i = 0; i < steps_array.length(); i++) {

                    JSONObject ob = steps_array.getJSONObject(i);
                    String step = ob.getString("shortDescription");
                    steps_list.add(step);
                }

            }
            else   if(type==102) {
                JSONObject object1 = receipiearray.getJSONObject(1);
                JSONArray steps_array = object1.getJSONArray("steps");
                for (int i = 0; i < steps_array.length(); i++) {

                    JSONObject ob = steps_array.getJSONObject(i);
                    String step = ob.getString("shortDescription");
                    steps_list.add(step);
                }
            }
            else       if(type==103) {
                JSONObject object1 = receipiearray.getJSONObject(2);
                JSONArray steps_array = object1.getJSONArray("steps");
                for (int i = 0; i < steps_array.length(); i++) {

                    JSONObject ob = steps_array.getJSONObject(i);
                    String step = ob.getString("shortDescription");
                    steps_list.add(step);

                }
            }
            else           if(type==104) {
                JSONObject object1 = receipiearray.getJSONObject(3);
                JSONArray steps_array=object1.getJSONArray("steps");
                for(int i=0;i<steps_array.length();i++){

                    JSONObject ob=steps_array.getJSONObject(i);
                    String step=ob.getString("shortDescription");
                    steps_list.add(step);
                }

            }


            listView.setAdapter(new StepsAdapter(getActivity(),steps_list,type,loadJSONFromAsset()));
            //Log.e("name",object1.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return v;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getActivity().getAssets().open("generated.json");

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

    public interface StepSelected{
        void onstepselected(int position);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        stepSelected=(StepSelected)(activity);
    }
}
