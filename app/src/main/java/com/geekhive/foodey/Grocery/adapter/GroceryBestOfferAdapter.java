package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.beans.groceryoffer.GroceryOffer;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

public class GroceryBestOfferAdapter extends RecyclerView.Adapter<GroceryBestOfferAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    GroceryOffer groceryOffer;

    public GroceryBestOfferAdapter(Context context,GroceryOffer groceryOffer) {
        this.mcontext = context;
        this.groceryOffer = groceryOffer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_bestoffer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
     holder.groceryTitle.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getName());
        //holder.groceryOriginal.setText("MRP : "+groceryOffer.getGroceryOffer().get(position).getGrocery().getMrp());
       // holder.tv_productQty.setText(groceryOffer.getGroceryOffer().get(position).getGrocery().getQuantity()+
               // groceryOffer.getGroceryOffer().get(position).getGrocery().getQuantityDetail());
        //holder.productNows.setText("Rs : "+groceryOffer.getGroceryOffer().get(position).getGrocery().getPrice());
        holder.tv_productCuts.setText(groceryOffer.getOfferDetails().get(position).getGroceryOffer().getDiscount() + " OFF");
        String url = "http://foodeyservices.com/img/grocery/"+
                groceryOffer.getOfferDetails().get(position).getGroceryOffer().getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(holder.vegImage);
        }
    }

    @Override
    public int getItemCount() {
        return groceryOffer.getOfferDetails().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView vegImage;
        TextView groceryTitle,groceryOriginal,tv_productQty,productNows,tv_productCuts;
        CardView cardView;
        Button btn_addCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vegImage = (ImageView)itemView.findViewById(R.id.iv_bestImage);
            groceryTitle = itemView.findViewById(R.id.tv_productTitle);
            groceryOriginal = itemView.findViewById(R.id.tv_productOriginals);
            groceryOriginal.setText("Rs 200");
            groceryOriginal.setPaintFlags(groceryOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            cardView = (CardView)itemView.findViewById(R.id.card_viewMain);
            tv_productQty=itemView.findViewById(R.id.tv_productQty);
                    productNows=itemView.findViewById(R.id.productNows);
            tv_productCuts = itemView.findViewById(R.id.tv_productCuts);
            btn_addCart = itemView.findViewById(R.id.btn_addCart);

        }
    }
}
