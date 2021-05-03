package com.geekhive.foodey.Food.orderhistory;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geekhive.foodey.Food.beans.order.CartDetail;
import com.geekhive.foodey.Food.beans.order.OrderHistory;
import com.geekhive.foodey.Food.beans.paytmIntegration.PaytmResponse;
import com.geekhive.foodey.Food.beans.ratingDelivery.RatingDelivery;
import com.geekhive.foodey.Food.beans.ratingRes.RatingRes;
import com.geekhive.foodey.Food.beans.restdelchecksum.RestDeliveryChecksum;
import com.geekhive.foodey.Food.beans.restdeltip.RestDeliveryTip;
import com.geekhive.foodey.Food.qrcode.QrScanActivity;
import com.geekhive.foodey.Food.tracking.TrackingActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by user pc on 30-07-2018.
 */

public class OrderHistoryFragment extends Fragment implements OnResponseListner {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    View view;
    Bundle savedInstanceStateq;
    Unbinder unbinder;
    @BindView(R.id.vR_fu_list)
    RecyclerView vRFuList;
    BookingAdapter bookingAdapter;

    ConnectionDetector mDetector;
    OrderHistory orderHistory = new OrderHistory();
    List<CartDetail> cartList;
    String qty;
    int quantity = 1;
    Dialog paypopup;
    String resId = "";
    String ratingRes = "";
    String feedbackRes = "";
    String typeRes = "";
    String cart_id = "";

    String del_id = "";
    String ratingDel = "";
    String feedbackDel = "";
    String typeDel = "";

    String order_id = "";
    String Price = "";

    String tip_no;
    String txn_amount;
    int count = 0;
    String userId;

    String listString;
    String pricelistString;

    Bundle payResponse;






    private ArrayList<String> items = new ArrayList<String>();
    private ArrayList<String> priceitem = new ArrayList<String>();

