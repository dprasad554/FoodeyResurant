package com.geekhive.foodey.Food.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchLocationActivity extends AppCompatActivity implements View.OnClickListener {
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
    @BindView(R.id.vL_tl_skip)
    TextView vLTlSkip;
    @BindView(R.id.vL_toolbarLayout)
    LinearLayout vLToolbarLayout;
    @BindView(R.id.vT_aslc_location)
    TextView vTAslcLocation;
    @BindView(R.id.vE_aslc_search)
    EditText vEAslcSearch;
    @BindView(R.id.vT_aslc_auto)
    TextView vTAslcAuto;
    @BindView(R.id.vT_aslc_current)
    TextView vTAslcCurrent;
    @BindView(R.id.vR_asr_list)
    RecyclerView vRAsrList;
    PopularCityAdapter popularCityAdapter;
    Geocoder geocoder;
    List<Address> addresses;

    ConnectionDetector mDetector;
    String state;
    String country;

    String order_id;
    PlacesClient placesClient;
    int AUTOCOMPLETE_REQUEST_CODE = 1;

    double lat;
    double lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        ButterKnife.bind(this);
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

                vTTlHeader.setTypeface(NIRMALAB);
                vTTlLocation.setTypeface(NIRMALA);
                vLTlCount.setTypeface(NIRMALAB);
                vTAslcAuto.setTypeface(NIRMALAB);
                vTAslcCurrent.setTypeface(NIRMALAB);
                vTAslcLocation.setTypeface(NIRMALAB);
                vEAslcSearch.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
        vITlBack.setVisibility(View.VISIBLE);
        vTTlHeader.setVisibility(View.GONE);
        vLTlLocation.setVisibility(View.VISIBLE);
        vRTlNotification.setVisibility(View.GONE);
        vITlSearch.setVisibility(View.GONE);
        vTTlHeader.setText(getResources().getString(R.string.booking_details));
        vITlBack.setOnClickListener(this);
        vTTlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location, 0, 0, 0);

        popularCityAdapter = new PopularCityAdapter();
        vRAsrList.setLayoutManager(new LinearLayoutManager(this));
        vRAsrList.setAdapter(popularCityAdapter);
        Places.initialize(getApplicationContext(), "AIzaSyB_RBQ5Le6REjSn35XB_f7_ufQmzETVOYY");
        // Create a new Places client instance.
        placesClient = Places.createClient(this);
        vEAslcSearch.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_tl_back:
                finish();
                break;
            case R.id.vE_aslc_search:
                final List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(SearchLocationActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
                break;
        }
    }

    public class PopularCityAdapter extends RecyclerView.Adapter<PopularCityAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_select_loc, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {


        }

        public int getItemCount() {
            return 2;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_adsl_main;
            TextView vT_adsl_location;

            public MyViewHolder(View view) {
                super(view);
                this.vT_adsl_location = (TextView) view.findViewById(R.id.vT_adsl_location);
                this.vL_adsl_main = (LinearLayout) view.findViewById(R.id.vL_adsl_main);
                new Runnable() {
                    public void run() {
                        vT_adsl_location.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
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
                    state = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

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
}
