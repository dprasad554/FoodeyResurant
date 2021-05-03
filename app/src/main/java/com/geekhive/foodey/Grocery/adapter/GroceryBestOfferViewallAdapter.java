package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.R;

import java.util.ArrayList;

public class GroceryBestOfferViewallAdapter extends RecyclerView.Adapter<GroceryBestOfferViewallAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    ArrayList<Integer> groceryImages;
    ArrayList<String> groceryTitle;

    public GroceryBestOfferViewallAdapter(Context context, ArrayList<Integer> groceryImages, ArrayList<String> groceryTitle ) {
        this.mcontext = context;
        this.groceryImages = groceryImages;
        this.groceryTitle = groceryTitle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_layout_bestoffer_viewall,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.vegImage.setImageResource(groceryImages.get(position));
        holder.groceryOriginal.setText(groceryTitle.get(position));
       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, GroceryDetailActivity.class);
                intent.putExtra("product_name",groceryTitle.get(position));
                mcontext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return groceryImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView vegImage;
        TextView groceryTitle,groceryOriginal;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vegImage = (ImageView)itemView.findViewById(R.id.iv_bestImage);
            groceryTitle = itemView.findViewById(R.id.tv_productTitle);
            groceryOriginal = itemView.findViewById(R.id.tv_productOriginals);
            groceryOriginal.setText("Rs 200");
            groceryOriginal.setPaintFlags(groceryOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //cardView = (CardView)itemView.findViewById(R.id.card_viewMain);
        }
    }
}
