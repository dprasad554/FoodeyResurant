package com.geekhive.foodey.Cakes.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhive.foodey.R;

import java.util.ArrayList;

public class CakeRecyclerViewAdapter extends RecyclerView.Adapter<CakeRecyclerViewAdapter.ViewHolder> {

    //Variables
    private Context mcontext;
    ArrayList<String> titlesNames;
    ArrayList<Integer> cakesImages;

    public CakeRecyclerViewAdapter(Context context, ArrayList<String> titlesNames, ArrayList<Integer> cakesImages) {
        this.titlesNames = titlesNames;
        this.cakesImages = cakesImages;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_cakeslider,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.image.setImageResource(cakesImages.get(position));
     holder.text.setText(titlesNames.get(position));
     holder.image.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
//             Intent intent = new Intent(mcontext,Cake_Activity.class);
//             mcontext.startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return titlesNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text,text1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.myImageView1);
            text = (TextView)itemView.findViewById(R.id.myTextView1);
            text1 = (TextView)itemView.findViewById(R.id.myTextView2);

        }
    }
}
