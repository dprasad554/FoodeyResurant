package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.activities.GroceryDetailActivity;
import com.geekhive.foodey.Grocery.activities.GroceryShopProductListActivity;
import com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList;
import com.geekhive.foodey.Grocery.beans.groceryproductlist.GrocerySubProductList;
import com.geekhive.foodey.Grocery.beans.shoppinglist.ShoppingList;
import com.geekhive.foodey.Grocery.custom.SnackBar;
import com.geekhive.foodey.Grocery.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GroceryProductListRecentAdapter extends RecyclerView.Adapter<GroceryProductListRecentAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GrocerySubProductList subProductList;
    int cont=0;

    public GroceryProductListRecentAdapter(Context context, GrocerySubProductList subProductList) {
        this.mcontext = context;
        this.subProductList = subProductList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_recentpurchaselist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.productName.setText(subProductList.getGrocery().get(position).getProductName());
        Typeface CustomCut = Typeface.createFromAsset(mcontext.getAssets(), "fonts/OpenSans-SemiBold.ttf");
        holder.productQty.setText("Quantity :"+subProductList.getGrocery().get(position).getQuantity()+
                subProductList.getGrocery().get(position).getQuantityDetail());
        holder.originalPrice.setText("MRP :"+" Rs."+subProductList.getGrocery().get(position).getMrp());
        holder.originalPrice.setTypeface(CustomCut);
        holder.originalPrice.setPaintFlags(holder.originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.savePrice.setText("Rs. "+subProductList.getGrocery().get(position).getPrice());
        holder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, GroceryDetailActivity.class);
                intent.putExtra("title",subProductList.getGrocery().get(position).getProductName());
                intent.putExtra("id",subProductList.getGrocery().get(position).getId());
                intent.putExtra("price",subProductList.getGrocery().get(position).getPrice());
                intent.putExtra("description",subProductList.getGrocery().get(position).getProductDescription());
                intent.putExtra("mrp",subProductList.getGrocery().get(position).getMrp());
                intent.putExtra("quantity",subProductList.getGrocery().get(position).getQuantity());
                intent.putExtra("store_id",subProductList.getGrocery().get(position).getStoreId());
                intent.putExtra("product_id",subProductList.getGrocery().get(position).getId());
                intent.putExtra("url",subProductList.getGrocery().get(position).getImage());
                mcontext.startActivity(intent);
            }
        });
        String url = WebServices.Foodey_Grocery_Image +
                subProductList.getGrocery().get(position).getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.productImage);
        }
        if (GroceryShopProductListActivity.favoriteDatabase.favoriteDao().isFavorite(subProductList.getGrocery().get(position).getId()) == 1) {
            holder.fav_btn.setImageResource(R.drawable.ic_favorite);
        }else {
            holder.fav_btn.setImageResource(R.drawable.heart);
        }


        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoriteList favoriteList = new FavoriteList();

                String id = subProductList.getGrocery().get(position).getId();
                String image = subProductList.getGrocery().get(position).getImage();
                String name = subProductList.getGrocery().get(position).getProductName();
                String quantity = subProductList.getGrocery().get(position).getQuantity();
                String quantity_details = subProductList.getGrocery().get(position).getQuantityDetail();
                String price = subProductList.getGrocery().get(position).getPrice();
                String mrp = subProductList.getGrocery().get(position).getMrp();

                favoriteList.setId(id);
                favoriteList.setImage(image);
                favoriteList.setName("Name :"+name);
                favoriteList.setQuantity("Quantity :"+quantity+quantity_details);
                favoriteList.setPrice(price);
                favoriteList.setMrp(mrp);


                if (GroceryShopProductListActivity.favoriteDatabase.favoriteDao().isFavorite(id) != 1) {
                    holder.fav_btn.setImageResource(R.drawable.ic_favorite);
                    GroceryShopProductListActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
                    SnackBar.makeText(mcontext, "Item added into favorite list", SnackBar.LENGTH_SHORT).show();

                } else {
                    holder.fav_btn.setImageResource(R.drawable.heart);
                    GroceryShopProductListActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
                    SnackBar.makeText(mcontext, "Item removed from favorite list", SnackBar.LENGTH_SHORT).show();
                }
            }
        });
        holder.vT_add_to_shoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingList shoppingList = new ShoppingList();

                String id = subProductList.getGrocery().get(position).getId();
                String image = subProductList.getGrocery().get(position).getImage();
                String name = subProductList.getGrocery().get(position).getProductName();
                String quantity = subProductList.getGrocery().get(position).getQuantity();
                String quantity_details = subProductList.getGrocery().get(position).getQuantityDetail();
                String price = subProductList.getGrocery().get(position).getPrice();
                String mrp = subProductList.getGrocery().get(position).getMrp();

                shoppingList.setId(id);
                shoppingList.setImage(image);
                shoppingList.setName("Name :"+name);
                shoppingList.setQuantity("Quantity :"+quantity+quantity_details);
                shoppingList.setPrice(price);
                shoppingList.setMrp(mrp);


                if (GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().isShoppingList(id) != 1) {
                   // holder.fav_btn.setImageResource(R.drawable.ic_favorite);
                    GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().addData(shoppingList);
                    SnackBar.makeText(mcontext, "Item added into shopping list", SnackBar.LENGTH_SHORT).show();

                } else {
                    //holder.fav_btn.setImageResource(R.drawable.heart);
                    GroceryShopProductListActivity.shoppingListDatabase.shoppingListDao().delete(shoppingList);
                    SnackBar.makeText(mcontext, "Item removed from shopping list", SnackBar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subProductList.getGrocery().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productQty,originalPrice,savePrice,vT_add;
        ImageView productImage,fav_btn;
        CardView vC_adhg_card;
        TextView vT_add_to_shoping;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tv_productName);
            productQty = itemView.findViewById(R.id.tv_productQTY);
            originalPrice = itemView.findViewById(R.id.tv_productPriceOriginal);
            savePrice = itemView.findViewById(R.id.tv_productPriceSave);
//            productDescription = itemView.findViewById(R.id.tv_productDescription);
            productImage = itemView.findViewById(R.id.iv_productImage);
            vC_adhg_card = itemView.findViewById(R.id.vC_adhg_card);
            vT_add_to_shoping = itemView.findViewById(R.id.vT_add_to_shoping);
            fav_btn = itemView.findViewById(R.id.fav_btn);
        }
    }
}
