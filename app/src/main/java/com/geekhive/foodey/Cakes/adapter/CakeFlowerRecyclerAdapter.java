package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.activities.CakeDetailActivity;
import com.geekhive.foodey.Cakes.activities.CakeShopSubCategoryActivity;
import com.geekhive.foodey.Cakes.beans.cakefavoritelist.CakeFavoriteList;
import com.geekhive.foodey.Cakes.beans.cakeproductlist.CakeProductlist;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class CakeFlowerRecyclerAdapter extends RecyclerView.Adapter<CakeFlowerRecyclerAdapter.ViewHolder> {
    //Variables
    private Context mcontext;
    CakeProductlist productlist;
    String url = "";

    public CakeFlowerRecyclerAdapter(Context context, CakeProductlist productlist) {
        this.productlist = productlist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_cakeslider,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text1.setText(productlist.getCake().get(position).getProductName());
        holder.text.setText("MRP Rs."+productlist.getCake().get(position).getPrice());
        if(productlist.getCake().get(position).getImage() != null) {
            url = WebServices.Foodey_Grocery_Image_URL + productlist.getCake().get(position).getImage();
        }
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.image);
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CakeDetailActivity.class);
                intent.putExtra("cakeName", productlist.getCake().get(position).getProductName());
                intent.putExtra("quantity", productlist.getCake().get(position).getQuantity()+
                        productlist.getCake().get(position).getQuantityDetail());
                intent.putExtra("price", productlist.getCake().get(position).getPrice());
                intent.putExtra("description", productlist.getCake().get(position).getProductDescription());
                intent.putExtra("url",WebServices.Foodey_Grocery_Image_URL + productlist.getCake().get(position).getImage());
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
        if (CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().isFavorite(productlist.getCake().get(position).getId()) == 1) {
            holder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.iv_fav_btn.setImageResource(R.drawable.heart);
        }
        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CakeFavoriteList favoriteList = new CakeFavoriteList();

                String id = productlist.getCake().get(position).getId();
                String image = productlist.getCake().get(position).getImage();
                String name = productlist.getCake().get(position).getProductName();
                String quantity = productlist.getCake().get(position).getQuantity();
                String quantity_details = productlist.getCake().get(position).getQuantityDetail();
                String price = productlist.getCake().get(position).getPrice();
                String mrp = productlist.getCake().get(position).getMrp();
                String storeid = productlist.getCake().get(position).getStoreId();

                favoriteList.setId(id);
                favoriteList.setImage(image);
                favoriteList.setName("Name :" + name);
                favoriteList.setQuantity("Quantity :" + quantity + quantity_details);
                favoriteList.setPrice(price);
                favoriteList.setMrp(mrp);
                favoriteList.setStoreid(storeid);
                if (CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().isFavorite(id) != 1) {
                    holder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
                    CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
                    SnackBar.makeText(mcontext, "Item added into favorite list", SnackBar.LENGTH_SHORT).show();

                } else {
                    holder.iv_fav_btn.setImageResource(R.drawable.heart);
                    CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
                    SnackBar.makeText(mcontext, "Item removed from favorite list", SnackBar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productlist.getCake().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image,iv_fav_btn;
        TextView text,text1;
        LinearLayout fav_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.myImageView1);
            text1 = (TextView)itemView.findViewById(R.id.myTextView2);
            text = (TextView)itemView.findViewById(R.id.myTextView1);
            fav_btn = (LinearLayout)itemView.findViewById(R.id.fav_btn);
            iv_fav_btn = (ImageView)itemView.findViewById(R.id.iv_fav_btn);

        }
    }
}
