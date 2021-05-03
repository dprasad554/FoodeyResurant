package com.geekhive.foodey.Food.eatout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekhive.foodey.Food.beans.addtocart.AddToCart;
import com.geekhive.foodey.Food.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Food.beans.restaurantmenulist.Food;
import com.geekhive.foodey.Food.beans.restaurantmenulist.RestaurantMenuList;
import com.geekhive.foodey.Food.orderfood.CheckOutActivityNew;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment implements OnResponseListner {

    View v;
    RecyclerView menuRecycler;
    ConnectionDetector mDetector;
    private String id;
    RestaurantMenuList restaurantMenuList;
    List<Food> foodList = new ArrayList<>();
    int quantity = 0;

    String price_;
    String mrp;
    String foodId;
    MenuAdapter menuAdapter;
    String res_id;
    Dialog paypopup;
    Integer total_price;
    String vegonly = "";

    public static MenuFragment getInstance(String category, String res_id, String type) {
        MenuFragment topRated = new MenuFragment();
        // Supply index input as an argument
        Bundle args = new Bundle();
        args.putString("id", category);
        args.putString("res_id", res_id);
        args.putString("vegonly", type);
        //args.putString("name", name);
        topRated.setArguments(args);
        return topRated;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            id = args.getString("id", "0");
            res_id = args.getString("res_id", "0");
            vegonly = args.getString("vegonly", "0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_menu, container, false);
        initialiseView(v, this.getActivity());

        setHasOptionsMenu(false);
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (foodList != null) {
                foodList.clear();
            }
            if (res_id != null) {
                if (vegonly.equals("vegonly")){
                    CallVegService(res_id, id);
                } else {
                    CallService(res_id, id);
                }

            }

            if (menuAdapter != null) {
                menuAdapter.notifyDataSetChanged();
            }
        }
    }

    void initialiseView(View v, final Context ctx) {
        mDetector = new ConnectionDetector(getActivity());
        menuRecycler = v.findViewById(R.id.menulist);
        Bundle args = getArguments();
        id = args.getString("id", "0");
        res_id = args.getString("res_id", "0");
        vegonly = args.getString("vegonly", "0");
        if (foodList.size() == 0) {
            if (vegonly.equals("vegonly")){
                CallVegService(res_id, id);
            } else {
                CallService(res_id, id);
            }
        }

    }

    private void CallService(String res_id, String category) {

        new WebServices(this).RestaurantMenuList(WebServices.Foodey_Services,
                WebServices.ApiType.restaurantmenulist, res_id, category);

    }

    private void CallVegService(String res_id, String category) {

        new WebServices(this).RestaurantVegMenuList(WebServices.Foodey_Services,
                WebServices.ApiType.restaurantvegmenulist, res_id, category,"veg");

    }

    private void CallAdd(String lat, String lang) {

        String str_origin = Prefs.getUserLat(getActivity()) + "," + Prefs.getUserLang(getActivity());
        // Destination of route
        String str_dest = lat + "," + lang;

        String key = getResources().getString(R.string.google_map_api);
        CallMapService(str_origin, str_dest, key);

    }

    private void CallMapService(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistance, origin, destination, key);
        } else {
            SnackBar.makeText(getActivity(), "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }

    private void CallAddService(String distanceAdd) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).AddToCart(WebServices.Foodey_Services,
                    WebServices.ApiType.addToCart, Prefs.getUserId(getContext()), res_id, foodId, String.valueOf(quantity), price_ , mrp, distanceAdd, Prefs.getCityId(getActivity()));

            return;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.restaurantmenulist) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {

                restaurantMenuList = (RestaurantMenuList) response;
                if (!isSucces || restaurantMenuList == null) {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (restaurantMenuList != null) {
                        if (restaurantMenuList.getFood() != null) {
                            foodList = new ArrayList<>();
                            foodList.addAll(restaurantMenuList.getFood());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            menuRecycler.setLayoutManager(linearLayoutManager);
                            MenuAdapter menuAdapter = new MenuAdapter();
                            menuRecycler.setAdapter(menuAdapter);
                        } else {
                            SnackBar.makeText(getActivity(), "No record found", SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        SnackBar.makeText(getActivity(), "No record found", SnackBar.LENGTH_SHORT).show();
                    }

                }
            }
        } if (apiType == WebServices.ApiType.restaurantvegmenulist) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {

                restaurantMenuList = (RestaurantMenuList) response;
                if (!isSucces || restaurantMenuList == null) {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (restaurantMenuList != null) {
                        if (restaurantMenuList.getFood() != null) {
                            foodList = new ArrayList<>();
                            foodList.addAll(restaurantMenuList.getFood());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            menuRecycler.setLayoutManager(linearLayoutManager);
                            MenuAdapter menuAdapter = new MenuAdapter();
                            menuRecycler.setAdapter(menuAdapter);
                        } else {
                            SnackBar.makeText(getActivity(), "No record found", SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        SnackBar.makeText(getActivity(), "No record found", SnackBar.LENGTH_SHORT).show();
                    }

                }
            }
        }
        if (apiType == WebServices.ApiType.addToCart) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                AddToCart cartListOut = (AddToCart) response;
                if (cartListOut != null) {
                    quantity = 0;
                    SnackBar.makeText(getActivity(), cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                    //menuAdapter.notifyDataSetChanged();
                } else {
                    SnackBar.makeText(getActivity(), cartListOut.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null) {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        if(distance.contains("km")){
                            String dis = distance.replace(" km", "");
                            CallAddService(dis);
                        } else {
                            CallAddService("1");
                        }
                    }
                } else {
                    SnackBar.makeText(getActivity(), getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
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
                holder.vT_admd_text.setText(foodList.get(position).getName());
                mrp = foodList.get(position).getMrp();
                price_ = foodList.get(position).getPrice();
                if(foodList.get(position).getType().equals("Non Veg")){
                    holder.symbol.setBackgroundResource(R.drawable.non_veg);
                }else {
                    holder.symbol.setBackgroundResource(R.drawable.veg);
                }
                Log.d("hhhjjj", price_);

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
                            mrp = foodList.get(position).getMrp();
                            price_ = foodList.get(position).getPrice();

                            // holder.vL_add_to_cart.setVisibility(View.VISIBLE);

                        } else {
                            holder.vL_add_to_cart.setVisibility(View.VISIBLE);
                            int qaunt = Integer.parseInt(holder.qty.getText().toString()) - 1;
                            holder.qty.setText(String.valueOf(qaunt));
                            holder.vL_count.setVisibility(View.GONE);
                            mrp = foodList.get(position).getMrp();
                            price_ = foodList.get(position).getPrice();

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
                            /*foodId = foodList.get(position).getId();
                            mrp = foodList.get(position).getMrp();
                            price_ = foodList.get(position).getPrice();*/
                            quantity = Integer.parseInt(holder.qty.getText().toString());
                            res_id = foodList.get(position).getResId();
                            price_ = foodList.get(position).getPrice();
                            mrp = foodList.get(position).getMrp();
                            foodId = foodList.get(position).getId();
                            CallAdd(foodList.get(position).getLatitude(), foodList.get(position).getLongitude());
                            InitializeCartpopup();
                            initializePopup(holder.qty.getText().toString(), foodList.get(position).getPrice());
                        } else {
                            SnackBar.makeText(getActivity(), "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                        }

                        holder.qtyA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int qaunt = Integer.parseInt(holder.qty.getText().toString()) + 1;
                                holder.qty.setText(String.valueOf(qaunt));
                                quantity = Integer.parseInt(holder.qty.getText().toString());
                                res_id = foodList.get(position).getResId();
                                price_ = foodList.get(position).getPrice();
                                mrp = foodList.get(position).getMrp();
                                foodId = foodList.get(position).getId();
                                CallAdd(foodList.get(position).getLatitude(), foodList.get(position).getLongitude());
                                InitializeCartpopup();
                                initializePopup(holder.qty.getText().toString(), foodList.get(position).getPrice());

                            }
                        });

                    }


                });


                holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(holder.qty.getText().toString()) != 0) {
                            quantity = Integer.parseInt(holder.qty.getText().toString());
                            res_id = foodList.get(position).getResId();
                            price_ = foodList.get(position).getPrice();
                            mrp = foodList.get(position).getMrp();
                            foodId = foodList.get(position).getId();
                            CallAdd(foodList.get(position).getLatitude(), foodList.get(position).getLongitude());
                            //InitializePaypopup();
                            // initializePopup();
                        } else {
                            SnackBar.makeText(getActivity(), "Invalid quantity", SnackBar.LENGTH_SHORT).show();
                        }
                    }

                });

            } else {
                holder.vT_admd_text.setText(foodList.get(position).getName());
                mrp = foodList.get(position).getMrp();
                price_ = foodList.get(position).getPrice();
                String mrp = "₹ " + foodList.get(position).getMrp();
                String price = "₹ " + foodList.get(position).getPrice();
                String price_t = foodList.get(position).getPrice();
                if(foodList.get(position).getType().equals("Non Veg")){
                    holder.symbol.setBackgroundResource(R.drawable.non_veg);
                }else {
                    holder.symbol.setBackgroundResource(R.drawable.veg);
                }
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

            String url = WebServices.Foodey_Food_Url + foodList.get(position).getImage();


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
            ImageView vI_admd_image,symbol;
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
                this.symbol = view.findViewById(R.id.symbol);
            }
        }
    }

    private void InitializeCartpopup() {
        paypopup = new Dialog(getActivity());
        paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        paypopup.setContentView(R.layout.bottom_popup);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void initializePopup(String quantity, String price) {
        paypopup.setContentView(R.layout.bottom_popup);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();

        final TextView vT_item_count = (TextView) paypopup.findViewById(R.id.vT_item_count);
        final TextView vT_item_price = (TextView) paypopup.findViewById(R.id.vT_item_price);
        final TextView view_cart = (TextView) paypopup.findViewById(R.id.view_cart);
        final ImageView view_cart_img = paypopup.findViewById(R.id.view_cart_img);


        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paypopup.getWindow().setGravity(Gravity.BOTTOM);


        total_price = Integer.parseInt(quantity) * Integer.parseInt(price);

        vT_item_count.setText(quantity + "  ITEM ");
        vT_item_price.setText("₹ " + total_price);

        view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheckOutActivityNew.class);
                startActivity(intent);
            }
        });

        view_cart_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheckOutActivityNew.class);
                startActivity(intent);
            }
        });


        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();
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
