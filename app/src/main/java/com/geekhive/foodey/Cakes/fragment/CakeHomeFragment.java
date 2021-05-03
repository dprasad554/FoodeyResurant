package com.geekhive.foodey.Cakes.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.geekhive.foodey.Cakes.activities.CakeShopStoreActivity;
import com.geekhive.foodey.Cakes.activities.CakeShopSubCategoryActivity;
import com.geekhive.foodey.Cakes.adapter.CakeBestOfferAdapter;
import com.geekhive.foodey.Cakes.adapter.CakeSliderImageAdapter;
import com.geekhive.foodey.Cakes.adapter.CakeSliderOfferAdapter;
import com.geekhive.foodey.Cakes.adapter.CakeTopCategoryAdapter;
import com.geekhive.foodey.Cakes.adapter.SpeciallistAdapter;
import com.geekhive.foodey.Cakes.beans.bestselling.BestSellingProduct;
import com.geekhive.foodey.Cakes.beans.cakecategories.CakeAllCategores;
import com.geekhive.foodey.Cakes.beans.cakemylocation.CakeMyLocation;
import com.geekhive.foodey.Cakes.beans.cakeoffer.CakeallOffer;
import com.geekhive.foodey.Cakes.beans.cakeslider.Cakeslider;
import com.geekhive.foodey.Cakes.beans.cakestore.CakeStoreList;
import com.geekhive.foodey.Cakes.beans.citylist.CityList;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.LOCATION_SERVICE;

public class CakeHomeFragment extends Fragment implements OnResponseListner {

    CakeSliderImageAdapter sliderImageAdapter;
    CakeSliderOfferAdapter sliderOfferAdapter;
    ViewPager viewPager, viewPager2;
    DotsIndicator dot1, dot2;
    Timer timer1;
    int slide[] = {R.drawable.cake1, R.drawable.cake2, R.drawable.cake3, R.drawable.cake4,};
    int slide2[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,};
    RecyclerView shopRecyclerView, recentRecyclerView, bestRecyclerView, vR_breakfast, special_recyclerView;
    LinearLayout linearStore, linearCategory;
    TextView specialCake, viewspecial;
    ConnectionDetector mDetector;

    List<Address> addresses;
    HomeOfferAdapter homeOfferAdapter;
    Geocoder geocoder;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    double lat;
    double lang;
    String lati;
    String longi;
    String city = "";
    boolean GpsStatus;

    ImageView search;
    Location currentLocation;
    LinearLayout main_layout, llError;

    LinearLayout vL_offer;
    RelativeLayout vr_new_launch;
    RelativeLayout vr_trending;

