package com.geekhive.foodey.Food.orderfood;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.cartlist.CartDetail;
import com.geekhive.foodey.Food.beans.payment.PaymentCOD;
import com.geekhive.foodey.Food.beans.removecart.RemoveCartItem;
import com.geekhive.foodey.Food.beans.updatecart.UpdateCartItem;
import com.geekhive.foodey.Food.customs.GPSTracker;
import com.geekhive.foodey.Food.manageaddress.ManageAddressActivity;
import com.geekhive.foodey.Food.orderhistory.OrderSummaryActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vT_aco_charges)
    TextView vTAcoCharges;
    @BindView(R.id.vT_aco_grand_total)
    TextView vTAcoGrandTotal;
    @BindView(R.id.vT_aco_grand_totalval)
    TextView vTAcoGrandTotalval;
    @BindView(R.id.vT_aco_distance)
    TextView vTAcoDistance;
    @BindView(R.id.vT_aco_view_break)
    TextView vTAcoViewBreak;
    @BindView(R.id.vT_aco_sub_total)
    TextView vTAcoSubTotal;
    @BindView(R.id.vT_aco_sub_totalval)
    TextView vTAcoSubTotalval;
    @BindView(R.id.vT_aco_gst)
    TextView vTAcoGst;
    @BindView(R.id.vT_aco_gstval)
    TextView vTAcoGstval;
    @BindView(R.id.vT_aco_delivery)
    TextView vTAcoDelivery;
    @BindView(R.id.vT_aco_deliveryval)
    TextView vTAcoDeliveryval;
    @BindView(R.id.vT_aco_discount)
    TextView vTAcoDiscount;
    @BindView(R.id.vT_aco_discountval)
    TextView vTAcoDiscountval;
    @BindView(R.id.vR_aco_main)
    RelativeLayout vRAcoMain;
    @BindView(R.id.vI_aco_back)
    ImageView vIAcoBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vC_aco_collapsing_toolbar)
    CollapsingToolbarLayout vCAcoCollapsingToolbar;
    @BindView(R.id.vA_aco_appbar)
    AppBarLayout vAAcoAppbar;
    @BindView(R.id.vT_aco_shop)
    TextView vTAcoShop;
    @BindView(R.id.vT_aco_location)
    TextView vTAcoLocation;
    @BindView(R.id.vT_aco_time)
    TextView vTAcoTime;
    @BindView(R.id.vT_aco_item)
    TextView vTAcoItem;
    @BindView(R.id.vR_aco_list)
    RecyclerView vRAcoList;
    @BindView(R.id.vT_aco_additem)
    TextView vTAcoAdditem;
    @BindView(R.id.vT_aco_current)
    TextView vTAcoCurrent;
    @BindView(R.id.vT_aco_currentval)
    TextView vTAcoCurrentval;
    @BindView(R.id.vT_aco_add_contact)
    TextView vTAcoAddContact;
    @BindView(R.id.vL_aco_add_contact)
    LinearLayout vLAcoAddContact;
    @BindView(R.id.vT_aco_schedule)
    TextView vTAcoSchedule;
    @BindView(R.id.vT_aco_order_now)
    TextView vTAcoOrderNow;
    @BindView(R.id.vL_aco_location)
    LinearLayout vLAcoLocation;
    @BindView(R.id.cartLayout)
    LinearLayout cartLayout;
    /*@BindView(R.id.emptyText)
    TextView emptyText;*/

    GoogleMap googleMap;
    double latitude;
    double longitude;


    DishAdapter dishAdapter;
    ConnectionDetector mDetector;

    CartDetail cartDetail;

    Geocoder geocoder;
    List<Address> addresses;

    String address;

    String qty;
    int quantity = 1;
    Dialog deletePopup;

    String grandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
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

                vTAcoAddContact.setTypeface(NIRMALAB);
                vTAcoAdditem.setTypeface(NIRMALA);
                vTAcoCharges.setTypeface(NIRMALAB);
                vTAcoCurrent.setTypeface(NIRMALAB);
                vTAcoCurrentval.setTypeface(NIRMALA);
                vTAcoDelivery.setTypeface(NIRMALA);
                vTAcoDeliveryval.setTypeface(NIRMALA);
                vTAcoDiscount.setTypeface(NIRMALA);
                vTAcoDiscountval.setTypeface(NIRMALA);
                vTAcoDistance.setTypeface(NIRMALA);
                vTAcoGrandTotal.setTypeface(NIRMALAB);
                vTAcoGrandTotalval.setTypeface(NIRMALAB);
                vTAcoGst.setTypeface(NIRMALA);
                vTAcoGstval.setTypeface(NIRMALA);
                vTAcoItem.setTypeface(NIRMALAB);
                vTAcoLocation.setTypeface(NIRMALA);
                vTAcoOrderNow.setTypeface(NIRMALAB);
                vTAcoSchedule.setTypeface(NIRMALAB);
                vTAcoShop.setTypeface(NIRMALA);
                vTAcoSubTotal.setTypeface(NIRMALA);
                vTAcoSubTotalval.setTypeface(NIRMALA);
                vTAcoTime.setTypeface(NIRMALA);
                vTAcoViewBreak.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        vIAcoBack.setOnClickListener(this);
        vLAcoLocation.setOnClickListener(this);
        vTAcoOrderNow.setOnClickListener(this);
    }


    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).GetCartList(WebServices.Foodey_Services,
                        WebServices.ApiType.cartList, Prefs.getUserId(this));
            } else {
                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallUpdateService(String cart_id, String quantity) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")){
                /*new WebServices(this).UpdateCartItem(WebServices.Foodey_Services,
                        WebServices.ApiType.updateCartItem, Prefs.getUserId(this), cart_id, quantity);*/
            } else {

                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong) , SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallRemoveService(String cart_id) {
        if (this.mDetector.isConnectingToInternet()) {

            /*if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")){
                new WebServices(this).RemoveCartItem(WebServices.Foodey_Services,
                        WebServices.ApiType.removeCartItem, Prefs.getUserId(this), cart_id);
            } else {

                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }*/

            return;
        }

    }

    private void CallCheckoutService() {
        if (this.mDetector.isConnectingToInternet()) {
            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).OrderPlaced(WebServices.Foodey_Services,
                        WebServices.ApiType.placeOrder, Prefs.getUserId(this), Prefs.getOrderId(this));
            } else {
                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong) , SnackBar.LENGTH_SHORT).show();
            }
            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.cartList) {
            if (!isSucces) {
                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                cartDetail = (CartDetail) response;
                /*if (cartDetail.getCartDetail().getCartList().size() != 0) {

                    cartLayout.setVisibility(View.VISIBLE);
                    //emptyText.setVisibility(View.GONE);
                   *//* grandTotal = cartData.getCartDetail().getGrandTotel() +"";
                    String price = "₹ " + cartData.getCartDetail().getGrandTotel();
                    vTAcoGrandTotalval.setText(price);
                    vTAcoShop.setText(cartData.getCartDetail().getCartList().get(0).getResturant().getName());
                    String location = cartData.getCartDetail().getCartList().get(0).getResturant().getAddress() + ", " + cartData.getCartDetail().getCartList().get(0).getResturant().getLocation();*//*
                    //vTAcoLocation.setText(location);
                    vTAcoCurrentval.setText(address);
                    dishAdapter = new DishAdapter();
                    vRAcoList.setLayoutManager(new LinearLayoutManager(this));
                    vRAcoList.setAdapter(dishAdapter);
                } else {
                    cartLayout.setVisibility(View.GONE);
                    //emptyText.setVisibility(View.VISIBLE);
                }*/
                /*cartAdapter = new CartAdapter();
                vRAmcList.setAdapter(cartAdapter);
                grandTotal = cartData.getCartDetail().getGrandTotel()+"";
                String total = "₹" + grandTotal;
                vTAmcSubval.setText(total);*/
                //Prefs.setTotal(MyCartActivity.this, cartData.getCartDetail().getGrandTotel() + "");

            }
        } else if (apiType == WebServices.ApiType.updateCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                UpdateCartItem updateCartItem = (UpdateCartItem) response;
                SnackBar.makeText(CheckOutActivity.this, updateCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                startActivity(new Intent(CheckOutActivity.this, CheckOutActivity.class));
                CheckOutActivity.this.finish();

            }
        }  else if (apiType == WebServices.ApiType.removeCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                RemoveCartItem removeCartItem = (RemoveCartItem) response;
                SnackBar.makeText(CheckOutActivity.this, removeCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                startActivity(new Intent(CheckOutActivity.this, CheckOutActivity.class));
                CheckOutActivity.this.finish();

            }
        } else if (apiType == WebServices.ApiType.placeOrder) {
            if (!isSucces) {
                SnackBar.makeText(CheckOutActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                PaymentCOD orderPlaced = (PaymentCOD) response;
                SnackBar.makeText(CheckOutActivity.this, orderPlaced.getMessage(), SnackBar.LENGTH_SHORT);
                Intent intent = new Intent(CheckOutActivity.this, OrderSummaryActivity.class);
                intent.putExtra("deliveryType", "");
                intent.putExtra("total", grandTotal);
                intent.putExtra("orderId", Prefs.getOrderId(this));
                //intent.putExtra("deliveryAddress", addressSelected);
                intent.putExtra("paymentmethod", "COD");
                intent.putExtra("paymentStatus","Pending");
                startActivity(intent);
                CheckOutActivity.this.finish();

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_aco_back:
                finish();
                break;
            case R.id.vL_aco_location:
                Intent intent = new Intent(this, ManageAddressActivity.class);
                intent.putExtra("from", "order");
                intent.putExtra("total",grandTotal);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.vT_aco_order_now:
                //new DialogFragmentWindow().show(getSupportFragmentManager(), "");
                //CallCheckoutService();
                Intent intent1 = new Intent(this, ManageAddressActivity.class);
                intent1.putExtra("from", "order");
                intent1.putExtra("total",grandTotal);
                intent1.putExtra("cart_id", cartDetail.getCartId());
                startActivity(intent1);
                overridePendingTransition(0, 0);

                break;

        }

    }

    private void setUpMapIfNeeded() {
        if (this.googleMap == null) {
            ((MapFragment) this.getFragmentManager().findFragmentById(R.id.vf_aco_map)).getMapAsync(this);
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
            createMarker(latitude, longitude, "", "", R.drawable.map_2, map);
            this.googleMap = map;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                CallService();
            } catch (IOException e) {
                e.printStackTrace();
            }
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


    public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dishes, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            /*Prefs.setOrderId(CheckOutActivity.this, cartData.getCartDetail().getCartList().get(0).getCart().getOrderId());
            holder.vT_add_name.setText(cartData.getCartDetail().getCartList().get(0).getCartDetail().get(position).getFoodId());
            String price = "₹" + " " + cartData.getCartDetail().getCartList().get(0).getCartDetail().get(position).getPrice();
            holder.vT_add_price.setText(price);
            quantity = Integer.parseInt(cartData.getCartDetail().getCartList().get(0).getCartDetail().get(position).getQuantity());
            holder.vT_add_quantity.setText(cartData.getCartDetail().getCartList().get(0).getCartDetail().get(position).getQuantity());*/

            /*if (cartData.getCartDetail().getCartList().get(position).getFood().getType().equals("Veg")) {
                holder.vI_add_veg.setImageDrawable(getResources().getDrawable(R.drawable.veg));
            } else {
                holder.vI_add_veg.setImageDrawable(getResources().getDrawable(R.drawable.non_veg));
            }*/


            holder.vT_add_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (quantity != 1) {
                        quantity = quantity - 1;
                        holder.vT_add_quantity.setText(String.valueOf(quantity));
                        //CallUpdateService(cartData.getCartDetail().getCartList().get(0).getCartDetail().get(position).getId(), String.valueOf(quantity));
                    } else {
                        Initializepopup();
                        //initializdeletePopup(cartData.getCartDetail().getCartList().get(0).getCartDetail().get(position).getId());
                    }
                }
            });

            holder.vT_add_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quantity = quantity + 1;
                    holder.vT_add_quantity.setText(String.valueOf(quantity));
                    //CallUpdateService(cartData.getCartDetail().getCartList().get(0).getCartDetail().get(position).getId(), String.valueOf(quantity));
                }
            });

        }

        public int getItemCount() {
            return 0;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_add_main, vL_add_hide;
            ImageView vI_add_veg;
            TextView vT_add_name, vT_add_price, vT_add_add, vT_add_decrease, vT_add_quantity, vT_add_increase;

            public MyViewHolder(View view) {
                super(view);
                this.vT_add_name = (TextView) view.findViewById(R.id.vT_add_name);
                this.vT_add_price = (TextView) view.findViewById(R.id.vT_add_price);
                this.vT_add_add = (TextView) view.findViewById(R.id.vT_add_add);
                this.vT_add_decrease = (TextView) view.findViewById(R.id.vT_add_decrease);
                this.vT_add_quantity = (TextView) view.findViewById(R.id.vT_add_quantity);
                this.vT_add_increase = (TextView) view.findViewById(R.id.vT_add_increase);

                this.vL_add_main = (LinearLayout) view.findViewById(R.id.vL_add_main);
                this.vL_add_hide = (LinearLayout) view.findViewById(R.id.vL_add_hide);
                this.vI_add_veg = (ImageView) view.findViewById(R.id.vI_add_veg);
                new Runnable() {
                    public void run() {
                        vT_add_name.setTypeface(NIRMALA);
                        vT_add_price.setTypeface(NIRMALA);
                        vT_add_add.setTypeface(NIRMALA);
                        vT_add_decrease.setTypeface(NIRMALA);
                        vT_add_quantity.setTypeface(NIRMALAB);
                        vT_add_increase.setTypeface(NIRMALA);


                    }
                }.run();
            }
        }
    }

    private void Initializepopup() {
        deletePopup = new Dialog(this);
        deletePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        deletePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        deletePopup.setContentView(R.layout.popup_shopping_delete);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializdeletePopup(final String cart_id) {
        deletePopup.setContentView(R.layout.popup_shopping_delete);
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
        deletePopup.show();

        final TextView vT_psd_delete = (TextView) deletePopup.findViewById(R.id.vT_psd_delete);
        final TextView vT_psd_confirmation = (TextView) deletePopup.findViewById(R.id.vT_psd_confirmation);
        final TextView vT_psd_yes = (TextView) deletePopup.findViewById(R.id.vT_psd_yes);
        final TextView vT_psd_cancel = (TextView) deletePopup.findViewById(R.id.vT_psd_cancel);

        vT_psd_delete.setText(getResources().getString(R.string.remove_product));
        vT_psd_confirmation.setText(getResources().getString(R.string.are_you_sure_remove));
        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_psd_delete.setTypeface(NIRMALAB);
                vT_psd_confirmation.setTypeface(NIRMALAB);
                vT_psd_yes.setTypeface(NIRMALAB);
                vT_psd_cancel.setTypeface(NIRMALAB);

            }
        };
        r.run();
        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
//        deletePopup.getWindow().setLayout(width, height);
        deletePopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deletePopup.getWindow().setGravity(Gravity.BOTTOM);


        vT_psd_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletePopup.isShowing()) {
                    deletePopup.dismiss();
                    CallRemoveService(cart_id);
                }
            }
        });

        vT_psd_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletePopup.isShowing())
                    deletePopup.dismiss();
            }
        });
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
        deletePopup.show();
    }


    public static class CategoryAdapter extends FragmentStatePagerAdapter {
        private String[] tabTitles = new String[]{"Tue (Today)",
                "Wed (Tommorrow)"};

        public CategoryAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return this.tabTitles.length;
        }

        public Fragment getItem(int position) {

            return new ScheduleFragment();
        }

        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

    }
}
