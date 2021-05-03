package com.geekhive.foodey.Food.orderfood;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.placeorder.PlaceOrderActivity;

/**
 * Created by user pc on 31-07-2018.
 */

public class DialogFragmentWindow extends DialogFragment {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_shedule, container);
        this.NIRMALA = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getActivity().getAssets(), "NIRMALAS.TTF");
        final TextView vT_fs_slater = (TextView) view.findViewById(R.id.vT_fs_slater);
        final TextView vT_fs_sdate = (TextView) view.findViewById(R.id.vT_fs_sdate);
        final TextView vT_fs_confirm = (TextView) view.findViewById(R.id.vT_fs_confirm);
        final TabLayout vV_fs_sliding_tabs = (TabLayout) view.findViewById(R.id.vV_fs_sliding_tabs);
        final ViewPager vV_fs_category_viewpager = (ViewPager) view.findViewById(R.id.vV_fs_category_viewpager);

        vT_fs_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PlaceOrderActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0,0);

            }
        });
        new Runnable() {
            public void run() {
                vT_fs_slater.setTypeface(NIRMALAB);
                vT_fs_sdate.setTypeface(NIRMALAB);
                vT_fs_confirm.setTypeface(NIRMALAB);



            }
        }.run();
        vV_fs_category_viewpager.setAdapter(new CheckOutActivity.CategoryAdapter(this.getChildFragmentManager()));
        vV_fs_category_viewpager.setCurrentItem(0);
        vV_fs_category_viewpager.setOffscreenPageLimit(2);
        vV_fs_sliding_tabs.setupWithViewPager(vV_fs_category_viewpager);
        ViewGroup vg = (ViewGroup) vV_fs_sliding_tabs.getChildAt(0);
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

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

   /* private List getFragments() {
        List fList = new ArrayList();
        fList.add(new ScheduleFragment());
        //fList.add(new ScheduleFragment());
        return fList;
    }*/
}