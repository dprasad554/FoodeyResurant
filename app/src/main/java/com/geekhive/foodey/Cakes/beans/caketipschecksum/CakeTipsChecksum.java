
package com.geekhive.foodey.Cakes.beans.caketipschecksum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeTipsChecksum {

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
