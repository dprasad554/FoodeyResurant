package com.geekhive.foodey.Food.eatout;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.beans.categories.RestaurantCategory;
import com.geekhive.foodey.Food.beans.offers.OfferList;
import com.geekhive.foodey.Food.beans.rating.ResturantRating;
import com.geekhive.foodey.Food.beans.restaurantdetails.RestaurantDetails;
import com.geekhive.foodey.Food.home.SearchRestaurantActivity;
import com.geekhive.foodey.Food.orderfood.CheckOutActivityNew;
import com.geekhive.foodey.Food.tablebooking.TableBookingActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantDetailsActivityCopy extends AppCompatActivity implements View.OnClickListener, OnResponseListner, CompoundButton.OnCheckedChangeListener {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    /*
        @BindView(R.id.vA_ard_appbar)
        AppBarLayout vAArdAppbar;*/
   /* @BindView(R.id.vC_ard_collapsing_toolbar)
    CollapsingToolbarLayout vCArdCollapsingToolbar;*/
    /*@BindView(R.id.vI_ard_image)
    ImageView vIArdImage;*/
    @BindView(R.id.vI_ard_back)
    ImageView vIArdBack;
    @BindView(R.id.vR_ard_main)
    LinearLayout vRArdMain;
    @BindView(R.id.vT_ard_name)
    TextView vTArdName;
    @BindView(R.id.vT_ard_status)
    TextView vTArdStatus;
    @BindView(R.id.vT_ard_place)
    TextView vTArdPlace;
    @BindView(R.id.vT_ard_timing)
    TextView vTArdTiming;
    @BindView(R.id.vT_ard_style)
    TextView vTArdStyle;
    @BindView(R.id.vT_ard_viewmap)
    TextView vTArdViewmap;
    @BindView(R.id.vT_ard_book)
    TextView vTArdBook;
    @BindView(R.id.vR_offer)
    RecyclerView vROffer;
    @BindView(R.id.vI_search)
    ImageView vISearch;
    @BindView(R.id.vS_sb)
    SwitchCompat vS_sb;
    @BindView(R.id.vT_st)
    TextView vT_st;
    @BindView(R.id.del_time)
    TextView delTime;
    @BindView(R.id.resRating)
    TextView resRating;
    @BindView(R.id.llResOffer)
    LinearLayout llResOffer;
    @BindView(R.id.appCost)
    TextView appCost;

    String rest_id;

    ConnectionDetector mDetector;

    RestaurantDetails restaurantDetails;
    HomeOfferAdapter homeOfferAdapter;
    OfferList offerList;

    int quantity = 0;

    String price_;
    String mrp;
    String foodId;
    RestaurantCategory restaurantCategory;

    Dialog searchpopup;


    private Fragment viewFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_copy);
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

                vTArdName.setTypeface(NIRMALAB);
                vTArdPlace.setTypeface(NIRMALA);
                //vTArdRating.setTypeface(NIRMALA);
                vTArdStatus.setTypeface(NIRMALA);
                vTArdStyle.setTypeface(NIRMALA);
                vTArdTiming.setTypeface(NIRMALA);
                vTArdViewmap.setTypeface(NIRMALA);

            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        rest_id = getIntent().getStringExtra("res_id");

        vIArdBack.setOnClickListener(this);
        CallService();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("res_id", rest_id);
        bundle.putString("vegonly", "nonveg");
        // set Fragmentclass Arguments
        FixturesTabs fragobj = new FixturesTabs();
        fragobj.setArguments(bundle);
        mFragmentTransaction.replace(R.id.fl_containerView, fragobj).commit();
        vTArdBook.setOnClickListener(this);
        vISearch.setOnClickListener(this);
        vS_sb.setOnCheckedChangeListener(this);

    }


    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantDetails(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantDetails, rest_id);
        }

    }

    private void CallOfferService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantOfferList(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantofferlist, rest_id, Prefs.getUserId(this));
        }

    }

    private void CallRatingService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantRating(WebServices.Foodey_Services,
                    WebServices.ApiType.resRat, rest_id);
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
                    String address = restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getAddress() + ", " + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getLocation();
                    vTArdName.setText(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getName());
                    delTime.setText(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getDeliveryTime());
                    //String approx = "₹ " + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getApproxAmount();
                    //appCost.setText(approx);
                    if(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getTableBooking().equals("0")){
                        vTArdBook.setVisibility(View.GONE);
                    }else {
                        vTArdBook.setVisibility(View.VISIBLE);
                    }

                    try {
                        String s = restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getTiming();
                        String[] split = s.split("to");
                        String firstSubString = split[0];
                        String secondSubString = split[1];

                        Date mToday = new Date();

                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                        String curTime = sdf.format(mToday);
                        Date start = null;

                        start = sdf.parse(firstSubString);

                        Date end = sdf.parse(secondSubString);
                        Date userDate = sdf.parse(curTime);

                        if (end.before(start)) {
                            Calendar mCal = Calendar.getInstance();
                            mCal.setTime(end);
                            mCal.add(Calendar.DAY_OF_YEAR, 1);
                            end.setTime(mCal.getTimeInMillis());
                        }

                        Log.d("curTime", userDate.toString());
                        Log.d("start", start.toString());
                        Log.d("end", end.toString());


                        if (userDate.after(start) && userDate.before(end)) {
                            Log.d("result", "falls between start and end , go to screen 1 ");
                            vTArdStatus.setText("Open");
                        } else {
                            Log.d("result", "does not fall between start and end , go to screen 2 ");
                            vTArdStatus.setText("Closed");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    vTArdPlace.setText(address);
                    vTArdTiming.setText(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getTiming());
                    vTArdStyle.setText(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getType());
                    vTArdViewmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double latitude = Double.parseDouble(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getLat());
                            double longitude = Double.parseDouble(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getLong());
                            String label = restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getName();
                            String uriBegin = "geo:" + latitude + "," + longitude;
                            String query = latitude + "," + longitude + "(" + label + ")";
                            String encodedQuery = Uri.encode(query);
                            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                            Uri uri = Uri.parse(uriString);
                            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                            startActivity(mapIntent);
                        }
                    });

                    CallOfferService();
                }
            }
        }
        if (apiType == WebServices.ApiType.restaurantofferlist) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                offerList = (OfferList) response;

                if (!isSucces || offerList == null) {
                    CallRatingService();
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (offerList != null) {
                        if (offerList.getOfferDetails() != null) {
                            if (offerList.getOfferDetails().size() != 0) {
                                llResOffer.setVisibility(View.VISIBLE);
                                homeOfferAdapter = new HomeOfferAdapter();
                                vROffer.setLayoutManager(new LinearLayoutManager(this.getApplicationContext(), 0, false));
                                vROffer.setAdapter(homeOfferAdapter);
                                CallRatingService();
                            } else {
                                llResOffer.setVisibility(View.GONE);
                                CallRatingService();
                            }

                        } else {
                            llResOffer.setVisibility(View.GONE);
                            CallRatingService();
                        }
                    } else {
                        llResOffer.setVisibility(View.GONE);
                        CallRatingService();
                    }
                }
            }
        }
        else if (apiType == WebServices.ApiType.resRat) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                ResturantRating resturantRating = (ResturantRating) response;

                if (!isSucces || resturantRating == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (resturantRating != null) {
                        if (resturantRating.getRaiting() != null) {
                            resRating.setText(resturantRating.getRaiting().get(0).getRating().getRating());
                        }
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vT_ard_book:
                Intent intent = new Intent(this, TableBookingActivity.class);
                intent.putExtra("res_id", rest_id);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.vI_ard_back:
                finish();
                break;
            case R.id.vI_ard_cart:
                Intent intentc = new Intent(RestaurantDetailsActivityCopy.this, CheckOutActivityNew.class);
                startActivity(intentc);
                finish();
                break;
            case R.id.vI_search:
                Intent intent1 = new Intent(RestaurantDetailsActivityCopy.this, SearchRestaurantActivity.class);
                intent1.putExtra("resId", rest_id);
                intent1.putExtra("restName", vTArdName.getText().toString());
                startActivity(intent1);
                /*InitializeSearchpopup();
                initializePopup();*/
                break;


        }

    }

    private void InitializeSearchpopup() {
        searchpopup = new Dialog(this);
        searchpopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        searchpopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        searchpopup.setContentView(R.layout.search_popup);
        searchpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        searchpopup.setCancelable(true);
        searchpopup.setCanceledOnTouchOutside(true);
    }

    private void initializePopup() {
        searchpopup.setContentView(R.layout.search_popup);
        searchpopup.setCancelable(true);
        searchpopup.setCanceledOnTouchOutside(true);
        searchpopup.show();


        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        searchpopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchpopup.getWindow().setGravity(Gravity.TOP);


        searchpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        searchpopup.setCancelable(true);
        searchpopup.setCanceledOnTouchOutside(true);
        searchpopup.show();
    }


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            vT_st.setText("VEG ONLY");
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("res_id", rest_id);
            bundle.putString("vegonly", "vegonly");
            // set Fragmentclass Arguments
            FixturesTabs fragobj = new FixturesTabs();
            fragobj.setArguments(bundle);
            mFragmentTransaction.replace(R.id.fl_containerView, fragobj).commit();
        } else {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("res_id", rest_id);
            bundle.putString("vegonly", "noveg");
            // set Fragmentclass Arguments
            FixturesTabs fragobj = new FixturesTabs();
            fragobj.setArguments(bundle);
            mFragmentTransaction.replace(R.id.fl_containerView, fragobj).commit();
        }


    }


    public class HomeOfferAdapter extends RecyclerView.Adapter<HomeOfferAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_offers, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

