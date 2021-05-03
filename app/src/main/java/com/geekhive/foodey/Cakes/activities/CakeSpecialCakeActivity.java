package com.geekhive.foodey.Cakes.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.adapter.CakeSpecialListRecyclerAdapter;
import com.geekhive.foodey.Cakes.beans.cakeproductlist.CakeProductlist;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;

public class CakeSpecialCakeActivity extends AppCompatActivity implements OnResponseListner {

    RecyclerView specialRecyclerView;
    Toolbar toolbar;
    ConnectionDetector mDetector;
    String cate,storeid;
    TextView tv_specialCake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_special_cake);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cate = getIntent().getStringExtra("id");
        storeid = getIntent().getStringExtra("storeid");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tv_specialCake = findViewById(R.id.tv_specialCake);
        if(cate.equals("1")){
            tv_specialCake.setText("Normal Cakes");
            getSupportActionBar().setTitle("Normal Cakes");
        }if(cate.equals("2")){
            tv_specialCake.setText("Cool Cakes");
            getSupportActionBar().setTitle("Cool Cakes");
        }if(cate.equals("3")){
            tv_specialCake.setText("Special Cakes");
            getSupportActionBar().setTitle("Special Cakes");
        }if(cate.equals("4")){
            tv_specialCake.setText("Flowers");
            getSupportActionBar().setTitle("Flowers");
        }if(cate.equals("5")){
            tv_specialCake.setText("Other Cakes");
            getSupportActionBar().setTitle("Other Cakes");
        }
        setValues();
        CallService();


    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).Productlist(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.productlist,cate,storeid);
            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.productlist) {
            if (!isSucces) {
                SnackBar.makeText(CakeSpecialCakeActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeProductlist productlist = (CakeProductlist) response;

                if (!isSucces || productlist.getCake() == null) {
                    SnackBar.makeText(CakeSpecialCakeActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } if(!productlist.getCake().get(0).getStoreId().equals(storeid)){
                    SnackBar.makeText(CakeSpecialCakeActivity.this,"No records found", SnackBar.LENGTH_SHORT).show();
                }else {
                    specialRecyclerView = findViewById(R.id.special_recyclerView);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                    specialRecyclerView.setLayoutManager(linearLayoutManager);
                    CakeSpecialListRecyclerAdapter specialAdapter = new CakeSpecialListRecyclerAdapter(this,productlist);
                    specialRecyclerView.setAdapter(specialAdapter);
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
