package com.geekhive.foodey.Cakes.beans.cakefavoritelist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName="favoritelist")
public class CakeFavoriteList {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "prname")
    private String name;

    @ColumnInfo(name = "prquantity")
    private String quantity;

    @ColumnInfo(name = "prprice")
    private String price;

    @ColumnInfo(name = "prmrp")
    private String mrp;

    @ColumnInfo(name = "pstoreid")
    private String storeid;

    @ColumnInfo(name = "pstorename")
    private String storename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String name) {
        this.quantity = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String name) {
        this.price = name;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String name) {
        this.mrp = name;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String name) {
        this.storeid = name;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String name) {
        this.storename = name;
    }


}
