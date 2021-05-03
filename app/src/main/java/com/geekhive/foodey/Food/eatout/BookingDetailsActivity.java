package com.geekhive.foodey.Food.eatout;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingDetailsActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.vT_abd_pname)
    TextView vTAbdPname;
    @BindView(R.id.vT_abd_place)
    TextView vTAbdPlace;
    @BindView(R.id.vT_abd_booking_rec)
    TextView vTAbdBookingRec;
    @BindView(R.id.vT_abd_confirm_book)
    TextView vTAbdConfirmBook;
    @BindView(R.id.vT_abd_confirmed)
    TextView vTAbdConfirmed;
    @BindView(R.id.vS_abd_progress)
    StateProgressBar vSAbdProgress;
    @BindView(R.id.vT_abd_update)
    TextView vTAbdUpdate;
    @BindView(R.id.vT_abd_name)
    TextView vTAbdName;
    @BindView(R.id.vT_abd_nameval)
    TextView vTAbdNameval;
    @BindView(R.id.vT_abd_date)
    TextView vTAbdDate;
    @BindView(R.id.vT_abd_dateval)
    TextView vTAbdDateval;
    @BindView(R.id.vT_abd_time)
    TextView vTAbdTime;
    @BindView(R.id.vT_abd_timeval)
    TextView vTAbdTimeval;
    @BindView(R.id.vT_abd_no)
    TextView vTAbdNo;
    @BindView(R.id.vT_abd_noval)
    TextView vTAbdNoval;
    @BindView(R.id.vT_abd_orderid)
    TextView vTAbdOrderid;
    @BindView(R.id.vT_abd_soffer)
    TextView vTAbdSoffer;
    @BindView(R.id.vT_abd_sofferval)
    TextView vTAbdSofferval;
    @BindView(R.id.vL_abd_soffer)
    LinearLayout vLAbdSoffer;
    @BindView(R.id.vT_abd_direction)
    TextView vTAbdDirection;
    @BindView(R.id.vL_abd_direction)
    LinearLayout vLAbdDirection;
    @BindView(R.id.vT_abd_view_res_details)
    TextView vTAbdViewResDetails;
    @BindView(R.id.vL_abd_view_res_details)
    LinearLayout vLAbdViewResDetails;
    @BindView(R.id.vT_abd_share_booking)
    TextView vTAbdShareBooking;
    @BindView(R.id.vL_abd_share_booking)
    LinearLayout vLAbdShareBooking;
    @BindView(R.id.vT_abd_cancel_booking)
    TextView vTAbdCancelBooking;
    @BindView(R.id.vL_abd_cancel_booking)
    LinearLayout vLAbdCancelBooking;
    @BindView(R.id.vT_abd_have_ques)
    TextView vTAbdHaveQues;
    @BindView(R.id.vL_abd_have_ques)
    LinearLayout vLAbdHaveQues;
    Dialog cancellationPopup;
    @BindView(R.id.vT_abd_edit)
    TextView vTAbdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
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
                vTAbdBookingRec.setTypeface(NIRMALA);
                vTAbdCancelBooking.setTypeface(NIRMALA);
                vTAbdConfirmBook.setTypeface(NIRMALA);
                vTAbdConfirmed.setTypeface(NIRMALA);
                vTAbdDate.setTypeface(NIRMALAB);
                vTAbdDateval.setTypeface(NIRMALA);
                vTAbdDirection.setTypeface(NIRMALA);
                vTAbdHaveQues.setTypeface(NIRMALA);
                vTAbdName.setTypeface(NIRMALAB);
                vTAbdNameval.setTypeface(NIRMALA);
                vTAbdNo.setTypeface(NIRMALA);
                vTAbdNoval.setTypeface(NIRMALA);
                vTAbdOrderid.setTypeface(NIRMALAB);
                vTAbdEdit.setTypeface(NIRMALAB);
                vTAbdPlace.setTypeface(NIRMALA);
                vTAbdPname.setTypeface(NIRMALAB);
                vTAbdShareBooking.setTypeface(NIRMALA);
                vTAbdSoffer.setTypeface(NIRMALA);
                vTAbdSofferval.setTypeface(NIRMALA);
                vTAbdTime.setTypeface(NIRMALAB);
                vTAbdTimeval.setTypeface(NIRMALA);
                vTAbdUpdate.setTypeface(NIRMALA);
                vTAbdViewResDetails.setTypeface(NIRMALA);


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
        vTTlHeader.setText(getResources().getString(R.string.booking_details));
        vITlBack.setOnClickListener(this);
        vLAbdCancelBooking.setOnClickListener(this);
        vLAbdDirection.setOnClickListener(this);
        vLAbdSoffer.setOnClickListener(this);
        vLAbdViewResDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_tl_back:
                finish();
                break;
            case R.id.vL_abd_cancel_booking:
                Initializepopup();
                initializcancellationPopup();
                break;
            case R.id.vL_abd_direction:
                Intent intent = new Intent(this, GetDirectionActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.vL_abd_soffer:
                Intent intento = new Intent(this, SelectOfferActivity.class);
                startActivity(intento);
                overridePendingTransition(0, 0);
                break;
            case R.id.vL_abd_view_res_details:
                Intent intentv = new Intent(this, RestaurantDetailsActivity.class);
                startActivity(intentv);
                overridePendingTransition(0, 0);
                break;
        }
    }

    private void Initializepopup() {
        cancellationPopup = new Dialog(BookingDetailsActivity.this);
        cancellationPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        cancellationPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        cancellationPopup.setContentView(R.layout.popup_cancellation);
        cancellationPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancellationPopup.setCancelable(true);
        cancellationPopup.setCanceledOnTouchOutside(true);
    }

    private void initializcancellationPopup() {
        cancellationPopup.setContentView(R.layout.popup_cancellation);
        cancellationPopup.setCancelable(true);
        cancellationPopup.setCanceledOnTouchOutside(true);
        cancellationPopup.show();

        final TextView vT_pc_choose = (TextView) cancellationPopup.findViewById(R.id.vT_pc_choose);
        final RecyclerView vR_pc_list = (RecyclerView) cancellationPopup.findViewById(R.id.vR_pc_list);
        final LinearLayout vL_pc_status = (LinearLayout) cancellationPopup.findViewById(R.id.vL_pc_status);
        final TextView vT_pc_status = (TextView) cancellationPopup.findViewById(R.id.vT_pc_status);
        final TextView vT_pc_notification = (TextView) cancellationPopup.findViewById(R.id.vT_pc_notification);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_pc_choose.setTypeface(NIRMALAB);
                vT_pc_status.setTypeface(NIRMALA);
                vT_pc_notification.setTypeface(NIRMALA);

            }
        };
        r.run();
