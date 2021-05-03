package com.geekhive.foodey.Food.faq;

import android.graphics.Typeface;
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

public class FaqActivity extends AppCompatActivity implements View.OnClickListener {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    FAQAdapter faqAdapter;
    @BindView(R.id.vI_af_back)
    ImageView vIAfBack;
    @BindView(R.id.vT_af_header)
    TextView vTAfHeader;
    @BindView(R.id.vT_af_general)
    TextView vTAfGeneral;
    @BindView(R.id.vR_af_list)
    RecyclerView vRAfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
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

                vTAfHeader.setTypeface(NIRMALAB);
                vTAfGeneral.setTypeface(NIRMALAB);


            }
        };
        r.run();
    }

    private void setvalues() {
        vIAfBack.setOnClickListener(this);

        faqAdapter = new FAQAdapter();
        vRAfList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAfList.setAdapter(faqAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_af_back:
                finish();
                break;
        }
    }

    public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {
        int spos=-1;

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_faq, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {


            holder.vL_adfq_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (spos==position)
                    {
                        spos=-1;
                    }else {
                        spos=position;
                    }
                    notifyDataSetChanged();
                }
            });

            if (spos==position)
            {
                holder.vT_adfq_answer.setVisibility(View.VISIBLE);
                holder.vI_adfq_arrow.setBackgroundResource(R.drawable.right_arrow_small);
                holder.vI_adfq_arrow.setRotation(90);

            }else {
                holder.vI_adfq_arrow.setRotation(0);
                holder.vT_adfq_answer.setVisibility(View.GONE);
                holder.vI_adfq_arrow.setBackgroundResource(R.drawable.right_arrow_small);

            }

        }

        public int getItemCount() {
            return 10;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_adfq_arrow;
            LinearLayout vL_adfq_card,vL_adfq_main;
            TextView vT_adfq_que,vT_adfq_answer;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adfq_card = (LinearLayout) view.findViewById(R.id.vL_adfq_card);
                this.vL_adfq_main = (LinearLayout) view.findViewById(R.id.vL_adfq_main);
                this.vI_adfq_arrow = (ImageView) view.findViewById(R.id.vI_adfq_arrow);
                this.vT_adfq_que = (TextView) view.findViewById(R.id.vT_adfq_que);
                this.vT_adfq_answer = (TextView) view.findViewById(R.id.vT_adfq_answer);

                new Runnable() {
                    public void run() {
                        vT_adfq_que.setTypeface(NIRMALA);
                        vT_adfq_answer.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
