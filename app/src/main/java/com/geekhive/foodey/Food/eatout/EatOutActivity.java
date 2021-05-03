package com.geekhive.foodey.Food.eatout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.restaurantList.RestaurantListOut;
import com.geekhive.foodey.Food.beans.sliderhome.SliderHomeList;
import com.geekhive.foodey.Food.orderfood.CheckOutActivityNew;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EatOutActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

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
    ImageView vITlCart;
    @BindView(R.id.vR_tl_search)
    RelativeLayout vRTlSearch;
    @BindView(R.id.vL_toolbarLayout)
    LinearLayout vLToolbarLayout;
    @BindView(R.id.vT_aeo_buffet)
    TextView vTAeoBuffet;
    @BindView(R.id.vL_aeo_buffet)
    LinearLayout vLAeoBuffet;
    @BindView(R.id.vT_aeo_bbq)
    TextView vTAeoBbq;
    @BindView(R.id.vL_aeo_bbq)
    LinearLayout vLAeoBbq;
    @BindView(R.id.vT_aeo_pizza)
    TextView vTAeoPizza;
    @BindView(R.id.vL_aeo_pizza)
    LinearLayout vLAeoPizza;
    @BindView(R.id.vT_aeo_drinks)
    TextView vTAeoDrinks;
    @BindView(R.id.vL_aeo_drinks)
    LinearLayout vLAeoDrinks;
    @BindView(R.id.vT_aeo_show)
    TextView vTAeoShow;
    @BindView(R.id.vL_aeo_show)
    LinearLayout vLAeoShow;
    @BindView(R.id.vT_aeo_biryani)
    TextView vTAeoBiryani;
    @BindView(R.id.vL_aeo_biryani)
    LinearLayout vLAeoBiryani;
    @BindView(R.id.vT_aeo_new)
    TextView vTAeoNew;
    @BindView(R.id.vL_aeo_new)
    LinearLayout vLAeoNew;
    @BindView(R.id.vT_aeo_budget)
    TextView vTAeoBudget;
    @BindView(R.id.vL_aeo_budget)
    LinearLayout vLAeoBudget;
    @BindView(R.id.vT_aeo_all)
    TextView vTAeoAll;
    @BindView(R.id.vL_aeo_all)
    LinearLayout vLAeoAll;
    @BindView(R.id.vL_aeo_hide)
    LinearLayout vLAeoHide;
    @BindView(R.id.vR_aeo_list)
    RecyclerView vRAeoList;
    @BindView(R.id.vN_aeo_nested)
    NestedScrollView vNAeoNested;
    @BindView(R.id.vV_ah_image)
    ViewPager vVAhImage;
    @BindView(R.id.vC_ah_circularIndicator)
    CirclePageIndicator vCAhCircularIndicator;
    int drawable[]={R.drawable.bannerimages,R.drawable.bannerimages,R.drawable.bannerimages};
    EatoutAdapter eatoutAdapter;

    SliderHomeList sliderHomeList;
    RestaurantListOut restaurantListOut;
    ConnectionDetector mDetector;
    String city = "";
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static boolean isVisible(final View view) {
        if (view == null) {
            return false;
        }
        if (!view.isShown()) {
            return false;
        }
        final Rect actualPosition = new Rect();
        view.getGlobalVisibleRect(actualPosition);
        final Rect screen = new Rect(0, 0, getScreenWidth(), getScreenHeight());
        return actualPosition.intersect(screen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_out);
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
                vTAeoAll.setTypeface(NIRMALA);
                vTAeoBbq.setTypeface(NIRMALA);
                vTAeoBiryani.setTypeface(NIRMALA);
                vTAeoBudget.setTypeface(NIRMALA);
                vTAeoBuffet.setTypeface(NIRMALA);
                vTAeoDrinks.setTypeface(NIRMALA);
                vTAeoNew.setTypeface(NIRMALA);
                vTAeoPizza.setTypeface(NIRMALA);
                vTAeoShow.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        city = getIntent().getStringExtra("city");
        CallSliderService();
        vITlBack.setVisibility(View.VISIBLE);
        vTTlHeader.setVisibility(View.VISIBLE);
        vTTlHeader.setText("Restaurants");
        vTTlLocation.setText("");
        vRTlNotification.setVisibility(View.GONE);

        vLAeoShow.setOnClickListener(this);
        vITlBack.setOnClickListener(this);
        vITlCart.setOnClickListener(this);

        /*eatoutAdapter = new EatoutAdapter();
        vRAeoList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAeoList.setAdapter(eatoutAdapter);*/



        vNAeoNested.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                /*if (isVisible(vIAeoBanner)) {
                    vLAeoHide.setVisibility(View.VISIBLE);
                    vLAeoShow.setVisibility(View.GONE);
                    vLAeoDrinks.setVisibility(View.VISIBLE);
                } else {
                    vLAeoHide.setVisibility(View.GONE);
                    vLAeoShow.setVisibility(View.VISIBLE);
                    vLAeoDrinks.setVisibility(View.GONE);
                }*/
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vL_aeo_show:
                vLAeoHide.setVisibility(View.VISIBLE);
                vLAeoShow.setVisibility(View.GONE);
                vLAeoDrinks.setVisibility(View.VISIBLE);
                break;
            case R.id.vI_tl_back:
                finish();
                break;
            case R.id.vI_tl_cart:
                Intent intents = new Intent(this, CheckOutActivityNew.class);
                startActivity(intents);
                overridePendingTransition(0, 0);
                break;
        }
    }

    private void CallSliderService() {
        if (this.mDetector.isConnectingToInternet()) {


            new WebServices(this).SliderImages(WebServices.Foodey_Services,
                    WebServices.ApiType.sliderImages);

            return;
        }

    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantList(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantList, city, Prefs.getUserId(this));
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.restaurantList) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                restaurantListOut = (RestaurantListOut) response;

                if (!isSucces || restaurantListOut == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    eatoutAdapter = new EatoutAdapter();
                    vRAeoList.setLayoutManager(new LinearLayoutManager(this));
                    vRAeoList.setAdapter(eatoutAdapter);
                }
            }
        } if (apiType == WebServices.ApiType.sliderImages) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                sliderHomeList = (SliderHomeList) response;
                CustomPagerMainAdapter mAdapter = new CustomPagerMainAdapter(this);
                vVAhImage.setAdapter(mAdapter);
                vCAhCircularIndicator.setSnap(true);
                vCAhCircularIndicator.setFillColor(getResources().getColor(R.color.colorPrimary));
                vCAhCircularIndicator.setStrokeColor(getResources().getColor(R.color.colorPrimary));
                vCAhCircularIndicator.setRadius(8 * 1);
                vCAhCircularIndicator.setViewPager(vVAhImage);
                CallService();
            }
        }
    }

    public class EatoutAdapter extends RecyclerView.Adapter<EatoutAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_eat_out, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_adeot_name.setText(restaurantListOut.getResturant().get(position).getName());
            holder.vT_adeot_place.setText(restaurantListOut.getResturant().get(position).getLocation());
            holder.vT_adeot_style.setText(restaurantListOut.getResturant().get(position).getType());

            Picasso.get()
                    .load(WebServices.Foodey_Image_Url + restaurantListOut.getResturant().get(position).getImage())
                    .into(holder.vI_adeot_image);


            String s = restaurantListOut.getResturant().get(position).getTiming();
            String[] split = s.split("to");
            final String firstSubString = split[0];
            final String secondSubString = split[1];

            try {


                Date mToday = new Date();

                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                String curTime = sdf.format(mToday);
                Date start = null;

                start = sdf.parse(firstSubString);

                Date end = sdf.parse(secondSubString);
                Date userDate = sdf.parse(curTime);

                if (end.before(start)) {
                    Calendar mCal = Calendar.getInstance();
                    mCal.setTime(end);
                    mCal.add(Calendar.DAY_OF_YEAR, 1);
                    end.setTime(mCal.getTimeInMillis());
                }

                Log.d("curTime", userDate.toString());
                Log.d("start", start.toString());
                Log.d("end", end.toString());


                if (userDate.after(start) && userDate.before(end)) {
                    Log.d("result", "open");
                    holder.vT_adeot_status.setText("Open");
                    if (restaurantListOut.getResturant().get(position).getStatus().equals("1")){
                        holder.vT_adeot_status.setText("Open");
                    } else {
                        holder.vT_adeot_status.setText("Closed");
                    }
                } else {
                    Log.d("result", "closed");
                    holder.vT_adeot_status.setText("Closed");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.vR_adeot_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                        if (holder.vT_adeot_status.getText().equals("Open")) {
                            Log.d("result", "open");
                            if (restaurantListOut.getResturant().get(position).getStatus().equals("1")){
                                Intent intent = new Intent(EatOutActivity.this, RestaurantDetailsActivityCopy.class);
                                intent.putExtra("from", "eat");
                                intent.putExtra("res_id", restaurantListOut.getResturant().get(position).getId());
                                startActivity(intent);
                                EatOutActivity.this.overridePendingTransition(0, 0);
                            } else {
                                SnackBar.makeText(EatOutActivity.this, "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d("result", "closed");
                            SnackBar.makeText(EatOutActivity.this, "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                        }
                }
            });
            Picasso.get()
                    .load(WebServices.Foodey_Rest_Url + restaurantListOut.getResturant().get(position).getImage())
                    .into(holder.vI_adeot_image);

        }

        public int getItemCount() {
            return restaurantListOut.getResturant().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout vR_adeot_main;
            ImageView vI_adeot_image;
            TextView vT_adeot_name, vT_adeot_status, vT_adeot_place, vT_adeot_style, vT_adeot_event,
                    vT_adeot_live, vT_adeot_rating;
            View vV_adeot_view;

            public MyViewHolder(View view) {
                super(view);
                this.vR_adeot_main = (RelativeLayout) view.findViewById(R.id.vR_adeot_main);
                this.vI_adeot_image = (ImageView) view.findViewById(R.id.vI_adeot_image);
                this.vT_adeot_name = (TextView) view.findViewById(R.id.vT_adeot_name);
                this.vT_adeot_status = (TextView) view.findViewById(R.id.vT_adeot_status);
                this.vT_adeot_place = (TextView) view.findViewById(R.id.vT_adeot_place);
                this.vT_adeot_style = (TextView) view.findViewById(R.id.vT_adeot_style);
                this.vT_adeot_event = (TextView) view.findViewById(R.id.vT_adeot_event);
                this.vT_adeot_live = (TextView) view.findViewById(R.id.vT_adeot_live);
                this.vT_adeot_rating = (TextView) view.findViewById(R.id.vT_adeot_rating);
                this.vV_adeot_view = view.findViewById(R.id.vV_adeot_view);
                new Runnable() {
                    public void run() {
                        vT_adeot_name.setTypeface(NIRMALAB);
                        vT_adeot_status.setTypeface(NIRMALA);
                        vT_adeot_place.setTypeface(NIRMALA);
                        vT_adeot_style.setTypeface(NIRMALA);
                        vT_adeot_event.setTypeface(NIRMALA);
                        vT_adeot_live.setTypeface(NIRMALA);
                        vT_adeot_rating.setTypeface(NIRMALA);
                    }
                }.run();
            }
        }
    }

    class CustomPagerMainAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerMainAdapter(Context context) {
            this.mContext = context;
            mLayoutInflater = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        }

        public int getCount() {
            return sliderHomeList.getSlider().size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == ((CardView) object);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = this.mLayoutInflater.inflate(R.layout.adapter_banner_home, container, false);
            CardView vC_adbh_card = (CardView) itemView.findViewById(R.id.vC_adbh_card);
            ImageView vI_adbh_image = (ImageView) itemView.findViewById(R.id.vI_adbh_image);
            Picasso.get()
                    .load(WebServices.Foodey_Image_Url + sliderHomeList.getSlider().get(position))
                    .into(vI_adbh_image);
            container.addView(itemView);
            return itemView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((CardView) object);
        }
    }
}
