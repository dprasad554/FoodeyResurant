package com.geekhive.foodey.Cakes.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.beans.cakeaddress.CakeAddressList;
import com.geekhive.foodey.Cakes.beans.cakechooseaddress.CakeChooseAddress;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeManageAddressActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;


    AddressAdapter addressAdapter;
    @BindView(R.id.vI_ama_back)
    ImageView vIAmaBack;
    @BindView(R.id.vT_ama_manage)
    TextView vTAmaManage;
    @BindView(R.id.vT_ama_saved)
    TextView vTAmaSaved;
    @BindView(R.id.vR_ama_list)
    RecyclerView vRAmaList;
    @BindView(R.id.vT_ama_add)
    TextView vTAmaAdd;

    ConnectionDetector mDetector;

    CakeAddressList addressList;
    String order_id;
    String address;
    String dis;
    //String addressSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_manage_address);
        ButterKnife.bind(this);
        dis = getIntent().getStringExtra("distance");
        initializeFonts();
        setTypeFace();
        setvalues();
        CallService();
    }

    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getAssets(), "NIRMALAS.TTF");
    }

    private void setTypeFace() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                vTAmaManage.setTypeface(NIRMALAB);
                vTAmaSaved.setTypeface(NIRMALAB);
                vTAmaAdd.setTypeface(NIRMALAB);


            }
        };
        r.run();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        //TODO Add the user id at Checkout activity through intent
        order_id = getIntent().getStringExtra("order_id");
        vIAmaBack.setOnClickListener(this);
        vTAmaAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_ama_back:
                finish();
                break;
            case R.id.vT_ama_add:
                Intent intent=new Intent(this, CakeAddAddressActivity.class);
                intent.putExtra("order_id", order_id);
                startActivity(intent);
                overridePendingTransition(0,0);
                CakeManageAddressActivity.this.finish();
                break;
        }
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
                new WebServices(this).AddressList(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.addressList, Prefs.getUserId(this));
            return;
        }

    }

    private void CallChooseService(String addressId) {
        if (this.mDetector.isConnectingToInternet()) {

                new WebServices(this).SelectAddress(WebServices.Foodey_Cakelocation_Services,
                        WebServices.ApiType.selectaddress, Prefs.getUserId(this), addressId);
            } else {

                SnackBar.makeText(CakeManageAddressActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            }

            return;
    }


    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {
        if (apiType == WebServices.ApiType.addressList) {
            if (!isSucces) {
                SnackBar.makeText(CakeManageAddressActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                addressList = (CakeAddressList) response;
                if(addressList.getAddress() != null){
                    addressAdapter = new AddressAdapter();
                    vRAmaList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    vRAmaList.setAdapter(addressAdapter);
                } else {
                    SnackBar.makeText(this, addressList.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.selectaddress) {
            if (!isSucces) {
                SnackBar.makeText(CakeManageAddressActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeChooseAddress chooseAddress = (CakeChooseAddress) response;
                if (chooseAddress != null){
                    Intent intent = new Intent(CakeManageAddressActivity.this, CakeCheckOutActivityFinal.class);
                    intent.putExtra("address", address);
                    intent.putExtra("distance", dis);
                    startActivity(intent);
                    CakeManageAddressActivity.this.finish();
                }
            }
        }
    }

    public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_addresses, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            address = addressList.getAddress().get(position).getHouse() + ", "
                    + addressList.getAddress().get(position).getApartment() + ", "
                    + addressList.getAddress().get(position).getStreet() + ", "
                    + addressList.getAddress().get(position).getLandmark() + ", "
                    + addressList.getAddress().get(position).getArea() + ", "
                    + addressList.getAddress().get(position).getCity() + ", "
                    + addressList.getAddress().get(position).getState() + ", "
                    + addressList.getAddress().get(position).getCountry() + ", "
                    + addressList.getAddress().get(position).getPincode();
            holder.vT_aad_deliveryadressval.setText(address);

            holder.vL_aad_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    address = addressList.getAddress().get(position).getHouse() + ", "
                            + addressList.getAddress().get(position).getApartment() + ", "
                            + addressList.getAddress().get(position).getStreet() + ", "
                            + addressList.getAddress().get(position).getLandmark() + ", "
                            + addressList.getAddress().get(position).getArea() + ", "
                            + addressList.getAddress().get(position).getCity() + ", "
                            + addressList.getAddress().get(position).getState() + ", "
                            + addressList.getAddress().get(position).getCountry() + ", "
                            + addressList.getAddress().get(position).getPincode();
                    CallChooseService(addressList.getAddress().get(position).getId());
                }
            });

        }

        public int getItemCount() {
            return addressList.getAddress().size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView vI_aad_image;
            LinearLayout vL_aad_main;
            TextView vT_aad_header, vT_aad_edit,vT_aad_delete,vT_aad_deliveryadressval;

            public MyViewHolder(View view) {
                super(view);
                this.vL_aad_main = (LinearLayout) view.findViewById(R.id.vL_aad_main);
                this.vI_aad_image = (ImageView) view.findViewById(R.id.vI_aad_image);
                this.vT_aad_header = (TextView) view.findViewById(R.id.vT_aad_header);
                this.vT_aad_edit = (TextView) view.findViewById(R.id.vT_aad_edit);
                this.vT_aad_delete = (TextView) view.findViewById(R.id.vT_aad_delete);
                this.vT_aad_deliveryadressval = (TextView) view.findViewById(R.id.vT_aad_deliveryadressval);

                new Runnable() {
                    public void run() {
                        vT_aad_header.setTypeface(NIRMALAB);
                        vT_aad_edit.setTypeface(NIRMALAB);
                        vT_aad_delete.setTypeface(NIRMALAB);
                        vT_aad_deliveryadressval.setTypeface(NIRMALA);

                    }
                }.run();
            }
        }
    }
}

