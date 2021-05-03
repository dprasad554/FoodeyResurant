
package com.geekhive.foodey.Cakes.beans.cakeglobalsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakeGlobalSearch {

    @SerializedName("Search")
    @Expose
    private Search search;

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

}
