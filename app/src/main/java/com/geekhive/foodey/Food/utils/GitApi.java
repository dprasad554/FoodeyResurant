package com.geekhive.foodey.Food.utils;

import com.geekhive.foodey.Food.beans.address.AddAddress;
import com.geekhive.foodey.Food.beans.address.AddressList;
import com.geekhive.foodey.Food.beans.addtocart.AddToCart;
import com.geekhive.foodey.Food.beans.bestseling.BestSelling;
import com.geekhive.foodey.Food.beans.bookservice.BookService;
import com.geekhive.foodey.Food.beans.breakfast.BreakfastListOut;
import com.geekhive.foodey.Food.beans.cartlist.CartList;
import com.geekhive.foodey.Food.beans.categories.RestaurantCategory;
import com.geekhive.foodey.Food.beans.checksumPaytm.Checksum;
import com.geekhive.foodey.Food.beans.chooseaddress.ChooseAddress;
import com.geekhive.foodey.Food.beans.citylist.CityList;
import com.geekhive.foodey.Food.beans.gsearch.GlobalSearchRest;
import com.geekhive.foodey.Food.beans.instruction.InstructionsAdd;
import com.geekhive.foodey.Food.beans.loginout.LoginOut;
import com.geekhive.foodey.Food.beans.mapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Food.beans.mylocation.MyLocation;
import com.geekhive.foodey.Food.beans.newslider.ResNewSlider;
import com.geekhive.foodey.Food.beans.offers.OfferList;
import com.geekhive.foodey.Food.beans.order.OrderHistory;
import com.geekhive.foodey.Food.beans.payment.PaymentCOD;
import com.geekhive.foodey.Food.beans.paytmIntegration.PaytmResponse;
import com.geekhive.foodey.Food.beans.promocode.PromoCode;
import com.geekhive.foodey.Food.beans.promoselect.PromoSelect;
import com.geekhive.foodey.Food.beans.rating.ResturantRating;
import com.geekhive.foodey.Food.beans.ratingDelivery.RatingDelivery;
import com.geekhive.foodey.Food.beans.ratingRes.RatingRes;
import com.geekhive.foodey.Food.beans.removecart.RemoveCartItem;
import com.geekhive.foodey.Food.beans.restaurantList.RestaurantListOut;
import com.geekhive.foodey.Food.beans.restaurantdetails.RestaurantDetails;
import com.geekhive.foodey.Food.beans.restaurantmenulist.RestaurantMenuList;
import com.geekhive.foodey.Food.beans.restdelchecksum.RestDeliveryChecksum;
import com.geekhive.foodey.Food.beans.restdeltip.RestDeliveryTip;
import com.geekhive.foodey.Food.beans.restitmsearch.RestaurantItemSearch;
import com.geekhive.foodey.Food.beans.rslider.ResSlider;
import com.geekhive.foodey.Food.beans.servicehistory.ServiceHistory;
import com.geekhive.foodey.Food.beans.serviceslider.ServiceSlider;
import com.geekhive.foodey.Food.beans.sliderhome.SliderHomeList;
import com.geekhive.foodey.Food.beans.sliderhome.SliderHomeListDown;
import com.geekhive.foodey.Food.beans.snacks.SnackListOut;
import com.geekhive.foodey.Food.beans.tablebooking.BookingDetails;
import com.geekhive.foodey.Food.beans.tablebooking.TableBooking;
import com.geekhive.foodey.Food.beans.takeaway.TakeAway;
import com.geekhive.foodey.Food.beans.updatecart.UpdateCartItem;
import com.geekhive.foodey.Food.beans.updatelocation.TrackLocation;
import com.geekhive.foodey.Food.beans.userlocation.UserLocation;
import com.geekhive.foodey.Food.beans.usrtakeaway.UserTakeAway;
import com.geekhive.foodey.Food.beans.wallet.WalletBalance;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitApi {


    @POST("login")
    @Multipart
    Call<LoginOut> login(@Part MultipartBody.Part mobile);

    @POST("resend_otp")
    @Multipart
    Call<LoginOut> resendOtp(@Part MultipartBody.Part mobile);

    @POST("activate_account")
    @Multipart
    Call<LoginOut> verifyOtp(@Part MultipartBody.Part mobile, @Part MultipartBody.Part otp, @Part MultipartBody.Part firebase_id);//, @Part MultipartBody.Part firebase_id

    @POST("edit_profile")
    @Multipart
    Call<LoginOut> editProfile(@Part MultipartBody.Part id,
                               @Part MultipartBody.Part firstname,
                               @Part MultipartBody.Part lastname,
                               @Part MultipartBody.Part mobile,
                               @Part MultipartBody.Part alternate_mobile,
                               @Part MultipartBody.Part refer_code);

    @POST("resturant_list")
    @Multipart
    Call<RestaurantListOut> getRestaurantList(@Part MultipartBody.Part location, @Part MultipartBody.Part user_id);

    @POST("location_base_breakfast")
    @Multipart
    Call<BreakfastListOut> getBreakfastList(@Part MultipartBody.Part user_id);

    @POST("location_base_snack")
    @Multipart
    Call<SnackListOut> getSnackList(@Part MultipartBody.Part user_id);

    @POST("resturant_details")
    @Multipart
    Call<RestaurantDetails> getRestaurantDetails(@Part MultipartBody.Part res_id);

    @GET("new_slider")
    Call<ResNewSlider> getNewSlider();

    @GET("resturant_slider")
    Call<ResSlider> getResSlider();

    @GET("slider_image")
    Call<SliderHomeList> getSliderImages();

    @GET("slider_image2")
    Call<SliderHomeListDown> getSliderImagesDown();

    @POST("addtocart")
    @Multipart
    Call<AddToCart> addToCart(@Part MultipartBody.Part user_id, @Part MultipartBody.Part res_id, @Part MultipartBody.Part food_id,
                              @Part MultipartBody.Part quantity, @Part MultipartBody.Part price, @Part MultipartBody.Part mrp,
                              @Part MultipartBody.Part distance, @Part MultipartBody.Part city_id);

    @POST("cartlist")
    @Multipart
    Call<CartList> getCartList(@Part MultipartBody.Part user_id);

    @POST("updatecart")
    @Multipart
    Call<UpdateCartItem> updateCartItem(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part quantity,
                                        @Part MultipartBody.Part cartdetail_id, @Part MultipartBody.Part distance);

    @POST("removetocart")
    @Multipart
    Call<RemoveCartItem> removeCart(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id);

    @POST("removetoitem")
    @Multipart
    Call<RemoveCartItem> removeCartItem(@Part MultipartBody.Part cartdetail_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part distance);

    @POST("address")
    @Multipart
    Call<AddAddress> addAddress(@Part MultipartBody.Part user_id, @Part MultipartBody.Part house, @Part MultipartBody.Part apartment, @Part MultipartBody.Part street,
                                @Part MultipartBody.Part landmark, @Part MultipartBody.Part area,
                                @Part MultipartBody.Part city, @Part MultipartBody.Part state,
                                @Part MultipartBody.Part country, @Part MultipartBody.Part pincode,
                                @Part MultipartBody.Part latitude, @Part MultipartBody.Part longitude);

    @POST("address_list")
    @Multipart
    Call<AddressList> getAddressList(@Part MultipartBody.Part user_id);

    @POST("choose_address")
    @Multipart
    Call<ChooseAddress> selectAddress(@Part MultipartBody.Part user_id, @Part MultipartBody.Part address_id, @Part MultipartBody.Part cart_id);

    @POST("pay_later")
    @Multipart
    Call<PaymentCOD> orderPlaced(@Part MultipartBody.Part order_id, @Part MultipartBody.Part user_id);

    @POST("order_history")
    @Multipart
    Call<OrderHistory> getOrderHistory(@Part MultipartBody.Part user_id);

    @POST("table_book")
    @Multipart
    Call<TableBooking> doTableBooking(@Part MultipartBody.Part user_id, @Part MultipartBody.Part name, @Part MultipartBody.Part mobile,
                                      @Part MultipartBody.Part alternate_mobile, @Part MultipartBody.Part email, @Part MultipartBody.Part no_guest,
                                      @Part MultipartBody.Part book_date, @Part MultipartBody.Part time, @Part MultipartBody.Part res_id);

    @POST("table_book_details")
    @Multipart
    Call<BookingDetails> getBookingDetails(@Part MultipartBody.Part user_id);

    @POST("res_category")
    @Multipart
    Call<RestaurantCategory> getRestaurantCategories(@Part MultipartBody.Part res_id);

    @POST("res_category_veg")
    @Multipart
    Call<RestaurantCategory>getVegCategories(@Part MultipartBody.Part res_id,@Part MultipartBody.Part type);

    @POST("res_category_food")
    @Multipart
    Call<RestaurantMenuList> getRestaurantMenulist(@Part MultipartBody.Part res_id, @Part MultipartBody.Part category);

    @POST("res_category_veg_food")
    @Multipart
    Call<RestaurantMenuList>getRestaurantVegFood(@Part MultipartBody.Part res_id, @Part MultipartBody.Part category,
                                                  @Part MultipartBody.Part type);

    @POST("resturant_offer")
    @Multipart
    Call<OfferList> getOfferList(@Part MultipartBody.Part res_id, @Part MultipartBody.Part user_id);

    @POST("generateChecksum")
    @Multipart
    Call<Checksum> generateCreheckSum(@Part MultipartBody.Part order_id, @Part MultipartBody.Part cust_id, @Part MultipartBody.Part txn_amount);

    @POST("payment_response")
    @Multipart
    Call<PaytmResponse> getPaytmResponse(@Part MultipartBody.Part ORDERID, @Part MultipartBody.Part user_id,
                                         @Part MultipartBody.Part TXNID, @Part MultipartBody.Part TXNAMOUNT,
                                         @Part MultipartBody.Part PAYMENTMODE, @Part MultipartBody.Part CURRENCY,
                                         @Part MultipartBody.Part TXNDATE, @Part MultipartBody.Part STATUS,
                                         @Part MultipartBody.Part RESPCODE, @Part MultipartBody.Part RESPMSG,
                                         @Part MultipartBody.Part GATEWAYNAME, @Part MultipartBody.Part BANKTXNID, @Part MultipartBody.Part BANKNAME);

    @POST("find_location_del")
    @Multipart
    Call<TrackLocation> getLocationUpdate(@Part MultipartBody.Part del_id,
                                          @Part MultipartBody.Part order_id);

    @POST("user_location")
    @Multipart
    Observable<UserLocation> getUserLocation(@Part MultipartBody.Part order_id);

    @GET("json?")
    Call<GetDistanceFromMap> getDistanceFromMap(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);

    @POST("update_location")
    @Multipart
    Call<MyLocation> updateMyLocation(@Part MultipartBody.Part id,
                                      @Part MultipartBody.Part latitude,
                                      @Part MultipartBody.Part longitude);

    @POST("search")
    @Multipart
    Call<GlobalSearchRest> getGlobalSearch(@Part MultipartBody.Part user_id,
                                           @Part MultipartBody.Part name);

    @POST("resturant_item_search")
    @Multipart
    Call<RestaurantItemSearch> getRestItemSearch(@Part MultipartBody.Part user_id,
                                                 @Part MultipartBody.Part name,
                                                 @Part MultipartBody.Part res_id);

    @POST("my_wallet")
    @Multipart
    Call<WalletBalance> getWalletBalance(@Part MultipartBody.Part user_id);

    @POST("give_rating_resturant")
    @Multipart
    Call<RatingRes> getResRating(@Part MultipartBody.Part res_id,@Part MultipartBody.Part user_id,@Part MultipartBody.Part rating,
                                 @Part MultipartBody.Part feedback,@Part MultipartBody.Part type,@Part MultipartBody.Part cart_id);
    @POST("give_rating_delivery")
    @Multipart
    Call<RatingDelivery> getDelRating(@Part MultipartBody.Part del_id, @Part MultipartBody.Part user_id, @Part MultipartBody.Part rating,
                                      @Part MultipartBody.Part feedback, @Part MultipartBody.Part type, @Part MultipartBody.Part cart_id);

    @POST("show_rating_res")
    @Multipart
    Call<ResturantRating> getRestuarnatRating(@Part MultipartBody.Part res_id);

    @GET("promo_code_list")
    Call<PromoCode> getPromoCodes();

    @POST("checkout")
    @Multipart
    Call<PromoSelect> applyPromo(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part promo_code);

    @POST("best_selling")
    @Multipart
    Call<BestSelling> bestSelling(@Part MultipartBody.Part user_id);

    @POST("add_instructions")
    @Multipart
    Call<InstructionsAdd> addInstructions(@Part MultipartBody.Part user_id,
                                          @Part MultipartBody.Part cart_id,
                                          @Part MultipartBody.Part instructions);
    @POST("add_tip")
    @Multipart
    Call<RestDeliveryTip> restAddTip(@Part MultipartBody.Part user_id, @Part MultipartBody.Part cart_id, @Part MultipartBody.Part del_id, @Part MultipartBody.Part order_id, @Part MultipartBody.Part tip);

    @POST("tipgenerateChecksum")
    @Multipart
    Call<RestDeliveryChecksum> generatetipsCreheckSum(@Part MultipartBody.Part tip_no, @Part MultipartBody.Part cust_id, @Part MultipartBody.Part txn_amount);

    @POST("tippayment_response")
    @Multipart
    Call<PaytmResponse> getTipsPaytmResponse(@Part MultipartBody.Part ORDERID, @Part MultipartBody.Part user_id,
                                                    @Part MultipartBody.Part TXNID, @Part MultipartBody.Part TXNAMOUNT,
                                                    @Part MultipartBody.Part PAYMENTMODE, @Part MultipartBody.Part CURRENCY,
                                                    @Part MultipartBody.Part TXNDATE, @Part MultipartBody.Part STATUS,
                                                    @Part MultipartBody.Part RESPCODE, @Part MultipartBody.Part RESPMSG,
                                                    @Part MultipartBody.Part GATEWAYNAME, @Part MultipartBody.Part BANKTXNID, @Part MultipartBody.Part BANKNAME);


    @GET("city_list")
    Call<CityList> getCityLIst();

    @POST("take_away_order_user")
    @Multipart
    Call<TakeAway> getTakeAway(@Part MultipartBody.Part user_id,
                               @Part MultipartBody.Part cart_id,
                               @Part MultipartBody.Part order_id);

    @POST("user_order_received")
    @Multipart
    Call<UserTakeAway> confirmTakeAway(@Part MultipartBody.Part cart_id,
                                       @Part MultipartBody.Part order_id,
                                       @Part MultipartBody.Part user_id,
                                       @Part MultipartBody.Part otp);

    @POST("service_history")
    @Multipart
    Call<ServiceHistory> getservicehistory(@Part MultipartBody.Part user_id);

    @GET("service_slider")
    Call<ServiceSlider> getServiceImages();

    @POST("add_service")
    @Multipart
    Call<BookService> bookaservice(@Part MultipartBody.Part name,
                                   @Part MultipartBody.Part user_id,
                                   @Part MultipartBody.Part phone,
                                   @Part MultipartBody.Part email,
                                   @Part MultipartBody.Part category,
                                   @Part MultipartBody.Part problem,
                                   @Part MultipartBody.Part book_date,
                                   @Part MultipartBody.Part book_time,
                                   @Part MultipartBody.Part image);
}

