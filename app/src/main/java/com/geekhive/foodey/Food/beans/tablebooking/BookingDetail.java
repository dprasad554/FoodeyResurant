
package com.geekhive.foodey.Food.beans.tablebooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingDetail {

    @SerializedName("Booking")
    @Expose
    private Booking booking;
    @SerializedName("Resturant")
    @Expose
    private Resturant resturant;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

}
