package com.geekhive.foodey.Food.eatout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectOfferActivity extends AppCompatActivity implements View.OnClickListener {
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
    @BindView(R.id.vT_aso_name)
    TextView vTAsoName;
    @BindView(R.id.vT_aso_place)
    TextView vTAsoPlace;
    @BindView(R.id.vT_aso_timing)
    TextView vTAsoTiming;
    @BindView(R.id.vT_aso_offer)
    TextView vTAsoOffer;
    @BindView(R.id.vT_aso_table)
    TextView vTAsoTable;
    @BindView(R.id.vR_aso_list)
    RecyclerView vRAsoList;

    OfferAdapter offerAdapter;
    @BindView(R.id.vL_tl_skip)
    TextView vLTlSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_offer);
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
                vTAsoName.setTypeface(NIRMALAB);
                vTAsoOffer.setTypeface(NIRMALA);
                vTAsoPlace.setTypeface(NIRMALA);
                vTAsoTable.setTypeface(NIRMALA);
                vTAsoTiming.setTypeface(NIRMALA);
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
        vITlSearch.setVisibility(View.GONE);
        vTTlHeader.setText(getResources().getString(R.string.select_day_time));
        vLTlSkip.setVisibility(View.VISIBLE);
        vLTlSkip.setOnClickListener(this);
        vITlBack.setOnClickListener(this);
        offerAdapter = new OfferAdapter();
        vRAsoList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAsoList.setAdapter(offerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_tl_back:
                finish();
                break;

            case R.id.vL_tl_skip:
                Intent intent=new Intent(SelectOfferActivity.this,BookingSummaryActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }

    public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_offer, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_ado_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SelectOfferActivity.this,BookingSummaryActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            });
        }

        public int getItemCount() {
            return 5;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_ado_main;
            TextView vT_ado_name, vT_ado_day, vT_ado_timing, vT_ado_vaild, vT_ado_select;

            public MyViewHolder(View view) {
                super(view);
                this.vL_ado_main = (LinearLayout) view.findViewById(R.id.vL_ado_main);
                this.vT_ado_name = (TextView) view.findViewById(R.id.vT_ado_name);
                this.vT_ado_day = (TextView) view.findViewById(R.id.vT_ado_day);
                this.vT_ado_timing = (TextView) view.findViewById(R.id.vT_ado_timing);
                this.vT_ado_vaild = (TextView) view.findViewById(R.id.vT_ado_vaild);
                this.vT_ado_select = (TextView) view.findViewById(R.id.vT_ado_select);

                new Runnable() {
                    public void run() {
                        vT_ado_name.setTypeface(NIRMALA);
                        vT_ado_day.setTypeface(NIRMALA);
                        vT_ado_vaild.setTypeface(NIRMALA);
                        vT_ado_select.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
