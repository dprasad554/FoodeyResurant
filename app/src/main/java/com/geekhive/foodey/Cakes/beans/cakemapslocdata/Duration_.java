
package com.geekhive.foodey.Cakes.beans.cakemapslocdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration_ {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private Long value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
