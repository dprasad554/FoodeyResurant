package com.geekhive.foodey.Cakes.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekhive.foodey.Cakes.beans.cakeaddtocart.CakeAddtocart;
import com.geekhive.foodey.Cakes.beans.cakeglobalsearch.Cake;
import com.geekhive.foodey.Cakes.beans.cakeglobalsearch.CakeGlobalSearch;
import com.geekhive.foodey.Cakes.beans.cakeglobalsearch.CakeStore;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cakeglobalsearch extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    public Typeface NIRMALA;
    public Typeface NIRMALAB;
    public Typeface NIRMALAS;

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.vR_g_list)
    RecyclerView vRAsrList;
    String searchName = "";
    ConnectionDetector mDetector;
    CakeGlobalSearch globalSearchRest;
    List<CakeStore> resturantList = new ArrayList<>();
    List<Cake> foodList = new ArrayList<>();
    String price_;
    String mrp;
    String foodId;
    MenuAdapter menuAdapter;
    String res_id;
    Dialog paypopup;
    Integer total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initializeFonts();
        setvalues();

    }

    private void initializeFonts() {
        this.NIRMALA = Typeface.createFromAsset(getAssets(), "NIRMALA.TTF");
        this.NIRMALAB = Typeface.createFromAsset(getAssets(), "NIRMALAB.TTF");
        this.NIRMALAS = Typeface.createFromAsset(getAssets(), "NIRMALAS.TTF");
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        //searchName = getIntent().getStringExtra("searchname");
        searchView.setQueryHint("Search");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setQuery("", true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CallService(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {

    }


    private void CallService(String searchName) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).GlobalSearchService(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.gsearchn, "1", searchName);
        }

    }

    private void CallAddService(String quantity, String resId, String price, String mrp, String foodId, String lat, String lang) {
        if (this.mDetector.isConnectingToInternet()) {

            Double dstn = distance(Double.parseDouble(Prefs.getUserLat(this)), Double.parseDouble(Prefs.getUserLang(this)),
                    Double.parseDouble(lat), Double.parseDouble(lang));
            String distanceAdd = String.valueOf(dstn);
            new WebServices(this).Addtocart(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.addtocart, "1", resId, foodId, quantity, price, mrp,"","","","",false,distanceAdd, Prefs.getCityId(this));

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.gsearchn) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                globalSearchRest = (CakeGlobalSearch) response;
                if (!isSucces || globalSearchRest == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (globalSearchRest.getSearch() != null) {
                        if (globalSearchRest.getSearch().getCake() != null) {
                            if (globalSearchRest.getSearch().getCake().size() != 0) {
                                foodList = new ArrayList<>();
                                foodList.addAll(globalSearchRest.getSearch().getCake());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Cakeglobalsearch.this);
                                vRAsrList.setLayoutManager(linearLayoutManager);
                                menuAdapter = new MenuAdapter();
                                vRAsrList.setAdapter(menuAdapter);
                            }
                        } else if (globalSearchRest.getSearch().getCakeStore() != null) {
                            if (globalSearchRest.getSearch().getCakeStore().size() != 0) {
                                resturantList = new ArrayList<>();
                                resturantList.addAll(globalSearchRest.getSearch().getCakeStore());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Cakeglobalsearch.this);
                                vRAsrList.setLayoutManager(linearLayoutManager);
                                EatOutAdapter eatOutAdapter = new EatOutAdapter();
                                vRAsrList.setAdapter(eatOutAdapter);
                            }
                        }
                    } else {
                        SnackBar.makeText(Cakeglobalsearch.this, "Something went wrong", SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (apiType == WebServices.ApiType.addtocart) {
            if (!isSucces) {
                SnackBar.makeText(Cakeglobalsearch.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                CakeAddtocart cartListOut = (CakeAddtocart) response;
                if (cartListOut != null) {
                    SnackBar.makeText(Cakeglobalsearch.this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                    //menuAdapter.notifyDataSetChanged();
                } else {
                    SnackBar.makeText(Cakeglobalsearch.this, cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class EatOutAdapter extends RecyclerView.Adapter<EatOutAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_eat_out, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_adeot_name.setText(resturantList.get(position).getName());
            holder.vT_adeot_place.setText(resturantList.get(position).getAddress());

            Picasso.get()
                    .load(WebServices.Foodey_Grocery_Image_URL + resturantList.get(position).getImage())
                    .into(holder.vI_adeot_image);

            if (resturantList.get(position).getStatus().equals("1")) {
                holder.vT_adeot_status.setText("Open");
            } else {
                holder.vT_adeot_status.setText("Closed");
            }
           /* String s = resturantList.get(position).getTiming();
            String[] split = s.split("to");
            final String firstSubString = split[0];
            final String secondSubString = split[1];

            try {


                Date mToday = new Date();

                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                String curTime = sdf.format(mToday);
                Date start = null;

                start = sdf.parse(firstSubString);

                Date end = sdf.parse(secondSubString);
                Date userDate = sdf.parse(curTime);

                if (end.before(start)) {
                    Calendar mCal = Calendar.getInstance();
                    mCal.setTime(end);
                    mCal.add(Calendar.DAY_OF_YEAR, 1);
                    end.setTime(mCal.getTimeInMillis());
                }

                Log.d("curTime", userDate.toString());
                Log.d("start", start.toString());
                Log.d("end", end.toString());


                if (userDate.after(start) && userDate.before(end)) {
                    Log.d("result", "open");
                    holder.vT_adeot_status.setText("Open");
                    if (resturantList.get(position).getStatus().equals("1")) {
                        holder.vT_adeot_status.setText("Open");
                    } else {
                        holder.vT_adeot_status.setText("Closed");
                    }
                } else {
                    Log.d("result", "closed");
                    holder.vT_adeot_status.setText("Closed");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

            holder.vR_adeot_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (holder.vT_adeot_status.getText().equals("Open")) {
                        Log.d("result", "open");
                        if (resturantList.get(position).getStatus().equals("1")) {
                            Intent intent = new Intent(Cakeglobalsearch.this, CakeShopSubCategoryActivity.class);
                            intent.putExtra("url",WebServices.Foodey_Grocery_Image_URL+resturantList.get(position).getImage());
                            intent.putExtra("title",resturantList.get(position).getName());
                            intent.putExtra("storeid",resturantList.get(position).getId());
                            intent.putExtra("lati",resturantList.get(position).getLatitude());
                            intent.putExtra("longi",resturantList.get(position).getLatitude());
                            startActivity(intent);
                            Cakeglobalsearch.this.overridePendingTransition(0, 0);
                        } else {
                            SnackBar.makeText(Cakeglobalsearch.this, "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.d("result", "closed");
                        SnackBar.makeText(Cakeglobalsearch.this, "Sorry restaurant is closed!!!", SnackBar.LENGTH_SHORT).show();
                    }
                }
            });

        }

        public int getItemCount() {
            return resturantList.size();//
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout vR_adeot_main;
            ImageView vI_adeot_image;
            TextView vT_adeot_name, vT_adeot_status, vT_adeot_place, vT_adeot_style, vT_adeot_event,
                    vT_adeot_live, vT_adeot_rating;
            View vV_adeot_view;

            public MyViewHolder(View view) {
                super(view);
                this.vR_adeot_main = (RelativeLayout) view.findViewById(R.id.vR_adeot_main);
                this.vI_adeot_image = (ImageView) view.findViewById(R.id.vI_adeot_image);
                this.vT_adeot_name = (TextView) view.findViewById(R.id.vT_adeot_name);
                this.vT_adeot_status = (TextView) view.findViewById(R.id.vT_adeot_status);
                this.vT_adeot_place = (TextView) view.findViewById(R.id.vT_adeot_place);
                this.vT_adeot_style = (TextView) view.findViewById(R.id.vT_adeot_style);
                this.vT_adeot_event = (TextView) view.findViewById(R.id.vT_adeot_event);
                this.vT_adeot_live = (TextView) view.findViewById(R.id.vT_adeot_live);
                this.vT_adeot_rating = (TextView) view.findViewById(R.id.vT_adeot_rating);
                this.vV_adeot_view = view.findViewById(R.id.vV_adeot_view);
                new Runnable() {
                    public void run() {
                        vT_adeot_name.setTypeface(NIRMALAB);
                        vT_adeot_status.setTypeface(NIRMALA);
                        vT_adeot_place.setTypeface(NIRMALA);
                        vT_adeot_style.setTypeface(NIRMALA);
                        vT_adeot_event.setTypeface(NIRMALA);
                        vT_adeot_live.setTypeface(NIRMALA);
                        vT_adeot_rating.setTypeface(NIRMALA);
                    }
                }.run();
            }
        }
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

        public MenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_details_copy, parent, false));
        }

        public void onBindViewHolder(final MenuAdapter.MyViewHolder holder, final int position) {

            if (foodList.get(position).getStatus().equals("1")) {
                holder.qtyM.setVisibility(View.VISIBLE);
                holder.qtyA.setVisibility(View.VISIBLE);
                holder.buttonAdd.setVisibility(View.GONE);
                holder.qty.setVisibility(View.VISIBLE);
                holder.visibleMenu.setVisibility(View.GONE);
                holder.vT_admd_text.setText(foodList.get(position).getProductName());
                mrp = foodList.get(position).getMrp();
                price_ = foodList.get(position).getPrice();

                Log.d("hhhjjj", price_);
                String mrp = foodList.get(position).getMrp();

                //  holder.vT_mrp.setText("MRP :"+mrp);
                String price = foodList.get(position).getPrice();
                holder.vT_price.setText(price);
                if (mrp.equals(price)) {
                    holder.vT_mrp.setVisibility(View.GONE);

                } else {
                    holder.vT_mrp.setText(mrp);
                    holder.vT_mrp.setPaintFlags(holder.vT_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }

                holder.qtyM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(holder.qty.getText().toString()) != 1) {
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            // holder.vL_add_to_cart.setVisibility(View.VISIBLE);

                        } else {
                            holder.vL_add_to_cart.setVisibility(View.VISIBLE);
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            holder.vL_count.setVisibility(View.GONE);


                        }
                    }
                });


                holder.vT_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        holder.vL_count.setVisibility(View.VISIBLE);
                        holder.vL_add_to_cart.setVisibility(View.GONE);
                        int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                        holder.qty.setText(String.valueOf(qaunt));

                        if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                            foodId = foodList.get(position).getId();
                            CallAddService(holder.qty.getText().toString(), foodList.get(position).getStoreId(), foodList.get(position).getPrice(),
                                    foodList.get(position).getMrp(), foodList.get(position).getId(), foodList.get(position).getLatitude(), foodList.get(position).getLongitude());
                        } else {
                            SnackBar.makeText(Cakeglobalsearch.this, "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                        }

                        holder.qtyA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                                holder.qty.setText(String.valueOf(qaunt));
                                foodId = foodList.get(position).getId();
                                CallAddService(holder.qty.getText().toString(), foodList.get(position).getStoreId(), foodList.get(position).getPrice(),
                                        foodList.get(position).getMrp(), foodList.get(position).getId(), foodList.get(position).getLatitude(), foodList.get(position).getLongitude());


                            }
                        });

                    }


                });


                holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                            foodId = foodList.get(position).getId();
                            CallAddService(holder.qty.getText().toString(), foodList.get(position).getStoreId(), foodList.get(position).getPrice(),
                                    foodList.get(position).getMrp(), foodList.get(position).getId(), foodList.get(position).getLatitude(), foodList.get(position).getLongitude());
                            //InitializePaypopup();
                            // initializePopup();
                        } else {
                            SnackBar.makeText(Cakeglobalsearch.this, "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                        }
                    }

                });

            } else {
                holder.vT_admd_text.setText(foodList.get(position).getProductName());
                mrp = foodList.get(position).getMrp();
                price_ = foodList.get(position).getPrice();
                String mrp = "₹ " + foodList.get(position).getMrp();
                String price = "₹ " + foodList.get(position).getPrice();
                String price_t = foodList.get(position).getPrice();

                // holder.vT_mrp.setText(mrp);
                holder.vT_price.setText(price_t);
                holder.qty.setVisibility(View.GONE);
                holder.qtyM.setVisibility(View.GONE);
                holder.qtyA.setVisibility(View.GONE);
                holder.buttonAdd.setVisibility(View.GONE);
                holder.vL_add_to_cart.setVisibility(View.GONE);
                holder.visibleMenu.setVisibility(View.VISIBLE);
                if (mrp.equals(price)) {
                    holder.vT_mrp.setVisibility(View.GONE);

                } else {
                    holder.vT_mrp.setText(mrp);
                    holder.vT_mrp.setPaintFlags(holder.vT_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }
            }

            String url = WebServices.Foodey_Grocery_Image_URL + foodList.get(position).getImage();


            if (!url.equals("")) {
                Picasso.get()
                        .load(url)//download URL
                        .error(R.drawable.foodey_logo_)//if failed
                        .into(holder.food_item_image);
            }


        }

        public int getItemCount() {
            return foodList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            FrameLayout vL_admd_main;
            LinearLayout vL_admd_hide, vL_add_to_cart, vL_count;
            ImageView vI_admd_image;
            TextView vT_admd_text, vT_price, visibleMenu, vT_mrp, vT_add;
            ImageView qtyM, qtyA, food_item_image;
            TextView qty, vT_count;
            Button buttonAdd;

            public MyViewHolder(View view) {
                super(view);
                this.vL_admd_main = (FrameLayout) view.findViewById(R.id.vL_admd_main);
                this.vL_admd_hide = (LinearLayout) view.findViewById(R.id.vL_admd_hide);
                this.vL_add_to_cart = (LinearLayout) view.findViewById(R.id.vL_add_to_cart);
                this.vL_count = (LinearLayout) view.findViewById(R.id.vL_count);

                this.vI_admd_image = (ImageView) view.findViewById(R.id.vI_admd_image);
                this.vT_admd_text = (TextView) view.findViewById(R.id.vT_admd_text);
                this.vT_add = view.findViewById(R.id.vT_add);
                //this.vT_count = view.findViewById(R.id.vT_count);


                this.qty = view.findViewById(R.id.qty);
                this.qtyA = view.findViewById(R.id.qtyA);
                this.qtyM = view.findViewById(R.id.qtyM);
                this.food_item_image = view.findViewById(R.id.food_item_image);
                vT_mrp = view.findViewById(R.id.vT_mrp);
                vT_price = view.findViewById(R.id.vT_price);
                buttonAdd = view.findViewById(R.id.buttonAdd);
                visibleMenu = view.findViewById(R.id.visibleMenu);
            }
        }
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}





