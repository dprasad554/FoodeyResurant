
package com.geekhive.foodey.Food.beans.servicehistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceHistory {

    @SerializedName("Service")
    @Expose
    private List<Service> service = null;

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

}
