package com.geekhive.foodey.Cakes.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekhive.foodey.Cakes.beans.cakecartlist.CakeCartDetail;
import com.geekhive.foodey.Cakes.beans.cakecartlist.CartList;
import com.geekhive.foodey.Cakes.beans.cakechecksumPaytm.CakeChecksum;
import com.geekhive.foodey.Cakes.beans.cakepayment.CakePaymentCOD;
import com.geekhive.foodey.Cakes.beans.cakepaytmIntegration.CakePaytmResponse;
import com.geekhive.foodey.Cakes.beans.cakeremovecart.CakeRemoveCartItem;
import com.geekhive.foodey.Cakes.beans.cakeupdatecart.CakeUpdateCartItem;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeCheckOutActivityFinal extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    @BindView(R.id.payButton)
    Button payButton;
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
    @BindView(R.id.addressSelected)
    TextView addressSelected;
    @BindView(R.id.vT_edit)
    TextView vT_edit;

    @BindView(R.id.vT_instructions_txt)
    TextView vT_instructions_txt;
    @BindView(R.id.vL_instruction)
    LinearLayout vL_instruction;

    @BindView(R.id.instruction_layout)
    LinearLayout instruction_layout;

    @BindView(R.id.vT_remove)
    TextView vT_remove;


    @BindView(R.id.vL_coupon_layout)
    LinearLayout vL_coupon_layout;

    @BindView(R.id.vT_coupon_txt)
    TextView vT_coupon_txt;


    @BindView(R.id.vL_coupon_l)
    LinearLayout vL_coupon_l;

    @BindView(R.id.vI_coupon)
    ImageView vI_coupon;

    @BindView(R.id.vI_instruction)
    ImageView vI_instruction;

    @BindView(R.id.vT_coupon_try)
    TextView vT_coupon_try;
    @BindView(R.id.vT_instructions)
    TextView vT_instructions;
    @BindView(R.id.vT_coupon)
    TextView vT_coupon;

    @BindView(R.id.vL_savings)
    LinearLayout vL_savings;

    @BindView(R.id.vL_offer_discount)
    LinearLayout vL_offer_discount;

    @BindView(R.id.vT_offer_discount)
    TextView vT_offer_discount;

    String qty;
    int quantity = 1;
    DishAdapter dishAdapter;
    ConnectionDetector mDetector;

    CartList cartList;
    Dialog deletePopup;
    String grandTotal;
    String orderId;

    String order_id, userId;

    List<CakeCartDetail> cartDetail;

    String address;

    Dialog paypopup, instructionPopup;

    Bundle payResponse;

    String instruction_desc = "";
    String coupon = "";

    String dis;

    Handler mHandlerTime;
    Runnable mRunnableTimeOut;

    private int SPLASH_TIME_OUT = 1000;
    String cart_id = "";
    String cardDetailId = "";
    String lat = "";
    String lang = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_check_out_final);
        ButterKnife.bind(this);
        setvalues();
        initializeFonts();
        setTypeFace();
        CallService();
    }
    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getAssets(), "NIRMALAS.TTF");
    }
    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        payButton.setOnClickListener(this);
        vI_aac_summary_back.setOnClickListener(this);
        removeCartItems.setOnClickListener(this);
        vT_edit.setOnClickListener(this);
        address = getIntent().getStringExtra("address");
        dis = getIntent().getStringExtra("distance");
        addressSelected.setText(address);

        vT_remove.setOnClickListener(this);
        vL_coupon_layout.setOnClickListener(this);
        vL_coupon_l.setOnClickListener(this);
        vI_coupon.setOnClickListener(this);
        vI_instruction.setOnClickListener(this);
        vT_coupon_try.setOnClickListener(this);
        vL_instruction.setOnClickListener(this);

        address = getIntent().getStringExtra("address");
        addressSelected.setText(address);


        try {
            instruction_desc = getIntent().getStringExtra("instruction_txt");
        } catch (Exception e) {
            instruction_desc = "";
            Log.e("Error", e.toString());
        }

        if (instruction_desc != null) {
            if (!instruction_desc.equals("")) {

                vT_instructions_txt.setText(instruction_desc);
                instruction_layout.setVisibility(View.VISIBLE);
                vL_instruction.setVisibility(View.GONE);


            } else {
                vL_instruction.setVisibility(View.VISIBLE);
                instruction_layout.setVisibility(View.GONE);
            }
        } else {
            vL_instruction.setVisibility(View.VISIBLE);
            instruction_layout.setVisibility(View.GONE);

        }


        try {
            coupon = getIntent().getStringExtra("coupon_value");
        } catch (Exception e) {
            coupon = "";
            Log.e("Error", e.toString());
        }

        if (coupon != null) {
            if (!coupon.equals("")) {
                vT_coupon_txt.setText(coupon);
                vL_coupon_layout.setVisibility(View.VISIBLE);
                vL_coupon_l.setVisibility(View.GONE);


            } else {
                vL_coupon_l.setVisibility(View.VISIBLE);
                vL_coupon_layout.setVisibility(View.GONE);
            }
        } else {
            vL_coupon_l.setVisibility(View.VISIBLE);
            vL_coupon_layout.setVisibility(View.GONE);
        }


    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                vT_instructions.setTypeface(NIRMALA);
                vT_coupon.setTypeface(NIRMALA);
                vT_coupon_try.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.payButton:
                InitializePaymentpopup();
                initializePayPopup();
                break;
            case R.id.vI_aac_summary_back:
                finish();
                break;
            case R.id.removeCartItems:
                Initializermovepopup();
                initializeremove(cartList.getCartList().getCakeCart().getId());
                break;
            case R.id.vT_edit:
                startActivity(new Intent(this, CakeManageAddressActivity.class));
                finish();
                break;
            case R.id.vL_instruction:
                vL_instruction.setVisibility(View.GONE);
                InitializeInstructionPopup();
                initializeInsPopup();
                break;
            case R.id.vT_remove:
                instruction_layout.setVisibility(View.GONE);
                vL_instruction.setVisibility(View.VISIBLE);
                break;
            case R.id.vL_coupon_l:
                //vL_coupon_l.setVisibility(View.GONE);
                //instruction_desc = getIntent().getStringExtra("instruction_txt");
                if(instruction_desc != null){
                    if (!instruction_desc.equals("")){
                        Intent intentc = new Intent(CakeCheckOutActivityFinal.this, CakeAddCoupon.class);
                        intentc.putExtra("instruction_txt", instruction_desc);
                        intentc.putExtra("address", address);
                        intentc.putExtra("cartId", cartList.getCartList().getCakeCart().getId());
                        startActivity(intentc);
                    }
                }else {
                    Intent intentc = new Intent(CakeCheckOutActivityFinal.this, CakeAddCoupon.class);
                    intentc.putExtra("instruction_txt", "");
                    intentc.putExtra("address", address);
                    intentc.putExtra("cartId", cartList.getCartList().getCakeCart().getId());
                    startActivity(intentc);
                }


                break;
            case R.id.vL_coupon_layout:
                if (!instruction_desc.equals("") || !instruction_desc.isEmpty()){
                    Intent intentc = new Intent(CakeCheckOutActivityFinal.this,CakeAddCoupon.class);
                    intentc.putExtra("instruction_txt", instruction_desc);
                    intentc.putExtra("address", address);
                    intentc.putExtra("cartId", cartList.getCartList().getCakeCart().getId());
                    startActivity(intentc);
                } else {
                    Intent intentc = new Intent(CakeCheckOutActivityFinal.this,CakeAddCoupon.class);
                    intentc.putExtra("instruction_txt", "");
                    intentc.putExtra("address", address);
                    intentc.putExtra("cartId", cartList.getCartList().getCakeCart().getId());
                    startActivity(intentc);
                }

                break;
        }
    }
    private void InitializePaymentpopup() {
        paypopup = new Dialog(CakeCheckOutActivityFinal.this);
        paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        paypopup.setContentView(R.layout.payment_popup);
        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
    }

    private void initializePayPopup() {
        paypopup.setContentView(R.layout.payment_popup);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();

        final Button vB_yes = paypopup.findViewById(R.id.vB_yes);
        final Button vB_no = paypopup.findViewById(R.id.vB_no);


//        final TextView view_cart = (TextView) paypopup.findViewById(R.id.view_cart);
//        final ImageView view_cart_img =  paypopup.findViewById(R.id.view_cart_img);


        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paypopup.getWindow().setGravity(Gravity.CENTER);


        vB_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paypopup.isShowing())
                    paypopup.dismiss();
                InitializePaypopup();
                initializePopup();


            }
        });
        vB_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                paypopup.dismiss();


            }
        });


        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();
    }

    private void InitializePaypopup() {
        paypopup = new Dialog(this);
        paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        paypopup.setContentView(R.layout.cake_popup_pay);
        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
    }

    private void initializePopup() {
        paypopup.setContentView(R.layout.cake_popup_pay);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();

        final TextView vT_psd_pay = (TextView) paypopup.findViewById(R.id.vT_psd_pay);
        final TextView vT_psd_cod = (TextView) paypopup.findViewById(R.id.vT_psd_cod);
        final TextView vT_psd_online = (TextView) paypopup.findViewById(R.id.vT_psd_online);


        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paypopup.getWindow().setGravity(Gravity.BOTTOM);


        vT_psd_cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paypopup.isShowing()) {
                    paypopup.dismiss();
                    CallCheckoutService();
                }
            }
        });

        vT_psd_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paypopup.isShowing()) {
                    paypopup.dismiss();
                    CallChecksumService();
                }
            }
        });
        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();
    }

    private void InitializeInstructionPopup() {
        instructionPopup = new Dialog(this);
        instructionPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        instructionPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        instructionPopup.setContentView(R.layout.popup_cake_instraction);
        instructionPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        instructionPopup.setCancelable(false);
        instructionPopup.setCanceledOnTouchOutside(false);
    }

    private void initializeInsPopup() {
        instructionPopup.setContentView(R.layout.popup_cake_instraction);
        instructionPopup.setCancelable(false);
        instructionPopup.setCanceledOnTouchOutside(false);
        instructionPopup.show();

        final EditText vL_ins_desc = instructionPopup.findViewById(R.id.vL_ins_desc);
        final Button vL_add_btn = instructionPopup.findViewById(R.id.vL_add_btn);
        final ImageView close_popup = instructionPopup.findViewById(R.id.close_popup);


//        final TextView view_cart = (TextView) paypopup.findViewById(R.id.view_cart);
//        final ImageView view_cart_img =  paypopup.findViewById(R.id.view_cart_img);


        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        instructionPopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        instructionPopup.getWindow().setGravity(Gravity.BOTTOM);


        vL_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instruction_desc = vL_ins_desc.getText().toString();
                instruction_layout.setVisibility(View.VISIBLE);
                //vT_instructions_txt.setVisibility(View.VISIBLE);
                vT_instructions_txt.setText(instruction_desc);

                if (instructionPopup.isShowing()) {
                    instructionPopup.dismiss();
                }

            }
        });
        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vL_instruction.setVisibility(View.VISIBLE);
                if (instructionPopup.isShowing()) {
                    instructionPopup.dismiss();
                }


            }
        });


        instructionPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        instructionPopup.setCancelable(true);
        instructionPopup.setCanceledOnTouchOutside(true);
        instructionPopup.show();
    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    private void CallChecksumService() {
        if (this.mDetector.isConnectingToInternet()) {
            this.order_id = generateString();
            this.userId = generateString();

                new WebServices(this).GenerateCheckSum(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.checksum, order_id, userId, grandTotal);//grandTotal
            } else {

                SnackBar.makeText(CakeCheckOutActivityFinal.this, (int) R.string.something_wrong, SnackBar.LENGTH_SHORT).show();
            }

            return;
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).GetCartList(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.cartList, Prefs.getUserId(this));
            } else {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
    }


    private void CallUpdateService(String distanceAdd) {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).UpdateCartItem(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.updateCartItem,Prefs.getUserId(this), cart_id, String.valueOf(quantity), cardDetailId,distanceAdd) ;
            } else {

                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong) , SnackBar.LENGTH_SHORT).show();
            }

            return;
    }


    private void CallRemoveService(String cart_id) {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).RemoveCart(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.removeCartItem, Prefs.getUserId(this), cart_id);
            } else {

                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
    }

    private void CallRemoveItemService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).RemoveCartItem(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.removeCartItem, cardDetailId, cart_id,distance);
            } else {

                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
    }

    private void CallAdd() {

        String str_origin = Prefs.getUserLat(this) + "," + Prefs.getUserLang(this);
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
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    private void CallAddRItem() {

        String str_origin = Prefs.getUserLat(this) + "," + Prefs.getUserLang(this);
        // Destination of route
        String str_dest = lat + "," + lang;

        String key = getResources().getString(R.string.google_map_api);
        CallMapServiceRItem(str_origin, str_dest, key);


    }

    private void CallMapServiceRItem(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistanceRes, origin, destination, key);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }



    private void CallCheckoutService() {
        if (this.mDetector.isConnectingToInternet()) {
                new WebServices(this).OrderPlaced(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.placeOrder, Prefs.getUserId(this), Prefs.getOrderId(this));
            } else {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong) , SnackBar.LENGTH_SHORT).show();
            }
            return;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (payResponse != null){
            if (payResponse.getString("STATUS").equals("TXN_SUCCESS")){
                callPayment();
            }

        }
    }

    public void callPayment(){
        if (payResponse.getString("STATUS").equals("TXN_SUCCESS")){
            new WebServices(CakeCheckOutActivityFinal.this).PaytmResponse(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.paytmresponse, Prefs.getOrderId(CakeCheckOutActivityFinal.this), Prefs.getUserId(CakeCheckOutActivityFinal.this),
                    payResponse.getString("TXNID"), payResponse.getString("TXNAMOUNT"),
                    payResponse.getString("PAYMENTMODE"), payResponse.getString("CURRENCY"),
                    payResponse.getString("TXNDATE"), payResponse.getString("STATUS"),
                    payResponse.getString("RESPCODE"), payResponse.getString("RESPMSG"),
                    payResponse.getString("GATEWAYNAME"), payResponse.getString("BANKTXNID"),
                    payResponse.getString("BANKNAME"));
        }


    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.cartList) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                cartList = (CartList) response;
                if (cartList.getCartList() != null) {
                    if (cartList.getCartList().getCakeCartDetail().size() != 0) {
                        cartSumLin.setVisibility(View.VISIBLE);
                        payButton.setVisibility(View.VISIBLE);
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
                    }
                } else {
                    cartSumLin.setVisibility(View.GONE);
                    payButton.setVisibility(View.GONE);
                    SnackBar.makeText(this, "Some Thing Went Wrong", SnackBar.LENGTH_SHORT).show();
                }

                if (coupon != null) {
                    vL_offer_discount.setVisibility(View.VISIBLE);
                    String disc = "-₹ " + cartList.getCartList().getCakeCart().getOfferDiscount();
                    vT_offer_discount.setText(disc);

                }



                if(cartList.getCartList().getCakeCart().getDiscount().equals("0")&&(cartList.getCartList().getCakeCart().getDiscount().equals(""))){
                    vL_savings.setVisibility(View.GONE);
                }

            }
        } else if (apiType == WebServices.ApiType.checksum) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeChecksum checksum = (CakeChecksum) response;

                Log.e("Order ID", Prefs.getOrderId(this));

                PaytmPGService Service = PaytmPGService.getProductionService();
                Map<String, String> paramMap = new HashMap<String, String>();

                // these are mandatory parameters

                paramMap.put("CALLBACK_URL", checksum.getGenerateChecksum().getCALLBACKURL());
                paramMap.put("CHANNEL_ID", "WAP");
                paramMap.put("CHECKSUMHASH", checksum.getGenerateChecksum().getCHECKSUMHASH());
                paramMap.put("CUST_ID", checksum.getGenerateChecksum().getCUSTID());
                paramMap.put("INDUSTRY_TYPE_ID", "Retail");
                paramMap.put("MID", checksum.getGenerateChecksum().getMID());
                paramMap.put("ORDER_ID", checksum.getGenerateChecksum().getORDERID());
                paramMap.put("TXN_AMOUNT", checksum.getGenerateChecksum().getTXNAMOUNT());
                paramMap.put("WEBSITE", "DEFAULT");
                //paramMap.put("MOBILE_NO","9999999999");
                //paramMap.put("EMAIL","abc@gmail.com");


                PaytmOrder Order = new PaytmOrder((HashMap<String, String>) paramMap);


                Service.initialize(Order, null);

                Service.startPaymentTransaction(this, true, true,
                        new PaytmPaymentTransactionCallback() {
                            @Override
                            public void someUIErrorOccurred(String inErrorMessage) {
                                // Some UI Error Occurred in Payment Gateway Activity.
                                // // This may be due to initialization of views in
                                // Payment Gateway Activity or may be due to //
                                // initialization of webview. // Error Message details
                                // the error occurred.
                            }

                            @Override
                            public void onTransactionResponse(Bundle inResponse) {
                                Log.d("LOG", "Payment Transaction is successful " + inResponse);
                                inResponse.getString("STATUS");
                                Log.e("Payment response", inResponse.toString());
                                Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();

                                if (mDetector.isConnectingToInternet()) {
                                    if (inResponse.getString("STATUS").equals("TXN_SUCCESS")) {
                                        new WebServices(CakeCheckOutActivityFinal.this).PaytmResponse(WebServices.Foodey_Cakelocation_Services,
                                                WebServices.ApiType.paytmresponse, Prefs.getOrderId(CakeCheckOutActivityFinal.this), Prefs.getUserId(CakeCheckOutActivityFinal.this),
                                                inResponse.getString("TXNID"), inResponse.getString("TXNAMOUNT"),
                                                inResponse.getString("PAYMENTMODE"), inResponse.getString("CURRENCY"),
                                                inResponse.getString("TXNDATE"), inResponse.getString("STATUS"),
                                                inResponse.getString("RESPCODE"), inResponse.getString("RESPMSG"),
                                                inResponse.getString("GATEWAYNAME"), inResponse.getString("BANKTXNID"),
                                                inResponse.getString("BANKNAME"));
                                    } else {

                                        SnackBar.makeText(CakeCheckOutActivityFinal.this, inResponse.getString("RESPMSG"), SnackBar.LENGTH_SHORT).show();
                                    }

                                    return;
                                }
                            }

                            @Override
                            public void networkNotAvailable() { // If network is not
                                // available, then this
                                // method gets called.
                            }

                            @Override
                            public void clientAuthenticationFailed(String inErrorMessage) {
                                // This method gets called if client authentication
                                // failed. // Failure may be due to following reasons //
                                // 1. Server error or downtime. // 2. Server unable to
                                // generate checksum or checksum response is not in
                                // proper format. // 3. Server failed to authenticate
                                // that client. That is value of payt_STATUS is 2. //
                                // Error Message describes the reason for failure.
                            }

                            @Override
                            public void onErrorLoadingWebPage(int iniErrorCode,
                                                              String inErrorMessage, String inFailingUrl) {

                            }

                            // had to be added: NOTE
                            @Override
                            public void onBackPressedCancelTransaction() {
                                Toast.makeText(CakeCheckOutActivityFinal.this, "Back pressed. Transaction cancelled", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                                Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
                                Toast.makeText(getBaseContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
                            }

                        });


            }
        } else if (apiType == WebServices.ApiType.paytmresponse) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakePaytmResponse paytmResponse = (CakePaytmResponse) response;
                /*SnackBar.makeText(CheckOutActivityFinal.this, paytmResponse.getMessage(), SnackBar.LENGTH_SHORT);
                Intent intent = new Intent(CheckOutActivityFinal.this, SuccessActivity.class);
                intent.putExtra("deliveryType", "");
                //intent.putExtra("total", grandTotal);
                intent.putExtra("deliveryAddress", addressSelected);
                intent.putExtra("paymentmethod", "Online");
                intent.putExtra("paymentStatus", "Completed");
                startActivity(intent);
                CheckOutActivity.this.finish();*/

                SnackBar.makeText(CakeCheckOutActivityFinal.this, paytmResponse.getMessage(), SnackBar.LENGTH_SHORT);
                Intent intent = new Intent(CakeCheckOutActivityFinal.this, CakeOrderSummaryActivity.class);
                intent.putExtra("deliveryType", "");
                intent.putExtra("total", grandTotal);
                intent.putExtra("orderId", Prefs.getOrderId(this));
                intent.putExtra("deliveryAddress", address);
                intent.putExtra("paymentmethod", "Online");
                intent.putExtra("paymentStatus", "Completed");
                startActivity(intent);
                CakeCheckOutActivityFinal.this.finish();
            }
        }else if (apiType == WebServices.ApiType.updateCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeUpdateCartItem updateCartItem = (CakeUpdateCartItem) response;
                SnackBar.makeText(CakeCheckOutActivityFinal.this, updateCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();

                Intent intent = new Intent(CakeCheckOutActivityFinal.this, CakeCheckOutActivityFinal.class);
                intent.putExtra("distance", dis);
                startActivity(intent);
                CakeCheckOutActivityFinal.this.finish();

            }
        }  else if (apiType == WebServices.ApiType.removeCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeRemoveCartItem removeCartItem = (CakeRemoveCartItem) response;
                SnackBar.makeText(CakeCheckOutActivityFinal.this, removeCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                Intent intent = new Intent(CakeCheckOutActivityFinal.this, CakeCheckOutActivityFinal.class);
                intent.putExtra("distance", dis);
                startActivity(intent);
                CakeCheckOutActivityFinal.this.finish();

            }
        } else if (apiType == WebServices.ApiType.placeOrder) {
            if (!isSucces) {
                SnackBar.makeText(CakeCheckOutActivityFinal.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakePaymentCOD orderPlaced = (CakePaymentCOD) response;
                SnackBar.makeText(CakeCheckOutActivityFinal.this, orderPlaced.getMessage(), SnackBar.LENGTH_SHORT);
                Intent intent = new Intent(CakeCheckOutActivityFinal.this, CakeOrderSummaryActivity.class);
                intent.putExtra("deliveryType", "");
                intent.putExtra("total", grandTotal);
                intent.putExtra("orderId", Prefs.getOrderId(this));
                intent.putExtra("deliveryAddress", address);
                intent.putExtra("paymentmethod", "COD");
                intent.putExtra("paymentStatus","Pending");
                startActivity(intent);
                CakeCheckOutActivityFinal.this.finish();

            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        String dis = distance.replace(" km", "");
                        CallUpdateService(dis);
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.mapdistanceRes) {
            if (!isSucces) {
               SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        String dis = distance.replace(" km", "");
                        CallRemoveItemService(dis);
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_dishes, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Prefs.setOrderId(CakeCheckOutActivityFinal.this, cartList.getCartList().getCakeCart().getOrderId());
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
            }
*/


            holder.vT_add_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cartDetail.size() == 1){
                        if (holder.vT_add_quantity.getText().toString().equals("1")){
                            Initializermovepopup();
                            initializeremove(cartDetail.get(position).getCartId());
                        } else {
                            if (quantity != 1) {
                                quantity = quantity - 1;
                                holder.vT_add_quantity.setText(String.valueOf(quantity));
                                cart_id = cartDetail.get(position).getCartId();
                                cardDetailId = cartDetail.get(position).getId();
                                CallAdd();
                                //CallUpdateService(cartDetail.get(position).getCartId(), String.valueOf(quantity), cartDetail.get(position).getId());
                            } else {
                                Initializepopup();
                                initializdeletePopup(cartDetail.get(position).getCartId(), cartDetail.get(position).getId());
                            }
                        }
                    } else {
                        if (quantity != 1) {
                            quantity = quantity - 1;
                            holder.vT_add_quantity.setText(String.valueOf(quantity));
                            cart_id = cartDetail.get(position).getCartId();
                            cardDetailId = cartDetail.get(position).getId();
                            CallAdd();
                            //CallUpdateService(cartDetail.get(position).getCartId(), String.valueOf(quantity), cartDetail.get(position).getId());
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
                    cart_id = cartDetail.get(position).getCartId();
                    cardDetailId = cartDetail.get(position).getId();
                    CallAdd();
                    //CallUpdateService(cartDetail.get(position).getCartId(), String.valueOf(quantity), cartDetail.get(position).getId());
                }
            });

        }

        public int getItemCount() {
            return cartDetail.size();
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

    private void initializdeletePopup(final String cart, final String DetailId) {
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
                    cardDetailId = cart;
                    cart_id = DetailId;
                    CallAddRItem();
                    //CallRemoveItemService(cardDetailId, cart_id);
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
