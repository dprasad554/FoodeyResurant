
package com.geekhive.foodey.Food.beans.loginout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginOut {

    @SerializedName("User")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
