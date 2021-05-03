
package com.geekhive.foodey.Grocery.beans.grocerychecksumPaytm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryChecksum {

    @SerializedName("GenerateChecksum")
    @Expose
    private GroceryGenerateChecksum generateChecksum;

    public GroceryGenerateChecksum getGenerateChecksum() {
        return generateChecksum;
    }

    public void setGenerateChecksum(GroceryGenerateChecksum generateChecksum) {
        this.generateChecksum = generateChecksum;
    }

}
