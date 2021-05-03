package com.geekhive.foodey.Food.booking;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.eatout.BookingDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user pc on 30-07-2018.
 */

public class HistoryFragment extends Fragment {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    View view;
    Bundle savedInstanceStateq;
    Unbinder unbinder;
    @BindView(R.id.vR_fu_list)
    RecyclerView vRFuList;
    BookingAdapter bookingAdapter;


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
        bookingAdapter = new BookingAdapter();
        vRFuList.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRFuList.setAdapter(bookingAdapter);
    }

    public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bookings, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vC_adb_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), BookingDetailsActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(0,0);
                }
            });

        }

        public int getItemCount() {
            return 2;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            CardView vC_adb_main;
            ImageView vI_adb_image;
            TextView vT_adb_pname, vT_adb_status, vT_adb_place, vT_adb_style, vT_adb_name, vT_adb_nameval,
                    vT_adb_date, vT_adb_dateval, vT_adb_time, vT_adb_timeval, vT_adb_no, vT_adb_noval, vT_adb_orderid;

            public MyViewHolder(View view) {
                super(view);
                this.vT_adb_pname = (TextView) view.findViewById(R.id.vT_adb_pname);
                this.vT_adb_place = (TextView) view.findViewById(R.id.vT_adb_place);
                this.vT_adb_status = (TextView) view.findViewById(R.id.vT_adb_status);
                this.vT_adb_style = (TextView) view.findViewById(R.id.vT_adb_style);
                this.vT_adb_name = (TextView) view.findViewById(R.id.vT_adb_name);
                this.vT_adb_nameval = (TextView) view.findViewById(R.id.vT_adb_nameval);
                this.vT_adb_date = (TextView) view.findViewById(R.id.vT_adb_date);
                this.vT_adb_dateval = (TextView) view.findViewById(R.id.vT_adb_dateval);
                this.vT_adb_time = (TextView) view.findViewById(R.id.vT_adb_time);
                this.vT_adb_timeval = (TextView) view.findViewById(R.id.vT_adb_timeval);
                this.vT_adb_no = (TextView) view.findViewById(R.id.vT_adb_no);
                this.vT_adb_noval = (TextView) view.findViewById(R.id.vT_adb_noval);
                this.vT_adb_orderid = (TextView) view.findViewById(R.id.vT_adb_orderid);
                this.vC_adb_main = (CardView) view.findViewById(R.id.vC_adb_main);
                this.vI_adb_image = (ImageView) view.findViewById(R.id.vI_adb_image);
                new Runnable() {
                    public void run() {
                        vT_adb_pname.setTypeface(NIRMALAB);
                        vT_adb_status.setTypeface(NIRMALAB);
                        vT_adb_place.setTypeface(NIRMALA);
                        vT_adb_style.setTypeface(NIRMALA);
                        vT_adb_name.setTypeface(NIRMALA);
                        vT_adb_nameval.setTypeface(NIRMALA);
                        vT_adb_date.setTypeface(NIRMALA);
                        vT_adb_dateval.setTypeface(NIRMALA);
                        vT_adb_time.setTypeface(NIRMALA);
                        vT_adb_timeval.setTypeface(NIRMALA);
                        vT_adb_no.setTypeface(NIRMALA);
                        vT_adb_noval.setTypeface(NIRMALA);
                        vT_adb_orderid.setTypeface(NIRMALAB);

                    }
                }.run();
            }
        }
    }
}

