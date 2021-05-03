
package com.geekhive.foodey.Food.beans.checksumPaytm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Checksum {

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
