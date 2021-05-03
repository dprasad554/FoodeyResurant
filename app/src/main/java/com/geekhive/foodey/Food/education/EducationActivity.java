package com.geekhive.foodey.Food.education;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.login.LoginActivity;
import com.geekhive.foodey.Food.utils.Prefs;
import com.rd.PageIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EducationActivity extends AppCompatActivity implements View.OnClickListener {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    @BindView(R.id.vI_ae_back)
    ImageView vIAeBack;
    @BindView(R.id.vT_ae_skip)
    TextView vTAeSkip;
    @BindView(R.id.vV_ae_educational)
    ViewPager vVAeEducational;
    @BindView(R.id.vV_ae_pageIndicatorView)
    PageIndicatorView vVAePageIndicatorView;
    private Customization customization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        ButterKnife.bind(this);
        initializeFonts();
        setTypeFace();
        setvalues();
    }


    private void setfullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
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

                vTAeSkip.setTypeface(NIRMALA);


            }
        };
        r.run();
    }

    private void setvalues() {
        customization = new Customization();
        CustomPagerMainAdapter mAdapter = new CustomPagerMainAdapter(this);
        vVAeEducational.setAdapter(mAdapter);
        vVAePageIndicatorView.setAnimationType(customization.getAnimationType());
        vVAePageIndicatorView.setOrientation(customization.getOrientation());
        vVAePageIndicatorView.setRtlMode(customization.getRtlMode());
        vVAePageIndicatorView.setInteractiveAnimation(customization.isInteractiveAnimation());
        vVAePageIndicatorView.setAutoVisibility(customization.isAutoVisibility());
        vVAePageIndicatorView.setViewPager(vVAeEducational);
        vTAeSkip.setOnClickListener(this);
        vIAeBack.setOnClickListener(this);
        vVAeEducational.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    vIAeBack.setVisibility(View.INVISIBLE);
                    vTAeSkip.setVisibility(View.VISIBLE);
                } else {
                    vIAeBack.setVisibility(View.VISIBLE);
                    vTAeSkip.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vT_ae_skip:
                Prefs.setSplashScreenPref(EducationActivity.this, "true");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.vI_ae_back:
                finish();
                break;
        }
    }

    class CustomPagerMainAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerMainAdapter(Context context) {
            this.mContext = context;
            mLayoutInflater = ((LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        }

        public int getCount() {
            return 4;
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = this.mLayoutInflater.inflate(R.layout.pager_item, container, false);
            LinearLayout vL_pi_select_res = itemView.findViewById(R.id.vL_pi_select_res);
            LinearLayout vL_pi_select_food = itemView.findViewById(R.id.vL_pi_select_food);
            LinearLayout vL_pi_order_food = itemView.findViewById(R.id.vL_pi_order_food);

            LinearLayout vL_pi_select_store = itemView.findViewById(R.id.vL_pi_select_gross);
            LinearLayout vL_pi_select_grocery = itemView.findViewById(R.id.vL_pi_select_gross_item);
            LinearLayout vL_pi_order_grocery = itemView.findViewById(R.id.vL_pi_order_gross);

            LinearLayout vL_pi_select_shop = itemView.findViewById(R.id.vL_pi_select_cake);
            LinearLayout vL_pi_select_cake = itemView.findViewById(R.id.vL_pi_select_cake_item);
            LinearLayout vL_pi_order_cake = itemView.findViewById(R.id.vL_pi_order_cake);

            LinearLayout vL_pi_get_start = itemView.findViewById(R.id.vL_pi_get_start);
            /*GifImageView gifImageView = itemView.findViewById(R.id.gifImage);
            gifImageView.setGifImageResource(R.drawable.food_);*/
            final RelativeLayout vR_pi_login_reg = itemView.findViewById(R.id.vR_pi_login_reg);
            final TextView vT_pi_select_res = itemView.findViewById(R.id.vT_pi_select_res);
            final TextView vT_pi_select_res_sam = itemView.findViewById(R.id.vT_pi_select_res_sam);
            final TextView vT_pi_select_food = itemView.findViewById(R.id.vT_pi_select_food);
            final TextView vT_pi_select_food_sam = itemView.findViewById(R.id.vT_pi_select_food_sam);
            final TextView vT_pi_order_food = itemView.findViewById(R.id.vT_pi_order_food);
            final TextView vT_pi_order_food_sam = itemView.findViewById(R.id.vT_pi_order_food_sam);
            final TextView vT_pi_get_start = itemView.findViewById(R.id.vT_pi_get_start);
            final TextView vT_pi_get_start_sam = itemView.findViewById(R.id.vT_pi_get_start_sam);
            final TextView vT_pi_login = itemView.findViewById(R.id.vT_pi_login);


            if (position == 3) {
                vR_pi_login_reg.setVisibility(View.VISIBLE);
                vL_pi_order_food.setVisibility(View.GONE);
                vL_pi_select_food.setVisibility(View.GONE);
                vL_pi_select_res.setVisibility(View.GONE);

                vL_pi_select_store.setVisibility(View.GONE);
                vL_pi_select_grocery.setVisibility(View.GONE);
                vL_pi_order_grocery.setVisibility(View.GONE);

                vL_pi_select_shop.setVisibility(View.GONE);
                vL_pi_select_cake.setVisibility(View.GONE);
                vL_pi_order_cake.setVisibility(View.GONE);

                vL_pi_get_start.setVisibility(View.VISIBLE);
                vTAeSkip.setVisibility(View.INVISIBLE);
                vIAeBack.setVisibility(View.VISIBLE);

            } else if (position == 2){
                vR_pi_login_reg.setVisibility(View.GONE);
                vL_pi_order_food.setVisibility(View.GONE);
                vL_pi_select_food.setVisibility(View.GONE);
                vL_pi_select_res.setVisibility(View.GONE);

                vL_pi_select_store.setVisibility(View.GONE);
                vL_pi_select_grocery.setVisibility(View.GONE);
                vL_pi_order_grocery.setVisibility(View.GONE);

                vL_pi_select_shop.setVisibility(View.VISIBLE);
                vL_pi_select_cake.setVisibility(View.VISIBLE);
                vL_pi_order_cake.setVisibility(View.VISIBLE);

                vL_pi_get_start.setVisibility(View.GONE);
                vTAeSkip.setVisibility(View.VISIBLE);
                vIAeBack.setVisibility(View.INVISIBLE);
            } else if (position == 1){
                vR_pi_login_reg.setVisibility(View.GONE);
                vL_pi_order_food.setVisibility(View.GONE);
                vL_pi_select_food.setVisibility(View.GONE);
                vL_pi_select_res.setVisibility(View.GONE);

                vL_pi_select_store.setVisibility(View.VISIBLE);
                vL_pi_select_grocery.setVisibility(View.VISIBLE);
                vL_pi_order_grocery.setVisibility(View.VISIBLE);

                vL_pi_select_shop.setVisibility(View.GONE);
                vL_pi_select_cake.setVisibility(View.GONE);
                vL_pi_order_cake.setVisibility(View.GONE);

                vL_pi_get_start.setVisibility(View.GONE);
                vTAeSkip.setVisibility(View.VISIBLE);
                vIAeBack.setVisibility(View.INVISIBLE);
            }else {
                vR_pi_login_reg.setVisibility(View.GONE);
                vL_pi_order_food.setVisibility(View.VISIBLE);
                vL_pi_select_food.setVisibility(View.VISIBLE);
                vL_pi_select_res.setVisibility(View.VISIBLE);

                vL_pi_select_store.setVisibility(View.GONE);
                vL_pi_select_grocery.setVisibility(View.GONE);
                vL_pi_order_grocery.setVisibility(View.GONE);

                vL_pi_select_shop.setVisibility(View.GONE);
                vL_pi_select_cake.setVisibility(View.GONE);
                vL_pi_order_cake.setVisibility(View.GONE);

                vL_pi_get_start.setVisibility(View.GONE);
                vTAeSkip.setVisibility(View.VISIBLE);
                vIAeBack.setVisibility(View.INVISIBLE);
            }

            new Runnable() {
                public void run() {
                    vT_pi_get_start.setTypeface(NIRMALAB);
                    vT_pi_get_start_sam.setTypeface(NIRMALA);
                    vT_pi_login.setTypeface(NIRMALAB);
                    vT_pi_order_food.setTypeface(NIRMALAB);
                    vT_pi_order_food_sam.setTypeface(NIRMALA);
                    vT_pi_select_food.setTypeface(NIRMALAB);
                    vT_pi_select_food_sam.setTypeface(NIRMALA);
                    vT_pi_select_res.setTypeface(NIRMALAB);
                    vT_pi_select_res_sam.setTypeface(NIRMALA);

                }
            }.run();


            vT_pi_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vR_pi_login_reg.setVisibility(View.GONE);
                    Prefs.setSplashScreenPref(EducationActivity.this, "true");
                    Intent intent = new Intent(EducationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                }
            });


            container.addView(itemView);
            return itemView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
