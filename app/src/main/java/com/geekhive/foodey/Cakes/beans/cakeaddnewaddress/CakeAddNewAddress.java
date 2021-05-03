
package com.geekhive.foodey.Cakes.beans.cakeaddnewaddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeAddNewAddress {

    @SerializedName("result")
    @Expose
    private CakeResult result;

    public CakeResult getResult() {
        return result;
    }

    public void setResult(CakeResult result) {
        this.result = result;
    }

}
