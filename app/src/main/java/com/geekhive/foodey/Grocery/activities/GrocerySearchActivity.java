package com.geekhive.foodey.Grocery.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.adapter.GroceryProductSearchAdapter;
import com.geekhive.foodey.Grocery.beans.searchdetails.GroceryList;
import com.geekhive.foodey.Grocery.beans.searchdetails.SearchDetails;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;

import java.util.ArrayList;
import java.util.List;

public class GrocerySearchActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView vR_g_list;
    ConnectionDetector mDetector;
    String name,search,store_id;
    SearchView searchView;
    SearchDetails searchDetails;
    List<GroceryList> groceryList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Search Result");

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        vR_g_list = (RecyclerView)findViewById(R.id.vR_g_list);
        searchView = findViewById(R.id.searchView);
        setValues();




    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);

        store_id = getIntent().getStringExtra("store_id");

        searchView.setQueryHint("Search");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setQuery("", true);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CallSearchservice(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void CallSearchservice(String name) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).CallSearchService(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.groceryproductsearch,Prefs.getUserId(this), name,store_id);
            return;
        }
    }
    private void callglobalsearchservice(String name) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).Callglobalsearchservice(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.callsearchservice,Prefs.getUserId(this), name);
            return;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
//        if (apiType == WebServices.ApiType.callsearchservice) {
//            if (!isSuccess) {
//                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
//            } else {
//                final SearchDetails searchDetails = (SearchDetails) response;
//                if (!isSuccess || searchDetails.getGrocery() == null) {
//                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
//                } else {
//                    LinearLayoutManager layoutShopManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//                    recentRecyclerView.setLayoutManager(layoutShopManager);
//                    GrocerySearchAdapter shopAdapter = new GrocerySearchAdapter(this,searchDetails);
//                    recentRecyclerView.setAdapter(shopAdapter);
//                }
//            }
//        }
        if (apiType == WebServices.ApiType.groceryproductsearch) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                  searchDetails = (SearchDetails) response;
                if (!isSuccess || searchDetails == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    if (searchDetails.getGroceryList() != null) {
                        if (searchDetails.getGroceryList().size() != 0) {
                            groceryList = new ArrayList<>();
                            groceryList.addAll(searchDetails.getGroceryList());
                            LinearLayoutManager layoutShopManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                            vR_g_list.setLayoutManager(layoutShopManager);
                            GroceryProductSearchAdapter groceryProductSearchAdapter = new GroceryProductSearchAdapter(GrocerySearchActivity.this, searchDetails);
                            vR_g_list.setAdapter(groceryProductSearchAdapter);
                        }
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