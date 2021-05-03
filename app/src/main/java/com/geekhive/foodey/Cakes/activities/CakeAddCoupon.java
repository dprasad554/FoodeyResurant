package com.geekhive.foodey.Cakes.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.geekhive.foodey.Cakes.beans.couponlist.CouponList;
import com.geekhive.foodey.Cakes.beans.couponlist.PromoCode;
import com.geekhive.foodey.Cakes.beans.promoselect.CakePromoSelect;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;

import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeAddCoupon extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    @BindView(R.id.vR_coupon_list)
    RecyclerView vRCouponlist;
    @BindView(R.id.vI_cpnl_back)
    ImageView vI_cpnl_back;

    String coupon = "";
    String instruction_desc = "";
    String address = "";
    String cartId = "";
    CouponAdapter groceryCouponAdapter;
    ConnectionDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_new);
        ButterKnife.bind(this);

        setvalues();

    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        vI_cpnl_back.setOnClickListener(this);
        instruction_desc = getIntent().getStringExtra("instruction_txt");
        address = getIntent().getStringExtra("address");
        cartId = getIntent().getStringExtra("cartId");
        CallService();

    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).GetCouponList(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.couponlist);
            } else {
                SnackBar.makeText(CakeAddCoupon.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallApplyService(String promo_code) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).PromoApply(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.promoapply, Prefs.getUserId(this), cartId, promo_code);
            } else {
                SnackBar.makeText(CakeAddCoupon.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.couponlist) {
            if (!isSucces) {
                SnackBar.makeText(CakeAddCoupon.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CouponList promoCode = (CouponList) response;
                if (promoCode.getPromoCode() != null) {
                    if (promoCode.getPromoCode().size() != 0) {
                        groceryCouponAdapter = new CouponAdapter(promoCode.getPromoCode());
                        vRCouponlist.setLayoutManager(new LinearLayoutManager(this));
                        vRCouponlist.setAdapter(groceryCouponAdapter);
                    }
                } else {

                }

            }
        } if (apiType == WebServices.ApiType.promoapply) {
            if (!isSucces) {
                SnackBar.makeText(CakeAddCoupon.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakePromoSelect promoSelect = (CakePromoSelect) response;
                if (promoSelect.getCakeCartList() != null) {
                    if (promoSelect.getCakeCartList().getCakeCart() != null) {
                        Intent intent = new Intent(CakeAddCoupon.this, CakeCheckOutActivityFinal.class);
                        intent.putExtra("coupon_value", coupon);
                        intent.putExtra("instruction_txt", instruction_desc);
                        intent.putExtra("address", address);
                        intent.putExtra("discount", promoSelect.getCakeCartList().getCakeCart().getDiscount());

                        startActivity(intent);
                    }
                } else {
                    SnackBar.makeText(CakeAddCoupon.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }

            }
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.vI_cpnl_back:
                finish();
                break;


        }
    }


    public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.MyViewHolder> {

        List<PromoCode> promoCode;

        public CouponAdapter(List<PromoCode> promoCode) {
            this.promoCode = promoCode;
        }

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coupon, parent, false));
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_ot.setText(promoCode.get(position).getPromoCode().getName());
            holder.vT_vt.setText(promoCode.get(position).getPromoCode().getDetail());
            holder.fcode.setText(promoCode.get(position).getPromoCode().getPromoCode());
            //coupon = promoCode.get(position).getPromoCode().getPromoCode();
            holder.vT_apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coupon = promoCode.get(position).getPromoCode().getPromoCode();
                    CallApplyService(coupon);
//                   vL_coupon_txt.setVisibility(View.VISIBLE);

                }
            });
        }


        public int getItemCount() {
            return promoCode.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView vT_ot, vT_vt, fcode, vT_apply;

            public MyViewHolder(View view) {
                super(view);
                this.vT_apply = view.findViewById(R.id.vT_apply);
                this.vT_ot = view.findViewById(R.id.vT_ot);
                this.vT_vt = view.findViewById(R.id.vT_vt);
                this.fcode = view.findViewById(R.id.fcode);

            }
        }
    }


}

