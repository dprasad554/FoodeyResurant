
package com.geekhive.foodey.Cakes.beans.cakeupdatelocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CakeTrackLocation {

    @SerializedName("Details")
    @Expose
    private List<CakeDetail> details = null;

    public List<CakeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CakeDetail> details) {
        this.details = details;
    }

}
