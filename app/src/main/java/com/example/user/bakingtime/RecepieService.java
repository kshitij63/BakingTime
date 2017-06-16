package com.example.user.bakingtime;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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

/**
 * Created by user on 6/14/2017.
 */

public class RecepieService extends IntentService {
     ArrayList<String> recep_lisst;
    ArrayList<String> Ingredient_List;
    String mai;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *   Used to name the worker thread, important only for debugging.
     */
    public RecepieService() {
        super("RecepieService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("called","in han");

    if(intent.getStringExtra("get").equals("list")){
        Log.e("called","in if");
        start_network(this.getApplicationContext());
   // get_Ingredient();
    }

    }

    public static void start_service(Context context){
        Intent intent=new Intent(context,RecepieService.class);
        intent.putExtra("get","list");
        context.startService(intent);
    }
    public ArrayList<String> start_network(final Context context){
        Log.e("called","start network");

        recep_lisst=new ArrayList<>();
        final RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, NetworkUtils.RECIPIE_API, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {

                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String dish=object.getString("name");
                                            recep_lisst.add(dish);

                    }
                    Log.e("called","method" +recep_lisst.size());
get_Ingredient();

                } catch (JSONException e) {
                    Log.e("error try",e.getMessage());
                }
                queue.stop();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("here we", error.getMessage());
                //           bar.setVisibility(View.INVISIBLE);
                //Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();

                queue.stop();
            }
        });
        queue.add(request);
        return recep_lisst;
    }


    public void get_Ingredient(){
      Ingredient_List=  new ArrayList<>();
        //bar.setVisibility(View.VISIBLE);

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, NetworkUtils.RECIPIE_API, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
               // bar.setVisibility(View.INVISIBLE);
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        //String id=String.valueOf(object.getLong("id"));

                        String list_ingre=null;
                            JSONArray steps=object.getJSONArray("ingredients");
                            for(int j=0;j<steps.length();j++){
                                JSONObject object1=steps.getJSONObject(j);
                                String ingre=object1.getString("quantity") +" "+object1.getString("measure") +" of " +object1.getString("ingredient");
                                if(list_ingre==null){
                                    list_ingre=ingre;
                                }
                                else {
                                    list_ingre=list_ingre +"\n\n" +ingre;
//                                    list_ingre=list_ingre
                                    mai=list_ingre;
                                }
                                //Ingredient_List.add(list_ingre);
                                }
               //mai=list_ingre;
                                Log.e("called",mai);
                                Ingredient_List.add(mai);

                 //           listView.setAdapter(new IngredientAdapter(IngredientActivity.this,Ingredient_List,Ingredient_List.size()));
                    }
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getApplication(), RecepieWidgetProvider.class));
                    RecepieWidgetProvider.mymet(getApplicationContext(),appWidgetManager,appWidgetIds,recep_lisst,Ingredient_List);
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.list_widget);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                queue.stop();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //bar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

                queue.stop();
            }
        });
        queue.add(request);

    }


}



