package com.geekhive.foodey.Food.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekhive.foodey.Food.beans.serviceslider.ServiceSlider;
import com.geekhive.foodey.Food.form.FormActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    @BindView(R.id.vI_tl_back)
    ImageView vITlBack;
    @BindView(R.id.vT_tl_header)
    TextView vTTlHeader;
    @BindView(R.id.vT_tl_location)
    TextView vTTlLocation;
    @BindView(R.id.vL_tl_location)
    LinearLayout vLTlLocation;
    @BindView(R.id.vI_tl_notification)
    ImageView vITlNotification;
    @BindView(R.id.vL_tl_count)
    TextView vLTlCount;
    @BindView(R.id.vL_tl_notification)
    LinearLayout vLTlNotification;
    @BindView(R.id.vR_tl_notification)
    RelativeLayout vRTlNotification;
    @BindView(R.id.vI_tl_cart)
    ImageView vITlCart;
    @BindView(R.id.vR_tl_search)
    RelativeLayout vRTlSearch;
    @BindView(R.id.vR_gl_search)
    RelativeLayout vRGLSearch;

    @BindView(R.id.vL_tl_skip)
    TextView vLTlSkip;
    @BindView(R.id.vL_toolbarLayout)
    LinearLayout vLToolbarLayout;
/*
    @BindView(R.id.vL_view_bookings)
    LinearLayout vL_view_bookings;*/


    View v;
    RecyclerView vROffer, vRServices, servicesRecyclerView;
    ConnectionDetector mDetector;

    ArrayList serviceImages = new ArrayList<>(Arrays.asList(R.drawable.airconditioner, R.drawable.makeup, R.drawable.woman, R.drawable.pest,
            R.drawable.kitchen, R.drawable.electricity, R.drawable.people, R.drawable.toolbox, R.drawable.painter));

    ArrayList serviceName = new ArrayList<>(Arrays.asList("Ac Service & Repair", "Saloon at Home", "Haircut at Home", "Cleaning & Pest Control", "Appliance Repair",
            "Electronics", "Plumber", "Carpenter", "Painter"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        vROffer = findViewById(R.id.vR_offers);
        // vRServices=findViewById(R.id.vR_services);
        //vL_view_bookings.setOnClickListener(this);
        servicesRecyclerView = findViewById(R.id.services_RecyclerView);
        mDetector = new ConnectionDetector(this);
        vITlBack.setVisibility(View.GONE);
        vTTlHeader.setVisibility(View.VISIBLE);
        vLTlLocation.setVisibility(View.GONE);
        vRTlNotification.setVisibility(View.GONE);
        vRGLSearch.setVisibility(View.GONE);
        vITlCart.setVisibility(View.VISIBLE);
        vTTlHeader.setText("Foodey Services");
        vITlCart.setOnClickListener(this);
        CallOfferService();


        LinearLayoutManager layoutServiceManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

//        vRServices.setLayoutManager(layoutServiceManager);
//        ServiceListAdapter serviceListAdapter = new ServiceListAdapter();
//        vRServices.setAdapter(serviceListAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        servicesRecyclerView.setLayoutManager(gridLayoutManager);
        CommercialServiceAdapter serviceAdapter = new CommercialServiceAdapter(this, serviceImages, serviceName);
        servicesRecyclerView.setAdapter(serviceAdapter);


    }

    private void CallOfferService() {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).ServiceOffer(WebServices.Foodey_ServiceUrl,
                    WebServices.ApiType.getserviceoffer);
            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.getserviceoffer) {
            if (!isSucces) {
                SnackBar.makeText(ServiceActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final ServiceSlider serviceSlider = (ServiceSlider) response;

                if (!isSucces || serviceSlider == null) {
                    SnackBar.makeText(ServiceActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {

                    LinearLayoutManager layoutOfferManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

                    vROffer.setLayoutManager(layoutOfferManager);
                    ServiceOfferAdapter serviceOfferAdapter = new ServiceOfferAdapter(this, serviceSlider);
                    vROffer.setAdapter(serviceOfferAdapter);


                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.vI_tl_cart:
                Intent intent = new Intent(this, ServiceHistoryActivity.class);
                startActivity(intent);
                break;


        }
    }


    private void initialiseView(View v, final Context context) {
    }


    public class ServiceOfferAdapter extends RecyclerView.Adapter<ServiceOfferAdapter.ViewHolder> {

        //Variables
        private Context mcontext;
        ServiceSlider serviceSlider;

        public ServiceOfferAdapter(Context context, ServiceSlider serviceSlider) {
            this.mcontext = context;
            this.serviceSlider = serviceSlider;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_offerslider, parent, false);
            return new ViewHolder(view);
        }

        @Override

        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


            Picasso.get()
                    .load(WebServices.Foodey_Service_Img_Url + serviceSlider.getSSlider().get(position).getImage())
                    .into(holder.myImageViewSS);


        }

        @Override
        public int getItemCount() {
            return serviceSlider.getSSlider().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView myImageViewSS;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                myImageViewSS = itemView.findViewById(R.id.myImageViewSS);

            }
        }
    }

    public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

        //Variables

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_servicelist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (position == 0) {
                holder.image.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.pestcontroloffer1));
                holder.linear.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.cpurple));
                holder.vT_adeot_name.setText("Pest Service");
            } else if (position == 1) {
                holder.vT_adeot_name.setText("Cleaning Service");
                holder.linear.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.corange));


                holder.image.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.pestcontroloffe2r));
            } else if (position == 2) {
                holder.vT_adeot_name.setText("Electrician");
                holder.linear.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.cblue));


                holder.image.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.pestcontroloffer3));
            } else if (position == 3) {
                holder.vT_adeot_name.setText("Ant and Cockroach Control");
                holder.linear.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.cb1));


                holder.image.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.pestcontroloffer3));
            } else if (position == 4) {
                holder.vT_adeot_name.setText("Mosquito Control");
                holder.linear.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.cb2));


                holder.image.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.pestcontroloffer3));
            } else if (position == 5) {
                holder.vT_adeot_name.setText("Bed bug Control");
                holder.linear.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.cb3));


                holder.image.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.pestcontroloffer3));
            }


            String ItemName = holder.vT_adeot_name.getText().toString();

            holder.vC_services.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getApplicationContext(), FormActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    i.putExtra("item", ItemName);
                    getApplicationContext().startActivity(i);

                }
            });


        }


        @Override
        public int getItemCount() {
            return 6;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            LinearLayout linear;
            TextView vT_adeot_name;
            CardView vC_services;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.vI_adeot_image);
                vT_adeot_name = itemView.findViewById(R.id.vT_adeot_name);
                linear = itemView.findViewById(R.id.linear);
                vC_services = itemView.findViewById(R.id.vC_services);


            }
        }
    }


}
