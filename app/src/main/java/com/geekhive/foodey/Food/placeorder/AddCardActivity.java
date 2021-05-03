package com.geekhive.foodey.Food.placeorder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCardActivity extends AppCompatActivity implements View.OnClickListener{

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    @BindView(R.id.vI_aac_back)
    ImageView vIAacBack;
    @BindView(R.id.vT_aac_header)
    TextView vTAacHeader;
    @BindView(R.id.vT_aac_accept)
    TextView vTAacAccept;
    @BindView(R.id.vE_aac_name)
    EditText vEAacName;
    @BindView(R.id.vT_aac_add)
    TextView vTAacAdd;

    Dialog successPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
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

                vTAacAccept.setTypeface(NIRMALA);
                vTAacAdd.setTypeface(NIRMALAB);
                /*vTAacBank.setTypeface(NIRMALA);
                vTAacBusiness.setTypeface(NIRMALAB);
                vTAacCards.setTypeface(NIRMALA);*/
                vTAacHeader.setTypeface(NIRMALAB);
                /*vTAacName.setTypeface(NIRMALA);
                vTAacOthers.setTypeface(NIRMALAB);
                vTAacPersonal.setTypeface(NIRMALAB);
                vTAacYourCard.setTypeface(NIRMALA);
                vEAacCardno.setTypeface(NIRMALA);
                vEAacCvv.setTypeface(NIRMALA);
                vEAacMonth.setTypeface(NIRMALA);*/
                vEAacName.setTypeface(NIRMALA);



            }
        };
        r.run();
    }

    private void setvalues() {
        vIAacBack.setOnClickListener(this);
        vTAacAdd.setOnClickListener(this);

        if (!(getIntent().getStringExtra("from")==null||getIntent().getStringExtra("from").length()==0))
        {
            vTAacAdd.setText(getResources().getString(R.string.pay_now));
           /* vTAacCards.setVisibility(View.VISIBLE);
            vTAacBusiness.setVisibility(View.INVISIBLE);
            vTAacOthers.setVisibility(View.INVISIBLE);
            vTAacPersonal.setBackgroundResource(R.drawable.circle_stroke_green);*/
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_aac_back:
                finish();
                break;
            case R.id.vT_aac_add:
                if (!(getIntent().getStringExtra("from")==null||getIntent().getStringExtra("from").length()==0))
                {
                    Initializepopup();
                    initializsuccessPopupFinal();

                }else {
                    Intent intent = new Intent(this, AddCardActivity.class);
                    intent.putExtra("from", "add");
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
                break;
        }
    }

    private void Initializepopup() {
        successPopup = new Dialog(AddCardActivity.this);
        successPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        successPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        successPopup.setContentView(R.layout.popup_cancellation);
        successPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        successPopup.setCancelable(true);
        successPopup.setCanceledOnTouchOutside(true);
    }
    private void initializsuccessPopupFinal() {
        successPopup.setContentView(R.layout.popup_cancellation);
        successPopup.setCancelable(true);
        successPopup.setCanceledOnTouchOutside(true);
        successPopup.show();

        final TextView vT_pc_choose = (TextView) successPopup.findViewById(R.id.vT_pc_choose);
        final RecyclerView vR_pc_list = (RecyclerView) successPopup.findViewById(R.id.vR_pc_list);
        final LinearLayout vL_pc_status = (LinearLayout) successPopup.findViewById(R.id.vL_pc_status);
        final TextView vT_pc_status = (TextView) successPopup.findViewById(R.id.vT_pc_status);
        final TextView vT_pc_notification = (TextView) successPopup.findViewById(R.id.vT_pc_notification);
        final TextView vT_pc_view_order = (TextView) successPopup.findViewById(R.id.vT_pc_view_order);
        vR_pc_list.setVisibility(View.GONE);
        vL_pc_status.setVisibility(View.VISIBLE);
        vT_pc_view_order.setVisibility(View.VISIBLE);

        vT_pc_choose.setText(getResources().getString(R.string.payment_success));
        vT_pc_status.setText(getResources().getString(R.string.order_awaiting));
        vT_pc_notification.setText(getResources().getString(R.string.get_notification));

        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_pc_choose.setTypeface(NIRMALAB);
                vT_pc_status.setTypeface(NIRMALA);
                vT_pc_notification.setTypeface(NIRMALA);
                vT_pc_view_order.setTypeface(NIRMALA);

            }
        };
        r.run();
        int width = getResources().getDisplayMetrics().widthPixels - 100;
        successPopup.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        successPopup.getWindow().setGravity(Gravity.CENTER);

        successPopup.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });


        successPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        successPopup.setCancelable(true);
        successPopup.setCanceledOnTouchOutside(true);
        successPopup.show();
    }
}
