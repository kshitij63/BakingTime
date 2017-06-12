package com.example.user.bakingtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class IngredientActivity extends AppCompatActivity {
ListView listView;
    ArrayList<String> Ingredient_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        Bundle bundle=getIntent().getExtras();
        Ingredient_List=bundle.getStringArrayList("list");
        listView=(ListView) findViewById(R.id.ingredient_list);
        listView.setAdapter(new IngredientAdapter(this,Ingredient_List));

    }
}
