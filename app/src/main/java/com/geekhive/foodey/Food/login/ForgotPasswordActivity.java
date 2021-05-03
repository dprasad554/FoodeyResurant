package com.geekhive.foodey.Food.login;

import android.content.Intent;
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

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    @BindView(R.id.vI_af_back)
    ImageView vIAfBack;
    @BindView(R.id.vT_af_forgot)
    TextView vTAfForgot;
    @BindView(R.id.vE_af_mobile)
    EditText vEAfMobile;
    @BindView(R.id.vT_af_otp)
    TextView vTAfOtp;
    @BindView(R.id.vE_af_otp)
    EditText vEAfOtp;
    @BindView(R.id.vT_af_otphas)
    TextView vTAfOtphas;
    @BindView(R.id.vT_af_resend)
    TextView vTAfResend;
    @BindView(R.id.vT_af_verify)
    TextView vTAfVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
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

                vTAfOtp.setTypeface(NIRMALA);
                vTAfForgot.setTypeface(NIRMALAB);
                vTAfOtphas.setTypeface(NIRMALA);
                vTAfResend.setTypeface(NIRMALAB);
                vTAfVerify.setTypeface(NIRMALAB);
                vEAfMobile.setTypeface(NIRMALA);
                vEAfOtp.setTypeface(NIRMALA);
            }
        };
        r.run();
    }
    private void setvalues() {
        vTAfVerify.setOnClickListener(this);
        vIAfBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.vI_af_back:
                finish();
                break;
            case R.id.vT_af_verify:
                Intent intentf=new Intent(this, ForgotConfirmationActivity.class);
                startActivity(intentf);
                overridePendingTransition(0,0);
                finish();
                break;
        }
    }
}
