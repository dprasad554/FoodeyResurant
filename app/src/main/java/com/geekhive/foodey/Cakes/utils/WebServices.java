package com.geekhive.foodey.Cakes.utils;

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

    public static String Foodey_Cake_Services = "http://foodeyservices.com/WebServices/";
    public static String Foodey_Grocery_Image_URL = "http://foodeyservices.com/img/cake/";
    public static String Foodey_Cakelocation_Services = "http://foodeyservices.com/WebServices/";
    public static String Foodey_Cakes_Image_Slider_URL = "http://foodeyservices.com/img/slider/";
    public static String Foodey_Cakes_Image_Store = "http://foodeyservices.com/img/cakestore/";

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
    //Cakecategory
    public void Cakecategory(String api, final ApiType apiTypes,String store_id){
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
        call = (Call<T>) gi.cakecategory(MultipartBody.Part.createFormData("store_id", store_id));
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
    //CakeStore
    public void CakeStore(String api, final ApiType apiTypes,String user_id){
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
        call = (Call<T>) gi.cakestore(MultipartBody.Part.createFormData("user_id", user_id));
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
    //Cakeslider
    public void Cakeslider(String api, final ApiType apiTypes){
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
        call = (Call<T>) gi.cakeslider();
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
    //CakeStore
    public void CakeOffer(String api, final ApiType apiTypes,String user_id){
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
        call = (Call<T>) gi.cakeoffer((MultipartBody.Part.createFormData("user_id", user_id)));
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
    //CallSearchService
    public void CallSearchService(String api, final ApiType apiTypes,String name){
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
        call = (Call<T>) gi.searchservice(name);
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
  /*  //Bestoffer
    public void Bestoffer(String api, final ApiType apiTypes,String user_id){
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
        call = (Call<T>) gi.cakeoffer((MultipartBody.Part.createFormData("user_id", user_id)));
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

    }*/
    //Productlist
    public void Productlist(String api, final ApiType apiTypes,String product_category,String store_id){
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
        call = (Call<T>) gi.productlist(
                MultipartBody.Part.createFormData("product_category", product_category),
                MultipartBody.Part.createFormData("store_id", store_id));
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
    //Addtocart
    public void Addtocart(String api, final ApiType apiTypes,String user_id,String store_id,String product_id
    ,String quantity,String price,String mrp,String message,String s_date,String s_time,String image, boolean fileImage,String distance, String city_id){
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

        File file = new File(image);
        byte[] bytes = new byte[0];
        try {
            FileInputStream fis = new FileInputStream(file);
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


        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);

        if (fileImage){
            call = (Call<T>) gi.addtocart(
                    MultipartBody.Part.createFormData("user_id", user_id),
                    MultipartBody.Part.createFormData("store_id", store_id),
                    MultipartBody.Part.createFormData("product_id", product_id),
                    MultipartBody.Part.createFormData("quantity", quantity),
                    MultipartBody.Part.createFormData("price", price),
                    MultipartBody.Part.createFormData("mrp", mrp),
                    MultipartBody.Part.createFormData("message", message),
                    MultipartBody.Part.createFormData("s_date", s_date),
                    MultipartBody.Part.createFormData("s_time", s_time),
                    MultipartBody.Part.createFormData("image", image,fbody),
                    MultipartBody.Part.createFormData("distance", distance),
                    MultipartBody.Part.createFormData("city_id", city_id));
        } else {
            call = (Call<T>) gi.addtocart(
                    MultipartBody.Part.createFormData("user_id", user_id),
                    MultipartBody.Part.createFormData("store_id", store_id),
                    MultipartBody.Part.createFormData("product_id", product_id),
                    MultipartBody.Part.createFormData("quantity", quantity),
                    MultipartBody.Part.createFormData("price", price),
                    MultipartBody.Part.createFormData("mrp", mrp),
                    MultipartBody.Part.createFormData("message", message),
                    MultipartBody.Part.createFormData("s_date", s_date),
                    MultipartBody.Part.createFormData("s_time", s_time),
                    MultipartBody.Part.createFormData("image", ""),
                    MultipartBody.Part.createFormData("distance", distance),
                    MultipartBody.Part.createFormData("city_id", city_id));
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
    public void UpdateCartItem(String api, final ApiType apiTypes, String user_id, String cart_id, String quantity, String cartdetail_id,String distance){
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
    public void RemoveCartItem(String api, final ApiType apiTypes, String cartdetail_id, String cart_id,String distance){
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
    //GetCouponList
    public void GetCouponList(String api, final ApiType apiTypes){
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
        call = (Call<T>) gi.couponlist();
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
    //HistoryDetails
    public void HistoryDetails(String api, final ApiType apiTypes, String user_id){
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
        call = (Call<T>) gi.historydetails(MultipartBody.Part.createFormData("user_id", user_id));
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
    public void SelectAddress(String api, final ApiType apiTypes, String user_id, String address_id){
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
        call = (Call<T>) gi.selectAddress(MultipartBody.Part.createFormData("user_id", user_id), MultipartBody.Part.createFormData("address_id", address_id));
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
    //GenerateTipsCheckSum
    public void GenerateTipsCheckSum(String api, final ApiType apiTypes, String order_id, String cust_id,String txn_amount){
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
        call = (Call<T>) gi.generatetipsCreheckSum(MultipartBody.Part.createFormData("tip_no", order_id),
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
    public void UpdateUserLocation(String api, final WebServices.ApiType apiTypes, String id, String latitude, String longitude){
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
    public void GlobalSearchService(String api, final WebServices.ApiType apiTypes, String user_id, String name){
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
        call = (Call<T>) gi.getRestuarnatRating(MultipartBody.Part.createFormData("store_id", res_id));
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
    public void PromoApply(String api, final WebServices.ApiType apiTypes, String user_id, String cart_id, String promo_code){
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

    //Restaurant Search
    public void ResRating(String api, final WebServices.ApiType apiTypes, String res_id, String user_id, String rating,
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
        call = (Call<T>) gi.giveResRating(MultipartBody.Part.createFormData("res_id", res_id),
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
    public void DelRating(String api, final WebServices.ApiType apiTypes, String del_id, String user_id, String rating,
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
    //CakeBestSelling
    public void CakeBestSelling(String api, final WebServices.ApiType apiTypes, String user_id){
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
        call = (Call<T>) gi.getbestselling(MultipartBody.Part.createFormData("user_id", user_id));
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
    //CakeBestSelling
    public void Addtips(String api, final WebServices.ApiType apiTypes, String user_id,String cart_id,String del_id,String order_id,String tip){
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
        call = (Call<T>) gi.addtips(MultipartBody.Part.createFormData("user_id", user_id),
                MultipartBody.Part.createFormData("cart_id", cart_id),
                MultipartBody.Part.createFormData("del_id", del_id),
                MultipartBody.Part.createFormData("order_id", order_id),
                MultipartBody.Part.createFormData("tip", tip));
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

    //CakeBestSelling
    public void CakeCategories(String api, final WebServices.ApiType apiTypes){
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
        call = (Call<T>) gi.cakecategory();
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
    //CakeBestSelling
    public void CakeStoreByCategories(String api, final WebServices.ApiType apiTypes,String user_id,String category_name){
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
        call = (Call<T>) gi.cakestorebycategory(MultipartBody.Part.createFormData("user_id", user_id),MultipartBody.Part.createFormData("category_name", category_name));
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

    public void GetCityList(String api, final WebServices.ApiType apiTypes){
        com.geekhive.foodey.Food.utils.ProgressDialog progressDialog = new com.geekhive.foodey.Food.utils.ProgressDialog();
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
        call = (Call<T>) gi.getCityList();
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
    /*//PaytmResponse
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
    }*/
    public enum ApiType {
        cakecategory,
        cakestore,
        productlistspecial,
        productlistcool,
        productlistnormal,
        productlistflower,
        productlistother,
        productlist,
        addtocart,
        cartList,
        updateCartItem,
        removeCartItem,
        addAddress,
        addressList,
        selectaddress,
        placeOrder,
        history,
        checksum,
        paytmresponse,
        tracklocation,
        userlocation,
        mapdistance,
        cakeoffer,
        bestoffer,
        cakeslider,
        callsearchservice,
        mylocation,
        couponlist,
        gsearchn,
        resRat,
        mapdistanceRes,
        promoapply,
        delRat,
        giveresturantrating,
        cakebestselling,
        addtips,
        tipschecksum,
        tipspaytmresponse,
        cakecategorylist,
        cakestorebycategory,
        citylist

    }

}
