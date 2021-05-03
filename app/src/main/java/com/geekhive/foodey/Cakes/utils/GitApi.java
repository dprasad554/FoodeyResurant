package com.geekhive.foodey.Cakes.utils;


import com.geekhive.foodey.Cakes.beans.bestselling.BestSellingProduct;
import com.geekhive.foodey.Cakes.beans.cakeaddress.CakeAddAddress;
import com.geekhive.foodey.Cakes.beans.cakeaddress.CakeAddressList;
import com.geekhive.foodey.Cakes.beans.cakeaddtips.CakeAddTips;
import com.geekhive.foodey.Cakes.beans.cakeaddtocart.CakeAddtocart;
import com.geekhive.foodey.Cakes.beans.cakecartlist.CartList;
import com.geekhive.foodey.Cakes.beans.cakecategories.CakeAllCategores;
import com.geekhive.foodey.Cakes.beans.cakecategory.Category;
import com.geekhive.foodey.Cakes.beans.cakechecksumPaytm.CakeChecksum;
import com.geekhive.foodey.Cakes.beans.cakechooseaddress.CakeChooseAddress;
import com.geekhive.foodey.Cakes.beans.cakeglobalsearch.CakeGlobalSearch;
import com.geekhive.foodey.Cakes.beans.cakehistory.CakeHistory;
import com.geekhive.foodey.Cakes.beans.cakemapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Cakes.beans.cakemylocation.CakeMyLocation;
import com.geekhive.foodey.Cakes.beans.cakeoffer.CakeallOffer;
import com.geekhive.foodey.Cakes.beans.cakepayment.CakePaymentCOD;
import com.geekhive.foodey.Cakes.beans.cakepaytmIntegration.CakePaytmResponse;
import com.geekhive.foodey.Cakes.beans.cakeproductlist.CakeProductlist;
import com.geekhive.foodey.Cakes.beans.cakeremovecart.CakeRemoveCartItem;
import com.geekhive.foodey.Cakes.beans.cakesearchdetails.CakeSearchDetails;
import com.geekhive.foodey.Cakes.beans.cakeslider.Cakeslider;
import com.geekhive.foodey.Cakes.beans.cakestore.CakeStoreList;
import com.geekhive.foodey.Cakes.beans.cakestorebycategory.StorebyCategory;
import com.geekhive.foodey.Cakes.beans.caketipschecksum.CakeTipsChecksum;
import com.geekhive.foodey.Cakes.beans.cakeupdatecart.CakeUpdateCartItem;
import com.geekhive.foodey.Cakes.beans.cakeupdatelocation.CakeTrackLocation;
import com.geekhive.foodey.Cakes.beans.cakeuserlocation.CakeUserLocation;
import com.geekhive.foodey.Cakes.beans.citylist.CityList;
import com.geekhive.foodey.Cakes.beans.couponlist.CouponList;
import com.geekhive.foodey.Cakes.beans.givecakedeliveryboyrating.GiveCakeDeliveryboyRating;
import com.geekhive.foodey.Cakes.beans.givecakestorerating.GiveCakeStoreRating;
import com.geekhive.foodey.Cakes.beans.promoselect.CakePromoSelect;
import com.geekhive.foodey.Cakes.beans.storerating.StoreRating;

