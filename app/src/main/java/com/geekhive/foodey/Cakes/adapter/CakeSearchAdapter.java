package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.activities.CakeDetailActivity;
import com.geekhive.foodey.Cakes.beans.cakesearchdetails.CakeSearchDetails;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class CakeSearchAdapter extends RecyclerView.Adapter<CakeSearchAdapter.ViewHolder>{
    //Variables
    private Context mcontext;
    CakeSearchDetails cakeSearchDetails;
    int minteger = 0;

    public CakeSearchAdapter(Context context,CakeSearchDetails cakeSearchDetails) {
        this.mcontext = context;
        this.cakeSearchDetails = cakeSearchDetails;
    }

    @NonNull
    @Override
    public CakeSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_searchlist_adapter, parent, false);
        return new CakeSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CakeSearchAdapter.ViewHolder holder, final int position) {
        String url = "http://foodeyservices.com/img/cake/"+
                cakeSearchDetails.getCake().get(position).getCake().getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.productImage);
        }
        holder.groceryName.setText(cakeSearchDetails.getCake().get(position).getCake().getProductName());
        holder.groceryPriceSave.setText("Rs. "+cakeSearchDetails.getCake().get(position).getCake().getPrice());
        holder.groceryPriceCut.setText("Mrp. "+cakeSearchDetails.getCake().get(position).getCake().getMrp());
        holder.productQTY.setText("Quantity: "+cakeSearchDetails.getCake().get(position).getCake().getQuantity());
        holder.linearProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CakeDetailActivity.class);
                intent.putExtra("title",cakeSearchDetails.getCake().get(position).getCake().getProductName());
                intent.putExtra("id",cakeSearchDetails.getCake().get(position).getCake().getId());
                intent.putExtra("price",cakeSearchDetails.getCake().get(position).getCake().getPrice());
                intent.putExtra("description",cakeSearchDetails.getCake().get(position).getCake().getProductDescription());
                intent.putExtra("mrp",cakeSearchDetails.getCake().get(position).getCake().getMrp());
                intent.putExtra("quantity",cakeSearchDetails.getCake().get(position).getCake().getQuantity());
                intent.putExtra("store_id",cakeSearchDetails.getCake().get(position).getCake().getStoreId());
                intent.putExtra("product_id","");
                intent.putExtra("url","http://foodeyservices.com/img/cake/"+cakeSearchDetails.getCake().get(position).getCake().getImage());
                intent.putExtra("category",cakeSearchDetails.getCake().get(position).getCake().getProductCategory());
                //intent.putExtra("lati", cakeSearchDetails.getCake().get(position).getCake().get);
                //intent.putExtra("longi", productlist.getCake().get(position).getLongitude());

                mcontext.startActivity(intent);
            }
        });

        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.increaseInteger(view);
            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.decreaseInteger(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cakeSearchDetails.getCake().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView groceryName, groceryNameTag, groceryPriceSave, groceryPriceCut, productQTY;
        Button increase, decrease;
        LinearLayout linearProductDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.iv_productImage);
            groceryName = itemView.findViewById(R.id.tv_productName);
            groceryNameTag = itemView.findViewById(R.id.tv_productNameTag);
            groceryPriceSave = itemView.findViewById(R.id.tv_productPriceSave);
            groceryPriceCut = itemView.findViewById(R.id.tv_productPriceCut);
            groceryPriceCut.setPaintFlags(groceryPriceCut.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            increase = itemView.findViewById(R.id.btn_increase);
            decrease = itemView.findViewById(R.id.btn_decrease);
            productQTY = itemView.findViewById(R.id.tv_productQTY);
            linearProductDetail = itemView.findViewById(R.id.linearProductList);
        }

        public void increaseInteger(View view) {
            minteger = minteger + 1;
            display(minteger);
        }

        public void decreaseInteger(View view) {
            if (minteger != 0) {
                minteger = minteger - 1;
                display(minteger);
            }
        }

        private void display(int number) {
            TextView displayInteger = (TextView) itemView.findViewById(R.id.tv_integerNumber);
            displayInteger.setText(String.valueOf(number));
        }
    }
}

