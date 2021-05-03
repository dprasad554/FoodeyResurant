package com.geekhive.foodey.Food.orderfood;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.beans.cartlist.CartDetail;
import com.geekhive.foodey.Food.beans.cartlist.CartList;
import com.geekhive.foodey.Food.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Food.beans.removecart.RemoveCartItem;
import com.geekhive.foodey.Food.beans.takeaway.TakeAway;
import com.geekhive.foodey.Food.beans.updatecart.UpdateCartItem;
import com.geekhive.foodey.Food.manageaddress.ManageAddressActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutActivityNew extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


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
    Dialog paypopup;
    String instruction_desc = "";
    String coupon = "";


    List<CartDetail> cartDetail;

    String cart_id = "";
    String cardDetailId = "";
    String lat = "";
    String lang = "";
    String takeaway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_new);
        ButterKnife.bind(this);
        setvalues();
        CallService();
        initializeFonts();
        setTypeFace();

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


            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        checkButton.setOnClickListener(this);
        vI_aac_summary_back.setOnClickListener(this);
        removeCartItems.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkButton:
                InitializeOnlinePopup();
                initializeOnlinePopup();
                /*if (instruction_desc != null && coupon != null) {
                    if (!instruction_desc.equals("") && !coupon.equals("")) {

                        Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                        intent.putExtra("order_id", orderId);
                        intent.putExtra("instruction_txt", instruction_desc);
                        intent.putExtra("coupon_value", coupon);
                        intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                        intent.putExtra("order_id", orderId);
                        intent.putExtra("instruction_txt", "");
                        intent.putExtra("coupon_value", "");
                        intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                        startActivity(intent);
                    }

                } else if (instruction_desc != null) {

                    if (!instruction_desc.equals("")) {


                        Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                        intent.putExtra("order_id", orderId);
                        intent.putExtra("instruction_txt", instruction_desc);
                        intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                        // intent.putExtra("coupon_value", coupon);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                        intent.putExtra("order_id", orderId);
                        intent.putExtra("instruction_txt", "");
                        intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                        // intent.putExtra("coupon_value", "");
                        startActivity(intent);
                    }

                } else if (coupon != null) {

                    if (!coupon.equals("")) {


                        Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                        intent.putExtra("order_id", orderId);
                        intent.putExtra("coupon_value", coupon);
                        intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                        // intent.putExtra("coupon_value", coupon);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                        intent.putExtra("order_id", orderId);
                        intent.putExtra("coupon_value", "");
                        intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                        // intent.putExtra("coupon_value", "");
                        startActivity(intent);
                    }

                } else {
                    Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                    intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                    startActivity(intent);
                }*/

                break;
            case R.id.vI_aac_summary_back:
                finish();
                break;
            case R.id.removeCartItems:
                Initializermovepopup();
                initializeremove(cartList.getCartList().getCart().getId());
                break;


        }
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).GetCartList(WebServices.Foodey_Services,
                        WebServices.ApiType.cartList, Prefs.getUserId(this));
            } else {
                SnackBar.makeText(CheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }
            return;
        }

    }

    private void CallUpdateService(String distanceAdd) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).UpdateCartItem(WebServices.Foodey_Services,
                        WebServices.ApiType.updateCartItem, Prefs.getUserId(this), cart_id, String.valueOf(quantity), cardDetailId, distanceAdd);
            } else {

                SnackBar.makeText(CheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallRemoveService(String cart_id) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).RemoveCart(WebServices.Foodey_Services,
                        WebServices.ApiType.removeCartItem, Prefs.getUserId(this), cart_id);
            } else {

                SnackBar.makeText(CheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallRemoveItemService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).RemoveCartItem(WebServices.Foodey_Services,
                        WebServices.ApiType.removeCartItem, cardDetailId, cart_id, distance);
            } else {

                SnackBar.makeText(CheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

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

    private void CallTakeAway(){
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).TakeAway(WebServices.Foodey_Services,
                    WebServices.ApiType.takeaway, Prefs.getUserId(this), cartList.getCartList().getCart().getId(), orderId);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.cartList) {
            if (!isSucces) {
                SnackBar.makeText(CheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                cartList = (CartList) response;
                if(cartList.getCartList() == null){
                    cartSumLin.setVisibility(View.GONE);
                    checkButton.setVisibility(View.GONE);
                }else {
                    takeaway = cartList.getCartList().getResturant().getTakeAway();
                    if (cartList.getCartList() != null) {
                        if (cartList.getCartList().getCartDetail().size() != 0) {
                            cartSumLin.setVisibility(View.VISIBLE);
                            checkButton.setVisibility(View.VISIBLE);
                            orderId = cartList.getCartList().getCart().getOrderId();
                            grandTotal = cartList.getCartList().getCart().getGrandTotal();
                            String price = "₹ " + grandTotal;
                            vT_grand_total.setText(price);
                            vT_rest_name.setText(cartList.getCartList().getResturant().getName());
                            String disc = "-₹ " + cartList.getCartList().getCart().getDiscount();
                            vT_discount.setText(disc);
                            String taxes = "₹ " + cartList.getCartList().getCart().getTax();
                            vT_taxes.setText(taxes);
                            String packages = "₹ " + cartList.getCartList().getCart().getPackingCharge();
                            vT_packaging.setText(packages);
                            String deliveryCharges = "₹ " + cartList.getCartList().getCart().getDelivery();
                            vT_delivery.setText(deliveryCharges);
                            String subamount = "₹ " + cartList.getCartList().getCart().getTotel();
                            vT_aco_sub_amount.setText(subamount);
                            String saving = "₹ " + cartList.getCartList().getCart().getTotalDiscount();
                            vT_savings.setText(saving);
                            lat = cartList.getCartList().getResturant().getLat();
                            lang = cartList.getCartList().getResturant().getLong();
                            cartDetail = cartList.getCartList().getCartDetail();
                            if(cartList.getCartList().getCart().getTotalDiscount().equals("0")){
                                vL_savings.setVisibility(View.GONE);
                            }
                            dishAdapter = new DishAdapter();
                            vRAcoList.setLayoutManager(new LinearLayoutManager(this));
                            vRAcoList.setAdapter(dishAdapter);
                        }
                    } else {
                        cartSumLin.setVisibility(View.GONE);
                        checkButton.setVisibility(View.GONE);
                        SnackBar.makeText(this, "NO items in cart", SnackBar.LENGTH_SHORT).show();
                    }
                }


            }
        } else if (apiType == WebServices.ApiType.updateCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                UpdateCartItem updateCartItem = (UpdateCartItem) response;
                SnackBar.makeText(CheckOutActivityNew.this, updateCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                startActivity(new Intent(CheckOutActivityNew.this, CheckOutActivityNew.class));
                CheckOutActivityNew.this.finish();

            }
        } else if (apiType == WebServices.ApiType.removeCartItem) {
            if (!isSucces) {
                SnackBar.makeText(CheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                RemoveCartItem removeCartItem = (RemoveCartItem) response;
                SnackBar.makeText(CheckOutActivityNew.this, removeCartItem.getMessage(), SnackBar.LENGTH_SHORT).show();
                startActivity(new Intent(CheckOutActivityNew.this, CheckOutActivityNew.class));
                CheckOutActivityNew.this.finish();

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
        } else if (apiType == WebServices.ApiType.takeaway) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                TakeAway takeAway = (TakeAway) response;
                if (takeAway != null) {
                    if (takeAway.getCart() != null) {
                        if (instruction_desc != null && coupon != null) {
                            if (!instruction_desc.equals("") && !coupon.equals("")) {
                                Intent intent = new Intent(CheckOutActivityNew.this, CheckOutActivityTakeAway.class);
                                intent.putExtra("order_id", orderId);
                                intent.putExtra("instruction_txt", instruction_desc);
                                intent.putExtra("coupon_value", coupon);
                                intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(CheckOutActivityNew.this, CheckOutActivityTakeAway.class);
                                intent.putExtra("order_id", orderId);
                                intent.putExtra("instruction_txt", "");
                                intent.putExtra("coupon_value", "");
                                intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                                startActivity(intent);
                            }
                        } else if (instruction_desc != null) {
                            if (!instruction_desc.equals("")) {
                                Intent intent = new Intent(CheckOutActivityNew.this, CheckOutActivityTakeAway.class);
                                intent.putExtra("order_id", orderId);
                                intent.putExtra("instruction_txt", instruction_desc);
                                intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                                // intent.putExtra("coupon_value", coupon);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(CheckOutActivityNew.this, CheckOutActivityTakeAway.class);
                                intent.putExtra("order_id", orderId);
                                intent.putExtra("instruction_txt", "");
                                intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                                // intent.putExtra("coupon_value", "");
                                startActivity(intent);
                            }
                        } else if (coupon != null) {
                            if (!coupon.equals("")) {
                                Intent intent = new Intent(CheckOutActivityNew.this, CheckOutActivityTakeAway.class);
                                intent.putExtra("order_id", orderId);
                                intent.putExtra("coupon_value", coupon);
                                intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                                // intent.putExtra("coupon_value", coupon);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(CheckOutActivityNew.this, CheckOutActivityTakeAway.class);
                                intent.putExtra("order_id", orderId);
                                intent.putExtra("coupon_value", "");
                                intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                                // intent.putExtra("coupon_value", "");
                                startActivity(intent);
                            }

                        } else {
                            Intent intent = new Intent(CheckOutActivityNew.this, CheckOutActivityTakeAway.class);
                            intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                            startActivity(intent);
                        }
                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dishes, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Prefs.setOrderId(CheckOutActivityNew.this, cartList.getCartList().getCart().getOrderId());
            holder.vT_add_name.setText(cartDetail.get(position).getFood().getName());
            String price = "₹" + " " + cartDetail.get(position).getFood().getPrice();
            holder.vT_add_price.setText(price);
            quantity = Integer.parseInt(cartDetail.get(position).getQuantity());
            qty = quantity + "";
            holder.vT_add_quantity.setText(qty);

            if (cartDetail.get(position).getFood().getType().equals("Veg")) {
                holder.vI_add_veg.setImageDrawable(getResources().getDrawable(R.drawable.veg));
            } else {
                holder.vI_add_veg.setImageDrawable(getResources().getDrawable(R.drawable.non_veg));
            }


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
                                cart_id = cartDetail.get(position).getCartId();
                                cardDetailId = cartDetail.get(position).getId();
                                CallAdd();
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
        deletePopup.setContentView(R.layout.popup_shopping_delete);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializdeletePopup(final String cartId, final String cardDetail_Id) {
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
                    cardDetailId = cardDetail_Id;
                    cart_id = cartId;
                    CallAddRItem();
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
        deletePopup.setContentView(R.layout.popup_shopping_delete);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializeremove(final String cart_id) {
        deletePopup.setContentView(R.layout.popup_shopping_delete);
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

    //Tale Away

    private void InitializeOnlinePopup() {
        paypopup = new Dialog(CheckOutActivityNew.this);
        paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        paypopup.setContentView(R.layout.takeaway_popup);
        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
    }

    private void initializeOnlinePopup() {
        paypopup.setContentView(R.layout.takeaway_popup);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();

        final Button v_takeaway = paypopup.findViewById(R.id.v_takeaway);
        final Button v_delivery = paypopup.findViewById(R.id.v_delivery);

//        final TextView view_cart = (TextView) paypopup.findViewById(R.id.view_cart);
//        final ImageView view_cart_img =  paypopup.findViewById(R.id.view_cart_img);


        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paypopup.getWindow().setGravity(Gravity.CENTER);

        if(takeaway.equals("0")){
            v_takeaway.setVisibility(View.GONE);
        }else {
            v_takeaway.setVisibility(View.VISIBLE);
        }

        v_takeaway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paypopup.isShowing()){
                    paypopup.dismiss();
                    CallTakeAway();
                }

            }
        });

        v_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paypopup.isShowing()){
                    paypopup.dismiss();
                    if (instruction_desc != null && coupon != null) {
                        if (!instruction_desc.equals("") && !coupon.equals("")) {
                            Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                            intent.putExtra("order_id", orderId);
                            intent.putExtra("instruction_txt", instruction_desc);
                            intent.putExtra("coupon_value", coupon);
                            intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                            intent.putExtra("order_id", orderId);
                            intent.putExtra("instruction_txt", "");
                            intent.putExtra("coupon_value", "");
                            intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                            startActivity(intent);
                        }

                    } else if (instruction_desc != null) {

                        if (!instruction_desc.equals("")) {


                            Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                            intent.putExtra("order_id", orderId);
                            intent.putExtra("instruction_txt", instruction_desc);
                            intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                            // intent.putExtra("coupon_value", coupon);
                            startActivity(intent);

                        } else {

                            Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                            intent.putExtra("order_id", orderId);
                            intent.putExtra("instruction_txt", "");
                            intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                            // intent.putExtra("coupon_value", "");
                            startActivity(intent);
                        }

                    } else if (coupon != null) {

                        if (!coupon.equals("")) {


                            Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                            intent.putExtra("order_id", orderId);
                            intent.putExtra("coupon_value", coupon);
                            intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                            // intent.putExtra("coupon_value", coupon);
                            startActivity(intent);

                        } else {

                            Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                            intent.putExtra("order_id", orderId);
                            intent.putExtra("coupon_value", "");
                            intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                            // intent.putExtra("coupon_value", "");
                            startActivity(intent);
                        }

                    } else {
                        Intent intent = new Intent(CheckOutActivityNew.this, ManageAddressActivity.class);
                        intent.putExtra("cart_id", cartList.getCartList().getCart().getId());
                        startActivity(intent);
                    }
                }

            }
        });


        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();
    }
}
