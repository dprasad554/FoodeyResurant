package com.geekhive.foodey.Cakes.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.beans.cakeaddress.CakeAddAddress;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.customs.GPSTracker;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeAddAddressActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerDragListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_aaa_back)
    ImageView vIAaaBack;
    @BindView(R.id.vT_aaa_current)
    TextView vTAaaCurrent;
    @BindView(R.id.vT_aaa_currentval)
    TextView vTAaaCurrentval;
    @BindView(R.id.vI_aaa_home)
    ImageView vIAaaHome;
    @BindView(R.id.vT_aaa_home)
    TextView vTAaaHome;
    @BindView(R.id.vL_aaa_home)
    LinearLayout vLAaaHome;
    @BindView(R.id.vI_aaa_work)
    ImageView vIAaaWork;
    @BindView(R.id.vT_aaa_work)
    TextView vTAaaWork;
    @BindView(R.id.vL_aaa_work)
    LinearLayout vLAaaWork;
    @BindView(R.id.vI_aaa_other)
    ImageView vIAaaOther;
    @BindView(R.id.vT_aaa_other)
    TextView vTAaaOther;
    @BindView(R.id.vL_aaa_other)
    LinearLayout vLAaaOther;
    @BindView(R.id.vE_aaa_house)
    EditText vEAaaHouse;
    @BindView(R.id.vE_aaa_apartment)
    EditText vEAaaApartment;
    @BindView(R.id.vE_aaa_street)
    EditText vEAaaStreet;
    @BindView(R.id.vE_aaa_area)
    EditText vEAaaArea;
    @BindView(R.id.vE_aaa_landmark)
    EditText vEAaaLandmark;
    @BindView(R.id.vE_aaa_city)
    EditText vEAaaCity;
    @BindView(R.id.vE_aaa_state)
    EditText vEAaaState;
    @BindView(R.id.vE_aaa_pin)
    EditText vEAaaPin;
    @BindView(R.id.vT_aaa_add)
    TextView vTAaaAdd;
    GoogleMap googleMap;
    double lat;
    double lang;
    @BindView(R.id.vL_aaa_select)
    LinearLayout vLAaaSelect;
   /* @BindView(R.id.search_)
    TextView search_;*/

    int AUTOCOMPLETE_REQUEST_CODE = 1;

    Geocoder geocoder;
    List<Address> addresses;

    ConnectionDetector mDetector;
    String state;
    String country;

    String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_add_address);
        ButterKnife.bind(this);

        initializeFonts();
        setTypeFace();
        setvalues();
        checkLocationService();
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

                vTAaaAdd.setTypeface(NIRMALAB);
                vTAaaCurrent.setTypeface(NIRMALAB);
                vTAaaCurrentval.setTypeface(NIRMALA);
                vTAaaHome.setTypeface(NIRMALAB);
                vTAaaOther.setTypeface(NIRMALAB);
                vTAaaWork.setTypeface(NIRMALAB);
                vEAaaCity.setTypeface(NIRMALA);
                vEAaaHouse.setTypeface(NIRMALA);
                vEAaaLandmark.setTypeface(NIRMALA);
                vEAaaPin.setTypeface(NIRMALA);
                vEAaaApartment.setTypeface(NIRMALA);
                vEAaaStreet.setTypeface(NIRMALA);
                vEAaaArea.setTypeface(NIRMALA);
                vEAaaState.setTypeface(NIRMALA);
            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        //order_id = getIntent().getStringExtra("order_id");
        geocoder = new Geocoder(this, Locale.getDefault());
        if (!(getIntent().getStringExtra("from") == null || getIntent().getStringExtra("from").length() == 0)) {
            vTAaaAdd.setText(getResources().getString(R.string.continue_));
            vLAaaSelect.setVisibility(View.GONE);
        }

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyB_RBQ5Le6REjSn35XB_f7_ufQmzETVOYY");
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
        vIAaaBack.setOnClickListener(this);
        vTAaaAdd.setOnClickListener(this);

        final List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

        /*search_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(AddAddressActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });*/

        vTAaaAdd.setOnClickListener(this);

        if (googleMap != null) {
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                    createMarker(lat, lang, "", "", R.drawable.map_2, googleMap);
                    try {
                        addresses = geocoder.getFromLocation(lang, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get(0).getLocality();
                        state = addresses.get(0).getAdminArea();
                        country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName();
                        vEAaaCity.setText(city);
                        vEAaaPin.setText(postalCode);
                        vTAaaCurrentval.setText(address);
                        vEAaaState.setText(state);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_aaa_back:
                finish();
                break;
            case R.id.vT_aaa_add:
                if (!vEAaaHouse.getText().toString().isEmpty() || !vEAaaHouse.getText().toString().equals("")) {
                    if (!vEAaaApartment.getText().toString().isEmpty() || !vEAaaApartment.getText().toString().equals("")) {
                        if (!vEAaaStreet.getText().toString().isEmpty() || !vEAaaStreet.getText().toString().equals("")) {
                            if (!vEAaaArea.getText().toString().isEmpty() || !vEAaaArea.getText().toString().equals("")) {
                                if (!vEAaaLandmark.getText().toString().isEmpty() || !vEAaaLandmark.getText().toString().equals("")) {
                                    if (!vEAaaCity.getText().toString().isEmpty() || !vEAaaCity.getText().toString().equals("")){
                                        if (!vEAaaState.getText().toString().isEmpty() || !vEAaaState.getText().toString().equals("")){
                                            if (!vEAaaPin.getText().toString().isEmpty() || !vEAaaPin.getText().toString().equals("")){
                                                CallService();
                                            } else {
                                                vEAaaPin.setError("Please fill");
                                            }
                                        } else {
                                            vEAaaState.setError("Please fill");
                                        }
                                    } else {
                                        vEAaaCity.setError("Please fill");
                                    }
                                } else {
                                    vEAaaLandmark.setError("Please fill");
                                }
                            } else {
                                vEAaaArea.setError("Please fill");
                            }
                        } else {
                            vEAaaStreet.setError("Please fill");
                        }
                    } else {
                        vEAaaApartment.setError("Please fill");
                    }
                } else {
                    vEAaaHouse.setError("Please fill");
                }
                break;
        }

    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).AddAddress(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.addAddress, Prefs.getUserId(this), vEAaaHouse.getText().toString(), vEAaaApartment.getText().toString(),
                    vEAaaStreet.getText().toString(), vEAaaLandmark.getText().toString(), vEAaaArea.getText().toString(), vEAaaCity.getText().toString(),
                    vEAaaState.getText().toString(), country, vEAaaPin.getText().toString(), String.valueOf(lat), String.valueOf(lang));

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.addAddress) {
            if (!isSucces) {
                SnackBar.makeText(CakeAddAddressActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeAddAddress addAddress = (CakeAddAddress) response;
                SnackBar.makeText(this, addAddress.getMessage(), SnackBar.LENGTH_SHORT);
                Intent intent = new Intent(CakeAddAddressActivity.this, CakeManageAddressActivity.class);
                intent.putExtra("order_id", order_id);
                startActivity(intent);
                CakeAddAddressActivity.this.finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                place.getLatLng();
                LatLng queriedLocation = place.getLatLng();
                //createMarker(queriedLocation.latitude, queriedLocation.longitude, "", "", R.drawable.map_2, googleMap);
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

    private void setUpMapIfNeeded() {
        if (this.googleMap == null) {
            ((MapFragment) this.getFragmentManager().findFragmentById(R.id.vf_aaa_map)).getMapAsync(this);
            if (this.googleMap != null) {
                this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lang), 17.0f));
        map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(lat,
                lang)).zoom(17.0f).build()));
        map.setMapType(1);
        //map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_in_night));
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            map.getUiSettings().setZoomControlsEnabled(true);
//            map.getUiSettings().set
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
            if (location != null) {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17.0f));
                map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(17.0f).build()));
            }
            createMarker(lat, lang, "", "", R.drawable.map_2, map);
            this.googleMap = map;

            try {
                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                vEAaaApartment.setText(knownName);
                vEAaaCity.setText(city);
                vEAaaPin.setText(postalCode);
                vEAaaState.setText(state);
                vTAaaCurrentval.setText(address);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    private void checkLocationService() {
        GPSTracker gps = new GPSTracker(CakeAddAddressActivity.this);
        if (gps.canGetLocation()) {
            this.lat = gps.getLatitude();
            this.lang = gps.getLongitude();
            try {
                addresses = geocoder.getFromLocation(lat, lang, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                vEAaaCity.setText(city);
                vEAaaPin.setText(postalCode);

                vTAaaCurrentval.setText(address);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("latitude", "" + this.lat);
            Log.e("longitude", "" + this.lang);
        }
        setUpMapIfNeeded();
    }

    protected Marker createMarker(double latitude, double longitude, String title, String snippet, int iconResID, GoogleMap map) {

        return map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(iconResID)));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        createMarker(marker.getPosition().latitude, marker.getPosition().longitude, marker.getTitle(), marker.getSnippet(), 0, googleMap);
        createMarkerPoint(marker.getPosition().latitude, marker.getPosition().longitude);
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        createMarker(marker.getPosition().latitude, marker.getPosition().longitude, marker.getTitle(), marker.getSnippet(), 0, googleMap);
        createMarkerPoint(marker.getPosition().latitude, marker.getPosition().longitude);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        createMarker(marker.getPosition().latitude, marker.getPosition().longitude, marker.getTitle(), marker.getSnippet(), 0, googleMap);
        createMarkerPoint(marker.getPosition().latitude, marker.getPosition().longitude);
    }

    public void createMarkerPoint(double latitude, double longitude){
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            vEAaaApartment.setText(knownName);
            vEAaaCity.setText(city);
            vEAaaPin.setText(postalCode);
            vEAaaState.setText(state);
            vTAaaCurrentval.setText(address);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
