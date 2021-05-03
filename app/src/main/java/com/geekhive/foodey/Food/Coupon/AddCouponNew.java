package com.geekhive.foodey.Food.Coupon;

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

import com.geekhive.foodey.Food.beans.promocode.PromoCode;
import com.geekhive.foodey.Food.beans.promocode.PromoCode_;
import com.geekhive.foodey.Food.beans.promoselect.PromoSelect;
import com.geekhive.foodey.Food.orderfood.CheckOutActivityFinal;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCouponNew extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    @BindView(R.id.vR_coupon_list)
    RecyclerView vRCouponlist;
    @BindView(R.id.vI_cpnl_back)
    ImageView vI_cpnl_back;

    String coupon = "";
    String instruction_desc = "";
    String address = "";
    String cartId = "";
    CouponAdapter couponAdapter;
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
                new WebServices(this).PromoCodes(WebServices.Foodey_Services,
                        WebServices.ApiType.promocode);
            } else {
                SnackBar.makeText(AddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallApplyService(String promo_code) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).PromoApply(WebServices.Foodey_Services,
                        WebServices.ApiType.promoapply, Prefs.getUserId(this), cartId, promo_code);
            } else {
                SnackBar.makeText(AddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.promocode) {
            if (!isSucces) {
                SnackBar.makeText(AddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                PromoCode promoCode = (PromoCode) response;
                if (promoCode.getPromoCode() != null) {
                    if (promoCode.getPromoCode().size() != 0) {
                        couponAdapter = new CouponAdapter(promoCode.getPromoCode());
                        vRCouponlist.setLayoutManager(new LinearLayoutManager(this));
                        vRCouponlist.setAdapter(couponAdapter);
                    }
                } else {

                }

            }
        } if (apiType == WebServices.ApiType.promoapply) {
            if (!isSucces) {
                SnackBar.makeText(AddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                PromoSelect promoSelect = (PromoSelect) response;
                if (promoSelect.getCartList() != null) {
                    if (promoSelect.getCartList().getCart() != null) {
                        Intent intent = new Intent(getApplicationContext(), CheckOutActivityFinal.class);
                        intent.putExtra("coupon_value", coupon);
                        intent.putExtra("instruction_txt", instruction_desc);
                        intent.putExtra("address", address);
                        startActivity(intent);
                    }
                } else {

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

        List<PromoCode_> promoCode;

        public CouponAdapter(List<PromoCode_> promoCode) {
            this.promoCode = promoCode;
        }

        public CouponAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CouponAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coupon, parent, false));
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

