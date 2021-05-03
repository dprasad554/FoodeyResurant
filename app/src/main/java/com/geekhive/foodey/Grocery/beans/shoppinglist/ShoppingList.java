package com.geekhive.foodey.Grocery.beans.shoppinglist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName="shoppinglist")
public class ShoppingList {


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

//    public boolean isSelected() {
//        return isSelected;
//    }
//
//    public void setSelected(boolean selected) {
//        isSelected = selected;
//    }

}
