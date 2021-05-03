package com.geekhive.foodey.Cakes.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.beans.cakecartlist.CakeCartDetail;
import com.geekhive.foodey.Cakes.beans.cakecartlist.CartList;
import com.geekhive.foodey.Cakes.beans.cakemapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Cakes.beans.cakeremovecart.CakeRemoveCartItem;
import com.geekhive.foodey.Cakes.beans.cakeupdatecart.CakeUpdateCartItem;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeCheckOutActivityNew extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    @BindView(R.id.checkButton)
    Button checkButton;
    @BindView(R.id.vR_aco_list)
    RecyclerView vRAcoList;
    @BindView(R.id.cartSumLin)
    LinearLayout cartSumLin;
    @BindView(R.id.vT_rest_name)
    TextView vT_rest_name;
    @BindView(R.id.vT_grand_total)
    TextView vT_grand_total;
    @BindView(R.id.vT_discount)
    TextView vT_discount;
    @BindView(R.id.vT_aco_sub_amount)
    TextView vT_aco_sub_amount;
    @BindView(R.id.vT_taxes)
    TextView vT_taxes;
    @BindView(R.id.vT_packaging)
    TextView vT_packaging;
    @BindView(R.id.vT_delivery)
    TextView vT_delivery;
    @BindView(R.id.vT_savings)
    TextView vT_savings;
    @BindView(R.id.vI_aac_summary_back)
    ImageView vI_aac_summary_back;
    @BindView(R.id.removeCartItems)
    TextView removeCartItems;
    @BindView(R.id.view_all)
    TextView view_all;
    @BindView(R.id.vL_savings)
    LinearLayout vL_savings;


    String qty;
    int quantity = 1;
    DishAdapter dishAdapter;
    ConnectionDetector mDetector;

    CartList cartList;
    Dialog deletePopup;
    String grandTotal;
    String orderId;
    String dis, lati, longi;

    List<CakeCartDetail> cartDetail;

    @BindView(R.id.vI_coupon)
    ImageView vI_coupon;

    @BindView(R.id.vL_coupon)
    LinearLayout vLCoupon;

    @BindView(R.id.vL_coupon_txt)
    LinearLayout vL_coupon_txt;

    @BindView(R.id.vT_coupon_txt)
    TextView vT_coupon_txt;

    @BindView(R.id.vT_coupon_try)
    TextView vT_coupon_try;
    @BindView(R.id.vT_coupon)
    TextView vT_coupon;

    String coupon = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_check_out_new);
        ButterKnife.bind(this);
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");
        coupon = getIntent().getStringExtra("coupon_value");
        setvalues();
        CallService();


        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CakeCheckOutActivityNew.this, CakeCartitemActivity.class);
                startActivity(intent);
            }
        });

    }

    private void CallAdd(String lat, String lang) {

        String str_origin = Prefs.getUserLat(CakeCheckOutActivityNew.this) + "," + Prefs.getUserLang(CakeCheckOutActivityNew.this);
        // Destination of route
        String str_dest = lat + "," + lang;
        String key = getResources().getString(R.string.google_map_api);
        CallMapService(str_origin, str_dest, key);
    }

    private void CallMapService(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistance, origin, destination, key);
        } else {
            SnackBar.makeText(CakeCheckOutActivityNew.this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        checkButton.setOnClickListener(this);
        vI_aac_summary_back.setOnClickListener(this);
        removeCartItems.setOnClickListener(this);
        vI_coupon.setOnClickListener(this);
        vLCoupon.setOnClickListener(this);
        vL_coupon_txt.setOnClickListener(this);
        vT_coupon_try.setOnClickListener(this);

        try{
            coupon = getIntent().getStringExtra("coupon_value");
            // instruction_desc=getIntent().getStringExtra("instruction_txt");
        } catch (Exception e){
            coupon = "";
            Log.e("Error", e.toString());
        }

        if (coupon != null){
            if(!coupon.equals("")){
                vT_coupon_txt.setText(coupon+" % discount");
//                vT_instructions_txt.setText(instruction_desc);
//                instruction_layout.setVisibility(View.VISIBLE);

                vL_coupon_txt.setVisibility(View.GONE);
                vLCoupon.setVisibility(View.GONE);


            }  else {
                vLCoupon.setVisibility(View.GONE);
                vL_coupon_txt.setVisibility(View.GONE);
            }
        } else {
            vLCoupon.setVisibility(View.GONE);
            vL_coupon_txt.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkButton:
                Intent intent = new Intent(CakeCheckOutActivityNew.this, CakeManageAddressActivity.class);
                intent.putExtra("order_id", orderId);
                intent.putExtra("distance", dis);
                startActivity(intent);
                break;
            case R.id.vI_aac_summary_back:
                finish();
                break;
            case R.id.removeCartItems:
                Initializermovepopup();
                initializeremove(cartList.getCartList().getCakeCart().getId());
                break;
            case R.id.vI_coupon:
                Intent i = new Intent(CakeCheckOutActivityNew.this, CakeAddCoupon.class);
                i.putExtra("lati", lati);
                i.putExtra("longi", longi);
                startActivity(i);
            case R.id.vT_coupon_try:
                Intent intentc = new Intent(CakeCheckOutActivityNew.this, CakeAddCoupon.class);
                intentc.putExtra("lati", lati);
                intentc.putExtra("longi", longi);
                startActivity(intentc);
                break;
        }
    }
    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GetCartList(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.cartList, Prefs.getUserId(this));
        } else {
            SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }

    private void CallUpdateService(String cart_id, String quantity, String cardDetailId) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).UpdateCartItem(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.updateCartItem, Prefs.getUserId(this), cart_id, quantity, cardDetailId, dis);
        } else {

            SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }

    private void CallRemoveService(String cart_id) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).RemoveCart(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.removeCartItem, Prefs.getUserId(this), cart_id);
        } else {

            SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }

    private void CallRemoveItemService(String cartdetail_id, String cart_id) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).RemoveCartItem(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.removeCartItem, cartdetail_id, cart_id, dis);
        } else {

            SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.cartList) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                cartList = (CartList) response;
                if (cartList.getCartList() != null) {
                    if (cartList.getCartList().getCakeCartDetail().size() != 0) {
                        lati = cartList.getCartList().getCakeStore().getLatitude();
                        longi = cartList.getCartList().getCakeStore().getLongitude();
                        cartSumLin.setVisibility(View.VISIBLE);
                        checkButton.setVisibility(View.VISIBLE);
                        orderId = cartList.getCartList().getCakeCart().getOrderId();
                        grandTotal = cartList.getCartList().getCakeCart().getGrandTotal();
                        String price = "₹ " + grandTotal;
                        vT_grand_total.setText(price);
                        vT_rest_name.setText(cartList.getCartList().getCakeStore().getName());
                        String disc = "-₹ " + cartList.getCartList().getCakeCartDetail().get(0).getCake().getDiscount();
                        vT_discount.setText(disc);
                        String taxes = "₹ " + cartList.getCartList().getCakeCart().getTax();
                        vT_taxes.setText(taxes);
                        String packages = "₹ " + cartList.getCartList().getCakeCart().getPackingCharge();
                        vT_packaging.setText(packages);
                        String deliveryCharges = "₹ " + cartList.getCartList().getCakeCart().getDelivery();
                        vT_delivery.setText(deliveryCharges);
                        String subamount = "₹ " + cartList.getCartList().getCakeCart().getTotel();
                        vT_aco_sub_amount.setText(subamount);
                        String saving = "₹ " + cartList.getCartList().getCakeCartDetail().get(0).getDiscount();
                        vT_savings.setText(saving);
                        cartDetail = cartList.getCartList().getCakeCartDetail();
                        dishAdapter = new DishAdapter();
                        vRAcoList.setLayoutManager(new LinearLayoutManager(this));
                        vRAcoList.setAdapter(dishAdapter);
                        if (cartList.getCartList().getCakeCartDetail().size() >= 5) {
                            view_all.setVisibility(View.VISIBLE);
                        }
                        CallAdd(lati, longi);
                    }
                } else {
                    cartSumLin.setVisibility(View.GONE);
                    checkButton.setVisibility(View.GONE);
                    SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
                }
                if(cartList.getCartList() == null){
                    vL_savings.setVisibility(View.GONE);
                }


            }
        } else if (apiType == WebServices.ApiType.updateCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeUpdateCartItem updateCartItem = (CakeUpdateCartItem) response;
                SnackBar.makeText(CakeCheckOutActivityNew.this, updateCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                Intent intent = new Intent(CakeCheckOutActivityNew.this, CakeCheckOutActivityNew.class);
                intent.putExtra("order_id", orderId);
                intent.putExtra("lati", lati);
                intent.putExtra("longi", longi);
                startActivity(intent);
                CakeCheckOutActivityNew.this.finish();

            }
        } else if (apiType == WebServices.ApiType.removeCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeRemoveCartItem removeCartItem = (CakeRemoveCartItem) response;
                SnackBar.makeText(CakeCheckOutActivityNew.this, removeCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                Intent intent = new Intent(CakeCheckOutActivityNew.this, CakeCheckOutActivityNew.class);
                intent.putExtra("distance", dis);
                startActivity(intent);
                CakeCheckOutActivityNew.this.finish();
            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null || getDistanceFromMap.getStatus() != "ZERO RESULTS") {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        dis = distance.replace(" km", "");
                        //CallService();
                    }
                } else {
                    SnackBar.makeText(CakeCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_dishes, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Prefs.setOrderId(CakeCheckOutActivityNew.this, cartList.getCartList().getCakeCart().getOrderId());
            holder.vT_add_name.setText(cartDetail.get(position).getCake().getProductName());
            String price = "₹" + " " + cartDetail.get(position).getCake().getPrice();
            holder.vT_add_price.setText(price);
            quantity = Integer.parseInt(cartDetail.get(position).getQuantity());
            qty = quantity + "";
            holder.vT_add_quantity.setText(qty);
/*
            if (cartDetail.get(position).getFood().getType().equals("Veg")) {
                holder.vI_add_veg.setImageDrawable(getResources().getDrawable(R.drawable.veg));
            } else {
                holder.vI_add_veg.setImageDrawable(getResources().getDrawable(R.drawable.non_veg));
            }*/


            holder.vT_add_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cartDetail.size() == 1) {
                        if (holder.vT_add_quantity.getText().toString().equals("1")) {
                            Initializermovepopup();
                            initializeremove(cartDetail.get(position).getCartId());
                        } else {
                            if (quantity != 1) {
                                quantity = quantity - 1;
                                holder.vT_add_quantity.setText(String.valueOf(quantity));
                                CallUpdateService(cartDetail.get(position).getCartId(), String.valueOf(quantity), cartDetail.get(position).getId());
                            } else {
                                Initializepopup();
                                initializdeletePopup(cartDetail.get(position).getCartId(), cartDetail.get(position).getId());
                            }
                        }
                    } else {
                        if (quantity != 1) {
                            quantity = quantity - 1;
                            holder.vT_add_quantity.setText(String.valueOf(quantity));
                            CallUpdateService(cartDetail.get(position).getCartId(), String.valueOf(quantity), cartDetail.get(position).getId());
                        } else {
                            Initializepopup();
                            initializdeletePopup(cartDetail.get(position).getCartId(), cartDetail.get(position).getId());
                        }
                    }
                }
            });

            holder.vT_add_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quantity = quantity + 1;
                    holder.vT_add_quantity.setText(String.valueOf(quantity));
                    CallUpdateService(cartDetail.get(position).getCartId(), String.valueOf(quantity), cartDetail.get(position).getId());
                }
            });

        }

        public int getItemCount() {
            if (cartDetail.size() > 5) {
                return 5;
            } else {
                return cartDetail.size();
            }

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
            }
        }
    }


    private void Initializepopup() {
        deletePopup = new Dialog(this);
        deletePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        deletePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        deletePopup.setContentView(R.layout.cake_popup_shopping_delete);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializdeletePopup(final String cart_id, final String cardDetailId) {
        deletePopup.setContentView(R.layout.cake_popup_shopping_delete);
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
        deletePopup.show();

        final TextView vT_psd_delete = (TextView) deletePopup.findViewById(R.id.vT_psd_delete);
        final TextView vT_psd_confirmation = (TextView) deletePopup.findViewById(R.id.vT_psd_confirmation);
        final TextView vT_psd_yes = (TextView) deletePopup.findViewById(R.id.vT_psd_yes);
        final TextView vT_psd_cancel = (TextView) deletePopup.findViewById(R.id.vT_psd_cancel);

        vT_psd_delete.setText(getResources().getString(R.string.remove_product));
        vT_psd_confirmation.setText(getResources().getString(R.string.are_you_sure_remove));
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
                    CallRemoveItemService(cardDetailId, cart_id);
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

    private void Initializermovepopup() {
        deletePopup = new Dialog(this);
        deletePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        deletePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        deletePopup.setContentView(R.layout.cake_popup_shopping_delete);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializeremove(final String cart_id) {
        deletePopup.setContentView(R.layout.cake_popup_shopping_delete);
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
        deletePopup.show();

        final TextView vT_psd_delete = (TextView) deletePopup.findViewById(R.id.vT_psd_delete);
        final TextView vT_psd_confirmation = (TextView) deletePopup.findViewById(R.id.vT_psd_confirmation);
        final TextView vT_psd_yes = (TextView) deletePopup.findViewById(R.id.vT_psd_yes);
        final TextView vT_psd_cancel = (TextView) deletePopup.findViewById(R.id.vT_psd_cancel);

        vT_psd_delete.setText(getResources().getString(R.string.remove_cart));
        vT_psd_confirmation.setText(getResources().getString(R.string.are_you_sure_remove_cart));
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

}
