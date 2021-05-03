
package com.geekhive.foodey.Cakes.beans.cakechecksumPaytm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeChecksum {

    @SerializedName("GenerateChecksum")
    @Expose
    private CakeGenerateChecksum generateChecksum;

    public CakeGenerateChecksum getGenerateChecksum() {
        return generateChecksum;
    }

    public void setGenerateChecksum(CakeGenerateChecksum generateChecksum) {
        this.generateChecksum = generateChecksum;
    }

}
