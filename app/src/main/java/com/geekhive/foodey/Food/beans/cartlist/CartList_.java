
package com.geekhive.foodey.Food.beans.cartlist;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList_ implements Serializable, Parcelable
{

    @SerializedName("Cart")
    @Expose
    private Cart cart;
    @SerializedName("Resturant")
    @Expose
    private Resturant resturant;
    @SerializedName("CartDetail")
    @Expose
    private List<CartDetail> cartDetail = null;
    public final static Creator<CartList_> CREATOR = new Creator<CartList_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CartList_ createFromParcel(Parcel in) {
            return new CartList_(in);
        }

        public CartList_[] newArray(int size) {
            return (new CartList_[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1980458896252626194L;

    protected CartList_(Parcel in) {
        this.cart = ((Cart) in.readValue((Cart.class.getClassLoader())));
        this.resturant = ((Resturant) in.readValue((Resturant.class.getClassLoader())));
        in.readList(this.cartDetail, (CartDetail.class.getClassLoader()));
    }

    public CartList_() {
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

    public List<CartDetail> getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(List<CartDetail> cartDetail) {
        this.cartDetail = cartDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cart);
        dest.writeValue(resturant);
        dest.writeList(cartDetail);
    }

    public int describeContents() {
        return  0;
    }

}
