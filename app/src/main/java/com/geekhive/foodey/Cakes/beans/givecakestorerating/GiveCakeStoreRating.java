
package com.geekhive.foodey.Cakes.beans.givecakestorerating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GiveCakeStoreRating {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
