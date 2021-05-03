package com.geekhive.foodey.Food.orderfood;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
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
import butterknife.Unbinder;

/**
 * Created by user pc on 31-07-2018.
 */

public class DishFragment extends Fragment {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    View view;
    Bundle savedInstanceStateq;
    Unbinder unbinder;
    @BindView(R.id.vR_fu_list)
    RecyclerView vRFuList;
    DishAdapter dishAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_upcoming, container, false);
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
        dishAdapter = new DishAdapter();
        vRFuList.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRFuList.setAdapter(dishAdapter);
    }

    public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dishes, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
        }

        public int getItemCount() {
            return 10;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_add_main,vL_add_hide;
            ImageView vI_add_veg;
            TextView vT_add_name, vT_add_price, vT_add_add, vT_add_decrease, vT_add_quantity, vT_add_increase;

            public MyViewHolder(View view) {
                super(view);
                this.vT_add_name = (TextView) view.findViewById(R.id.vT_add_name);
                this.vT_add_price = (TextView) view.findViewById(R.id.vT_add_price);
                this.vT_add_add = (TextView) view.findViewById(R.id.vT_add_add);
                this.vT_add_decrease = (TextView) view.findViewById(R.id.vT_add_decrease);
                this.vT_add_quantity = (TextView) view.findViewById(R.id.vT_add_quantity);
                this.vT_add_increase = (TextView) view.findViewById(R.id.vT_add_increase);

                this.vL_add_main = (LinearLayout) view.findViewById(R.id.vL_add_main);
                this.vL_add_hide = (LinearLayout) view.findViewById(R.id.vL_add_hide);
                this.vI_add_veg = (ImageView) view.findViewById(R.id.vI_add_veg);
                new Runnable() {
                    public void run() {
                        vT_add_name.setTypeface(NIRMALA);
                        vT_add_price.setTypeface(NIRMALA);
                        vT_add_add.setTypeface(NIRMALA);
                        vT_add_decrease.setTypeface(NIRMALA);
                        vT_add_quantity.setTypeface(NIRMALAB);
                        vT_add_increase.setTypeface(NIRMALA);


                    }
                }.run();
            }
        }
    }
}


