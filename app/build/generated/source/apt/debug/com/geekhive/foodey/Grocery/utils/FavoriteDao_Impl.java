package com.geekhive.foodey.Grocery.utils;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class FavoriteDao_Impl implements FavoriteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFavoriteList;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFavoriteList;

  public FavoriteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavoriteList = new EntityInsertionAdapter<FavoriteList>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `favoritelist`(`id`,`image`,`prname`,`prquantity`,`prprice`,`prmrp`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavoriteList value) {
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
      }
    };
    this.__deletionAdapterOfFavoriteList = new EntityDeletionOrUpdateAdapter<FavoriteList>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favoritelist` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavoriteList value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
  }

  @Override
  public void addData(FavoriteList favoriteList) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavoriteList.insert(favoriteList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(FavoriteList favoriteList) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFavoriteList.handle(favoriteList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<FavoriteList> getFavoriteData() {
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
      final List<FavoriteList> _result = new ArrayList<FavoriteList>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FavoriteList _item;
        _item = new FavoriteList();
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
