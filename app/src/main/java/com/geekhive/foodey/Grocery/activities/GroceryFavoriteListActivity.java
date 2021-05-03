package com.geekhive.foodey.Grocery.activities;

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

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList;
import com.geekhive.foodey.Grocery.beans.groceryaddcart.GroceryAddToCart;
import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.Grocery.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public  class GroceryFavoriteListActivity extends AppCompatActivity implements OnResponseListner {

      private RecyclerView rv;
      private FavoriteAdapter adapter;
      Toolbar toolbar;
      String title, product_sub_category;
    String storeid,sub_id;
    String url,lati,longi;
    String item_count;
    GroceryStoreNameList groceryStoreNameList;


    ConnectionDetector mDetector;
    String id,image,name,quantity,price,mrp ;
    String dis;
    String cardDetailId = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_favorite_list);

        toolbar = findViewById(R.id.toolbar);
        sub_id = getIntent().getStringExtra("sub_id");
        title = getIntent().getStringExtra("title");
       // product_sub_category = getIntent().getStringExtra("sub_id");
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        storeid = getIntent().getStringExtra("store_id");

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
           lati = getIntent().getStringExtra("lati");
           longi = getIntent().getStringExtra("longi");

    }

    private void getFavData() {
        if(GroceryShopProductListActivity.favoriteDatabase!= null){
            List<FavoriteList>favoriteLists=GroceryShopProductListActivity.favoriteDatabase.favoriteDao().getFavoriteData();
            adapter=new FavoriteAdapter(favoriteLists,getApplicationContext());
            rv.setAdapter(adapter);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            Intent intent = new Intent(GroceryFavoriteListActivity.this, GroceryCheckOutActivityNew.class);
//            intent.putExtra("lati", lati);
//            intent.putExtra("longi", longi);
            startActivity(intent);
            finish();
            return true;
        }

        switch (id) {

            case android.R.id.home:
                Intent intent = new Intent(this,GroceryShopProductListActivity.class);
                intent.putExtra("store_id", storeid);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                intent.putExtra("sub_id",sub_id);

                startActivity(intent);
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,GroceryShopProductListActivity.class);
        intent.putExtra("store_id", storeid);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("sub_id",sub_id);



        startActivity(intent);
        finish();
        super.onBackPressed();
        finish();
    }

    private void CallAdd(String lat, String lang) {

        String str_origin = Prefs.getUserLat(this) + "," + Prefs.getUserLang(this);
        // Destination of route
        String str_dest = lat + "," + lang;

        String key = getResources().getString(R.string.google_map_api);
        CallMapService(str_origin, str_dest, key);


    }
    private void CallMapService(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistance, origin, destination, key);
            //CallService();
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }
    private void CallService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).AddToCart(WebServices.Foodey_Grocery_Services,
                        WebServices.ApiType.addtocart,Prefs.getUserId(this), storeid, id, item_count, price, mrp, distance, Prefs.getCityId(this));

        } else {
            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }
//    private void CallRemoveItemService(String distance) {
//        if (this.mDetector.isConnectingToInternet()) {
//
//            new WebServices(this).RemoveCartItem(WebServices.Foodey_Grocery_Services,
//                    WebServices.ApiType.removeCartItem, cardDetailId, cart_id, distance);
//        } else {
//
//            SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
//        }
//
//        return;
//    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.addtocart) {
            if (!isSucces) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final GroceryAddToCart addToCart = (GroceryAddToCart) response;
                if (!isSucces || addToCart == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(this, "Item added in cart", SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        dis = distance.replace(" km", "");
                        CallService(dis);
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }

    }


    public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
        private List<FavoriteList>favoriteLists;
        Context context;

        public FavoriteAdapter(List<FavoriteList> favoriteLists, Context context) {
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


            FavoriteList fl=favoriteLists.get(i);
            String url = WebServices.Foodey_Grocery_Image + fl.getImage();
            if (!url.equals("")){
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(viewHolder.img);
            }
            viewHolder.tv.setText(fl.getName());
            viewHolder.ftv_quantiy.setText(fl.getQuantity());
            viewHolder.ftv_price.setText(fl.getPrice());
            Typeface CustomCut = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-SemiBold.ttf");
            viewHolder.ftv_mrp.setText("MRP : Rs."+fl.getMrp());
            viewHolder.ftv_mrp.setTypeface(CustomCut);
            viewHolder.ftv_mrp.setPaintFlags(viewHolder.ftv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            if (GroceryShopProductListActivity.favoriteDatabase.favoriteDao().isFavorite(favoriteLists.get(i).getId()) == 1) {
                viewHolder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
            }else {
                viewHolder.iv_fav_btn.setImageResource(R.drawable.heart);
            }
            viewHolder.fav_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FavoriteList favoriteList = new FavoriteList();

                     id = favoriteLists.get(i).getId();
                     image = favoriteLists.get(i).getImage();
                     name = favoriteLists.get(i).getName();
                     quantity = favoriteLists.get(i).getQuantity();
                     price = favoriteLists.get(i).getPrice();
                     mrp = favoriteLists.get(i).getMrp();

                    favoriteList.setId(id);
                    favoriteList.setImage(image);
                    favoriteList.setName("Name :"+name);
                    favoriteList.setQuantity("Quantity :"+quantity);
                    favoriteList.setPrice(price);
                    favoriteList.setMrp(mrp);
                    if (GroceryShopProductListActivity.favoriteDatabase.favoriteDao().isFavorite(id) != 1) {
                        viewHolder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
                        GroceryShopProductListActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
                        SnackBar.makeText(context, "Item added into favorite list", SnackBar.LENGTH_SHORT).show();

                    } else {
                        viewHolder.iv_fav_btn.setImageResource(R.drawable.heart);
                        GroceryShopProductListActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
                        Intent intentg = new Intent(context, GroceryFavoriteListActivity.class);
                        intentg.putExtra("store_id", storeid);
                        intentg.putExtra("url",url);

                        intentg.putExtra("sub_id",sub_id);

                        String title_ = title;
                        String lat_=lati;
                        String long_= longi;
                        intentg.putExtra("title",title_);
                        intentg.putExtra("lati",lat_);
                        intentg.putExtra("longi",long_);
                        intentg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         startActivity(intentg);
                        //SnackBar.makeText(context, "Item removed from favorite list", SnackBar.LENGTH_SHORT).show();
                    }
                }
            });
