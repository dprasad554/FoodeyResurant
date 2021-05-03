/*
package com.geekhive.foodey.Grocery.utils;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList;
import com.geekhive.foodey.Grocery.beans.shoppinglist.ShoppingList;

@Database(entities={ShoppingList.class},version = 3, exportSchema = false)
public abstract class ShoppingListDatabase extends RoomDatabase {

    public abstract ShoppingListDao shoppingListDao();

}
*/
package com.geekhive.foodey.Grocery.utils;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geekhive.foodey.Grocery.beans.shoppinglist.ShoppingList;

@Database(entities={ShoppingList.class},version = 3, exportSchema = false)
public abstract class ShoppingListDatabase extends RoomDatabase {

    public abstract ShoppingListDao shoppingListDao();

}

