
package com.geekhive.foodey.Cakes.beans.cakeuserlocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeUserLocation {

    @SerializedName("Address")
    @Expose
    private CakeAddress address;

    public CakeAddress getAddress() {
        return address;
    }

    public void setAddress(CakeAddress address) {
        this.address = address;
    }

}
