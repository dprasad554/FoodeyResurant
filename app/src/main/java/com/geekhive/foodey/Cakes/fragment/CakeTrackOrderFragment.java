package com.geekhive.foodey.Cakes.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
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

import com.geekhive.foodey.Cakes.beans.cakeaddtips.CakeAddTips;
import com.geekhive.foodey.Cakes.beans.cakehistory.CakeCartDetail;
import com.geekhive.foodey.Cakes.beans.cakehistory.CakeHistory;
import com.geekhive.foodey.Cakes.beans.cakepaytmIntegration.CakePaytmResponse;
import com.geekhive.foodey.Cakes.beans.caketipschecksum.CakeTipsChecksum;
import com.geekhive.foodey.Cakes.beans.givecakedeliveryboyrating.GiveCakeDeliveryboyRating;
import com.geekhive.foodey.Cakes.beans.givecakestorerating.GiveCakeStoreRating;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.tracking.TrackingActivity;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class CakeTrackOrderFragment extends Fragment implements OnResponseListner {

    RecyclerView trackRecyclerView;
    ArrayList orderStatus_img = new ArrayList<>(Arrays.asList(R.drawable.green_circle, R.drawable.green_circle));
    ArrayList deliveryStatus_img = new ArrayList<>(Arrays.asList(R.drawable.red_circle, R.drawable.red_circle));
    ConnectionDetector mDetector;
    ImageView search;
    BottomNavigationView bottomNavigationView;

    String resId = "";
    String cart_id = "";

    String del_id = "";
    String ratingDel = "";
    String feedbackDel = "";
    String typeDel = "";
    String typeRes = "";
    String feedbackRes = "";
    String ratingRes = "";
    String deliveryboyID;
    String orderid;
    String Price = "";
    String cartid;
    String order_id, userId;
    String grandTotal;
    String txn_amount;
    String tip_no;
    List<CakeCartDetail> cartList;
    private ArrayList<String> items = new ArrayList<String>();
    Dialog paypopup;
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    String listString;




    public CakeTrackOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cake_fragment_tarck_order, container, false);

        //For Recent
        trackRecyclerView = (RecyclerView) view.findViewById(R.id.TackOrder_RecyclerView);
        setValues();
        CallService();
        return view;
    }

    private void setValues() {
        mDetector = new ConnectionDetector(getActivity());
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).HistoryDetails(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.history, Prefs.getUserId(getActivity()));
        } else {
            SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    private void CallDelService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(getActivity()).isEmpty() || !Prefs.getUserId(getActivity()).equals("")) {
                new WebServices(this).DelRating(WebServices.Foodey_Cakelocation_Services,
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
                new WebServices(this).ResRating(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.giveresturantrating, resId, Prefs.getUserId(getActivity()), ratingRes, feedbackRes, typeRes, cart_id);
            } else {

                SnackBar.makeText(getActivity(), getResources().getString(R.string.no_internet), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallAddTipsService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(getActivity()).isEmpty() || !Prefs.getUserId(getActivity()).equals("")) {
                new WebServices(this).Addtips(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.addtips, Prefs.getUserId(getActivity()), cartid, deliveryboyID, orderid, Price);
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
            this.order_id = generateString();
            this.userId = generateString();

            new WebServices(this).GenerateTipsCheckSum(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.tipschecksum, tip_no, userId, txn_amount);//grandTotal
        } else {

            SnackBar.makeText(getActivity(), (int) R.string.something_wrong, SnackBar.LENGTH_SHORT).show();
        }

        return;
    }


    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.history) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeHistory history = (CakeHistory) response;
                if (!isSuccess || history == null) {
                    SnackBar.makeText(getActivity(), "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    if (history.getOrderHistory() == null) {
                        SnackBar.makeText(getActivity(), "No Record Found", SnackBar.LENGTH_SHORT).show();
                    } else {
                        LinearLayoutManager layoutTrackManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        trackRecyclerView.setLayoutManager(layoutTrackManager);
                        CakeTrackOrderAdapter trackAdapter = new CakeTrackOrderAdapter(getActivity(), history, orderStatus_img, deliveryStatus_img);
                        trackRecyclerView.setAdapter(trackAdapter);
                    }
                }
            }
        } else if (apiType == WebServices.ApiType.delRat) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GiveCakeDeliveryboyRating ratingDelivery = (GiveCakeDeliveryboyRating) response;
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
        } else if (apiType == WebServices.ApiType.giveresturantrating) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GiveCakeStoreRating ratingRes = (GiveCakeStoreRating) response;
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
        } else if (apiType == WebServices.ApiType.addtips) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeAddTips cakeAddTips = (CakeAddTips) response;
                if (cakeAddTips != null) {
                    if (cakeAddTips != null) {
                        if (!cakeAddTips.getTip().equals("")) {
                            txn_amount = cakeAddTips.getTip().getTip().toString();
                            tip_no = cakeAddTips.getTip().getTipNo();
                            CallChecksumService();
                            // SnackBar.makeText(getActivity(), "Sucess"+"Rs."+cakeAddTips.getTip().getTip(), SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.tipschecksum) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeTipsChecksum checksum = (CakeTipsChecksum) response;

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
                                        new WebServices(CakeTrackOrderFragment.this).TipsPaytmResponse(WebServices.Foodey_Cakelocation_Services,
                                                WebServices.ApiType.tipspaytmresponse, Prefs.getOrderId(getActivity()), Prefs.getUserId(getActivity()),
                                                inResponse.getString("TXNID"), inResponse.getString("TXNAMOUNT"),
                                                inResponse.getString("PAYMENTMODE"), inResponse.getString("CURRENCY"),
                                                inResponse.getString("TXNDATE"), inResponse.getString("STATUS"),
                                                inResponse.getString("RESPCODE"), inResponse.getString("RESPMSG"),
                                                inResponse.getString("GATEWAYNAME"), inResponse.getString("BANKTXNID"),
                                                inResponse.getString("BANKNAME"));
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
        } else if (apiType == WebServices.ApiType.tipspaytmresponse) {
            if (!isSuccess) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
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



    public class CakeTrackOrderAdapter extends RecyclerView.Adapter<CakeTrackOrderAdapter.ViewHolder> {

        //Variables
        private Context context;
        ArrayList<Integer> orderStatusImg;
        ArrayList<Integer> deliveryStatusImg;
        CakeHistory history;
        List<String> statusList = new ArrayList<>();
        Dialog paypopup;
        int count = 0;


        public CakeTrackOrderAdapter(Context context, CakeHistory history, ArrayList<Integer> orderStatusImg,
                                     ArrayList<Integer> deliveryStatusImg) {
            this.context = context;
            this.orderStatusImg = orderStatusImg;
            this.deliveryStatusImg = deliveryStatusImg;
            this.history = history;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_trackorder, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            String dttm = history.getOrderHistory().getCakeCart().get(position).getDate() + ", " + history.getOrderHistory().getCakeCart().get(position).getTime();
            holder.vT_ado_orderonval.setText(dttm);

            String price = "₹ " + history.getOrderHistory().getCakeCart().get(position).getGrandTotal();
            holder.vT_ado_priceval.setText(price);

            holder.vT_ado_orderid_val.setText(history.getOrderHistory().getCakeCart().get(position).getOrderId());

            holder.vT_restaurant_text.setText(history.getOrderHistory().getCakeStore().get(position).getName());
            holder.vT_address_txt.setText(history.getOrderHistory().getCakeStore().get(position).getAddress());


            //      String itemCount=cartList.get(position).getFood().getName();


            String url = WebServices.Foodey_Grocery_Image_URL + history.getOrderHistory().getCakeStore().get(position).getImage();


            if (!url.equals("")) {
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.food_item_image);
            }


            cartList = history.getOrderHistory().getCakeCartDetail().get(position);


            if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("0")) {
                holder.vT_ado_status.setText("Pending");
            } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("1")) {
                holder.vT_ado_status.setText("Confirmed");
            } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("2")) {
                String canceled = "Canceled " + history.getOrderHistory().getCakeCart().get(position).getCancelBy();
                holder.vT_ado_status.setText(canceled);
            } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("3")) {
                holder.vT_ado_status.setText("Delivery Boy confirmed");
            } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("4")) {
                holder.vT_ado_status.setText("Delivered");
            } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("5")) {
                holder.vT_ado_status.setText("Track Order");
            }

            items = new ArrayList<>();
            for (int i = 0; i < cartList.size(); i++) {
                items.add(cartList.get(i).getCake().getProductName() + " X " + cartList.get(i).getQuantity());
            }
             listString = "";
            for (String s : items) {
                listString += s + ",";

            }
            holder.vT_item_list.setText(listString);
            String abs = listString;


            holder.vT_ado_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("5")) {
                        Intent intent = new Intent(getActivity(), TrackingActivity.class);
                        intent.putExtra("del_id", history.getOrderHistory().getDeliveryBoy().get(position).getId().toString());
                        intent.putExtra("order_id", history.getOrderHistory().getCakeCart().get(position).getOrderId());
                        intent.putExtra("position", position);
                        //orderHistory.getOrderHistory().getDeliveryBoy().getCart(
                        getActivity().startActivity(intent);
                    }

                }
            });

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
                    final TextView vT_item_list = paypopup.findViewById(R.id.vT_item_list);



                    // final ImageView view_cart_img = paypopup.findViewById(R.id.view_cart_img);


                    int width = getResources().getDisplayMetrics().widthPixels - 100;
                    int height = getResources().getDisplayMetrics().heightPixels - 250;
                    paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paypopup.getWindow().setGravity(Gravity.BOTTOM);


                    vT_restaurant_text.setText(history.getOrderHistory().getCakeStore().get(position).getName());
                    vT_address_txt.setText(history.getOrderHistory().getCakeStore().get(position).getAddress());
                    vT_ado_orderonval.setText(dttm);
                    String price = "₹ " + history.getOrderHistory().getCakeCart().get(position).getTotel();

                    //   vT_ado_priceval.setText(price);

                    vT_ado_orderid_val.setText(history.getOrderHistory().getCakeCart().get(position).getOrderId());

                    vT_aco_sub_amount.setText("₹ " + history.getOrderHistory().getCakeCart().get(position).getTotel());
                    vT_discount.setText("₹ " + history.getOrderHistory().getCakeCart().get(position).getDiscount());
                    vT_taxes.setText("₹ " + history.getOrderHistory().getCakeCart().get(position).getTax());
                    vT_packaging.setText("₹ " + history.getOrderHistory().getCakeCart().get(position).getPackingCharge());
                    vT_delivery.setText("₹ " + history.getOrderHistory().getCakeCart().get(position).getDelivery());
                    vT_grand_total.setText("₹ " + history.getOrderHistory().getCakeCart().get(position).getGrandTotal());

                    vT_item_list.setText(abs);