    private static final int REQUEST_CODE_QR_SCAN = 101;
    public static final int REQUEST_FOR_STORAGE_PERMISSION = 123;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_upcoming_order, container, false);
        this.savedInstanceStateq = savedInstanceState;
        unbinder = ButterKnife.bind(this, view);
        initializeFonts();
        setvalues();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (orderHistory != null) {
                if (orderHistory.getOrderHistory() != null) {
                    if (orderHistory.getOrderHistory().getCart() != null) {
                        orderHistory.getOrderHistory().getCart().clear();
                    }
                }

            }


            CallService();

            if (bookingAdapter != null) {
                bookingAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        CallService();
        if (payResponse != null) {
            if (payResponse.getString("STATUS").equals("TXN_SUCCESS")) {
                callPayment();
            }

        }
    }

    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAS.TTF");
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(getActivity());
        CallService();
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(getActivity()).isEmpty() || !Prefs.getUserId(getActivity()).equals("")) {
                new WebServices(this).OrderHistory(WebServices.Foodey_Services,
                        WebServices.ApiType.orderHistory, Prefs.getUserId(getActivity()));
            } else {

                SnackBar.makeText(getActivity(), getResources().getString(R.string.no_internet), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallDelService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(getActivity()).isEmpty() || !Prefs.getUserId(getActivity()).equals("")) {
                new WebServices(this).DelRating(WebServices.Foodey_Services,
                        WebServices.ApiType.delRat, del_id, Prefs.getUserId(getActivity()), ratingDel, feedbackDel, typeDel, cart_id);
            } else {

                SnackBar.makeText(getActivity(), getResources().getString(R.string.no_internet), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallResService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(getActivity()).isEmpty() || !Prefs.getUserId(getActivity()).equals("")) {
                new WebServices(this).ResRating(WebServices.Foodey_Services,
                        WebServices.ApiType.resRat, resId, Prefs.getUserId(getActivity()), ratingRes, feedbackRes, typeRes, cart_id);
            } else {

                SnackBar.makeText(getActivity(), getResources().getString(R.string.no_internet), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }
    private void CallAddTipsService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(getActivity()).isEmpty() || !Prefs.getUserId(getActivity()).equals("")) {
                new WebServices(this).RestAddTip(WebServices.Foodey_Services,
                        WebServices.ApiType.restdeliverytip, Prefs.getUserId(getActivity()), cart_id, del_id, order_id, Price);
            } else {

                SnackBar.makeText(getActivity(), getResources().getString(R.string.no_internet), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }
    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    private void CallChecksumService() {
        if (this.mDetector.isConnectingToInternet()) {
            this.tip_no = generateString();
            this.userId = generateString();

            new WebServices(this).GenerateTipsCheckSum(WebServices.Foodey_Services,
                    WebServices.ApiType.tipschecksum, tip_no, userId, txn_amount);//grandTotal
        } else {

            SnackBar.makeText(getActivity(), (int) R.string.something_wrong, SnackBar.LENGTH_SHORT).show();
        }

        return;
    }

    public void callPayment() {
        if (payResponse.getString("STATUS").equals("TXN_SUCCESS")) {
            new WebServices(OrderHistoryFragment.this).TipsPaytmResponse(WebServices.Foodey_Services,
                    WebServices.ApiType.tipspaytmresponse, Prefs.getOrderId(getActivity()), Prefs.getUserId(getActivity()),
                    payResponse.getString("TXNID"), payResponse.getString("TXNAMOUNT"),
                    payResponse.getString("PAYMENTMODE"), payResponse.getString("CURRENCY"),
                    payResponse.getString("TXNDATE"), payResponse.getString("STATUS"),
                    payResponse.getString("RESPCODE"), payResponse.getString("RESPMSG"),
                    payResponse.getString("GATEWAYNAME"), payResponse.getString("BANKTXNID"),
                    payResponse.getString("BANKNAME"));
        }

        /*if (inResponse.getString("STATUS").equals("TXN_SUCCESS")) {
            payResponse = inResponse;
            new WebServices(OrderHistoryFragment.this).TipsPaytmResponse(WebServices.Foodey_Services,
                    WebServices.ApiType.tipspaytmresponse, Prefs.getOrderId(getActivity()), Prefs.getUserId(getActivity()),
                    inResponse.getString("TXNID"), inResponse.getString("TXNAMOUNT"),
                    inResponse.getString("PAYMENTMODE"), inResponse.getString("CURRENCY"),
                    inResponse.getString("TXNDATE"), inResponse.getString("STATUS"),
                    inResponse.getString("RESPCODE"), inResponse.getString("RESPMSG"),
                    inResponse.getString("GATEWAYNAME"), inResponse.getString("BANKTXNID"),
                    inResponse.getString("BANKNAME"));
        } else {

            SnackBar.makeText(getActivity(), inResponse.getString("RESPMSG"), SnackBar.LENGTH_SHORT).show();
        }*/


    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.orderHistory) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                orderHistory = (OrderHistory) response;
                if (orderHistory != null) {
                    if (orderHistory.getOrderHistory() != null) {
                        bookingAdapter = new BookingAdapter();
                        vRFuList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        vRFuList.setAdapter(bookingAdapter);
                    } else {
                        SnackBar.makeText(getActivity(), orderHistory.getMessage(), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
        else if (apiType == WebServices.ApiType.delRat) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                RatingDelivery ratingDelivery = (RatingDelivery) response;
                if (ratingDelivery != null) {
                    if (ratingDelivery != null) {
                        if (!ratingDelivery.getMessage().equals("")) {
                            SnackBar.makeText(getActivity(), ratingDelivery.getMessage(), SnackBar.LENGTH_SHORT).show();
                            CallResService();
                        }
                    } else {
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
        else if (apiType == WebServices.ApiType.resRat) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                RatingRes ratingRes = (RatingRes) response;
                if (ratingRes != null) {
                    if (ratingRes != null) {
                        if (!ratingRes.getMessage().equals("")) {
                            SnackBar.makeText(getActivity(), ratingRes.getMessage(), SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
        if (apiType == WebServices.ApiType.restdeliverytip) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                RestDeliveryTip deliveryTip = (RestDeliveryTip) response;
                if (deliveryTip != null) {
                    if (!deliveryTip.getTip() .equals("")) {
                        txn_amount = deliveryTip.getTip().getTip();
                        tip_no = deliveryTip.getTip().getTipNo();
                        CallChecksumService();


//                        Intent intent = new Intent(GroceryCheckOutActivityFinal.this, GroceryCheckOutActivityFinal.class);
//                        intent.putExtra("coupon_value", coupon);
//                        instruction_desc = storeInstruction.getCartList().getGCart().getInstructions();
//                        intent.putExtra("instruction_txt", instruction_desc);
//                        intent.putExtra("address", address);
//                        startActivity(intent);
                    }
                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        }
        else if (apiType == WebServices.ApiType.tipschecksum) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                RestDeliveryChecksum checksum = (RestDeliveryChecksum) response;

                Log.e("Order ID", Prefs.getOrderId(getActivity()));

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

                Service.startPaymentTransaction(getActivity(), true, true,
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
                                Toast.makeText(getActivity(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();

                                if (mDetector.isConnectingToInternet()) {
                                    if (inResponse.getString("STATUS").equals("TXN_SUCCESS")) {
                                        payResponse = inResponse;
                                        /*new WebServices(OrderHistoryFragment.this).TipsPaytmResponse(WebServices.Foodey_Services,
                                                WebServices.ApiType.tipspaytmresponse, Prefs.getOrderId(getActivity()), Prefs.getUserId(getActivity()),
                                                inResponse.getString("TXNID"), inResponse.getString("TXNAMOUNT"),
                                                inResponse.getString("PAYMENTMODE"), inResponse.getString("CURRENCY"),
                                                inResponse.getString("TXNDATE"), inResponse.getString("STATUS"),
                                                inResponse.getString("RESPCODE"), inResponse.getString("RESPMSG"),
                                                inResponse.getString("GATEWAYNAME"), inResponse.getString("BANKTXNID"),
                                                inResponse.getString("BANKNAME"));*/
                                    } else {

                                        SnackBar.makeText(getActivity(), inResponse.getString("RESPMSG"), SnackBar.LENGTH_SHORT).show();
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
                                Toast.makeText(getActivity(), "Back pressed. Transaction cancelled", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                                Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
                                Toast.makeText(getActivity(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
                            }

                        });


            }
        }else if (apiType == WebServices.ApiType.tipspaytmresponse) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                PaytmResponse paytmResponse = (PaytmResponse) response;
                /*SnackBar.makeText(CheckOutActivityFinal.this, paytmResponse.getMessage(), SnackBar.LENGTH_SHORT);
                Intent intent = new Intent(CheckOutActivityFinal.this, SuccessActivity.class);
                intent.putExtra("deliveryType", "");
                //intent.putExtra("total", grandTotal);
                intent.putExtra("deliveryAddress", addressSelected);
                intent.putExtra("paymentmethod", "Online");
                intent.putExtra("paymentStatus", "Completed");
                startActivity(intent);
                CheckOutActivity.this.finish();*/

                SnackBar.makeText(getActivity(), paytmResponse.getMessage(), SnackBar.LENGTH_SHORT);
                /*Intent intent = new Intent(getActivity(), CakeOrderSummaryActivity.class);
                intent.putExtra("deliveryType", "");
                intent.putExtra("total", grandTotal);
                intent.putExtra("orderId", Prefs.getOrderId(getActivity()));
                intent.putExtra("deliveryAddress", address);
                intent.putExtra("paymentmethod", "Online");
                intent.putExtra("paymentStatus", "Completed");
                startActivity(intent);
                CakeCheckOutActivityFinal.this.finish();*/
            }
        }

    }

    public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_order_history, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            String dttm = orderHistory.getOrderHistory().getCart().get(position).getDate() + ", " + orderHistory.getOrderHistory().getCart().get(position).getTime();
            holder.vT_ado_orderonval.setText(dttm);

            String price = "₹ " + orderHistory.getOrderHistory().getCart().get(position).getGrandTotal();
            holder.vT_ado_priceval.setText(price);

            holder.vT_ado_orderid_val.setText(orderHistory.getOrderHistory().getCart().get(position).getOrderId());
            order_id = orderHistory.getOrderHistory().getCart().get(position).getOrderId();


            holder.vT_restaurant_text.setText(orderHistory.getOrderHistory().getResturant().get(position).getName());
            holder.vT_address_txt.setText(orderHistory.getOrderHistory().getResturant().get(position).getAddress());


            //      String itemCount=cartList.get(position).getFood().getName();


            String url = WebServices.Foodey_Rest_Url + orderHistory.getOrderHistory().getResturant().get(position).getImage();


            if (!url.equals("")) {
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.food_item_image);
            }


            cartList = orderHistory.getOrderHistory().getCartDetail().get(position);
//            holder.vR_ard_menulist.setLayoutManager(mLayoutManagerb);
//            holder.vR_ard_menulist.setAdapter(menuAdapter);

//            RecyclerView.LayoutManager mLayoutManagerc = new LinearLayoutManager(getActivity());
//            DishCountAdapter dishcountAdapter = new DishCountAdapter();
//
//              holder.vR_iemCount.setLayoutManager(mLayoutManagerc);
//              holder.vR_iemCount.setAdapter(dishcountAdapter);


            if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("0")) {
                holder.vT_ado_status.setText("Pending");

            } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("1")) {
                holder.vT_ado_status.setText("Confirmed");
            } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("2")) {
                String canceled = "Canceled " + orderHistory.getOrderHistory().getCart().get(position).getCancelBy();
                holder.vT_ado_status.setText(canceled);

            } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("3")) {
                if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("1")){
                    holder.vT_ado_status.setText("Preparing");
                } else {
                    holder.vT_ado_status.setText("Delivery Boy confirmed");
                }
                //holder.vT_ado_status.setText("Delivery Boy confirmed");

            } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("4")) {
                holder.vT_ado_status.setText("Delivered");

            } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("5")) {
                if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("1")){
                    holder.vT_ado_status.setText("Preparing");
                } else {
                    holder.vT_ado_status.setText("Track Order");
                }


            }

            if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("1")){
                if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("1")) {
                    holder.vT_qr_code.setVisibility(View.VISIBLE);
                } else{
                    holder.vT_qr_code.setVisibility(View.INVISIBLE);
                }
            } else {
                holder.vT_qr_code.setVisibility(View.INVISIBLE);
            }
            holder.vT_qr_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!mayRequestGalleryImages()) {
                        return;
                    } else {
                        Intent intent = new Intent(getActivity(), QrScanActivity.class);
                        intent.putExtra("res_id", orderHistory.getOrderHistory().getResturant().get(position).getId());
                        intent.putExtra("order_id", orderHistory.getOrderHistory().getCart().get(position).getOrderId());
                        intent.putExtra("cart_id", orderHistory.getOrderHistory().getCart().get(position).getId());
                        intent.putExtra("rest_name", orderHistory.getOrderHistory().getResturant().get(position).getEmail());
                        startActivity(intent);
                        /*Intent i = new Intent(getActivity(), QrCodeActivity.class);
                        getActivity().startActivityForResult( i,REQUEST_CODE_QR_SCAN);*/
                    }

                }
            });

            items=new ArrayList<>();
            priceitem=new ArrayList<>();
            for (int i = 0; i < cartList.size(); i++) {
                items.add(cartList.get(i).getFood().getName()+" X "+cartList.get(i).getQuantity());
                //priceitem.add("₹ "+cartList.get(i).getMrp());
            }
            listString = "";
            pricelistString="";
            for (String s : items)
            {
                listString += s + ",";

            }
