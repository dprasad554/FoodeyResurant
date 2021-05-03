package com.geekhive.foodey.Food.utils;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebServices<T> {
    //public static String Foodey_Services = "http://demo2.geekhive.co.in/WebServices/";
    public static String Foodey_Services = "http://foodeyservices.com/WebServices/";
    public static String Foodey_Image_Url = "http://foodeyservices.com/img/";
    public static String Foodey_Rest_Url = "http://foodeyservices.com/img/resturant/";
    public static String Foodey_Offer_Url = "http://foodeyservices.com/img/offer/";
    public static String Foodey_Food_Url = "http://foodeyservices.com/img/food/";
    public static String Foodey_ServiceUrl = "http://foodeyservices.com/WebServices/";
    public static String Foodey_Service_Img_Url = "http://foodeyservices.com/img/slider/";

    public static String FoodeyService = Foodey_Services;
    private static OkHttpClient.Builder builder;

    ApiType apiTypeVariable;
    Call<T> call = null;
    Context context;


    OnResponseListner<T> onResponseListner;
    android.app.ProgressDialog pdLoading;
    private T t;

    public WebServices(OnResponseListner<T> onResponseListner) {
        if (onResponseListner instanceof Activity) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof IntentService) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof android.app.DialogFragment) {
            android.app.DialogFragment dialogFragment = (android.app.DialogFragment) onResponseListner;
            this.context = dialogFragment.getActivity();
        } else {
            Fragment fragment = (Fragment) onResponseListner;
            this.context = fragment.getActivity();
        }

        this.onResponseListner = onResponseListner;
        builder = getHttpClient();
    }

    public WebServices(Context context, OnResponseListner<T> onResponseListner) {
        this.onResponseListner = onResponseListner;
        this.context = context;
        builder = getHttpClient();
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }


    public OkHttpClient.Builder getHttpClient() {
        if (builder == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.readTimeout(60, TimeUnit.SECONDS);
            client.connectTimeout(60, TimeUnit.SECONDS);
            client.addInterceptor(loggingInterceptor);
            return client;
        }
        return builder;
    }

    //Login
    public void Login(String api, final ApiType apiTypes, String mobile){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.login(MultipartBody.Part.createFormData("mobile", mobile));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Verify Otp
    public void VerifyOtp(String api, final ApiType apiTypes, String mobile, String otp, String firebase_id){//String firebase_id
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.verifyOtp(MultipartBody.Part.createFormData("mobile", mobile),MultipartBody.Part.createFormData("otp", otp),MultipartBody.Part.createFormData("firebase_id", firebase_id));//,MultipartBody.Part.createFormData("firebase_id", firebase_id)
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Resend Otp
    public void ResendOtp(String api, final ApiType apiTypes, String mobile){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.resendOtp(MultipartBody.Part.createFormData("mobile", mobile));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Edit Profile
    public void EditProfile(String api, final ApiType apiTypes, String mobile, String id, String firstname, String lastname, String alternate_mobile, String refer_code){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.editProfile(MultipartBody.Part.createFormData("id", id),
                MultipartBody.Part.createFormData("firstname", firstname),
                MultipartBody.Part.createFormData("lastname", lastname),
                MultipartBody.Part.createFormData("mobile", mobile),
                MultipartBody.Part.createFormData("alternate_mobile", alternate_mobile),
                MultipartBody.Part.createFormData("refer_code", refer_code));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Restaurant List
    public void RestaurantList(String api, final ApiType apiTypes, String location, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getRestaurantList(MultipartBody.Part.createFormData("location", location),
                MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Breakfast List
    public void BreakfastList(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getBreakfastList(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Snack List
    public void SnackList(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getSnackList(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Restaurant List
    public void RestaurantDetails(String api, final ApiType apiTypes, String res_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getRestaurantDetails(MultipartBody.Part.createFormData("res_id", res_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Res Slider
    public void ResSlider(String api, final ApiType apiTypes){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getResSlider();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                try {
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                try {
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(null, apiTypeVariable, false);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        });
    }

    //Res Slider
    public void NewSlider(String api, final ApiType apiTypes){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getNewSlider();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                try {
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                try {
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(null, apiTypeVariable, false);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        });
    }

    //SliderImages
    public void SliderImages(String api, final ApiType apiTypes){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getSliderImages();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                try {
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                try {
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        });
    }

    //SliderImagesDown
    public void SliderImagesDown(String api, final ApiType apiTypes){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getSliderImagesDown();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {

                    e.printStackTrace();
                }
                try {} catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                try {
                    Log.e(apiTypes.toString(), t.getMessage() + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(null, apiTypeVariable, false);
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

        });
    }

    //Add To Cart
    public void AddToCart(String api, final ApiType apiTypes, String user_id, String res_id, String food_id, String quantity, String price, String mrp, String distance, String city_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.addToCart(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("res_id", res_id),
                MultipartBody.Part.createFormData("food_id", food_id),
                MultipartBody.Part.createFormData("quantity", quantity),
                MultipartBody.Part.createFormData("price", price),
                MultipartBody.Part.createFormData("mrp", mrp),
                MultipartBody.Part.createFormData("distance", distance),
                MultipartBody.Part.createFormData("city_id", city_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //CartList
    public void GetCartList(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getCartList(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //UpdateCartItem
    public void UpdateCartItem(String api, final ApiType apiTypes, String user_id, String cart_id, String quantity, String cartdetail_id, String distance){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.updateCartItem(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("cart_id", cart_id),
                MultipartBody.Part.createFormData("quantity", quantity),
                MultipartBody.Part.createFormData("cartdetail_id", cartdetail_id),
                MultipartBody.Part.createFormData("distance", distance));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RemoveCart
    public void RemoveCart(String api, final ApiType apiTypes, String user_id, String cart_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.removeCart(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("cart_id", cart_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RemoveCartItem
    public void RemoveCartItem(String api, final ApiType apiTypes, String cartdetail_id, String cart_id, String distance){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.removeCartItem(MultipartBody.Part.createFormData("cartdetail_id", cartdetail_id),
                MultipartBody.Part.createFormData("cart_id", cart_id),
                MultipartBody.Part.createFormData("distance", distance));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //AddAddress
    public void AddAddress(String api, final ApiType apiTypes, String user_id, String house, String apartment,
                           String street, String landmark, String area, String city, String state,
                           String country, String pincode, String latitude, String longitude){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.addAddress(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("house", house),
                MultipartBody.Part.createFormData("apartment", apartment),
                MultipartBody.Part.createFormData("street", street),
                MultipartBody.Part.createFormData("landmark", landmark),
                MultipartBody.Part.createFormData("area", area),
                MultipartBody.Part.createFormData("city", city),
                MultipartBody.Part.createFormData("state", state),
                MultipartBody.Part.createFormData("country", country),
                MultipartBody.Part.createFormData("pincode", pincode),
                MultipartBody.Part.createFormData("latitude", latitude),
                MultipartBody.Part.createFormData("longitude", longitude));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //AddressList
    public void AddressList(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getAddressList(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //SelectAddress
    public void SelectAddress(String api, final ApiType apiTypes, String user_id, String address_id, String cart_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.selectAddress(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("address_id", address_id),
                MultipartBody.Part.createFormData("cart_id", cart_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //OrderPlaced
    public void OrderPlaced(String api, final ApiType apiTypes, String user_id, String order_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.orderPlaced(MultipartBody.Part.createFormData("order_id", order_id), MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //OrderHistory
    public void OrderHistory(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getOrderHistory(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Table Booking
    public void TableBooking(String api, final ApiType apiTypes, String user_id, String name, String mobile,
                             String alternate_mobile, String email, String no_guest, String book_date, String time, String res_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.doTableBooking(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("name", name),
                MultipartBody.Part.createFormData("mobile", mobile),
                MultipartBody.Part.createFormData("alternate_mobile", alternate_mobile),
                MultipartBody.Part.createFormData("email", email),
                MultipartBody.Part.createFormData("no_guest", no_guest),
                MultipartBody.Part.createFormData("book_date", book_date),
                MultipartBody.Part.createFormData("time", time),
                MultipartBody.Part.createFormData("res_id", res_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //BookingDetails
    public void BookingDetails(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getBookingDetails(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RestaurantCategories
    public void RestaurantCategories(String api, final ApiType apiTypes, String res_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getRestaurantCategories(MultipartBody.Part.createFormData("res_id", res_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Restaurant Veg Category
    public void RestaurantVegCategories(String api, final ApiType apiTypes, String res_id,String type){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getVegCategories(MultipartBody.Part.createFormData("res_id", res_id),
                MultipartBody.Part.createFormData("type", type));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RestaurantMenuList
    public void RestaurantMenuList(String api, final ApiType apiTypes, String res_id, String category){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getRestaurantMenulist(MultipartBody.Part.createFormData("res_id", res_id),
                MultipartBody.Part.createFormData("category", category));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RestaurantVegFood
    public void RestaurantVegMenuList(String api, final ApiType apiTypes, String res_id, String category,String type){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getRestaurantVegFood(MultipartBody.Part.createFormData("res_id", res_id),
                MultipartBody.Part.createFormData("category", category),MultipartBody.Part.createFormData("type", type));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RestaurantOfferList
    public void RestaurantOfferList(String api, final ApiType apiTypes, String resId, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getOfferList(MultipartBody.Part.createFormData("res_id", resId),
                MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Generate Check Sum for Paytm
    public void GenerateCheckSum(String api, final ApiType apiTypes, String order_id, String cust_id, String txn_amount){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.generateCreheckSum(MultipartBody.Part.createFormData("order_id", order_id),
                MultipartBody.Part.createFormData("cust_id", cust_id),
                MultipartBody.Part.createFormData("txn_amount", txn_amount));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //PaytmResponse
    public void PaytmResponse(String api, final ApiType apiTypes, String ORDERID, String user_id, String TXNID, String TXNAMOUNT, String PAYMENTMODE,String CURRENCY,String TXNDATE,String STATUS,String RESPCODE,String RESPMSG,String GATEWAYNAME,String BANKTXNID,String BANKNAME){
        String bankName;
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        if (BANKNAME != null){
            bankName = BANKNAME;
        } else {
            bankName = "";
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getPaytmResponse(
                MultipartBody.Part.createFormData("ORDERID", ORDERID), MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("TXNID", TXNID), MultipartBody.Part.createFormData("TXNAMOUNT", TXNAMOUNT),
                MultipartBody.Part.createFormData("PAYMENTMODE", PAYMENTMODE), MultipartBody.Part.createFormData("CURRENCY", CURRENCY),
                MultipartBody.Part.createFormData("TXNDATE", TXNDATE), MultipartBody.Part.createFormData("STATUS", STATUS),
                MultipartBody.Part.createFormData("RESPCODE", RESPCODE), MultipartBody.Part.createFormData("RESPMSG", RESPMSG),
                MultipartBody.Part.createFormData("GATEWAYNAME", GATEWAYNAME), MultipartBody.Part.createFormData("BANKTXNID", BANKTXNID),
                MultipartBody.Part.createFormData("BANKNAME", bankName));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Track Location
    public void TrackLocation(String api, final ApiType apiTypes, String del_id, String order_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getLocationUpdate(MultipartBody.Part.createFormData("del_id", del_id),
                MultipartBody.Part.createFormData("order_id", order_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //User Location
    public void UserLocation(String api, final ApiType apiTypes, String order_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getUserLocation(MultipartBody.Part.createFormData("order_id", order_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }


    //Distance
    public void DistanceMap(String api, final ApiType apiTypes, String origin, String destination, String key){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getDistanceFromMap(origin, destination, key);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //My Location
    public void UpdateUserLocation(String api, final ApiType apiTypes, String id, String latitude, String longitude){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.updateMyLocation(MultipartBody.Part.createFormData("id", id),
                MultipartBody.Part.createFormData("latitude", latitude),
                MultipartBody.Part.createFormData("longitude", longitude));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Global Search
    public void GlobalSearchService(String api, final ApiType apiTypes, String user_id, String name){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getGlobalSearch(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("name", name));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Restaurant Search
    public void RestaurantSearchService(String api, final ApiType apiTypes, String user_id, String name, String resId){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getRestItemSearch(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("name", name),
                MultipartBody.Part.createFormData("res_id", resId));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Restaurant Search
    public void WalletBal(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getWalletBalance(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Restaurant Search
    public void ResRating(String api, final ApiType apiTypes, String res_id, String user_id, String rating,
                          String feedback, String type, String cart_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getResRating(MultipartBody.Part.createFormData("res_id", res_id),
                MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("rating", rating),
                MultipartBody.Part.createFormData("feedback", feedback),
                MultipartBody.Part.createFormData("type", type),
                MultipartBody.Part.createFormData("cart_id", cart_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }


    //Restaurant Search
    public void DelRating(String api, final ApiType apiTypes, String del_id, String user_id, String rating,
                          String feedback, String type, String cart_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getDelRating(MultipartBody.Part.createFormData("del_id", del_id),
                MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("rating", rating),
                MultipartBody.Part.createFormData("feedback", feedback),
                MultipartBody.Part.createFormData("type", type),
                MultipartBody.Part.createFormData("cart_id", cart_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Restaurant Search
    public void RestaurantRating(String api, final ApiType apiTypes, String res_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getRestuarnatRating(MultipartBody.Part.createFormData("res_id", res_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Restaurant Search
    public void PromoCodes(String api, final ApiType apiTypes){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getPromoCodes();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }


    //Promo Apply
    public void PromoApply(String api, final ApiType apiTypes, String user_id, String cart_id, String promo_code){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.applyPromo(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("cart_id", cart_id),
                MultipartBody.Part.createFormData("promo_code", promo_code));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Best Selling
    public void BestSellingList(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.bestSelling(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //AddInstructions
    public void AddInstructions(String api, final ApiType apiTypes, String user_id, String cart_id, String instructions){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.addInstructions(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("cart_id", cart_id),
                MultipartBody.Part.createFormData("instructions", instructions));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    public void RestAddTip(String api, final ApiType apiTypes, String user_id,String cart_id,String del_id,String order_id,String tip){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.restAddTip(MultipartBody.Part.createFormData("user_id", user_id),MultipartBody.Part.createFormData("cart_id", cart_id),MultipartBody.Part.createFormData("del_id", del_id),
                MultipartBody.Part.createFormData("order_id", order_id),MultipartBody.Part.createFormData("tip", tip));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }
    public void GenerateTipsCheckSum(String api, final ApiType apiTypes, String tip_no, String cust_id,String txn_amount){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.generatetipsCreheckSum(MultipartBody.Part.createFormData("tip_no", tip_no),
                MultipartBody.Part.createFormData("cust_id", cust_id),MultipartBody.Part.createFormData("txn_amount", txn_amount));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }
    public void TipsPaytmResponse(String api, final ApiType apiTypes, String ORDERID, String user_id, String TXNID, String TXNAMOUNT, String PAYMENTMODE,String CURRENCY,String TXNDATE,String STATUS,String RESPCODE,String RESPMSG,String GATEWAYNAME,String BANKTXNID,String BANKNAME){
        String bankName;
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        if (BANKNAME != null){
            bankName = BANKNAME;
        } else {
            bankName = "";
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getTipsPaytmResponse(
                MultipartBody.Part.createFormData("ORDERID", ORDERID), MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("TXNID", TXNID), MultipartBody.Part.createFormData("TXNAMOUNT", TXNAMOUNT),
                MultipartBody.Part.createFormData("PAYMENTMODE", PAYMENTMODE), MultipartBody.Part.createFormData("CURRENCY", CURRENCY),
                MultipartBody.Part.createFormData("TXNDATE", TXNDATE), MultipartBody.Part.createFormData("STATUS", STATUS),
                MultipartBody.Part.createFormData("RESPCODE", RESPCODE), MultipartBody.Part.createFormData("RESPMSG", RESPMSG),
                MultipartBody.Part.createFormData("GATEWAYNAME", GATEWAYNAME), MultipartBody.Part.createFormData("BANKTXNID", BANKTXNID),
                MultipartBody.Part.createFormData("BANKNAME", bankName));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    public void GetCityList(String api, final ApiType apiTypes){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getCityLIst();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    public void TakeAway(String api, final ApiType apiTypes, String user_id, String cart_id, String order_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getTakeAway(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("cart_id", cart_id),
                MultipartBody.Part.createFormData("order_id", order_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }



    public void ConfirmTakeAway(String api, final ApiType apiTypes, String cart_id, String order_id, String user_id, String otp){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.confirmTakeAway(MultipartBody.Part.createFormData("cart_id", cart_id),
                MultipartBody.Part.createFormData("order_id", order_id),
                MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("otp", otp));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //service booking list
    public void ServiceHistory(String api, final ApiType apiTypes, String user_id){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getservicehistory(MultipartBody.Part.createFormData("user_id", user_id));
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }
    //service offer
    public void ServiceOffer(String api, final ApiType apiTypes){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getServiceImages();
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                try {
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                try {
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(null, apiTypeVariable, false);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        });
    }

    //Service Book
    public void BookAService(String api, final ApiType apiTypes, String name,String user_id,String phone,String email,String category,
                             String problem,String book_date,String book_time,String imagefile, String imagepath){
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);

        if (!imagepath.equals("") || !imagefile.isEmpty()){
            File fileAdharFront = new File(imagepath);
            byte[] bytes = new byte[0];
            try {
                FileInputStream fis = new FileInputStream(fileAdharFront);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                while (true) {
                    int readNum = fis.read(buf);
                    if (readNum == -1) {
                        break;
                    }
                    bos.write(buf, 0, readNum);
                    System.out.println("read " + readNum + " bytes,");
                }
                bytes = bos.toByteArray();
                fis.close();
                bos.flush();
                bos.close();
            } catch (IOException e2) {
            }

            RequestBody fbodyAdharFront = RequestBody.create(MediaType.parse("image/*"), fileAdharFront);
            call = (Call<T>) gi.bookaservice(MultipartBody.Part.createFormData("name", name),
                    MultipartBody.Part.createFormData("user_id", user_id),
                    MultipartBody.Part.createFormData("phone", phone),
                    MultipartBody.Part.createFormData("email", email),
                    MultipartBody.Part.createFormData("category", category),
                    MultipartBody.Part.createFormData("problem", problem),
                    MultipartBody.Part.createFormData("book_date", book_date),
                    MultipartBody.Part.createFormData("book_time", book_time),
                    MultipartBody.Part.createFormData("image", imagefile, fbodyAdharFront));
        } else {
            call = (Call<T>) gi.bookaservice(MultipartBody.Part.createFormData("name", name),
                    MultipartBody.Part.createFormData("user_id", user_id),
                    MultipartBody.Part.createFormData("phone", phone),
                    MultipartBody.Part.createFormData("email", email),
                    MultipartBody.Part.createFormData("category", category),
                    MultipartBody.Part.createFormData("problem", problem),
                    MultipartBody.Part.createFormData("book_date", book_date),
                    MultipartBody.Part.createFormData("book_time", book_time),
                    MultipartBody.Part.createFormData("image", ""));

        }

        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    public enum ApiType {
        login,
        products,
        verifyOtp,
        resendOtp,
        productCategoryList,
        productDetails,
        editProfile,
        restaurantList,
        breakfastList,
        snackList,
        restaurantDetails,
        addToCart,
        cartList,
        updateCartItem,
        removeCartItem,
        addAddress,
        addressList,
        placeOrder,
        orderHistory,
        sliderImages,
        sliderImagesDown,
        tablebooking,
        bookingdetails,
        selectaddress,
        restaurantcategories,
        restaurantmenulist,
        restaurantvegcategories,
        restaurantvegmenulist,
        restaurantofferlist,
        selectService,
        checksum,
        storeList,
        selectStore,
        paytmresponse,
        tracklocation,
        userlocation,
        mapdistance,
        mapdistanceRes,
        mylocation,
        gsearchn,
        restsrch,
        walletbal,
        delRat,
        resRat,
        promocode,
        promoapply,
        bestselling,
        instructions,
        restdeliverytip,
        tipschecksum,
        tipspaytmresponse,
        citylist,
        takeaway,
        otpt,
        getservicehistory,
        getserviceoffer,
        bservice
    }

}
