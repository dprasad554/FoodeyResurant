package com.geekhive.foodey.Grocery.activities;

import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.geekhive.foodey.Grocery.adapter.GroceryProductListRecentAdapter;
import com.geekhive.foodey.Grocery.beans.groceryproductlist.GrocerySubProductList;
import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.FavoriteDatabase;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.ShoppingListDatabase;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;

public class GroceryShopProductListActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView productRecyclerView;
    String title, product_sub_category;
    ConnectionDetector mDetector;
    String url = "";
    String storeid,lati,longi;
    GroceryStoreNameList storeNameList;

    public static FavoriteDatabase favoriteDatabase;
    public  static ShoppingListDatabase shoppingListDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_shop_product_list);

        toolbar = findViewById(R.id.toolbar);
        title = getIntent().getStringExtra("title");
        product_sub_category = getIntent().getStringExtra("sub_id");
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        storeid = getIntent().getStringExtra("store_id");
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //For Pack Size
        productRecyclerView = (RecyclerView) findViewById(R.id.ProductList_RecyclerView);
        favoriteDatabase = Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class, "myfavdb").allowMainThreadQueries().build();
        shoppingListDatabase = Room.databaseBuilder(getApplicationContext(), ShoppingListDatabase.class, "mytuu").allowMainThreadQueries().build();

        setValues();
        CallService();
    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).SubProductList(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.productlist, product_sub_category);
        } else {
            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {

        if (apiType == WebServices.ApiType.productlist) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final GrocerySubProductList subProductList = (GrocerySubProductList) response;
                if (subProductList != null) {
                    if (subProductList.getGrocery() != null) {
                        storeid=subProductList.getGrocery().get(0).getStoreId();
                        LinearLayoutManager layoutProductManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                        productRecyclerView.setLayoutManager(layoutProductManager);
                        GroceryProductListRecentAdapter listAdapter = new GroceryProductListRecentAdapter(this, subProductList);
                        productRecyclerView.setAdapter(listAdapter);
                    } else {

                        SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                    }
                }else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();

                }

            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shoplist_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favorite) {
            Intent intent=new Intent(GroceryShopProductListActivity.this, GroceryFavoriteListActivity.class);

            intent.putExtra("store_id", storeid);
            intent.putExtra("url",url);
            intent.putExtra("lati",lati);
            intent.putExtra("longi",longi);
            intent.putExtra("title",title);
            intent.putExtra("sub_id",product_sub_category);

            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.shoppinglist) {
            Intent intent=new Intent(GroceryShopProductListActivity.this, GroceryShopingListActivity.class);

            intent.putExtra("store_id", storeid);
            intent.putExtra("url",url);
            intent.putExtra("lati",lati);
            intent.putExtra("longi",longi);
            intent.putExtra("title",title);
            intent.putExtra("sub_id",product_sub_category);

            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.search) {
            Intent intent=new Intent(GroceryShopProductListActivity.this, GrocerySearchActivity.class);
            intent.putExtra("store_id", storeid);

            startActivity(intent);
            finish();
            return true;
        }
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
