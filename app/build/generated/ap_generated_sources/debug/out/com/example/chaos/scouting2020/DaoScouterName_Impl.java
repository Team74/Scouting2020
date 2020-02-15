package com.example.chaos.scouting2020;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class DaoScouterName_Impl implements DaoScouterName {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEntityScouterName;

  public DaoScouterName_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntityScouterName = new EntityInsertionAdapter<EntityScouterName>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `EntityScouterName`(`ScouterName`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EntityScouterName value) {
        if (value.ScouterName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.ScouterName);
        }
      }
    };
  }

  @Override
  public void insert(EntityScouterName entityScouterName) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEntityScouterName.insert(entityScouterName);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public EntityScouterName getScouters() {
    final String _sql = "SELECT * FROM EntityScouterName LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfScouterName = _cursor.getColumnIndexOrThrow("ScouterName");
      final EntityScouterName _result;
      if(_cursor.moveToFirst()) {
        _result = new EntityScouterName();
        _result.ScouterName = _cursor.getString(_cursorIndexOfScouterName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String[] getAllScouterNames() {
    final String _sql = "SELECT ScouterName FROM EntityScouterName";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final String[] _result = new String[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getAllScouterNamesAsList() {
    final String _sql = "SELECT ScouterName FROM EntityScouterName";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
