package com.geekhive.foodey.Grocery.adapter;//package com.geekhive.grocery.adapter;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Paint;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.geekhive.grocery.R;
//import com.geekhive.grocery.activites.GroceryDetailActivity;
//import com.geekhive.grocery.beans.storesubproductlist.StoreSubProductList;
//
//import java.util.ArrayList;
//
//public class StoreCategoryListAdapter extends RecyclerView.Adapter<StoreCategoryListAdapter.ViewHolder> {
//
//    //Variables
//    private Context mcontext;
//    StoreSubProductList storeSubProductList;
//
//    int minteger = 0;
//    String category;
//    String quantity_;
//
//    public StoreCategoryListAdapter(Context context, StoreSubProductList storeSubProductList, String category) {
//        this.mcontext = context;
//        this.storeSubProductList = storeSubProductList;
//        this.category = category;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_recentpurchaselist, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        holder.groceryName.setText(storeSubProductList.getGrocery().get(position).getProductName());
//        holder.groceryNameTag.setText(category);
//        holder.groceryPriceSave.setText(storeSubProductList.getGrocery().get(position).getMrp());
//        holder.groceryPriceCut.setText(storeSubProductList.getGrocery().get(position).getPrice());
//        quantity_ = storeSubProductList.getGrocery().get(position).getQuantity()+storeSubProductList.getGrocery().get(position).getQuantityDetail();
////        holder.productImage.setImageResource(groceryImages.get(position));
//        holder.productImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mcontext,GroceryDetailActivity.class);
//                intent.putExtra("title",storeSubProductList.getGrocery().get(position).getProductName());
//                intent.putExtra("id",storeSubProductList.getGrocery().get(position).getId());
//                intent.putExtra("price",storeSubProductList.getGrocery().get(position).getPrice());
//                intent.putExtra("description",storeSubProductList.getGrocery().get(position).getProductDescription());
//                intent.putExtra("mrp",storeSubProductList.getGrocery().get(position).getMrp());
//                intent.putExtra("quantity",quantity_);
//                //intent.putExtra("")
//                mcontext.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return storeSubProductList.getGrocery().size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView productImage;
//        TextView groceryName, groceryNameTag, groceryPriceSave, groceryPriceCut, productQTY;
//        LinearLayout linearProductDetail;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            productImage = (ImageView) itemView.findViewById(R.id.iv_productImage);
//            groceryName = itemView.findViewById(R.id.tv_productName);
//            groceryNameTag = itemView.findViewById(R.id.tv_productNameTag);
//            groceryPriceSave = itemView.findViewById(R.id.tv_productPriceSave);
//            groceryPriceCut = itemView.findViewById(R.id.tv_productPriceCut);
//            groceryPriceCut.setText("Rs 200");
//            groceryPriceCut.setPaintFlags(groceryPriceCut.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            productQTY = itemView.findViewById(R.id.tv_productQTY);
//            linearProductDetail = itemView.findViewById(R.id.linearProductList);
//
//        }
//
//        public void increaseInteger(View view) {
//            minteger = minteger + 1;
//            display(minteger);
//        }
//
//        public void decreaseInteger(View view) {
//            if (minteger != 0) {
//                minteger = minteger - 1;
//                display(minteger);
//            }
//        }
//
//        private void display(int number) {
//            TextView displayInteger = (TextView) itemView.findViewById(R.id.tv_integerNumber);
//            displayInteger.setText(String.valueOf(number));
//        }
//    }
//}
