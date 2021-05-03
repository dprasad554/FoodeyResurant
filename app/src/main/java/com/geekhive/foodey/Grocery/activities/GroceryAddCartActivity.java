package com.geekhive.foodey.Grocery.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.adapter.GroceryCartAdapter;
import com.geekhive.foodey.R;

import java.util.ArrayList;
import java.util.Arrays;

public class GroceryAddCartActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView cartRecyclerView;
    ArrayList groceryQty = new ArrayList<>(Arrays.asList("200 g", "500 g","1 kg"));
    ArrayList groceryPrice = new ArrayList<>(Arrays.asList("MRP : 102", "MRP : 222", "MRP : 419"));
    ArrayList groceryPackage = new ArrayList<>(Arrays.asList("Carton", "Carton", "Carton"));
    Button checkout;
    TextView remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_add_cart);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Checkout");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        remove = findViewById(R.id.tv_remove);
        Typeface CustomRemove = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        remove.setTypeface(CustomRemove);

        //For Pack Size
        cartRecyclerView = (RecyclerView)findViewById(R.id.Cart_RecyclerView);
        LinearLayoutManager layoutCartManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        cartRecyclerView.setLayoutManager(layoutCartManager);
        GroceryCartAdapter cartAdapter = new GroceryCartAdapter(this,groceryQty,groceryPrice,groceryPackage);
        cartRecyclerView.setAdapter(cartAdapter);

        checkout = findViewById(R.id.btn_checkOut);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroceryAddCartActivity.this, GroceryManageAddressActivity.class);
                startActivity(intent);
            }
        });
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
