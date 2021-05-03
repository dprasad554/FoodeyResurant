package com.geekhive.foodey.Food.eatout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.loginout.LoginOut;
import com.geekhive.foodey.Food.landing.LandingActivity;
import com.geekhive.foodey.Food.profile.EditProfile;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTPVerificationActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {


    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vT_av_mobile)
    TextView vTAvMobile;
    @BindView(R.id.vT_av_mobileval)
    TextView vTAvMobileval;
    @BindView(R.id.vT_av_otp)
    TextView vTAvOtp;
    @BindView(R.id.vE_av_otp)
    EditText vEAvOtp;
    @BindView(R.id.vT_av_otphas)
    TextView vTAvOtphas;
    @BindView(R.id.vT_av_resend)
    TextView vTAvResend;
    @BindView(R.id.vT_av_verify)
    TextView vTAvVerify;

    String mobileNo;

    ConnectionDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
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

                vTAvMobile.setTypeface(NIRMALA);
                vTAvMobileval.setTypeface(NIRMALAB);
                vTAvOtp.setTypeface(NIRMALA);
                vTAvOtphas.setTypeface(NIRMALA);
                vTAvResend.setTypeface(NIRMALA);
                vTAvVerify.setTypeface(NIRMALAB);
                vEAvOtp.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        mobileNo = getIntent().getExtras().getString("mobile");
        vTAvVerify.setOnClickListener(this);
        vTAvResend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vT_av_verify:
                if (!vEAvOtp.getText().toString().equals("") || !vEAvOtp.getText().toString().isEmpty()){
                    initializeFirebase(vEAvOtp.getText().toString());
                } else {
                    SnackBar.makeText(OTPVerificationActivity.this, "Please enter otp to continue", SnackBar.LENGTH_SHORT).show();
                }
                break;
            case R.id.vT_av_resend:
                CallResendService(mobileNo);
                break;
        }
    }

    private void CallVerifyService(String otp, String firebase_id) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!otp.isEmpty() || !otp.equals("")) {

                new WebServices(this).VerifyOtp(WebServices.Foodey_Services,
                        WebServices.ApiType.verifyOtp, mobileNo, otp,firebase_id);//firebase_id
            } else {

                SnackBar.makeText(OTPVerificationActivity.this, "Please enter otp to continue", SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallResendService(String mobile) {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).ResendOtp(WebServices.Foodey_Services,
                        WebServices.ApiType.resendOtp, mobile);

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.verifyOtp) {
            if (!isSucces) {
                SnackBar.makeText(OTPVerificationActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final LoginOut loginOut = (LoginOut) response;

                if (!isSucces || loginOut == null) {
                    SnackBar.makeText(OTPVerificationActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (!loginOut.getUser().getFirstname().equals("") || !loginOut.getUser().getFirstname().isEmpty()){
                        Intent intent = new Intent(OTPVerificationActivity.this, LandingActivity.class);
                        String mobileNo = loginOut.getUser().getMobile();
                        Prefs.setMobileNumber(OTPVerificationActivity.this, mobileNo);
                        Prefs.setUserId(OTPVerificationActivity.this, loginOut.getUser().getId());
                        Prefs.setReferalCode(OTPVerificationActivity.this, loginOut.getUser().getMyCode());
                        intent.putExtra("userId", loginOut.getUser().getId());
                        intent.putExtra("mobile", mobileNo);
                        //Prefs.setUserEmail(this, loginOut.getUser().get());
                        String name = loginOut.getUser().getFirstname() + loginOut.getUser().getLastname();
                        Prefs.setUserName(this, name);
                        if (!loginOut.getUser().getLatitude().equals("")){
                            Prefs.setUserLat(this, loginOut.getUser().getLatitude());
                        }

                        if (!loginOut.getUser().getLongitude().equals("")){
                            Prefs.setUserLang(this, loginOut.getUser().getLongitude());
                        }
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        OTPVerificationActivity.this.finish();
                    } else {
                        Intent intent = new Intent(OTPVerificationActivity.this, EditProfile.class);
                        intent.putExtra("userId", loginOut.getUser().getId());
                        intent.putExtra("mobile", loginOut.getUser().getMobile());
                        startActivity(intent);
                        OTPVerificationActivity.this.finish();
                    }

                }
            }
        } if (apiType == WebServices.ApiType.resendOtp) {
            if (!isSucces) {
                SnackBar.makeText(OTPVerificationActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final LoginOut loginOut = (LoginOut) response;

                if (!isSucces || loginOut == null) {
                    SnackBar.makeText(OTPVerificationActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(OTPVerificationActivity.this, "New OTP has been sent to your registered number", SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initializeFirebase(final String otp) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Home Activity", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("Home Activiy", msg);
                        CallVerifyService(otp, token);

                        //Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        // [END retrieve_current_token]
    }
}
