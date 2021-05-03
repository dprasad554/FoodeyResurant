package com.geekhive.foodey.Food.service;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekhive.foodey.Food.form.FormActivity;
import com.geekhive.foodey.R;

import java.util.ArrayList;

public class CommercialServiceAdapter extends RecyclerView.Adapter<CommercialServiceAdapter.ViewHolder> {

    //Variables
    private Context context;
    ArrayList<String> serviceNames;
    ArrayList<Integer> serviceImages;


    public CommercialServiceAdapter(Context context, ArrayList<Integer> serviceImages, ArrayList<String> serviceNames) {
        this.context = context;
        this.serviceImages = serviceImages;
        this.serviceNames = serviceNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_service,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.image.setImageResource(serviceImages.get(position));
        holder.salon.setText(serviceNames.get(position));
        String ItemName = holder.salon.getText().toString();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FormActivity.class);
                intent.putExtra("item",ItemName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView salon;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.service_Image);
            salon = (TextView)itemView.findViewById(R.id.tv_servicesList);
            linearLayout = itemView.findViewById(R.id.linearServices);
        }
    }
}
