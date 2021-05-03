package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.activities.CakeShopStoreActivity;
import com.geekhive.foodey.Cakes.activities.StoreListByCategory;
import com.geekhive.foodey.Cakes.beans.cakecategories.CakeAllCategores;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class SpeciallistAdapter extends RecyclerView.Adapter<SpeciallistAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    String url = "";
    CakeAllCategores cakeAllCategores;

    public SpeciallistAdapter(Context context,CakeAllCategores cakeAllCategores) {
        this.mcontext = context;
        this.cakeAllCategores = cakeAllCategores;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.special_appater, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

      //  url = WebServices.Foodey_Grocery_Image_URL + bestSellingProduct.getCakeList().getCake().get(position).getImage();
        holder.categoryTitle.setText(cakeAllCategores.getCakeCategory().get(position).getCategoryType());
        //holder.tv_price.setText("₹ "+bestSellingProduct.getCakeList().getCake().get(position).getPrice());
        if(cakeAllCategores.getCakeCategory().get(position).getCategoryType().equals("Normal Cake")){
            Picasso.get()
                    .load(R.drawable.normalcake)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.categoryImage);
        }if(cakeAllCategores.getCakeCategory().get(position).getCategoryType().equals("Cool Cake")){
            Picasso.get()
                    .load(R.drawable.coolcake)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.categoryImage);
        }
        if(cakeAllCategores.getCakeCategory().get(position).getCategoryType().equals("Spl Design Cake")){
            Picasso.get()
                    .load(R.drawable.splcake)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.categoryImage);
        }if(cakeAllCategores.getCakeCategory().get(position).getCategoryType().equals("Flowers")){
            Picasso.get()
                    .load(R.drawable.flowers)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.categoryImage);
        }if(cakeAllCategores.getCakeCategory().get(position).getCategoryType().equals("Other")){
            Picasso.get()
                    .load(R.drawable.others)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.categoryImage);
        }
        holder.cat_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CakeShopStoreActivity.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
       return cakeAllCategores.getCakeCategory().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryTitle,tv_price;
        RelativeLayout cat_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.iv_category);
            categoryTitle = (TextView) itemView.findViewById(R.id.tv_categoryText);
            cat_name = (RelativeLayout)itemView.findViewById(R.id.cat_name);
        }
    }
}
