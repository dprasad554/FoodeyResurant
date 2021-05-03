package com.geekhive.foodey.Food.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.addtocart.AddToCart;
import com.geekhive.foodey.Food.beans.snacks.SnackListOut;
import com.geekhive.foodey.Food.eatout.RestaurantDetailsActivityCopy;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SnacksActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    HomeSnackAdapter homeSnackAdapter;
    @BindView(R.id.vI_afr_back)
    ImageView vIAfrBack;
    @BindView(R.id.vT_afr_header)
    TextView vTAfrHeader;
    @BindView(R.id.vT_afr_your)
    TextView vTAfrYour;
    @BindView(R.id.vR_afr_list)
    RecyclerView vRAfrList;

    SnackListOut snackListOut;

    ConnectionDetector mDetector;

    int quantity = 1;
    String qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourties);
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

                vTAfrHeader.setTypeface(NIRMALAB);
                //vTAfrYour.setTypeface(NIRMALAB);


            }
        };
        r.run();
    }

    private void setvalues() {
        vIAfrBack.setOnClickListener(this);
        mDetector = new ConnectionDetector(this);
        CallSnackService();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_afr_back:
                finish();
                break;
        }
    }

    private void CallSnackService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).SnackList(WebServices.Foodey_Services,
                    WebServices.ApiType.snackList, "kadapa");
        }

    }

    private void CallAddService(String quantity, String res_id, String price, String mrp, String foodId, String lat, String lang) {
        if (this.mDetector.isConnectingToInternet()) {

            Double dstn = distance(Double.parseDouble(Prefs.getUserLat(this)), Double.parseDouble(Prefs.getUserLat(this)),
                    Double.parseDouble(lat), Double.parseDouble(lang));
            String distanceAdd = String.valueOf(dstn);
            new WebServices(this).AddToCart(WebServices.Foodey_Services,
                    WebServices.ApiType.addToCart, Prefs.getUserId(this), res_id, foodId, quantity, price, mrp, distanceAdd, Prefs.getCityId(this));

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.snackList) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                snackListOut = (SnackListOut) response;

                if (!isSucces || snackListOut == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (snackListOut != null) {
                        if (snackListOut.getFoodList() != null) {
                            homeSnackAdapter = new HomeSnackAdapter();
                            vRAfrList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            vRAfrList.setAdapter(homeSnackAdapter);
                        }
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        } if (apiType == WebServices.ApiType.addToCart) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                AddToCart cartListOut = (AddToCart) response;
                if (cartListOut != null) {
                    SnackBar.makeText(this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                    //menuAdapter.notifyDataSetChanged();
                } else {
                    SnackBar.makeText(this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }
    public class HomeSnackAdapter extends RecyclerView.Adapter<HomeSnackAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_snacks_list, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_adeot_name.setText(snackListOut.getFoodList().get(position).getFood().getName());
            holder.vT_adeot_style.setText("Snacks");
            Picasso.get()
                    .load(WebServices.Foodey_Image_Url + snackListOut.getFoodList().get(position).getFood().getImage())
                    .into(holder.vI_adeot_image);

            holder.vI_adeot_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SnacksActivity.this, RestaurantDetailsActivityCopy.class);
                    intent.putExtra("from", "eat");
                    intent.putExtra("res_id", snackListOut.getFoodList().get(position).getFood().getResId());
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            });

        }

        public int getItemCount() {
            return snackListOut.getFoodList().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView vT_adeot_name, vT_adeot_style;
            ImageView vI_adeot_image;

            public MyViewHolder(View view) {
                super(view);
                vT_adeot_name = view.findViewById(R.id.vT_adeot_name);
                vI_adeot_image = view.findViewById(R.id.vI_adeot_image);
                vT_adeot_style = view.findViewById(R.id.vT_adeot_style);
                new Runnable() {
                    public void run() {
                        vT_adeot_name.setTypeface(NIRMALAB);
                        vT_adeot_style.setTypeface(NIRMALA);
                    }
                }.run();
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
