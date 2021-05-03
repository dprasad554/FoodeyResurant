package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.activities.CakeDetailActivity;
import com.geekhive.foodey.Cakes.beans.cakeproductlist.CakeProductlist;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class CakeSpecialListRecyclerAdapter extends RecyclerView.Adapter<CakeSpecialListRecyclerAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    CakeProductlist productlist;
    String url = "";


    public CakeSpecialListRecyclerAdapter(Context context, CakeProductlist productlist) {
        this.mcontext = context;
        this.productlist = productlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_specialcake,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.cakeName.setText(productlist.getCake().get(position).getProductName());
        holder.tv_shopTime.setText("Rs."+productlist.getCake().get(position).getPrice());
        holder.tv_quantity.setText("Quantity :"+productlist.getCake().get(position).getQuantity()+
                productlist.getCake().get(position).getQuantityDetail());
        if(productlist.getCake().get(position).getImage() != null) {
            url = WebServices.Foodey_Grocery_Image_URL + productlist.getCake().get(position).getImage();
        }
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.cakeImg);
        }
     holder.cakeImg.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(mcontext, CakeDetailActivity.class);
             intent.putExtra("cakeName", productlist.getCake().get(position).getProductName());
             intent.putExtra("quantity", productlist.getCake().get(position).getQuantity()+" "+
                     productlist.getCake().get(position).getQuantityDetail());
             intent.putExtra("price", productlist.getCake().get(position).getPrice());
             intent.putExtra("description", productlist.getCake().get(position).getProductDescription());
             intent.putExtra("url",url);
             intent.putExtra("mrp", productlist.getCake().get(position).getMrp());
             intent.putExtra("discount", productlist.getCake().get(position).getDiscount());
             intent.putExtra("store_id", productlist.getCake().get(position).getStoreId());
             intent.putExtra("product_id", productlist.getCake().get(position).getId());
             intent.putExtra("category", productlist.getCake().get(position).getProductCategory());
             intent.putExtra("lati", productlist.getCake().get(position).getLatitude());
             intent.putExtra("longi", productlist.getCake().get(position).getLongitude());
             mcontext.startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return productlist.getCake().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cakeImg;
        TextView cakeName;
        TextView tv_shopTime;
        TextView tv_quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeImg = (ImageView)itemView.findViewById(R.id.iv_cakeImg);
            cakeName = (TextView)itemView.findViewById(R.id.tv_cakeName);
            tv_shopTime = (TextView)itemView.findViewById(R.id.tv_shopTime);
            tv_quantity = (TextView)itemView.findViewById(R.id.tv_quantity);
        }
    }
}
