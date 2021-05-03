
package com.geekhive.foodey.Grocery.beans.grocerystorename;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryStoreNameList implements Serializable, Parcelable
{

    @SerializedName("Store")
    @Expose
    private List<Store> store = null;
    public final static Creator<GroceryStoreNameList> CREATOR = new Creator<GroceryStoreNameList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GroceryStoreNameList createFromParcel(Parcel in) {
            return new GroceryStoreNameList(in);
        }

        public GroceryStoreNameList[] newArray(int size) {
            return (new GroceryStoreNameList[size]);
        }

    }
    ;
    private final static long serialVersionUID = -2823018144703901614L;

    protected GroceryStoreNameList(Parcel in) {
        in.readList(this.store, (Store.class.getClassLoader()));
    }

    public GroceryStoreNameList() {
    }

    public List<Store> getStore() {
        return store;
    }

    public void setStore(List<Store> store) {
        this.store = store;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(store);
    }

    public int describeContents() {
        return  0;
    }

}
