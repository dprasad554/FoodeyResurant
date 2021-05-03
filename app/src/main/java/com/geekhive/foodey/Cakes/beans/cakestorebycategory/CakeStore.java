
package com.geekhive.foodey.Cakes.beans.cakestorebycategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeStore {

    @SerializedName("CakeStore")
    @Expose
    private CakeStore_ cakeStore;

    public CakeStore_ getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(CakeStore_ cakeStore) {
        this.cakeStore = cakeStore;
    }

}
