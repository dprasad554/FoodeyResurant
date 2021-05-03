package com.geekhive.foodey.Grocery.activities;

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


import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Grocery.beans.getgrocerypromo.GroceryPromoList;
import com.geekhive.foodey.Grocery.beans.getgrocerypromo.PromoCode;
import com.geekhive.foodey.Grocery.beans.grocerypromoapply.GroceryPromoApply;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.ConnectionDetector;
import com.geekhive.foodey.Grocery.utils.OnResponseListner;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroceryAddCouponNew extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    @BindView(R.id.vR_coupon_list)
    RecyclerView vRCouponlist;
    @BindView(R.id.vI_cpnl_back)
    ImageView vI_cpnl_back;

    String coupon = "";
    String instruction_desc = "";
    String address = "";
    String cartId = "";
    GroceryCouponAdapter groceryCouponAdapter;
    ConnectionDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_activity_coupon_new);
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
                new WebServices(this).GroceryPromoCodes(WebServices.Foodey_Grocery_Services,
                        WebServices.ApiType.grocerypromocode);
            } else {
                SnackBar.makeText(GroceryAddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    private void CallApplyService(String promo_code) {
        if (this.mDetector.isConnectingToInternet()) {

            if (!Prefs.getUserId(this).isEmpty() || !Prefs.getUserId(this).equals("")) {
                new WebServices(this).GroceryPromoApply(WebServices.Foodey_Grocery_Services,
                        WebServices.ApiType.grocerypromoapply, Prefs.getUserId(this), cartId, promo_code);
            } else {
                SnackBar.makeText(GroceryAddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.grocerypromocode) {
            if (!isSucces) {
                SnackBar.makeText(GroceryAddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GroceryPromoList groceryPromoList = (GroceryPromoList) response;
                if (groceryPromoList.getPromoCode() != null) {
                    if (groceryPromoList.getPromoCode().size() != 0) {
                        groceryCouponAdapter = new GroceryCouponAdapter(groceryPromoList.getPromoCode());
                        vRCouponlist.setLayoutManager(new LinearLayoutManager(this));
                        vRCouponlist.setAdapter(groceryCouponAdapter);
                    }
                } else {

                }

            }
        } if (apiType == WebServices.ApiType.grocerypromoapply) {
            if (!isSucces) {
                SnackBar.makeText(GroceryAddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GroceryPromoApply groceryPromoApply = (GroceryPromoApply) response;
                if (groceryPromoApply.getGCartList() != null) {
                    if (groceryPromoApply.getGCartList().getGCart() != null) {
                        Intent intent = new Intent(GroceryAddCouponNew.this, GroceryCheckOutActivityFinal.class);
                        intent.putExtra("coupon_value", coupon);
                        intent.putExtra("instruction_txt", instruction_desc);
                        intent.putExtra("address", address);
                        startActivity(intent);
                    }
                } else {
                    SnackBar.makeText(GroceryAddCouponNew.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
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


    public class GroceryCouponAdapter extends RecyclerView.Adapter<GroceryCouponAdapter.MyViewHolder> {

        List<PromoCode> promoCode;

        public GroceryCouponAdapter(List<PromoCode> promoCode) {
            this.promoCode = promoCode;
        }

        public GroceryCouponAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GroceryCouponAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_adapter_coupon, parent, false));
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

