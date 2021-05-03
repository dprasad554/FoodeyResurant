package com.geekhive.foodey.Food.booking;

import android.graphics.Typeface;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookingsFragment extends Fragment {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    Fragment fragment;
    View view;
    Bundle savedInstanceStateq;
    Unbinder unbinder;
    @BindView(R.id.vV_fb_sliding_tabs)
    TabLayout vVFbSlidingTabs;
    @BindView(R.id.vV_fb_category_viewpager)
    ViewPager vVFbCategoryViewpager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_bookings, container, false);
        this.savedInstanceStateq = savedInstanceState;
        unbinder = ButterKnife.bind(this, view);
        initializeFonts();
        setvalues();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAS.TTF");
    }

    private void setvalues() {


        this.vVFbCategoryViewpager.setAdapter(new CategoryAdapter(getActivity().getSupportFragmentManager()));
        this.vVFbCategoryViewpager.setCurrentItem(0);
        this.vVFbCategoryViewpager.setOffscreenPageLimit(2);
        this.vVFbSlidingTabs.setupWithViewPager(this.vVFbCategoryViewpager);
        ViewGroup vg = (ViewGroup) this.vVFbSlidingTabs.getChildAt(0);
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

    private class CategoryAdapter extends FragmentStatePagerAdapter {
        private String[] tabTitles = new String[]{getResources().getString(R.string.upcoming)};

        public CategoryAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return this.tabTitles.length;
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment = new UpcomingFragment();
                    break;
                /*case 1:
                    //fragment = new HistoryFragment();
                    break;*/


            }
            return fragment;
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
}