    public CakeHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cake_fragment_home, container, false);

        /*Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));*/

        dot1 = view.findViewById(R.id.dot1);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        vL_offer = view.findViewById(R.id.vL_offer);
        vr_new_launch = view.findViewById(R.id.vr_new_launch);
        vr_trending = view.findViewById(R.id.vr_trending);

        shopRecyclerView = (RecyclerView) view.findViewById(R.id.top_RecyclerView);
        viewspecial = (TextView) view.findViewById(R.id.tv_viewAllStore);
        main_layout = (LinearLayout) view.findViewById(R.id.main_layout);
        llError = (LinearLayout) view.findViewById(R.id.llError);
       /* Typeface myCustomFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/fonts/JosefinSans-Bold.ttf");
        viewspecial.setTypeface(myCustomFont);*/
        viewspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CakeShopStoreActivity.class);
                startActivity(intent);
            }
        });

        specialCake = (TextView) view.findViewById(R.id.tv_top);

        //For Best
        bestRecyclerView = (RecyclerView) view.findViewById(R.id.best_recyclerView);


        /*linearStore = view.findViewById(R.id.linearStore);
        linearStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CakeShopStoreActivity.class);
                startActivity(intent);
            }
        });*/


        vR_breakfast = (RecyclerView) view.findViewById(R.id.vR_breakfast);

        special_recyclerView = (RecyclerView) view.findViewById(R.id.speciallist_recyclerView);

        /*linearCategory = view.findViewById(R.id.linearCategory);
        linearCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CakeCheckOutActivityNew.class);
                startActivity(intent);
            }
        });*/

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

                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                city = addresses.get(0).getLocality();
                CallMyLocationService(Prefs.getUserLat(getActivity()), Prefs.getUserLang(getActivity()));
                if (addresses.size() != 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    city = addresses.get(0).getLocality();
                    CallMyLocationService(Prefs.getUserLat(getActivity()), Prefs.getUserLang(getActivity()));
                } else {

                    //fetchLastLocation();
                }

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
                                //                              addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                                city = addresses.get(0).getLocality();
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

    private void CallMyLocationService(String latitude, String longitude) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).UpdateUserLocation(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.mylocation, Prefs.getUserId(getActivity()), latitude, longitude);
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
                            //                          addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                            city = addresses.get(0).getLocality();
                           /* addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            city = addresses.get(0).getLocality();*/
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

    private void CallSliderService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).Cakeslider(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cakeslider);
            return;
        }

    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).CakeStore(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cakestore, Prefs.getUserId(getActivity()));
            return;
        }
    }

    private void CallOfferService() {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).CakeOffer(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cakeoffer, Prefs.getUserId(getActivity()));
            return;
        }

    }

    private void CallBestsellingService() {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).CakeBestSelling(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cakebestselling, Prefs.getUserId(getActivity()));
            return;
        }

    }

    private void CallCakecategoryService() {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).CakeCategories(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cakecategorylist);
            return;
        }

    }

    private void CallCityList() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GetCityList(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.citylist);
            return;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.mylocation) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Food.utils.SnackBar.LENGTH_SHORT).show();
            } else {
                CakeMyLocation myLocation = (CakeMyLocation) response;
                if (myLocation != null) {
                    if (myLocation.getMessage() != null) {
                        CallSliderService();
                        CallCityList();
                    } else {
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Grocery.custom.SnackBar.LENGTH_SHORT).show();
                    }
                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Grocery.custom.SnackBar.LENGTH_SHORT).show();
                }

            }
        }
        if (apiType == WebServices.ApiType.cakeslider) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final Cakeslider cakeslider = (Cakeslider) response;
                if (cakeslider.getCSlider() != null) {
                    //For First Slide
                    sliderImageAdapter = new CakeSliderImageAdapter(getActivity(), cakeslider);
                    viewPager.setAdapter(sliderImageAdapter);
                    dot1.setViewPager(viewPager);


                    //For First Slider Timmer
                    TimerTask timerTask1 = new TimerTask() {
                        @Override
                        public void run() {
                            viewPager.post(new Runnable() {

                                @Override
                                public void run() {
                                    viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % cakeslider.getCSlider().size());
                                }
                            });
                        }
                    };

                    timer1 = new Timer();
                    timer1.schedule(timerTask1, 3400, 3400);
                    CallService();
                } else {
                    CallService();
                    //CallService();
                }
            }

        }
        if (apiType == WebServices.ApiType.cakestore) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeStoreList cakeStoreList = (CakeStoreList) response;
                if (cakeStoreList.getCakeStore() != null) {
                    if (cakeStoreList.getCakeStore().size() != 0) {
                        Collections.reverse(cakeStoreList.getCakeStore());
                        GridLayoutManager layoutShopManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
                        layoutShopManager.setAutoMeasureEnabled(true);
                        shopRecyclerView.setLayoutManager(layoutShopManager);
                        CakeTopCategoryAdapter topAdapter = new CakeTopCategoryAdapter(getActivity(), cakeStoreList);
                        shopRecyclerView.setAdapter(topAdapter);
                        CallOfferService();
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
        if (apiType == WebServices.ApiType.cakeoffer) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeallOffer cakeallOffer = (CakeallOffer) response;
                if (cakeallOffer.getOfferDetails() != null) {
                    vR_breakfast.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
                    homeOfferAdapter = new HomeOfferAdapter(getActivity(), cakeallOffer);
                    vR_breakfast.setAdapter(homeOfferAdapter);
                    vL_offer.setVisibility(View.VISIBLE);
                    CallBestsellingService();
                } else {
                    vL_offer.setVisibility(View.GONE);
                    CallBestsellingService();
                }
            }
        }
        if (apiType == WebServices.ApiType.cakebestselling) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Food.utils.SnackBar.LENGTH_SHORT).show();
            } else {
                BestSellingProduct bestSellingProduct = (BestSellingProduct) response;
                if (bestSellingProduct != null) {
                    if (bestSellingProduct.getCakeList() != null) {
                        LinearLayoutManager layoutBestManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        bestRecyclerView.setLayoutManager(layoutBestManager);
                        CakeBestOfferAdapter BestAdapter = new CakeBestOfferAdapter(getActivity(), bestSellingProduct);
                        bestRecyclerView.setAdapter(BestAdapter);
                        vr_new_launch.setVisibility(View.VISIBLE);
                        CallCakecategoryService();


                    } else {
                        vr_new_launch.setVisibility(View.GONE);
                        CallCakecategoryService();
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Grocery.custom.SnackBar.LENGTH_SHORT).show();
                    }
                } else {
                    CallCakecategoryService();
                    //startActivity(new Intent(getActivity(), CakeBottomNavigationActivity.class));
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Grocery.custom.SnackBar.LENGTH_SHORT).show();
                }
            }
        }
        if (apiType == WebServices.ApiType.cakecategorylist) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Food.utils.SnackBar.LENGTH_SHORT).show();
            } else {
                CakeAllCategores cakeAllCategores = (CakeAllCategores) response;
                if (cakeAllCategores != null) {
                    if (cakeAllCategores.getCakeCategory() != null) {
                        LinearLayoutManager layoutShopManager = new LinearLayoutManager(getActivity(),GridLayoutManager.HORIZONTAL, false);
                        layoutShopManager.setAutoMeasureEnabled(true);
                        special_recyclerView.setLayoutManager(layoutShopManager);
                        SpeciallistAdapter specialistAdapter = new SpeciallistAdapter(getActivity(), cakeAllCategores);
                        special_recyclerView.setAdapter(specialistAdapter);
                        vr_trending.setVisibility(View.VISIBLE);
                    } else {
                        vr_trending.setVisibility(View.GONE);
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Grocery.custom.SnackBar.LENGTH_SHORT).show();
                    }
                } else {
                    //startActivity(new Intent(getActivity(), CakeBottomNavigationActivity.class));
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), com.geekhive.foodey.Grocery.custom.SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.citylist) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CityList cityList = (CityList) response;
                if (cityList != null) {
                    if (cityList.getCity() != null) {
                        for (int i = 0; i < cityList.getCity().size(); i++) {
                            if (cityList.getCity().get(i).getCityName().equals(city)) {
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

    public class HomeOfferAdapter extends RecyclerView.Adapter<HomeOfferAdapter.MyViewHolder> {
        CakeallOffer cakeoffer;
        private Context mcontext;

        public HomeOfferAdapter(Context context, CakeallOffer cakeoffer) {
            this.mcontext = context;
            this.cakeoffer = cakeoffer;
        }

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_home_offers, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            String url;
            url = "http://bado.dsoftveda.com/img/offer/" + cakeoffer.getOfferDetails().get(position).getCakeOffer().getImage();
            holder.vT_adhr_offer.setText(cakeoffer.getOfferDetails().get(position).getCakeOffer().getDiscount() + " % Discount ");
            if (!url.equals("")) {
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.vI_adhr_image);
            }
            holder.vI_adhr_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mcontext, CakeShopSubCategoryActivity.class);
                    intent.putExtra("url", "http://bado.dsoftveda.com/img/offer/" + cakeoffer.getOfferDetails().get(position).getCakeOffer().getImage());
                    intent.putExtra("title", cakeoffer.getOfferDetails().get(position).getCakeStore().getName());
                    intent.putExtra("storeid", cakeoffer.getOfferDetails().get(position).getCakeStore().getId());
                    intent.putExtra("lati", cakeoffer.getOfferDetails().get(position).getCakeStore().getLatitude());
                    intent.putExtra("longi", cakeoffer.getOfferDetails().get(position).getCakeStore().getLongitude());
                    mcontext.startActivity(intent);

                    /*Intent intent = new Intent(mcontext, CakeDetailActivity.class);
                    intent.putExtra("cakeName", cakeoffer.getCakeOffer().get(position).getCake().getProductName());
                    intent.putExtra("quantity", cakeoffer.getCakeOffer().get(position).getCake().getQuantity()+
                            cakeoffer.getCakeOffer().get(position).getCake().getQuantityDetail());
                    intent.putExtra("price", cakeoffer.getCakeOffer().get(position).getCake().getPrice());
                    intent.putExtra("description", cakeoffer.getCakeOffer().get(position).getCake().getProductDescription());
                    intent.putExtra("url",WebServices.Foodey_Grocery_Image_URL + cakeoffer.getCakeOffer().get(position).getCake().getImage());
                    intent.putExtra("mrp", cakeoffer.getCakeOffer().get(position).getCake().getMrp());
                    intent.putExtra("discount", cakeoffer.getCakeOffer().get(position).getCake().getDiscount());
                    intent.putExtra("store_id", cakeoffer.getCakeOffer().get(position).getCake().getStoreId());
                    intent.putExtra("product_id", cakeoffer.getCakeOffer().get(position).getCake().getId());
                    intent.putExtra("category", cakeoffer.getCakeOffer().get(position).getCake().getProductCategory());
                    mcontext.startActivity(intent);*/
                }
            });
        }

        public int getItemCount() {

            return cakeoffer.getOfferDetails().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            CardView vC_adhr_card;
            ImageView vI_adhr_image;
            TextView vT_adhr_name, vT_adhr_place, vT_adhr_offer;

            public MyViewHolder(View view) {
                super(view);
                this.vC_adhr_card = (CardView) view.findViewById(R.id.vC_adhr_card);
                this.vI_adhr_image = (ImageView) view.findViewById(R.id.vI_adhr_image);
                this.vT_adhr_name = (TextView) view.findViewById(R.id.vT_adhr_name);
                this.vT_adhr_place = (TextView) view.findViewById(R.id.vT_adhr_place);
                this.vT_adhr_offer = (TextView) view.findViewById(R.id.vT_adhr_offer);
            }
        }
    }

}
