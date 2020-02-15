package com.example.chaos.scouting2020;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unchecked")
public class DaoTeamData_Impl implements DaoTeamData {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEntityTeamData;

  public DaoTeamData_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntityTeamData = new EntityInsertionAdapter<EntityTeamData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `EntityTeamData`(`TeamNumber`,`TeamName`,`PitScouter`,`RobotWeight`,`RobotDriveBaseType`,`PitScoutingNotes`,`ShootingLocation1`,`ShootingLocation2`,`ShootingLocation3`,`StartLocationLeft`,`StartLocationCenter`,`StartLocationRight`,`AutonNotes`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EntityTeamData value) {
        stmt.bindLong(1, value.TeamNumber);
        if (value.TeamName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.TeamName);
        }
        if (value.PitScouter == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.PitScouter);
        }
        stmt.bindLong(4, value.RobotWeight);
        if (value.RobotDriveBaseType == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.RobotDriveBaseType);
        }
        if (value.PitScoutingNotes == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.PitScoutingNotes);
        }
        final int _tmp;
        _tmp = value.ShootingLocation1 ? 1 : 0;
        stmt.bindLong(7, _tmp);
        final int _tmp_1;
        _tmp_1 = value.ShootingLocation2 ? 1 : 0;
        stmt.bindLong(8, _tmp_1);
        final int _tmp_2;
        _tmp_2 = value.ShootingLocation3 ? 1 : 0;
        stmt.bindLong(9, _tmp_2);
        final int _tmp_3;
        _tmp_3 = value.StartLocationLeft ? 1 : 0;
        stmt.bindLong(10, _tmp_3);
        final int _tmp_4;
        _tmp_4 = value.StartLocationCenter ? 1 : 0;
        stmt.bindLong(11, _tmp_4);
        final int _tmp_5;
        _tmp_5 = value.StartLocationRight ? 1 : 0;
        stmt.bindLong(12, _tmp_5);
        if (value.AutonNotes == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.AutonNotes);
        }
      }
    };
  }

  @Override
  public void insert(EntityTeamData entityTeamData) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEntityTeamData.insert(entityTeamData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public EntityTeamData getRecord(int teamNumber) {
    final String _sql = "SELECT * FROM EntityTeamData WHERE TeamNumber = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, teamNumber);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndexOrThrow("TeamNumber");
      final int _cursorIndexOfTeamName = _cursor.getColumnIndexOrThrow("TeamName");
      final int _cursorIndexOfPitScouter = _cursor.getColumnIndexOrThrow("PitScouter");
      final int _cursorIndexOfRobotWeight = _cursor.getColumnIndexOrThrow("RobotWeight");
      final int _cursorIndexOfRobotDriveBaseType = _cursor.getColumnIndexOrThrow("RobotDriveBaseType");
      final int _cursorIndexOfPitScoutingNotes = _cursor.getColumnIndexOrThrow("PitScoutingNotes");
      final int _cursorIndexOfShootingLocation1 = _cursor.getColumnIndexOrThrow("ShootingLocation1");
      final int _cursorIndexOfShootingLocation2 = _cursor.getColumnIndexOrThrow("ShootingLocation2");
      final int _cursorIndexOfShootingLocation3 = _cursor.getColumnIndexOrThrow("ShootingLocation3");
      final int _cursorIndexOfStartLocationLeft = _cursor.getColumnIndexOrThrow("StartLocationLeft");
      final int _cursorIndexOfStartLocationCenter = _cursor.getColumnIndexOrThrow("StartLocationCenter");
      final int _cursorIndexOfStartLocationRight = _cursor.getColumnIndexOrThrow("StartLocationRight");
      final int _cursorIndexOfAutonNotes = _cursor.getColumnIndexOrThrow("AutonNotes");
      final EntityTeamData _result;
      if(_cursor.moveToFirst()) {
        _result = new EntityTeamData();
        _result.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        _result.TeamName = _cursor.getString(_cursorIndexOfTeamName);
        _result.PitScouter = _cursor.getString(_cursorIndexOfPitScouter);
        _result.RobotWeight = _cursor.getInt(_cursorIndexOfRobotWeight);
        _result.RobotDriveBaseType = _cursor.getString(_cursorIndexOfRobotDriveBaseType);
        _result.PitScoutingNotes = _cursor.getString(_cursorIndexOfPitScoutingNotes);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfShootingLocation1);
        _result.ShootingLocation1 = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfShootingLocation2);
        _result.ShootingLocation2 = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfShootingLocation3);
        _result.ShootingLocation3 = _tmp_2 != 0;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfStartLocationLeft);
        _result.StartLocationLeft = _tmp_3 != 0;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfStartLocationCenter);
        _result.StartLocationCenter = _tmp_4 != 0;
        final int _tmp_5;
        _tmp_5 = _cursor.getInt(_cursorIndexOfStartLocationRight);
        _result.StartLocationRight = _tmp_5 != 0;
        _result.AutonNotes = _cursor.getString(_cursorIndexOfAutonNotes);
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
  public int[] getAllTeamNumbers() {
    final String _sql = "SELECT DISTINCT TeamNumber FROM EntityTeamData ORDER BY TeamNumber ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int[] _result = new int[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final int _item;
        _item = _cursor.getInt(0);
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
  public EntityTeamData[] getAllTeamData() {
    final String _sql = "SELECT * FROM EntityTeamData";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndexOrThrow("TeamNumber");
      final int _cursorIndexOfTeamName = _cursor.getColumnIndexOrThrow("TeamName");
      final int _cursorIndexOfPitScouter = _cursor.getColumnIndexOrThrow("PitScouter");
      final int _cursorIndexOfRobotWeight = _cursor.getColumnIndexOrThrow("RobotWeight");
      final int _cursorIndexOfRobotDriveBaseType = _cursor.getColumnIndexOrThrow("RobotDriveBaseType");
      final int _cursorIndexOfPitScoutingNotes = _cursor.getColumnIndexOrThrow("PitScoutingNotes");
      final int _cursorIndexOfShootingLocation1 = _cursor.getColumnIndexOrThrow("ShootingLocation1");
      final int _cursorIndexOfShootingLocation2 = _cursor.getColumnIndexOrThrow("ShootingLocation2");
      final int _cursorIndexOfShootingLocation3 = _cursor.getColumnIndexOrThrow("ShootingLocation3");
      final int _cursorIndexOfStartLocationLeft = _cursor.getColumnIndexOrThrow("StartLocationLeft");
      final int _cursorIndexOfStartLocationCenter = _cursor.getColumnIndexOrThrow("StartLocationCenter");
      final int _cursorIndexOfStartLocationRight = _cursor.getColumnIndexOrThrow("StartLocationRight");
      final int _cursorIndexOfAutonNotes = _cursor.getColumnIndexOrThrow("AutonNotes");
      final EntityTeamData[] _result = new EntityTeamData[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final EntityTeamData _item;
        _item = new EntityTeamData();
        _item.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        _item.TeamName = _cursor.getString(_cursorIndexOfTeamName);
        _item.PitScouter = _cursor.getString(_cursorIndexOfPitScouter);
        _item.RobotWeight = _cursor.getInt(_cursorIndexOfRobotWeight);
        _item.RobotDriveBaseType = _cursor.getString(_cursorIndexOfRobotDriveBaseType);
        _item.PitScoutingNotes = _cursor.getString(_cursorIndexOfPitScoutingNotes);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfShootingLocation1);
        _item.ShootingLocation1 = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfShootingLocation2);
        _item.ShootingLocation2 = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfShootingLocation3);
        _item.ShootingLocation3 = _tmp_2 != 0;
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfStartLocationLeft);
        _item.StartLocationLeft = _tmp_3 != 0;
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfStartLocationCenter);
        _item.StartLocationCenter = _tmp_4 != 0;
        final int _tmp_5;
        _tmp_5 = _cursor.getInt(_cursorIndexOfStartLocationRight);
        _item.StartLocationRight = _tmp_5 != 0;
        _item.AutonNotes = _cursor.getString(_cursorIndexOfAutonNotes);
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
  public DaoTeamData.PitScoutingReportData[] getPitScoutingReportDataRaw(SupportSQLiteQuery rawQuery) {
    final SupportSQLiteQuery _internalQuery = rawQuery;
    final Cursor _cursor = __db.query(_internalQuery);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndex("TeamNumber");
      final int _cursorIndexOfPitScouter = _cursor.getColumnIndex("PitScouter");
      final int _cursorIndexOfRobotWeight = _cursor.getColumnIndex("RobotWeight");
      final int _cursorIndexOfShootingLocation1 = _cursor.getColumnIndex("ShootingLocation1");
      final int _cursorIndexOfShootingLocation2 = _cursor.getColumnIndex("ShootingLocation2");
      final int _cursorIndexOfShootingLocation3 = _cursor.getColumnIndex("ShootingLocation3");
      final int _cursorIndexOfStartLocationLeft = _cursor.getColumnIndex("StartLocationLeft");
      final int _cursorIndexOfStartLocationCenter = _cursor.getColumnIndex("StartLocationCenter");
      final int _cursorIndexOfStartLocationRight = _cursor.getColumnIndex("StartLocationRight");
      final int _cursorIndexOfRobotDriveBaseType = _cursor.getColumnIndex("RobotDriveBaseType");
      final DaoTeamData.PitScoutingReportData[] _result = new DaoTeamData.PitScoutingReportData[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final DaoTeamData.PitScoutingReportData _item;
        _item = new DaoTeamData.PitScoutingReportData();
        if (_cursorIndexOfTeamNumber != -1) {
          _item.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        }
        if (_cursorIndexOfPitScouter != -1) {
          _item.PitScouter = _cursor.getString(_cursorIndexOfPitScouter);
        }
        if (_cursorIndexOfRobotWeight != -1) {
          _item.RobotWeight = _cursor.getInt(_cursorIndexOfRobotWeight);
        }
        if (_cursorIndexOfShootingLocation1 != -1) {
          final int _tmp;
          _tmp = _cursor.getInt(_cursorIndexOfShootingLocation1);
          _item.ShootingLocation1 = _tmp != 0;
        }
        if (_cursorIndexOfShootingLocation2 != -1) {
          final int _tmp_1;
          _tmp_1 = _cursor.getInt(_cursorIndexOfShootingLocation2);
          _item.ShootingLocation2 = _tmp_1 != 0;
        }
        if (_cursorIndexOfShootingLocation3 != -1) {
          final int _tmp_2;
          _tmp_2 = _cursor.getInt(_cursorIndexOfShootingLocation3);
          _item.ShootingLocation3 = _tmp_2 != 0;
        }
        if (_cursorIndexOfStartLocationLeft != -1) {
          final int _tmp_3;
          _tmp_3 = _cursor.getInt(_cursorIndexOfStartLocationLeft);
          _item.StartLocationLeft = _tmp_3 != 0;
        }
        if (_cursorIndexOfStartLocationCenter != -1) {
          final int _tmp_4;
          _tmp_4 = _cursor.getInt(_cursorIndexOfStartLocationCenter);
          _item.StartLocationCenter = _tmp_4 != 0;
        }
        if (_cursorIndexOfStartLocationRight != -1) {
          final int _tmp_5;
          _tmp_5 = _cursor.getInt(_cursorIndexOfStartLocationRight);
          _item.StartLocationRight = _tmp_5 != 0;
        }
        if (_cursorIndexOfRobotDriveBaseType != -1) {
          _item.RobotDriveBaseType = _cursor.getString(_cursorIndexOfRobotDriveBaseType);
        }
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
    }
  }
}
