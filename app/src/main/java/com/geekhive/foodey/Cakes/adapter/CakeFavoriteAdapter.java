package com.geekhive.foodey.Cakes.adapter;

import androidx.annotation.NonNull;

public class CakeFavoriteAdapter /*extends RecyclerView.Adapter<CakeFavoriteAdapter.ViewHolder>*/ {
   /* private List<CakeFavoriteList>favoriteLists;
    Context context;

    public CakeFavoriteAdapter(List<CakeFavoriteList> favoriteLists, Context context) {
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


        CakeFavoriteList fl=favoriteLists.get(i);
        String url = "http://bado.dsoftveda.com/img/cake/"+fl.getImage();
        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(viewHolder.img);
        }
        viewHolder.tv.setText(fl.getName());
        viewHolder.ftv_quantiy.setText(fl.getQuantity());
        viewHolder.ftv_price.setText("Rs . "+fl.getPrice());
        Typeface CustomCut = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-SemiBold.ttf");
        viewHolder.ftv_mrp.setText("MRP : Rs."+fl.getMrp());
        viewHolder.ftv_mrp.setTypeface(CustomCut);
        viewHolder.ftv_mrp.setPaintFlags(viewHolder.ftv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        if (CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().isFavorite(favoriteLists.get(i).getId()) == 1) {
            viewHolder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
        }else {
            viewHolder.iv_fav_btn.setImageResource(R.drawable.heart);
        }
        viewHolder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CakeFavoriteList favoriteList = new CakeFavoriteList();

                String id = favoriteLists.get(i).getId();
                String image = favoriteLists.get(i).getImage();
                String name = favoriteLists.get(i).getName();
                String quantity = favoriteLists.get(i).getQuantity();
                String price = favoriteLists.get(i).getPrice();
                String mrp = favoriteLists.get(i).getMrp();

                favoriteList.setId(id);
                favoriteList.setImage(image);
                favoriteList.setName("Name :"+name);
                favoriteList.setQuantity("Quantity :"+quantity);
                favoriteList.setPrice(price);
                favoriteList.setMrp(mrp);
                if (CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().isFavorite(id) != 1) {
                    viewHolder.iv_fav_btn.setImageResource(R.drawable.ic_favorite);
                    CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
                    SnackBar.makeText(context, "Item added into favorite list", SnackBar.LENGTH_SHORT).show();

                } else {
                    viewHolder.iv_fav_btn.setImageResource(R.drawable.heart);
                    CakeShopSubCategoryActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
                    Intent intentg = new Intent(context, CakeFavoriteListActivity.class);
                    context.startActivity(intentg);
                    //SnackBar.makeText(context, "Item removed from favorite list", SnackBar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img,iv_fav_btn;
        TextView tv,ftv_quantiy,ftv_price,ftv_mrp;
        LinearLayout fav_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.fimg_pr);
            tv=(TextView)itemView.findViewById(R.id.ftv_name);
            ftv_quantiy = (TextView)itemView.findViewById(R.id.ftv_quantiy);
            ftv_price = (TextView)itemView.findViewById(R.id.ftv_price);
            ftv_mrp = (TextView)itemView.findViewById(R.id.ftv_mrp);
            fav_btn = (LinearLayout) itemView.findViewById(R.id.fav_btn);
            iv_fav_btn = (ImageView)itemView.findViewById(R.id.iv_fav_btn);
        }
    }*/
}
