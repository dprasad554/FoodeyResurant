package com.geekhive.foodey.Grocery.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.adapter.GrocerySliderViewPagerAdapter;
import com.geekhive.foodey.Grocery.beans.groceryaddcart.GroceryAddToCart;
import com.geekhive.foodey.Grocery.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class GroceryDetailActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    Timer timer1;
    DotsIndicator dot1;
    ViewPager viewPager;
    GrocerySliderViewPagerAdapter homePagerAdapter;
    TextView original, description;
    int slide[] = {R.drawable.productslideone, R.drawable.productslidetwo};
    Button addCart;
    String product_name, url;
    String title, id, price, prd_description, mrp, quantity, user_id, store_id, product_id;
    TextView tv_pQty, tv_pPrice, tv_productName, tv_productOriginal, tv_product_content;
    Button increase, decrease;
    int minteger = 1;
    ConnectionDetector mDetector;
    String item_count;
    TextView displayInteger;
    ImageView iv_productImage;

    RecyclerView vRAcoDeliverymode;
    ModeAdapter modeAdapter;
    ArrayList<String> timeslot;
    TextView vT_ap_sideheader;
    public Typeface Montserrat_Regular;
    public Typeface Montserrat_SemiBold;
    Dialog schedulePopup;
    String selected_datetime = "";
    String selectedDateStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_grocery_detail);

        toolbar = findViewById(R.id.toolbar);
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        price = getIntent().getStringExtra("price");
        prd_description = getIntent().getStringExtra("description");
        mrp = getIntent().getStringExtra("mrp");
        quantity = getIntent().getStringExtra("quantity");
        user_id = Prefs.getUserId(this);
        store_id = getIntent().getStringExtra("store_id");
        product_id = getIntent().getStringExtra("product_id");
        url = getIntent().getStringExtra("url");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        iv_productImage = findViewById(R.id.iv_productImage);
        tv_pQty = findViewById(R.id.tv_pQty);
        tv_pPrice = findViewById(R.id.tv_pPrice);
        tv_productName = findViewById(R.id.tv_productName);
        tv_productOriginal = findViewById(R.id.tv_productCut);
        tv_product_content = findViewById(R.id.tv_product_content);
        vRAcoDeliverymode = findViewById(R.id.vR_aco_deliverymode);

        increase = findViewById(R.id.btn_increase);
        decrease = findViewById(R.id.btn_decrease);

        tv_productName = findViewById(R.id.tv_productName);
        Typeface CustomName = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        tv_productName.setTypeface(CustomName);
        tv_productName.setText(product_name);

        original = findViewById(R.id.tv_productOriginal);
        Typeface CustomOriginal = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        original.setTypeface(CustomOriginal);

        tv_productOriginal = findViewById(R.id.tv_productCut);
        Typeface CustomCut = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        tv_productOriginal.setTypeface(CustomCut);
        tv_productOriginal.setText("Rs 235");
        tv_productOriginal.setPaintFlags(tv_productOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        description = findViewById(R.id.tv_product_description);
        Typeface CustomDescription = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        description.setTypeface(CustomDescription);

        tv_product_content = findViewById(R.id.tv_product_content);
        Typeface CustomContent = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-SemiBold.ttf");
        tv_product_content.setTypeface(CustomContent);
        String url_ = WebServices.Foodey_Grocery_Image + url;
        if (!url.equals("")) {
            Picasso.get()
                    .load(url_)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(iv_productImage);
        }
        tv_pQty.setText(quantity);
        tv_pPrice.setText("Rs. " + price);
        tv_productName.setText(title);
        tv_productOriginal.setText("MRP:" + mrp);
        int p = Integer.parseInt(price);
        int m = Integer.parseInt(mrp);
        if (p >= m) {
            tv_productOriginal.setVisibility(View.GONE);
        }
        tv_product_content.setText(prd_description);
        addCart = findViewById(R.id.btn_addCart);
        displayInteger = (TextView) findViewById(R.id.tv_integerNumber);
        displayInteger.setText("1");
        item_count = displayInteger.getText().toString();

        modeAdapter = new ModeAdapter();
        vRAcoDeliverymode.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAcoDeliverymode.setAdapter(modeAdapter);

        //For Titles
        timeslot = new ArrayList<String>();
        timeslot.add("08:30AM-12:30AM");
        timeslot.add("12:30AM-8.30PM");
        timeslot.add("8.30PM-11.30PM");


        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallAdd(Prefs.getSoreLat(GroceryDetailActivity.this), Prefs.getSoreLang(GroceryDetailActivity.this));

            }
        });

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseInteger(view);
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseInteger(view);
            }
        });

        setValues();
        //CallService();
    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService(String distance) {
        if (this.mDetector.isConnectingToInternet()) {
            if (!item_count.equals("0")) {
                new WebServices(this).AddToCart(WebServices.Foodey_Grocery_Services,
                        WebServices.ApiType.addtocart, user_id, store_id, product_id, item_count, price, mrp, distance, Prefs.getCityId(this));
            } else {
                SnackBar.makeText(this, "Quantity cant be 0", SnackBar.LENGTH_SHORT).show();
            }

        } else {
            SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    private void CallAdd(String lat, String lang) {

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
            //CallService();
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSuccess) {
        if (apiType == WebServices.ApiType.addtocart) {
            if (!isSuccess) {
                SnackBar.makeText(this, "Something Went Wrong", SnackBar.LENGTH_SHORT).show();
            } else {
                final GroceryAddToCart addToCart = (GroceryAddToCart) response;
                if (!isSuccess || addToCart == null) {
                    SnackBar.makeText(this, "No Record Found", SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(this, addToCart.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSuccess) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        if (distance.contains("km")) {
                            String dis = distance.replace(" km", "");
                            CallService(dis);
                        } else {
                            String dis = "1";
                            CallService(dis);
                        }

                    }

                } else {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);
    }

    public void decreaseInteger(View view) {
        if (minteger != 1) {
            minteger = minteger - 1;
            display(minteger);
        }
    }

    private void display(int number) {
        displayInteger.setText(String.valueOf(number));
        item_count = displayInteger.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            startActivity(new Intent(GroceryDetailActivity.this, GroceryCheckOutActivityNew.class));
            return true;
        }
        switch (id) {
            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
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
                Toast.makeText(GroceryDetailActivity.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
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

        ScheduleAdapter filterAdapter = new ScheduleAdapter(this, timeslot);
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
                String dateTime = selectedDateStr + " - " + selected_datetime;
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
                vT_ap_sideheader = (TextView) view.findViewById(R.id.vT_ap_sideheader);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
