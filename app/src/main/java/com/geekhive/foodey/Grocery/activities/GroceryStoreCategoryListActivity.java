package com.geekhive.foodey.Grocery.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;

public class GroceryStoreCategoryListActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView storeRecyclerView;
    ConnectionDetector mDetector;
    String title,product_sub_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_store_category_list);

        toolbar = findViewById(R.id.toolbar);
        title = getIntent().getStringExtra("title");
        product_sub_category = getIntent().getStringExtra("sub_id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //For Pack Size
        storeRecyclerView = (RecyclerView)findViewById(R.id.storecategory_RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        storeRecyclerView.setLayoutManager(gridLayoutManager);

        setValues();
        CallService();
    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
//        if (this.mDetector.isConnectingToInternet()) {
//            new WebServices(this).StoreCategoryProductList(WebServices.Foodey_Grocery_Services,
//                    WebServices.ApiType.storecategorysublist,product_sub_category);
//        }else {
//            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
//        }
//        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
//        if (apiType == WebServices.ApiType.storecategorysublist) {
//            if (!isSuccess) {
//                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
//            } else {
//                final StoreSubProductList storeCategoryProductList = (StoreSubProductList) response;
//                if (!isSuccess || storeCategoryProductList == null) {
//                    SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
//                } else {
//                    if(storeCategoryProductList.getGrocery() == null){
//                        SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
//                    }else {
//                        //For Sub Category
//                        StoreCategoryListAdapter storeAdapter = new StoreCategoryListAdapter(this,storeCategoryProductList, title);
//                        storeRecyclerView.setAdapter(storeAdapter);
//                    }
//                }
//            }
//        }
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
