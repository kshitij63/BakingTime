package com.example.user.bakingtime;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RecepieWidgetProvider extends AppWidgetProvider {
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Bundle options=appWidgetManager.getAppWidgetOptions(appWidgetId);
        int widht=options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
            RemoteViews mv=null;

                mv=big(context);


            appWidgetManager.updateAppWidget(appWidgetId, mv);




        // Construct the RemoteViews object


        // Instruct the widget manager to update the widget
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    public static RemoteViews small(Context context){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recepie_widget_provider);
        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent intent1=PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.cheese_widget,intent1);
    return views;
    }

    public static RemoteViews big(Context context){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_listview);
        // Set the GridWidgetService intent to act as the adapter for the GridView
        Intent intent = new Intent(context, RecepieWidgetService.class);
        views.setRemoteAdapter(R.id.list_widget, intent);
        // Set the PlantDetailActivity intent to launch when clicked
        Intent appIntent = new Intent(context, IngredientActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.list_widget, appPendingIntent);
        // Handle empty gardens
        views.setEmptyView(R.id.list_widget, R.id.empty_view);
        return views;
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }
}

