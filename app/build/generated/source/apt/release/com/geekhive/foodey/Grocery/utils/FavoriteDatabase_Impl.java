package com.geekhive.foodey.Grocery.utils;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class FavoriteDatabase_Impl extends FavoriteDatabase {
  private volatile FavoriteDao _favoriteDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `favoritelist` (`id` TEXT NOT NULL, `image` TEXT, `prname` TEXT, `prquantity` TEXT, `prprice` TEXT, `prmrp` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"277f2f9c54cf01d0b4984a2e8e14ee36\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `favoritelist`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFavoritelist = new HashMap<String, TableInfo.Column>(6);
        _columnsFavoritelist.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsFavoritelist.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        _columnsFavoritelist.put("prname", new TableInfo.Column("prname", "TEXT", false, 0));
        _columnsFavoritelist.put("prquantity", new TableInfo.Column("prquantity", "TEXT", false, 0));
        _columnsFavoritelist.put("prprice", new TableInfo.Column("prprice", "TEXT", false, 0));
        _columnsFavoritelist.put("prmrp", new TableInfo.Column("prmrp", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavoritelist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavoritelist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavoritelist = new TableInfo("favoritelist", _columnsFavoritelist, _foreignKeysFavoritelist, _indicesFavoritelist);
        final TableInfo _existingFavoritelist = TableInfo.read(_db, "favoritelist");
        if (! _infoFavoritelist.equals(_existingFavoritelist)) {
          throw new IllegalStateException("Migration didn't properly handle favoritelist(com.geekhive.foodey.Grocery.beans.favoritelist.FavoriteList).\n"
                  + " Expected:\n" + _infoFavoritelist + "\n"
                  + " Found:\n" + _existingFavoritelist);
        }
      }
    }, "277f2f9c54cf01d0b4984a2e8e14ee36", "3490d71cec3c4f9550f7f235888994e5");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "favoritelist");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `favoritelist`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FavoriteDao favoriteDao() {
    if (_favoriteDao != null) {
      return _favoriteDao;
    } else {
      synchronized(this) {
        if(_favoriteDao == null) {
          _favoriteDao = new FavoriteDao_Impl(this);
        }
        return _favoriteDao;
      }
    }
  }
}
