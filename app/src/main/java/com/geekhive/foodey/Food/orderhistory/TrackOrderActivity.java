package com.geekhive.foodey.Food.orderhistory;

import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.customs.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TrackOrderActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    @BindView(R.id.vT_ato_location)
    TextView vTAtoLocation;
    @BindView(R.id.vT_ato_time)
    TextView vTAtoTime;
    @BindView(R.id.vC_ato_image)
    CircleImageView vCAtoImage;
    @BindView(R.id.vT_ato_name)
    TextView vTAtoName;
    @BindView(R.id.vT_ato_position)
    TextView vTAtoPosition;
    @BindView(R.id.vT_ato_call)
    TextView vTAtoCall;
    @BindView(R.id.vL_ato_location)
    LinearLayout vLAtoLocation;

    GoogleMap googleMap;
    double latitude;
    double longitude;
    @BindView(R.id.vI_ato_back)
    ImageView vIAtoBack;
    @BindView(R.id.vT_ato_header)
    TextView vTAtoHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        ButterKnife.bind(this);

        checkLocationService();
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

                vTAtoHeader.setTypeface(NIRMALAB);
                vTAtoCall.setTypeface(NIRMALAB);
                vTAtoLocation.setTypeface(NIRMALAB);
                vTAtoName.setTypeface(NIRMALAB);
                vTAtoPosition.setTypeface(NIRMALA);
                vTAtoTime.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
       vIAtoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_ato_back:
                finish();
                break;
        }
    }

    private void setUpMapIfNeeded() {
        if (this.googleMap == null) {
            ((MapFragment) this.getFragmentManager().findFragmentById(R.id.vf_ato_map)).getMapAsync(this);
            if (this.googleMap != null) {
                this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 17.0f));
        map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(latitude,
                longitude)).zoom(17.0f).build()));
        map.setMapType(1);
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_in_night));
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            map.getUiSettings().setZoomControlsEnabled(true);
//            map.getUiSettings().set
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
            if (location != null) {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17.0f));
                map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(17.0f).build()));
            }
            createMarker(latitude, longitude, "", "", R.drawable.map_2, map);
            this.googleMap = map;
        }
    }

    private void checkLocationService() {
        GPSTracker gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            this.latitude = gps.getLatitude();
            this.longitude = gps.getLongitude();
            Log.e("latitude", "" + this.latitude);
            Log.e("longitude", "" + this.longitude);
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

}
