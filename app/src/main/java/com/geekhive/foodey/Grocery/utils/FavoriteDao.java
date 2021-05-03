package com.geekhive.foodey.Grocery.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.migration.Migration;

import com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    public void addData(FavoriteList favoriteList);

    @Query("select * from favoritelist")
    public List<FavoriteList> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    public int isFavorite(String id);

    @Delete
    public void delete(FavoriteList favoriteList);

}
