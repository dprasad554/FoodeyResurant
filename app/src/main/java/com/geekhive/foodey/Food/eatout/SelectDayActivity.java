package com.geekhive.foodey.Food.eatout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.customs.SpacesItemDecoration;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class SelectDayActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.vL_toolbarLayout)
    LinearLayout vLToolbarLayout;
    @BindView(R.id.vT_asd_day)
    TextView vTAsdDay;
    @BindView(R.id.vC_asd_calendarView)
    HorizontalCalendarView vCAsdCalendarView;
    @BindView(R.id.vT_asd_after)
    TextView vTAsdAfter;
    @BindView(R.id.vI_asd_after)
    ImageView vIAsdAfter;
    @BindView(R.id.vR_asd_after)
    RecyclerView vRAsdAfter;
    @BindView(R.id.vT_asd_eve)
    TextView vTAsdEve;
    @BindView(R.id.vI_asd_eve)
    ImageView vIAsdEve;
    @BindView(R.id.vR_asd_eve)
    RecyclerView vRAsdEve;
    @BindView(R.id.vL_asd_main)
    LinearLayout vLAsdMain;
    TimeAdapter timeAdapter;
    EveTimeAdapter eveTimeAdapter;
    @BindView(R.id.vL_tl_skip)
    TextView vLTlSkip;
    @BindView(R.id.vR_asd_calender)
    RecyclerView vRAsdCalender;

    DayAdapter dayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day);
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
                vTAsdAfter.setTypeface(NIRMALAB);
                vTAsdDay.setTypeface(NIRMALAB);
                vTAsdEve.setTypeface(NIRMALAB);
                vLTlSkip.setTypeface(NIRMALA);

            }
        };
        r.run();
    }

    private void setvalues() {
        vITlBack.setVisibility(View.VISIBLE);
        vTTlHeader.setVisibility(View.VISIBLE);
        vLTlLocation.setVisibility(View.GONE);
        vRTlNotification.setVisibility(View.GONE);
        vITlSearch.setVisibility(View.GONE);
        vTTlHeader.setText(getResources().getString(R.string.select_day_time));
        vLTlSkip.setVisibility(View.VISIBLE);
        vLTlSkip.setText(getResources().getString(R.string.continue_));

        vITlBack.setOnClickListener(this);
        vLTlSkip.setOnClickListener(this);
        vIAsdEve.setOnClickListener(this);
        vIAsdAfter.setOnClickListener(this);

        vRAsdAfter.setVisibility(View.VISIBLE);
        vIAsdAfter.setBackgroundResource(R.drawable.right_arrow_small);
        vIAsdAfter.setRotation(-90);

        vRAsdEve.setVisibility(View.GONE);
        vIAsdEve.setBackgroundResource(R.drawable.right_arrow_small);
        vIAsdEve.setRotation(90);

        Calendar startDate = Calendar.getInstance();

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 4);


        HorizontalCalendar vC_ps_calendarView = new HorizontalCalendar.Builder(vLAsdMain, R.id.vC_asd_calendarView)
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
                String selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                Toast.makeText(SelectDayActivity.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                Log.e("onDateSelected", selectedDateStr + " - Position = " + position);
            }

        });


        dayAdapter = new DayAdapter();
        vRAsdCalender.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        vRAsdCalender.setAdapter(dayAdapter);


        timeAdapter = new TimeAdapter();
        RecyclerView.LayoutManager mLayoutManagerb = new GridLayoutManager(getApplicationContext(), 5);
        vRAsdAfter.addItemDecoration(new SpacesItemDecoration(5));
        vRAsdAfter.setLayoutManager(mLayoutManagerb);
        vRAsdAfter.setAdapter(timeAdapter);

        eveTimeAdapter = new EveTimeAdapter();
        RecyclerView.LayoutManager mLayoutManagerc = new GridLayoutManager(getApplicationContext(), 5);
        vRAsdEve.addItemDecoration(new SpacesItemDecoration(5));
        vRAsdEve.setLayoutManager(mLayoutManagerc);
        vRAsdEve.setAdapter(eveTimeAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_tl_back:
                finish();
                break;
            case R.id.vI_asd_after:
                vRAsdAfter.setVisibility(View.VISIBLE);
                vIAsdAfter.setBackgroundResource(R.drawable.right_arrow_small);
                vIAsdAfter.setRotation(-90);

                vRAsdEve.setVisibility(View.GONE);
                vIAsdEve.setBackgroundResource(R.drawable.right_arrow_small);
                vIAsdEve.setRotation(90);
                break;
            case R.id.vI_asd_eve:
                vRAsdAfter.setVisibility(View.GONE);
                vIAsdAfter.setBackgroundResource(R.drawable.right_arrow_small);
                vIAsdAfter.setRotation(90);

                vRAsdEve.setVisibility(View.VISIBLE);
                vIAsdEve.setBackgroundResource(R.drawable.right_arrow_small);
                vIAsdEve.setRotation(-90);
                break;
            case R.id.vL_tl_skip:
                Intent intent = new Intent(SelectDayActivity.this, SelectOfferActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

    public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

        int sel=-1;
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_day, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vL_addy_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   sel=position;
                   notifyDataSetChanged();
                }
            });

            if (position==sel)
            {
                holder.vL_addy_main.setBackgroundResource(R.drawable.circle_red__corner_bend);
                holder.vT_addy_day.setTextColor(getResources().getColor(R.color.white));
            }else {
                holder.vL_addy_main.setBackgroundResource(R.drawable.circle_grey__corner_bend);
                holder.vT_addy_day.setTextColor(getResources().getColor(R.color.text_color));
            }

        }

        public int getItemCount() {
            return 20;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_addy_main;
            TextView vT_addy_day;

            public MyViewHolder(View view) {
                super(view);
                this.vL_addy_main = (LinearLayout) view.findViewById(R.id.vL_addy_main);
                this.vT_addy_day = (TextView) view.findViewById(R.id.vT_addy_day);

                new Runnable() {
                    public void run() {
                        vT_addy_day.setTypeface(NIRMALAB);

                    }
                }.run();
            }
        }
    }
    public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.MyViewHolder> {
        int sel=-1;
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_time, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vL_adtd_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sel=position;
                    notifyDataSetChanged();
                }
            });

            if (position==sel)
            {
                holder.vL_adtd_main.setBackgroundResource(R.drawable.circle_red__corner_bend);
                holder.vT_adtd_time.setTextColor(getResources().getColor(R.color.white));
            }else {
                holder.vL_adtd_main.setBackgroundResource(R.drawable.card_border_stroke_white);
                holder.vT_adtd_time.setTextColor(getResources().getColor(R.color.text_color));
            }

        }

        public int getItemCount() {
            return 20;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_adtd_main;
            TextView vT_adtd_time;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adtd_main = (LinearLayout) view.findViewById(R.id.vL_adtd_main);
                this.vT_adtd_time = (TextView) view.findViewById(R.id.vT_adtd_time);

                new Runnable() {
                    public void run() {
                        vT_adtd_time.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }

    public class EveTimeAdapter extends RecyclerView.Adapter<EveTimeAdapter.MyViewHolder> {

        int sel=-1;
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_time, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vL_adtd_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sel=position;
                    notifyDataSetChanged();
                }
            });

            if (position==sel)
            {
                holder.vL_adtd_main.setBackgroundResource(R.drawable.circle_red__corner_bend);
                holder.vT_adtd_time.setTextColor(getResources().getColor(R.color.white));
            }else {
                holder.vL_adtd_main.setBackgroundResource(R.drawable.card_border_stroke_white);
                holder.vT_adtd_time.setTextColor(getResources().getColor(R.color.text_color));
            }

        }

        public int getItemCount() {
            return 20;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_adtd_main;
            TextView vT_adtd_time;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adtd_main = (LinearLayout) view.findViewById(R.id.vL_adtd_main);
                this.vT_adtd_time = (TextView) view.findViewById(R.id.vT_adtd_time);

                new Runnable() {
                    public void run() {
                        vT_adtd_time.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
