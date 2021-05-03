package com.geekhive.foodey.Food.eatout;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.categories.RestaurantCategory;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;

import java.util.ArrayList;
import java.util.List;

public class FixturesTabs extends Fragment implements OnResponseListner {

    ConnectionDetector mDetector;
    RestaurantCategory restaurantCategory;
    String rest_id;
    TabLayout tabs;
    ViewPager viewPager;
    String vegonly = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fixtures_new_tabs,container, false);
        // Setting ViewPager for each Tabs
        rest_id = getArguments().getString("res_id");
        vegonly = getArguments().getString("vegonly");
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        // Set Tabs inside Toolbar
        tabs = (TabLayout) view.findViewById(R.id.result_tabs);
        mDetector = new ConnectionDetector(getActivity());

        if (vegonly.equals("vegonly")){
            CallVegCategoryService();
        } else {
            CallCategoryService();
        }


        return view;

    }

    private void CallCategoryService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantCategories(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantcategories, rest_id);
        }
    }
    private void CallVegCategoryService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).RestaurantVegCategories(WebServices.Foodey_Services,
                    WebServices.ApiType.restaurantvegcategories, rest_id,"veg");
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.restaurantcategories) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                restaurantCategory = (RestaurantCategory) response;
                if (restaurantCategory != null) {
                    //menuAdapter.notifyDataSetChanged();
                    setupViewPager(viewPager);
                    tabs.setupWithViewPager(viewPager);
                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        } if (apiType == WebServices.ApiType.restaurantvegcategories) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                restaurantCategory = (RestaurantCategory) response;
                if (restaurantCategory != null) {
                    //menuAdapter.notifyDataSetChanged();
                    setupViewPager(viewPager);
                    tabs.setupWithViewPager(viewPager);
                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        AdapterPager adapter = new AdapterPager(getChildFragmentManager());
        try {
            for(int j=0; j<restaurantCategory.getCategory().size(); j++){
                adapter.addFragment(MenuFragment.getInstance(restaurantCategory.getCategory().get(j).getCategory(), rest_id, vegonly), restaurantCategory.getCategory().get(j).getCategory());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
    }

    // pager adapter
    static class AdapterPager extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public AdapterPager(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
