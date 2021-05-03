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

public class ScheduleFragment extends Fragment {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    View view;
    Bundle savedInstanceStateq;
    Unbinder unbinder;
    @BindView(R.id.vR_fu_list)
    RecyclerView vRFuList;
    ShecduleAdapter shecduleAdapter;


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
        shecduleAdapter = new ShecduleAdapter();
        vRFuList.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRFuList.setAdapter(shecduleAdapter);
    }

    public class ShecduleAdapter extends RecyclerView.Adapter<ShecduleAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_schedule_time, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

        }

        public int getItemCount() {
            return 5;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_adst_card;
            ImageView vI_adb_image;
            TextView vT_adst_start, vT_adst_dash, vT_adst_end;

            public MyViewHolder(View view) {
                super(view);
                this.vT_adst_start = (TextView) view.findViewById(R.id.vT_adst_start);
                this.vT_adst_end = (TextView) view.findViewById(R.id.vT_adst_end);
                this.vT_adst_dash = (TextView) view.findViewById(R.id.vT_adst_dash);
                this.vL_adst_card = (LinearLayout) view.findViewById(R.id.vL_adst_card);
                this.vI_adb_image = (ImageView) view.findViewById(R.id.vI_adb_image);
                new Runnable() {
                    public void run() {
                        vT_adst_start.setTypeface(NIRMALA);
                        vT_adst_end.setTypeface(NIRMALA);
                        vT_adst_dash.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}


