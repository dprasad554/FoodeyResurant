package com.geekhive.foodey.Cakes.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.beans.cakeaddtocart.CakeAddtocart;
import com.geekhive.foodey.Cakes.beans.cakefavoritelist.CakeFavoriteList;
import com.geekhive.foodey.Cakes.beans.cakemapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CakeFavoriteListActivity extends AppCompatActivity implements OnResponseListner {
      private RecyclerView rv;
      private CakeFavoriteAdapter adapter;
      Toolbar toolbar;
      String title, product_sub_category;
      String st_id;
      String url,lati,longi;
      ConnectionDetector mDetector;
      String dis;

    String id,image,name,quantity,price,mrp,storeid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_favorite_list);

        toolbar = findViewById(R.id.toolbar);
        product_sub_category = getIntent().getStringExtra("sub_id");
        st_id = getIntent().getStringExtra("storeid");
        url = getIntent().getStringExtra("url");
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");
        title = getIntent().getStringExtra("title");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Favorite List");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rv=(RecyclerView)findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        setValues();
        getFavData();

    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void getFavData() {
        if(CakeShopSubCategoryActivity.favoriteDatabase != null){
            List<CakeFavoriteList>favoriteLists=CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().getFavoriteData();

            adapter=new CakeFavoriteAdapter(favoriteLists,getApplicationContext());
            rv.setAdapter(adapter);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Intent intent = new Intent(CakeFavoriteListActivity.this, CakeCheckOutActivityNew.class);
            intent.putExtra("lati", lati);
            intent.putExtra("longi", longi);
            startActivity(intent);
            return true;
        }
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(this,CakeShopSubCategoryActivity.class);
                intent.putExtra("storeid", st_id);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,CakeShopSubCategoryActivity.class);
        intent.putExtra("storeid", st_id);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        startActivity(intent);
        finish();
        super.onBackPressed();
        finish();
    }

    public class CakeFavoriteAdapter extends RecyclerView.Adapter<CakeFavoriteAdapter.ViewHolder> {
        private List<CakeFavoriteList>favoriteLists;
        Context context;

        public CakeFavoriteAdapter(List<CakeFavoriteList> favoriteLists, Context context) {
            this.favoriteLists = favoriteLists;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_list,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            CakeFavoriteList fl=favoriteLists.get(i);
            String url1 = "http://bado.dsoftveda.com/img/cake/"+fl.getImage();
            if (!url1.equals("")){
                Picasso.get()
                        .load(url1)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(viewHolder.img);
            }
            viewHolder.tv.setText(fl.getName());
            viewHolder.ftv_quantiy.setText(fl.getQuantity());
            viewHolder.ftv_price.setText("Rs . "+fl.getPrice());
            Typeface CustomCut = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-SemiBold.ttf");
            viewHolder.ftv_mrp.setText("MRP : Rs."+fl.getMrp());
            viewHolder.ftv_mrp.setTypeface(CustomCut);
            viewHolder.ftv_mrp.setPaintFlags(viewHolder.ftv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            id = fl.getId();
            image = fl.getImage();
            name = fl.getName();
            quantity = fl.getQuantity();
            price = fl.getPrice();
            mrp = fl.getMrp();
            storeid = fl.getStoreid();

            if (CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().isFavorite(favoriteLists.get(i).getId()) == 1) {
                viewHolder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
            }else {
                viewHolder.iv_fav_btn.setImageResource(R.drawable.heart);
            }
            viewHolder.fav_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CakeFavoriteList favoriteList = new CakeFavoriteList();

                     id = favoriteLists.get(i).getId();
                     image = favoriteLists.get(i).getImage();
                     name = favoriteLists.get(i).getName();
                     quantity = favoriteLists.get(i).getQuantity();
                     price = favoriteLists.get(i).getPrice();
                     mrp = favoriteLists.get(i).getMrp();
                     storeid = favoriteLists.get(i).getStoreid();


                    favoriteList.setId(id);
                    favoriteList.setImage(image);
                    favoriteList.setName("Name :"+name);
                    favoriteList.setQuantity("Quantity :"+quantity);
                    favoriteList.setPrice(price);
                    favoriteList.setMrp(mrp);
                    favoriteList.setStoreid(storeid);
                    if (CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().isFavorite(id) != 1) {
                        viewHolder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
                        CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
                        SnackBar.makeText(context, "Item added into favorite list", SnackBar.LENGTH_SHORT).show();

                    } else {
                        viewHolder.iv_fav_btn.setImageResource(R.drawable.heart);
                        CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
                        adapter.notifyDataSetChanged();
                        Intent intent = new Intent(context, CakeFavoriteListActivity.class);
                        intent.putExtra("storeid", st_id);
                        intent.putExtra("url",url);
                        String title_ = title;
                        String lat_= lati;
                        String long_= longi;
                        intent.putExtra("title",title_);
                        intent.putExtra("lati",lat_);
                        intent.putExtra("longi",long_);
                        startActivity(intent);
                        //SnackBar.makeText(context, "Item removed from favorite list", SnackBar.LENGTH_SHORT).show();
                    }
                }
            });

            viewHolder.vT_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallAdd(lati,longi);
                }
            });
        }
        @Override
        public int getItemCount() {
            return favoriteLists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView img,iv_fav_btn;
            TextView tv,ftv_quantiy,ftv_price,ftv_mrp;
            LinearLayout fav_btn;
            TextView vT_add;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img=(ImageView)itemView.findViewById(R.id.fimg_pr);
                tv=(TextView)itemView.findViewById(R.id.ftv_name);
                ftv_quantiy = (TextView)itemView.findViewById(R.id.ftv_quantiy);
                ftv_price = (TextView)itemView.findViewById(R.id.ftv_price);
                ftv_mrp = (TextView)itemView.findViewById(R.id.ftv_mrp);
                fav_btn = (LinearLayout) itemView.findViewById(R.id.fav_btn);
                iv_fav_btn = (ImageView)itemView.findViewById(R.id.iv_fav_btn);
                vT_add = (TextView)itemView.findViewById(R.id.vT_add);
            }
        }
    }

    private void CallAdd(String lat, String lang) {

        String str_origin = Prefs.getUserLat(CakeFavoriteListActivity.this) + "," + Prefs.getUserLang(CakeFavoriteListActivity.this);
        // Destination of route
        String str_dest = lat + "," + lang;
        String key = getResources().getString(R.string.google_map_api);
        CallMapService(str_origin, str_dest, key);
    }
    private void CallMapService(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistance, origin, destination, key);
        } else {
            SnackBar.makeText(CakeFavoriteListActivity.this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }
    private void CallService(String distanceAdd) {
        if (this.mDetector.isConnectingToInternet()) {
            boolean fileImage = false;
            String message_ = "";
        /*    if (!filepathcakepic.equals("")){
                fileImage = true;
            } else {
                fileImage = false;
            }*/
            String filepathcakepic = "";
            String selectedDateStr = "";
            String selected_datetime = "";
            new WebServices(this).Addtocart(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.addtocart,Prefs.getUserId(this),storeid,id,quantity,price,mrp,message_,selectedDateStr,selected_datetime,filepathcakepic,fileImage,distanceAdd, Prefs.getCityId(this));
            return;
        }
    }
    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(CakeFavoriteListActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null||getDistanceFromMap.getStatus() != "ZERO RESULTS") {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        dis = distance.replace(" km", "");
                        CallService(dis);
                    }

                } else {
                    SnackBar.makeText(CakeFavoriteListActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } if (apiType == WebServices.ApiType.addtocart) {
            if (!isSucces) {
                SnackBar.makeText(CakeFavoriteListActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeAddtocart addtocart = (CakeAddtocart) response;
                if (!isSucces || addtocart == null) {
                    SnackBar.makeText(CakeFavoriteListActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(CakeFavoriteListActivity.this, addtocart.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }
}
