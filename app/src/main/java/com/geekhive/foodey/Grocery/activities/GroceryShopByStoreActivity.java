package com.geekhive.foodey.Grocery.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.adapter.GroceryShopStoreAdapter;
import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;

public class GroceryShopByStoreActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView shopstorerecyclerView;
    ConnectionDetector mDetector;
    GroceryStoreNameList storeNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_shop_by_store);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shop By Store");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //For ShopStore RecyclerView
        shopstorerecyclerView = findViewById(R.id.storeshope_RecyclerView);
        LinearLayoutManager shopStoreManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        shopstorerecyclerView.setLayoutManager(shopStoreManager);

        setValues();
        CallService();
    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).StoreName(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.storename, Prefs.getUserId(this));
            return;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.storename) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                storeNameList = (GroceryStoreNameList) response;
                GroceryShopStoreAdapter shopstoreAdapter = new GroceryShopStoreAdapter(this, storeNameList);
                shopstorerecyclerView.setAdapter(shopstoreAdapter);
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
