package com.geekhive.foodey.Cakes.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.VerticalStepView;
import com.geekhive.foodey.Cakes.beans.cakehistory.CakeHistory;
import com.geekhive.foodey.Cakes.beans.cakemapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Cakes.beans.cakeupdatelocation.CakeTrackLocation;
import com.geekhive.foodey.Cakes.beans.cakeuserlocation.CakeUserLocation;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.FetchURL;
import com.geekhive.foodey.Cakes.utils.GitApi;
import com.geekhive.foodey.Cakes.utils.LatLngInterpolator;
import com.geekhive.foodey.Cakes.utils.MarkerAnimation;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.TaskLoadedCallback;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class CakeTrackingActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback, View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.riderInfo)
    TextView riderInfo;
    @BindView(R.id.callRider)
    LinearLayout callRider;
    @BindView(R.id.tv_call)
    TextView tv_call;
    @BindView(R.id.orderETA)
    TextView orderETA;
    @BindView(R.id.orderId)
    TextView custOrderId;

    ConnectionDetector mDetector;
    String order_id;
    GoogleMap googleMap;
    double userLat;
    double userLang;
    double driverLat;
    double driverLang;
    Geocoder geocoder;
    List<Address> addresses;

    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    private Marker currentLocationMarker;

    String del_id;
    MapFragment mapFragment;

    Retrofit retrofit;
    GitApi apiService;
    Disposable disposable;
    private final int CALL_REQUEST = 100;
    String driverNo = "";
    private VerticalStepView mSetpview0;
    CakeHistory orderHistory;
    int pos = 0;
    List<String> statusList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_tracking_copy);
        ButterKnife.bind(this);
        initializeFonts();
        setTypeFace();
        setvalues();
    }

    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                //orderStatus.setTypeface(NIRMALAB);
                riderInfo.setTypeface(NIRMALAS);
                tv_call.setTypeface(NIRMALA);
                orderETA.setTypeface(NIRMALAB);

            }
        };
        r.run();
    }

    private void setvalues() {
        pos = getIntent().getIntExtra("position", 0);
        mDetector = new ConnectionDetector(this);
        del_id = getIntent().getStringExtra("del_id");
        order_id = getIntent().getStringExtra("order_id");
        custOrderId.setText(order_id);
        callRider.setOnClickListener(this);
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.track_map);

        //CallUserLocationService();

        CallOrderService();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(WebServices.Foodey_Cake_Services)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(GitApi.class);

        disposable = Observable.interval(1000, 5000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::callLocationUpdate, this::onError);

        mSetpview0 = (VerticalStepView) findViewById(R.id.step_view0);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callRider:
                callPhoneNumber();
                break;
        }
    }
    private void callLocationUpdate(Long aLong) {


        Observable<CakeUserLocation> observable = apiService.getUserLocation(MultipartBody.Part.createFormData("order_id", order_id));
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(result -> result)
                .subscribe(this::handleResults, this::handleError);
    }

    private void onError(Throwable throwable) {
        Toast.makeText(this, "OnError in Observable Timer",
                Toast.LENGTH_LONG).show();
    }


    private void handleResults(CakeUserLocation userLocation) {


        if (userLocation != null){
            if (userLocation.getAddress() != null){
                userLat = Double.parseDouble(userLocation.getAddress().getLatitude());
                userLang = Double.parseDouble(userLocation.getAddress().getLongitude());

                place1 = new MarkerOptions().position(new LatLng(userLat, userLang)).title("Home").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_home));

                CallService();
            }

        } else {
            SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable t) {

        //Add your error here.
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).TrackLocation(WebServices.Foodey_Cake_Services,
                    WebServices.ApiType.tracklocation, del_id, order_id);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    private void CallMapService(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistance,origin, destination, key);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    private void CallOrderService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")){
                new WebServices(this).HistoryDetails(WebServices.Foodey_Cake_Services,
                        WebServices.ApiType.history, Prefs.getUserId(this));
            } else {

                SnackBar.makeText(this, getResources().getString(R.string.something_wrong) , SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }
    /*private void CallUserLocationService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).UserLocation(WebServices.Foodey_Services,
                    WebServices.ApiType.userlocation, order_id);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }*/
    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.tracklocation) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeTrackLocation trackLocation = (CakeTrackLocation) response;
                if (trackLocation != null){
                    if (trackLocation.getDetails() != null){
                        driverLat = Double.parseDouble(trackLocation.getDetails().get(0).getLatitude());
                        driverLang = Double.parseDouble(trackLocation.getDetails().get(0).getLongitude());
                        String name = "Delivery agent " + trackLocation.getDetails().get(1).getFirstname() + " " + trackLocation.getDetails().get(1).getLastname() + " is on his way";
                        riderInfo.setText(name);
                        driverNo = trackLocation.getDetails().get(1).getMobile();
                        place2 = new MarkerOptions().position(new LatLng(driverLat, driverLang)).title("Delivery Partner").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_delivery));//.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_delivery));
                        mapFragment.getMapAsync(CakeTrackingActivity.this);
                        new FetchURL(CakeTrackingActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");


                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.userlocation) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeUserLocation userLocation = (CakeUserLocation) response;
                if (userLocation != null){
                    if (userLocation.getAddress() != null){
                        userLat = Double.parseDouble(userLocation.getAddress().getLatitude());
                        userLang = Double.parseDouble(userLocation.getAddress().getLongitude());

                        place1 = new MarkerOptions().position(new LatLng(userLat, userLang)).title("Home");

                        CallService();
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null){
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null){
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDuration().getText();
                        orderETA.setText(distance);
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.history) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                orderHistory = (CakeHistory) response;
                if (orderHistory != null){
                    if (orderHistory.getOrderHistory() != null){
                        if (orderHistory.getOrderHistory().getCakeCart().get(pos).getStatus().equals("5")){
                            statusList.add("Order confirmed");
                            statusList.add("Store Packed the order");
                            statusList.add("Delivery boy assigned");
                            statusList.add("Order picked");
                            statusList.add("Your cake is arriving to your location");
                            statusList.add("Your cake is delivered");
                            mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 1)
                                    .reverseDraw(false)
                                    .setStepViewTexts(statusList)
                                    .setLinePaddingProportion(0.85f)
                                    .setTextSize(16)
                                    .setStepsViewIndicatorCompletedLineColor(getResources().getColor(R.color.colorPrimary))
                                    .setStepsViewIndicatorUnCompletedLineColor(getResources().getColor(R.color.colorPrimary))
                                    .setStepViewComplectedTextColor(getResources().getColor(R.color.colorPrimary))
                                    .setStepViewUnComplectedTextColor(getResources().getColor(R.color.colorPrimary))
                                    .setStepsViewIndicatorCompleteIcon(getResources().getDrawable(R.drawable.completed))
                                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))
                                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));
                        }
                    } else {
                        SnackBar.makeText(this, "No record found", SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        Log.d("mylog", "Added Markers");
        googleMap.addMarker(place1);
        googleMap.addMarker(place2);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(driverLat, driverLang), 8.0f));
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(driverLat,
                driverLang)).zoom(8.0f).build()));
        googleMap.setMapType(1);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        showMarker();
    }

    private void showMarker() {
        LatLng latLng = new LatLng(driverLat, driverLat);
        LatLng newLatLng = new LatLng(driverLat, driverLang);
        if (currentLocationMarker == null)
            currentLocationMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_delivery)).position(newLatLng));
        else
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());

        String str_origin = userLat + "," + userLang;
        // Destination of route
        String str_dest = driverLat + "," + driverLang;

        String key = getResources().getString(R.string.google_map_api);
        CallMapService(str_origin, str_dest, key);
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_map_api);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = googleMap.addPolyline((PolylineOptions) values[0]);
        currentPolyline.setColor(this.getResources().getColor(R.color.green_2));
    }


    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(CakeTrackingActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST);

                    return;
                } else {
                    if (!driverNo.equals("")){
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + driverNo));
                        startActivity(callIntent);
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == CALL_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (!driverNo.equals("")){
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + driverNo));
                    startActivity(callIntent);
                }
            } else {
                Toast.makeText(CakeTrackingActivity.this, "Please allow calling permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
