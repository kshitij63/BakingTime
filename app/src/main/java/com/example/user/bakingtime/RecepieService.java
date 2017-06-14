package com.example.user.bakingtime;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

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

                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecepieWidgetProvider.class));
                    RecepieWidgetProvider.mymet(context,appWidgetManager,appWidgetIds,recep_lisst);

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


}



