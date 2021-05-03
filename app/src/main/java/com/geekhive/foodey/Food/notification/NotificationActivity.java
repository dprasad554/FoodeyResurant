package com.geekhive.foodey.Food.notification;

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

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_an_back)
    ImageView vIAnBack;
    @BindView(R.id.vT_an_header)
    TextView vTAnHeader;
    @BindView(R.id.vR_an_list)
    RecyclerView vRAnList;
    NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
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

                vTAnHeader.setTypeface(NIRMALAB);


            }
        };
        r.run();
    }

    private void setvalues() {
        vIAnBack.setOnClickListener(this);

        notificationAdapter = new NotificationAdapter();
        vRAnList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAnList.setAdapter(notificationAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_an_back:
                finish();
                break;
        }
    }

    public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notification, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {


        }

        public int getItemCount() {
            return 10;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_adn_banner;
            LinearLayout vL_adn_main;
            TextView vT_adn_time;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adn_main = (LinearLayout) view.findViewById(R.id.vL_adn_main);
                this.vI_adn_banner = (ImageView) view.findViewById(R.id.vI_adn_banner);
                this.vT_adn_time = (TextView) view.findViewById(R.id.vT_adn_time);

                new Runnable() {
                    public void run() {
                        vT_adn_time.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
