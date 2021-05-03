package com.geekhive.foodey.Cakes.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class CakeTrackOrderAdapter  {

   /* //Variables
    private Context context;
    ArrayList<Integer> orderStatusImg;
    ArrayList<Integer> deliveryStatusImg;
    CakeHistory history;
    List<String> statusList = new ArrayList<>();
    Dialog paypopup;
    String resId = "";
    String cart_id = "";

    String del_id = "";
    String ratingDel = "";
    String feedbackDel = "";
    String typeDel = "";
    String typeRes = "";
    String feedbackRes = "";

    public CakeTrackOrderAdapter(Context context, CakeHistory history, ArrayList<Integer> orderStatusImg,
                                 ArrayList<Integer> deliveryStatusImg) {
        this.context = context;
        this.orderStatusImg = orderStatusImg;
        this.deliveryStatusImg = deliveryStatusImg;
        this.history = history;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_layout_trackorder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String canceled = "";
        statusList = new ArrayList<>();
        holder.orderStatus.setText(history.getOrderHistory().getCakeCart().get(position).getOrderStatus());
        if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("6") || history.getOrderHistory().getCakeCart().get(position).getStatus().equals("7")
        || history.getOrderHistory().getCakeCart().get(position).getStatus().equals("0")) {
            //statusList.add("Pending");
            statusList.add("Pending");
            statusList.add("Confirmed");
            statusList.add("Order packed");
            statusList.add("Delivery boy assigned");
            statusList.add("Track Order");
            holder.mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 4)
                    .reverseDraw(false)
                    .setStepViewTexts(statusList)
                    .setLinePaddingProportion(0.85f)
                    .setTextSize(14)
                    .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorCompleteIcon(context.getResources().getDrawable(R.drawable.completed))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
        } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("1")) {
            //statusList.add("Confirmed");
            statusList.add("Pending");
            statusList.add("Confirmed");
            statusList.add("Order packed");
            statusList.add("Delivery boy assigned");
            statusList.add("Track Order");
            holder.mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 3)
                    .reverseDraw(false)
                    .setStepViewTexts(statusList)
                    .setLinePaddingProportion(0.85f)
                    .setTextSize(14)
                    .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorCompleteIcon(context.getResources().getDrawable(R.drawable.completed))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
        } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("2")) {
            canceled = "Canceled " + history.getOrderHistory().getCakeCart().get(position).getCancelBy();
            statusList.add("Pending");
            statusList.add("Confirmed");
            statusList.add(canceled);
            statusList.add("Order packed");
            statusList.add("Delivery boy assigned");
            statusList.add("Track Order");
            holder.mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 4)
                    .reverseDraw(false)
                    .setStepViewTexts(statusList)
                    .setLinePaddingProportion(0.85f)
                    .setTextSize(14)
                    .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorCompleteIcon(context.getResources().getDrawable(R.drawable.completed))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
        } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("3")) {
            statusList.add("Pending");
            statusList.add("Confirmed");
            statusList.add("Order packed");
            statusList.add("Delivery boy assigned");
            statusList.add("Track Order");
            holder.mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 2)
                    .reverseDraw(false)
                    .setStepViewTexts(statusList)
                    .setLinePaddingProportion(0.85f)
                    .setTextSize(14)
                    .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorCompleteIcon(context.getResources().getDrawable(R.drawable.completed))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
        } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("8")) {
            statusList.add("Pending");
            statusList.add("Confirmed");
            statusList.add("Order packed");
            statusList.add("Delivery boy assigned");
            statusList.add("Track Order");
            holder.mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 1)
                    .reverseDraw(false)
                    .setStepViewTexts(statusList)
                    .setLinePaddingProportion(0.105f)
                    .setTextSize(14)
                    .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorCompleteIcon(context.getResources().getDrawable(R.drawable.completed))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
        } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("4")) {

            statusList.add("Pending");
            statusList.add("Confirmed");
            statusList.add("Delivery boy assigned");
            statusList.add("Order packed");
            statusList.add("Track Order");
            holder.mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 0)
                    .reverseDraw(false)
                    .setStepViewTexts(statusList)
                    .setLinePaddingProportion(0.85f)
                    .setTextSize(14)
                    .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorCompleteIcon(context.getResources().getDrawable(R.drawable.completed))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
        } else if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("5")) {

            statusList.add("Pending");
            statusList.add("Confirmed");
            statusList.add("Delivery boy assigned");
            statusList.add("Order packed");
            statusList.add("Track Order");
            holder.mSetpview0.setStepsViewIndicatorComplectingPosition(statusList.size() - 0)
                    .reverseDraw(false)
                    .setStepViewTexts(statusList)
                    .setLinePaddingProportion(0.85f)
                    .setTextSize(14)
                    .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.colorPrimary))
                    .setStepsViewIndicatorCompleteIcon(context.getResources().getDrawable(R.drawable.completed))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
        }
        if (history.getOrderHistory().getCakeCart().get(position).getStatus().equals("5")){
            holder.track_order.setVisibility(View.VISIBLE);
        } else {
            holder.track_order.setVisibility(View.GONE);
        }
        holder.orderNo.setText(history.getOrderHistory().getCakeCart().get(position).getOrderId());
        holder.orderPrice.setText("Rs."+history.getOrderHistory().getCakeCart().get(position).getGrandTotal());
        holder.track_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CakeTrackingActivity.class);
                intent.putExtra("del_id", history.getOrderHistory().getCakeCart().get(position).getDelId());
                intent.putExtra("order_id", history.getOrderHistory().getCakeCart().get(position).getOrderId());
                intent.putExtra("position", position);
                //orderHistory.getOrderHistory().getDeliveryBoy().getCart(
                context.startActivity(intent);
            }
        });
        holder.view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CakeOrderlistActivity.class);
                intent.putExtra("title",history.getOrderHistory().getCakeCartDetail().get(position).get(0).getCake().getProductName());
                intent.putExtra("price","Price :"+"₹"+history.getOrderHistory().getCakeCartDetail().get(position).get(0).getCake().getPrice());
                intent.putExtra("Quantity",history.getOrderHistory().getCakeCartDetail().get(position).get(0).getCake().getQuantity()+" "+
                        history.getOrderHistory().getCakeCartDetail().get(position).get(0).getCake().getQuantityDetail());
                String url = "http://foodeyservices.com/img/cake/"+
                        history.getOrderHistory().getCakeCartDetail().get(position).get(0).getCake().getImage();
                intent.putExtra("URL",url);
                context.startActivity(intent);
            }
        });
        holder.vT_rate_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitializeRatepopup();
                initializePopup(history.getOrderHistory().getCakeStore().get(position).getImage(),
                        history.getOrderHistory().getCakeStore().get(position).getImage(),
                        history.getOrderHistory().getCakeStore().get(position).getName(),
                        history.getOrderHistory().getCakeStore().get(position).getId(),
                        Prefs.getUserId(context),
                        history.getOrderHistory().getCakeCart().get(position).getId(),
                        history.getOrderHistory().getDeliveryBoy().get(position).getId());
            }
        });
    }
    private void InitializeRatepopup() {
        paypopup = new Dialog(context);
        paypopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        paypopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        paypopup.setContentView(R.layout.popup_rate_delivery);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    private void initializePopup(String dbImg, String resImg, String dbName, String rsName, String res_id, String user_id, String cart_id, String del_id) {
        paypopup.setContentView(R.layout.popup_rate_delivery);
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();

        this.resId = resId;
        this.cart_id = cart_id;
        this.del_id = del_id;
        typeRes = "resturant";
        typeDel = "delivery";


        final CircleImageView vI_deliveryboy = paypopup.findViewById(R.id.vI_deliveryboy);
        final TextView delBoyName = paypopup.findViewById(R.id.delBoyName);
        final RatingBar mRatingBar = paypopup.findViewById(R.id.ratingBar);
        final TextView mRatingScale = paypopup.findViewById(R.id.tvRatingScale);
        final EditText mFeedback = paypopup.findViewById(R.id.vE_rate);

        final CircleImageView vI_res = paypopup.findViewById(R.id.vI_res);
        final TextView resName = paypopup.findViewById(R.id.restName);
        final RatingBar mRatingBarRes = paypopup.findViewById(R.id.ratingBarRes);
        final TextView mRatingScaleRes = paypopup.findViewById(R.id.tvRatingScaleRes);
        final EditText mFeedbackRes = paypopup.findViewById(R.id.vE_rate_res);

        String url = WebServices.Foodey_Rest_Url + resImg;


        if (!url.equals("")) {
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(vI_res);
        }

        String url1 = WebServices.Foodey_Image_Url + resImg;


        if (!url.equals("")) {
            Picasso.get()
                    .load(url1)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(vI_deliveryboy);
        }

        delBoyName.setText(dbName);
        resName.setText(rsName);


        final Button mSendFeedback = paypopup.findViewById(R.id.btnSubmit);
        final TextView vTSkip = paypopup.findViewById(R.id.vT_skip);


      *//*  int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;*//*
        paypopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paypopup.getWindow().setGravity(Gravity.CENTER);


        if (!mFeedback.getText().toString().isEmpty() || !mFeedback.getText().toString().equals("")) {
            feedbackDel = mFeedback.getText().toString();
        }


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                ratingDel = String.valueOf(v);
                switch ((int) ratingBar.getRating()) {
                    case 1:

                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });


        if (!mFeedbackRes.getText().toString().isEmpty() || !mFeedbackRes.getText().toString().equals("")) {
            feedbackRes = mFeedbackRes.getText().toString();
        }

        mRatingBarRes.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScaleRes.setText(String.valueOf(v));
                ratingRes = String.valueOf(v);
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScaleRes.setText("Very bad");
                        break;
                    case 2:
                        mRatingScaleRes.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScaleRes.setText("Good");
                        break;
                    case 4:
                        mRatingScaleRes.setText("Great");
                        break;
                    case 5:
                        mRatingScaleRes.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScaleRes.setText("");
                }
            }
        });


        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paypopup.isShowing()) {
                    paypopup.dismiss();
                    CallDelService();
                }
            }
        });
        vTSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paypopup.isShowing())
                    paypopup.dismiss();
            }
        });


        paypopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        paypopup.setCancelable(true);
        paypopup.setCanceledOnTouchOutside(true);
        paypopup.show();
    }

    @Override
    public int getItemCount() {
        return history.getOrderHistory().getCakeCartDetail().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderStatus,orderNo,orderStatusOne,orderStatusTwo,
                 orderStatusThree,orderPrice,track_order,view_order,vT_rate_txt;
        ImageView greenImg,redImg;
        RelativeLayout cart_list;
        VerticalStepView mSetpview0;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderStatus = (TextView)itemView.findViewById(R.id.tv_orderStatus);
            orderNo = (TextView)itemView.findViewById(R.id.tv_orderNo);
            orderPrice = (TextView)itemView.findViewById(R.id.tv_orderPrice);
            track_order = (TextView)itemView.findViewById(R.id.track_order);
            cart_list = (RelativeLayout)itemView.findViewById(R.id.cart_list);
            view_order = (TextView)itemView.findViewById(R.id.view_order);
            mSetpview0 = (VerticalStepView) itemView.findViewById(R.id.step_view0);
            vT_rate_txt = (TextView)itemView.findViewById(R.id.vT_rate_txt);
        }
    }*/
}
