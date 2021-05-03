
package com.geekhive.foodey.Grocery.beans.grocerychecksum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryTipChecksum {

    @SerializedName("GenerateChecksum")
    @Expose
    private GenerateChecksum generateChecksum;

    public GenerateChecksum getGenerateChecksum() {
        return generateChecksum;
    }

    public void setGenerateChecksum(GenerateChecksum generateChecksum) {
        this.generateChecksum = generateChecksum;
    }

}
