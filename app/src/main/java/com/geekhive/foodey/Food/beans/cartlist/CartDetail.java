
package com.geekhive.foodey.Food.beans.cartlist;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartDetail implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("food_id")
    @Expose
    private String foodId;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("mrp")
    @Expose
    private String mrp;
    @SerializedName("Food")
    @Expose
    private Food food;
    public final static Creator<CartDetail> CREATOR = new Creator<CartDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CartDetail createFromParcel(Parcel in) {
            return new CartDetail(in);
        }

        public CartDetail[] newArray(int size) {
            return (new CartDetail[size]);
        }

    }
    ;
    private final static long serialVersionUID = -2876583635128635845L;

    protected CartDetail(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.cartId = ((String) in.readValue((String.class.getClassLoader())));
        this.foodId = ((String) in.readValue((String.class.getClassLoader())));
        this.quantity = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.discount = ((String) in.readValue((String.class.getClassLoader())));
        this.mrp = ((String) in.readValue((String.class.getClassLoader())));
        this.food = ((Food) in.readValue((Food.class.getClassLoader())));
    }

    public CartDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(cartId);
        dest.writeValue(foodId);
        dest.writeValue(quantity);
        dest.writeValue(price);
        dest.writeValue(date);
        dest.writeValue(discount);
        dest.writeValue(mrp);
        dest.writeValue(food);
    }

    public int describeContents() {
        return  0;
    }

}
