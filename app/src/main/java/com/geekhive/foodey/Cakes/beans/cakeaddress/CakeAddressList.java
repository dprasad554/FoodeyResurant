
package com.geekhive.foodey.Cakes.beans.cakeaddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CakeAddressList {

    @SerializedName("Address")
    @Expose
    private List<CakeAddress> address = null;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CakeAddress> getAddress() {
        return address;
    }

    public void setAddress(List<CakeAddress> address) {
        this.address = address;
    }

}
