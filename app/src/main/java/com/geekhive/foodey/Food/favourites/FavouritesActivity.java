package com.geekhive.foodey.Food.favourites;

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

public class FavouritesActivity extends AppCompatActivity implements View.OnClickListener {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    FavouritesAdapter favouritesAdapter;
    @BindView(R.id.vI_afr_back)
    ImageView vIAfrBack;
    @BindView(R.id.vT_afr_header)
    TextView vTAfrHeader;
    @BindView(R.id.vT_afr_your)
    TextView vTAfrYour;
    @BindView(R.id.vR_afr_list)
    RecyclerView vRAfrList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourties);
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

                vTAfrHeader.setTypeface(NIRMALAB);
                vTAfrYour.setTypeface(NIRMALAB);


            }
        };
        r.run();
    }

    private void setvalues() {
        vIAfrBack.setOnClickListener(this);

        favouritesAdapter = new FavouritesAdapter();
        vRAfrList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAfrList.setAdapter(favouritesAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_afr_back:
                finish();
                break;
        }
    }

    public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.MyViewHolder> {
        int spos = -1;

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fav, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {


        }

        public int getItemCount() {
            return 10;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_adfv_like;
            LinearLayout vL_adfv_main;
            TextView vT_adfv_name;

            public MyViewHolder(View view) {
                super(view);
                this.vL_adfv_main = (LinearLayout) view.findViewById(R.id.vL_adfv_main);
                this.vI_adfv_like = (ImageView) view.findViewById(R.id.vI_adfv_like);
                this.vT_adfv_name = (TextView) view.findViewById(R.id.vT_adfv_name);

                new Runnable() {
                    public void run() {
                        vT_adfv_name.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
