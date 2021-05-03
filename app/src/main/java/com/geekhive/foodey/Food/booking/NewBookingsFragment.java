package com.geekhive.foodey.Food.booking;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewBookingsFragment extends Fragment {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    Fragment fragment;
    View view;
    Bundle savedInstanceStateq;
    Unbinder unbinder;
    /*@BindView(R.id.vV_fb_sliding_tabs)
    TabLayout vVFbSlidingTabs;
    @BindView(R.id.vV_fb_category_viewpager)
    ViewPager vVFbCategoryViewpager;*/
    @BindView(R.id.tv_DBDate)
    TextView tv_DBDate;
    @BindView(R.id.tv_DBTime)
    TextView tv_DBTime;
    @BindView(R.id.dateLinear)
    LinearLayout dateLinear;
    @BindView(R.id.timeLinear)
    LinearLayout timeLinear;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.activity_table_booking, container, false);
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

        dateLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCalender();
            }
        });
        /*this.vVFbCategoryViewpager.setAdapter(new CategoryAdapter(getActivity().getSupportFragmentManager()));
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
        }*/
    }

    private void ShowCalender() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        if (dayOfMonth > 9) {
                            if (monthOfYear > 9) {

                                tv_DBDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            } else {
                                tv_DBDate.setText(year + "-" + ("0" +(monthOfYear + 1)) + "-" + dayOfMonth);
                            }
                        } else {
                            tv_DBDate.setText(year + "-" + ("0" + (monthOfYear + 1)) + "-" + ("0"+dayOfMonth));
                        }


                    }
                }, mYear, mMonth, mDay);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setCalendarViewShown(true);
        datePickerDialog.show();

    }

    private class CategoryAdapter extends FragmentStatePagerAdapter {
        private String[] tabTitles = new String[]{getResources().getString(R.string.upcoming),
                getResources().getString(R.string.history)};

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
                case 1:
                    fragment = new HistoryFragment();
                    break;


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
