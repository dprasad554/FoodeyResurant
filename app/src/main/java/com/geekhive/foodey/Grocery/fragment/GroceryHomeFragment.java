package com.geekhive.foodey.Grocery.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.activities.GroceryBestOfferListActivity;
import com.geekhive.foodey.Grocery.activities.GroceryBestSellingListActivity;
import com.geekhive.foodey.Grocery.activities.GroceryCheckOutActivityNew;
import com.geekhive.foodey.Grocery.activities.GroceryRecentPurchaseListActivity;
import com.geekhive.foodey.Grocery.activities.GrocerySearchActivity;
import com.geekhive.foodey.Grocery.activities.GroceryShopByStoreActivity;
import com.geekhive.foodey.Grocery.adapter.Bestselling_Adapter;
import com.geekhive.foodey.Grocery.adapter.GroceryRecentAdapter;
import com.geekhive.foodey.Grocery.adapter.GroceryTopCategoryAdapter;
import com.geekhive.foodey.Grocery.beans.citylist.CityList;
import com.geekhive.foodey.Grocery.beans.groceryaddcart.GroceryAddToCart;
import com.geekhive.foodey.Grocery.beans.grocerybestselling.GroceryBestSelling;
import com.geekhive.foodey.Grocery.beans.groceryhomeslider.HomeSlider;
import com.geekhive.foodey.Grocery.beans.grocerymylocation.GroceryMyLocation;
import com.geekhive.foodey.Grocery.beans.groceryoffer.GroceryOffer;
import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.Grocery.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Grocery.beans.recentpurchase.RecentPurchase;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.LOCATION_SERVICE;

public class GroceryHomeFragment extends Fragment implements OnResponseListner {

    TextView category, cart, shop, viewAll, recently, best, viewAllNewLaunches, vT_viewAll_best;
    LinearLayout linearShop, linearTrack;
    Timer timer1;
    DotsIndicator dot1;
    ViewPager viewPager;
    HomeSliderViewPagerAdapter homePagerAdapter;
    int slide[] = {R.drawable.homeone, R.drawable.hometwo, R.drawable.homethree, R.drawable.homefour};
    ArrayList groceryImages = new ArrayList<>(Arrays.asList(R.drawable.productslidetwo, R.drawable.maggi, R.drawable.surf, R.drawable.pasta));
    ArrayList groceryOriginal = new ArrayList<>(Arrays.asList("Horlicks", "Maggi", "Surf Excel", "Pasta"));
    ArrayList producttitle = new ArrayList<>(Arrays.asList("Horlicks", "maggie", "surf", "Pasta"));
    RecyclerView shopRecyclerView, recentRecyclerView, bestRecyclerView, new_lunches, best_selling;
    ImageView iv_cart, search;
    ConnectionDetector mDetector;
    HomeSlider homeSlider;
    GroceryStoreNameList storeNameList;
    RecentPurchase recentPurchase;
    GroceryBestSelling groceryBestSelling;
    GroceryOffer groceryOffer;
    String store_id, product_id, item_count, price, mrp;
    EditText et_login;
    String name;
    RelativeLayout rl_recent;
    Geocoder geocoder;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Location currentLocation;
    double lat;
    double lang;
    String lati;
    String longi;
    List<Address> addresses;
    String city = "";
    boolean GpsStatus;

    LinearLayout main_layout, llError;
    LinearLayout bestOffers;
    LinearLayout bestSell;
    public GroceryHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grocery_fragment_home, container, false);

        category = view.findViewById(R.id.tv_Category);
        Typeface CustomCategory = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        category.setTypeface(CustomCategory);
        bestOffers = view.findViewById(R.id.bestOffers);
        bestSell = view.findViewById(R.id.bestSell);
        cart = view.findViewById(R.id.tv_store);
        Typeface CustomCart = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        cart.setTypeface(CustomCart);
        rl_recent = view.findViewById(R.id.rl_recent);
        shop = view.findViewById(R.id.tv_top);
        Typeface CustomShop = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        shop.setTypeface(CustomShop);
        et_login = view.findViewById(R.id.et_login);
        search = view.findViewById(R.id.search);
        viewAll = view.findViewById(R.id.tv_viewAllStore);
        vT_viewAll_best = view.findViewById(R.id.vT_viewAll_best);

        main_layout = (LinearLayout) view.findViewById(R.id.main_layout);
        llError = (LinearLayout) view.findViewById(R.id.llError);


        Typeface CustomView = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        viewAll.setTypeface(CustomView);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroceryShopByStoreActivity.class);
                startActivity(intent);
            }
        });
        //  viewAllNewLaunches = view.findViewById(R.id.vT_viewallLaunches);
        // Typeface CustomLuanches = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        //  viewAllNewLaunches.setTypeface(CustomLuanches);

