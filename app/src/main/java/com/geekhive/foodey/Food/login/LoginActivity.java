package com.geekhive.foodey.Food.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.loginout.LoginOut;
import com.geekhive.foodey.Food.eatout.OTPVerificationActivity;
import com.geekhive.foodey.Food.education.EducationActivity;
import com.geekhive.foodey.Food.register.RegisterActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    @BindView(R.id.vI_al_back)
    ImageView vIAlBack;
    @BindView(R.id.vT_al_app)
    TextView vTAlApp;
    @BindView(R.id.vT_al_instant)
    TextView vTAlInstant;
    @BindView(R.id.vT_al_login)
    TextView vTAlLogin;
    @BindView(R.id.vE_al_Mobile)
    EditText vEAlMobile;
    @BindView(R.id.vI_al_pass)
    ImageView vIAlPass;
    @BindView(R.id.vE_al_pass)
    EditText vEAlPass;
    @BindView(R.id.vT_al_forgot)
    TextView vTAlForgot;
    @BindView(R.id.vT_al_logina)
    TextView vTAlLogina;
    @BindView(R.id.vT_al_new_user)
    TextView vTAlNewUser;
    @BindView(R.id.vT_al_signup)
    TextView vTAlSignup;
    @BindView(R.id.vL_al_signup)
    LinearLayout vLAlSignup;
    @BindView(R.id.vT_al_fb)
    TextView vTAlFb;
    @BindView(R.id.vL_al_fb)
    LinearLayout vLAlFb;

    ConnectionDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Prefs.getSplashScreenPref(this).equals("")) {
            startActivity(new Intent(this, EducationActivity.class));
            LoginActivity.this.finish();
        }
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

                vTAlApp.setTypeface(NIRMALAB);
                vTAlFb.setTypeface(NIRMALAB);
                vTAlForgot.setTypeface(NIRMALAB);
                vTAlInstant.setTypeface(NIRMALA);
                vTAlLogin.setTypeface(NIRMALAB);
                vTAlLogina.setTypeface(NIRMALAB);
                vTAlNewUser.setTypeface(NIRMALA);
                vTAlSignup.setTypeface(NIRMALAB);
                vEAlMobile.setTypeface(NIRMALA);
                vEAlPass.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        vIAlBack.setOnClickListener(this);
        vLAlFb.setOnClickListener(this);
        vTAlForgot.setOnClickListener(this);
        vTAlLogina.setOnClickListener(this);
        vLAlSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vL_al_signup:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.vI_al_back:
                finish();
                break;
            case R.id.vT_al_forgot:
                Intent intentf = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intentf);
                overridePendingTransition(0, 0);
                break;
            case R.id.vT_al_logina:
                CallService();
                break;
        }
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            if (!vEAlMobile.getText().toString().isEmpty() || !vEAlMobile.getText().toString().equals("")) {
                if (isValidMobile(vEAlMobile.getText().toString())) {
                    if (!(vEAlMobile.getText().toString().length() < 10)){
                        new WebServices(this).Login(WebServices.Foodey_Services,
                                WebServices.ApiType.login, vEAlMobile.getText().toString());
                    } else {
                        vEAlMobile.setError("Invalid Number");
                    }

                } else {
                    vEAlMobile.setError("Invalid Number");
                }
            }else {
                SnackBar.makeText(LoginActivity.this, "Please enter your mobile number to continue", SnackBar.LENGTH_SHORT).show();
            }
        } else {
            SnackBar.makeText(LoginActivity.this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;


    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.login) {
            if (!isSucces) {
                SnackBar.makeText(LoginActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final LoginOut loginOut = (LoginOut) response;

                if (!isSucces || loginOut == null) {
                    SnackBar.makeText(LoginActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, OTPVerificationActivity.class);
                    String mobileNo = loginOut.getUser().getMobile();
                    intent.putExtra("mobile", mobileNo);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    LoginActivity.this.finish();
                }
            }
        }
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


}
