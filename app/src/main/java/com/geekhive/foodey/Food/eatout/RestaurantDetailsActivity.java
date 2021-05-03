package com.geekhive.foodey.Food.eatout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.addtocart.AddToCart;
import com.geekhive.foodey.Food.beans.restaurantdetails.RestaurantDetails;
import com.geekhive.foodey.Food.carousel.CarouselEffectTransformer;
import com.geekhive.foodey.Food.customs.SpacesItemDecoration;
import com.geekhive.foodey.Food.orderfood.CheckOutActivityNew;
import com.geekhive.foodey.Food.tablebooking.TableBookingActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantDetailsActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_ard_image)
    ImageView vIArdImage;
    @BindView(R.id.vI_ard_back)
    ImageView vIArdBack;
    @BindView(R.id.vT_ard_photos)
    TextView vTArdPhotos;
    @BindView(R.id.vT_ard_name)
    TextView vTArdName;
    @BindView(R.id.vT_ard_status)
    TextView vTArdStatus;
    @BindView(R.id.vT_ard_place)
    TextView vTArdPlace;
    @BindView(R.id.vT_ard_timing)
    TextView vTArdTiming;
    @BindView(R.id.vT_ard_price)
    TextView vTArdPrice;
    @BindView(R.id.vT_ard_style)
    TextView vTArdStyle;
    @BindView(R.id.vT_ard_viewmap)
    TextView vTArdViewmap;
    @BindView(R.id.vT_ard_rating)
    TextView vTArdRating;
    @BindView(R.id.vR_ard_main)
    RelativeLayout vRArdMain;
    @BindView(R.id.vC_ard_collapsing_toolbar)
    CollapsingToolbarLayout vCArdCollapsingToolbar;
    @BindView(R.id.vA_ard_appbar)
    AppBarLayout vAArdAppbar;
    @BindView(R.id.vC_ard_banner)
    CardView vCArdBanner;
    @BindView(R.id.vT_ard_menu)
    TextView vTArdMenu;
    @BindView(R.id.vT_ard_seeall)
    TextView vTArdSeeall;
    @BindView(R.id.vL_ard_menu)
    LinearLayout vLArdMenu;
    @BindView(R.id.vR_ard_menulist)
    RecyclerView vRArdMenulist;
    @BindView(R.id.vT_ard_recommended)
    TextView vTArdRecommended;
    @BindView(R.id.vT_ard_recommended_value)
    TextView vTArdRecommendedValue;
    @BindView(R.id.vT_ard_about)
    TextView vTArdAbout;
    @BindView(R.id.vT_ard_aboutval)
    TextView vTArdAboutval;
    @BindView(R.id.vT_ard_facilities)
    TextView vTArdFacilities;
    @BindView(R.id.vR_ard_facilities)
    RecyclerView vRArdFacilities;
    @BindView(R.id.vT_ard_open)
    TextView vTArdOpen;
    @BindView(R.id.vT_ard_day)
    TextView vTArdDay;
    @BindView(R.id.vT_ard_all_day_dinning)
    TextView vTArdAllDayDinning;
    @BindView(R.id.vT_ard_timing_dinning)
    TextView vTArdTimingDinning;
    @BindView(R.id.vT_ard_address)
    TextView vTArdAddress;
    @BindView(R.id.vT_ard_addressval)
    TextView vTArdAddressval;
    @BindView(R.id.vT_ard_map_down)
    TextView vTArdMapDown;
    @BindView(R.id.vT_ard_mobile)
    TextView vTArdMobile;
    @BindView(R.id.vT_ard_call)
    TextView vTArdCall;
    @BindView(R.id.vT_ard_rate_review)
    TextView vTArdRateReview;
    @BindView(R.id.vT_ard_rate_whole)
    TextView vTArdRateWhole;
    @BindView(R.id.vT_ard_rate5)
    TextView vTArdRate5;
    @BindView(R.id.vP_ard_five)
    ProgressBar vPArdFive;
    @BindView(R.id.vT_ard_rate5_ppl)
    TextView vTArdRate5Ppl;
    @BindView(R.id.vT_ard_rate4)
    TextView vTArdRate4;
    @BindView(R.id.vP_ard_four)
    ProgressBar vPArdFour;
    @BindView(R.id.vT_ard_rate4_ppl)
    TextView vTArdRate4Ppl;
    @BindView(R.id.vT_ard_rate3)
    TextView vTArdRate3;
    @BindView(R.id.vP_ard_three)
    ProgressBar vPArdThree;
    @BindView(R.id.vT_ard_rate3_ppl)
    TextView vTArdRate3Ppl;
    @BindView(R.id.vT_ard_rate2)
    TextView vTArdRate2;
    @BindView(R.id.vP_ard_two)
    ProgressBar vPArdTwo;
    @BindView(R.id.vT_ard_rate2_ppl)
    TextView vTArdRate2Ppl;
    @BindView(R.id.vT_ard_rate1)
    TextView vTArdRate1;
    @BindView(R.id.vP_ard_one)
    ProgressBar vPArdOne;
    @BindView(R.id.vT_ard_rate1_ppl)
    TextView vTArdRate1Ppl;
    @BindView(R.id.vT_ard_leave)
    TextView vTArdLeave;
    @BindView(R.id.vT_ard_leave1)
    TextView vTArdLeave1;
    @BindView(R.id.vI_ard_leave1)
    ImageView vIArdLeave1;
    @BindView(R.id.vT_ard_leave2)
    TextView vTArdLeave2;
    @BindView(R.id.vI_ard_leave2)
    ImageView vIArdLeave2;
    @BindView(R.id.vT_ard_leave3)
    TextView vTArdLeave3;
    @BindView(R.id.vI_ard_leave3)
    ImageView vIArdLeave3;
    @BindView(R.id.vT_ard_leave4)
    TextView vTArdLeave4;
    @BindView(R.id.vI_ard_leave4)
    ImageView vIArdLeave4;
    @BindView(R.id.vT_ard_leave5)
    TextView vTArdLeave5;
    @BindView(R.id.vI_ard_leave5)
    ImageView vIArdLeave5;
    @BindView(R.id.vR_ard_review)
    RecyclerView vRArdReview;
    @BindView(R.id.vT_ard_order)
    TextView vTArdOrder;
    @BindView(R.id.vT_ard_book)
    TextView vTArdBook;

    @BindView(R.id.vT_ard_offer)
    TextView vTArdOffer;
    @BindView(R.id.vV_ard_offer)
    View vVArdOffer;
    @BindView(R.id.vL_ard_offer)
    LinearLayout vLArdOffer;
    @BindView(R.id.vT_ard_menu_t)
    TextView vTArdMenuT;
    @BindView(R.id.vV_ard_menu_t)
    View vVArdMenuT;
    @BindView(R.id.vL_ard_menu_t)
    LinearLayout vLArdMenuT;
    @BindView(R.id.vT_ard_about_address)
    TextView vTArdAboutAddress;
    @BindView(R.id.vV_ard_about_address)
    View vVArdAboutAddress;
    @BindView(R.id.vL_ard_about_address)
    LinearLayout vLArdAboutAddress;
    @BindView(R.id.vT_ard_review)
    TextView vTArdReview;
    @BindView(R.id.vV_ard_review)
    View vVArdReview;
    @BindView(R.id.vL_ard_review)
    LinearLayout vLArdReview;
    @BindView(R.id.vL_ard_sliding_tabs)
    LinearLayout vLArdSlidingTabs;

    Dialog Dialog;

    MenuAdapter menuAdapter;
    FacilitiesAdapter facilitiesAdapter;
    ReviewAdapter reviewAdapter;
    @BindView(R.id.vI_ard_cart)
    ImageView vIArdCart;
    @BindView(R.id.vL_ard_menu_full)
    LinearLayout vLArdMenuFull;
    @BindView(R.id.vL_ard_rate_full)
    LinearLayout vLArdRateFull;
    @BindView(R.id.vL_ard_about_full)
    LinearLayout vLArdAboutFull;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vN_ard_nested)
    NestedScrollView vNArdNested;
    @BindView(R.id.offerHeading)
    TextView offerHeading;
    @BindView(R.id.offerDetails)
    TextView offerDetails;

    String rest_id;

    ConnectionDetector mDetector;

    RestaurantDetails restaurantDetails;

    int quantity = 0;

    String price_;
    String mrp;
    String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
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

                vTArdAbout.setTypeface(NIRMALAB);
                vTArdOffer.setTypeface(NIRMALAB);
                vTArdAboutAddress.setTypeface(NIRMALAB);
                vTArdReview.setTypeface(NIRMALAB);
                vTArdMenuT.setTypeface(NIRMALAB);
                vTArdAboutval.setTypeface(NIRMALA);
                vTArdAddress.setTypeface(NIRMALAB);
                vTArdAddressval.setTypeface(NIRMALA);
                vTArdAllDayDinning.setTypeface(NIRMALAB);
                vTArdBook.setTypeface(NIRMALAB);
                vTArdCall.setTypeface(NIRMALA);
                vTArdDay.setTypeface(NIRMALA);
                vTArdFacilities.setTypeface(NIRMALAB);
                offerHeading.setTypeface(NIRMALAB);
                offerDetails.setTypeface(NIRMALA);
                vTArdLeave.setTypeface(NIRMALAB);
                vTArdLeave1.setTypeface(NIRMALA);
                vTArdLeave2.setTypeface(NIRMALA);
                vTArdLeave3.setTypeface(NIRMALA);
                vTArdLeave4.setTypeface(NIRMALA);
                vTArdLeave5.setTypeface(NIRMALA);
                vTArdMapDown.setTypeface(NIRMALAB);
                vTArdMenu.setTypeface(NIRMALAB);
                vTArdMobile.setTypeface(NIRMALA);
                vTArdName.setTypeface(NIRMALAB);
                vTArdOpen.setTypeface(NIRMALAB);
                vTArdOrder.setTypeface(NIRMALAB);
                vTArdPhotos.setTypeface(NIRMALA);
                vTArdPlace.setTypeface(NIRMALA);
                vTArdPrice.setTypeface(NIRMALA);
                vTArdRate1.setTypeface(NIRMALA);
                vTArdRate1Ppl.setTypeface(NIRMALA);
                vTArdRate2.setTypeface(NIRMALA);
                vTArdRate2Ppl.setTypeface(NIRMALA);
                vTArdRate3.setTypeface(NIRMALA);
                vTArdRate3Ppl.setTypeface(NIRMALA);
                vTArdRate4.setTypeface(NIRMALA);
                vTArdRate4Ppl.setTypeface(NIRMALA);
                vTArdRate5.setTypeface(NIRMALA);
                vTArdRate5Ppl.setTypeface(NIRMALA);
                vTArdRateReview.setTypeface(NIRMALAB);
                vTArdRateWhole.setTypeface(NIRMALAB);
                vTArdRating.setTypeface(NIRMALA);
                vTArdRecommended.setTypeface(NIRMALAB);
                vTArdRecommendedValue.setTypeface(NIRMALA);
                vTArdSeeall.setTypeface(NIRMALA);
                vTArdStatus.setTypeface(NIRMALA);
                vTArdStyle.setTypeface(NIRMALA);
                vTArdTiming.setTypeface(NIRMALA);
                vTArdTimingDinning.setTypeface(NIRMALA);
                vTArdViewmap.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        rest_id = getIntent().getStringExtra("res_id");

        facilitiesAdapter = new FacilitiesAdapter();
        RecyclerView.LayoutManager mLayoutManagerf = new GridLayoutManager(getApplicationContext(), 2);
        vRArdFacilities.addItemDecoration(new SpacesItemDecoration(2));
        vRArdFacilities.setLayoutManager(mLayoutManagerf);
        vRArdFacilities.setAdapter(facilitiesAdapter);

        reviewAdapter = new ReviewAdapter();
        vRArdReview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRArdReview.setAdapter(reviewAdapter);

        vTArdBook.setOnClickListener(this);
        vIArdBack.setOnClickListener(this);
        vTArdPhotos.setOnClickListener(this);
        vLArdOffer.setOnClickListener(this);
        vLArdMenuT.setOnClickListener(this);
        vLArdAboutAddress.setOnClickListener(this);
        vLArdReview.setOnClickListener(this);
        vIArdCart.setOnClickListener(this);
        vTArdSeeall.setOnClickListener(this);


        vNArdNested.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (isVisible(vCArdBanner)) {
                    vTArdOffer.setTextColor(getResources().getColor(R.color.colorPrimary));
                    vTArdMenuT.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdAboutAddress.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdReview.setTextColor(getResources().getColor(R.color.text_color));

                    vVArdOffer.setVisibility(View.VISIBLE);
                    vVArdMenuT.setVisibility(View.GONE);
                    vVArdAboutAddress.setVisibility(View.GONE);
                    vVArdReview.setVisibility(View.GONE);

                } else if (isVisible(vLArdMenuFull)) {
                    vTArdOffer.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdMenuT.setTextColor(getResources().getColor(R.color.colorPrimary));
                    vTArdAboutAddress.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdReview.setTextColor(getResources().getColor(R.color.text_color));

                    vVArdOffer.setVisibility(View.GONE);
                    vVArdMenuT.setVisibility(View.VISIBLE);
                    vVArdAboutAddress.setVisibility(View.GONE);
                    vVArdReview.setVisibility(View.GONE);

                } else if (isVisible(vLArdAboutFull)) {
                    vTArdOffer.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdMenuT.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdAboutAddress.setTextColor(getResources().getColor(R.color.colorPrimary));
                    vTArdReview.setTextColor(getResources().getColor(R.color.text_color));

                    vVArdOffer.setVisibility(View.GONE);
                    vVArdMenuT.setVisibility(View.GONE);
                    vVArdAboutAddress.setVisibility(View.VISIBLE);
                    vVArdReview.setVisibility(View.GONE);

                } else if (isVisible(vLArdRateFull)) {
                    vTArdOffer.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdMenuT.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdAboutAddress.setTextColor(getResources().getColor(R.color.text_color));
                    vTArdReview.setTextColor(getResources().getColor(R.color.colorPrimary));

                    vVArdOffer.setVisibility(View.GONE);
                    vVArdMenuT.setVisibility(View.GONE);
                    vVArdAboutAddress.setVisibility(View.GONE);
                    vVArdReview.setVisibility(View.VISIBLE);
                }
            }
        });

        CallService();


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
                    WebServices.ApiType.addToCart, Prefs.getUserId(RestaurantDetailsActivity.this), rest_id, foodId, quantity, price, mrp, distanceAdd, Prefs.getCityId(this));

            return;
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
                    Picasso.get()
                            .load(WebServices.Foodey_Image_Url + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getImage())
                            .into(vIArdImage);


                    vTArdPlace.setText(address);
                    vTArdAddressval.setText(address);
                    vTArdTiming.setText(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getTiming());
                    vTArdStyle.setText(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getType());
                    vTArdAboutval.setText(restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getAbout());
                    vTArdViewmap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String uri = "http://maps.google.com/maps?q=loc:" + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getLat() + "," + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getLong() + " (" + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getName() + ")";
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                            startActivity(intent);
                        }
                    });
                    vTArdMapDown.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String uri = "http://maps.google.com/maps?q=loc:" + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getLat() + "," + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getLong() + " (" + restaurantDetails.getResturantDetails().get(0).getResturant().get(0).getName() + ")";
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                            startActivity(intent);
                        }
                    });
                    menuAdapter = new MenuAdapter();
                    RecyclerView.LayoutManager mLayoutManagerb = new LinearLayoutManager(getApplicationContext());
                    vRArdMenulist.setLayoutManager(mLayoutManagerb);
                    vRArdMenulist.setAdapter(menuAdapter);
                }
            }
        }
        if (apiType == WebServices.ApiType.addToCart) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                AddToCart cartListOut = (AddToCart) response;
                if (cartListOut != null) {
                    quantity = 0;
                    SnackBar.makeText(this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                    //menuAdapter.notifyDataSetChanged();
                } else {
                    SnackBar.makeText(this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
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
            case R.id.vT_ard_photos:
                Initializepopup();
                initializDialog();
                break;
            case R.id.vL_ard_offer:
                vTArdOffer.setTextColor(getResources().getColor(R.color.colorPrimary));
                vTArdMenuT.setTextColor(getResources().getColor(R.color.text_color));
                vTArdAboutAddress.setTextColor(getResources().getColor(R.color.text_color));
                vTArdReview.setTextColor(getResources().getColor(R.color.text_color));

                vVArdOffer.setVisibility(View.VISIBLE);
                vVArdMenuT.setVisibility(View.GONE);
                vVArdAboutAddress.setVisibility(View.GONE);
                vVArdReview.setVisibility(View.GONE);

                vCArdBanner.getParent().requestChildFocus(vCArdBanner, vCArdBanner);
                break;
            case R.id.vL_ard_menu_t:
                vTArdOffer.setTextColor(getResources().getColor(R.color.text_color));
                vTArdMenuT.setTextColor(getResources().getColor(R.color.colorPrimary));
                vTArdAboutAddress.setTextColor(getResources().getColor(R.color.text_color));
                vTArdReview.setTextColor(getResources().getColor(R.color.text_color));

                vVArdOffer.setVisibility(View.GONE);
                vVArdMenuT.setVisibility(View.VISIBLE);
                vVArdAboutAddress.setVisibility(View.GONE);
                vVArdReview.setVisibility(View.GONE);
                vLArdMenuFull.getParent().requestChildFocus(vLArdMenuFull, vLArdMenuFull);
                break;
            case R.id.vL_ard_about_address:
                vTArdOffer.setTextColor(getResources().getColor(R.color.text_color));
                vTArdMenuT.setTextColor(getResources().getColor(R.color.text_color));
                vTArdAboutAddress.setTextColor(getResources().getColor(R.color.colorPrimary));
                vTArdReview.setTextColor(getResources().getColor(R.color.text_color));

                vVArdOffer.setVisibility(View.GONE);
                vVArdMenuT.setVisibility(View.GONE);
                vVArdAboutAddress.setVisibility(View.VISIBLE);
                vVArdReview.setVisibility(View.GONE);
                vLArdAboutFull.getParent().requestChildFocus(vLArdAboutFull, vLArdAboutFull);
                break;
            case R.id.vL_ard_review:
                vTArdOffer.setTextColor(getResources().getColor(R.color.text_color));
                vTArdMenuT.setTextColor(getResources().getColor(R.color.text_color));
                vTArdAboutAddress.setTextColor(getResources().getColor(R.color.text_color));
                vTArdReview.setTextColor(getResources().getColor(R.color.colorPrimary));

                vVArdOffer.setVisibility(View.GONE);
                vVArdMenuT.setVisibility(View.GONE);
                vVArdAboutAddress.setVisibility(View.GONE);
                vVArdReview.setVisibility(View.VISIBLE);

                vLArdRateFull.getParent().requestChildFocus(vLArdRateFull, vLArdRateFull);
                break;
            case R.id.vT_ard_seeall:
                Intent intent1 = new Intent(RestaurantDetailsActivity.this, MenuListActivity.class);
                intent1.putExtra("res_id", rest_id);
                startActivity(intent1);
                finish();
                break;
            case R.id.vI_ard_cart:
                Intent intent2 = new Intent(RestaurantDetailsActivity.this, CheckOutActivityNew.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static boolean isVisible(final View view) {
        if (view == null) {
            return false;
        }
        if (!view.isShown()) {
            return false;
        }
        final Rect actualPosition = new Rect();
        view.getGlobalVisibleRect(actualPosition);
        final Rect screen = new Rect(0, 0, getScreenWidth(), getScreenHeight());
        return actualPosition.intersect(screen);
    }

    private void Initializepopup() {
        Dialog = new Dialog(this);
        Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Dialog.setContentView(R.layout.popup_image);
        Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
    }

    private void initializDialog() {
        Dialog.setContentView(R.layout.popup_image);
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
        Dialog.show();

        final TextView vT_pi_name = (TextView) Dialog.findViewById(R.id.vT_pi_name);
        final TextView vT_pi_photos = (TextView) Dialog.findViewById(R.id.vT_pi_photos);
        final TextView vT_pi_pcount = (TextView) Dialog.findViewById(R.id.vT_pi_pcount);
        final ImageView vI_pi_close = (ImageView) Dialog.findViewById(R.id.vI_pi_close);
        ViewPager vV_pi_viewpager = (ViewPager) Dialog.findViewById(R.id.vV_pi_viewpager);
//        final PagerContainer vP_pi_planContainer = (PagerContainer) Dialog.findViewById(R.id.vP_pi_planContainer);

        vV_pi_viewpager.setClipChildren(false);
        vV_pi_viewpager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.pager_margin));
        vV_pi_viewpager.setOffscreenPageLimit(2);
        vV_pi_viewpager.setPageTransformer(false, new CarouselEffectTransformer(this));

        MyPagerAdapter adapter = new MyPagerAdapter(this);
        vV_pi_viewpager.setAdapter(adapter);
//        vV_pi_viewpager = vP_pi_planContainer.getViewPager();
//        vV_pi_viewpager.setAdapter(adapter);
//        vV_pi_viewpager.setOffscreenPageLimit(adapter.getCount());
//        vV_pi_viewpager.setPageMargin(5);
//        vV_pi_viewpager.setClipChildren(false);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_pi_name.setTypeface(NIRMALAB);
                vT_pi_photos.setTypeface(NIRMALA);
                vT_pi_pcount.setTypeface(NIRMALA);

            }
        };
        r.run();
        Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Dialog.getWindow().setGravity(Gravity.CENTER);


        vI_pi_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Dialog.isShowing())
                    Dialog.dismiss();

            }
        });

        Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
        Dialog.show();
    }


    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_details, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

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
                        SnackBar.makeText(RestaurantDetailsActivity.this, "Minimum Quantity should be 1", SnackBar.LENGTH_SHORT).show();
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
                                restaurantDetails.getResturantDetails().get(1).getFood().get(position).getMrp(),
                                restaurantDetails.getResturantDetails().get(1).getFood().get(position).getId(),
                                restaurantDetails.getResturantDetails().get(1).getFood().get(position).getLatitude(),
                                restaurantDetails.getResturantDetails().get(1).getFood().get(position).getLongitude());
                    } else {
                        SnackBar.makeText(RestaurantDetailsActivity.this, "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                    }
                }
            });


        }

        public int getItemCount() {
            if (restaurantDetails.getResturantDetails().get(1).getFood().size() > 6) {
                return 6;
            } else {
                return restaurantDetails.getResturantDetails().get(1).getFood().size();
            }
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

    public class FacilitiesAdapter extends RecyclerView.Adapter<FacilitiesAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_features, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {


        }

        public int getItemCount() {
            return 6;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_adf_lay;
            TextView vT_adf_text;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adf_lay = (LinearLayout) view.findViewById(R.id.vL_adf_lay);
                this.vT_adf_text = (TextView) view.findViewById(R.id.vT_adf_text);

                new Runnable() {
                    public void run() {
                        vT_adf_text.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }

    public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ratings, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {


        }

        public int getItemCount() {
            return 6;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView vT_adr_rating_des, vT_adr_by, vT_adr_at;
            ImageView vI_adr_image;
            RatingBar vR_adr_rating;

            public MyViewHolder(View view) {
                super(view);
                this.vR_adr_rating = (RatingBar) view.findViewById(R.id.vR_adr_rating);
                this.vI_adr_image = (ImageView) view.findViewById(R.id.vI_adr_image);
                this.vT_adr_rating_des = (TextView) view.findViewById(R.id.vT_adr_rating_des);
                this.vT_adr_by = (TextView) view.findViewById(R.id.vT_adr_by);
                this.vT_adr_at = (TextView) view.findViewById(R.id.vT_adr_at);

                new Runnable() {
                    public void run() {
                        vT_adr_rating_des.setTypeface(NIRMALA);
                        vT_adr_by.setTypeface(NIRMALA);
                        vT_adr_at.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }

    public class MyPagerAdapter extends PagerAdapter {

        Context context;

        public MyPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_pop_photos, null);


            container.addView(view);


            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 16;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
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