//        viewAllNewLaunches.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), GroceryNewlyLaunchesListActivity.class);
//                startActivity(intent);
//
//            }
//        });


        recently = view.findViewById(R.id.tv_recently);
        Typeface CustomRecently = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        recently.setTypeface(CustomRecently);

        viewAll = view.findViewById(R.id.tv_viewAllRecent);
        Typeface CustomRView = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        viewAll.setTypeface(CustomRView);


        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroceryRecentPurchaseListActivity.class);
                intent.putExtra("store_id", store_id);
                startActivity(intent);
            }
        });

        best = view.findViewById(R.id.tv_best);
        Typeface CustomBest = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        best.setTypeface(CustomBest);

        viewAll = view.findViewById(R.id.tv_viewAllBest);
        Typeface CustomBView = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        viewAll.setTypeface(CustomBView);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroceryBestOfferListActivity.class);
                startActivity(intent);
            }
        });
        vT_viewAll_best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroceryBestSellingListActivity.class);
                startActivity(intent);


            }
        });


        linearShop = view.findViewById(R.id.linearCart);
        linearShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), AddCartActivity.class);
//                startActivity(intent);
            }
        });

        linearTrack = view.findViewById(R.id.linearShop);
        linearTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroceryShopByStoreActivity.class);
                startActivity(intent);
            }
        });

        //For Home Screen Slider
        dot1 = view.findViewById(R.id.dot1);
        viewPager = (ViewPager) view.findViewById(R.id.home_ViewPager);


        //For Store
        shopRecyclerView = (RecyclerView) view.findViewById(R.id.top_RecyclerView);
        LinearLayoutManager layoutShopManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        shopRecyclerView.setLayoutManager(layoutShopManager);


        //For Recent
        recentRecyclerView = (RecyclerView) view.findViewById(R.id.recent_RecyclerView);


        //For Best
        bestRecyclerView = (RecyclerView) view.findViewById(R.id.best_recyclerView);


        //new lunches
        // new_lunches = (RecyclerView) view.findViewById(R.id.new_lunches);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
