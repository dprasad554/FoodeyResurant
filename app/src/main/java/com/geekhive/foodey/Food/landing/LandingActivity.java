package com.geekhive.foodey.Food.landing;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geekhive.foodey.Food.beans.newslider.ResNewSlider;
import com.geekhive.foodey.Food.beans.rslider.ResSlider;
import com.geekhive.foodey.Food.carousel.CarouselEffectTransformer;
import com.geekhive.foodey.Food.eatout.RestaurantDetailsActivity;
import com.geekhive.foodey.Food.service.ServiceActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.activities.CakeBottomNavigationActivity;
import com.geekhive.foodey.Food.ReferEarn.ReferandEarn;
import com.geekhive.foodey.Food.beans.citylist.CityList;
import com.geekhive.foodey.Food.beans.mylocation.MyLocation;
import com.geekhive.foodey.Food.beans.sliderhome.SliderHomeList;
import com.geekhive.foodey.Food.beans.sliderhome.SliderHomeListDown;
import com.geekhive.foodey.Food.home.HomeActivity;
import com.geekhive.foodey.Food.login.LoginActivity;
import com.geekhive.foodey.Food.more.WebViewActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.Food.wallet.WalletActivity;
import com.geekhive.foodey.Grocery.activities.GroceryBottomNavigationActivity;
import com.geekhive.foodey.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner, NavigationView.OnNavigationItemSelectedListener {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    @BindView(R.id.foodCard)
    LinearLayout foodCard;
    @BindView(R.id.groceryCard)
    LinearLayout groceryCard;
    @BindView(R.id.cakeCard)
    LinearLayout cakeCard;
    @BindView(R.id.servicesCard)
    LinearLayout servicesCard;
    @BindView(R.id.foodText)
    TextView foodText;
    @BindView(R.id.groceryText)
    TextView groceryText;
    @BindView(R.id.cakeText)
    TextView cakeText;
    @BindView(R.id.servicesText)
    TextView servicesText;


    @BindView(R.id.vV_ah_image)
    ViewPager vVAhImage;
    @BindView(R.id.vC_ah_circularIndicator)
    CirclePageIndicator vCAhCircularIndicator;

    @BindView(R.id.vV_ah_image_down)
    ViewPager vVAhImageDown;
    @BindView(R.id.vC_ah_circularIndicator_down)
    CirclePageIndicator vCAhCircularIndicatorDown;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView nav_view;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.vT_tl_location)
    TextView vTTlLocation;
    @BindView(R.id.vL_tl_location)
    LinearLayout vLTlLocation;

    ConnectionDetector mDetector;
    ResNewSlider sliderHomeList;
    ResSlider sliderHomeListDown;

    private int mCurNavigationId;
    TextView vlUserName;

    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    double lat;
    double lang;
    String lati;
    String longi;
    boolean GpsStatus;
    private static final int REQUEST_CODE = 101;
    private static final int REQUEST_CODE_FLEXI_UPDATE = 17362;
    AppUpdateManager mAppUpdateManager;

    List<Address> addresses;
    Geocoder geocoder;
    String city = "";
    Dialog Flashpopup;
    PlacesClient placesClient;
    int AUTOCOMPLETE_REQUEST_CODE = 0;

    NotificationManager notificationManager;
    Dialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        if (Prefs.getMobileNumber(this).equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
            LandingActivity.this.finish();
        } else {
            toolbar.setTitle("Foodey");

            DrawerLayout drawer = drawer_layout;
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer_layout.addDrawerListener(toggle);
            toggle.syncState();
            nav_view.setNavigationItemSelectedListener(this);
            vlUserName = nav_view.getHeaderView(0).findViewById(R.id.vL_username);

            initializeFonts();
            setTypeFace();
            setvalues();
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fetchLastLocation();
            CallSliderService();
            CallSliderDownService();

            mCurNavigationId = R.id.home;
        }


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
                foodText.setTypeface(NIRMALAB);
                groceryText.setTypeface(NIRMALAB);
                cakeText.setTypeface(NIRMALAB);
                groceryText.setTypeface(NIRMALAB);
                vTTlLocation.setTypeface(NIRMALA);

            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        foodCard.setOnClickListener(this);
        groceryCard.setOnClickListener(this);
        cakeCard.setOnClickListener(this);
        servicesCard.setOnClickListener(this);
        toolbar.setOnClickListener(this);
        drawer_layout.setOnClickListener(this);
        nav_view.setOnClickListener(this);

        vlUserName.setText(Prefs.getUserName(this));


        geocoder = new Geocoder(this, Locale.getDefault());
        //Map Current Location Work
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (Prefs.getUserLat(this).equals("")) {
            fetchLastLocation();
        } else {
            try {
                lat = Double.parseDouble(Prefs.getUserLat(this));
                lang = Double.parseDouble(Prefs.getUserLang(this));
                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                city = addresses.get(0).getLocality();
                vTTlLocation.setText(address);
                //CallMyLocationService(lati, longi);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        vTTlLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Places.initialize(getApplicationContext(), "AIzaSyB_RBQ5Le6REjSn35XB_f7_ufQmzETVOYY");
                // Create a new Places client instance.
                placesClient = Places.createClient(LandingActivity.this);


                final List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(LandingActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });
        //forceUpdate();
       /* mAppUpdateManager = AppUpdateManagerFactory.create(this);
        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new com.google.android.play.core.tasks.OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {

//                        if(appUpdateInfo.availableVersionCode() > currentVersionCode) {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

                    try {
                        mAppUpdateManager.startUpdateFlowForResult(
                                appUpdateInfo, AppUpdateType.IMMEDIATE, LandingActivity.this, REQUEST_CODE_FLEXI_UPDATE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }

                } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    LandingActivity.this.popupSnackbarForCompleteUpdate();
                    //new FetchAppVersionFromGooglePlayStore().execute();
                    forceUpdate();
                } else {
                    forceUpdate();
                    //new FetchAppVersionFromGooglePlayStore().execute();
                    Log.e("TAG", "checkForAppUpdateAvailability: something else");
                }
            }
        });*/

        /*mAppUpdateManager = AppUpdateManagerFactory.create(this);

        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){

                try {
                    mAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, AppUpdateType.FLEXIBLE, MainActivity.this, RC_APP_UPDATE);
                }

            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }

        } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
            popupSnackbarForCompleteUpdate();
        } else {
            Log.e(TAG, "checkForAppUpdateAvailability: something else");
        }
    });*/

    /*InstallStateUpdatedListener installStateUpdatedListener = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED){
                        popupSnackbarForCompleteUpdate();
                    } else if (state.installStatus() == InstallStatus.INSTALLED){
                        if (mAppUpdateManager != null){
                            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                        }

                    } else {
                        Log.i("Landing", "InstallStateUpdatedListener: state: " + state.installStatus());
                    }
                }
            };*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                //getPlace(place.getId());
                lat = place.getLatLng().latitude;
                lang = place.getLatLng().longitude;
                try {
                    addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    city = addresses.get(0).getLocality();
                    Prefs.setUserLat(LandingActivity.this, String.valueOf(lat));
                    Prefs.setUserLang(LandingActivity.this, String.valueOf(lang));
                    vTTlLocation.setText(address);
                    CallMyLocationService(String.valueOf(lat), String.valueOf(lang));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("AddAddress", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.e("AddAddress", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if (requestCode == REQUEST_CODE_FLEXI_UPDATE) {
            if (resultCode != RESULT_OK) {
                Log.e("TAG", "onActivityResult: app download failed");
            }
        }
    }

    private void fetchLastLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]
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
                                Prefs.setUserLat(LandingActivity.this, lati);
                                Prefs.setUserLang(LandingActivity.this, longi);
                                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                city = addresses.get(0).getLocality();
                                vTTlLocation.setText(address);
                                CallMyLocationService(lati, longi);
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
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (GpsStatus) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
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
                            Prefs.setUserLat(LandingActivity.this, lati);
                            Prefs.setUserLang(LandingActivity.this, longi);
                            addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            city = addresses.get(0).getLocality();
                            vTTlLocation.setText(address);
                            CallMyLocationService(lati, longi);
                            //CallMyLocationService(lati, longi);
                            //CallSliderService();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            new android.app.AlertDialog.Builder(this).setTitle("Location Permission").setMessage("Please enable location permission!")

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

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_FLEXI_UPDATE) {
            if (resultCode != RESULT_OK) {
                Log.e("TAG", "onActivityResult: app download failed");
            }
        }
    }*/

    private void CallSliderService() {
        if (this.mDetector.isConnectingToInternet()) {


            new WebServices(this).NewSlider(WebServices.Foodey_Services,
                    WebServices.ApiType.sliderImages);

            return;
        }

    }

    private void CallCityList() {
        if (this.mDetector.isConnectingToInternet()) {


            new WebServices(this).GetCityList(WebServices.Foodey_Services,
                    WebServices.ApiType.citylist);

            return;
        }

    }

    private void CallSliderDownService() {
        if (this.mDetector.isConnectingToInternet()) {


            new WebServices(this).ResSlider(WebServices.Foodey_Services,
                    WebServices.ApiType.sliderImagesDown);

            return;
        }

    }

    private void CallMyLocationService(String latitude, String longitude) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).UpdateUserLocation(WebServices.Foodey_Services,
                    WebServices.ApiType.mylocation, Prefs.getUserId(this), latitude, longitude);
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.sliderImages) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                sliderHomeList = (ResNewSlider) response;
                if (sliderHomeList != null) {
                    if (sliderHomeList.getSlider() != null) {
                        CustomPagerMainAdapter mAdapter = new CustomPagerMainAdapter(this);
                        vVAhImage.setAdapter(mAdapter);
                        vCAhCircularIndicator.setSnap(true);
                        vCAhCircularIndicator.setFillColor(getResources().getColor(R.color.colorPrimary));
                        vCAhCircularIndicator.setStrokeColor(getResources().getColor(R.color.colorPrimary));
                        vCAhCircularIndicator.setRadius(8 * 1);
                        vCAhCircularIndicator.setViewPager(vVAhImage);
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        } else if (apiType == WebServices.ApiType.citylist) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CityList cityList = (CityList) response;
                if (cityList != null) {
                    if (cityList.getCity() != null) {
                        for (int i = 0; i < cityList.getCity().size(); i++) {
                            if (cityList.getCity().get(i).getCityName().equals(city)) {
                                Prefs.setCityId(LandingActivity.this, cityList.getCity().get(i).getId());
                            }
                        }
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        } else if (apiType == WebServices.ApiType.sliderImagesDown) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                sliderHomeListDown = (ResSlider) response;
                if (sliderHomeListDown != null) {
                    if (sliderHomeListDown.getRSlider() != null) {
                        CustomPagerMainAdapterDown mAdapter = new CustomPagerMainAdapterDown(this);
                        vVAhImageDown.setAdapter(mAdapter);
                        vCAhCircularIndicatorDown.setSnap(true);
                        vCAhCircularIndicatorDown.setFillColor(getResources().getColor(R.color.colorPrimary));
                        vCAhCircularIndicatorDown.setStrokeColor(getResources().getColor(R.color.colorPrimary));
                        vCAhCircularIndicatorDown.setRadius(8 * 1);
                        vCAhCircularIndicatorDown.setViewPager(vVAhImageDown);
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        }
        if (apiType == WebServices.ApiType.mylocation) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                MyLocation myLocation = (MyLocation) response;
                if (myLocation != null) {
                    if (myLocation.getMessage() != null) {
                        Log.e("Landing Activity", "Location Updated");
                        CallCityList();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foodCard:
                startActivity(new Intent(LandingActivity.this, HomeActivity.class));
                LandingActivity.this.finish();
                break;
            case R.id.groceryCard:
                startActivity(new Intent(LandingActivity.this, GroceryBottomNavigationActivity.class));
                LandingActivity.this.finish();
//                new AlertDialog.Builder(this)
//                        .setTitle("Coming Soon!!!")
//                        //.setMessage("Are you sure you want to delete this entry?")
//
//                        // Specifying a listener allows you to take an action before dismissing the dialog.
//                        // The dialog is automatically dismissed when a dialog button is clicked.
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Continue with delete operation
//                                dialog.dismiss();
//                            }
//                        })
//
//                        // A null listener allows the button to dismiss the dialog and take no further action.
//                        .setNegativeButton(android.R.string.no, null)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();

                break;
            case R.id.cakeCard:
                startActivity(new Intent(LandingActivity.this, CakeBottomNavigationActivity.class));
                LandingActivity.this.finish();
                /*new AlertDialog.Builder(this)
                        .setTitle("Coming Soon!!!")
                        //.setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                dialog.dismiss();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();*/
                break;
            case R.id.servicesCard:
                Intent intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                /*new AlertDialog.Builder(this)
                        .setTitle("Coming Soon!!!")
                        //.setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                dialog.dismiss();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();*/
                /*Intent intent=new Intent(this, ServiceActivity.class);
                startActivity(intent);*/
                break;
        }
    }


    @Override
    public void onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_about) {
                /*Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("weburl", "http://foodeyservices.com/homes/privacy_policy");
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);*/
        } else if (id == R.id.nav_contact_us) {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:9010721212"));
            startActivity(intent);
            LandingActivity.this.overridePendingTransition(0, 0);

        } else if (id == R.id.nav_wallet) {
            Intent intentw = new Intent(this, WalletActivity.class);
            startActivity(intentw);
        } else if (id == R.id.nav_refer_earn) {
            Intent intenta = new Intent(this, ReferandEarn.class);
            intenta.putExtra("weburl", "http://foodeyservices.com/homes/privacy_policy");
            startActivity(intenta);
        } else if (id == R.id.nav_privacy) {
            Intent intenta = new Intent(this, WebViewActivity.class);
            intenta.putExtra("weburl", "http://foodeyservices.com/homes/privacy_policy");
            startActivity(intenta);
        } else if (id == R.id.nav_refund) {
            Intent intentr = new Intent(this, WebViewActivity.class);
            intentr.putExtra("weburl", "http://foodeyservices.com/homes/refund_policy");
            startActivity(intentr);
        } else if (id == R.id.nav_terms) {
            Intent intentt = new Intent(this, WebViewActivity.class);
            intentt.putExtra("weburl", "http://foodeyservices.com/homes/terms_conditions");
            startActivity(intentt);
        } else if (id == R.id.nav_disclaimer) {
            Intent intentd = new Intent(this, WebViewActivity.class);
            intentd.putExtra("weburl", "http://foodeyservices.com/homes/disclaimber");
            startActivity(intentd);
        } else if (id == R.id.nav_signout) {
            Prefs.setUserId(this, null);
            Prefs.setMobileNumber(this, null);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }


        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
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

    class CustomPagerMainAdapterDown extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerMainAdapterDown(Context context) {
            this.mContext = context;
            mLayoutInflater = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        }

        public int getCount() {
            return sliderHomeListDown.getRSlider().size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == ((CardView) object);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = this.mLayoutInflater.inflate(R.layout.adapter_banner_home_down, container, false);
            CardView vC_adbh_card = (CardView) itemView.findViewById(R.id.vC_adbh_card);
            ImageView vI_adbh_image = (ImageView) itemView.findViewById(R.id.vI_adbh_image);
            Picasso.get()
                    .load(WebServices.Foodey_Service_Img_Url + sliderHomeListDown.getRSlider().get(position).getImage())
                    .into(vI_adbh_image);
            container.addView(itemView);
            return itemView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((CardView) object);
        }
    }

    private void popupSnackbarForCompleteUpdate() {

        SnackBar.makeText(this, "This app's New version is ready!", SnackBar.LENGTH_SHORT).setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppUpdateManager != null) {
                    mAppUpdateManager.completeUpdate();
                }
            }
        }).show();
        /*SnackBar.makeText(
                findViewById(R.id.layout),
                "This app's New version is ready!",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAppUpdateManager != null) {
                    mAppUpdateManager.completeUpdate();
                }
            }
        });

        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        snackbar.show();*/
    }

    public void forceUpdate() {
        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String currentVersion = packageInfo.versionName;
        new ForceUpdateAsync(currentVersion, LandingActivity.this).execute();
    }

    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {

        private String latestVersion;
        private String currentVersion;
        private Context context;

        public ForceUpdateAsync(String currentVersion, Context context) {
            this.currentVersion = currentVersion;
            this.context = context;
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            try {
                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select(".hAyfc .htlgb")
                        .get(3)
                        .ownText();
                Log.e("latestversion", "---" + latestVersion);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (latestVersion != null) {
                if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                    // Toast.makeText(context,"update is available.",Toast.LENGTH_LONG).show();if(!(context instanceof LandingActivity)) {
                    if (!((Activity) context).isFinishing()) {
                        showForceUpdateDialog();
                    }

                }
            }
            super.onPostExecute(jsonObject);
        }

        public void showForceUpdateDialog() {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
        }

    }


    public BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            registerReceiver(myReceiver, new IntentFilter("FBR-IMAGE"));
            String action = intent.getAction();
            //endProgress();
            if (notificationManager != null)
                notificationManager.cancelAll(); //closes notification
            removeStickyBroadcast(intent);
            pushNotificationFood(intent);

            //changeUi(action);
        }
    };


    private void pushNotificationFood(Intent intent) {

        try {

            String message = intent.getExtras().getString("ms");
            if (!message.equals("")) {
                String ms = "http://" + message;
                Initializepopup();
                initializDialog(ms);
            } else {

                Log.e("Message", "No Message To Display");

            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //registerReceiver(broadcastReceiver, new IntentFilter(GoogleService.str_receiver));

        try {
            registerReceiver(myReceiver, new IntentFilter("FBR-IMAGE"));
        } catch (Exception e) {
            // already registered
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //unregisterReceiver(broadcastReceiver);

        try {
            unregisterReceiver(myReceiver);
        } catch (Exception e) {
            // already unregistered
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        if(passHandler!=null)
//            passHandler.removeCallbacksAndMessages(null);
        //stop location updates when Activity is no longer active
//        if (mGoogleApiClient != null) {
//            if(mGoogleApiClient.isConnected())
//                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }
    }


    private void Initializepopup() {
        Dialog = new Dialog(this);
        Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Dialog.setContentView(R.layout.splash_popup);
        Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
    }

    private void initializDialog(String image) {
        Dialog.setContentView(R.layout.splash_popup);
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
        Dialog.show();

        final ImageView close_splash = (ImageView) Dialog.findViewById(R.id.close_splash);
        final ImageView imgFlash = (ImageView) Dialog.findViewById(R.id.imgFlash);

        Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Dialog.getWindow().setGravity(Gravity.CENTER);

        Picasso.get()
                .load(image)
                .into(imgFlash);

        close_splash.setOnClickListener(new View.OnClickListener() {
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

}
