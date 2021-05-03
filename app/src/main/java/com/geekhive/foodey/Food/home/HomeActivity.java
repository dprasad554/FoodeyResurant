package com.geekhive.foodey.Food.home;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.GlobalSearch.SearchActivity;
import com.geekhive.foodey.Food.landing.LandingActivity;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.booking.UpcomingFragment;
import com.geekhive.foodey.Food.orderfood.CheckOutActivityNew;
import com.geekhive.foodey.Food.orderhistory.OrderHistoryFragment;
import com.geekhive.foodey.Food.profile.ProfileFragment;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String PREFS_NAME = "splash_message_screen";
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_tl_back)
    ImageView vITlBack;
    @BindView(R.id.vT_tl_header)
    TextView vTTlHeader;
    @BindView(R.id.vT_tl_location)
    TextView vTTlLocation;
    @BindView(R.id.vL_tl_location)
    LinearLayout vLTlLocation;
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
    @BindView(R.id.vL_toolbarLayout)
    LinearLayout vLToolbarLayout;
    @BindView(R.id.vV_aol_category_viewpager)
    ViewPager vVAolCategoryViewpager;
    @BindView(R.id.vI_gl_search)
    ImageView vIGlSearch;



  /*  @BindView(R.id.vV_aol_sliding_tabs)
    TabLayout vVAolSlidingTabs;*/

    Fragment fragment;
    @BindView(R.id.vI_ah_homeImage)
    ImageView vIAhHomeImage;
    @BindView(R.id.vT_ah_homeText)
    TextView vTAhHomeText;
    @BindView(R.id.vL_ah_homeLayout)
    LinearLayout vLAhHomeLayout;
    @BindView(R.id.vI_ah_bookingImage)
    ImageView vIAhBookingImage;
    @BindView(R.id.vT_ah_bookingText)
    TextView vTAhBookingText;
    @BindView(R.id.vL_ah_bookingLayout)
    LinearLayout vLAhBookingLayout;
    @BindView(R.id.vI_ah_orderImage)
    ImageView vIAhOrderImage;
    @BindView(R.id.vT_ah_orderText)
    TextView vTAhOrderText;
    @BindView(R.id.vL_ah_orderLayout)
    LinearLayout vLAhOrderLayout;
    @BindView(R.id.vI_ah_profileImage)
    ImageView vIAhProfileImage;
    @BindView(R.id.vT_ah_profileext)
    TextView vTAhProfileext;
    @BindView(R.id.vL_ah_profileLayout)
    LinearLayout vLAhProfileLayout;
    @BindView(R.id.vV_ah_home)
    View vVAhHome;
    @BindView(R.id.vV_ah_booking)
    View vVAhBooking;
    @BindView(R.id.vV_ah_order)
    View vVAhOrder;
    @BindView(R.id.vV_ah_profile)
    View vVAhProfile;

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
    Dialog Flashpopup;
    PlacesClient placesClient;
    int AUTOCOMPLETE_REQUEST_CODE = 0;

  //  SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, 0);
//  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//    String currentDate = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initializeFonts();
        setTypeFace();
        setvalues();





