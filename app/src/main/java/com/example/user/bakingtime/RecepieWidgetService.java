package com.example.user.bakingtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        Log.e("called","widgetservices" +intent.getStringArrayListExtra("yelo"));

        return new ListRemoteFactory(this.getApplicationContext(),intent.getStringArrayListExtra("yelo"),intent.getStringArrayListExtra("yelo2"));
    }
}


class ListRemoteFactory implements RemoteViewsService.RemoteViewsFactory{

SharedPreferences preferences;
    Context context;
    ArrayList<String> recep_lisst;
    ArrayList<String> Ingre_imp;

    ListRemoteFactory(Context app,ArrayList<String> recep_lisst,ArrayList<String> Ingre_imp){
        context=app;
        this.recep_lisst=recep_lisst;
        this.Ingre_imp=Ingre_imp;
        preferences=context.getSharedPreferences("none",Context.MODE_PRIVATE);

    }
    @Override
    public void onCreate() {
        Log.e("called","oncreate");
    }

    @Override
    public void onDataSetChanged() {

        //bar.setVisibility(View.VISIBLE);


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
        Log.e("called",recep_lisst.size()+"");

        RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.recepie_widget_provider);
        views.setTextViewText(R.id.name_widget,recep_lisst.get(position));
        views.setTextViewText(R.id.ingredient_widget,Ingre_imp.get(position));
        Bundle extras = new Bundle();
        String name_m=preferences.getString(recep_lisst.get(position),"none");
        extras.putString("id",name_m);
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