//        new_lunches.setLayoutManager(gridLayoutManager);
//        NewlunchesAdapter newlunchesadapter = new NewlunchesAdapter(getActivity());
//        new_lunches.setAdapter(newlunchesadapter);

        best_selling = (RecyclerView) view.findViewById(R.id.best_selling);


        iv_cart = view.findViewById(R.id.iv_cart);
        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GroceryCheckOutActivityNew.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_login.getText().toString().equals("")) {
                    SnackBar.makeText(getActivity(), "Please enter your search", SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), GrocerySearchActivity.class);
                    intent.putExtra("name", et_login.getText().toString());
                    intent.putExtra("search", "search");
                    startActivity(intent);
                }

            }
        });
        setValues();

        return view;
    }

    private void setValues() {
        mDetector = new ConnectionDetector(getActivity());
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        //Map Current Location Work
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (Prefs.getUserLat(getActivity()).equals("")) {
            fetchLastLocation();
        } else {
            try {
                lat = Double.parseDouble(Prefs.getUserLat(getActivity()));
                lang = Double.parseDouble(Prefs.getUserLang(getActivity()));
                addresses = geocoder.getFromLocation(lat, lang, 1);
                if (addresses.size() != 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    city = addresses.get(0).getLocality();
                    CallMyLocationService(Prefs.getUserLat(getActivity()), Prefs.getUserLang(getActivity()));
                } else {

                    //fetchLastLocation();
                }
                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                /*String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                city = addresses.get(0).getLocality();*/
                CallMyLocationService(Prefs.getUserLat(getActivity()), Prefs.getUserLang(getActivity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                               /* addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                city = addresses.get(0).getLocality();*/
                                city = addresses.get(0).getLocality();
                                CallMyLocationService(lati, longi);
                                //CallSliderService();
                            } catch (Exception e) {
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
//                            addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                            city = addresses.get(0).getLocality();
                            /*addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            city = addresses.get(0).getLocality();*/
                            city = addresses.get(0).getLocality();
                            CallMyLocationService(lati, longi);
                            //CallSliderService();
                        } catch (Exception e) {
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

    private void CallMyLocationService(String latitude, String longitude) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).UpdateUserLocation(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.mylocation, Prefs.getUserId(getActivity()), latitude, longitude);
        }
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).HomeSlider(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.homeslider);
            return;
        }
    }

    private void CallOfferService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).OfferService(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.offerservice, Prefs.getUserId(getActivity()));
            return;
        }
    }

    private void CallResentService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).Recentservice(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.recentservice, Prefs.getUserId(getActivity()));
            return;
        }
    }

    private void CallStoreService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).StoreName(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.storename, Prefs.getUserId(getActivity()));
            return;
        }
    }

    private void CallAddtocartService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).AddToCart(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.addtocart, Prefs.getUserId(getActivity()), store_id, product_id, item_count, price, mrp, distance, Prefs.getCityId(getActivity()));

        } else {
            SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
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
            //CallService();
        } else {
            SnackBar.makeText(getActivity(), "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    private void CallBestSellingService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).BestSellingservice(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.bestsellingservice, Prefs.getUserId(getActivity()));
            return;
        }
    }

    private void CallCityList() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GetCityList(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.citylist);
            return;
        }
    }


    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.mylocation) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Food.utils.SnackBar.LENGTH_SHORT).show();
            } else {
                GroceryMyLocation myLocation = (GroceryMyLocation) response;
                if (myLocation != null) {
                    if (myLocation.getMessage() != null) {
                        CallService();
                        CallCityList();
                        main_layout.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);
                    } else {
                        CallService();
                        main_layout.setVisibility(View.GONE);
                        llError.setVisibility(View.VISIBLE);
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                } else {
                    CallService();
                    main_layout.setVisibility(View.GONE);
                    llError.setVisibility(View.VISIBLE);
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
        if (apiType == WebServices.ApiType.homeslider) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                homeSlider = (HomeSlider) response;
                if (homeSlider.getGSlider() != null) {
                    homePagerAdapter = new HomeSliderViewPagerAdapter(getActivity());
                    viewPager.setAdapter(homePagerAdapter);
                    dot1.setViewPager(viewPager);

                    //For First Slider Timer
                    TimerTask timerTask1 = new TimerTask() {
                        @Override
                        public void run() {
                            viewPager.post(new Runnable() {
                                @Override
                                public void run() {
                                    viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % slide.length);
                                }
                            });
                        }
                    };

                    timer1 = new Timer();
                    timer1.schedule(timerTask1, 4800, 4800);


                    CallStoreService();
                    main_layout.setVisibility(View.VISIBLE);
                    llError.setVisibility(View.GONE);
                } else {
                    CallStoreService();
                    main_layout.setVisibility(View.GONE);
                    llError.setVisibility(View.VISIBLE);
                }

            }
        }
        if (apiType == WebServices.ApiType.storename) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                storeNameList = (GroceryStoreNameList) response;
                if (storeNameList.getStore() != null) {
                    if (storeNameList.getStore().size() != 0) {
                        GroceryTopCategoryAdapter topAdapter = new GroceryTopCategoryAdapter(getActivity(), storeNameList);
                        shopRecyclerView.setAdapter(topAdapter);
                        CallResentService();
                        main_layout.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);

                    } else {
                        CallResentService();
                        main_layout.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);
                    }
                } else {
                    CallResentService();
                    main_layout.setVisibility(View.GONE);
                    llError.setVisibility(View.VISIBLE);
                }
            }
        }
        if (apiType == WebServices.ApiType.recentservice) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                recentPurchase = (RecentPurchase) response;
                if (recentPurchase.getRecentPurchase() != null) {

                    if (recentPurchase.getRecentPurchase().getGCartDetail().size() != 0) {
                        if (recentPurchase.getRecentPurchase().getGCartDetail().get(0).size() != 0) {
                            rl_recent.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutRecentManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            recentRecyclerView.setLayoutManager(layoutRecentManager);
                            GroceryRecentAdapter recentAdapter = new GroceryRecentAdapter(getActivity(), recentPurchase);
                            recentRecyclerView.setAdapter(recentAdapter);
                            CallOfferService();
                            main_layout.setVisibility(View.VISIBLE);
                            llError.setVisibility(View.GONE);
                        } else {
                            CallOfferService();
                            rl_recent.setVisibility(View.GONE);
                            main_layout.setVisibility(View.VISIBLE);
                            llError.setVisibility(View.GONE);
                        }
                    } else {
                        CallOfferService();
                        rl_recent.setVisibility(View.GONE);
                        main_layout.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);
                    }
                } else {
                    CallOfferService();
                    main_layout.setVisibility(View.GONE);
                    llError.setVisibility(View.VISIBLE);
                }
            }
        }
        if (apiType == WebServices.ApiType.offerservice) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                groceryOffer = (GroceryOffer) response;
                if (groceryOffer.getOfferDetails() != null) {
                    if (groceryOffer.getOfferDetails().size() != 0) {
                        LinearLayoutManager layoutBestManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        bestRecyclerView.setLayoutManager(layoutBestManager);
                        GroceryBestOfferAdapter BestAdapter = new GroceryBestOfferAdapter(getActivity(), groceryOffer);
                        bestRecyclerView.setAdapter(BestAdapter);
                        CallBestSellingService();
                        main_layout.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);
                        bestOffers.setVisibility(View.VISIBLE);
                    } else {
                        bestOffers.setVisibility(View.GONE);
                        CallBestSellingService();
                        main_layout.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);
                    }
                } else {
                    if (!groceryOffer.getMessage().equals("") || !groceryOffer.getMessage().isEmpty()){
                        CallBestSellingService();
                        bestOffers.setVisibility(View.GONE);
                        main_layout.setVisibility(View.VISIBLE);
                        llError.setVisibility(View.GONE);
                    } else {
                        CallBestSellingService();
                        bestOffers.setVisibility(View.GONE);
                        main_layout.setVisibility(View.GONE);
                        llError.setVisibility(View.VISIBLE);
                    }

                }
            }
        }
        if (apiType == WebServices.ApiType.bestsellingservice) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                groceryBestSelling = (GroceryBestSelling) response;
                if (groceryBestSelling.getGroceryList() != null) {
                    if (groceryBestSelling.getGroceryList().getGrocery().size() != 0) {
                        LinearLayoutManager layoutSellingManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        best_selling.setLayoutManager(layoutSellingManager);
                        Bestselling_Adapter bestselling_adapter = new Bestselling_Adapter(getActivity(), groceryBestSelling);
                        best_selling.setAdapter(bestselling_adapter);
                        bestSell.setVisibility(View.VISIBLE);
                    } else {
                        bestSell.setVisibility(View.GONE);
                    }
                } else {
                    bestSell.setVisibility(View.GONE);
                }
            }
        }

        if (apiType == WebServices.ApiType.addtocart) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final GroceryAddToCart addToCart = (GroceryAddToCart) response;
                if (!isSuccess || addToCart == null) {
                    SnackBar.makeText(getActivity(), "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(getActivity(), "Item added in cart", SnackBar.LENGTH_SHORT).show();
                }
            }
        }
