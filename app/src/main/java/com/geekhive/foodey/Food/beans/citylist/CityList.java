
package com.geekhive.foodey.Food.beans.citylist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityList {

    @SerializedName("City")
    @Expose
    private List<City> city = null;

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

}
