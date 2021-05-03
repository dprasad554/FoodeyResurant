package com.geekhive.foodey.Cakes.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.geekhive.foodey.Cakes.beans.cakefavoritelist.CakeFavoriteList;

import java.util.List;

@Dao
public interface CakeFavoriteDao {
    @Insert
    public void addData(CakeFavoriteList favoriteList);

    @Query("select * from favoritelist")
    public List<CakeFavoriteList> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    public int isFavorite(String id);

    @Delete
    public void delete(CakeFavoriteList favoriteList);

}
