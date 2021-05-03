package com.geekhive.foodey.Food.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.geekhive.foodey.Food.beans.addtocart.AddToCart;
import com.geekhive.foodey.Food.beans.bestseling.BestSelling;
import com.geekhive.foodey.Food.beans.breakfast.BreakfastListOut;
import com.geekhive.foodey.Food.beans.citylist.CityList;
import com.geekhive.foodey.Food.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Food.beans.mylocation.MyLocation;
import com.geekhive.foodey.Food.beans.newslider.ResNewSlider;
import com.geekhive.foodey.Food.beans.offers.OfferList;
import com.geekhive.foodey.Food.beans.restaurantList.RestaurantListOut;
import com.geekhive.foodey.Food.beans.snacks.SnackListOut;
import com.geekhive.foodey.Food.eatout.EatOutActivity;
import com.geekhive.foodey.Food.eatout.RestaurantDetailsActivityCopy;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by user pc on 25-07-2018.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    View view;
    Bundle savedInstanceStateq;
    @BindView(R.id.vR_restaurant)
    RecyclerView vRRestaurant;
    @BindView(R.id.vT_restaurant)
    TextView vTRestaurant;
    @BindView(R.id.vT_restaurant_more)
    TextView vTRestaurantMore;
    @BindView(R.id.vL_restaurant)
    LinearLayout vLRestaurant;
    @BindView(R.id.vR_breakfast)
    RecyclerView vRBreakfast;
    @BindView(R.id.vT_breakfast)
    TextView vTBreakfast;
    @BindView(R.id.vT_breakfast_more)
    TextView vTBreakfastMore;
    @BindView(R.id.vL_breakfast)
    LinearLayout vLBreakfast;

    /*@BindView(R.id.vR_offer)
    RecyclerView vROffer;*/
    @BindView(R.id.vT_offer)
    TextView vTOffer;
    @BindView(R.id.vT_offer_more)
    TextView vTOfferMore;
    @BindView(R.id.vL_offer)
    LinearLayout vLOffer;

    @BindView(R.id.vR_snacks)
    RecyclerView vRSnacks;
    @BindView(R.id.vT_snacks)
    TextView vTSnacks;
    @BindView(R.id.vT_snacks_more)
    TextView vTSnacksMore;
    @BindView(R.id.vL_snacks)
    LinearLayout vLSnacks;
    Unbinder unbinder;
    HomeRestaurantAdapter homeRestaurantAdapter;
    HomeBreakfastAdapter homeBreakfastAdapter;
    HomeSnackAdapter homeSnackAdapter;
    //HomeOfferAdapter homeOfferAdapter;
    @BindView(R.id.vV_ah_image)
    ViewPager vVAhImage;
    @BindView(R.id.vC_ah_circularIndicator)
    CirclePageIndicator vCAhCircularIndicator;
    @BindView(R.id.llError)
    LinearLayout llError;
    @BindView(R.id.llMain)
    LinearLayout llMain;
    /*@BindView(R.id.add_breakfast)
    Button add_breakfast;*/
    int drawable[] = {R.drawable.bannerimages, R.drawable.bannerimages, R.drawable.bannerimages};

    ConnectionDetector mDetector;

    RestaurantListOut restaurantListOut;
    BreakfastListOut breakfastListOut;
    SnackListOut snackListOut;
    OfferList offerList;
    ResNewSlider sliderHomeList;
    String message;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    double lat;
    double lang;
    String lati;
    String longi;
    List<Address> addresses;
    Geocoder geocoder;
    boolean GpsStatus;
    private static final int REQUEST_CODE = 101;
    String city = "";
    int quantBr = 1;
    int quantSn = 1;
    int quantSp = 1;

    String quantity = "";
    String res_id = "";
    String price = "";
    String mrp = "";
    String foodId = "";
    BestSelling bestSelling;
    String firstSubString;
    String secondSubString;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_home_copy, container, false);
        this.savedInstanceStateq = savedInstanceState;
        unbinder = ButterKnife.bind(this, view);
        initializeFonts();
        setTypeFace();
        setvalues();
        return view;
    }


    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAS.TTF");
    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                vTRestaurant.setTypeface(NIRMALAB);
                vTRestaurantMore.setTypeface(NIRMALA);
                vTBreakfast.setTypeface(NIRMALAB);
                vTBreakfastMore.setTypeface(NIRMALA);
                vTOffer.setTypeface(NIRMALAB);
                vTOfferMore.setTypeface(NIRMALA);
                vTSnacks.setTypeface(NIRMALAB);
                vTSnacksMore.setTypeface(NIRMALA);


            }
        };
        r.run();
    }


    private void setvalues() {

        mDetector = new ConnectionDetector(getActivity());
        vTRestaurantMore.setOnClickListener(this);
        vTBreakfastMore.setOnClickListener(this);
        vTSnacksMore.setOnClickListener(this);

        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        //Map Current Location Work
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (Prefs.getUserLat(getActivity()).equals("")) {
            fetchLastLocation();
        } else {
            try {
                lat = Double.parseDouble(Prefs.getUserLat(getActivity()));
                lang = Double.parseDouble(Prefs.getUserLang(getActivity()));

                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                if (addresses.size() != 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    city = addresses.get(0).getLocality();
                    CallMyLocationService(Prefs.getUserLat(getActivity()), Prefs.getUserLang(getActivity()));
                } else {
                    fetchLastLocation();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //pushNotification();
        //CallService();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vT_restaurant_more:
                Intent intent = new Intent(getActivity(), EatOutActivity.class);
                intent.putExtra("from", "eat");
                intent.putExtra("city", city);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.vT_offer_more:
                Intent intentm = new Intent(getActivity(), EatOutActivity.class);
                intentm.putExtra("from", "eat");
                intentm.putExtra("city", city);
                startActivity(intentm);
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.vT_breakfast_more:
                Intent intentb = new Intent(getActivity(), BreakfastActivity.class);
                intentb.putExtra("from", "eat");
                intentb.putExtra("city", city);
                startActivity(intentb);
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.vT_snacks_more:
                Intent intents = new Intent(getActivity(), SnacksActivity.class);
                intents.putExtra("from", "eat");
                intents.putExtra("city", city);
                startActivity(intents);
                getActivity().overridePendingTransition(0, 0);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CallService();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void CallMyLocationService(String latitude, String longitude) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).UpdateUserLocation(WebServices.Foodey_Services,
                    WebServices.ApiType.mylocation, Prefs.getUserId(getActivity()), latitude, longitude);
        }
    }

    private void CallCityList() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GetCityList(WebServices.Foodey_Services,
                    WebServices.ApiType.citylist);
            return;
        }
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantList(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantList, city, Prefs.getUserId(getActivity()));
        }

    }

    private void CallBestService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).BestSellingList(WebServices.Foodey_Services,
                    WebServices.ApiType.bestselling, Prefs.getUserId(getActivity()));
        }

    }


    /*private void CallOfferService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantOfferList(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantofferlist);
        }

    }*/

    private void CallSnackService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).SnackList(WebServices.Foodey_Services,
                    WebServices.ApiType.snackList, Prefs.getUserId(getActivity()));
        }

    }

    /*private void CallBreakfastService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).BreakfastList(WebServices.Foodey_Services,
                    WebServices.ApiType.breakfastList, Prefs.getUserId(getActivity()));
        }
    }*/

    private void CallSliderService() {
        if (this.mDetector.isConnectingToInternet()) {


            new WebServices(this).NewSlider(WebServices.Foodey_Services,
                    WebServices.ApiType.sliderImages);

            return;
        }

    }

    private void CallAddService(String distanceAdd) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).AddToCart(WebServices.Foodey_Services,
                    WebServices.ApiType.addToCart, Prefs.getUserId(getContext()), res_id, foodId, quantity, price, mrp, distanceAdd, Prefs.getCityId(getActivity()));
            return;
        }
    }


    private void CallAdd(String lat, String lang) {

        String str_origin = Prefs.getUserLat(getActivity()) + "," + Prefs.getUserLang(getActivity());
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
            SnackBar.makeText(getActivity(), "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.restaurantList) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                restaurantListOut = (RestaurantListOut) response;

                if (!isSucces || restaurantListOut == null) {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (restaurantListOut != null) {
                        if (restaurantListOut.getResturant() != null) {
                            vLRestaurant.setVisibility(View.VISIBLE);
                            homeRestaurantAdapter = new HomeRestaurantAdapter();
                            vRRestaurant.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                            vRRestaurant.setAdapter(homeRestaurantAdapter);
                            CallSnackService();
                            llMain.setVisibility(View.VISIBLE);
                            llError.setVisibility(View.GONE);

                        } else {
                            vLRestaurant.setVisibility(View.GONE);
                            CallSnackService();
                            SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                            llMain.setVisibility(View.GONE);
                            llError.setVisibility(View.VISIBLE);
                        }

                    } else {
                        llMain.setVisibility(View.GONE);
                        llError.setVisibility(View.VISIBLE);
                        vLRestaurant.setVisibility(View.GONE);
                        CallSnackService();
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                }
            }
        }
        if (apiType == WebServices.ApiType.bestselling) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                bestSelling = (BestSelling) response;

                if (!isSucces || bestSelling == null) {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (bestSelling != null) {
                        if (bestSelling.getFoodList() != null) {
                            vLOffer.setVisibility(View.VISIBLE);
                            vLOffer.setVisibility(View.VISIBLE);
                            homeBreakfastAdapter = new HomeBreakfastAdapter();
                            vRBreakfast.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), 0, false));
                            vRBreakfast.setAdapter(homeBreakfastAdapter);
                          /*  llMain.setVisibility(View.VISIBLE);
                            llError.setVisibility(View.GONE);*/
                            //CallBestService();
                            /*llMain.setVisibility(View.VISIBLE);
                            llError.setVisibility(View.GONE);*/
                        } else {
                            /*llMain.setVisibility(View.GONE);
                            llError.setVisibility(View.VISIBLE);*/
                            vLOffer.setVisibility(View.GONE);
                            vLOffer.setVisibility(View.GONE);
                        }
                    } else {
                        /*llMain.setVisibility(View.GONE);
                        llError.setVisibility(View.VISIBLE);*/
                        vLOffer.setVisibility(View.GONE);
                        vLOffer.setVisibility(View.GONE);
                    }
                }
            }
        }
        if (apiType == WebServices.ApiType.breakfastList) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                breakfastListOut = (BreakfastListOut) response;

                if (!isSucces || breakfastListOut == null) {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (breakfastListOut != null) {
                        if (breakfastListOut.getFoodList() != null) {
                            vLBreakfast.setVisibility(View.VISIBLE);
                            vRBreakfast.setVisibility(View.VISIBLE);
                            homeBreakfastAdapter = new HomeBreakfastAdapter();
                            vRBreakfast.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), 0, false));
                            vRBreakfast.setAdapter(homeBreakfastAdapter);
                            CallBestService();
                        } else {
                            vLBreakfast.setVisibility(View.GONE);
                            vRBreakfast.setVisibility(View.GONE);
                            CallBestService();
                            // CallSnackService();
                            //SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        vLBreakfast.setVisibility(View.GONE);
                        vRBreakfast.setVisibility(View.GONE);
                        CallBestService();
                        //CallSnackService();
                        //SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (apiType == WebServices.ApiType.snackList) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                snackListOut = (SnackListOut) response;

                if (!isSucces || snackListOut == null) {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (snackListOut != null) {
                        if (snackListOut.getFoodList() != null) {
                            vLSnacks.setVisibility(View.VISIBLE);
                            vRSnacks.setVisibility(View.VISIBLE);
                            homeSnackAdapter = new HomeSnackAdapter();
                            vRSnacks.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), 0, false));
                            vRSnacks.setAdapter(homeSnackAdapter);
                            //CallBreakfastService();
                            CallBestService();
                        } else {
                            vLSnacks.setVisibility(View.GONE);
                            vRSnacks.setVisibility(View.GONE);
                            //CallBreakfastService();
                            CallBestService();
                            //SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        vLSnacks.setVisibility(View.GONE);
                        vRSnacks.setVisibility(View.GONE);
                        //CallBreakfastService();
                        CallBestService();
                        //SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (apiType == WebServices.ApiType.addToCart) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                AddToCart cartListOut = (AddToCart) response;
                if (cartListOut != null) {
                    SnackBar.makeText(getActivity(), cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                    //menuAdapter.notifyDataSetChanged();
                } else {
                    SnackBar.makeText(getActivity(), cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
        if (apiType == WebServices.ApiType.sliderImages) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                sliderHomeList = (ResNewSlider) response;
                if (sliderHomeList != null) {
                    if (sliderHomeList.getSlider() != null) {
                        CustomPagerMainAdapter mAdapter = new CustomPagerMainAdapter(getActivity());
                        vVAhImage.setAdapter(mAdapter);
                        vCAhCircularIndicator.setSnap(true);
                        vCAhCircularIndicator.setFillColor(getResources().getColor(R.color.colorPrimary));
                        vCAhCircularIndicator.setStrokeColor(getResources().getColor(R.color.colorPrimary));
                        vCAhCircularIndicator.setRadius(8 * 1);
                        vCAhCircularIndicator.setViewPager(vVAhImage);
                        CallService();
                        /*llMain.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);*/
                    } else {
                        /*llMain.setVisibility(View.GONE);
                        llError.setVisibility(View.VISIBLE);*/
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                } else {
                    /*llMain.setVisibility(View.GONE);
                    llError.setVisibility(View.VISIBLE);*/
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        }
        if (apiType == WebServices.ApiType.mylocation) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                MyLocation myLocation = (MyLocation) response;
                if (myLocation != null) {
                    if (myLocation.getMessage() != null) {
                        llMain.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);
                        CallSliderService();
                        CallCityList();
                    } else {
                        llMain.setVisibility(View.GONE);
                        llError.setVisibility(View.VISIBLE);
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                } else {
                    llMain.setVisibility(View.GONE);
                    llError.setVisibility(View.VISIBLE);
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        } else if (apiType == WebServices.ApiType.citylist) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CityList cityList = (CityList) response;
                if (cityList != null) {
                    if (cityList.getCity() != null) {
                        for (int i=0; i<cityList.getCity().size(); i++){
                            if (cityList.getCity().get(i).getCityName().equals(city)){
                                Prefs.setCityId(getActivity(), cityList.getCity().get(i).getId());
                            }
                        }
                    } else {
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        if(distance.contains("km")){
                            String dis = distance.replace(" km", "");
                            CallAddService(dis);
                        } else {
                            CallAddService("1");
                        }
                    }

                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }

    }

    public class HomeRestaurantAdapter extends RecyclerView.Adapter<HomeRestaurantAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_restaurant_copy, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vT_adhg_name.setText(restaurantListOut.getResturant().get(position).getName());
            holder.vT_adhg_place.setText(restaurantListOut.getResturant().get(position).getLocation());
            holder.vT_adhg_style.setText(restaurantListOut.getResturant().get(position).getType());
            holder.vT_adhg_offer.setText(restaurantListOut.getResturant().get(position).getTiming());
            holder.vT_adhg_distance.setText(restaurantListOut.getResturant().get(position).getDeliveryTime());
            //Picasso.get().load(restaurantListOut.getResturant().get(position).getImage()).into(holder.vI_adhg_image);


            Picasso.get()
                    .load(WebServices.Foodey_Rest_Url + restaurantListOut.getResturant().get(position).getImage())
                    .into(holder.vI_adhg_image);

            holder.vC_adhg_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String s = restaurantListOut.getResturant().get(position).getTiming();
                        if(s.contains("to")){
                            String[] split = s.split("to");
                            firstSubString = split[0];
                            secondSubString = split[1];
                        }else if(s.contains("-")){
                            String[] split = s.split("-");
                            firstSubString = split[0];
                            secondSubString = split[1];
                        }
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
                            Log.d("result", "open");
                            if (restaurantListOut.getResturant().get(position).getStatus().equals("1")) {
                                Intent intent = new Intent(getActivity(), RestaurantDetailsActivityCopy.class);
                                intent.putExtra("from", "eat");
                                intent.putExtra("res_id", restaurantListOut.getResturant().get(position).getId());
                                startActivity(intent);
                                getActivity().overridePendingTransition(0, 0);
                            } else {
                                SnackBar.makeText(getActivity(), "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("result", "closed");
                            SnackBar.makeText(getActivity(), "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        public int getItemCount() {
            if (restaurantListOut.getResturant().size() > 3) {
                return 3;
            } else {
                return restaurantListOut.getResturant().size();
            }
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            CardView vC_adhg_card;
            ImageView vI_adhg_image;
            TextView vT_adhg_name, vT_adhg_place, vT_adhg_style, vT_adhg_offer, vT_adhg_distance;


            public MyViewHolder(View view) {
                super(view);
                this.vC_adhg_card = (CardView) view.findViewById(R.id.vC_adhg_card);
                this.vI_adhg_image = (ImageView) view.findViewById(R.id.vI_adhg_image);
                this.vT_adhg_name = (TextView) view.findViewById(R.id.vT_adhg_name);
                this.vT_adhg_place = (TextView) view.findViewById(R.id.vT_adhg_place);
                this.vT_adhg_style = (TextView) view.findViewById(R.id.vT_adhg_style);
                this.vT_adhg_offer = (TextView) view.findViewById(R.id.vT_adhg_offer);
                this.vT_adhg_distance = view.findViewById(R.id.vT_adhg_distance);
                new Runnable() {
                    public void run() {
                        vT_adhg_name.setTypeface(NIRMALA);
                        vT_adhg_place.setTypeface(NIRMALA);
                        vT_adhg_style.setTypeface(NIRMALA);
                        vT_adhg_offer.setTypeface(NIRMALA);
                        vT_adhg_distance.setTypeface(NIRMALAB);
                    }
                }.run();
            }
        }
    }

    public class HomeBreakfastAdapter extends RecyclerView.Adapter<HomeBreakfastAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_breakfast, parent, false));
        }

        public void onBindViewHolder(final HomeBreakfastAdapter.MyViewHolder holder, final int position) {
            quantBr = Integer.parseInt(holder.qty.getText().toString());
            holder.vT_adhr_name.setText(bestSelling.getFoodList().get(position).getFood().getName());
            price = "₹ " + bestSelling.getFoodList().get(position).getFood().getPrice();
            holder.vT_adhr_place.setText(price);
            holder.vT_adhr_loc.setText(bestSelling.getFoodList().get(position).getResturant().getName());

            Picasso.get()
                    .load(WebServices.Foodey_Food_Url + bestSelling.getFoodList().get(position).getFood().getImage())
                    .into(holder.vI_adhr_image);
            holder.vI_adhr_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(getActivity(), RestaurantDetailsActivityCopy.class);
                    intent.putExtra("from", "eat");
                    intent.putExtra("res_id", bestSelling.getFoodList().get(position).getFood().getResId());
                    startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);*/

                    try {
                        String s = bestSelling.getFoodList().get(position).getResturant().getTiming();
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
                            Log.d("result", "open");
                            if (bestSelling.getFoodList().get(position).getResturant().getStatus().equals("1")) {
                                Intent intent = new Intent(getActivity(), RestaurantDetailsActivityCopy.class);
                                intent.putExtra("from", "eat");
                                intent.putExtra("res_id", bestSelling.getFoodList().get(position).getResturant().getId());
                                startActivity(intent);
                                getActivity().overridePendingTransition(0, 0);
                            } else {
                                SnackBar.makeText(getActivity(), "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d("result", "closed");
                            SnackBar.makeText(getActivity(), "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.add_breakfast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.vL_count.setVisibility(View.VISIBLE);
                    holder.add_breakfast.setVisibility(View.GONE);
                    int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                    holder.qty.setText(String.valueOf(qaunt));

                    if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                        quantity = holder.qty.getText().toString();
                        res_id = bestSelling.getFoodList().get(position).getFood().getResId();
                        price = bestSelling.getFoodList().get(position).getFood().getPrice();
                        mrp = bestSelling.getFoodList().get(position).getFood().getMrp();
                        foodId = bestSelling.getFoodList().get(position).getFood().getId();
                        CallAdd(bestSelling.getFoodList().get(position).getFood().getLatitude(), bestSelling.getFoodList().get(position).getFood().getLongitude());
                    }

                    holder.qtyA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            quantity = holder.qty.getText().toString();
                            res_id = bestSelling.getFoodList().get(position).getFood().getResId();
                            price = bestSelling.getFoodList().get(position).getFood().getPrice();
                            mrp = bestSelling.getFoodList().get(position).getFood().getMrp();
                            foodId = bestSelling.getFoodList().get(position).getFood().getId();
                            CallAdd(bestSelling.getFoodList().get(position).getFood().getLatitude(), bestSelling.getFoodList().get(position).getFood().getLongitude());

                        }
                    });

                    holder.qtyM.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!holder.qty.getText().toString().equals("1")) {
                                int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                                holder.qty.setText(String.valueOf(qaunt));
                            } else {
                                SnackBar.makeText(getActivity(), "Quantity can not be less than 1", SnackBar.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            });

        }

        public int getItemCount() {

            return bestSelling.getFoodList().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_adhr_image, qtyM, qtyA;
            TextView vT_adhr_name, vT_adhr_place, vT_adhr_loc, vT_adhr_offer, vT_adhr_rating, qty;
            LinearLayout add_breakfast;
            LinearLayout vL_count;

            public MyViewHolder(View view) {
                super(view);
                this.vI_adhr_image = (ImageView) view.findViewById(R.id.vI_adhr_image_breakfast);
                this.vT_adhr_name = (TextView) view.findViewById(R.id.vT_adhr_name);
                this.vT_adhr_place = (TextView) view.findViewById(R.id.vT_adhr_place);
                this.add_breakfast = view.findViewById(R.id.vL_add_to_cart);
                this.vL_count = view.findViewById(R.id.vL_count);
                this.vT_adhr_loc = view.findViewById(R.id.vT_adhr_loc);
                this.qty = view.findViewById(R.id.qty);
                this.qtyA = view.findViewById(R.id.qtyA);
                this.qtyM = view.findViewById(R.id.qtyM);

                new Runnable() {
                    public void run() {
                        vT_adhr_name.setTypeface(NIRMALA);
                        vT_adhr_place.setTypeface(NIRMALA);
                    }
                }.run();
            }
        }
    }

    /*public class HomeOfferAdapter extends RecyclerView.Adapter<HomeOfferAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_offers_copy, parent, false));
        }

        public void onBindViewHolder(final HomeOfferAdapter.MyViewHolder holder, final int position) {

            holder.vT_adhr_name.setText(bestSelling.getFoodList().getFood().get(position).getName());
            holder.vT_adhr_place.setText(bestSelling.getFoodList().getFood().get(position).getCategory());
           // holder.vT_rest_name.setText(bestSelling.getFoodList().getFood().get(position).getR);
            Picasso.get()
                    .load(WebServices.Foodey_Food_Url + bestSelling.getFoodList().getFood().get(position).getImage())
                    .into(holder.vI_adhr_image);

            holder.vC_adhr_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        String s = restaurantListOut.getResturant().get(position).getTiming();
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
                            Log.d("result", "open");
                            if (restaurantListOut.getResturant().get(position).getStatus().equals("1")) {
                                Intent intent = new Intent(getActivity(), RestaurantDetailsActivityCopy.class);
                                intent.putExtra("from", "eat");
                                intent.putExtra("res_id",  bestSelling.getFoodList().getFood().get(position).getResId());
                                startActivity(intent);
                                getActivity().overridePendingTransition(0, 0);
                            } else {
                                SnackBar.makeText(getActivity(), "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d("result", "closed");
                            SnackBar.makeText(getActivity(), "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    Intent intent = new Intent(getActivity(), RestaurantDetailsActivityCopy.class);
//                    intent.putExtra("from", "eat");
//                    intent.putExtra("res_id", bestSelling.getFoodList().getFood().get(position).getResId());
//                    startActivity(intent);
//                    getActivity().overridePendingTransition(0, 0);
                }
            });

        }

        public int getItemCount() {

            return bestSelling.getFoodList().getFood().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            CardView vC_adhr_card;
            ImageView vI_adhr_image;
            TextView vT_adhr_name, vT_adhr_place,vT_rest_name;

            public MyViewHolder(View view) {
                super(view);
                this.vC_adhr_card = (CardView) view.findViewById(R.id.vC_adhr_card);
                this.vI_adhr_image = (ImageView) view.findViewById(R.id.vI_adhr_image_offer);
                this.vT_adhr_name = (TextView) view.findViewById(R.id.vT_adhr_name);
                this.vT_adhr_place = (TextView) view.findViewById(R.id.vT_adhr_place);
                this.vT_rest_name = (TextView) view.findViewById(R.id.vT_rest_name);

            }
        }
    }*/

    public class HomeSnackAdapter extends RecyclerView.Adapter<HomeSnackAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_res, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_adhr_name.setText(snackListOut.getFoodList().get(position).getFood().getName());
            price = "₹ " + snackListOut.getFoodList().get(position).getFood().getPrice();
            holder.vT_adhr_place.setText(price);
            //holder.vT_adhr_place.setText(snackListOut.getFoodList().get(position).getFood().getFoodTime());

            Picasso.get()
                    .load(WebServices.Foodey_Food_Url + snackListOut.getFoodList().get(position).getFood().getImage())
                    .into(holder.vI_adhr_image);

            holder.vI_adhr_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RestaurantDetailsActivityCopy.class);
                    intent.putExtra("from", "eat");
                    intent.putExtra("res_id", snackListOut.getFoodList().get(position).getFood().getResId());
                    startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);
                }
            });

            holder.add_breakfast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.vL_count.setVisibility(View.VISIBLE);
                    holder.add_breakfast.setVisibility(View.GONE);
                    int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                    holder.qty.setText(String.valueOf(qaunt));

                    if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                        quantity = holder.qty.getText().toString();
                        res_id = snackListOut.getFoodList().get(position).getFood().getResId();
                        price = snackListOut.getFoodList().get(position).getFood().getPrice();
                        mrp = snackListOut.getFoodList().get(position).getFood().getMrp();
                        foodId = snackListOut.getFoodList().get(position).getFood().getId();
                        CallAdd(snackListOut.getFoodList().get(position).getFood().getLatitude(), snackListOut.getFoodList().get(position).getFood().getLongitude());
                    }

                    holder.qtyA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            quantity = holder.qty.getText().toString();
                            res_id = snackListOut.getFoodList().get(position).getFood().getResId();
                            price = snackListOut.getFoodList().get(position).getFood().getPrice();
                            mrp = snackListOut.getFoodList().get(position).getFood().getMrp();
                            foodId = snackListOut.getFoodList().get(position).getFood().getId();
                            CallAdd(snackListOut.getFoodList().get(position).getFood().getLatitude(), snackListOut.getFoodList().get(position).getFood().getLongitude());

                        }
                    });

                    holder.qtyM.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!holder.qty.getText().toString().equals("1")) {
                                int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                                holder.qty.setText(String.valueOf(qaunt));
                            } else {
                                SnackBar.makeText(getActivity(), "Quantity can not be less than 1", SnackBar.LENGTH_SHORT).show();
                            }
                        }
                    });

                    /*holder.vL_count.setVisibility(View.VISIBLE);
                    holder.add_breakfast.setVisibility(View.GONE);
                    int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                    holder.qty.setText(String.valueOf(qaunt));

                    if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                        CallAddService(holder.qty.getText().toString(), snackListOut.getFoodList().get(position).getFood().getResId(), snackListOut.getFoodList().get(position).getFood().getPrice(),
                                snackListOut.getFoodList().get(position).getFood().getMrp(), snackListOut.getFoodList().get(position).getFood().getId());
                    } else {
                        SnackBar.makeText(getActivity(), "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                    }

                    holder.qtyA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            CallAddService(holder.qty.getText().toString(), snackListOut.getFoodList().get(position).getFood().getResId(), snackListOut.getFoodList().get(position).getFood().getPrice(),
                                    snackListOut.getFoodList().get(position).getFood().getMrp(), snackListOut.getFoodList().get(position).getFood().getId());


                        }
                    });*/

                }
            });

        }

        public int getItemCount() {
            //return snackListOut.getFoodDetails().size();
            if (snackListOut.getFoodList().size() > 5) {
                return 5;
            } else {
                return snackListOut.getFoodList().size();
            }
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_adhr_image, qtyM, qtyA;
            TextView vT_adhr_name, vT_adhr_place, vT_adhr_offer, vT_adhr_rating, qty;
            LinearLayout add_breakfast;
            LinearLayout vL_count;

            public MyViewHolder(View view) {
                super(view);
                this.vI_adhr_image = (ImageView) view.findViewById(R.id.vI_adhr_image_breakfast);
                this.vT_adhr_name = (TextView) view.findViewById(R.id.vT_adhr_name);
                this.vT_adhr_place = (TextView) view.findViewById(R.id.vT_adhr_place);
                this.add_breakfast = view.findViewById(R.id.vL_add_to_cart);
                this.vL_count = view.findViewById(R.id.vL_count);
                this.qty = view.findViewById(R.id.qty);
                this.qtyA = view.findViewById(R.id.qtyA);
                this.qtyM = view.findViewById(R.id.qtyM);

                new Runnable() {
                    public void run() {
                        vT_adhr_name.setTypeface(NIRMALA);
                        vT_adhr_place.setTypeface(NIRMALA);
                    }
                }.run();
            }
        }
    }

    class CustomPagerMainAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerMainAdapter(Context context) {
            this.mContext = context;
            mLayoutInflater = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        }

        public int getCount() {
            return sliderHomeList.getSlider().size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == ((CardView) object);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = this.mLayoutInflater.inflate(R.layout.adapter_banner_home, container, false);
            CardView vC_adbh_card = (CardView) itemView.findViewById(R.id.vC_adbh_card);
            ImageView vI_adbh_image = (ImageView) itemView.findViewById(R.id.vI_adbh_image);
            Picasso.get()
                    .load(WebServices.Foodey_Service_Img_Url + sliderHomeList.getSlider().get(position).getImage())
                    .into(vI_adbh_image);
            container.addView(itemView);
            return itemView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((CardView) object);
        }
    }

    private void fetchLastLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                return;
            } else {
                Task<Location> task = fusedLocationProviderClient.getLastLocation();
                task.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            try {
                                currentLocation = location;
                                lat = currentLocation.getLatitude();
                                lang = currentLocation.getLongitude();
                                lati = String.valueOf(lat);
                                longi = String.valueOf(lang);
                                Prefs.setUserLat(getActivity(), lati);
                                Prefs.setUserLang(getActivity(), longi);
                                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                if (addresses.size() != 0) {
                                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                    city = addresses.get(0).getLocality();
                                    CallMyLocationService(lati, longi);
                                } else {
                                    fetchLastLocation();
                                }

                                //CallSliderService();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        } else {
            GPSStatus();
        }
    }

    public void GPSStatus() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (GpsStatus) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        try {
                            currentLocation = location;
                            lat = currentLocation.getLatitude();
                            lang = currentLocation.getLongitude();
                            lati = String.valueOf(lat);
                            longi = String.valueOf(lang);
                            Prefs.setUserLat(getActivity(), lati);
                            Prefs.setUserLang(getActivity(), longi);
                            addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            city = addresses.get(0).getLocality();
                            CallMyLocationService(lati, longi);
                            //CallSliderService();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            new AlertDialog.Builder(getActivity()).setTitle("Location Permission").setMessage("Please enable location permission!")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent1);
                            dialog.dismiss();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    /*private double distance(double lat1, double lon1, double lat2, double lon2) {
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
    }*/

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /*public double distance(double lat1, double lon1, double lat2, double lon2) {
        int Radius = 6371;// radius of earth in Km
       *//* double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;*//*
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }*/

}