//            holder.vT_adhr_name.setText(offerList.getOfferDetails().getResturant().get(position).getName());
            //   holder.vT_adhr_place.setText(offerList.getOfferDetails().getResturant().get(position).getLocation());
           // String offer = offerList.getOfferDetails().get(position).getOffer().getName() + "\n" + offerList.getOfferDetails().get(position).getOffer().getDetail();
            //  holder.vT_adhr_offer.setText(offer);


            Picasso.get()
                    .load(WebServices.Foodey_Offer_Url + offerList.getOfferDetails().get(position).getOffer().getImage())
                    .into(holder.vI_adhr_image);
            holder.vC_adhr_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RestaurantDetailsActivityCopy.this, RestaurantDetailsActivityCopy.class);
                    intent.putExtra("from", "eat");
                    intent.putExtra("res_id", offerList.getOfferDetails().get(position).getOffer().getId());
                    startActivity(intent);
                    RestaurantDetailsActivityCopy.this.overridePendingTransition(0, 0);
                }
            });

        }

        public int getItemCount() {

            return offerList.getOfferDetails().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            CardView vC_adhr_card;
            ImageView vI_adhr_image;
            //TextView vT_adhr_name, vT_adhr_place, vT_adhr_offer;

            public MyViewHolder(View view) {
                super(view);
                this.vC_adhr_card = (CardView) view.findViewById(R.id.vC_adhr_card);
                this.vI_adhr_image = (ImageView) view.findViewById(R.id.vI_adhr_image);
                /*this.vT_adhr_name = (TextView) view.findViewById(R.id.vT_adhr_name);
                this.vT_adhr_place = (TextView) view.findViewById(R.id.vT_adhr_place);
                this.vT_adhr_offer = (TextView) view.findViewById(R.id.vT_adhr_offer);
                new Runnable() {
                    public void run() {
//                       vT_adhr_name.setTypeface(NIRMALA);
//                       vT_adhr_place.setTypeface(NIRMALA);
//                       vT_adhr_offer.setTypeface(NIRMALA);
                    }
                }.run();*/
            }
        }
    }


}
