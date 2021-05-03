package com.geekhive.foodey.Grocery.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.migration.Migration;

import com.geekhive.foodey.Grocery.beans.shoppinglist.ShoppingList;

import java.util.List;

@Dao
public interface ShoppingListDao {
    @Insert
    public void addData(ShoppingList shoppingList);

    @Query("select * from shoppingList")
    public List<ShoppingList> getShoppingData();

    @Query("SELECT EXISTS (SELECT 1 FROM shoppingList WHERE id=:id)")
    public int isShoppingList(String id);

    @Delete
    public void delete(ShoppingList shoppingList);

}