//                    RecyclerView.LayoutManager mLayoutManagerb = new LinearLayoutManager(getActivity());
//                    MenuAdapter menuAdapter = new MenuAdapter();
//
//                    vR_ard_menulist.setLayoutManager(mLayoutManagerb);
//                    vR_ard_menulist.setAdapter(menuAdapter);

                    if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("0")) {
                        vT_ado_status.setText("Pending");
                    } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("1")) {
                        vT_ado_status.setText("Confirmed");
                    } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("2")) {
                        String canceled = "Canceled " + history.getOrderHistory().getCakeCart().get(position).getCancelBy();
                        vT_ado_status.setText(canceled);
                    } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("3")) {
                        vT_ado_status.setText("Delivery Boy confirmed");
                    } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("4")) {
                        vT_ado_status.setText("Delivered");
                    } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("5")) {
                        vT_ado_status.setText("Track Order");
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
            if (history.getOrderHistory().getCakeCart().get(position).getOrderStatus() != null &&
                    !history.getOrderHistory().getCakeCart().get(position).getOrderStatus().equals("") &&
                    history.getOrderHistory().getCakeCart().get(position).getOrderStatus().equals("Delivery Boy")) {

                holder.vT_add_tips.setVisibility(View.VISIBLE);
            } else {

                holder.vT_add_tips.setVisibility(View.GONE);
            }

            if (history.getOrderHistory().getCakeCart().get(position).getOrderStatus() != null &&
                    !history.getOrderHistory().getCakeCart().get(position).getOrderStatus().equals("") &&
                    history.getOrderHistory().getCakeCart().get(position).getOrderStatus().equals("Delivered")) {

                holder.vT_rate_txt.setVisibility(View.VISIBLE);
            } else {

                holder.vT_rate_txt.setVisibility(View.GONE);
            }


            holder.vT_rate_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    InitializeRatepopup();
                    initializePopup(history.getOrderHistory().getDeliveryBoy().get(position).getProfile().toString(),
                            history.getOrderHistory().getCakeStore().get(position).getImage(),
                            history.getOrderHistory().getDeliveryBoy().get(position).getFirstname().toString(),
                            history.getOrderHistory().getCakeStore().get(position).getName(),
                            history.getOrderHistory().getCakeStore().get(position).getId(),
                            Prefs.getUserId(getActivity()),
                            history.getOrderHistory().getCakeCart().get(position).getId(),
                            history.getOrderHistory().getDeliveryBoy().get(position).getId().toString());


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


        private void InitializeTipspopup() {
            paypopup = new Dialog(context);
            paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
            paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            paypopup.setContentView(R.layout.popup_tips_delivery);
            paypopup.setCancelable(true);
            paypopup.setCanceledOnTouchOutside(true);
            paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        private void initializeTipsPopup() {
            paypopup.setContentView(R.layout.popup_tips_delivery);
            paypopup.setCancelable(true);
            paypopup.setCanceledOnTouchOutside(true);
            paypopup.show();

            resId = resId;
            cart_id = cart_id;
            del_id = del_id;
            typeRes = "cakesotre";
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

            paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            paypopup.setCancelable(true);
            paypopup.setCanceledOnTouchOutside(true);
            paypopup.show();
        }

        @Override
        public int getItemCount() {
            return history.getOrderHistory().getCakeCartDetail().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView vC_ado_main;
            //ImageView vI_ado_image;
            LinearLayout vL_ado_items, vL_cl;
            TextView vT_ado_status,
                    vT_ado_items, vT_ado_orderon, vT_ado_orderonval, vT_ado_price, vT_ado_priceval, vT_ado_order_id, vT_ado_orderid_val,
                    vT_restaurant_text, vT_address_txt, vT_rate_txt, vT_item_list,vT_add_tips;

            RecyclerView vR_ard_menulist, vR_iemCount;
            ImageView food_item_image;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                /*orderStatus = (TextView) itemView.findViewById(R.id.tv_orderStatus);
                orderNo = (TextView) itemView.findViewById(R.id.tv_orderNo);
                orderPrice = (TextView) itemView.findViewById(R.id.tv_orderPrice);
                track_order = (TextView) itemView.findViewById(R.id.track_order);
                cart_list = (RelativeLayout) itemView.findViewById(R.id.cart_list);
                view_order = (TextView) itemView.findViewById(R.id.view_order);
                mSetpview0 = (VerticalStepView) itemView.findViewById(R.id.step_view0);
                vT_rate_txt = (TextView) itemView.findViewById(R.id.vT_rate_txt);
                vT_add_tips = (TextView) itemView.findViewById(R.id.vT_add_tips);*/
                this.vT_ado_status = (TextView) itemView.findViewById(R.id.vT_ado_status);
                this.vT_ado_items = (TextView) itemView.findViewById(R.id.vT_ado_items);
                this.vR_ard_menulist = (RecyclerView) itemView.findViewById(R.id.vR_ard_menulist);
                this.vT_ado_orderon = (TextView) itemView.findViewById(R.id.vT_ado_orderon);
                this.vT_ado_orderonval = (TextView) itemView.findViewById(R.id.vT_ado_orderonval);
                this.vT_ado_price = (TextView) itemView.findViewById(R.id.vT_ado_price);
                this.vT_ado_priceval = (TextView) itemView.findViewById(R.id.vT_ado_priceval);
                this.vC_ado_main = (CardView) itemView.findViewById(R.id.vC_ado_main);
                this.vL_ado_items = (LinearLayout) itemView.findViewById(R.id.vL_ado_items);
                this.vL_cl = (LinearLayout) itemView.findViewById(R.id.vL_cl);
                this.vT_rate_txt = (TextView) itemView.findViewById(R.id.vT_rate_txt);
                this.vT_item_list = (TextView) itemView.findViewById(R.id.vT_item_list);


                this.vT_ado_order_id = itemView.findViewById(R.id.vT_ado_order_id);
                this.vT_ado_orderid_val = itemView.findViewById(R.id.vT_ado_orderid_val);
                //  this.vR_iemCount = (RecyclerView) view.findViewById(R.id.vR_iemCount);


                this.vT_restaurant_text = itemView.findViewById(R.id.vT_restaurant_text);
                this.vT_address_txt = itemView.findViewById(R.id.vT_address_txt);
                this.food_item_image = itemView.findViewById(R.id.food_item_image);
                this.vT_add_tips = itemView.findViewById(R.id.vT_add_tips);
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

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history_menu_details, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_admd_text.setText(cartList.get(position).getCake().getProductName() + " X " + cartList.get(position).getQuantity());
            String price = "₹ " + cartList.get(position).getCake().getPrice();
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

    private void initializePopup(Object dbImg, String resImg, String dbName, String rsName, String res_id, String user_id, String cart_id, Object del_id) {
        paypopup.setContentView(R.layout.popup_rate_delivery);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();

        resId = resId;
        cart_id = cart_id;
        del_id = del_id;
        typeRes = "cakesotre";
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

        String url = WebServices.Foodey_Grocery_Image_URL + resImg;


        if (!url.equals("")) {
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(vI_res);
        }

        String url1 = WebServices.Foodey_Grocery_Image_URL + resImg;


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


}