//Current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        Log.d("Date ", formattedDate);

        Prefs.setPrefDate(this,formattedDate);



        String currentDate = df.format(c);
        Log.d("Date44 ", currentDate);

        if (Prefs.getPrefDate(this).equals(currentDate)){

        }
        else
        {

            InitializeFlashpopup();
            initializePopup();
            Prefs.setPrefDate(this,currentDate);
        }


    }
    private void InitializeFlashpopup() {
        Flashpopup = new Dialog(HomeActivity.this);
        Flashpopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Flashpopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Flashpopup.setContentView(R.layout.splash_popup);
        Flashpopup.setCancelable(true);
        Flashpopup.setCanceledOnTouchOutside(true);
        Flashpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void initializePopup() {
        Flashpopup.setContentView(R.layout.splash_popup);
        Flashpopup.setCancelable(true);
        Flashpopup.setCanceledOnTouchOutside(true);
        Flashpopup.show();


        final ImageView close_splash = Flashpopup.findViewById(R.id.close_splash);

        close_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flashpopup.dismiss();
            }
        });

        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        Flashpopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Flashpopup.getWindow().setGravity(Gravity.CENTER);




        Flashpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Flashpopup.setCancelable(true);
        Flashpopup.setCanceledOnTouchOutside(true);
        Flashpopup.show();
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
                vTAhHomeText.setTypeface(NIRMALA);
                vTAhBookingText.setTypeface(NIRMALA);
                vTAhOrderText.setTypeface(NIRMALA);
                vTAhProfileext.setTypeface(NIRMALA);


            }
        };
        r.run();
    }


    private void setvalues() {
        initializeTabForCategory();
        //vTTlLocation.setText("Kadapa");
        initializeFirebase();

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
                placesClient = Places.createClient(HomeActivity.this);


                final List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(HomeActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });


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
                    String city = addresses.get(0).getLocality();
                    Prefs.setUserLat(HomeActivity.this, String.valueOf(lat));
                    Prefs.setUserLang(HomeActivity.this, String.valueOf(lang));
                    vTTlLocation.setText(address);
                    initializeTabForCategory();
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
                                Prefs.setUserLat(HomeActivity.this, lati);
                                Prefs.setUserLang(HomeActivity.this, longi);
                                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                city = addresses.get(0).getLocality();
                                vTTlLocation.setText(address);
                                //CallMyLocationService(lati, longi);
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
                            Prefs.setUserLat(HomeActivity.this, lati);
                            Prefs.setUserLang(HomeActivity.this, longi);
                            addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            city = addresses.get(0).getLocality();
                            vTTlLocation.setText(address);
                            //CallMyLocationService(lati, longi);
                            //CallSliderService();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            new AlertDialog.Builder(this).setTitle("Location Permission").setMessage("Please enable location permission!")

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

    private void initializeFirebase() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Home Activity", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("Home Activiy", msg);
                        //Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }






    private void initializeTabForCategory() {

        this.vVAolCategoryViewpager.setAdapter(new CategoryAdapter(getSupportFragmentManager()));
        this.vVAolCategoryViewpager.setCurrentItem(0);
        this.vVAolCategoryViewpager.setOffscreenPageLimit(4);
        vTTlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location, 0, 0, 0);

        vLAhHomeLayout.setOnClickListener(this);
        vLAhBookingLayout.setOnClickListener(this);
        vLAhOrderLayout.setOnClickListener(this);
        vLAhProfileLayout.setOnClickListener(this);
        vRTlNotification.setOnClickListener(this);
        vLTlLocation.setOnClickListener(this);
        vITlSearch.setOnClickListener(this);
        vIGlSearch.setOnClickListener(this);

        vVAhHome.setVisibility(View.VISIBLE);
        vVAhBooking.setVisibility(View.GONE);
        vVAhOrder.setVisibility(View.GONE);
        vVAhProfile.setVisibility(View.GONE);


        vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
        vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
        vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
        vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

        vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
        vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
        vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
        vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);

        vVAolCategoryViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        vTTlHeader.setVisibility(View.GONE);
                        vLTlLocation.setVisibility(View.VISIBLE);
                        vVAhHome.setVisibility(View.VISIBLE);
                        vVAhBooking.setVisibility(View.GONE);
                        vVAhOrder.setVisibility(View.GONE);
                        vVAhProfile.setVisibility(View.GONE);

                        vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                        vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                        vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                        vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                        vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
                        vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
                        vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
                        vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);
                        break;
                    case 1:
                        vTTlHeader.setVisibility(View.VISIBLE);
                        vLTlLocation.setVisibility(View.GONE);
                        vTTlHeader.setText(getResources().getString(R.string.table_booking));
                        vVAhHome.setVisibility(View.GONE);
                        vVAhBooking.setVisibility(View.VISIBLE);
                        vVAhOrder.setVisibility(View.GONE);
                        vVAhProfile.setVisibility(View.GONE);

                        vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                        vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                        vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                        vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                        vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
                        vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
                        vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
                        vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);
                        break;
                    case 2:
                        vTTlHeader.setVisibility(View.VISIBLE);
                        vLTlLocation.setVisibility(View.GONE);
                        vTTlHeader.setText(getResources().getString(R.string.order_history));
                        vVAhHome.setVisibility(View.GONE);
                        vVAhBooking.setVisibility(View.GONE);
                        vVAhOrder.setVisibility(View.VISIBLE);
                        vVAhProfile.setVisibility(View.GONE);

                        vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                        vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                        vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                        vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                        vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
                        vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
                        vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
                        vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);
                        break;
                    case 3:
                        vTTlHeader.setVisibility(View.VISIBLE);
                        vLTlLocation.setVisibility(View.GONE);
                        vTTlHeader.setText(getResources().getString(R.string.profile));
                        vVAhHome.setVisibility(View.GONE);
                        vVAhBooking.setVisibility(View.GONE);
                        vVAhOrder.setVisibility(View.GONE);
                        vVAhProfile.setVisibility(View.VISIBLE);

                        vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                        vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                        vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                        vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                        vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
                        vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
                        vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
                        vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vL_ah_homeLayout:
                vTTlHeader.setVisibility(View.GONE);
                vLTlLocation.setVisibility(View.VISIBLE);
                vVAolCategoryViewpager.setCurrentItem(0, true);
                vVAhHome.setVisibility(View.VISIBLE);
                vVAhBooking.setVisibility(View.GONE);
                vVAhOrder.setVisibility(View.GONE);
                vVAhProfile.setVisibility(View.GONE);

                vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                vIAhHomeImage.setBackgroundResource(R.drawable.home);
                vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
                vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
                vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);
                break;
            case R.id.vL_ah_bookingLayout:
                vTTlHeader.setVisibility(View.VISIBLE);
                vLTlLocation.setVisibility(View.GONE);
                vTTlHeader.setText(getResources().getString(R.string.table_booking));

                vVAolCategoryViewpager.setCurrentItem(1, true);
                vVAhHome.setVisibility(View.GONE);
                vVAhBooking.setVisibility(View.VISIBLE);
                vVAhOrder.setVisibility(View.GONE);
                vVAhProfile.setVisibility(View.GONE);

                vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
                vIAhBookingImage.setBackgroundResource(R.drawable.bookings);
                vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
                vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);
                break;
            case R.id.vL_ah_orderLayout:
                vTTlHeader.setVisibility(View.VISIBLE);
                vLTlLocation.setVisibility(View.GONE);
                vTTlHeader.setText(getResources().getString(R.string.order_history));

                vVAolCategoryViewpager.setCurrentItem(2, true);
                vVAhHome.setVisibility(View.GONE);
                vVAhBooking.setVisibility(View.GONE);
                vVAhOrder.setVisibility(View.VISIBLE);
                vVAhProfile.setVisibility(View.GONE);

                vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
                vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
                vIAhOrderImage.setBackgroundResource(R.drawable.order1);
                vIAhProfileImage.setBackgroundResource(R.drawable.profile_white);
                break;
            case R.id.vL_ah_profileLayout:
                vTTlHeader.setVisibility(View.VISIBLE);
                vLTlLocation.setVisibility(View.GONE);
                vTTlHeader.setText(getResources().getString(R.string.profile));
                vVAolCategoryViewpager.setCurrentItem(3, true);
                vVAhHome.setVisibility(View.GONE);
                vVAhBooking.setVisibility(View.GONE);
                vVAhOrder.setVisibility(View.GONE);
                vVAhProfile.setVisibility(View.VISIBLE);

                vTAhHomeText.setTextColor(getResources().getColor(R.color.white));
                vTAhBookingText.setTextColor(getResources().getColor(R.color.white));
                vTAhOrderText.setTextColor(getResources().getColor(R.color.white));
                vTAhProfileext.setTextColor(getResources().getColor(R.color.white));

                vIAhHomeImage.setBackgroundResource(R.drawable.home_white);
                vIAhBookingImage.setBackgroundResource(R.drawable.bookings_white);
                vIAhOrderImage.setBackgroundResource(R.drawable.orderwhite);
                vIAhProfileImage.setBackgroundResource(R.drawable.profile_red);
                break;
            case R.id.vR_tl_notification:
                /*Intent intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);*/
                break;
            case R.id.vL_tl_location:
                /*Intent intentl = new Intent(this, SearchLocationActivity.class);
                startActivity(intentl);
                overridePendingTransition(0, 0);*/
                break;
            case R.id.vI_tl_cart:
                Intent intents = new Intent(this, CheckOutActivityNew.class);
                startActivity(intents);
                overridePendingTransition(0, 0);
                break;

            case R.id.vI_gl_search:
                Intent intentg = new Intent(this, SearchActivity.class);
                startActivity(intentg);
                overridePendingTransition(0, 0);
                break;


        }
    }


    private class CategoryAdapter extends FragmentStatePagerAdapter {
        private String[] tabTitles = new String[]{getResources().getString(R.string.home),
                getResources().getString(R.string.booking), getResources().getString(R.string.orders),
                getResources().getString(R.string.profile)};

        public CategoryAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return this.tabTitles.length;
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new UpcomingFragment();
                    break;
                case 2:
                    fragment = new OrderHistoryFragment();
                    break;
                case 3:
                    fragment = new ProfileFragment();
                    break;

            }
            return fragment;
        }



        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View v = getLayoutInflater().inflate(R.layout.custom_tab, null);
            final TextView tv = (TextView) v.findViewById(R.id.ad_cu_textView);
            tv.setText(this.tabTitles[position]);
            new Runnable() {
                public void run() {
                    tv.setTypeface(NIRMALA);
                }
            }.run();
            return v;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(HomeActivity.this, LandingActivity.class));
        HomeActivity.this.finish();
    }
}
