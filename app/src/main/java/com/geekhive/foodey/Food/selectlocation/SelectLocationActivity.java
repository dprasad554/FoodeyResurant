package com.geekhive.foodey.Food.selectlocation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectLocationActivity extends AppCompatActivity implements View.OnClickListener{
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    @BindView(R.id.vT_asl_tellus)
    TextView vTAslTellus;
    @BindView(R.id.vT_asl_this)
    TextView vTAslThis;
    @BindView(R.id.vT_asl_auto)
    TextView vTAslAuto;
    @BindView(R.id.vT_asl_no)
    TextView vTAslNo;
    @BindView(R.id.vI_asl_back)
    ImageView vIAslBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        ButterKnife.bind(this);
        initializeFonts();
        setTypeFace();
        setValues();
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

                vTAslAuto.setTypeface(NIRMALAB);
                vTAslNo.setTypeface(NIRMALAB);
                vTAslTellus.setTypeface(NIRMALAB);
                vTAslThis.setTypeface(NIRMALA);




            }
        };
        r.run();
    }
    private void setValues() {
        vIAslBack.setOnClickListener(this);
        vTAslNo.setOnClickListener(this);
        vTAslAuto.setOnClickListener(this);
        vTAslNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.vI_asl_back:
                finish();
                break;
            case R.id.vT_asl_auto:
                Intent intentf=new Intent(this, HomeActivity.class);
                startActivity(intentf);
                overridePendingTransition(0,0);
                break;

            case R.id.vT_asl_no:
                Intent intentn=new Intent(this, HomeActivity.class);
                startActivity(intentn);
                overridePendingTransition(0,0);
                break;
        }
    }
}
