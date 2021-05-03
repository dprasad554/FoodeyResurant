package com.geekhive.foodey.Food.profile;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.loginout.LoginOut;
import com.geekhive.foodey.Food.landing.LandingActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfile extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.upload_profile_image)
    TextView upload_profile_image;
    @BindView(R.id.first_name)
    EditText first_name;
    @BindView(R.id.last_name)
    EditText last_name;
    @BindView(R.id.mobile_number)
    EditText mobile_number;
    @BindView(R.id.alternate_mobile)
    EditText alternate_mobile;
    @BindView(R.id.refer_code)
    EditText refer_code;
    @BindView(R.id.vI_ep_back)
    ImageView vI_ep_back;
    @BindView(R.id.vT_af_header)
    TextView vT_af_header;
    @BindView(R.id.update_profile)
    Button update_profile;

    ConnectionDetector mDetector;

    String userId;
    String mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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

                upload_profile_image.setTypeface(NIRMALAB);
                first_name.setTypeface(NIRMALAB);
                last_name.setTypeface(NIRMALAB);
                mobile_number.setTypeface(NIRMALA);
                refer_code.setTypeface(NIRMALA);
                alternate_mobile.setTypeface(NIRMALAB);
                vT_af_header.setTypeface(NIRMALAB);
                update_profile.setTypeface(NIRMALAB);

            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        userId = getIntent().getExtras().getString("userId");
        mobile = getIntent().getExtras().getString("mobile");

        mobile_number.setText(mobile);
        vI_ep_back.setOnClickListener(this);
        update_profile.setOnClickListener(this);

        /*Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                email_id.setText(possibleEmail);
            }
        }*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vI_ep_back:
                EditProfile.this.finish();
                break;
            case R.id.update_profile:
                CallService();
                break;
        }
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!first_name.getText().toString().isEmpty() || !first_name.getText().toString().equals("")) {
                if (!last_name.getText().toString().isEmpty() || !last_name.getText().toString().equals("")) {
                    if (!alternate_mobile.getText().toString().isEmpty() || !alternate_mobile.getText().toString().equals("")) {
                        if (isValidMobile(alternate_mobile.getText().toString())) {
                            if (!(alternate_mobile.getText().toString().length() < 10)){
                                if (refer_code.getText().toString().equals("")){
                                    new WebServices(this).EditProfile(WebServices.Foodey_Services,
                                            WebServices.ApiType.editProfile, mobile, userId, first_name.getText().toString(), last_name.getText().toString(), alternate_mobile.getText().toString(),"");
                                } else {
                                    new WebServices(this).EditProfile(WebServices.Foodey_Services,
                                            WebServices.ApiType.editProfile, mobile, userId, first_name.getText().toString(), last_name.getText().toString(), alternate_mobile.getText().toString(), refer_code.getText().toString());
                                }
                            } else {
                                alternate_mobile.setError("Invalid number");
                            }

                        } else {
                            alternate_mobile.setError("Invalid number");
                        }
                    } else {

                        SnackBar.makeText(EditProfile.this, "Please enter alternate mobile number", SnackBar.LENGTH_SHORT).show();
                    }
                } else {

                    SnackBar.makeText(EditProfile.this, "Please enter your last name", SnackBar.LENGTH_SHORT).show();
                }
            } else {

                SnackBar.makeText(EditProfile.this, "Please enter your first name", SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.editProfile) {
            if (!isSucces) {
                SnackBar.makeText(EditProfile.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final LoginOut loginOut = (LoginOut) response;

                if (!isSucces || loginOut == null) {
                    SnackBar.makeText(EditProfile.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(EditProfile.this, LandingActivity.class);
                    String mobileNo = loginOut.getUser().getMobile();
                    Prefs.setMobileNumber(EditProfile.this, mobileNo);
                    Prefs.setUserId(EditProfile.this, loginOut.getUser().getId());
                    Prefs.setReferalCode(EditProfile.this, loginOut.getUser().getMyCode());
                    intent.putExtra("userId", loginOut.getUser().getId());
                    intent.putExtra("mobile", mobileNo);
                    //Prefs.setUserEmail(this, loginOut.getUser().getEmail());
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
                    EditProfile.this.finish();
                }
            }
        }
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}
