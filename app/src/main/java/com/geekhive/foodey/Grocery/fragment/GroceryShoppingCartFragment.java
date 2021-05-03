package com.geekhive.foodey.Grocery.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekhive.foodey.R;

public class GroceryShoppingCartFragment extends Fragment {

    public GroceryShoppingCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.grocery_fragment_shopping_cart, container, false);

        return view;
    }
}
