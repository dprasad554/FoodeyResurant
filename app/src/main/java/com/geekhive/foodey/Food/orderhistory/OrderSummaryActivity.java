package com.geekhive.foodey.Food.orderhistory;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.home.HomeActivity;
import com.kofigyan.stateprogressbar.StateProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderSummaryActivity extends AppCompatActivity implements View.OnClickListener{

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
    @BindView(R.id.vT_aos_pname)
    TextView vTAosPname;
    @BindView(R.id.vT_aos_place)
    TextView vTAosPlace;
    @BindView(R.id.vT_aos_booking_rec)
    TextView vTAosBookingRec;
    @BindView(R.id.vT_aos_confirm_book)
    TextView vTAosConfirmBook;
    @BindView(R.id.vT_aos_confirmed)
    TextView vTAosConfirmed;
    @BindView(R.id.vS_aos_progress)
    StateProgressBar vSAosProgress;
    @BindView(R.id.vT_aos_charges)
    TextView vTAosCharges;
    @BindView(R.id.vT_aos_grand_total)
    TextView vTAosGrandTotal;
    @BindView(R.id.vT_aos_grand_totalval)
    TextView vTAosGrandTotalval;
    @BindView(R.id.vT_order)
    TextView vT_order;
    @BindView(R.id.vT_order_id)
    TextView vT_order_id;
    @BindView(R.id.deliveryHead)
    TextView deliveryHead;
    @BindView(R.id.addressText)
    TextView addressText;
    @BindView(R.id.vT_payment)
    TextView vT_payment;
    @BindView(R.id.vT_payment_method)
    TextView vT_payment_method;
    @BindView(R.id.vT_payment_stat)
    TextView vT_payment_stat;
    @BindView(R.id.vT_payment_status)
    TextView vT_payment_status;

    String total, orderid, paymentmethod, paymentStatus, addressSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
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
                vTAosBookingRec.setTypeface(NIRMALA);
                vTAosCharges.setTypeface(NIRMALAB);
                vTAosConfirmBook.setTypeface(NIRMALA);
                vTAosConfirmed.setTypeface(NIRMALAB);
                vTAosGrandTotal.setTypeface(NIRMALAB);
                vTAosGrandTotalval.setTypeface(NIRMALA);
                vTAosPlace.setTypeface(NIRMALA);
                vTAosPname.setTypeface(NIRMALAB);
                vT_order.setTypeface(NIRMALAB);
                vT_order_id.setTypeface(NIRMALA);
                deliveryHead.setTypeface(NIRMALAB);
                addressText.setTypeface(NIRMALA);
                vT_payment.setTypeface(NIRMALAB);
                vT_payment_method.setTypeface(NIRMALA);
                vT_payment_stat.setTypeface(NIRMALAB);
                vT_payment_status.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {

        total = getIntent().getStringExtra("total");
        orderid = getIntent().getStringExtra("orderId");
        paymentmethod = getIntent().getStringExtra("paymentmethod");
        paymentStatus = getIntent().getStringExtra("paymentStatus");
        addressSelected = getIntent().getStringExtra("deliveryAddress");

        String grandTotal = "₹ " + total;
        vTAosGrandTotalval.setText(grandTotal);
        vT_order_id.setText(orderid);
        vT_payment_method.setText(paymentmethod);
        vT_payment_status.setText(paymentStatus);
        addressText.setText(addressSelected);

        vITlBack.setVisibility(View.VISIBLE);
        vTTlHeader.setVisibility(View.VISIBLE);
        vLTlLocation.setVisibility(View.GONE);
        vRTlNotification.setVisibility(View.GONE);
        vITlSearch.setVisibility(View.GONE);
        vTTlHeader.setText(getResources().getString(R.string.order_summary));
        vITlBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_tl_back:
                startActivity(new Intent(OrderSummaryActivity.this, HomeActivity.class));
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(OrderSummaryActivity.this, HomeActivity.class));
        finish();
    }

    public void closeSummary(View view) {
        startActivity(new Intent(OrderSummaryActivity.this, HomeActivity.class));
        finish();
    }
}
