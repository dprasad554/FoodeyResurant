
package com.geekhive.foodey.Grocery.beans.grocerysubcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroceryProductSubCategory {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("product_category_id")
    @Expose
    private String productCategoryId;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
