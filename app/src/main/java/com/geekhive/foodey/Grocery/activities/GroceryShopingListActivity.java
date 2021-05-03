package com.geekhive.foodey.Grocery.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.beans.groceryaddcart.GroceryAddToCart;
import com.geekhive.foodey.Grocery.beans.grocerycartlist.CartList;
import com.geekhive.foodey.Grocery.beans.grocerycartlist.GCartDetail;
import com.geekhive.foodey.Grocery.beans.groceryremovecart.GroceryRemoveCartItem;
import com.geekhive.foodey.Grocery.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Grocery.beans.shoppinglist.ShoppingList;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GroceryShopingListActivity extends AppCompatActivity implements OnResponseListner {
    Toolbar toolbar;
    private RecyclerView productRecyclerView;
    String title, product_sub_category;
    ConnectionDetector mDetector;
    String id, image, name, quantity, price, mrp;
    String dis;
    String cardDetailId = "";
    String cart_id = "";
    String storeid, sub_id;
    String url, lati, longi;
    String item_count;
    CartList cartList;
    Button btn_add;
    ShoppingList shoppingList = new ShoppingList();


    List<GCartDetail> cartDetail;


    private GroceryShppingListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_shopiinglist);

        toolbar = findViewById(R.id.toolbar);
        btn_add = findViewById(R.id.btn_add);

        product_sub_category = getIntent().getStringExtra("sub_id");
        sub_id = getIntent().getStringExtra("sub_id");
        title = getIntent().getStringExtra("title");
        // product_sub_category = getIntent().getStringExtra("sub_id");
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        storeid = getIntent().getStringExtra("store_id");
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shopping List");

        //  productRecyclerView.setHasFixedSize(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //For Pack Size
        productRecyclerView = (RecyclerView) findViewById(R.id.Shoppinglist_RecyclerView);
        productRecyclerView.setHasFixedSize(true);

        //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        GroceryShppingListAdapter listAdapter = new GroceryShppingListAdapter(this);
//        productRecyclerView.setAdapter(listAdapter);
        setValues();

        getShoppinListgData();


    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
//        cart_id = cartList.getCartList().getGCartDetail().get(0).getCartId();
//        cardDetailId = cartList.getCartList().getGCartDetail().get(0).getId();

        //CallService();
    }

    //    private void CallService() {
//        if (this.mDetector.isConnectingToInternet()) {
//            new WebServices(this).SubProductList(WebServices.Foodey_Grocery_Services,
//                    WebServices.ApiType.productlist, product_sub_category);
//        } else {
//            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
//        }
//        return;
//    }
    private void getShoppinListgData() {
        if (GroceryShopProductListActivity.shoppingListDatabase != null) {
            List<ShoppingList> shoppinglists = GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().getShoppingData();
            listAdapter = new GroceryShppingListAdapter(shoppinglists, getApplicationContext());
            productRecyclerView.setAdapter(listAdapter);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        Intent intent = new Intent(GroceryShopingListActivity.this, GroceryBottomNavigationActivity.class);
//        startActivity(intent);

        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            Intent intent = new Intent(this, GroceryCheckOutActivityNew.class);
//            intent.putExtra("lati", lati);
//            intent.putExtra("longi", longi);
            startActivity(intent);
            finish();
            return true;
        }
        switch (id) {

            case android.R.id.home:
                Intent intent = new Intent(this, GroceryShopProductListActivity.class);
                intent.putExtra("store_id", storeid);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                intent.putExtra("sub_id", sub_id);
                intent.putExtra("lati", lati);
                intent.putExtra("longi", longi);

                startActivity(intent);
                finish();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GroceryShopProductListActivity.class);
        intent.putExtra("store_id", storeid);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("sub_id", sub_id);
        intent.putExtra("lati", lati);
        intent.putExtra("longi", longi);
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
                    WebServices.ApiType.addtocart, Prefs.getUserId(this), storeid, id, item_count, price, mrp, distance, Prefs.getCityId(this));

        } else {
            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    private void CallAddRItem() {

        String str_origin = Prefs.getUserLat(this) + "," + Prefs.getUserLang(this);
        // Destination of route
        String str_dest = lati + "," + longi;

        String key = getResources().getString(R.string.google_map_api);
        CallMapServiceRItem(str_origin, str_dest, key);


    }

    private void CallMapServiceRItem(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistanceRes, origin, destination, key);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    private void CallRemoveItemService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).RemoveCartItem(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.removeCartItem, cardDetailId, cart_id, distance);
        } else {

            SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }


    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.addtocart) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final GroceryAddToCart addToCart = (GroceryAddToCart) response;
                if (!isSuccess || addToCart == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(this, "Item added in cart", SnackBar.LENGTH_SHORT).show();
                   /* GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().delete(shoppingList);
                    Intent intentg = new Intent(GroceryShopingListActivity.this, GroceryShopingListActivity.class);
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
                    startActivity(intentg);*/
                }
            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSuccess) {
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
        } else if (apiType == WebServices.ApiType.mapdistanceRes) {
            if (!isSuccess) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        String dis = distance.replace(" km", "");
                        CallRemoveItemService(dis);
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
        if (apiType == WebServices.ApiType.removeCartItem) {
            if (!isSuccess) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GroceryRemoveCartItem removeCartItem = (GroceryRemoveCartItem) response;

                SnackBar.makeText(this, removeCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                if (!isSuccess || removeCartItem == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();

                } else {
                    SnackBar.makeText(this, removeCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
                //startActivity(new Intent(this, GroceryCheckOutActivityFinal.class));


            }
        }


    }

    public class GroceryShppingListAdapter extends RecyclerView.Adapter<GroceryShppingListAdapter.ViewHolder> {

        //Variables
        //GrocerySubProductList subProductList;
        private List<ShoppingList> shoppinglists;
        private Context context;


        String quantity = "";

        public GroceryShppingListAdapter(List<ShoppingList> shoppinglists, Context context) {
            this.shoppinglists = shoppinglists;
            this.context = context;


            //this.subProductList = subProductList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_shoppinglist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
            ShoppingList s1 = shoppinglists.get(i);
            String url = WebServices.Foodey_Grocery_Image  + s1.getImage();
            if (!url.equals("")) {
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.productImage);
            }
            holder.productName.setText(s1.getName());
            holder.originalPrice.setText("₹ " + s1.getPrice());
            id = s1.getId();
            price = s1.getPrice();
            mrp = s1.getMrp();


            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox cb = (CheckBox) view;
                    //ShoppingList sl = cb.getTag();
                    if (holder.checkBox.isChecked()) {
                        item_count = String.valueOf("1");
                        btn_add.setVisibility(View.VISIBLE);
                        Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                       // GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().delete(shoppingList);


                    } else {
                        ShoppingList shoppingList = new ShoppingList();
                        item_count = String.valueOf("0");
                        btn_add.setVisibility(View.GONE);
                        GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().delete(shoppingList);
                        System.out.println( "Shopping Date:::::"+GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().getShoppingData().get(0).getName());
                       /* Intent intentg = new Intent(GroceryShopingListActivity.this, GroceryShopingListActivity.class);
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
                        startActivity(intentg);*/


                        Toast.makeText(context, "Unselected", Toast.LENGTH_SHORT).show();
                    }

                }
            });
