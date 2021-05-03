package com.geekhive.foodey.Grocery.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteList>favoriteLists;
    Context context;

    public FavoriteAdapter(List<FavoriteList> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


      FavoriteList fl=favoriteLists.get(i);
        String url = "http://bado.dsoftveda.com/img/grocery/"+fl.getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(viewHolder.img);
        }
        viewHolder.tv.setText(fl.getName());
        viewHolder.ftv_quantiy.setText(fl.getQuantity());
        viewHolder.ftv_price.setText(fl.getPrice());
        Typeface CustomCut = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-SemiBold.ttf");
        viewHolder.ftv_mrp.setText("MRP : Rs."+fl.getMrp());
        viewHolder.ftv_mrp.setTypeface(CustomCut);
        viewHolder.ftv_mrp.setPaintFlags(viewHolder.ftv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv,ftv_quantiy,ftv_price,ftv_mrp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.fimg_pr);
            tv=(TextView)itemView.findViewById(R.id.ftv_name);
            ftv_quantiy = (TextView)itemView.findViewById(R.id.ftv_quantiy);
            ftv_price = (TextView)itemView.findViewById(R.id.ftv_price);
            ftv_mrp = (TextView)itemView.findViewById(R.id.ftv_mrp);
        }
    }
}
