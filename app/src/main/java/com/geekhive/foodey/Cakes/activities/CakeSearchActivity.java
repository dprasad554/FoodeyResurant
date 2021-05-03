package com.geekhive.foodey.Cakes.activities;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Cakes.adapter.CakeSearchAdapter;
import com.geekhive.foodey.Cakes.beans.cakesearchdetails.CakeSearchDetails;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;

public class CakeSearchActivity extends AppCompatActivity implements OnResponseListner {
    Toolbar toolbar;
    RecyclerView recentRecyclerView;
    ConnectionDetector mDetector;
    String name,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_search_activity);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Result");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setValues();
        name = getIntent().getStringExtra("name");
        search = getIntent().getStringExtra("search");
        CallSearchservice();
        //For Recent Purchase
        recentRecyclerView = (RecyclerView)findViewById(R.id.recent_recyclerView);

    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallSearchservice() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).CallSearchService(WebServices.Foodey_Cake_Services,
                    WebServices.ApiType.callsearchservice,name);
            return;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.callsearchservice) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeSearchDetails cakeSearchDetails = (CakeSearchDetails) response;
                if (!isSuccess || cakeSearchDetails.getCake() == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    LinearLayoutManager layoutShopManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                    recentRecyclerView.setLayoutManager(layoutShopManager);
                    CakeSearchAdapter shopAdapter = new CakeSearchAdapter(this,cakeSearchDetails);
                    recentRecyclerView.setAdapter(shopAdapter);
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
