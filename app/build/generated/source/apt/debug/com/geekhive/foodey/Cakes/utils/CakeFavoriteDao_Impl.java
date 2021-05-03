package com.geekhive.foodey.Cakes.utils;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.geekhive.foodey.Cakes.beans.cakefavoritelist.CakeFavoriteList;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class CakeFavoriteDao_Impl implements CakeFavoriteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCakeFavoriteList;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCakeFavoriteList;

  public CakeFavoriteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCakeFavoriteList = new EntityInsertionAdapter<CakeFavoriteList>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `favoritelist`(`id`,`image`,`prname`,`prquantity`,`prprice`,`prmrp`,`pstoreid`,`pstorename`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CakeFavoriteList value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getImage() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getImage());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getQuantity() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getQuantity());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPrice());
        }
        if (value.getMrp() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMrp());
        }
        if (value.getStoreid() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStoreid());
        }
        if (value.getStorename() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getStorename());
        }
      }
    };
    this.__deletionAdapterOfCakeFavoriteList = new EntityDeletionOrUpdateAdapter<CakeFavoriteList>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favoritelist` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CakeFavoriteList value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
  }

  @Override
  public void addData(CakeFavoriteList favoriteList) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCakeFavoriteList.insert(favoriteList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(CakeFavoriteList favoriteList) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfCakeFavoriteList.handle(favoriteList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CakeFavoriteList> getFavoriteData() {
    final String _sql = "select * from favoritelist";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("prname");
      final int _cursorIndexOfQuantity = _cursor.getColumnIndexOrThrow("prquantity");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("prprice");
      final int _cursorIndexOfMrp = _cursor.getColumnIndexOrThrow("prmrp");
      final int _cursorIndexOfStoreid = _cursor.getColumnIndexOrThrow("pstoreid");
      final int _cursorIndexOfStorename = _cursor.getColumnIndexOrThrow("pstorename");
      final List<CakeFavoriteList> _result = new ArrayList<CakeFavoriteList>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CakeFavoriteList _item;
        _item = new CakeFavoriteList();
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item.setImage(_tmpImage);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpQuantity;
        _tmpQuantity = _cursor.getString(_cursorIndexOfQuantity);
        _item.setQuantity(_tmpQuantity);
        final String _tmpPrice;
        _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final String _tmpMrp;
        _tmpMrp = _cursor.getString(_cursorIndexOfMrp);
        _item.setMrp(_tmpMrp);
        final String _tmpStoreid;
        _tmpStoreid = _cursor.getString(_cursorIndexOfStoreid);
        _item.setStoreid(_tmpStoreid);
        final String _tmpStorename;
        _tmpStorename = _cursor.getString(_cursorIndexOfStorename);
        _item.setStorename(_tmpStorename);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int isFavorite(String id) {
    final String _sql = "SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
