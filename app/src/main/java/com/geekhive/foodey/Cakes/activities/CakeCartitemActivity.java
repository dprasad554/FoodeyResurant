package com.geekhive.foodey.Cakes.activities;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Cakes.adapter.CakeCartlistAdapter;
import com.geekhive.foodey.Cakes.beans.cakecartlist.CartList;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;

public class CakeCartitemActivity extends AppCompatActivity implements OnResponseListner {
    Toolbar toolbar;
    RecyclerView recentRecyclerView;
    ConnectionDetector mDetector;
    CartList cartList;
    String name,search;
    CakeCheckOutActivityFinal.DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_recent_purchase_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cart List");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setValues();
        name = getIntent().getStringExtra("name");
        search = getIntent().getStringExtra("search");
        CallResentService();
        //For Recent Purchase
        recentRecyclerView = (RecyclerView)findViewById(R.id.recent_recyclerView);

    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallResentService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GetCartList(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cartList, Prefs.getUserId(this));
            return;
        }
    }
    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.cartList) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                cartList = (CartList) response;
                if (cartList.getCartList().getCakeCartDetail() != null) {
                    if (cartList.getCartList().getCakeCartDetail().size() != 0) {
                        LinearLayoutManager layoutShopManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                        recentRecyclerView.setLayoutManager(layoutShopManager);
                        CakeCartlistAdapter shopAdapter = new CakeCartlistAdapter(this,cartList);
                        recentRecyclerView.setAdapter(shopAdapter);
                    }
                }
            }
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
