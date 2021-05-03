
package com.geekhive.foodey.Food.beans.tablebooking;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingDetails {

    @SerializedName("BookDetail")
    @Expose
    private List<BookDetail> bookDetail = null;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookDetail> getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(List<BookDetail> bookDetail) {
        this.bookDetail = bookDetail;
    }

}
