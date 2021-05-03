
package com.geekhive.foodey.Grocery.beans.groceryglobalsearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Grocery")
    @Expose
    private List<Grocery> grocery = null;
    @SerializedName("Store")
    @Expose
    private List<Store> store = null;

    public List<Grocery> getGrocery() {
        return grocery;
    }

    public void setGrocery(List<Grocery> grocery) {
        this.grocery = grocery;
    }

    public List<Store> getStore() {
        return store;
    }

    public void setStore(List<Store> store) {
        this.store = store;
    }

}
