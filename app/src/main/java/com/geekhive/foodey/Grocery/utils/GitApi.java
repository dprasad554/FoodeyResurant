package com.geekhive.foodey.Grocery.utils;


import com.geekhive.foodey.Grocery.beans.citylist.CityList;
import com.geekhive.foodey.Grocery.beans.deliveryrating.DeliveryRating;
import com.geekhive.foodey.Grocery.beans.deliverytip.DeliveryTip;
import com.geekhive.foodey.Grocery.beans.getgrocerypromo.GroceryPromoList;
import com.geekhive.foodey.Grocery.beans.groceryaddcart.GroceryAddToCart;
import com.geekhive.foodey.Grocery.beans.groceryaddress.GroceryAddAddress;
import com.geekhive.foodey.Grocery.beans.groceryaddress.GroceryAddressList;
import com.geekhive.foodey.Grocery.beans.grocerybestselling.GroceryBestSelling;
import com.geekhive.foodey.Grocery.beans.grocerycartlist.CartList;
import com.geekhive.foodey.Grocery.beans.grocerycategory.GroceryMainCategory;
import com.geekhive.foodey.Grocery.beans.grocerycheckout.GroceryCheckout;
import com.geekhive.foodey.Grocery.beans.grocerychecksum.GroceryTipChecksum;
import com.geekhive.foodey.Grocery.beans.grocerychecksumPaytm.GroceryChecksum;
import com.geekhive.foodey.Grocery.beans.grocerychooseaddress.GroceryChooseAddress;
import com.geekhive.foodey.Grocery.beans.groceryglobalsearch.GlobalSearch;
import com.geekhive.foodey.Grocery.beans.groceryhistory.GroceryHistory;
import com.geekhive.foodey.Grocery.beans.groceryhomeslider.HomeSlider;
import com.geekhive.foodey.Grocery.beans.groceryoffer.GroceryOffer;
import com.geekhive.foodey.Grocery.beans.grocerypayment.GroceryPaymentCOD;
import com.geekhive.foodey.Grocery.beans.grocerypaytmIntegration.GroceryPaytmResponse;
import com.geekhive.foodey.Grocery.beans.groceryproductlist.GrocerySubProductList;
import com.geekhive.foodey.Grocery.beans.grocerypromoapply.GroceryPromoApply;
import com.geekhive.foodey.Grocery.beans.groceryremovecart.GroceryRemoveCartItem;
import com.geekhive.foodey.Grocery.beans.grocerystorename.GroceryStoreNameList;
import com.geekhive.foodey.Grocery.beans.grocerysubcategory.GrocerySubCategory;
import com.geekhive.foodey.Grocery.beans.groceryupdatecart.GroceryUpdateCartItem;
import com.geekhive.foodey.Grocery.beans.grocerymylocation.GroceryMyLocation;
import com.geekhive.foodey.Grocery.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Grocery.beans.recentpurchase.RecentPurchase;
import com.geekhive.foodey.Grocery.beans.searchdetails.SearchDetails;
import com.geekhive.foodey.Grocery.beans.storeinstruction.StoreInstruction;
import com.geekhive.foodey.Grocery.beans.storerating.GroceryStoreRating;
import com.geekhive.foodey.Grocery.beans.viewstorerating.ViewStoreRating;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface GitApi {

   @POST("g_slider")
   Call<HomeSlider> homeSlider();

   @POST("grocery_recent_purchase")
   @FormUrlEncoded
   Call<RecentPurchase> recentservice(@Field("user_id") String user_id);

   @POST("grocery_search")
   @Multipart
   Call<SearchDetails>callsearchservice(@Part MultipartBody.Part user_id, @Part MultipartBody.Part name,@Part MultipartBody.Part store_id);


   @POST("grocery_global_search")
   @Multipart
   Call<GlobalSearch> callglobalsearchservice(@Part MultipartBody.Part user_id, @Part MultipartBody.Part name);


   @POST("grocery_store")
   @Multipart
   Call<GroceryStoreNameList> storeName(@Part MultipartBody.Part user_id);

   @POST("groceryoffer")
   @Multipart
   Call<GroceryOffer> offerservice(@Part MultipartBody.Part user_id);


   @POST("grocery_category")
   @FormUrlEncoded
   Call<GroceryMainCategory> mainCategory(@Field("store_id") String store_id);

   @POST("grocery_product_sub_category")
   @FormUrlEncoded
   Call<GrocerySubCategory> subCategory(@Field("main_id") String main_id);

   @POST("grocery_product_list")
   @FormUrlEncoded
   Call<GrocerySubProductList> productList(@Field("product_sub_category") String product_sub_category);

   @POST("groceryaddress")
   @Multipart
   Call<GroceryAddAddress> addAddress(@Part MultipartBody.Part user_id,@Part MultipartBody.Part order_id, @Part MultipartBody.Part house, @Part MultipartBody.Part apartment, @Part MultipartBody.Part street,
                                      @Part MultipartBody.Part landmark, @Part MultipartBody.Part area,
                                      @Part MultipartBody.Part city, @Part MultipartBody.Part state,
                                      @Part MultipartBody.Part country, @Part MultipartBody.Part pincode,
                                      @Part MultipartBody.Part latitude, @Part MultipartBody.Part longitude);

   @POST("groceryaddress_list")
   @Multipart
   Call<GroceryAddressList> getAddressList(@Part MultipartBody.Part user_id);

   @POST("grocerychoose_address")
   @Multipart
   Call<GroceryChooseAddress> selectAddress(@Part MultipartBody.Part user_id, @Part MultipartBody.Part address_id);

   @POST("groceryaddtocart")
   @Multipart
   Call<GroceryAddToCart> addToCart(@Part MultipartBody.Part user_id, @Part MultipartBody.Part store_id,
                                    @Part MultipartBody.Part product_id, @Part MultipartBody.Part quantity,
                                    @Part MultipartBody.Part price, @Part MultipartBody.Part mrp,
                                    @Part MultipartBody.Part distance, @Part MultipartBody.Part city_id);


   @POST("grocerycartlist")
   @Multipart
   Call<CartList> getCartList(@Part MultipartBody.Part user_id);

   @POST("groceryupdatecart")
   @Multipart
   Call<GroceryUpdateCartItem> updateCartItem(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part quantity,
                                              @Part MultipartBody.Part cartdetail_id, @Part MultipartBody.Part distance);


   @POST("groceryremovetocart")
   @Multipart
   Call<GroceryRemoveCartItem> removeCart(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id);

   @POST("groceryremovetoitem")
   @Multipart
   Call<GroceryRemoveCartItem> removeCartItem(@Part MultipartBody.Part cartdetail_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part distance);

   @POST("grocerypay_later")
   @Multipart
   Call<GroceryPaymentCOD> orderPlaced(@Part MultipartBody.Part order_id, @Part MultipartBody.Part user_id);

   @POST("groceryorder_history")
   @Multipart
   Call<GroceryHistory> historydetails(@Part MultipartBody.Part user_id);

   @POST("store_generateChecksum")
   @Multipart
   Call<GroceryChecksum> generateCreheckSum(@Part MultipartBody.Part order_id, @Part MultipartBody.Part cust_id, @Part MultipartBody.Part txn_amount);

   @POST("store_payment_response")
   @Multipart
   Call<GroceryPaytmResponse> getPaytmResponse(@Part MultipartBody.Part ORDERID, @Part MultipartBody.Part user_id,
                                               @Part MultipartBody.Part TXNID, @Part MultipartBody.Part TXNAMOUNT,
                                               @Part MultipartBody.Part PAYMENTMODE, @Part MultipartBody.Part CURRENCY,
                                               @Part MultipartBody.Part TXNDATE, @Part MultipartBody.Part STATUS,
                                               @Part MultipartBody.Part RESPCODE, @Part MultipartBody.Part RESPMSG,
                                               @Part MultipartBody.Part GATEWAYNAME, @Part MultipartBody.Part BANKTXNID, @Part MultipartBody.Part BANKNAME);

   @POST("update_location")
   @Multipart
   Call<GroceryMyLocation> updateMyLocation(@Part MultipartBody.Part id,
                                            @Part MultipartBody.Part latitude,
                                            @Part MultipartBody.Part longitude);

   @GET("json?")
   Call<GetDistanceFromMap> getDistanceFromMap(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);


   //	12.8996648	77.6499509


   @POST("give_rating_grocerystore")
   @Multipart
   Call<GroceryStoreRating> getStoreRating(@Part MultipartBody.Part store_id, @Part MultipartBody.Part user_id, @Part MultipartBody.Part rating,
                                         @Part MultipartBody.Part feedback, @Part MultipartBody.Part type, @Part MultipartBody.Part cart_id);
   @POST("grocery_give_rating_delivery")
   @Multipart
   Call<DeliveryRating> getDelRating(@Part MultipartBody.Part del_id, @Part MultipartBody.Part user_id, @Part MultipartBody.Part rating,
                                     @Part MultipartBody.Part feedback, @Part MultipartBody.Part type, @Part MultipartBody.Part cart_id);


   @POST("cshow_rating_store")
   @Multipart
   Call<ViewStoreRating> storeRating(@Part MultipartBody.Part store_id);

   @GET("grocery_promo_code_list")
   Call<GroceryPromoList> getGroceryPromoCodes();

   @POST("grocerycheckout")
   @Multipart
   Call<GroceryPromoApply> applyGroceryPromo(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part promo_code);

   @POST("grocery_add_instructions")
   @Multipart
   Call<StoreInstruction> addStoreInstruction(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part instructions);

   @POST("grocery_best_selling")
   @Multipart
   Call<GroceryBestSelling> bestSellingItem(@Part MultipartBody.Part user_id);

   @POST("grocery_add_tip")
   @Multipart
   Call<DeliveryTip> groceryAddTip(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part del_id, @Part MultipartBody.Part order_id, @Part MultipartBody.Part tip);

   @POST("grocery_tipgenerateChecksum")
   @Multipart
   Call<GroceryTipChecksum> generatetipsCreheckSum(@Part MultipartBody.Part tip_no, @Part MultipartBody.Part cust_id, @Part MultipartBody.Part txn_amount);

   @POST("grocery_tippayment_response")
   @Multipart
   Call<GroceryPaytmResponse> getTipsPaytmResponse(@Part MultipartBody.Part ORDERID, @Part MultipartBody.Part user_id,
                                                @Part MultipartBody.Part TXNID, @Part MultipartBody.Part TXNAMOUNT,
                                                @Part MultipartBody.Part PAYMENTMODE, @Part MultipartBody.Part CURRENCY,
                                                @Part MultipartBody.Part TXNDATE, @Part MultipartBody.Part STATUS,
                                                @Part MultipartBody.Part RESPCODE, @Part MultipartBody.Part RESPMSG,
                                                @Part MultipartBody.Part GATEWAYNAME, @Part MultipartBody.Part BANKTXNID, @Part MultipartBody.Part BANKNAME);

   @GET("city_list")
   Call<CityList> getCityList();

}



