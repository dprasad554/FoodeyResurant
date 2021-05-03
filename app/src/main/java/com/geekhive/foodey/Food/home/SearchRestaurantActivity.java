package com.geekhive.foodey.Food.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.beans.addtocart.AddToCart;
import com.geekhive.foodey.Food.beans.restitmsearch.FoodList;
import com.geekhive.foodey.Food.beans.restitmsearch.RestaurantItemSearch;
import com.geekhive.foodey.Food.orderfood.CheckOutActivityNew;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchRestaurantActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    @BindView(R.id.vI_tl_back)
    ImageView vITlBack;
    @BindView(R.id.vT_tl_header)
    TextView vTTlHeader;
    @BindView(R.id.vT_tl_location)
    TextView vTTlLocation;
    /*@BindView(R.id.vL_tl_location)
    LinearLayout vLTlLocation;*/
    @BindView(R.id.vI_tl_notification)
    ImageView vITlNotification;
    @BindView(R.id.vL_tl_count)
    TextView vLTlCount;
    @BindView(R.id.vL_tl_notification)
    LinearLayout vLTlNotification;
    @BindView(R.id.vR_tl_notification)
    RelativeLayout vRTlNotification;
    @BindView(R.id.vI_tl_cart)
    ImageView vITlSearch;
    @BindView(R.id.vR_tl_search)
    RelativeLayout vRTlSearch;
    @BindView(R.id.vL_tl_skip)
    TextView vLTlSkip;
    @BindView(R.id.vT_asr_current)
    TextView vTAsrCurrent;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.vR_asr_list)
    RecyclerView vRAsrList;

    String searchName = "";
    List<FoodList> foodList = new ArrayList<>();

    MenuAdapter menuAdapter;
    ConnectionDetector mDetector;
    String price_;
    String mrp;
    String foodId;
    String res_id = "";
    Dialog paypopup;
    Integer total_price;
    String restName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);
        ButterKnife.bind(this);

        initializeFonts();
        setTypeFace();
        setvalues();
    }
    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getAssets(), "NIRMALAS.TTF");
    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                vTTlHeader.setTypeface(NIRMALAB);
                vTTlLocation.setTypeface(NIRMALA);
                vLTlCount.setTypeface(NIRMALAB);
                vTAsrCurrent.setTypeface(NIRMALAB);



            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        res_id = getIntent().getStringExtra("resId");
        restName = getIntent().getStringExtra("restName");
        vITlBack.setVisibility(View.VISIBLE);
        vTTlHeader.setVisibility(View.GONE);
        vRTlNotification.setVisibility(View.GONE);
        vITlSearch.setVisibility(View.VISIBLE);
        vTTlHeader.setText(getResources().getString(R.string.booking_details));
        vITlBack.setOnClickListener(this);
        vTTlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location, 0, 0, 0);
        vTTlLocation.setText(restName);
        searchView.setQueryHint("Search");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setQuery("", true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CallService(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void CallService(String searchName) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantSearchService(WebServices.Foodey_Services,
                    WebServices.ApiType.restsrch, Prefs.getUserId(this),  searchName, res_id);
        }

    }

    //TODO: Add distance code here(Google dist code)
    private void CallAddService(String quantity, String resId, String price, String mrp, String foodId, String lat, String lang) {
        if (this.mDetector.isConnectingToInternet()) {

            Double dstn = distance(Double.parseDouble(Prefs.getUserLat(this)), Double.parseDouble(Prefs.getUserLat(this)),
                    Double.parseDouble(lat), Double.parseDouble(lang));
            String distanceAdd = String.valueOf(dstn);
            new WebServices(this).AddToCart(WebServices.Foodey_Services,
                    WebServices.ApiType.addToCart, Prefs.getUserId(this), resId, foodId, quantity, price, mrp, distanceAdd, Prefs.getCityId(this));

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.restsrch) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                RestaurantItemSearch restaurantItemSearch  = (RestaurantItemSearch) response;
                if (!isSucces || restaurantItemSearch == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (restaurantItemSearch.getFoodList() != null) {
                            if (restaurantItemSearch.getFoodList().size() != 0) {
                                foodList = new ArrayList<>();
                                foodList.addAll(restaurantItemSearch.getFoodList());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchRestaurantActivity.this);
                                vRAsrList.setLayoutManager(linearLayoutManager);
                                menuAdapter = new MenuAdapter();
                                vRAsrList.setAdapter(menuAdapter);
                            }
                    } else {
                        SnackBar.makeText(SearchRestaurantActivity.this, "Something went wrong", SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (apiType == WebServices.ApiType.addToCart) {
            if (!isSucces) {
                SnackBar.makeText(SearchRestaurantActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                AddToCart cartListOut = (AddToCart) response;
                if (cartListOut != null) {
                    SnackBar.makeText(SearchRestaurantActivity.this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                    //menuAdapter.notifyDataSetChanged();
                } else {
                    SnackBar.makeText(SearchRestaurantActivity.this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_tl_back:
                finish();
                break;
            case R.id.vI_tl_cart:
                Intent intents = new Intent(this, CheckOutActivityNew.class);
                startActivity(intents);
                overridePendingTransition(0, 0);
                break;
        }
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

        public MenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_details_copy, parent, false));
        }

        public void onBindViewHolder(final MenuAdapter.MyViewHolder holder, final int position) {

            if (foodList.get(position).getStatus().equals("1")) {
                holder.qtyM.setVisibility(View.VISIBLE);
                holder.qtyA.setVisibility(View.VISIBLE);
                holder.buttonAdd.setVisibility(View.GONE);
                holder.qty.setVisibility(View.VISIBLE);
                holder.visibleMenu.setVisibility(View.GONE);
                holder.vT_admd_text.setText(foodList.get(position).getName());
                mrp = foodList.get(position).getMrp();
                price_ = foodList.get(position).getPrice();

                Log.d("hhhjjj", price_);
                String mrp = foodList.get(position).getMrp();

                //  holder.vT_mrp.setText("MRP :"+mrp);
                String price = foodList.get(position).getPrice();
                holder.vT_price.setText(price);
                if (mrp.equals(price)) {
                    holder.vT_mrp.setVisibility(View.GONE);

                } else {
                    holder.vT_mrp.setText(mrp);
                    holder.vT_mrp.setPaintFlags(holder.vT_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }

                holder.qtyM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(holder.qty.getText().toString()) != 1) {
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            // holder.vL_add_to_cart.setVisibility(View.VISIBLE);

                        } else {
                            holder.vL_add_to_cart.setVisibility(View.VISIBLE);
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            holder.vL_count.setVisibility(View.GONE);


                        }
                    }
                });


                holder.vT_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        holder.vL_count.setVisibility(View.VISIBLE);
                        holder.vL_add_to_cart.setVisibility(View.GONE);
                        int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                        holder.qty.setText(String.valueOf(qaunt));

                        if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                            foodId = foodList.get(position).getId();
                            CallAddService(holder.qty.getText().toString(), foodList.get(position).getResId(), foodList.get(position).getPrice(),
                                    foodList.get(position).getMrp(), foodList.get(position).getId(), foodList.get(position).getLatitude(), foodList.get(position).getLongitude());
                        } else {
                            SnackBar.makeText(SearchRestaurantActivity.this, "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                        }

                        holder.qtyA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                                holder.qty.setText(String.valueOf(qaunt));
                                foodId = foodList.get(position).getId();
                                CallAddService(holder.qty.getText().toString(), foodList.get(position).getResId(), foodList.get(position).getPrice(),
                                        foodList.get(position).getMrp(), foodList.get(position).getId(), foodList.get(position).getLatitude(), foodList.get(position).getLongitude());


                            }
                        });

                    }


                });


                holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                            foodId = foodList.get(position).getId();
                            CallAddService(holder.qty.getText().toString(), foodList.get(position).getResId(), foodList.get(position).getPrice(),
                                    foodList.get(position).getMrp(), foodList.get(position).getId(), foodList.get(position).getLatitude(), foodList.get(position).getLongitude());
                            //InitializePaypopup();
                            // initializePopup();
                        } else {
                            SnackBar.makeText(SearchRestaurantActivity.this, "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                        }
                    }

                });

            } else {
                holder.vT_admd_text.setText(foodList.get(position).getName());
                mrp = foodList.get(position).getMrp();
                price_ = foodList.get(position).getPrice();
                String mrp = "₹ " + foodList.get(position).getMrp();
                String price = "₹ " + foodList.get(position).getPrice();
                String price_t = foodList.get(position).getPrice();

                // holder.vT_mrp.setText(mrp);
                holder.vT_price.setText(price_t);
                holder.qty.setVisibility(View.GONE);
                holder.qtyM.setVisibility(View.GONE);
                holder.qtyA.setVisibility(View.GONE);
                holder.buttonAdd.setVisibility(View.GONE);
                holder.vL_add_to_cart.setVisibility(View.GONE);
                holder.visibleMenu.setVisibility(View.VISIBLE);
                if (mrp.equals(price)) {
                    holder.vT_mrp.setVisibility(View.GONE);

                } else {
                    holder.vT_mrp.setText(mrp);
                    holder.vT_mrp.setPaintFlags(holder.vT_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }
            }

            String url = WebServices.Foodey_Food_Url + foodList.get(position).getImage();


            if (!url.equals("")) {
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.food_item_image);
            }


        }

        public int getItemCount() {
            return foodList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            FrameLayout vL_admd_main;
            LinearLayout vL_admd_hide, vL_add_to_cart, vL_count;
            ImageView vI_admd_image;
            TextView vT_admd_text, vT_price, visibleMenu, vT_mrp, vT_add;
            ImageView qtyM, qtyA, food_item_image;
            TextView qty, vT_count;
            Button buttonAdd;

            public MyViewHolder(View view) {
                super(view);
                this.vL_admd_main = (FrameLayout) view.findViewById(R.id.vL_admd_main);
                this.vL_admd_hide = (LinearLayout) view.findViewById(R.id.vL_admd_hide);
                this.vL_add_to_cart = (LinearLayout) view.findViewById(R.id.vL_add_to_cart);
                this.vL_count = (LinearLayout) view.findViewById(R.id.vL_count);

                this.vI_admd_image = (ImageView) view.findViewById(R.id.vI_admd_image);
                this.vT_admd_text = (TextView) view.findViewById(R.id.vT_admd_text);
                this.vT_add = view.findViewById(R.id.vT_add);
                //this.vT_count = view.findViewById(R.id.vT_count);


                this.qty = view.findViewById(R.id.qty);
                this.qtyA = view.findViewById(R.id.qtyA);
                this.qtyM = view.findViewById(R.id.qtyM);
                this.food_item_image = view.findViewById(R.id.food_item_image);
                vT_mrp = view.findViewById(R.id.vT_mrp);
                vT_price = view.findViewById(R.id.vT_price);
                buttonAdd = view.findViewById(R.id.buttonAdd);
                visibleMenu = view.findViewById(R.id.visibleMenu);
            }
        }
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
