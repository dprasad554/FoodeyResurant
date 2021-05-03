package com.geekhive.foodey.Grocery.activities;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.adapter.GroceryShopStoreSubCategoryAdapter;
import com.geekhive.foodey.Grocery.adapter.GrocerySliderViewPagerAdapter;
import com.geekhive.foodey.Grocery.beans.grocerysubcategory.GrocerySubCategory;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class GroceryStoreSubCategoryActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    Timer timer1;
    DotsIndicator dot1;
    ViewPager viewPager;
    GrocerySliderViewPagerAdapter homePagerAdapter;
    int slide[] = {R.drawable.homeone, R.drawable.hometwo, R.drawable.homethree, R.drawable.homefour};
    ArrayList serviceImage = new ArrayList<>(Arrays.asList(R.drawable.grocerystaples, R.drawable.household, R.drawable.personal, R.drawable.babykids,
            R.drawable.bevaerages, R.drawable.biscuits, R.drawable.noodlesandsauces, R.drawable.breckfast, R.drawable.homekitchen
            , R.drawable.furnishing, R.drawable.frozen, R.drawable.patcare, R.drawable.vegitable, R.drawable.homeone));

    ArrayList categoryTitle = new ArrayList<>(Arrays.asList("Pulses", "Aatta & Others Flours", "Rice & others Grains", "Dry Fruits & Nuts", "Edible Oils", "Ghee & Vanaspati"
            , "Spices", "Salt & Sugar", "Organic Staples", "Vegetables", "Fruits", "Others"));
    RecyclerView storeDetailRecyclerView;
    TextView storeCategory;
    String title, id,storeId;
    ConnectionDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_store_sub_category);

        toolbar = findViewById(R.id.toolbar);
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        storeId = getIntent().getStringExtra("store_id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //For Home Screen Slider
        dot1 = findViewById(R.id.dot1);
        viewPager = (ViewPager) findViewById(R.id.home_ViewPager);

        //For First Slide
        homePagerAdapter = new GrocerySliderViewPagerAdapter(this, slide);
        viewPager.setAdapter(homePagerAdapter);
        dot1.setViewPager(viewPager);

        //For First Slider Timer
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % slide.length);
                    }
                });
            }
        };

        timer1 = new Timer();
        timer1.schedule(timerTask1, 4800, 4800);

        //For Shop
        storeDetailRecyclerView = (RecyclerView) findViewById(R.id.StoreDetail_RecyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        storeDetailRecyclerView.setLayoutManager(gridLayoutManager);

        storeCategory = findViewById(R.id.tv_store_byCategory);
        Typeface CustomCategory = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        storeCategory.setTypeface(CustomCategory);
        storeCategory.setText(title);

        setValues();
        CallService();
    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GrocerySubCategory(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.storesubcategory, id);
        } else {
            SnackBar.makeText(this, "Something", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.storesubcategory) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final GrocerySubCategory subCategory = (GrocerySubCategory) response;
                if (!isSuccess || subCategory == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    //For Sub Category
                    GroceryShopStoreSubCategoryAdapter subcategoryAdapter = new GroceryShopStoreSubCategoryAdapter(this, subCategory);
                    storeDetailRecyclerView.setAdapter(subcategoryAdapter);
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
