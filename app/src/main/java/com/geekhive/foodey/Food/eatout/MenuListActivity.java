package com.geekhive.foodey.Food.eatout;

import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.addtocart.AddToCart;
import com.geekhive.foodey.Food.beans.restaurantdetails.Food;
import com.geekhive.foodey.Food.beans.restaurantdetails.RestaurantDetails;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuListActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALAB;
    String rest_id;
    @BindView(R.id.vR_ard_menulist)
    RecyclerView vRArdMenuList;
    @BindView(R.id.vI_aac_menu_back)
    ImageView vIAacMenuBack;
    @BindView(R.id.vT_aac_menu_header)
    TextView vTAacMenuHeader;
    @BindView(R.id.searchView)
    SearchView searchView;
    LinearLayoutManager linearLayoutManager;

    ConnectionDetector mDetector;

    RestaurantDetails restaurantDetails;

    int quantity = 0;

    String price_;
    String mrp;
    String foodId;
    MenuAdapter menuAdapter;
    private ArrayList<Food> arraylist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        ButterKnife.bind(this);
        mDetector = new ConnectionDetector(this);
        rest_id = getIntent().getStringExtra("res_id");
        vIAacMenuBack.setOnClickListener(this);
        initializeFonts();
        setTypeFace();
        CallService();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //bookingListAdapter.filter(s);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                vTAacMenuHeader.setTypeface(NIRMALAB);
            }
        };
        r.run();
    }


    private void initializeFonts() {
        this.NIRMALAB = Typeface.createFromAsset(getAssets(), "NIRMALAB.TTF");
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantDetails(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantDetails, rest_id);
        }

    }

    private void CallAddService(String quantity, String price, String mrp, String foodId, String lat, String lang) {
        if (this.mDetector.isConnectingToInternet()) {
            Double dstn = distance(Double.parseDouble(Prefs.getUserLat(this)), Double.parseDouble(Prefs.getUserLat(this)),
                    Double.parseDouble(lat), Double.parseDouble(lang));
            String distanceAdd = String.valueOf(dstn);

            new WebServices(this).AddToCart(WebServices.Foodey_Services,
                    WebServices.ApiType.addToCart, Prefs.getUserId(MenuListActivity.this), rest_id, foodId, quantity + "", price_, mrp, distanceAdd, Prefs.getCityId(this));

            return;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_aac_menu_back:
                finish();
                break;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.restaurantDetails) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {

                restaurantDetails = (RestaurantDetails) response;

                if (!isSucces || restaurantDetails == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    menuAdapter = new MenuAdapter();
                    RecyclerView.LayoutManager mLayoutManagerb = new LinearLayoutManager(getApplicationContext());
                    vRArdMenuList.setLayoutManager(mLayoutManagerb);
                    vRArdMenuList.setAdapter(menuAdapter);
                    arraylist.addAll(restaurantDetails.getResturantDetails().get(1).getFood());
                }
            }
        }
        if (apiType == WebServices.ApiType.addToCart) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                AddToCart cartListOut = (AddToCart) response;
                if (cartListOut != null) {
                    SnackBar.makeText(this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }


    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

        public MenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_details, parent, false));
        }

        public void onBindViewHolder(final MenuAdapter.MyViewHolder holder, final int position) {

            holder.vT_admd_text.setText(restaurantDetails.getResturantDetails().get(1).getFood().get(position).getName());
            price_ = restaurantDetails.getResturantDetails().get(1).getFood().get(position).getPrice();
            mrp = restaurantDetails.getResturantDetails().get(1).getFood().get(position).getMrp();
            String price = "₹ " + restaurantDetails.getResturantDetails().get(1).getFood().get(position).getPrice();
            holder.vT_price.setText(price);
            holder.qty.setText("0");
            //holder.qty.setText(String.valueOf(quantity));


            holder.qtyM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(holder.qty.getText().toString()) != 1) {
                        int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                        holder.qty.setText(String.valueOf(qaunt));
                    } else {
                        SnackBar.makeText(MenuListActivity.this, "Minimum Quantity should be 1", SnackBar.LENGTH_SHORT).show();
                    }
                }
            });

            holder.qtyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                    holder.qty.setText(String.valueOf(qaunt));
                }
            });

            holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                        foodId = restaurantDetails.getResturantDetails().get(1).getFood().get(position).getId();
                        CallAddService(holder.qty.getText().toString(), restaurantDetails.getResturantDetails().get(1).getFood().get(position).getPrice(),
                                restaurantDetails.getResturantDetails().get(1).getFood().get(position).getMrp(), restaurantDetails.getResturantDetails().get(1).getFood().get(position).getId(),
                                restaurantDetails.getResturantDetails().get(1).getFood().get(position).getLatitude(), restaurantDetails.getResturantDetails().get(1).getFood().get(position).getLongitude());
                    } else {
                        SnackBar.makeText(MenuListActivity.this, "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                    }
                }
            });


        }

        public int getItemCount() {
            return restaurantDetails.getResturantDetails().get(1).getFood().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_admd_main, vL_admd_hide;
            ImageView vI_admd_image;
            TextView vT_admd_text, vT_price;
            ImageView qtyM, qtyA;
            TextView qty;
            Button buttonAdd;

            public MyViewHolder(View view) {
                super(view);
                this.vL_admd_main = (LinearLayout) view.findViewById(R.id.vL_admd_main);
                this.vL_admd_hide = (LinearLayout) view.findViewById(R.id.vL_admd_hide);
                this.vI_admd_image = (ImageView) view.findViewById(R.id.vI_admd_image);
                this.vT_admd_text = (TextView) view.findViewById(R.id.vT_admd_text);
                this.qty = view.findViewById(R.id.qty);
                this.qtyA = view.findViewById(R.id.qtyA);
                this.qtyM = view.findViewById(R.id.qtyM);
                vT_price = view.findViewById(R.id.vT_price);
                buttonAdd = view.findViewById(R.id.buttonAdd);

                new Runnable() {
                    public void run() {
                        vT_admd_text.setTypeface(NIRMALAB);
                        vT_price.setTypeface(NIRMALAB);
                    }
                }.run();
            }
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        restaurantDetails.getResturantDetails().get(1).getFood().clear();
        if (charText.length() == 0) {
            restaurantDetails.getResturantDetails().get(1).getFood().addAll(arraylist);
        } else {
            for (Food wp : arraylist) {

                //String name = wp.getManf_name() + wp.getModel_name();
                String name2 = wp.getName();
                String name3 = wp.getCategory();

                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText) || name2.toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getCategory().toLowerCase(Locale.getDefault())
                        .contains(charText) || name3.toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    restaurantDetails.getResturantDetails().get(1).getFood().add(wp);
                }
            }
        }
        menuAdapter.notifyDataSetChanged();
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