//        int width = getResources().getDisplayMetrics().widthPixels - 100;
//        int height = getResources().getDisplayMetrics().heightPixels - 250;
//        cancellationPopup.getWindow().setLayout(width, height);
        cancellationPopup.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancellationPopup.getWindow().setGravity(Gravity.CENTER);

        CancelListAdapter cancelListAdapter = new CancelListAdapter();
        vR_pc_list.setLayoutManager(new LinearLayoutManager(BookingDetailsActivity.this));
        vR_pc_list.setAdapter(cancelListAdapter);


        cancellationPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancellationPopup.setCancelable(true);
        cancellationPopup.setCanceledOnTouchOutside(true);
        cancellationPopup.show();
    }

    private void initializcancellationPopupFinal() {
        cancellationPopup.setContentView(R.layout.popup_cancellation);
        cancellationPopup.setCancelable(true);
        cancellationPopup.setCanceledOnTouchOutside(true);
        cancellationPopup.show();

        final TextView vT_pc_choose = (TextView) cancellationPopup.findViewById(R.id.vT_pc_choose);
        final RecyclerView vR_pc_list = (RecyclerView) cancellationPopup.findViewById(R.id.vR_pc_list);
        final LinearLayout vL_pc_status = (LinearLayout) cancellationPopup.findViewById(R.id.vL_pc_status);
        final TextView vT_pc_status = (TextView) cancellationPopup.findViewById(R.id.vT_pc_status);
        final TextView vT_pc_notification = (TextView) cancellationPopup.findViewById(R.id.vT_pc_notification);
        vR_pc_list.setVisibility(View.GONE);
        vL_pc_status.setVisibility(View.VISIBLE);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_pc_choose.setTypeface(NIRMALAB);
                vT_pc_status.setTypeface(NIRMALA);
                vT_pc_notification.setTypeface(NIRMALA);

            }
        };
        r.run();
        int width = getResources().getDisplayMetrics().widthPixels - 100;
        cancellationPopup.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancellationPopup.getWindow().setGravity(Gravity.CENTER);

        cancellationPopup.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });


        cancellationPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancellationPopup.setCancelable(true);
        cancellationPopup.setCanceledOnTouchOutside(true);
        cancellationPopup.show();
    }

    public class CancelListAdapter extends RecyclerView.Adapter<CancelListAdapter.MyViewHolder> {

        private int selectedPosi = -1;

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cancellation, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vL_adc_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosi = position;
                    notifyDataSetChanged();

                }
            });

            if (selectedPosi == position) {
                holder.vI_adc_select.setVisibility(View.VISIBLE);
                cancellationPopup.dismiss();
                Initializepopup();
                initializcancellationPopupFinal();
            } else {
                holder.vI_adc_select.setVisibility(View.GONE);
            }

        }

        public int getItemCount() {
            return 3;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_adc_select;
            LinearLayout vL_adc_main;
            TextView vT_adc_name;

            public MyViewHolder(View view) {
                super(view);
                this.vI_adc_select = (ImageView) view.findViewById(R.id.vI_adc_select);
                this.vT_adc_name = (TextView) view.findViewById(R.id.vT_adc_name);
                this.vL_adc_main = (LinearLayout) view.findViewById(R.id.vL_adc_main);
                new Runnable() {
                    public void run() {
                        vT_adc_name.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
