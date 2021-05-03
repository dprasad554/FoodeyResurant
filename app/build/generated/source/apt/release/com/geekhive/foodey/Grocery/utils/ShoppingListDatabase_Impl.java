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
public final class ShoppingListDatabase_Impl extends ShoppingListDatabase {
  private volatile ShoppingListDao _shoppingListDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `shoppinglist` (`id` TEXT NOT NULL, `image` TEXT, `prname` TEXT, `prquantity` TEXT, `prprice` TEXT, `prmrp` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"1eb8d5d3defd66d9139574ca7b9ec7dd\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `shoppinglist`");
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
        final HashMap<String, TableInfo.Column> _columnsShoppinglist = new HashMap<String, TableInfo.Column>(6);
        _columnsShoppinglist.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsShoppinglist.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        _columnsShoppinglist.put("prname", new TableInfo.Column("prname", "TEXT", false, 0));
        _columnsShoppinglist.put("prquantity", new TableInfo.Column("prquantity", "TEXT", false, 0));
        _columnsShoppinglist.put("prprice", new TableInfo.Column("prprice", "TEXT", false, 0));
        _columnsShoppinglist.put("prmrp", new TableInfo.Column("prmrp", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShoppinglist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShoppinglist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShoppinglist = new TableInfo("shoppinglist", _columnsShoppinglist, _foreignKeysShoppinglist, _indicesShoppinglist);
        final TableInfo _existingShoppinglist = TableInfo.read(_db, "shoppinglist");
        if (! _infoShoppinglist.equals(_existingShoppinglist)) {
          throw new IllegalStateException("Migration didn't properly handle shoppinglist(com.geekhive.foodey.Grocery.beans.shoppinglist.ShoppingList).\n"
                  + " Expected:\n" + _infoShoppinglist + "\n"
                  + " Found:\n" + _existingShoppinglist);
        }
      }
    }, "1eb8d5d3defd66d9139574ca7b9ec7dd", "66655f39704fac9ddbca4a6d4cea8720");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "shoppinglist");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `shoppinglist`");
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
  public ShoppingListDao shoppingListDao() {
    if (_shoppingListDao != null) {
      return _shoppingListDao;
    } else {
      synchronized(this) {
        if(_shoppingListDao == null) {
          _shoppingListDao = new ShoppingListDao_Impl(this);
        }
        return _shoppingListDao;
      }
    }
  }
}
