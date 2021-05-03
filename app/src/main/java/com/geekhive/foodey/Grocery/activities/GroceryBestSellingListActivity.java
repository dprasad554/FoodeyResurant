package com.geekhive.foodey.Grocery.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.adapter.GroceryBestSellingListAdapter;
import com.geekhive.foodey.Grocery.beans.grocerybestselling.GroceryBestSelling;
import com.geekhive.foodey.Grocery.beans.recentpurchase.RecentPurchase;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;

public class GroceryBestSellingListActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView bestSellingRecyclerView,searchRecyclerview;
    ConnectionDetector mDetector;
    RecentPurchase recentPurchase;
    String name,search;
    SearchView searchView;
    String store_id;
    GroceryBestSelling groceryBestSelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_best_selling_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Best Selling Products");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = getIntent().getStringExtra("name");
        search = getIntent().getStringExtra("search");
        store_id = getIntent().getStringExtra("store_id");


        //For Recent Purchase
        bestSellingRecyclerView = (RecyclerView)findViewById(R.id.bestselling);
//        searchRecyclerview = (RecyclerView)findViewById(R.id.searchRecyclerview);

        searchView = (SearchView) findViewById(R.id.searchView);


        setValues();


    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);

        store_id = getIntent().getStringExtra("store_id");

//        searchView.setQueryHint("Search");
//        searchView.onActionViewExpanded();
//        searchView.setIconified(false);
//        searchView.clearFocus();
//        searchView.setQuery("", true);

        CallBestSellingService();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
//////                CallService(query);
////                return true;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                return false;
////            }
////        });
    }
    private void CallBestSellingService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).BestSellingservice(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.bestsellingservice, Prefs.getUserId(this));
            return;
        }
    }

//    private void CallService(String searchName) {
//        if (this.mDetector.isConnectingToInternet()) {
//            new WebServices(this).CallSearchService(WebServices.Foodey_Grocery_Services,
//                    WebServices.ApiType.callproductsearchservice, Prefs.getUserId(this), searchName,store_id);
//        }
//
//    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
//        if (apiType == WebServices.ApiType.recentservice) {
//            if (!isSuccess) {
//                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
//            } else {
//                recentPurchase = (RecentPurchase) response;
//                if (recentPurchase.getRecentPurchase() != null) {
//                    if (recentPurchase.getRecentPurchase().getGCartDetail().size() != 0) {
//                        LinearLayoutManager layoutShopManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//                        recentRecyclerView.setLayoutManager(layoutShopManager);
//                        GroceryRecentPurchaseListAdapter shopAdapter = new GroceryRecentPurchaseListAdapter(this,recentPurchase);
//                        recentRecyclerView.setAdapter(shopAdapter);
//                    }
//                }
//            }
//        }

        if (apiType == WebServices.ApiType.bestsellingservice) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                groceryBestSelling = (GroceryBestSelling) response;
                if (groceryBestSelling.getGroceryList() != null) {

                    if (groceryBestSelling.getGroceryList().getGrocery().size() != 0) {


                        LinearLayoutManager layoutSellingManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                        bestSellingRecyclerView.setLayoutManager(layoutSellingManager);
                        GroceryBestSellingListAdapter bestSellingAdapter = new GroceryBestSellingListAdapter(this,groceryBestSelling);
                        bestSellingRecyclerView.setAdapter(bestSellingAdapter);

                    }
                }
            }
        }
//        if (apiType == WebServices.ApiType.callproductsearchservice) {
//            if (!isSuccess) {
//                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
//            } else {
//                 SearchDetails searchDetails = (SearchDetails) response;
//                if (!isSuccess || searchDetails == null) {
//                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
//                } else {
//                    if (searchDetails.getGroceryList()!= null) {
//                        LinearLayoutManager layoutShopManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//                        searchRecyclerview.setLayoutManager(layoutShopManager);
//                        GroceryProductSearchAdapter shopProductAdapter = new GroceryProductSearchAdapter(GroceryRecentPurchaseListActivity.this, searchDetails);
//                        searchRecyclerview.setAdapter(shopProductAdapter);
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
