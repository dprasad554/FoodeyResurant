
package com.geekhive.foodey.Grocery.beans.recentpurchase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentPurchase {

    @SerializedName("RecentPurchase")
    @Expose
    private RecentPurchase_ recentPurchase;

    public RecentPurchase_ getRecentPurchase() {
        return recentPurchase;
    }

    public void setRecentPurchase(RecentPurchase_ recentPurchase) {
        this.recentPurchase = recentPurchase;
    }

}
