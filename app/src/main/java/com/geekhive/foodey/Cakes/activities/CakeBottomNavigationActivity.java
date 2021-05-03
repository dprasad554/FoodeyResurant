package com.geekhive.foodey.Cakes.activities;

import android.Manifest;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.fragment.CakeHomeFragment;
import com.geekhive.foodey.Cakes.fragment.CakeTrackOrderFragment;
import com.geekhive.foodey.Cakes.utils.CakeFavoriteDatabase;
import com.geekhive.foodey.Food.landing.LandingActivity;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.custom.SnackBar;
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

public class CakeBottomNavigationActivity extends AppCompatActivity implements View.OnClickListener{

    TextView place;
    private Fragment viewFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    BottomNavigationView bottomNavigationView;
    ImageView iv_cart;
    ImageView search;
    LinearLayout ll_search;
    EditText et_login;
    LinearLayout search2;
    LinearLayout ll_tool;
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
    Location currentLocation ;
    boolean GpsStatus;
    TextView vT_tl_location;
    ImageView vI_tl_cart;
    ImageView vIGlSearch;
    public static CakeFavoriteDatabase favoriteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_bottomnavigation);
        vT_tl_location = findViewById(R.id.vT_tl_location);
        vI_tl_cart = findViewById(R.id.vI_tl_cart);
        vIGlSearch = findViewById(R.id.vI_gl_search);
        setvalues();

        /*iv_cart = findViewById(R.id.iv_cart);
        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CakeBottomNavigationActivity.this, CakeCheckOutActivityNew.class));
            }
        });
        et_login = findViewById(R.id.et_login);
        search = findViewById(R.id.search);*/
       /* ll_search = findViewById(R.id.ll_search);
        search2 = findViewById(R.id.search2);
        ll_tool = findViewById(R.id.ll_main);

        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_tool.setVisibility(View.GONE);
                ll_search.setVisibility(View.VISIBLE);
                search.setVisibility(View.VISIBLE);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_login.getText().toString().equals("")){
                    SnackBar.makeText(CakeBottomNavigationActivity.this, "Please enter your search", SnackBar.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(CakeBottomNavigationActivity.this, CakeSearchActivity.class);
                    intent.putExtra("name", et_login.getText().toString());
                    intent.putExtra("search", "search");
                    ll_search.setVisibility(View.GONE);
                    ll_tool.setVisibility(View.VISIBLE);
                    startActivity(intent);
                }

            }
        });
*/
        //Load Fragment To Activity
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new CakeHomeFragment()).commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewFragment = new CakeHomeFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView,viewFragment).commit();
                        break;
                    case R.id.track:
                        viewFragment = new CakeTrackOrderFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView,viewFragment).commit();
                        break;
                    case R.id.profile:
                        if(CakeShopSubCategoryActivity.favoriteDatabase != null){
                            Intent intentg = new Intent(CakeBottomNavigationActivity.this, CakeFavoriteListActivity.class);
                            startActivity(intentg);
                        }else {
                            SnackBar.makeText(CakeBottomNavigationActivity.this, "No item in favorite list", SnackBar.LENGTH_SHORT).show();
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
            case R.id.vI_tl_cart:
                Intent intentg = new Intent(CakeBottomNavigationActivity.this, CakeCheckOutActivityNew.class);
                startActivity(intentg);
                overridePendingTransition(0, 0);
                break;
            case R.id.vI_gl_search:
                Intent intent = new Intent(CakeBottomNavigationActivity.this, Cakeglobalsearch.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }
    private void setvalues() {
        initializeTabForCategory();
        //vTTlLocation.setText("Kadapa");
        initializeFirebase();
        vI_tl_cart.setOnClickListener(this);
        vIGlSearch.setOnClickListener(this);
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
                vT_tl_location.setText(address);
                /*mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, new CakeHomeFragment()).commit();*/
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
                placesClient = Places.createClient(CakeBottomNavigationActivity.this);


                final List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(CakeBottomNavigationActivity.this);
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
                    Prefs.setUserLat(CakeBottomNavigationActivity.this, String.valueOf(lat));
                    Prefs.setUserLang(CakeBottomNavigationActivity.this, String.valueOf(lang));
                    vT_tl_location.setText(address);
                    mFragmentManager = getSupportFragmentManager();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.containerView, new CakeHomeFragment()).commit();
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
                                Prefs.setUserLat(CakeBottomNavigationActivity.this, lati);
                                Prefs.setUserLang(CakeBottomNavigationActivity.this, longi);
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
    } public void GPSStatus() {
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
                            Prefs.setUserLat(CakeBottomNavigationActivity.this, lati);
                            Prefs.setUserLang(CakeBottomNavigationActivity.this, longi);
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
        vT_tl_location.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location, 0, 0, 0);
        vIGlSearch.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CakeBottomNavigationActivity.this, LandingActivity.class));
        CakeBottomNavigationActivity.this.finish();
    }
}
