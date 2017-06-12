package com.example.user.bakingtime;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        TextView Nutellaview,BrownieView,YellowCakeView,CheeseCakeview;
    private static final int NUTELLA=101;
    private static final int BROWN=102;
    private static final int YELLOW=103;
    private static final int CHEESE=104;

    //LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nutellaview=(TextView) findViewById(R.id.nutella_text);
        BrownieView=(TextView) findViewById(R.id.brownie_text);
        YellowCakeView=(TextView) findViewById(R.id.yellow_cake);
        CheeseCakeview=(TextView) findViewById(R.id.cheese_text);

        Nutellaview.setOnClickListener(this);
        BrownieView.setOnClickListener(this);
        YellowCakeView.setOnClickListener(this);
        CheeseCakeview.setOnClickListener(this);

       // try {
         //  bitmap= Picasso.with(this).load(R.drawable.brownie).get();
        //}// catch (IOException e) {
          //  e.printStackTrace();
       // }
       // Nutellaview.setBackgroundDrawable(new BitmapDrawable(getResources(),bitmap));
        //MyAsyncTask task=new MyAsyncTask();
        //task.execute();
      //  layout=(LinearLayout) findViewById(R.id.lay);
       // BackgroungTarget nutellatarget=new BackgroungTarget(this,Nutellaview);
        //BackgroungTarget brownietarget=new BackgroungTarget(this,BrownieView);
        //BackgroungTarget yellowtarget=new BackgroungTarget(this,YellowCakeView);
        //BackgroungTarget cheesetarget=new BackgroungTarget(this,CheeseCakeview);





    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,SingleRecepieActivity.class);
        if((TextView)v==Nutellaview){
            intent.putExtra("recepie",NUTELLA);
            startActivity(intent);
        }
        else if(v==BrownieView){
            intent.putExtra("recepie",BROWN);
            startActivity(intent);
        }
        else if(v==YellowCakeView){
            intent.putExtra("recepie",YELLOW);
            startActivity(intent);
        }
        else {
            intent.putExtra("recepie", CHEESE);
            startActivity(intent);
        }
    }

    public class MyAsyncTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... params) {
            //try {
              //Bitmap map=  Picasso.with(MainActivity.this).load(R.drawable.yellowcake).get();
            //} catch (IOException e) {
              //  e.printStackTrace();
            //}
            //Picasso.with(MainActivity.this).load(R.drawable.nutella_pie).into(Nutellaview);
            //Picasso.with(MainActivity.this).load(R.drawable.cheese).into(CheeseCakeview);
return null;
        }
    }

}
