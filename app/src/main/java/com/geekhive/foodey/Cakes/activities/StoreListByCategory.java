package com.geekhive.foodey.Cakes.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Cakes.adapter.CakeStorebycategory;
import com.geekhive.foodey.Cakes.beans.cakestorebycategory.StorebyCategory;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;

public class StoreListByCategory extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView shopnameRecyclerView;
    ConnectionDetector mDetector;
    String categoryname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_shop_store);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shop By Store");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        shopnameRecyclerView =(RecyclerView)findViewById(R.id.shopname_recyclerView);
        categoryname = getIntent().getStringExtra("categoryname");

        setValues();
        CakeStorbycategories();

    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CakeStorbycategories() {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).CakeStoreByCategories(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cakestorebycategory, Prefs.getUserId(this),categoryname);
            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.cakestorebycategory) {
            if (!isSucces) {
                SnackBar.makeText(StoreListByCategory.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final StorebyCategory storebyCategory = (StorebyCategory) response;

                if (!isSucces || storebyCategory == null) {
                    SnackBar.makeText(StoreListByCategory.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                    shopnameRecyclerView.setLayoutManager(layoutspecialManager);
                    CakeStorebycategory cakeStorebycategory = new CakeStorebycategory(this,storebyCategory);
                    shopnameRecyclerView.setAdapter(cakeStorebycategory);
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
