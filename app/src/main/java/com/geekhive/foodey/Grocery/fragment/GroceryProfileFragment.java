package com.geekhive.foodey.Grocery.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.login.LoginActivity;
import com.geekhive.foodey.Food.more.WebViewActivity;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.activities.GroceryCheckOutActivityNew;
import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroceryProfileFragment extends Fragment implements View.OnClickListener {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    Unbinder unbinder;
    View view;
    Bundle savedInstanceStateq;
    @BindView(R.id.vI_fp_image)
    ImageView vIFpImage;
    @BindView(R.id.vT_fp_name)
    TextView vTFpName;
    @BindView(R.id.vT_fp_number)
    TextView vTFpNumber;
    @BindView(R.id.vT_fp_mail)
    TextView vTFpMail;
    @BindView(R.id.vT_fp_edit)
    TextView vTFpEdit;
    @BindView(R.id.vT_fp_manage)
    TextView vTFpManage;
    @BindView(R.id.vL_fp_manage)
    LinearLayout vLFpManage;
    @BindView(R.id.vT_fp_about)
    TextView vTFpAbout;
    @BindView(R.id.vL_fp_about)
    LinearLayout vLFpAbout;
    @BindView(R.id.vT_fp_privacy)
    TextView vTFpPrivacy;
    @BindView(R.id.vL_fp_privacy)
    LinearLayout vLFpPrivacy;
    @BindView(R.id.vT_fp_refund)
    TextView vTFpRefund;
    @BindView(R.id.vL_fp_refund)
    LinearLayout vLFpRefund;
    @BindView(R.id.vT_fp_terms)
    TextView vTFpTerms;
    @BindView(R.id.vL_fp_terms)
    LinearLayout vLFpTerms;
    @BindView(R.id.vT_fp_disclaimer)
    TextView vTFpDisclaimer;
    @BindView(R.id.vL_fp_disclaimer)
    LinearLayout vLFpDisclaimer;
    @BindView(R.id.vT_fp_signout)
    TextView vTFpSignout;
    @BindView(R.id.vL_fp_signout)
    LinearLayout vLFpSignout;
    @BindView(R.id.vL_fp_cart)
    LinearLayout vLFpCart;
    @BindView(R.id.vT_fp_cart)
    TextView vTFpCart;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.savedInstanceStateq = savedInstanceState;
        unbinder = ButterKnife.bind(this, view);
        initializeFonts();
        setTypeFace();
        setvalues();
        return view;
    }

    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAS.TTF");
    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                vTFpEdit.setTypeface(NIRMALAB);
                vTFpAbout.setTypeface(NIRMALAB);
                vTFpPrivacy.setTypeface(NIRMALAB);
                vTFpMail.setTypeface(NIRMALA);
                vTFpManage.setTypeface(NIRMALAB);
                vTFpName.setTypeface(NIRMALAB);
                vTFpNumber.setTypeface(NIRMALA);
                vTFpRefund.setTypeface(NIRMALAB);
                vTFpTerms.setTypeface(NIRMALAB);
                vTFpDisclaimer.setTypeface(NIRMALAB);
                vTFpSignout.setTypeface(NIRMALAB);
                vTFpCart.setTypeface(NIRMALAB);


            }
        };
        r.run();
    }

    private void setvalues() {
        vLFpAbout.setOnClickListener(this);
        vLFpPrivacy.setOnClickListener(this);
        vLFpManage.setOnClickListener(this);
        vLFpRefund.setOnClickListener(this);
        vLFpTerms.setOnClickListener(this);
        vLFpDisclaimer.setOnClickListener(this);
        vLFpSignout.setOnClickListener(this);
        vLFpCart.setOnClickListener(this);

        vTFpName.setText(Prefs.getUserName(getActivity()));
        vTFpNumber.setText(Prefs.getMobileNumber(getActivity()));
        vTFpMail.setText(Prefs.getUserEmail(getActivity()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vL_fp_about:
                /*Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("weburl", "http://foodeyservices.com/homes/privacy_policy");
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);*/
                break;
            case R.id.vL_fp_privacy:
                Intent intentp = new Intent(getActivity(), WebViewActivity.class);
                intentp.putExtra("weburl", "http://foodeyservices.com/homes/privacy_policy");
                startActivity(intentp);
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.vL_fp_refund:
                Intent intentr = new Intent(getActivity(), WebViewActivity.class);
                intentr.putExtra("weburl", "http://foodeyservices.com/homes/refund_policy");
                startActivity(intentr);
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.vL_fp_terms:
                Intent intentt = new Intent(getActivity(), WebViewActivity.class);
                intentt.putExtra("weburl", "http://foodeyservices.com/homes/terms_conditions");
                startActivity(intentt);
                getActivity().overridePendingTransition(0, 0);
                break;

            case R.id.vL_fp_disclaimer:
                Intent intentd = new Intent(getActivity(), WebViewActivity.class);
                intentd.putExtra("weburl", "http://foodeyservices.com/homes/disclaimber");
                startActivity(intentd);
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.vL_fp_signout:
                Prefs.setUserId(getActivity(),null);
                Prefs.setMobileNumber(getActivity(),null);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.vL_fp_cart:
                Intent intentc = new Intent(getActivity(), GroceryCheckOutActivityNew.class);
                startActivity(intentc);
                getActivity().overridePendingTransition(0, 0);
        }
    }


}
