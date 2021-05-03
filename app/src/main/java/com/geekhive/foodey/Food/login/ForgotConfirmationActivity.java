package com.geekhive.foodey.Food.login;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotConfirmationActivity extends AppCompatActivity implements View.OnClickListener{
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_afc_back)
    ImageView vIAfcBack;
    @BindView(R.id.vT_afc_forgot)
    TextView vTAfcForgot;
    @BindView(R.id.vT_afc_mobile)
    TextView vTAfcMobile;
    @BindView(R.id.vT_afc_status)
    TextView vTAfcStatus;
    @BindView(R.id.vT_afc_reset)
    TextView vTAfcReset;
    @BindView(R.id.vE_afc_new_pass)
    EditText vEAfcNewPass;
    @BindView(R.id.vE_afc_confirm_pass)
    EditText vEAfcConfirmPass;
    @BindView(R.id.vT_afc_update)
    TextView vTAfcUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_confirmation);
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

                vTAfcForgot.setTypeface(NIRMALAB);
                vTAfcMobile.setTypeface(NIRMALA);
                vTAfcReset.setTypeface(NIRMALAB);
                vTAfcStatus.setTypeface(NIRMALAB);
                vTAfcUpdate.setTypeface(NIRMALAB);
                vEAfcConfirmPass.setTypeface(NIRMALA);
                vEAfcNewPass.setTypeface(NIRMALA);
            }
        };
        r.run();
    }
    private void setvalues() {
        vTAfcUpdate.setOnClickListener(this);
        vIAfcBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.vI_afc_back:
                finish();
                break;
            case R.id.vT_afc_update:
                finish();
                break;
        }
    }
}

