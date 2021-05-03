package com.geekhive.foodey.Food.placeorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPaymentActivity extends AppCompatActivity implements View.OnClickListener{

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_asp_back)
    ImageView vIAspBack;
    @BindView(R.id.vT_asp_price)
    TextView vTAspPrice;
    @BindView(R.id.vT_asp_wallet)
    TextView vTAspWallet;
    @BindView(R.id.vR_asp_wallet)
    RecyclerView vRAspWallet;
    @BindView(R.id.vT_asp_online)
    TextView vTAspOnline;
    @BindView(R.id.vT_asp_onlinesave)
    TextView vTAspOnlinesave;
    @BindView(R.id.vR_asp_online)
    RecyclerView vRAspOnline;
    @BindView(R.id.vT_asp_cash)
    TextView vTAspCash;
    @BindView(R.id.vR_asp_cash)
    RecyclerView vRAspCash;

    PaymentAdapter wpaymentAdapter;
    PaymentAdapter opaymentAdapter;
    PaymentAdapter cpaymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment);
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

                vTAspCash.setTypeface(NIRMALAB);
                vTAspOnline.setTypeface(NIRMALAB);
                vTAspOnlinesave.setTypeface(NIRMALA);
                vTAspPrice.setTypeface(NIRMALAB);
                vTAspWallet.setTypeface(NIRMALAB);



            }
        };
        r.run();
    }

    private void setvalues() {
        vIAspBack.setOnClickListener(this);


        Drawable arryw[]={getResources().getDrawable(R.drawable.paytm),
                getResources().getDrawable(R.drawable.mobikwik),
                getResources().getDrawable(R.drawable.freecharge)};
        String arrysw[]={getResources().getString(R.string.paytm),
                getResources().getString(R.string.mobiikwik),
                getResources().getString(R.string.freecharge)};
        wpaymentAdapter = new PaymentAdapter(arryw,arrysw);
        vRAspWallet.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAspWallet.setAdapter(wpaymentAdapter);

        Drawable arryo[]={getResources().getDrawable(R.drawable.bank_card),
                getResources().getDrawable(R.drawable.sodexo),
                getResources().getDrawable(R.drawable.net_banking)};
        String arryso[]={getResources().getString(R.string.credit_debit),
                getResources().getString(R.string.sodexo),
                getResources().getString(R.string.netbanking)};
        opaymentAdapter = new PaymentAdapter(arryo,arryso);
        vRAspOnline.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAspOnline.setAdapter(opaymentAdapter);


        Drawable arryc[]={getResources().getDrawable(R.drawable.cash)};
        String arrysc[]={getResources().getString(R.string.cash)};
        cpaymentAdapter = new PaymentAdapter(arryc,arrysc);
        vRAspCash.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAspCash.setAdapter(cpaymentAdapter);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_asp_back:
                finish();
                break;
        }
    }

    public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
        Drawable[] arryw;
        String[] arrysw;
        public PaymentAdapter(Drawable[] arryw, String[] arrysw) {
            this.arrysw=arrysw;
            this.arryw=arryw;
        }

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_payment, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_adpa_name.setText(arrysw[position]);
            holder.vI_adpa_like.setBackground(arryw[position]);

            holder.vL_adpa_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SelectPaymentActivity.this,AddCardActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            });

        }

        public int getItemCount() {
            return arrysw.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_adpa_like;
            LinearLayout vL_adpa_card;
            TextView vT_adpa_name;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adpa_card = (LinearLayout) view.findViewById(R.id.vL_adpa_card);
                this.vI_adpa_like = (ImageView) view.findViewById(R.id.vI_adpa_like);
                this.vT_adpa_name = (TextView) view.findViewById(R.id.vT_adpa_name);

                new Runnable() {
                    public void run() {
                        vT_adpa_name.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
