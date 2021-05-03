
package com.geekhive.foodey.Cakes.beans.cakeglobalsearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Cake")
    @Expose
    private List<Cake> cake = null;
    @SerializedName("CakeStore")
    @Expose
    private List<CakeStore> cakeStore = null;

    public List<Cake> getCake() {
        return cake;
    }

    public void setCake(List<Cake> food) {
        this.cake = food;
    }

    public List<CakeStore> getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(List<CakeStore> cakeStore) {
        this.cakeStore = cakeStore;
    }
}
