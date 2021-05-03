
package com.geekhive.foodey.Grocery.beans.grocerystorename;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store implements Serializable, Parcelable
{

    @SerializedName("Store")
    @Expose
    private Store_ store;
    public final static Creator<Store> CREATOR = new Creator<Store>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        public Store[] newArray(int size) {
            return (new Store[size]);
        }

    }
    ;
    private final static long serialVersionUID = -6965013820868595488L;

    protected Store(Parcel in) {
        this.store = ((Store_) in.readValue((Store_.class.getClassLoader())));
    }

    public Store() {
    }

    public Store_ getStore() {
        return store;
    }

    public void setStore(Store_ store) {
        this.store = store;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(store);
    }

    public int describeContents() {
        return  0;
    }

}