import io.reactivex.Observable;
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

    /* @POST("media")
     @FormUrlEncoded
     Call<Addmedia> addmedia(@Part MultipartBody.Part firstname, @Part MultipartBody.Part lastname, @Part MultipartBody.Part mobile,
                             @Part MultipartBody.Part email, @Part MultipartBody.Part id_proof, @Part MultipartBody.Part driving_license,
                             @Part MultipartBody.Part residance_proof, @Part MultipartBody.Part password);*/
    @POST("cake_category")
    @Multipart
    Call<Category> cakecategory(@Part MultipartBody.Part store_id);

    @POST("cake_store")
    @Multipart
    Call<CakeStoreList> cakestore(@Part MultipartBody.Part user_id);

    @POST("cakeoffer")
    @Multipart
    Call<CakeallOffer> cakeoffer(@Part MultipartBody.Part user_id);

    @POST("c_slider")
    Call<Cakeslider> cakeslider();

    @POST("cake_search")
    @FormUrlEncoded
    Call<CakeSearchDetails> searchservice(@Field("name") String name);

    @POST("cake_product_list")
    @Multipart
    Call<CakeProductlist> productlist(@Part MultipartBody.Part product_category,
                                      @Part MultipartBody.Part store_id);

    @POST("cakeaddtocart")
    @Multipart
    Call<CakeAddtocart> addtocart(@Part MultipartBody.Part user_id,
                                  @Part MultipartBody.Part store_id,
                                  @Part MultipartBody.Part product_id,
                                  @Part MultipartBody.Part quantity,
                                  @Part MultipartBody.Part price,
                                  @Part MultipartBody.Part mrp,
                                  @Part MultipartBody.Part message,
                                  @Part MultipartBody.Part s_date,
                                  @Part MultipartBody.Part s_time,
                                  @Part MultipartBody.Part image,
                                  @Part MultipartBody.Part distance,
                                  @Part MultipartBody.Part city_id
    );

    @POST("cakecartlist")
    @Multipart
    Call<CartList> getCartList(@Part MultipartBody.Part user_id);


    @POST("cakeupdatecart")
    @Multipart
    Call<CakeUpdateCartItem> updateCartItem(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part quantity,
                                            @Part MultipartBody.Part cartdetail_id, @Part MultipartBody.Part distance);

    @POST("cakeremovetocart")
    @Multipart
    Call<CakeRemoveCartItem> removeCart(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id);

    @POST("cakeremovetoitem")
    @Multipart
    Call<CakeRemoveCartItem> removeCartItem(@Part MultipartBody.Part cartdetail_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part distance);

    @POST("cakepay_later")
    @Multipart
    Call<CakePaymentCOD> orderPlaced(@Part MultipartBody.Part order_id, @Part MultipartBody.Part user_id);

    @POST("cakeorder_history")
    @Multipart
    Call<CakeHistory> historydetails(@Part MultipartBody.Part user_id);

    @POST("cakeaddress")
    @Multipart
    Call<CakeAddAddress> addAddress(@Part MultipartBody.Part user_id, @Part MultipartBody.Part house, @Part MultipartBody.Part apartment, @Part MultipartBody.Part street,
                                    @Part MultipartBody.Part landmark, @Part MultipartBody.Part area,
                                    @Part MultipartBody.Part city, @Part MultipartBody.Part state,
                                    @Part MultipartBody.Part country, @Part MultipartBody.Part pincode,
                                    @Part MultipartBody.Part latitude, @Part MultipartBody.Part longitude);

    @POST("cakeaddress_list")
    @Multipart
    Call<CakeAddressList> getAddressList(@Part MultipartBody.Part user_id);

    @POST("cakechoose_address")
    @Multipart
    Call<CakeChooseAddress> selectAddress(@Part MultipartBody.Part user_id, @Part MultipartBody.Part address_id);

    @POST("cake_generateChecksum")
    @Multipart
    Call<CakeChecksum> generateCreheckSum(@Part MultipartBody.Part order_id, @Part MultipartBody.Part cust_id, @Part MultipartBody.Part txn_amount);

    @POST("cake_payment_response")
    @Multipart
    Call<CakePaytmResponse> getPaytmResponse(@Part MultipartBody.Part ORDERID, @Part MultipartBody.Part user_id,
                                             @Part MultipartBody.Part TXNID, @Part MultipartBody.Part TXNAMOUNT,
                                             @Part MultipartBody.Part PAYMENTMODE, @Part MultipartBody.Part CURRENCY,
                                             @Part MultipartBody.Part TXNDATE, @Part MultipartBody.Part STATUS,
                                             @Part MultipartBody.Part RESPCODE, @Part MultipartBody.Part RESPMSG,
                                             @Part MultipartBody.Part GATEWAYNAME, @Part MultipartBody.Part BANKTXNID, @Part MultipartBody.Part BANKNAME);


  //getTipsPaytmResponse
  @POST("cake_tippayment_response")
  @Multipart
  Call<CakePaytmResponse> getTipsPaytmResponse(@Part MultipartBody.Part ORDERID, @Part MultipartBody.Part user_id,
                                           @Part MultipartBody.Part TXNID, @Part MultipartBody.Part TXNAMOUNT,
                                           @Part MultipartBody.Part PAYMENTMODE, @Part MultipartBody.Part CURRENCY,
                                           @Part MultipartBody.Part TXNDATE, @Part MultipartBody.Part STATUS,
                                           @Part MultipartBody.Part RESPCODE, @Part MultipartBody.Part RESPMSG,
                                           @Part MultipartBody.Part GATEWAYNAME, @Part MultipartBody.Part BANKTXNID, @Part MultipartBody.Part BANKNAME);


    @POST("cake_find_location_del")
    @Multipart
    Call<CakeTrackLocation> getLocationUpdate(@Part MultipartBody.Part del_id,
                                              @Part MultipartBody.Part order_id);

    @POST("cake_user_location")
    @Multipart
    Observable<CakeUserLocation> getUserLocation(@Part MultipartBody.Part order_id);

    @GET("json?")
    Call<GetDistanceFromMap> getDistanceFromMap(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);

    @POST("update_location")
    @Multipart
    Call<CakeMyLocation> updateMyLocation(@Part MultipartBody.Part id,
                                          @Part MultipartBody.Part latitude,
                                          @Part MultipartBody.Part longitude);

    @POST("cake_promo_code_list")
    Call<CouponList> couponlist();

    //	12.8996648	77.6499509
    @POST("cake_global_search")
    @Multipart
    Call<CakeGlobalSearch> getGlobalSearch(@Part MultipartBody.Part user_id,
                                           @Part MultipartBody.Part name);

    @POST("cshow_rating_store")
    @Multipart
    Call<StoreRating> getRestuarnatRating(@Part MultipartBody.Part store_id);

    @POST("cakecheckout")
    @Multipart
    Call<CakePromoSelect> applyPromo(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part promo_code);

    @POST("cgive_rating_store")
    @Multipart
    Call<GiveCakeStoreRating> giveResRating(@Part MultipartBody.Part store_id, @Part MultipartBody.Part user_id, @Part MultipartBody.Part rating,
                                            @Part MultipartBody.Part feedback, @Part MultipartBody.Part type, @Part MultipartBody.Part cart_id);

    @POST("cgive_rating_delivery")
    @Multipart
    Call<GiveCakeDeliveryboyRating> getDelRating(@Part MultipartBody.Part del_id, @Part MultipartBody.Part user_id, @Part MultipartBody.Part rating,
                                                 @Part MultipartBody.Part feedback, @Part MultipartBody.Part type, @Part MultipartBody.Part cart_id);

    @POST("cake_best_selling")
    @Multipart
    Call<BestSellingProduct> getbestselling(@Part MultipartBody.Part user_id);

    @POST("cake_add_tip")
    @Multipart
    Call<CakeAddTips> addtips(@Part MultipartBody.Part user_id,@Part MultipartBody.Part cart_id,@Part MultipartBody.Part del_id,
                              @Part MultipartBody.Part order_id,@Part MultipartBody.Part tip);

    @POST("cake_tipgenerateChecksum")
    @Multipart
    Call<CakeTipsChecksum> generatetipsCreheckSum(@Part MultipartBody.Part order_id, @Part MultipartBody.Part cust_id, @Part MultipartBody.Part txn_amount);

    @POST("cake_category_all")
    Call<CakeAllCategores> cakecategory();

    @POST("cake_category_wise_store")
    @Multipart
    Call<StorebyCategory> cakestorebycategory(@Part MultipartBody.Part user_id,@Part MultipartBody.Part category_name);

    @GET("city_list")
    Call<CityList> getCityList();
}
