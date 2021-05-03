package com.geekhive.foodey.Food.booking;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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
import com.geekhive.foodey.Food.beans.tablebooking.BookingDetails;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user pc on 30-07-2018.
 */

public class UpcomingFragment extends Fragment implements OnResponseListner {
    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;
    View view;
    Bundle savedInstanceStateq;
    Unbinder unbinder;
    @BindView(R.id.vR_fu_list)
    RecyclerView vRFuList;
    BookingAdapter bookingAdapter;
    ConnectionDetector mDetector;
    BookingDetails bookingDetails;

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
        mDetector = new ConnectionDetector(getActivity());
        CallService();

    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).BookingDetails(WebServices.Foodey_Services,
                    WebServices.ApiType.bookingdetails, Prefs.getUserId(getActivity()));
        } else {
            SnackBar.makeText(getActivity(), "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.bookingdetails) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                bookingDetails = (BookingDetails) response;
                if (bookingDetails != null){
                    if (bookingDetails.getBookDetail() != null){
                        bookingAdapter = new BookingAdapter();
                        vRFuList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        vRFuList.setAdapter(bookingAdapter);
                        //CallService();
                    } else {
                        SnackBar.makeText(getActivity(), bookingDetails.getMessage(), SnackBar.LENGTH_SHORT).show();
                    }
                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bookings, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            String restaurantName = bookingDetails.getBookDetail().get(position).getResturant().getName().toString();
            String restAdd = bookingDetails.getBookDetail().get(position).getResturant().getAddress().toString();
            String restStyle = bookingDetails.getBookDetail().get(position).getResturant().getType().toString();
            holder.vT_adb_pname.setText(restaurantName);
            holder.vT_adb_place.setText(restAdd);
            holder.vT_adb_style.setText(restStyle);
            if (bookingDetails.getBookDetail().get(position).getBooking().getStatus().equals("0")){
                holder.vT_adb_status.setText("Pending");
            } else if (bookingDetails.getBookDetail().get(position).getBooking().getStatus().equals("1")){
                holder.vT_adb_status.setText("Confirmed");
            } else if (bookingDetails.getBookDetail().get(position).getBooking().getStatus().equals("2")){
                holder.vT_adb_status.setText("Cancel");
            }

            Picasso.get()
                    .load(WebServices.Foodey_Rest_Url + bookingDetails.getBookDetail().get(position).getResturant().getImage())
                    .into(holder.vI_adb_image);

            holder.vT_adb_nameval.setText(bookingDetails.getBookDetail().get(position).getBooking().getName());
            holder.vT_adb_dateval.setText(bookingDetails.getBookDetail().get(position).getBooking().getBookDate());
            holder.vT_adb_timeval.setText(bookingDetails.getBookDetail().get(position).getBooking().getTime());
            holder.vT_adb_noval.setText(bookingDetails.getBookDetail().get(position).getBooking().getNoGuest());

            String orderid = "ID: " + bookingDetails.getBookDetail().get(position).getBooking().getId();
            holder.vT_adb_orderid.setText(orderid);


            holder.vT_ard_viewmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String geoUri = "http://maps.google.com/maps?q=loc:" + bookingDetails.getBookDetail().get(position).getResturant().getLat() + "," + bookingDetails.getBookDetail().get(position).getResturant().getLong() + " (" + bookingDetails.getBookDetail().get(position).getResturant().getName() + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                    startActivity(intent);
                }
            });

        }

        public int getItemCount() {
            return bookingDetails.getBookDetail().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            CardView vC_adb_main;
            ImageView vI_adb_image;
            TextView vT_adb_pname,vT_adb_status,vT_adb_place,vT_adb_style,vT_adb_name,vT_adb_nameval,
                    vT_adb_date,vT_adb_dateval,vT_adb_time,vT_adb_timeval,vT_adb_no,vT_adb_noval,vT_adb_orderid, vT_ard_viewmap;

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
                this.vT_ard_viewmap = (TextView) view.findViewById(R.id.vT_ard_viewmap);
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
                        vT_ard_viewmap.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}
