
package com.geekhive.foodey.Cakes.beans.cakeaddnewaddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeResult {

    @SerializedName("message")
    @Expose
    private CakeMessage message;

    public CakeMessage getMessage() {
        return message;
    }

    public void setMessage(CakeMessage message) {
        this.message = message;
    }

}