//            holder.vT_add_to_shoping.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ShoppingList shoppingList = new ShoppingList();
//
//                    String id = shoppinglists.get(0).getId();
//                    String image = shoppinglists.get(0).getImage();
//                    String name =shoppinglists.get(0).getName();
//                    String quantity = shoppinglists.get(0).getQuantity();
//                    String quantity_details =shoppinglists.get(0).getQuantity();
//                    String price = shoppinglists.get(0).getPrice();
//                    String mrp = shoppinglists.get(0).getMrp();
//
//                    shoppingList.setId(id);
//                    shoppingList.setImage(image);
//                    shoppingList.setName("Name :"+name);
//                    shoppingList.setQuantity("Quantity :"+quantity+quantity_details);
//                    shoppingList.setPrice(price);
//                    shoppingList.setMrp(mrp);
//
//
//                    if (GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().isShoppingList(id) != 1) {
//                        // holder.fav_btn.setImageResource(R.drawable.ic_favorite);
//                        GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().addData(shoppingList);
//                        System.out.println( "Item added into shopping list"+GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().getShoppingData().get(0).getName());
//
//                        // SnackBar.makeText(context, "Item added into shopping list", SnackBar.LENGTH_SHORT).show();
//
//                    } else {
//                        //holder.fav_btn.setImageResource(R.drawable.heart);
//                        GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().delete(shoppingList);
//                       // System.out.println( "Item removed from shopping list"+GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().getShoppingData().get(0).getName());
//
//                        // SnackBar.makeText(context, "Item removed from shopping list", SnackBar.LENGTH_SHORT).show();
//                    }
//                }
//            });

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallAdd(lati, longi);

                }
            });

        }


        @Override
        public int getItemCount() {
            return shoppinglists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView productName, productQty, originalPrice, savePrice, vT_add, qty;
            ImageView productImage, qtyA;
            CardView vC_adhg_card;
            LinearLayout vL_count;
            CheckBox checkBox;
            TextView vT_add_to_shoping;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                productName = itemView.findViewById(R.id.vT_adeot_name);
                productQty = itemView.findViewById(R.id.tv_productQTY);
                originalPrice = itemView.findViewById(R.id.vT_adeot_price);
                savePrice = itemView.findViewById(R.id.tv_productPriceSave);
                productImage = itemView.findViewById(R.id.vI_adeot_image);
                vC_adhg_card = itemView.findViewById(R.id.vC_adhg_card);
                vT_add = itemView.findViewById(R.id.vT_add);
                vL_count = itemView.findViewById(R.id.vL_count);
                qty = itemView.findViewById(R.id.qty);
                qtyA = itemView.findViewById(R.id.qtyA);
                checkBox = itemView.findViewById(R.id.checkBox);
                vT_add_to_shoping = itemView.findViewById(R.id.vT_add_to_shoping);


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
