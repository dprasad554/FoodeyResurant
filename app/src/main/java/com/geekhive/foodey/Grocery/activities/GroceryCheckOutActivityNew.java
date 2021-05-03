package com.geekhive.foodey.Grocery.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;
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
import android.widget.Toast;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.beans.grocerycartlist.CartList;
import com.geekhive.foodey.Grocery.beans.grocerycartlist.GCartDetail;
import com.geekhive.foodey.Grocery.beans.groceryremovecart.GroceryRemoveCartItem;
import com.geekhive.foodey.Grocery.beans.groceryupdatecart.GroceryUpdateCartItem;
import com.geekhive.foodey.Grocery.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class GroceryCheckOutActivityNew extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

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


    TextView vT_ap_sideheader;




    @BindView(R.id.vR_aco_deliverymode)
    RecyclerView vRAcoDeliverymode;

    ModeAdapter modeAdapter;
    ArrayList<String> timeslot;

    public Typeface Montserrat_Regular;
    public Typeface Montserrat_SemiBold;

    Dialog schedulePopup;

    String selected_datetime = "";
    String selectedDateStr="";






    String qty;
    int quantity = 1;
    DishAdapter dishAdapter;
    ConnectionDetector mDetector;

    CartList cartList;
    Dialog deletePopup;
    String grandTotal;
    String orderId;
    String lat = "";
    String lang = "";
    String cart_id = "";
    String cardDetailId = "";
    List<GCartDetail> cartDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_check_out_new);
        ButterKnife.bind(this);
        setvalues();
        CallService();

        modeAdapter = new ModeAdapter();
        vRAcoDeliverymode.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAcoDeliverymode.setAdapter(modeAdapter);

        //For Titles
        timeslot = new ArrayList<String>();
        timeslot.add("08:30AM-12:30AM");
        timeslot.add("12:30AM-8.30PM");
        timeslot.add("8.30PM-11.30PM");

       /* view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroceryCheckOutActivityNew.this, GroceryCartitemActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        checkButton.setOnClickListener(this);
        vI_aac_summary_back.setOnClickListener(this);
        removeCartItems.setOnClickListener(this);
        view_all.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkButton:
                Intent intent = new Intent(GroceryCheckOutActivityNew.this, GroceryManageAddressActivity.class);
                intent.putExtra("order_id", orderId);
                startActivity(intent);
                break;
            case R.id.vI_aac_summary_back:
                finish();
                break;
            case R.id.removeCartItems:
                Initializermovepopup();
                initializeremove(cartList.getCartList().getGCart().getId());
                break;
            case R.id.view_all:
                Initializeviewallpopup();
                initializeviewall();
                break;
        }
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {


            new WebServices(this).GetCartList(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.cartList, Prefs.getUserId(this));
        } else {
            SnackBar.makeText(GroceryCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }


    private void CallUpdateService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).UpdateCartItem(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.updateCartItem, Prefs.getUserId(this), cart_id, String.valueOf(quantity), cardDetailId, distance);
        } else {

            SnackBar.makeText(GroceryCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }

    private void CallRemoveService(String cart_id) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).RemoveCart(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.removeCartItem, Prefs.getUserId(this), cart_id);
        } else {

            SnackBar.makeText(GroceryCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
        }

        return;
    }

    private void CallRemoveItemService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).RemoveCartItem(WebServices.Foodey_Grocery_Services,
                    WebServices.ApiType.removeCartItem, cardDetailId, cart_id, distance);
        } else {

            SnackBar.makeText(GroceryCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
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


    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.cartList) {
            if (!isSucces) {
                SnackBar.makeText(GroceryCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                cartList = (CartList) response;
                if (cartList.getCartList() != null) {
                    if (cartList.getCartList().getGCartDetail().size() != 0) {
                        cartSumLin.setVisibility(View.VISIBLE);
                        checkButton.setVisibility(View.VISIBLE);
                        orderId = cartList.getCartList().getGCart().getOrderId();
                        grandTotal = cartList.getCartList().getGCart().getGrandTotal();
                        String price = "₹ " + grandTotal;
                        vT_grand_total.setText(price);
                        vT_rest_name.setText(cartList.getCartList().getStore().getName());
                        String disc = "-₹ " + cartList.getCartList().getGCart().getDiscount();
                        vT_discount.setText(disc);
                        String taxes = "₹ " + cartList.getCartList().getGCart().getTax();
                        vT_taxes.setText(taxes);
                        String packages = "₹ " + cartList.getCartList().getGCart().getPackingCharge();
                        vT_packaging.setText(packages);
                        String deliveryCharges = "₹ " + cartList.getCartList().getGCart().getDeliveryBoyCharge();
                        vT_delivery.setText(deliveryCharges);
                        String subamount = "₹ " + cartList.getCartList().getGCart().getTotel();
                        vT_aco_sub_amount.setText(subamount);
                        String saving = "₹ " + cartList.getCartList().getGCart().getDiscount();
                        vT_savings.setText(saving);
                        lat = cartList.getCartList().getStore().getLatitude();
                        lang = cartList.getCartList().getStore().getLongitude();
                        cartDetail = cartList.getCartList().getGCartDetail();
                        dishAdapter = new DishAdapter();
                        vRAcoList.setLayoutManager(new LinearLayoutManager(this));
                        vRAcoList.setAdapter(dishAdapter);
                        if (cartList.getCartList().getGCartDetail().size() >= 5) {
                            view_all.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    cartSumLin.setVisibility(View.GONE);
                    checkButton.setVisibility(View.GONE);
                    SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
                }
                if(cartList.getCartList().getGCart().getTotalDiscount().equals("0")){
                    vL_savings.setVisibility(View.GONE);
                }

            }
        } else if (apiType == WebServices.ApiType.updateCartItem) {
            if (!isSucces) {
                SnackBar.makeText(GroceryCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GroceryUpdateCartItem updateCartItem = (GroceryUpdateCartItem) response;
                SnackBar.makeText(GroceryCheckOutActivityNew.this, "Cart List Updated", SnackBar.LENGTH_SHORT).show();
                startActivity(new Intent(GroceryCheckOutActivityNew.this, GroceryCheckOutActivityNew.class));
                GroceryCheckOutActivityNew.this.finish();

            }
        } else if (apiType == WebServices.ApiType.removeCartItem) {
            if (!isSucces) {
                SnackBar.makeText(GroceryCheckOutActivityNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GroceryRemoveCartItem removeCartItem = (GroceryRemoveCartItem) response;
                SnackBar.makeText(GroceryCheckOutActivityNew.this, "Cart List Updated", SnackBar.LENGTH_SHORT).show();
                startActivity(new Intent(GroceryCheckOutActivityNew.this, GroceryCheckOutActivityNew.class));
                GroceryCheckOutActivityNew.this.finish();

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
    private void InitializeSchedulepopup() {
        schedulePopup = new Dialog(this);
        schedulePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        schedulePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        schedulePopup.setContentView(R.layout.cake_popup_schedule);
        schedulePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        schedulePopup.setCancelable(true);
        schedulePopup.setCanceledOnTouchOutside(true);
    }

    private void initializScheduleshowpopup(String head) {
        schedulePopup.setContentView(R.layout.cake_popup_schedule);
        schedulePopup.setCancelable(true);
        schedulePopup.setCanceledOnTouchOutside(true);
        schedulePopup.show();

        final LinearLayout VL_ps_menu = (LinearLayout) schedulePopup.findViewById(R.id.VL_ps_menu);
        final TextView vT_ps_schedule = (TextView) schedulePopup.findViewById(R.id.vT_ps_schedule);
        final TextView vT_ps_done = (TextView) schedulePopup.findViewById(R.id.vT_ps_done);
        final RecyclerView vR_ps_list = (RecyclerView) schedulePopup.findViewById(R.id.vR_ps_list);
        final ImageView vI_ps_close = (ImageView) schedulePopup.findViewById(R.id.vI_ps_close);


        Calendar startDate = Calendar.getInstance();
        selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", startDate).toString();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 4);

        vT_ps_schedule.setText(head);
//        HorizontalCalendarView vC_ps_calendarView=schedulePopup.findViewById(R.id.vC_ps_calendarView);
        HorizontalCalendar vC_ps_calendarView = new HorizontalCalendar.Builder(VL_ps_menu, R.id.vC_ps_calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .mode(HorizontalCalendar.Mode.DAYS)
                .configure()
                .sizeMiddleText(14f)
                .formatMiddleText("EEE")
                .formatBottomText("d MMM")
                .showTopText(false)
                .showBottomText(true)
                .textColor(getResources().getColor(R.color.text_color), getResources().getColor(R.color.white))
                .end()
                .defaultSelectedDate(startDate).build();

        vC_ps_calendarView.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                Toast.makeText(GroceryCheckOutActivityNew.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                Log.e("onDateSelected", selectedDateStr + " - Position = " + position);
            }

        });
        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_ps_schedule.setTypeface(Montserrat_SemiBold);
                vT_ps_done.setTypeface(Montserrat_Regular);

            }
        };
        r.run();
        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
//        schedulePopup.getWindow().setLayout(width, height);
        schedulePopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        schedulePopup.getWindow().setGravity(Gravity.BOTTOM);

        ScheduleAdapter filterAdapter = new ScheduleAdapter(this,timeslot);
        vR_ps_list.setLayoutManager(new LinearLayoutManager(this));
        vR_ps_list.setAdapter(filterAdapter);


        vI_ps_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulePopup.dismiss();
            }
        });
        vT_ps_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulePopup.dismiss();
                String dateTime = selectedDateStr+" - "+selected_datetime;
                vT_ap_sideheader.setText(dateTime);
            }
        });
        schedulePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        schedulePopup.setCancelable(true);
        schedulePopup.setCanceledOnTouchOutside(true);
        schedulePopup.show();
    }
    public class ModeAdapter extends RecyclerView.Adapter<ModeAdapter.MyViewHolder> {

        String array[] = {
                getResources().getString(R.string.schedule_delivery)};


        int selected = -1;

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ModeAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_pay, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_ap_header.setText(array[position]);

            if (selected == position) {
                holder.vI_ap_select.setBackgroundResource(R.drawable.circle_stroke_green);
            } else {
                holder.vI_ap_select.setBackgroundResource(R.drawable.circle_stroke_grey);
            }

            holder.vL_ap_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = position;
                    notifyDataSetChanged();
                    if (position == 0) {
                        InitializeSchedulepopup();
                        initializScheduleshowpopup(getResources().getString(R.string.schedule_delivery));
                    }
                }
            });
            //holder.vT_ap_sideheader.setText(selectedDateStr+" - "+selected_datetime);
        }
        public int getItemCount() {
            return 1;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_ap_main;
            ImageView vI_ap_select, vI_ap_edit;
            TextView vT_ap_header;

            public MyViewHolder(View view) {
                super(view);
                this.vL_ap_main = (LinearLayout) view.findViewById(R.id.vL_ap_main);
                this.vI_ap_select = (ImageView) view.findViewById(R.id.vI_ap_select);
                this.vI_ap_edit = (ImageView) view.findViewById(R.id.vI_ap_edit);
                this.vT_ap_header = (TextView) view.findViewById(R.id.vT_ap_header);
                vT_ap_sideheader =  view.findViewById(R.id.vT_ap_sideheader);

                new Runnable() {
                    public void run() {
                        vT_ap_header.setTypeface(Montserrat_SemiBold);
                        vT_ap_sideheader.setTypeface(Montserrat_Regular);
                    }
                }.run();
            }
        }
    }
    public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

        private Context mcontext;
        ArrayList<String> titlesNames;
        int selected = -1;
        public ScheduleAdapter(Context context, ArrayList<String> titlesNames) {
            this.titlesNames = titlesNames;
            this.mcontext = context;
        }
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_schedule, parent, false));
        }
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vT_ads_header.setText(titlesNames.get(position));

            if (selected == position) {
                holder.vI_ads_select.setBackgroundResource(R.drawable.circle_stroke_green);
            } else {
                holder.vI_ads_select.setBackgroundResource(R.drawable.circle_stroke_grey);
            }

            holder.vT_ads_header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = position;
                    selected_datetime = titlesNames.get(position);
                    notifyDataSetChanged();
                }
            });

        }

        public int getItemCount() {
            return 3;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_ads_main;
            ImageView vI_ads_select;
            TextView vT_ads_header;

            public MyViewHolder(View view) {
                super(view);
                this.vL_ads_main = (LinearLayout) view.findViewById(R.id.vL_ads_main);
                this.vI_ads_select = (ImageView) view.findViewById(R.id.vI_ads_select);
                this.vT_ads_header = (TextView) view.findViewById(R.id.vT_ads_header);

                new Runnable() {
                    public void run() {
                        vT_ads_header.setTypeface(Montserrat_Regular);
                    }
                }.run();
            }
        }
    }

    public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_adapter_dishes, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Prefs.setOrderId(GroceryCheckOutActivityNew.this, cartList.getCartList().getGCart().getOrderId());
            holder.vT_add_name.setText(cartDetail.get(position).getGrocery().getProductName());
            String price = "₹" + " " + cartDetail.get(position).getGrocery().getPrice();
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
                    cart_id = cartDetail.get(position).getCartId();
                    cardDetailId = cartDetail.get(position).getId();
                    if (cartDetail.size() == 1) {
                        if (holder.vT_add_quantity.getText().toString().equals("1")) {
                            Initializermovepopup();
                            initializeremove(cartDetail.get(position).getCartId());
                        } else {
                            if (quantity != 1) {
                                quantity = quantity - 1;
                                holder.vT_add_quantity.setText(String.valueOf(quantity));
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
                    cart_id = cartDetail.get(position).getCartId();
                    cardDetailId = cartDetail.get(position).getId();
                    quantity = quantity + 1;
                    holder.vT_add_quantity.setText(String.valueOf(quantity));
                    CallAdd();
                }
            });
            if(cartList.getCartList().getGCartDetail().size() >=5){
                holder.vI_add_veg.setVisibility(View.VISIBLE);
            }
            String url = "http://foodeyservices.com/img/grocery/"+
                    cartList.getCartList().getGCartDetail().get(position).getGrocery().getImage();
            if (!url.equals("")){
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.vI_add_veg);
            }

        }

        public int getItemCount() {
            if (cartDetail.size() > 5) {
                return 1;
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
        deletePopup.setContentView(R.layout.grocery_popup_shopping_delete);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializdeletePopup(final String cart_id, final String cardDetailId) {
        deletePopup.setContentView(R.layout.grocery_popup_shopping_delete);
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
        deletePopup.setContentView(R.layout.grocery_popup_shopping_delete);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializeremove(final String cart_id) {
        deletePopup.setContentView(R.layout.grocery_popup_shopping_delete);
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

    private void Initializeviewallpopup() {
        deletePopup = new Dialog(this);
        deletePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        deletePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        deletePopup.setContentView(R.layout.grocery_viewall_popup);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
    }

    private void initializeviewall() {
        deletePopup.setContentView(R.layout.grocery_viewall_popup);
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
        deletePopup.show();
        final TextView vT_psd_delete = (TextView) deletePopup.findViewById(R.id.vT_psd_delete);
        final RecyclerView vR_aco_list = (RecyclerView) deletePopup.findViewById(R.id.vR_aco_list);
        vT_psd_delete.setText("View all cart item");
        dishAdapter = new DishAdapter();
        vR_aco_list.setLayoutManager(new LinearLayoutManager(this));
        vR_aco_list.setAdapter(dishAdapter);
        deletePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deletePopup.setCancelable(true);
        deletePopup.setCanceledOnTouchOutside(true);
        deletePopup.show();
    }

}
