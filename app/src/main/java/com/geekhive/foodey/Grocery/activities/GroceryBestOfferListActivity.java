package com.geekhive.foodey.Grocery.activities;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.beans.groceryaddcart.GroceryAddToCart;
import com.geekhive.foodey.Grocery.beans.groceryoffer.GroceryOffer;
import com.geekhive.foodey.Grocery.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class GroceryBestOfferListActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    RecyclerView bestRecyclerView;
    ArrayList groceryImages = new ArrayList<>(Arrays.asList(R.drawable.foodgrainsmasalaoil, R.drawable.foodgrainsmasalaoil, R.drawable.foodgrainsmasalaoil));
    ArrayList groceryName = new ArrayList<>(Arrays.asList("Horlicks", "Ashirwaad Aatta","Pulses"));
    ConnectionDetector mDetector;
    GroceryOffer groceryOffer;
    String store_id,product_id,item_count,price,mrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_best_offer_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Best Offer");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setValues();
        CallOfferService();
        //For Recent Purchase
        bestRecyclerView = (RecyclerView)findViewById(R.id.bestofferlist_recyclerview);

    }
    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallOfferService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).OfferService(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.offerservice,Prefs.getUserId(this));
            return;
        }
    }
    private void CallAddtocartService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).AddToCart(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.addtocart, Prefs.getUserId(this), store_id, product_id, item_count, price, mrp, distance, Prefs.getCityId(this));

        } else {
            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
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
            //CallAddtocartService();
            //CallService();
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
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
                }
            }
        }if (apiType == WebServices.ApiType.offerservice) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                groceryOffer = (GroceryOffer) response;
                if (groceryOffer.getOfferDetails()!= null) {
                    if (groceryOffer.getOfferDetails().size() != 0) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);
                        bestRecyclerView.setLayoutManager(gridLayoutManager);
                        GroceryBestOfferViewallAdapter bestOfferViewallAdapter = new GroceryBestOfferViewallAdapter(this,groceryOffer);
                        bestRecyclerView.setAdapter(bestOfferViewallAdapter);
                    }
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
                        String dis = distance.replace(" km", "");
                        CallAddtocartService(dis);
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class GroceryBestOfferViewallAdapter extends RecyclerView.Adapter<GroceryBestOfferViewallAdapter.ViewHolder> {

        //Variables
        private Context mcontext;
        GroceryOffer groceryOffer;

        public GroceryBestOfferViewallAdapter(Context context,GroceryOffer groceryOffer) {
            this.mcontext = context;
            this.groceryOffer = groceryOffer;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_bestoffer_viewall,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.groceryTitle.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getName());
            //holder.groceryOriginal.setText("MRP : "+groceryOffer.getOfferDetails().get(position).getGroceryOffer().getDiscount());
            holder.tv_productQty.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getDetail());
//                    groceryOffer.getOfferDetails().get(position).getGrocery().getQuantityDetail());
//            holder.productNows.setText("Rs : "+groceryOffer.getOfferDetails().get(position).getGrocery().getPrice());
            holder.tv_productCuts.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getDiscount() + " OFF");
            String url =WebServices.Foodey_Offer_Url+
                    groceryOffer.getOfferDetails().get(position).getGroceryOffer().getImage();
            if (!url.equals("")){
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.vegImage);
            }
            holder.btn_addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!groceryOffer.getOfferDetails().get(position).getGroceryOffer().getId().equals(null)){
                        CallAdd(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getLatitude(),
                                groceryOffer.getOfferDetails().get(position).getGroceryOffer().getLongitude());

                    }
                }
            });
//            store_id = groceryOffer.getGroceryOffer().get(position).getGrocery().getStoreId().toString();
//            product_id = groceryOffer.getGroceryOffer().get(position).getGrocery().getId();
//            item_count = "1";
//            price = groceryOffer.getGroceryOffer().get(position).getGrocery().getPrice();
//            mrp = groceryOffer.getGroceryOffer().get(position).getGrocery().getMrp();
        }

        @Override
        public int getItemCount() {
            return groceryOffer.getOfferDetails().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView vegImage;
            TextView groceryTitle,groceryOriginal,tv_productQty,productNows,tv_productCuts;
            CardView cardView;
            Button btn_addCart;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                vegImage = (ImageView)itemView.findViewById(R.id.iv_bestImage);
                groceryTitle = itemView.findViewById(R.id.tv_productTitle);
                groceryOriginal = itemView.findViewById(R.id.tv_productOriginals);
                groceryOriginal.setText("Rs 200");
                groceryOriginal.setPaintFlags(groceryOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                cardView = (CardView)itemView.findViewById(R.id.card_viewMain);
                tv_productQty=itemView.findViewById(R.id.tv_productQty);
                productNows=itemView.findViewById(R.id.productNows);
                tv_productCuts = itemView.findViewById(R.id.tv_productCuts);
                btn_addCart = itemView.findViewById(R.id.btn_addCart);

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
