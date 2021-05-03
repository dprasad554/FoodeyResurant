package com.geekhive.foodey.Food.orderfood;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.carousel.CarouselEffectTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFoodDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.vI_aod_image)
    ImageView vIAodImage;
    @BindView(R.id.vT_aod_photos)
    TextView vTAodPhotos;
    @BindView(R.id.vI_aod_fav)
    ImageView vIAodFav;
    @BindView(R.id.vI_aod_veg)
    ImageView vIAodVeg;
    @BindView(R.id.vT_aod_veg)
    TextView vTAodVeg;
    @BindView(R.id.vL_aod_veg)
    LinearLayout vLAodVeg;
    @BindView(R.id.vT_aod_name)
    TextView vTAodName;
    @BindView(R.id.vT_aod_place)
    TextView vTAodPlace;
    @BindView(R.id.vT_aod_style)
    TextView vTAodStyle;
    @BindView(R.id.vT_aod_viewmap)
    TextView vTAodViewmap;
    @BindView(R.id.vT_aod_rating)
    TextView vTAodRating;
    @BindView(R.id.vR_aod_main)
    RelativeLayout vRAodMain;
    @BindView(R.id.vT_aod_offer)
    TextView vTAodOffer;
    @BindView(R.id.vI_aod_back)
    ImageView vIAodBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vC_aod_collapsing_toolbar)
    CollapsingToolbarLayout vCAodCollapsingToolbar;
    @BindView(R.id.vA_aod_appbar)
    AppBarLayout vAAodAppbar;
    @BindView(R.id.vV_aod_sliding_tabs)
    TabLayout vVAodSlidingTabs;
    @BindView(R.id.vV_aod_category_viewpager)
    ViewPager vVAodCategoryViewpager;
    @BindView(R.id.vT_aod_pricetotal)
    TextView vTAodPricetotal;
    @BindView(R.id.vT_aod_item)
    TextView vTAodItem;
    @BindView(R.id.vT_aod_delivery)
    TextView vTAodDelivery;
    @BindView(R.id.vT_aod_continue)
    TextView vTAodContinue;

    Dialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_details);
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

                vTAodContinue.setTypeface(NIRMALAB);
                vTAodDelivery.setTypeface(NIRMALA);
                vTAodItem.setTypeface(NIRMALA);
                vTAodName.setTypeface(NIRMALAB);
                vTAodOffer.setTypeface(NIRMALA);
                vTAodPhotos.setTypeface(NIRMALA);
                vTAodPlace.setTypeface(NIRMALA);
                vTAodPricetotal.setTypeface(NIRMALAB);
                vTAodRating.setTypeface(NIRMALA);
                vTAodStyle.setTypeface(NIRMALA);
                vTAodVeg.setTypeface(NIRMALA);
                vTAodViewmap.setTypeface(NIRMALAB);


            }
        };
        r.run();
    }

    private void setvalues() {

        vIAodBack.setOnClickListener(this);
        vTAodPhotos.setOnClickListener(this);
        vTAodContinue.setOnClickListener(this);

        this.vVAodCategoryViewpager.setAdapter(new CategoryAdapter(getSupportFragmentManager()));
        this.vVAodCategoryViewpager.setCurrentItem(0);
        this.vVAodCategoryViewpager.setOffscreenPageLimit(2);
        this.vVAodSlidingTabs.setupWithViewPager(this.vVAodCategoryViewpager);
        ViewGroup vg = (ViewGroup) this.vVAodSlidingTabs.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(NIRMALAB);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.vI_aod_back:
                finish();
                break;
            case R.id.vT_aod_photos:
                Initializepopup();
                initializDialog();
                break;
            case R.id.vT_aod_continue:
                Intent intent=new Intent(this,CheckOutActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }

    private class CategoryAdapter extends FragmentStatePagerAdapter {
        private String[] tabTitles = new String[]{"Soups",
                "Starters", "Main Course", "Beverages", "Desserts"};

        public CategoryAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return this.tabTitles.length;
        }

        public Fragment getItem(int position) {
            return new DishFragment();
        }

        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View v = getLayoutInflater().inflate(R.layout.custom_tab, null);
            final TextView tv = (TextView) v.findViewById(R.id.ad_cu_textView);
            tv.setText(this.tabTitles[position]);
            new Runnable() {
                public void run() {
                    tv.setTypeface(NIRMALA);
                }
            }.run();
            return v;
        }
    }


    private void Initializepopup() {
        Dialog = new Dialog(this);
        Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Dialog.setContentView(R.layout.popup_image);
        Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
    }

    private void initializDialog() {
        Dialog.setContentView(R.layout.popup_image);
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
        Dialog.show();

        final TextView vT_pi_name = (TextView) Dialog.findViewById(R.id.vT_pi_name);
        final TextView vT_pi_photos = (TextView) Dialog.findViewById(R.id.vT_pi_photos);
        final TextView vT_pi_pcount = (TextView) Dialog.findViewById(R.id.vT_pi_pcount);
        final ImageView vI_pi_close = (ImageView) Dialog.findViewById(R.id.vI_pi_close);
        ViewPager vV_pi_viewpager = (ViewPager) Dialog.findViewById(R.id.vV_pi_viewpager);
//        final PagerContainer vP_pi_planContainer = (PagerContainer) Dialog.findViewById(R.id.vP_pi_planContainer);

        vV_pi_viewpager.setClipChildren(false);
        vV_pi_viewpager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.pager_margin));
        vV_pi_viewpager.setOffscreenPageLimit(2);
        vV_pi_viewpager.setPageTransformer(false, new CarouselEffectTransformer(this));

        MyPagerAdapter adapter = new MyPagerAdapter(this);
        vV_pi_viewpager.setAdapter(adapter);
//        vV_pi_viewpager = vP_pi_planContainer.getViewPager();
//        vV_pi_viewpager.setAdapter(adapter);
//        vV_pi_viewpager.setOffscreenPageLimit(adapter.getCount());
//        vV_pi_viewpager.setPageMargin(5);
//        vV_pi_viewpager.setClipChildren(false);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_pi_name.setTypeface(NIRMALAB);
                vT_pi_photos.setTypeface(NIRMALA);
                vT_pi_pcount.setTypeface(NIRMALA);

            }
        };
        r.run();
        Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Dialog.getWindow().setGravity(Gravity.CENTER);


        vI_pi_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Dialog.isShowing())
                    Dialog.dismiss();

            }
        });

        Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Dialog.setCancelable(true);
        Dialog.setCanceledOnTouchOutside(true);
        Dialog.show();
    }
    public class MyPagerAdapter extends PagerAdapter {

        Context context;

        public MyPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_pop_photos, null);


            container.addView(view);


            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 16;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

    }

}