//            for (String p : priceitem)
//            {
//                pricelistString = p ;
//
//            }
            holder.vT_item_list.setText(listString);
            String abs = listString;
            String priceitem = pricelistString;

            holder.vT_ado_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("5")) {
                        if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("0")){
                            Intent intent = new Intent(getActivity(), TrackingActivity.class);
                            intent.putExtra("del_id", orderHistory.getOrderHistory().getDeliveryBoy().get(position).getId());
                            intent.putExtra("order_id", orderHistory.getOrderHistory().getCart().get(position).getOrderId());
                            intent.putExtra("position", position);
                            //orderHistory.getOrderHistory().getDeliveryBoy().getCart(
                            getActivity().startActivity(intent);
                        }else {
                            SnackBar.makeText(getActivity(), "", SnackBar.LENGTH_SHORT);
                        }

                    }else {
                        SnackBar.makeText(getActivity(), "", SnackBar.LENGTH_SHORT);
                    }

                }
            });
            //holder.vT_ado_status;

            holder.vL_cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InitializeOrderHistorypopup();
                    initializePopup(abs);
                }

                private void InitializeOrderHistorypopup() {
                    paypopup = new Dialog(getActivity());
                    paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    paypopup.setContentView(R.layout.adapter_order);
                    paypopup.setCancelable(true);
                    paypopup.setCanceledOnTouchOutside(true);
                    paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                private void initializePopup(String abs) {
                    paypopup.setContentView(R.layout.adapter_order);
                    paypopup.setCancelable(true);
                    paypopup.setCanceledOnTouchOutside(true);
                    paypopup.show();

                    final TextView vT_ado_status = paypopup.findViewById(R.id.vT_ado_status);
                    final TextView vT_ado_orderonval = paypopup.findViewById(R.id.vT_ado_orderonval);

                    final TextView vT_item_list = paypopup.findViewById(R.id.vT_item_list);


                    // final TextView vT_ado_priceval =  paypopup.findViewById(R.id.vT_ado_priceval);
                    final TextView vT_ado_orderid_val = paypopup.findViewById(R.id.vT_ado_orderid_val);
                    final ImageView close_popup = paypopup.findViewById(R.id.close_popup);


                    final RecyclerView vR_ard_menulist = paypopup.findViewById(R.id.vR_ard_menulist);
                    final TextView vT_aco_sub_amount = paypopup.findViewById(R.id.vT_aco_sub_amount);
                    final TextView vT_discount = paypopup.findViewById(R.id.vT_discount);
                    final TextView vT_taxes = paypopup.findViewById(R.id.vT_taxes);
                    final TextView vT_packaging = paypopup.findViewById(R.id.vT_packaging);
                    final TextView vT_delivery = paypopup.findViewById(R.id.vT_delivery);
                    final TextView vT_grand_total = paypopup.findViewById(R.id.vT_grand_total);
                    final TextView vT_restaurant_text = paypopup.findViewById(R.id.vT_restaurant_text);
                    final TextView vT_address_txt = paypopup.findViewById(R.id.vT_address_txt);
                   // final TextView vT_item_list_price = paypopup.findViewById(R.id.vT_item_list_price);





                    // final ImageView view_cart_img = paypopup.findViewById(R.id.view_cart_img);


                    int width = getResources().getDisplayMetrics().widthPixels - 100;
                    int height = getResources().getDisplayMetrics().heightPixels - 250;
                    paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paypopup.getWindow().setGravity(Gravity.BOTTOM);


                    vT_restaurant_text.setText(orderHistory.getOrderHistory().getResturant().get(position).getName());
                    vT_address_txt.setText(orderHistory.getOrderHistory().getResturant().get(position).getAddress());
                    vT_ado_orderonval.setText(dttm);
                    String price = "₹ " + orderHistory.getOrderHistory().getCart().get(position).getTotel();

                    //   vT_ado_priceval.setText(price);

                    vT_ado_orderid_val.setText(orderHistory.getOrderHistory().getCart().get(position).getOrderId());

                    vT_aco_sub_amount.setText("₹ " + orderHistory.getOrderHistory().getCart().get(position).getTotel());
                    vT_discount.setText("₹ " + orderHistory.getOrderHistory().getCart().get(position).getDiscount());
                    vT_taxes.setText("₹ " + orderHistory.getOrderHistory().getCart().get(position).getTax());
                    vT_packaging.setText("₹ " + orderHistory.getOrderHistory().getCart().get(position).getPackingCharge());
                    vT_delivery.setText("₹ " + orderHistory.getOrderHistory().getCart().get(position).getDelivery());
                    vT_grand_total.setText("₹ " + orderHistory.getOrderHistory().getCart().get(position).getGrandTotal());





                  /*  items=new ArrayList<>();
                    for (int i = 0; i < cartList.size(); i++) {
                        items.add(cartList.get(i).getFood().getName()+" X "+cartList.get(i).getQuantity());
                        listString = "";
                        for (String s : items)
                        {
                            listString += s + ",";
                            System.out.println("ittemstring:::::"+listString);
                        }

                    }*/


                   vT_item_list.setText(abs);
                   // vT_item_list_price.setText(priceitem);


                  //  vT_item_list.setText(cartList.get(position).getFood().getName()+" X "+ cartList.get(position).getQuantity());

//////
//                    RecyclerView.LayoutManager mLayoutManagerb = new LinearLayoutManager(getActivity());
//                    MenuAdapter menuAdapter = new MenuAdapter(abs);
//
//                   vR_ard_menulist.setLayoutManager(mLayoutManagerb);
//                   vR_ard_menulist.setAdapter(menuAdapter);

                    if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("0")) {
                        holder.vT_ado_status.setText("Pending");

                    } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("1")) {
                        holder.vT_ado_status.setText("Confirmed");
                    } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("2")) {
                        String canceled = "Canceled " + orderHistory.getOrderHistory().getCart().get(position).getCancelBy();
                        holder.vT_ado_status.setText(canceled);

                    } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("3")) {
                        if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("1")){
                            holder.vT_ado_status.setText("Preparing");
                        } else {
                            holder.vT_ado_status.setText("Delivery Boy confirmed");
                        }
                        //holder.vT_ado_status.setText("Delivery Boy confirmed");

                    } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("4")) {
                        holder.vT_ado_status.setText("Delivered");

                    } else if (orderHistory.getOrderHistory().getCart().get(position).getStatus().equals("5")) {
                        if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("1")){
                            holder.vT_ado_status.setText("Preparing");
                        } else {
                            holder.vT_ado_status.setText("Track Order");
                        }


                    }
                    close_popup.setOnClickListener(new View.OnClickListener() {
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
            });

            if (orderHistory.getOrderHistory().getCart().get(position).getOrderStatus() != null &&
                    !orderHistory.getOrderHistory().getCart().get(position).getOrderStatus().equals("") &&
                    orderHistory.getOrderHistory().getCart().get(position).getOrderStatus().equals("Delivery Boy")) {
                if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("1")){
                    holder.vT_add_tips.setVisibility(View.GONE);
                } else {
                    holder.vT_add_tips.setVisibility(View.VISIBLE);
                }

            } else {

                holder.vT_add_tips.setVisibility(View.GONE);
            }

            if (orderHistory.getOrderHistory().getCart().get(position).getOrderStatus() != null &&
                    !orderHistory.getOrderHistory().getCart().get(position).getOrderStatus().equals("") &&
                    orderHistory.getOrderHistory().getCart().get(position).getOrderStatus().equals("Delivered")) {
                if (orderHistory.getOrderHistory().getCart().get(position).getTakeAway().equals("1")){
                    holder.vT_rate_txt.setVisibility(View.GONE);
                } else {
                    holder.vT_rate_txt.setVisibility(View.VISIBLE);
                }

            } else {

                holder.vT_rate_txt.setVisibility(View.GONE);
            }

            holder.vT_rate_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InitializeRatepopup();
                    initializePopup(orderHistory.getOrderHistory().getDeliveryBoy().get(position).getProfile(),
                            orderHistory.getOrderHistory().getResturant().get(position).getImage(),
                            orderHistory.getOrderHistory().getDeliveryBoy().get(position).getFirstname(),
                            orderHistory.getOrderHistory().getResturant().get(position).getName(),
                            orderHistory.getOrderHistory().getResturant().get(position).getId(),
                            Prefs.getUserId(getActivity()),
                            orderHistory.getOrderHistory().getCart().get(position).getId(),
                            orderHistory.getOrderHistory().getDeliveryBoy().get(position).getId());
                }
            });

            holder.vT_add_tips.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InitializeTipspopup();
                    initializeTipsPopup();

                }
            });


        }
        private void InitializeTipspopup () {
            paypopup = new Dialog(getActivity());
            paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
            paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            paypopup.setContentView(R.layout.popup_tips_delivery);
            paypopup.setCancelable(true);
            paypopup.setCanceledOnTouchOutside(true);
            paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        private void initializeTipsPopup () {
            paypopup.setContentView(R.layout.popup_tips_delivery);
            paypopup.setCancelable(true);
            paypopup.setCanceledOnTouchOutside(true);
            paypopup.show();

            resId = resId;
            cart_id = cart_id;
            del_id = del_id;
            typeRes = "resturant";
            typeDel = "delivery";
            final Button vB_pnt5 = paypopup.findViewById(R.id.vB_pnt5);
            final Button vB_pnt10 = paypopup.findViewById(R.id.vB_pnt10);
            final Button vB_pnt20 = paypopup.findViewById(R.id.vB_pnt20);
            final Button payButton = paypopup.findViewById(R.id.payButton);


            vB_pnt5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (count == 0) {
                        vB_pnt5.setBackground(getResources().getDrawable(R.drawable.rounded_corner));
                        count = 1;
                    } else {
                        Price = "5";
                        vB_pnt5.setBackground(getResources().getDrawable(R.drawable.gradient_green));
                        count = 0;
                    }
                }
            });
            vB_pnt10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (count == 0) {
                        vB_pnt10.setBackground(getResources().getDrawable(R.drawable.rounded_corner));
                        count = 1;
                    } else {
                        Price = "10";
                        vB_pnt10.setBackground(getResources().getDrawable(R.drawable.gradient_green));
                        count = 0;
                    }
                }
            });

            vB_pnt20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (count == 0) {
                        vB_pnt20.setBackground(getResources().getDrawable(R.drawable.rounded_corner));
                        count = 1;
                    } else {
                        Price = "15";
                        vB_pnt20.setBackground(getResources().getDrawable(R.drawable.gradient_green));
                        count = 0;
                    }

                }
            });
            payButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (paypopup.isShowing()) {
                        paypopup.dismiss();
                        CallAddTipsService();
                    }

                }
            });
        }

        public int getItemCount() {
            return orderHistory.getOrderHistory().getCart().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            CardView vC_ado_main;
            //ImageView vI_ado_image;
            LinearLayout vL_ado_items, vL_cl;
            TextView vT_ado_status,
                    vT_ado_items, vT_ado_orderon, vT_ado_orderonval, vT_ado_price, vT_ado_priceval, vT_ado_order_id, vT_ado_orderid_val, vT_restaurant_text, vT_address_txt, vT_rate_txt,vT_item_list,vT_add_tips;
            TextView vT_qr_code;

            ImageView food_item_image;
            RecyclerView vR_ard_menulist;

            public MyViewHolder(View view) {
                super(view);
                this.vT_ado_status = (TextView) view.findViewById(R.id.vT_ado_status);
                this.vT_ado_items = (TextView) view.findViewById(R.id.vT_ado_items);
                this.vR_ard_menulist = (RecyclerView) view.findViewById(R.id.vR_ard_menulist);
                this.vT_ado_orderon = (TextView) view.findViewById(R.id.vT_ado_orderon);
                this.vT_ado_orderonval = (TextView) view.findViewById(R.id.vT_ado_orderonval);
                this.vT_ado_price = (TextView) view.findViewById(R.id.vT_ado_price);
                this.vT_ado_priceval = (TextView) view.findViewById(R.id.vT_ado_priceval);
                this.vC_ado_main = (CardView) view.findViewById(R.id.vC_ado_main);
                this.vL_ado_items = (LinearLayout) view.findViewById(R.id.vL_ado_items);
                this.vL_cl = (LinearLayout) view.findViewById(R.id.vL_cl);
                this.vT_rate_txt = (TextView) view.findViewById(R.id.vT_rate_txt);
                this.vT_item_list = (TextView) view.findViewById(R.id.vT_item_list);
                this.vT_add_tips = (TextView) view.findViewById(R.id.vT_add_tips);
                this.vT_qr_code = view.findViewById(R.id.vT_qr_code);




                this.vT_ado_order_id = view.findViewById(R.id.vT_ado_order_id);
                this.vT_ado_orderid_val = view.findViewById(R.id.vT_ado_orderid_val);
                //  this.vR_iemCount = (RecyclerView) view.findViewById(R.id.vR_iemCount);


                this.vT_restaurant_text = view.findViewById(R.id.vT_restaurant_text);
                this.vT_address_txt = view.findViewById(R.id.vT_address_txt);
                this.food_item_image = view.findViewById(R.id.food_item_image);
                new Runnable() {
                    public void run() {
                        vT_ado_status.setTypeface(NIRMALAB);
                        vT_ado_items.setTypeface(NIRMALAB);
                        vT_ado_orderon.setTypeface(NIRMALAB);
                        vT_ado_orderonval.setTypeface(NIRMALA);
                        vT_ado_price.setTypeface(NIRMALAB);
                        vT_ado_priceval.setTypeface(NIRMALA);
                        vT_ado_order_id.setTypeface(NIRMALAB);
                        vT_ado_orderid_val.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }


    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

        private Context mcontext;
        String value;

//        public MenuAdapter(Context context, String value) {
//
//            this.mcontext = context;
//            this.value = value;
//        }

        public MenuAdapter(String abs) {
            this.value = abs;

        }


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history_menu_details, parent, false));
        }




        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vT_admd_text.setText(value);
            String price = "₹ " + cartList.get(position).getFood().getPrice();
            holder.vT_price.setText(price);
        }

        public int getItemCount() {

            return cartList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_admd_main, vL_admd_hide;
            ImageView vI_admd_image;
            TextView vT_admd_text, vT_price;

            public MyViewHolder(View view) {
                super(view);
                this.vL_admd_main = (LinearLayout) view.findViewById(R.id.vL_admd_main);
                this.vL_admd_hide = (LinearLayout) view.findViewById(R.id.vL_admd_hide);
                this.vI_admd_image = (ImageView) view.findViewById(R.id.vI_admd_image);
                this.vT_admd_text = (TextView) view.findViewById(R.id.vT_admd_text);
                vT_price = view.findViewById(R.id.vT_price);

                new Runnable() {
                    public void run() {
                        vT_admd_text.setTypeface(NIRMALAB);
                        vT_price.setTypeface(NIRMALAB);
                    }
                }.run();
            }
        }
    }


    private void InitializeRatepopup() {
        paypopup = new Dialog(getActivity());
        paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        paypopup.setContentView(R.layout.popup_rate_delivery);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void initializePopup(String dbImg, String resImg, String dbName, String rsName, String res_id, String user_id, String cart_id, String del_id) {
        paypopup.setContentView(R.layout.popup_rate_delivery);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();

        this.resId = resId;
        this.cart_id = cart_id;
        this.del_id = del_id;
        typeRes = "resturant";
        typeDel = "delivery";


        final CircleImageView vI_deliveryboy = paypopup.findViewById(R.id.vI_deliveryboy);
        final TextView delBoyName = paypopup.findViewById(R.id.delBoyName);
        final RatingBar mRatingBar = paypopup.findViewById(R.id.ratingBar);
        final TextView mRatingScale = paypopup.findViewById(R.id.tvRatingScale);
        final EditText mFeedback = paypopup.findViewById(R.id.vE_rate);

        final CircleImageView vI_res = paypopup.findViewById(R.id.vI_res);
        final TextView resName = paypopup.findViewById(R.id.restName);
        final RatingBar mRatingBarRes = paypopup.findViewById(R.id.ratingBarRes);
        final TextView mRatingScaleRes = paypopup.findViewById(R.id.tvRatingScaleRes);
        final EditText mFeedbackRes = paypopup.findViewById(R.id.vE_rate_res);

        String url = WebServices.Foodey_Rest_Url + resImg;


        if (!url.equals("")) {
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(vI_res);
        }

        String url1 = WebServices.Foodey_Image_Url + resImg;


        if (!url.equals("")) {
            Picasso.get()
                    .load(url1)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(vI_deliveryboy);
        }

        delBoyName.setText(dbName);
        resName.setText(rsName);


        final Button mSendFeedback = paypopup.findViewById(R.id.btnSubmit);
        final TextView vTSkip = paypopup.findViewById(R.id.vT_skip);


        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paypopup.getWindow().setGravity(Gravity.CENTER);


        if (!mFeedback.getText().toString().isEmpty() || !mFeedback.getText().toString().equals("")) {
            feedbackDel = mFeedback.getText().toString();
        }


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                ratingDel = String.valueOf(v);
                switch ((int) ratingBar.getRating()) {
                    case 1:

                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });


        if (!mFeedbackRes.getText().toString().isEmpty() || !mFeedbackRes.getText().toString().equals("")) {
            feedbackRes = mFeedbackRes.getText().toString();
        }

        mRatingBarRes.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScaleRes.setText(String.valueOf(v));
                ratingRes = String.valueOf(v);
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScaleRes.setText("Very bad");
                        break;
                    case 2:
                        mRatingScaleRes.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScaleRes.setText("Good");
                        break;
                    case 4:
                        mRatingScaleRes.setText("Great");
                        break;
                    case 5:
                        mRatingScaleRes.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScaleRes.setText("");
                }
            }
        });


        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paypopup.isShowing()) {
                    paypopup.dismiss();
                    CallDelService();
                }
            }
        });
        vTSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paypopup.isShowing())
                    paypopup.dismiss();
            }
        });


        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
        {
            Log.d("MainActivity","COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d("MainActivity","Have scan result in your app activity :"+ result);
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
    }


    private boolean mayRequestGalleryImages() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        requestPermissions(
                new String[]{Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                REQUEST_FOR_STORAGE_PERMISSION);
        return false;
    }

    /**
     * This method calls if user if a user has denied a permission and selected the
     * Don't ask again option in the permission request dialog,
     * or if a device policy prohibits the permission
     */
    private void showPermissionRationaleSnackBar() {

        Snackbar.make(getActivity().findViewById(android.R.id.content),
                "Please Grant Permissions",
                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission
                                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                REQUEST_FOR_STORAGE_PERMISSION);
                    }
                }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FOR_STORAGE_PERMISSION:

                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                        //selectImage();
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                                ActivityCompat.shouldShowRequestPermissionRationale
                                        (getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale
                                (getActivity(), Manifest.permission.CAMERA)) {

                            showPermissionRationaleSnackBar();

                        } else {
                            Toast.makeText(getActivity(), "Go to settings and enable permission", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                break;

        }

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
        }

    }
}


