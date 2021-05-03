package com.geekhive.foodey.Grocery.activities;

import android.Manifest;
import android.app.AlertDialog;
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
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.landing.LandingActivity;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.fragment.GroceryHomeFragment;
import com.geekhive.foodey.Grocery.fragment.GroceryTrackOrderFragment;
import com.geekhive.foodey.R;
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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class GroceryBottomNavigationActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout vL_tl_location;
    private Fragment viewFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    BottomNavigationView bottomNavigationView;
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    @BindView(R.id.vL_tl_count)
    TextView vLTlCount;
    @BindView(R.id.vT_ah_homeText)
    TextView vTAhHomeText;
    @BindView(R.id.vT_ah_bookingText)
    TextView vTAhBookingText;
    @BindView(R.id.vT_ah_orderText)
    TextView vTAhOrderText;
    @BindView(R.id.vT_ah_profileext)
    TextView vTAhProfileext;
    @BindView(R.id.vT_tl_header)
    TextView vTTlHeader;
    ImageView vIGlSearch;
    ImageView vI_tl_cart;

    TextView vT_tl_location;
    Geocoder geocoder;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    double lat;
    double lang;
    String lati;
    String longi;
    List<Address> addresses;
    String city = "";
    PlacesClient placesClient;
    int AUTOCOMPLETE_REQUEST_CODE = 1;
    Location currentLocation;
    boolean GpsStatus;

    String title, product_sub_category;
    String storeid;
    String url = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_bottomnavigation);
        vIGlSearch = findViewById(R.id.vI_gl_search);
        vT_tl_location = findViewById(R.id.vT_tl_location);
        vI_tl_cart = findViewById(R.id.vI_tl_cart);
        setvalues();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new GroceryHomeFragment()).commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewFragment = new GroceryHomeFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView,viewFragment).commit();
                        break;
                    case R.id.track:
                        viewFragment = new GroceryTrackOrderFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView, viewFragment).commit();
                        break;
                    case R.id.profile:
                        if(GroceryShopProductListActivity.shoppingListDatabase != null){
                            Intent intentg = new Intent(GroceryBottomNavigationActivity.this, GroceryShopingListActivity.class);
                            startActivity(intentg);
                        }else {
                            SnackBar.makeText(GroceryBottomNavigationActivity.this, "No item in shopping list", SnackBar.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_gl_search:
                Intent intentg = new Intent(GroceryBottomNavigationActivity.this, Groceryglobalsearch.class);
                startActivity(intentg);
                overridePendingTransition(0, 0);
                break;
            case R.id.vI_tl_cart:
                startActivity(new Intent(GroceryBottomNavigationActivity.this, GroceryCheckOutActivityNew.class));
                overridePendingTransition(0, 0);
        }
    }

    private void setvalues() {
        //vTTlLocation.setText("Kadapa");
        initializeTabForCategory();

        title = getIntent().getStringExtra("title");
        product_sub_category = getIntent().getStringExtra("sub_id");
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        storeid = getIntent().getStringExtra("store_id");
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");

        vI_tl_cart.setOnClickListener(this);
        initializeFirebase();

        geocoder = new Geocoder(this, Locale.getDefault());
        vIGlSearch.setOnClickListener(this);
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
                vT_tl_location.setText(address);
                //CallMyLocationService(lati, longi);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        vT_tl_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Places.initialize(getApplicationContext(), "AIzaSyB_RBQ5Le6REjSn35XB_f7_ufQmzETVOYY");
                // Create a new Places client instance.
                placesClient = Places.createClient(GroceryBottomNavigationActivity.this);


                final List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(GroceryBottomNavigationActivity.this);
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
                    Prefs.setUserLat(GroceryBottomNavigationActivity.this, String.valueOf(lat));
                    Prefs.setUserLang(GroceryBottomNavigationActivity.this, String.valueOf(lang));
                    vT_tl_location.setText(address);
                    mFragmentManager = getSupportFragmentManager();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.containerView, new GroceryHomeFragment()).commit();
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
                                Prefs.setUserLat(GroceryBottomNavigationActivity.this, lati);
                                Prefs.setUserLang(GroceryBottomNavigationActivity.this, longi);
                                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                city = addresses.get(0).getLocality();
                                vT_tl_location.setText(address);
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
                            Prefs.setUserLat(GroceryBottomNavigationActivity.this, lati);
                            Prefs.setUserLang(GroceryBottomNavigationActivity.this, longi);
                            addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            city = addresses.get(0).getLocality();
                            vT_tl_location.setText(address);
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


    private void initializeTabForCategory() {
        vT_tl_location.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location, 0, 0, 0);
        vIGlSearch.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GroceryBottomNavigationActivity.this, LandingActivity.class));
        GroceryBottomNavigationActivity.this.finish();
    }
}

