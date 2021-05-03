package com.geekhive.foodey.Cakes.utils;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geekhive.foodey.Cakes.beans.cakefavoritelist.CakeFavoriteList;

@Database(entities={CakeFavoriteList.class},version = 4, exportSchema = false)
public abstract class CakeFavoriteDatabase extends RoomDatabase {

    public abstract CakeFavoriteDao favoriteDao();

}
