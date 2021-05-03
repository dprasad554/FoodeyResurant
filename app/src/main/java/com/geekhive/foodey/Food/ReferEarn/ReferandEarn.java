package com.geekhive.foodey.Food.ReferEarn;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferandEarn extends AppCompatActivity implements View.OnClickListener {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    @BindView(R.id.vI_afr_back)
    ImageView vI_afr_back;
    @BindView(R.id.vL_rfr_btn)
    Button vL_rfr_btn;
    @BindView(R.id.vL_refer_xt)
    TextView vL_refer_xt;
    @BindView(R.id.vT_rct)
    TextView vT_rct;
    @BindView(R.id.referal_code)
    TextView referal_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);
        ButterKnife.bind(this);

        initializeFonts();
        setTypeFace();
        setvalues();

    }

    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(this.getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(this.getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(this.getAssets(), "NIRMALAS.TTF");
    }

    private void setvalues() {
        referal_code.setText(Prefs.getReferalCode(this));
        vI_afr_back.setOnClickListener(this);
        vL_rfr_btn.setOnClickListener(this);


    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                vL_refer_xt.setTypeface(NIRMALAB);
                vT_rct.setTypeface(NIRMALAB);
                referal_code.setTypeface(NIRMALAB);

            }
        };
        r.run();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_afr_back:
                finish();
                break;
            case R.id.vL_rfr_btn:
                String userCode = "Use my Referral Code: " + Prefs.getReferalCode(this) + " to sign into Foodey App and get 50 Wallet points \n " +
                        "https://play.google.com/store/apps/details?id=com.geekhive.foodey";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Foodey App");
                intent.putExtra(Intent.EXTRA_TEXT, userCode);
                startActivity(Intent.createChooser(intent, "Choose One"));
                break;
        }
    }

}
