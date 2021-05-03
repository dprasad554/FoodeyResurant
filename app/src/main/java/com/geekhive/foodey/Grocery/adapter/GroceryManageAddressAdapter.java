package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekhive.foodey.R;

import java.util.ArrayList;

public class GroceryManageAddressAdapter extends RecyclerView.Adapter<GroceryManageAddressAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    ArrayList<String> addressManage;

    public GroceryManageAddressAdapter(Context context, ArrayList<String> addressManage) {
        this.mcontext = context;
        this.addressManage = addressManage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_manageaddress,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
     holder.address.setText(addressManage.get(position));
    }

    @Override
    public int getItemCount() {
        return addressManage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address = (TextView)itemView.findViewById(R.id.tv_address);
        }
    }
}
