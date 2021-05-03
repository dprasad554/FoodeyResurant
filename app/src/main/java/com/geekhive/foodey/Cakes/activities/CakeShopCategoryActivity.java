package com.geekhive.foodey.Cakes.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.geekhive.foodey.R;

import java.util.ArrayList;
import java.util.Arrays;

public class CakeShopCategoryActivity extends AppCompatActivity {

    TextView special,cool,normal,viewSpecial,viewCool,viewNormal;
    Toolbar toolbar;
    RecyclerView specialrecyclerView,coolcakerecyclerView,normalcakerecyclerView;
    ArrayList titlesNames = new ArrayList<>(Arrays.asList("Baked Cheesecake", "Chocolate Cake", "Carrot & Walnut Cake",
            "Carrot Mud Cupcakes","Coconut Cake","Vanilla Cupcakes"));
    ArrayList cakeImages = new ArrayList<>(Arrays.asList(R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5,R.drawable.image6));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_shop_category);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shop By Category");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shop Product");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        special = findViewById(R.id.tv_specialCake);
       /* Typeface CustomSpecial = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        special.setTypeface(CustomSpecial);*/

        viewSpecial = findViewById(R.id.tv_viewSpecial);
       /* Typeface CustomViewS = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewSpecial.setTypeface(CustomViewS);*/
        viewSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopCategoryActivity.this, CakeSpecialCakeActivity.class);
                startActivity(intent);
            }
        });

        cool = findViewById(R.id.tv_coolCakes);
        /*Typeface CustomCool = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        cool.setTypeface(CustomCool);*/

        viewCool = findViewById(R.id.tv_viewCool);
       /* Typeface CustomViewC = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewCool.setTypeface(CustomViewC);*/
        viewCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopCategoryActivity.this, CakeCoolCakeActivity.class);
                startActivity(intent);
            }
        });

        normal = findViewById(R.id.tv_normalCakes);
        /*Typeface CustomNormal = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        normal.setTypeface(CustomNormal);*/

        viewNormal = findViewById(R.id.tv_viewNormal);
       /* Typeface CustomViewN = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewNormal.setTypeface(CustomViewN);*/
        viewNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopCategoryActivity.this, CakeNormalCakeActivity.class);
                startActivity(intent);
            }
        });

       /* LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        specialrecyclerView =(RecyclerView)findViewById(R.id.special_recyclerView);
        specialrecyclerView.setLayoutManager(layoutspecialManager);
        SpecialRecyclerAdapter specialadapter = new SpecialRecyclerAdapter(this,titlesNames,cakeImages);
        specialrecyclerView.setAdapter(specialadapter);

        LinearLayoutManager layoutcoolManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        coolcakerecyclerView =(RecyclerView) findViewById(R.id.coolcake_recyclerView);
        coolcakerecyclerView.setLayoutManager(layoutcoolManager);
        CoolRecyclerViewAdapter cakeadapter = new CoolRecyclerViewAdapter(this,titlesNames,cakeImages);
        coolcakerecyclerView.setAdapter(cakeadapter);

        LinearLayoutManager layoutnormalManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        normalcakerecyclerView =(RecyclerView) findViewById(R.id.normalcake_recyclerView);
        normalcakerecyclerView.setLayoutManager(layoutnormalManager);
        CoolRecyclerViewAdapter normaladapter = new CoolRecyclerViewAdapter(this,titlesNames,cakeImages);
        normalcakerecyclerView.setAdapter(normaladapter);*/
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
