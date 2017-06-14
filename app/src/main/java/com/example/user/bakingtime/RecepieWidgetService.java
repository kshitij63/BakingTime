package com.example.user.bakingtime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
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

public class RecepieWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteFactory(this.getApplicationContext());
    }
}


class ListRemoteFactory implements RemoteViewsService.RemoteViewsFactory{



    Context context;
    ArrayList<String> recep_lisst;

    ListRemoteFactory(Context app){
        context=app;
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        //bar.setVisibility(View.VISIBLE);

        recep_lisst=new ArrayList<>();
        final RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, NetworkUtils.RECIPIE_API, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
               // bar.setVisibility(View.INVISIBLE);
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String dish=object.getString("name");
                       // String url=object.getString("image");
                        //Recepie recepie=new Recepie(url,dish);
                        //    for_widget.add(dish);

                        recep_lisst.add(dish);
                    }
                   // listView.setAdapter(new RecipieAdapter(MainActivity.this,main_list));

//Bundle bundle=new Bundle();
                    //                  bundle.putStringArrayList("widget_list",for_widget);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                queue.stop();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
     //           bar.setVisibility(View.INVISIBLE);
                //Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();

                queue.stop();
            }
        });
        queue.add(request);
        Log.e("har",recep_lisst.size()+"");
    }



    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return recep_lisst.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.e("har2",recep_lisst.size()+"");

        RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.recepie_widget_provider);
        views.setTextViewText(R.id.name_widget,recep_lisst.get(position));
        Bundle extras = new Bundle();
        extras.putLong("id",1);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        views.setOnClickFillInIntent(R.id.name_widget, fillInIntent);
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}