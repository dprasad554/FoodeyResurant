
package com.geekhive.foodey.Cakes.beans.cakeaddmedia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeAddmedia {

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
