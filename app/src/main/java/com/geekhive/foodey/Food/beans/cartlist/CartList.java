
package com.geekhive.foodey.Food.beans.cartlist;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList implements Serializable, Parcelable
{

    @SerializedName("CartList")
    @Expose
    private CartList_ cartList;
    public final static Creator<CartList> CREATOR = new Creator<CartList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CartList createFromParcel(Parcel in) {
            return new CartList(in);
        }

        public CartList[] newArray(int size) {
            return (new CartList[size]);
        }

    }
    ;
    private final static long serialVersionUID = 644380440216388903L;

    protected CartList(Parcel in) {
        this.cartList = ((CartList_) in.readValue((CartList_.class.getClassLoader())));
    }

    public CartList() {
    }

    public CartList_ getCartList() {
        return cartList;
    }

    public void setCartList(CartList_ cartList) {
        this.cartList = cartList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cartList);
    }

    public int describeContents() {
        return  0;
    }

}
