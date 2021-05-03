package com.geekhive.foodey.Grocery.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.adapter.GroceryShopStoreCategoryAdapter;
import com.geekhive.foodey.Grocery.beans.grocerycategory.GroceryMainCategory;
import com.geekhive.foodey.Grocery.beans.viewstorerating.ViewStoreRating;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class GroceryShopStoreCategoryActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView storecategoryRecyclerView;
    TextView shopStoreCategory;
    ConnectionDetector mDetector;
    String title,store_id,imageUrl,lati,longi;
    ImageView store_banner;
    TextView vT_store_rating;
    ArrayList serviceImage = new ArrayList<>(Arrays.asList(R.drawable.grocerystaples,R.drawable.household,R.drawable.personal,R.drawable.babykids,
            R.drawable.bevaerages,R.drawable.biscuits,R.drawable.noodlesandsauces,R.drawable.breckfast,R.drawable.homekitchen
            ,R.drawable.furnishing,R.drawable.frozen,R.drawable.patcare,R.drawable.vegitable,R.drawable.homeone));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_shop_store_category);

        toolbar = findViewById(R.id.toolbar);
        vT_store_rating = findViewById(R.id.vT_store_rating);

        title = getIntent().getStringExtra("title");
        store_id = getIntent().getStringExtra("store_id");
        imageUrl = getIntent().getStringExtra("imageUrl");
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");


        store_banner = findViewById(R.id.store_banner);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        shopStoreCategory = findViewById(R.id.tv_shopStoreCategory);
        Typeface CustomCategory = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        shopStoreCategory.setTypeface(CustomCategory);
        shopStoreCategory.setText(title);

        //For Recent
        storecategoryRecyclerView = (RecyclerView)findViewById(R.id.shopstore_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        storecategoryRecyclerView.setLayoutManager(gridLayoutManager);


        String url = WebServices.Foodey_Store_Image_URL + imageUrl;
        Picasso.get().load(url).error(R.drawable.shopstore).fit().placeholder(R.drawable.shopstore).into(store_banner);
        setValues();
        CallService();
    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GroceryMainCategory(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.storemaincategory,store_id);
        }else {
            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }
    private void CallShowStoreRatingService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).ShowStoreRating(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.showStoreRat,store_id);
        }else {
            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.storemaincategory) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final GroceryMainCategory groceryMainCategory = (GroceryMainCategory) response;
                if (!isSuccess || groceryMainCategory == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    CallShowStoreRatingService();
//                    //For Store Main Grocery Category
                    GroceryShopStoreCategoryAdapter shopstoreAdapter = new GroceryShopStoreCategoryAdapter(this,groceryMainCategory,serviceImage);
                    storecategoryRecyclerView.setAdapter(shopstoreAdapter);
                }
            }
        }
        else if (apiType == WebServices.ApiType.showStoreRat) {
            if (!isSuccess) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                ViewStoreRating viewStoreRating = (ViewStoreRating) response;

                if (!isSuccess || viewStoreRating == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (viewStoreRating != null) {
                        if (viewStoreRating.getRaiting() != null) {
                            vT_store_rating.setText(viewStoreRating.getRaiting().get(0).getCRating().getRating());
                        }
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
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
