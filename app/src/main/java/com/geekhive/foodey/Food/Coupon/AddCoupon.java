package com.geekhive.foodey.Food.Coupon;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Food.orderfood.CheckOutActivityNew;
import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCoupon extends AppCompatActivity implements View.OnClickListener {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vR_coupon_list)
    RecyclerView vRCouponlist;

    @BindView(R.id.vI_cpnl_back)
    ImageView vI_cpnl_back;

//    @BindView(R.id.vL_coupon_txt)
//    TextView vL_coupon_txt;
    String coupon= "30% off";
    String instruction_desc = "";



    CouponAdapter couponAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        initializeFonts();
      //  setTypeFace();
        setvalues();

        }
    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getAssets(), "NIRMALAS.TTF");
    }
//    private void setTypeFace() {
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };
//        r.run();
//    }
    private void setvalues() {
        couponAdapter = new CouponAdapter();
        vRCouponlist.setLayoutManager(new LinearLayoutManager(this));
        vRCouponlist.setAdapter(couponAdapter);
        vI_cpnl_back.setOnClickListener(this);
        instruction_desc = getIntent().getStringExtra("instruction_txt");

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


        public CouponAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CouponAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coupon, parent, false));
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vT_apply.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   Intent intent=new Intent(AddCoupon.this, CheckOutActivityNew.class);
                   intent.putExtra("coupon_value",coupon);
                   intent.putExtra("instruction_txt",instruction_desc);


//                   instruction_desc = getIntent().getStringExtra("instruction_txt");
//


                   startActivity(intent);
//                   vL_coupon_txt.setVisibility(View.VISIBLE);

           }
           });
        }



        public int getItemCount() {
            return 4;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView vT_apply,vT_ot,vT_vt;

            public MyViewHolder(View view) {
                super(view);
               this. vT_apply=view.findViewById(R.id.vT_apply);
                this.vT_ot = (TextView) view.findViewById(R.id.vT_ot);
                this.vT_vt = (TextView) view.findViewById(R.id.vT_vt);

                new Runnable() {
                    public void run() {
                        vT_ot.setTypeface(NIRMALAB);
                        vT_vt.setTypeface(NIRMALA);


                    }
                }.run();

            }
        }
    }


        }

