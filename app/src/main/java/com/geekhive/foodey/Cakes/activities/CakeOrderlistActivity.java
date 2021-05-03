package com.geekhive.foodey.Cakes.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.activities.GroceryCheckOutActivityNew;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class CakeOrderlistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recentRecyclerView;
    ConnectionDetector mDetector;
    String title,price,Quantity,URL;
    GroceryCheckOutActivityNew.DishAdapter dishAdapter;

    LinearLayout vL_add_main, vL_add_hide;
    ImageView vI_add_veg;
    TextView vT_add_name, vT_add_price, vT_add_add, vT_add_decrease, vT_add_quantity, vT_add_increase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_orderdetials_adapter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order Details");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        title = getIntent().getStringExtra("title");
        price = getIntent().getStringExtra("price");
        Quantity = getIntent().getStringExtra("Quantity");
        URL = getIntent().getStringExtra("URL");

        vT_add_name = (TextView) findViewById(R.id.vT_add_name);
        vT_add_price = (TextView) findViewById(R.id.vT_add_price);
        vT_add_decrease = (TextView) findViewById(R.id.vT_add_decrease);
        vI_add_veg = (ImageView) findViewById(R.id.vI_add_veg);


        vT_add_name.setText(title);
        vT_add_price.setText(price);
        vT_add_decrease.setText(Quantity);
        if (!URL.equals("")){
            Picasso.get()
                    .load(URL)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(vI_add_veg);
        }

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

