package com.geekhive.foodey.Food.placeorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceOrderActivity extends AppCompatActivity implements View.OnClickListener{

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_apo_back)
    ImageView vIApoBack;
    @BindView(R.id.vT_apo_price)
    TextView vTApoPrice;
    @BindView(R.id.vT_apo_save)
    TextView vTApoSave;
    @BindView(R.id.vT_apo_cod)
    TextView vTApoCod;
    @BindView(R.id.vL_apo_cod)
    LinearLayout vLApoCod;
    @BindView(R.id.vT_apo_add)
    TextView vTApoAdd;
    @BindView(R.id.vL_apo_add)
    LinearLayout vLApoAdd;
    @BindView(R.id.vT_apo_place)
    TextView vTApoPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
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

                vTApoAdd.setTypeface(NIRMALA);
                vTApoCod.setTypeface(NIRMALA);
                vTApoPlace.setTypeface(NIRMALA);
                vTApoPrice.setTypeface(NIRMALAB);
                vTApoSave.setTypeface(NIRMALA);



            }
        };
        r.run();
    }

    private void setvalues() {
        vIApoBack.setOnClickListener(this);
        vTApoPlace.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_apo_back:
                finish();
                break;
            case R.id.vT_apo_place:
                Intent intent=new Intent(this,SelectPaymentActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }
}
