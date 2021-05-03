
package com.geekhive.foodey.Food.beans.updatelocation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackLocation {

    @SerializedName("Details")
    @Expose
    private List<Detail> details = null;

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

}
