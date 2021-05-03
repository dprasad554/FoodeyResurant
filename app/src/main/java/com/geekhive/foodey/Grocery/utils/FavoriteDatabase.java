package com.geekhive.foodey.Grocery.utils;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList;

@Database(entities={FavoriteList.class},version = 3, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

}
