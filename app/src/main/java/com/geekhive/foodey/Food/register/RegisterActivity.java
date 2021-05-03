package com.geekhive.foodey.Food.register;

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
import com.geekhive.foodey.Food.login.LoginActivity;
import com.geekhive.foodey.Food.selectlocation.SelectLocationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    @BindView(R.id.vI_ar_back)
    ImageView vIArBack;
    @BindView(R.id.vT_ar_app)
    TextView vTArApp;
    @BindView(R.id.vT_ar_instant)
    TextView vTArInstant;
    @BindView(R.id.vT_ar_new)
    TextView vTArNew;
    @BindView(R.id.vE_ar_name)
    EditText vEAfName;
    @BindView(R.id.vE_ar_email)
    EditText vEAfEmail;
    @BindView(R.id.vE_ar_phone)
    EditText vEAfPhone;
    @BindView(R.id.vE_ar_pass)
    EditText vEAfPass;
    @BindView(R.id.vT_ar_signup)
    TextView vTAfSignup;
    @BindView(R.id.vT_ar_login)
    TextView vTAfLogin;
    @BindView(R.id.vL_ar_login)
    LinearLayout vLAfLogin;
    @BindView(R.id.vT_ar_fb)
    TextView vTAfFb;
    @BindView(R.id.vL_ar_fb)
    LinearLayout vLAfFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

                vTArApp.setTypeface(NIRMALAB);
                vTAfFb.setTypeface(NIRMALAB);
                vTAfLogin.setTypeface(NIRMALAB);
                vTAfSignup.setTypeface(NIRMALAB);
                vTArInstant.setTypeface(NIRMALA);
                vTArNew.setTypeface(NIRMALAB);
                vEAfEmail.setTypeface(NIRMALA);
                vEAfName.setTypeface(NIRMALA);
                vEAfPass.setTypeface(NIRMALA);
                vEAfPhone.setTypeface(NIRMALA);



            }
        };
        r.run();
    }
    private void setvalues() {
        vIArBack.setOnClickListener(this);
        vLAfFb.setOnClickListener(this);
        vTAfLogin.setOnClickListener(this);
        vTAfSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.vT_ar_signup:
                Intent intent=new Intent(this, SelectLocationActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
            case R.id.vI_ar_back:
                finish();
                break;
            case R.id.vT_ar_login:
                Intent intentl=new Intent(this, LoginActivity.class);
                intentl.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentl);
                overridePendingTransition(0,0);
                finish();
                break;

        }
    }
}