//        if (apiType == WebServices.ApiType.mylocation) {
//            if (!isSuccess) {
//                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Food.utils.SnackBar.LENGTH_SHORT).show();
//            } else {
//                GroceryMyLocation myLocation = (GroceryMyLocation) response;
//                if (myLocation != null) {
//                    if (myLocation.getMessage() != null) {
//                        CallService();
//                        main_layout.setVisibility(View.VISIBLE);
//                        llError.setVisibility(View.GONE);
//                    } else {
//                        CallService();
//                        main_layout.setVisibility(View.GONE);
//                        llError.setVisibility(View.VISIBLE);
//                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
//                    }
//                } else {
//                    CallService();
//                    main_layout.setVisibility(View.GONE);
//                    llError.setVisibility(View.VISIBLE);
//                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
//                }
//            }
//        }
        if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        String dis = distance.replace(" km", "");
                        CallAddtocartService(dis);
                    }

                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.citylist) {
            if (!isSuccess) {
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
        }
    }

    class HomeSliderViewPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public HomeSliderViewPagerAdapter(Context context) {
            this.mContext = context;
            mLayoutInflater = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        }

        public int getCount() {
            return homeSlider.getGSlider().size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = this.mLayoutInflater.inflate(R.layout.grocery_layout_homeslider, container, false);
            ImageView image = (ImageView) itemView.findViewById(R.id.iv_homeSlider);
            String imageName = WebServices.Foodey_Grocery_Image_URL + homeSlider.getGSlider().get(position).getImage();
            Picasso.get()
                    .load(imageName)
                    .into(image);
            container.addView(itemView);
            return itemView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    public class GroceryBestOfferAdapter extends RecyclerView.Adapter<GroceryBestOfferAdapter.ViewHolder> {

        //Variables
        private Context mcontext;
        GroceryOffer groceryOffer;

        public GroceryBestOfferAdapter(Context context, GroceryOffer groceryOffer) {
            this.mcontext = context;
            this.groceryOffer = groceryOffer;
        }

        @NonNull
        @Override
        public GroceryBestOfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_bestoffer, parent, false);
            return new GroceryBestOfferAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.groceryTitle.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getName());
            // holder.groceryOriginal.setText("MRP : "+groceryOffer.getGroceryOffer().get(position).getGrocery().getMrp());
            /// holder.tv_productQty.setText(groceryOffer.getGroceryOffer().get(position).getGrocery().getQuantity()+
            //groceryOffer.getGroceryOffer().get(position).getGrocery().getQuantityDetail());
            //holder.productNows.setText("Rs : "+groceryOffer.getGroceryOffer().get(position).getGrocery().getPrice());
            holder.tv_productCuts.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getDiscount() + " OFF");
            holder.tv_productQty.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getDetail());
            String url = WebServices.Foodey_Offer_Url +
                    groceryOffer.getOfferDetails().get(position).getGroceryOffer().getImage();
            if (!url.equals("")) {
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.vegImage);
            }
            holder.btn_addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!groceryOffer.getOfferDetails().get(position).getGroceryOffer().getId().equals(null)) {
                        /*store_id = groceryOffer.getOfferDetails().get(position).getGroceryOffer().getStoreId();
                        product_id = groceryOffer.getOfferDetails().get(position).getGroceryOffer().getId();
                        item_count = "1";
                        price = groceryOffer.getOfferDetails().get(position).getGroceryOffer().ge();
                        mrp = groceryOffer.getOfferDetails().get(position).getGroceryOffer().getMrp();*/
                        /*CallAdd(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getLatitude(),
                                groceryOffer.getOfferDetails().get(position).getGroceryOffer().getLongitude());*/
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return groceryOffer.getOfferDetails().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView vegImage;
            TextView groceryTitle, groceryOriginal, tv_productQty, productNows, tv_productCuts;
            CardView cardView;
            Button btn_addCart;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                vegImage = (ImageView) itemView.findViewById(R.id.iv_bestImage);
                groceryTitle = itemView.findViewById(R.id.tv_productTitle);
                groceryOriginal = itemView.findViewById(R.id.tv_productOriginals);
                groceryOriginal.setText("Rs 200");
                groceryOriginal.setPaintFlags(groceryOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                cardView = (CardView) itemView.findViewById(R.id.card_viewMain);
                tv_productQty = itemView.findViewById(R.id.tv_productQty);
                productNows = itemView.findViewById(R.id.productNows);
                tv_productCuts = itemView.findViewById(R.id.tv_productCuts);
                btn_addCart = itemView.findViewById(R.id.btn_addCart);

            }
        }
    }
}
