package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.activities.CakeDetailActivity;
import com.geekhive.foodey.Cakes.beans.bestselling.BestSellingProduct;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class CakeBestOfferAdapter extends RecyclerView.Adapter<CakeBestOfferAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    BestSellingProduct bestSellingProduct;
    String url = "";

    public CakeBestOfferAdapter(Context context, BestSellingProduct bestSellingProduct) {
        this.mcontext = context;
        this.bestSellingProduct = bestSellingProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_bestoffer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        url = WebServices.Foodey_Grocery_Image_URL + bestSellingProduct.getCakeList().getCake().get(position).getImage();
        holder.tv_name.setText(bestSellingProduct.getCakeList().getCake().get(position).getProductName());
        holder.price.setText("₹ "+bestSellingProduct.getCakeList().getCake().get(position).getPrice());

        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.vegImage);
        }
        holder.vegImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CakeDetailActivity.class);
                intent.putExtra("cakeName", bestSellingProduct.getCakeList().getCake().get(position).getProductName());
                intent.putExtra("quantity", bestSellingProduct.getCakeList().getCake().get(position).getQuantity()+
                        bestSellingProduct.getCakeList().getCake().get(position).getQuantityDetail());
                intent.putExtra("price", bestSellingProduct.getCakeList().getCake().get(position).getPrice());
                intent.putExtra("description", bestSellingProduct.getCakeList().getCake().get(position).getProductDescription());
                intent.putExtra("url",WebServices.Foodey_Grocery_Image_URL + bestSellingProduct.getCakeList().getCake().get(position).getImage());
                intent.putExtra("mrp", bestSellingProduct.getCakeList().getCake().get(position).getMrp());
                intent.putExtra("discount", bestSellingProduct.getCakeList().getCake().get(position).getDiscount());
                intent.putExtra("store_id", bestSellingProduct.getCakeList().getCake().get(position).getStoreId());
                intent.putExtra("product_id", bestSellingProduct.getCakeList().getCake().get(position).getId());
                intent.putExtra("category", bestSellingProduct.getCakeList().getCake().get(position).getProductCategory());
                intent.putExtra("lati", bestSellingProduct.getCakeList().getCake().get(position).getLatitude());
                intent.putExtra("longi", bestSellingProduct.getCakeList().getCake().get(position).getLongitude());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bestSellingProduct.getCakeList().getCake().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView vegImage;
        TextView price,tv_name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vegImage = (ImageView)itemView.findViewById(R.id.vI_adhg_image);
            price = (TextView) itemView.findViewById(R.id.price);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
