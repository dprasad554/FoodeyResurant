package com.geekhive.foodey.Cakes.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Cakes.adapter.CakeShopStoreRecyclerAdapter;
import com.geekhive.foodey.Cakes.beans.cakestore.CakeStoreList;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;

public class CakeShopStoreActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView shopnameRecyclerView;
    ConnectionDetector mDetector;


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


        setValues();
        CallService();

    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).CakeStore(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.cakestore, Prefs.getUserId(this));
            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.cakestore) {
            if (!isSucces) {
                SnackBar.makeText(CakeShopStoreActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeStoreList cakeStoreList = (CakeStoreList) response;

                if (!isSucces || cakeStoreList == null) {
                    SnackBar.makeText(CakeShopStoreActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {

                    LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                    shopnameRecyclerView.setLayoutManager(layoutspecialManager);
                    CakeShopStoreRecyclerAdapter specialadapter = new CakeShopStoreRecyclerAdapter(this,cakeStoreList);
                    shopnameRecyclerView.setAdapter(specialadapter);
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
