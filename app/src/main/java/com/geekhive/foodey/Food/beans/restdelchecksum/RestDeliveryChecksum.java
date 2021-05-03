
package com.geekhive.foodey.Food.beans.restdelchecksum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestDeliveryChecksum {

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
