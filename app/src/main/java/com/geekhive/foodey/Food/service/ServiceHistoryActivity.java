package com.geekhive.foodey.Food.service;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekhive.foodey.Food.beans.servicehistory.ServiceHistory;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceHistoryActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

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
    @BindView(R.id.vL_tl_skip)
    TextView vLTlSkip;
    @BindView(R.id.vR_gl_search)
    RelativeLayout vRGLSearch;
    @BindView(R.id.vL_toolbarLayout)
    LinearLayout vLToolbarLayout;
    View v;
    RecyclerView serviceOrder;
    ConnectionDetector mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_history);
        ButterKnife.bind(this);
        serviceOrder = findViewById(R.id.serviceOrder);
        mDetector = new ConnectionDetector(this);
        vITlBack.setVisibility(View.GONE);
        vTTlHeader.setVisibility(View.VISIBLE);
        vLTlLocation.setVisibility(View.GONE);
        vRTlNotification.setVisibility(View.GONE);
        vITlCart.setVisibility(View.GONE);
        vRGLSearch.setVisibility(View.GONE);
        vTTlHeader.setText("Service History");
        CallService();


    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {

            new WebServices(this).ServiceHistory(WebServices.Foodey_ServiceUrl,
                    WebServices.ApiType.getservicehistory, Prefs.getUserId(this));
            return;
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.getservicehistory) {
            if (!isSucces) {
                SnackBar.makeText(ServiceHistoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final ServiceHistory serviceHistory = (ServiceHistory) response;

                if (!isSucces || serviceHistory == null) {
                    SnackBar.makeText(ServiceHistoryActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {

                    if (serviceHistory.getService() == null) {
                        SnackBar.makeText(ServiceHistoryActivity.this, "No records found", SnackBar.LENGTH_SHORT).show();
                    } else {
                        LinearLayoutManager layoutHistoryManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                        serviceOrder.setLayoutManager(layoutHistoryManager);
                        ServiceHistoryAdapter serviceHistoryAdapter = new ServiceHistoryAdapter(this, serviceHistory);
                        serviceOrder.setAdapter(serviceHistoryAdapter);


                    }
                }
            }
        }
    }


    private void initialiseView(View v, final Context context) {
    }


    public class ServiceHistoryAdapter extends RecyclerView.Adapter<ServiceHistoryAdapter.ViewHolder> {

        //Variables
        private Context mcontext;
        ServiceHistory serviceHistory;

        public ServiceHistoryAdapter(Context context, ServiceHistory serviceHistory) {
            this.mcontext = context;
            this.serviceHistory = serviceHistory;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_history, parent, false);
            return new ViewHolder(view);
        }

        @Override

        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.customerName.setText(serviceHistory.getService().get(position).getName());
            holder.customerMob.setText(serviceHistory.getService().get(position).getPhone());
            holder.orderId.setText("Order ID: " + serviceHistory.getService().get(position).getId());
            holder.orderDate.setText(serviceHistory.getService().get(position).getBookDate());
            holder.orderTime.setText(serviceHistory.getService().get(position).getBookTime());
            holder.category.setText(serviceHistory.getService().get(position).getCategory());


        }

        @Override
        public int getItemCount() {
            return serviceHistory.getService().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView customerName, customerMob, orderId, orderTime, orderDate, category;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                customerName = (TextView) itemView.findViewById(R.id.customerName);
                customerMob = (TextView) itemView.findViewById(R.id.customerMob);
                orderId = (TextView) itemView.findViewById(R.id.orderId);
                orderTime = (TextView) itemView.findViewById(R.id.orderTime);
                orderDate = (TextView) itemView.findViewById(R.id.orderDate);
                category = (TextView) itemView.findViewById(R.id.category);

            }
        }
    }


}
