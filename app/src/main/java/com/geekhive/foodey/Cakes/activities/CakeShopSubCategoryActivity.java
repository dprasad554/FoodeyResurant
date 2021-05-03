package com.geekhive.foodey.Cakes.activities;

import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.adapter.CakeCoolRecyclerViewAdapter;
import com.geekhive.foodey.Cakes.adapter.CakeFlowerRecyclerAdapter;
import com.geekhive.foodey.Cakes.adapter.CakeNormalRecyclerAdapter;
import com.geekhive.foodey.Cakes.adapter.CakeOtherRecyclerAdapter;
import com.geekhive.foodey.Cakes.adapter.CakeSpecialRecyclerAdapter;
import com.geekhive.foodey.Cakes.beans.cakecategory.Category;
import com.geekhive.foodey.Cakes.beans.cakeproductlist.CakeProductlist;
import com.geekhive.foodey.Cakes.beans.storerating.StoreRating;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.CakeFavoriteDatabase;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

import static com.geekhive.foodey.Cakes.utils.WebServices.ApiType.resRat;

public class CakeShopSubCategoryActivity extends AppCompatActivity implements OnResponseListner {

    TextView special,cool,normal,viewSpecial,viewCool,viewNormal,viewFlower,viewOther,tv_storename;
    Toolbar toolbar;
    RecyclerView specialrecyclerView,coolcakerecyclerView,normalcakerecyclerView,othercakerecyclerView,flowercakerecyclerView;
    ImageView store_image;
    String url = "";
    ConnectionDetector mDetector;
    String title,storeid,lati,longi;
    SearchView searchView;
    Category category;
    LinearLayout ll_special,ll_cool,ll_normal,ll_flower,ll_other;
    public static CakeFavoriteDatabase favoriteDatabase;
    @BindView(R.id.resRating)
    TextView resRating;
    TextView tv_comment,tv_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_shop_sub_category);

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        storeid = getIntent().getStringExtra("storeid");
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ll_special = findViewById(R.id.ll_special);
        ll_cool = findViewById(R.id.ll_cool);
        ll_normal = findViewById(R.id.ll_normal);
        ll_flower = findViewById(R.id.ll_flower);
        ll_other = findViewById(R.id.ll_other);


        tv_status = findViewById(R.id.tv_status);
        tv_comment = findViewById(R.id.tv_comment);
        resRating = findViewById(R.id.resRating);
        searchView = findViewById(R.id.searchView);
        store_image = findViewById(R.id.store_image);
        if (url != null){
            if (!url.equals("")){
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(store_image);
            }
        }


        special = findViewById(R.id.tv_specialCake);
       /* Typeface CustomSpecial = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        special.setTypeface(CustomSpecial);*/

        viewSpecial = findViewById(R.id.tv_viewSpecial);
       /* Typeface CustomViewS = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewSpecial.setTypeface(CustomViewS);*/
        viewSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopSubCategoryActivity.this, CakeSpecialCakeActivity.class);
                intent.putExtra("id", category.getCakeCategory().get(2).getId());
                intent.putExtra("storeid",storeid);
                startActivity(intent);
            }
        });

        cool = findViewById(R.id.tv_coolCakes);
       /* Typeface CustomCool = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        cool.setTypeface(CustomCool);*/

        viewCool = findViewById(R.id.tv_viewCool);
        /*Typeface CustomViewC = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewCool.setTypeface(CustomViewC);*/
        viewCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopSubCategoryActivity.this, CakeSpecialCakeActivity.class);
                intent.putExtra("id", category.getCakeCategory().get(1).getId());
                intent.putExtra("storeid",storeid);
                startActivity(intent);
            }
        });
        normal = findViewById(R.id.tv_normalCakes);
       /* Typeface CustomNormal = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        normal.setTypeface(CustomNormal);*/

        viewNormal = findViewById(R.id.tv_viewNormal);
       /* Typeface CustomViewN = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewNormal.setTypeface(CustomViewN);*/
        viewNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopSubCategoryActivity.this, CakeSpecialCakeActivity.class);
                intent.putExtra("id", category.getCakeCategory().get(0).getId());
                intent.putExtra("storeid",storeid);
                startActivity(intent);
            }
        });

        viewFlower = findViewById(R.id.tv_viewFlower);
      /*  Typeface CustomViewF = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewFlower.setTypeface(CustomViewF);*/
        viewFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopSubCategoryActivity.this, CakeSpecialCakeActivity.class);
                intent.putExtra("id", category.getCakeCategory().get(3).getId());
                intent.putExtra("storeid",storeid);
                startActivity(intent);
            }
        });

        viewOther = findViewById(R.id.tv_viewOther);
       /* Typeface CustomViewO = Typeface.createFromAsset(this.getAssets(), "fonts/fonts/JosefinSans-Bold");
        viewOther.setTypeface(CustomViewO);*/
        viewOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeShopSubCategoryActivity.this, CakeSpecialCakeActivity.class);
                intent.putExtra("id", category.getCakeCategory().get(4).getId());
                intent.putExtra("storeid",storeid);
                startActivity(intent);
            }
        });
        specialrecyclerView =(RecyclerView)findViewById(R.id.special_recyclerView);
        coolcakerecyclerView =(RecyclerView) findViewById(R.id.coolcake_recyclerView);
        normalcakerecyclerView =(RecyclerView) findViewById(R.id.normalcake_recyclerView);
        flowercakerecyclerView =(RecyclerView) findViewById(R.id.flowercake_recyclerView);
        othercakerecyclerView =(RecyclerView) findViewById(R.id.othercake_recyclerView);

        favoriteDatabase = Room.databaseBuilder(getApplicationContext(), CakeFavoriteDatabase.class, "mycakefavdb").allowMainThreadQueries().build();
        setValues();
        CallService();
        CallRatingService();

    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).Cakecategory(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cakecategory,storeid);
            return;
        }
    }
    private void CallSpecialService() {
        if (this.mDetector.isConnectingToInternet()) {
            if(category.getCakeCategory().get(2).getCategoryType().equals("Spl Design Cake")){
                new WebServices(this).Productlist(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.productlistspecial,category.getCakeCategory().get(2).getId(),storeid);
                return;
            }

        }
    }
    private void CallCoolService() {
        if (this.mDetector.isConnectingToInternet()) {
            if(category.getCakeCategory().get(1).getCategoryType().equals("Cool Cake")) {
                new WebServices(this).Productlist(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.productlistcool, category.getCakeCategory().get(1).getId(),storeid);
                return;
            }
        }

    }
    private void CallNormalService() {
        if (this.mDetector.isConnectingToInternet()) {
            if(category.getCakeCategory().get(0).getCategoryType().equals("Normal Cake")) {
                new WebServices(this).Productlist(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.productlistnormal, category.getCakeCategory().get(0).getId(),storeid);
                return;
            }
        }

    }
    private void CallFlowerService() {
        if (this.mDetector.isConnectingToInternet()) {
            if(category.getCakeCategory().get(3).getCategoryType().equals("Flowers")) {
                new WebServices(this).Productlist(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.productlistflower, category.getCakeCategory().get(3).getId(),storeid);
                return;
            }
        }

    }
    private void CallOtherService() {
        if (this.mDetector.isConnectingToInternet()) {
            if(category.getCakeCategory().get(4).getCategoryType().equals("Other")) {
                new WebServices(this).Productlist(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.productlistother, category.getCakeCategory().get(4).getId(),storeid);
                return;
            }
        }

    }

    private void CallRatingService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantRating(WebServices.Foodey_Cakelocation_Services,
                    resRat,storeid);
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.cakecategory) {
            if (!isSucces) {
                SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                category = (Category) response;

                if (!isSucces || category.getCakeCategory() == null) {
                    SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    CallSpecialService();
                    CallCoolService();
                    CallNormalService();
                    CallFlowerService();
                    CallOtherService();
                }
            }
        }if (apiType == WebServices.ApiType.productlistspecial) {
            if (!isSucces) {
                SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeProductlist productlist = (CakeProductlist) response;

                if (!isSucces || productlist.getCake() == null) {
                    SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }else if(!productlist.getCake().get(0).getStoreId().equals(storeid)){
                    ll_special.setVisibility(View.GONE);
                    SnackBar.makeText(CakeShopSubCategoryActivity.this,"No records found", SnackBar.LENGTH_SHORT).show();
                }else {
                    ll_special.setVisibility(View.VISIBLE);
                    LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    specialrecyclerView.setLayoutManager(layoutspecialManager);
                    CakeSpecialRecyclerAdapter specialadapter = new CakeSpecialRecyclerAdapter(this,productlist);
                    specialrecyclerView.setAdapter(specialadapter);
                }
            }
        }if (apiType == WebServices.ApiType.productlistcool) {
            if (!isSucces) {
                SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeProductlist productlist = (CakeProductlist) response;

                if (!isSucces || productlist.getCake() == null) {
                    SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }else if(!productlist.getCake().get(0).getStoreId().equals(storeid)){
                    ll_cool.setVisibility(View.GONE);
                    SnackBar.makeText(CakeShopSubCategoryActivity.this,"No records found", SnackBar.LENGTH_SHORT).show();
                } else {
                    ll_cool.setVisibility(View.VISIBLE);
                    LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    coolcakerecyclerView.setLayoutManager(layoutspecialManager);
                    CakeCoolRecyclerViewAdapter coolRecyclerViewAdapter = new CakeCoolRecyclerViewAdapter(this,productlist);
                    coolcakerecyclerView.setAdapter(coolRecyclerViewAdapter);
                }
            }
        }if (apiType == WebServices.ApiType.productlistnormal) {
            if (!isSucces) {
                SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeProductlist productlist = (CakeProductlist) response;

                if (!isSucces || productlist.getCake() == null) {
                    SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }else if(!productlist.getCake().get(0).getStoreId().equals(storeid)){
                    ll_normal.setVisibility(View.GONE);
                    SnackBar.makeText(CakeShopSubCategoryActivity.this,"No records found", SnackBar.LENGTH_SHORT).show();
                }else {
                    ll_normal.setVisibility(View.VISIBLE);
                    LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    normalcakerecyclerView.setLayoutManager(layoutspecialManager);
                    CakeNormalRecyclerAdapter normalRecyclerAdapter = new CakeNormalRecyclerAdapter(this,productlist);
                    normalcakerecyclerView.setAdapter(normalRecyclerAdapter);
                }
            }
        }if (apiType == WebServices.ApiType.productlistflower) {
            if (!isSucces) {
                SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeProductlist productlist = (CakeProductlist) response;

                if (!isSucces || productlist.getCake() == null) {
                    SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }else if(!productlist.getCake().get(0).getStoreId().equals(storeid)){
                    ll_flower.setVisibility(View.GONE);
                    SnackBar.makeText(CakeShopSubCategoryActivity.this,"No records found", SnackBar.LENGTH_SHORT).show();
                } else {
                    ll_flower.setVisibility(View.VISIBLE);
                    LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    flowercakerecyclerView.setLayoutManager(layoutspecialManager);
                    CakeFlowerRecyclerAdapter flowerRecyclerAdapter = new CakeFlowerRecyclerAdapter(this,productlist);
                    flowercakerecyclerView.setAdapter(flowerRecyclerAdapter);
                }
            }
        }if (apiType == WebServices.ApiType.productlistother) {
            if (!isSucces) {
                SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeProductlist productlist = (CakeProductlist) response;
                if (!isSucces || productlist.getCake() == null) {
                    SnackBar.makeText(CakeShopSubCategoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else if(!productlist.getCake().get(0).getStoreId().equals(storeid)){
                    ll_other.setVisibility(View.GONE);
                    SnackBar.makeText(CakeShopSubCategoryActivity.this,"No records found", SnackBar.LENGTH_SHORT).show();
                }else {
                    ll_other.setVisibility(View.VISIBLE);
                    LinearLayoutManager layoutspecialManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    othercakerecyclerView.setLayoutManager(layoutspecialManager);
                    CakeOtherRecyclerAdapter otherRecyclerAdapter = new CakeOtherRecyclerAdapter(this,productlist);
                    othercakerecyclerView.setAdapter(otherRecyclerAdapter);
                }
            }
        } else if (apiType == resRat) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                StoreRating resturantRating = (StoreRating) response;

                if (!isSucces || resturantRating == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (resturantRating != null) {
                        if (resturantRating.getRaiting() != null) {
                            resRating.setText("* "+resturantRating.getRaiting().get(0).getCRating().getRating());
                            tv_comment.setText(resturantRating.getRaiting().get(0).getCRating().getFeedback());
                        }
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favorite) {
            Intent intent = new Intent(this, CakeFavoriteListActivity.class);
            intent.putExtra("storeid", storeid);
            intent.putExtra("url",url);
            intent.putExtra("lati",lati);
            intent.putExtra("longi",longi);
            intent.putExtra("title",title);
            startActivity(intent);
           // startActivity(new Intent(CakeShopSubCategoryActivity.this, CakeFavoriteListActivity.class));
            finish();
            return true;
        }
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(this,CakeBottomNavigationActivity.class);
                startActivity(intent);
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,CakeBottomNavigationActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }
}

