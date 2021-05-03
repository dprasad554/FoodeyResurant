
package com.geekhive.foodey.Food.beans.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletBalance {

    @SerializedName("UserWallet")
    @Expose
    private UserWallet userWallet;

    public UserWallet getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(UserWallet userWallet) {
        this.userWallet = userWallet;
    }

}