//            viewHolder.vT_add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CallAdd(lati,longi);
//
//                }
//            });

            viewHolder.qtyM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (Integer.parseInt(viewHolder.qty.getText().toString()) != 1) {
                        int qaunt = Integer.parseInt(viewHolder.qty.getText().toString()) - 1;
                        viewHolder.qty.setText(String.valueOf(qaunt));
                        // holder.vL_add_to_cart.setVisibility(View.VISIBLE);

                    } else {
                        viewHolder.vL_add_to_cart.setVisibility(View.VISIBLE);
                        int qaunt = Integer.parseInt(viewHolder.qty.getText().toString()) - 1;
                        viewHolder.qty.setText(String.valueOf(qaunt));
                        viewHolder.vL_count.setVisibility(View.GONE);


                    }
                }

                    });


            viewHolder.vT_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.vL_count.setVisibility(View.VISIBLE);
                    viewHolder.vL_add_to_cart.setVisibility(View.GONE);
                    int qaunt = Integer.parseInt(viewHolder.qty.getText().toString()) + 1;
                    viewHolder.qty.setText(String.valueOf(qaunt));
                    item_count=viewHolder.qty.getText().toString();
//                    lati=groceryStoreNameList.getStore().get(0).getStore().getLatitude();
//                    longi=groceryStoreNameList.getStore().get(0).getStore().getLongitude();
                    id = favoriteLists.get(i).getId();
                    image = favoriteLists.get(i).getImage();
                    name = favoriteLists.get(i).getName();
                    quantity = favoriteLists.get(i).getQuantity();
                    price = favoriteLists.get(i).getPrice();
                    mrp = favoriteLists.get(i).getMrp();


                    if (Integer.parseInt(viewHolder.qty.getText().toString()) != 0) {
                        CallAdd(lati,longi);

                    } else {
                        SnackBar.makeText(getApplicationContext(), "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                    }
                    viewHolder.qtyA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int qaunt = Integer.parseInt(viewHolder.qty.getText().toString()) + 1;
                            viewHolder.qty.setText(String.valueOf(qaunt));
                            CallAdd(lati,longi);

                        }
                    });


                }
            });
        }

        @Override
        public int getItemCount() {
            return favoriteLists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView img,iv_fav_btn,qtyM, qtyA;
            TextView tv,ftv_quantiy,ftv_price,ftv_mrp,vT_add;
            LinearLayout fav_btn;
            LinearLayout  vL_count, vL_add_to_cart;
            TextView qty;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.fimg_pr);
                tv= itemView.findViewById(R.id.ftv_name);
                iv_fav_btn= itemView.findViewById(R.id.iv_fav_btn);

                ftv_quantiy =itemView.findViewById(R.id.ftv_quantiy);
                ftv_price = itemView.findViewById(R.id.ftv_price);
                ftv_mrp = itemView.findViewById(R.id.ftv_mrp);
                fav_btn = (LinearLayout) itemView.findViewById(R.id.fav_btn);
                vT_add = itemView.findViewById(R.id.vT_add);
                this.qty = itemView.findViewById(R.id.qty);
                this.qtyA = itemView.findViewById(R.id.qtyA);
                this.qtyM = itemView.findViewById(R.id.qtyM);
                this.vL_count = (LinearLayout) itemView.findViewById(R.id.vL_count);
                this.vL_add_to_cart = (LinearLayout) itemView.findViewById(R.id.vL_add_to_cart);


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
