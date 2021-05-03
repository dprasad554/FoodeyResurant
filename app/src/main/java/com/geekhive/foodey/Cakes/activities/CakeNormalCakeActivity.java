package com.geekhive.foodey.Cakes.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Cakes.adapter.CakeNormalListRecyclerAdapter;
import com.geekhive.foodey.R;

import java.util.ArrayList;

public class CakeNormalCakeActivity extends AppCompatActivity {

    RecyclerView specialRecyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_normalcake);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Normal Cakes");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //For Titles
        ArrayList<String> titlesNames = new ArrayList<String>();
        titlesNames.add("Baked Cheese Cake");
        titlesNames.add("Chocolate Cake");
        titlesNames.add("Carrot & Walnut Cake");
        titlesNames.add("Carrot Mud Cupcakes");
        titlesNames.add("Coconut Cake");
        titlesNames.add("Vanilla Cupcakes");

        //For Images
        ArrayList<Integer> cakesImages = new ArrayList<Integer>();
        cakesImages.add(R.drawable.image1);
        cakesImages.add(R.drawable.image2);
        cakesImages.add(R.drawable.image3);
        cakesImages.add(R.drawable.image4);
        cakesImages.add(R.drawable.image5);
        cakesImages.add(R.drawable.image6);

        specialRecyclerView = findViewById(R.id.normal_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        specialRecyclerView.setLayoutManager(linearLayoutManager);
        CakeNormalListRecyclerAdapter normalAdapter = new CakeNormalListRecyclerAdapter(this,titlesNames,cakesImages);
        specialRecyclerView.setAdapter(normalAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
