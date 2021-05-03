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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingSummaryActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.vL_tl_skip)
    TextView vLTlSkip;
    @BindView(R.id.vL_toolbarLayout)
    LinearLayout vLToolbarLayout;
    @BindView(R.id.vT_abs_booking_summary)
    TextView vTAbsBookingSummary;
    @BindView(R.id.vT_abs_date)
    TextView vTAbsDate;
    @BindView(R.id.vT_abs_dateval)
    TextView vTAbsDateval;
    @BindView(R.id.vT_abs_offer)
    TextView vTAbsOffer;
    @BindView(R.id.vT_abs_offerval)
    TextView vTAbsOfferval;
    @BindView(R.id.vT_abs_guest)
    TextView vTAbsGuest;
    @BindView(R.id.vR_abs_guest)
    RecyclerView vRAbsGuest;
    @BindView(R.id.vI_abs_guest)
    ImageView vIAbsGuest;
    @BindView(R.id.vT_abs_guest_detail)
    TextView vTAbsGuestDetail;
    @BindView(R.id.vT_abs_phone_verified)
    TextView vTAbsPhoneVerified;
    @BindView(R.id.vT_abs_name)
    TextView vTAbsName;
    @BindView(R.id.vE_abs_name)
    EditText vEAbsName;
    @BindView(R.id.vT_abs_phone)
    TextView vTAbsPhone;
    @BindView(R.id.vE_abs_phone)
    EditText vEAbsPhone;
    @BindView(R.id.vT_abs_email)
    TextView vTAbsEmail;
    @BindView(R.id.vE_abs_email)
    EditText vEAbsEmail;
    @BindView(R.id.vT_abs_request)
    TextView vTAbsRequest;
    @BindView(R.id.vE_abs_request)
    EditText vEAbsRequest;
    @BindView(R.id.vT_abs_enter)
    TextView vTAbsEnter;
    @BindView(R.id.vT_abs_continue)
    TextView vTAbsContinue;

    GuestAdapter guestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);
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
                vTAbsBookingSummary.setTypeface(NIRMALAB);
                vTAbsContinue.setTypeface(NIRMALAB);
                vTAbsDate.setTypeface(NIRMALA);
                vTAbsDateval.setTypeface(NIRMALA);
                vTAbsEmail.setTypeface(NIRMALA);
                vTAbsEnter.setTypeface(NIRMALA);
                vTAbsGuest.setTypeface(NIRMALAB);
                vTAbsGuestDetail.setTypeface(NIRMALAB);
                vTAbsName.setTypeface(NIRMALA);
                vTAbsOffer.setTypeface(NIRMALA);
                vTAbsOfferval.setTypeface(NIRMALA);
                vTAbsPhone.setTypeface(NIRMALA);
                vTAbsPhoneVerified.setTypeface(NIRMALA);
                vTAbsRequest.setTypeface(NIRMALA);
                vEAbsEmail.setTypeface(NIRMALA);
                vEAbsName.setTypeface(NIRMALA);
                vEAbsPhone.setTypeface(NIRMALA);
                vEAbsRequest.setTypeface(NIRMALA);

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
        vTTlHeader.setText("The Black Rabbit Bangalore");

        vITlBack.setOnClickListener(this);
        vIAbsGuest.setOnClickListener(this);
        vTAbsEmail.setOnClickListener(this);
        vTAbsRequest.setOnClickListener(this);
        vTAbsContinue.setOnClickListener(this);
        guestAdapter = new GuestAdapter();
        vRAbsGuest.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        vRAbsGuest.setAdapter(guestAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_tl_back:
                finish();
                break;
            case R.id.vT_abs_email:
                vEAbsEmail.setVisibility(View.VISIBLE);
                break;
            case R.id.vT_abs_request:
                vEAbsRequest.setVisibility(View.VISIBLE);
                break;
            case R.id.vT_abs_continue:
                Intent intent = new Intent(this, OTPVerificationActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

    public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.MyViewHolder> {

        int sel=-1;
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_guest, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vL_adg_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sel=position;
                    notifyDataSetChanged();
                }
            });

            if (position==sel)
            {
                holder.vL_adg_main.setBackgroundResource(R.drawable.circle_red);
                holder.vT_adg_count.setTextColor(getResources().getColor(R.color.white));
            }else {
                holder.vL_adg_main.setBackgroundResource(R.drawable.circle_stroke_blue);
                holder.vT_adg_count.setTextColor(getResources().getColor(R.color.text_color));
            }
        }

        public int getItemCount() {
            return 20;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_adg_main;
            TextView vT_adg_count;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adg_main = (LinearLayout) view.findViewById(R.id.vL_adg_main);
                this.vT_adg_count = (TextView) view.findViewById(R.id.vT_adg_count);

                new Runnable() {
                    public void run() {
                        vT_adg_count.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
