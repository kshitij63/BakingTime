package com.example.user.bakingtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by user on 6/12/2017.
 */

public class SingleRecepieFragment extends Fragment {



    RecyclerView listView;
    LinearLayoutManager manager;
ProgressBar bar;


    boolean from_tab;
    String type;
    ArrayList<String> steps_list;

    TextView textView;
    StepSelected stepSelected;




    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.single_recepie_fragment,container,false);

        type=getArguments().getString("type");
        bar=(ProgressBar) v.findViewById(R.id.bar);
        from_tab=getArguments().getBoolean("fromtab");
         listView = (RecyclerView) v.findViewById(R.id.step_list);
        manager=new LinearLayoutManager(getContext());

        if(savedInstanceState!=null){
    steps_list=savedInstanceState.getStringArrayList("list1");
    listView.setAdapter(new StepsRecycleAdapter(getContext(),steps_list,from_tab,stepSelected,type,bar));
            listView.setLayoutManager(manager);

manager.scrollToPosition(savedInstanceState.getInt("index1"));
        }
else {
    steps_list = new ArrayList<>();

    make_network_call(getActivity());
}
        textView=(TextView) v.findViewById(R.id.ingre_textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),IngredientActivity.class);
                intent.putExtra("id",type);
                startActivity(intent);
            }
        });



        return v;
    }
    public void make_network_call(Context context) {
        bar.setVisibility(View.VISIBLE);

        final RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, NetworkUtils.RECIPIE_API, new Response.Listener<String>() {

            @Override
            public void onResponse(final String response) {
                RecepieIdlingResource resource=((SingleRecepieActivity ) getActivity()).getRecepieIdle();
if(resource!=null){
    resource.setIdleState(true);
}
                bar.setVisibility(View.INVISIBLE);
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String id=String.valueOf(object.getLong("id"));
                        if(type.equals(id)){
                            JSONArray steps=object.getJSONArray("steps");
                            JSONArray ingre=object.getJSONArray("ingredients");
                            for(int j=0;j<steps.length();j++){
                                JSONObject object1=steps.getJSONObject(j);
                                String des=object1.getString("shortDescription");
steps_list.add(des);
                            }
                            listView.setLayoutManager(manager);
                            listView.setAdapter(new StepsRecycleAdapter(getActivity(),steps_list,from_tab,stepSelected,type,bar));

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
                Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();

                queue.stop();
            }
        });
        queue.add(request);
    }












    public interface StepSelected{
        void onstepselected(int position);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        stepSelected=(StepSelected)(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list1",steps_list);
        outState.putInt("index1",manager.findFirstVisibleItemPosition());
        //LinearLayoutManager manager;
    }

}
