
package com.geekhive.foodey.Cakes.beans.cakestorebycategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StorebyCategory {

    @SerializedName("CakeStore")
    @Expose
    private List<CakeStore> cakeStore = null;

    public List<CakeStore> getCakeStore() {
        return cakeStore;
    }

    public void setCakeStore(List<CakeStore> cakeStore) {
        this.cakeStore = cakeStore;
    }

}
