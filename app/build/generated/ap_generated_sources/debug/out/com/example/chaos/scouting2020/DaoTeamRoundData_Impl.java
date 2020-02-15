package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
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
public class DaoTeamRoundData_Impl implements DaoTeamRoundData {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEntityTeamRoundData;

  public DaoTeamRoundData_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntityTeamRoundData = new EntityInsertionAdapter<EntityTeamRoundData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `EntityTeamRoundData`(`TeamNumber`,`RoundNumber`,`Scouter`,`TeamColor`,`AutonHighScore`,`AutonLowScore`,`AutonPickUp`,`AutonStartLine`,`TeleopHighScore`,`TeleopLowScore`,`TeleopPickUp`,`RotationControl`,`PositionControl`,`Climb`,`BrokeDown`,`FinalStage`,`Notes`,`RateShooting`,`RateClimb`,`RateWheel`,`RateAuton`,`RateDriver`,`WouldPick`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EntityTeamRoundData value) {
        stmt.bindLong(1, value.TeamNumber);
        stmt.bindLong(2, value.RoundNumber);
        if (value.Scouter == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.Scouter);
        }
        if (value.TeamColor == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.TeamColor);
        }
        stmt.bindLong(5, value.AutonHighScore);
        stmt.bindLong(6, value.AutonLowScore);
        stmt.bindLong(7, value.AutonPickUp);
        final int _tmp;
        _tmp = value.AutonStartLine ? 1 : 0;
        stmt.bindLong(8, _tmp);
        stmt.bindLong(9, value.TeleopHighScore);
        stmt.bindLong(10, value.TeleopLowScore);
        stmt.bindLong(11, value.TeleopPickUp);
        final int _tmp_1;
        _tmp_1 = value.RotationControl ? 1 : 0;
        stmt.bindLong(12, _tmp_1);
        final int _tmp_2;
        _tmp_2 = value.PositionControl ? 1 : 0;
        stmt.bindLong(13, _tmp_2);
        stmt.bindLong(14, value.Climb);
        final int _tmp_3;
        _tmp_3 = value.BrokeDown ? 1 : 0;
        stmt.bindLong(15, _tmp_3);
        stmt.bindLong(16, value.FinalStage);
        if (value.Notes == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.Notes);
        }
        stmt.bindLong(18, value.RateShooting);
        stmt.bindLong(19, value.RateClimb);
        stmt.bindLong(20, value.RateWheel);
        stmt.bindLong(21, value.RateAuton);
        stmt.bindLong(22, value.RateDriver);
        final int _tmp_4;
        _tmp_4 = value.WouldPick ? 1 : 0;
        stmt.bindLong(23, _tmp_4);
      }
    };
  }

  @Override
  public void insert(EntityTeamRoundData entityTeamRoundData) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEntityTeamRoundData.insert(entityTeamRoundData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public EntityTeamRoundData getRecord(int teamNumber, int roundNumber) {
    final String _sql = "SELECT * FROM EntityTeamRoundData WHERE TeamNumber = ? AND RoundNumber = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, teamNumber);
    _argIndex = 2;
    _statement.bindLong(_argIndex, roundNumber);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndexOrThrow("TeamNumber");
      final int _cursorIndexOfRoundNumber = _cursor.getColumnIndexOrThrow("RoundNumber");
      final int _cursorIndexOfScouter = _cursor.getColumnIndexOrThrow("Scouter");
      final int _cursorIndexOfTeamColor = _cursor.getColumnIndexOrThrow("TeamColor");
      final int _cursorIndexOfAutonHighScore = _cursor.getColumnIndexOrThrow("AutonHighScore");
      final int _cursorIndexOfAutonLowScore = _cursor.getColumnIndexOrThrow("AutonLowScore");
      final int _cursorIndexOfAutonPickUp = _cursor.getColumnIndexOrThrow("AutonPickUp");
      final int _cursorIndexOfAutonStartLine = _cursor.getColumnIndexOrThrow("AutonStartLine");
      final int _cursorIndexOfTeleopHighScore = _cursor.getColumnIndexOrThrow("TeleopHighScore");
      final int _cursorIndexOfTeleopLowScore = _cursor.getColumnIndexOrThrow("TeleopLowScore");
      final int _cursorIndexOfTeleopPickUp = _cursor.getColumnIndexOrThrow("TeleopPickUp");
      final int _cursorIndexOfRotationControl = _cursor.getColumnIndexOrThrow("RotationControl");
      final int _cursorIndexOfPositionControl = _cursor.getColumnIndexOrThrow("PositionControl");
      final int _cursorIndexOfClimb = _cursor.getColumnIndexOrThrow("Climb");
      final int _cursorIndexOfBrokeDown = _cursor.getColumnIndexOrThrow("BrokeDown");
      final int _cursorIndexOfFinalStage = _cursor.getColumnIndexOrThrow("FinalStage");
      final int _cursorIndexOfNotes = _cursor.getColumnIndexOrThrow("Notes");
      final int _cursorIndexOfRateShooting = _cursor.getColumnIndexOrThrow("RateShooting");
      final int _cursorIndexOfRateClimb = _cursor.getColumnIndexOrThrow("RateClimb");
      final int _cursorIndexOfRateWheel = _cursor.getColumnIndexOrThrow("RateWheel");
      final int _cursorIndexOfRateAuton = _cursor.getColumnIndexOrThrow("RateAuton");
      final int _cursorIndexOfRateDriver = _cursor.getColumnIndexOrThrow("RateDriver");
      final int _cursorIndexOfWouldPick = _cursor.getColumnIndexOrThrow("WouldPick");
      final EntityTeamRoundData _result;
      if(_cursor.moveToFirst()) {
        _result = new EntityTeamRoundData();
        _result.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        _result.RoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
        _result.Scouter = _cursor.getString(_cursorIndexOfScouter);
        _result.TeamColor = _cursor.getString(_cursorIndexOfTeamColor);
        _result.AutonHighScore = _cursor.getInt(_cursorIndexOfAutonHighScore);
        _result.AutonLowScore = _cursor.getInt(_cursorIndexOfAutonLowScore);
        _result.AutonPickUp = _cursor.getInt(_cursorIndexOfAutonPickUp);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAutonStartLine);
        _result.AutonStartLine = _tmp != 0;
        _result.TeleopHighScore = _cursor.getInt(_cursorIndexOfTeleopHighScore);
        _result.TeleopLowScore = _cursor.getInt(_cursorIndexOfTeleopLowScore);
        _result.TeleopPickUp = _cursor.getInt(_cursorIndexOfTeleopPickUp);
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRotationControl);
        _result.RotationControl = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfPositionControl);
        _result.PositionControl = _tmp_2 != 0;
        _result.Climb = _cursor.getInt(_cursorIndexOfClimb);
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfBrokeDown);
        _result.BrokeDown = _tmp_3 != 0;
        _result.FinalStage = _cursor.getInt(_cursorIndexOfFinalStage);
        _result.Notes = _cursor.getString(_cursorIndexOfNotes);
        _result.RateShooting = _cursor.getInt(_cursorIndexOfRateShooting);
        _result.RateClimb = _cursor.getInt(_cursorIndexOfRateClimb);
        _result.RateWheel = _cursor.getInt(_cursorIndexOfRateWheel);
        _result.RateAuton = _cursor.getInt(_cursorIndexOfRateAuton);
        _result.RateDriver = _cursor.getInt(_cursorIndexOfRateDriver);
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfWouldPick);
        _result.WouldPick = _tmp_4 != 0;
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
  public EntityTeamRoundData[] getAllTeamRoundData() {
    final String _sql = "SELECT * FROM EntityTeamRoundData";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndexOrThrow("TeamNumber");
      final int _cursorIndexOfRoundNumber = _cursor.getColumnIndexOrThrow("RoundNumber");
      final int _cursorIndexOfScouter = _cursor.getColumnIndexOrThrow("Scouter");
      final int _cursorIndexOfTeamColor = _cursor.getColumnIndexOrThrow("TeamColor");
      final int _cursorIndexOfAutonHighScore = _cursor.getColumnIndexOrThrow("AutonHighScore");
      final int _cursorIndexOfAutonLowScore = _cursor.getColumnIndexOrThrow("AutonLowScore");
      final int _cursorIndexOfAutonPickUp = _cursor.getColumnIndexOrThrow("AutonPickUp");
      final int _cursorIndexOfAutonStartLine = _cursor.getColumnIndexOrThrow("AutonStartLine");
      final int _cursorIndexOfTeleopHighScore = _cursor.getColumnIndexOrThrow("TeleopHighScore");
      final int _cursorIndexOfTeleopLowScore = _cursor.getColumnIndexOrThrow("TeleopLowScore");
      final int _cursorIndexOfTeleopPickUp = _cursor.getColumnIndexOrThrow("TeleopPickUp");
      final int _cursorIndexOfRotationControl = _cursor.getColumnIndexOrThrow("RotationControl");
      final int _cursorIndexOfPositionControl = _cursor.getColumnIndexOrThrow("PositionControl");
      final int _cursorIndexOfClimb = _cursor.getColumnIndexOrThrow("Climb");
      final int _cursorIndexOfBrokeDown = _cursor.getColumnIndexOrThrow("BrokeDown");
      final int _cursorIndexOfFinalStage = _cursor.getColumnIndexOrThrow("FinalStage");
      final int _cursorIndexOfNotes = _cursor.getColumnIndexOrThrow("Notes");
      final int _cursorIndexOfRateShooting = _cursor.getColumnIndexOrThrow("RateShooting");
      final int _cursorIndexOfRateClimb = _cursor.getColumnIndexOrThrow("RateClimb");
      final int _cursorIndexOfRateWheel = _cursor.getColumnIndexOrThrow("RateWheel");
      final int _cursorIndexOfRateAuton = _cursor.getColumnIndexOrThrow("RateAuton");
      final int _cursorIndexOfRateDriver = _cursor.getColumnIndexOrThrow("RateDriver");
      final int _cursorIndexOfWouldPick = _cursor.getColumnIndexOrThrow("WouldPick");
      final EntityTeamRoundData[] _result = new EntityTeamRoundData[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final EntityTeamRoundData _item;
        _item = new EntityTeamRoundData();
        _item.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        _item.RoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
        _item.Scouter = _cursor.getString(_cursorIndexOfScouter);
        _item.TeamColor = _cursor.getString(_cursorIndexOfTeamColor);
        _item.AutonHighScore = _cursor.getInt(_cursorIndexOfAutonHighScore);
        _item.AutonLowScore = _cursor.getInt(_cursorIndexOfAutonLowScore);
        _item.AutonPickUp = _cursor.getInt(_cursorIndexOfAutonPickUp);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAutonStartLine);
        _item.AutonStartLine = _tmp != 0;
        _item.TeleopHighScore = _cursor.getInt(_cursorIndexOfTeleopHighScore);
        _item.TeleopLowScore = _cursor.getInt(_cursorIndexOfTeleopLowScore);
        _item.TeleopPickUp = _cursor.getInt(_cursorIndexOfTeleopPickUp);
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfRotationControl);
        _item.RotationControl = _tmp_1 != 0;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfPositionControl);
        _item.PositionControl = _tmp_2 != 0;
        _item.Climb = _cursor.getInt(_cursorIndexOfClimb);
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(_cursorIndexOfBrokeDown);
        _item.BrokeDown = _tmp_3 != 0;
        _item.FinalStage = _cursor.getInt(_cursorIndexOfFinalStage);
        _item.Notes = _cursor.getString(_cursorIndexOfNotes);
        _item.RateShooting = _cursor.getInt(_cursorIndexOfRateShooting);
        _item.RateClimb = _cursor.getInt(_cursorIndexOfRateClimb);
        _item.RateWheel = _cursor.getInt(_cursorIndexOfRateWheel);
        _item.RateAuton = _cursor.getInt(_cursorIndexOfRateAuton);
        _item.RateDriver = _cursor.getInt(_cursorIndexOfRateDriver);
        final int _tmp_4;
        _tmp_4 = _cursor.getInt(_cursorIndexOfWouldPick);
        _item.WouldPick = _tmp_4 != 0;
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
  public DaoTeamRoundData.MatchReportData[] getMatchReportDataRaw(SupportSQLiteQuery rawQuery) {
    final SupportSQLiteQuery _internalQuery = rawQuery;
    final Cursor _cursor = __db.query(_internalQuery);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndex("TeamNumber");
      final int _cursorIndexOfNumRounds = _cursor.getColumnIndex("NumRounds");
      final int _cursorIndexOfAvgHighScore = _cursor.getColumnIndex("AvgHighScore");
      final int _cursorIndexOfAvgLowScore = _cursor.getColumnIndex("AvgLowScore");
      final int _cursorIndexOfNumSuccessfulClimbs = _cursor.getColumnIndex("NumSuccessfulClimbs");
      final int _cursorIndexOfNumFailedClimbs = _cursor.getColumnIndex("NumFailedClimbs");
      final int _cursorIndexOfPercentClimbs = _cursor.getColumnIndex("PercentClimbs");
      final int _cursorIndexOfPercentBreakdowns = _cursor.getColumnIndex("PercentBreakdowns");
      final int _cursorIndexOfPercentStage2 = _cursor.getColumnIndex("PercentStage2");
      final int _cursorIndexOfPercentStage3 = _cursor.getColumnIndex("PercentStage3");
      final DaoTeamRoundData.MatchReportData[] _result = new DaoTeamRoundData.MatchReportData[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final DaoTeamRoundData.MatchReportData _item;
        _item = new DaoTeamRoundData.MatchReportData();
        if (_cursorIndexOfTeamNumber != -1) {
          _item.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        }
        if (_cursorIndexOfNumRounds != -1) {
          _item.NumRounds = _cursor.getInt(_cursorIndexOfNumRounds);
        }
        if (_cursorIndexOfAvgHighScore != -1) {
          _item.AvgHighScore = _cursor.getFloat(_cursorIndexOfAvgHighScore);
        }
        if (_cursorIndexOfAvgLowScore != -1) {
          _item.AvgLowScore = _cursor.getFloat(_cursorIndexOfAvgLowScore);
        }
        if (_cursorIndexOfNumSuccessfulClimbs != -1) {
          _item.NumSuccessfulClimbs = _cursor.getInt(_cursorIndexOfNumSuccessfulClimbs);
        }
        if (_cursorIndexOfNumFailedClimbs != -1) {
          _item.NumFailedClimbs = _cursor.getInt(_cursorIndexOfNumFailedClimbs);
        }
        if (_cursorIndexOfPercentClimbs != -1) {
          _item.PercentClimbs = _cursor.getFloat(_cursorIndexOfPercentClimbs);
        }
        if (_cursorIndexOfPercentBreakdowns != -1) {
          _item.PercentBreakdowns = _cursor.getFloat(_cursorIndexOfPercentBreakdowns);
        }
        if (_cursorIndexOfPercentStage2 != -1) {
          _item.PercentStage2 = _cursor.getFloat(_cursorIndexOfPercentStage2);
        }
        if (_cursorIndexOfPercentStage3 != -1) {
          _item.PercentStage3 = _cursor.getFloat(_cursorIndexOfPercentStage3);
        }
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
    }
  }

  @Override
  public DaoTeamRoundData.OpinionReportData[] getOpinionReportDataRaw(SupportSQLiteQuery rawQuery) {
    final SupportSQLiteQuery _internalQuery = rawQuery;
    final Cursor _cursor = __db.query(_internalQuery);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndex("TeamNumber");
      final int _cursorIndexOfAvgShootingOpinion = _cursor.getColumnIndex("AvgShootingOpinion");
      final int _cursorIndexOfAvgClimbingOpinion = _cursor.getColumnIndex("AvgClimbingOpinion");
      final int _cursorIndexOfAvgSpinningOpinion = _cursor.getColumnIndex("AvgSpinningOpinion");
      final int _cursorIndexOfAvgAutonOpinion = _cursor.getColumnIndex("AvgAutonOpinion");
      final int _cursorIndexOfAvgDriverOpinion = _cursor.getColumnIndex("AvgDriverOpinion");
      final int _cursorIndexOfAvgWouldPickOpinion = _cursor.getColumnIndex("AvgWouldPickOpinion");
      final int _cursorIndexOfAvgStarOpinion = _cursor.getColumnIndex("AvgStarOpinion");
      final DaoTeamRoundData.OpinionReportData[] _result = new DaoTeamRoundData.OpinionReportData[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final DaoTeamRoundData.OpinionReportData _item;
        _item = new DaoTeamRoundData.OpinionReportData();
        if (_cursorIndexOfTeamNumber != -1) {
          _item.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        }
        if (_cursorIndexOfAvgShootingOpinion != -1) {
          _item.AvgShootingOpinion = _cursor.getFloat(_cursorIndexOfAvgShootingOpinion);
        }
        if (_cursorIndexOfAvgClimbingOpinion != -1) {
          _item.AvgClimbingOpinion = _cursor.getFloat(_cursorIndexOfAvgClimbingOpinion);
        }
        if (_cursorIndexOfAvgSpinningOpinion != -1) {
          _item.AvgSpinningOpinion = _cursor.getFloat(_cursorIndexOfAvgSpinningOpinion);
        }
        if (_cursorIndexOfAvgAutonOpinion != -1) {
          _item.AvgAutonOpinion = _cursor.getFloat(_cursorIndexOfAvgAutonOpinion);
        }
        if (_cursorIndexOfAvgDriverOpinion != -1) {
          _item.AvgDriverOpinion = _cursor.getFloat(_cursorIndexOfAvgDriverOpinion);
        }
        if (_cursorIndexOfAvgWouldPickOpinion != -1) {
          _item.AvgWouldPickOpinion = _cursor.getFloat(_cursorIndexOfAvgWouldPickOpinion);
        }
        if (_cursorIndexOfAvgStarOpinion != -1) {
          _item.AvgStarOpinion = _cursor.getFloat(_cursorIndexOfAvgStarOpinion);
        }
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
    }
  }

  @Override
  public DaoTeamRoundData.TeamRoundDataReportData[] getTeamRoundDataReportDataRaw(SimpleSQLiteQuery simpleSQLiteQuery) {
    final SupportSQLiteQuery _internalQuery = simpleSQLiteQuery;
    final Cursor _cursor = __db.query(_internalQuery);
    try {
      final int _cursorIndexOfTeamNumber = _cursor.getColumnIndex("TeamNumber");
      final int _cursorIndexOfRoundNumber = _cursor.getColumnIndex("RoundNumber");
      final int _cursorIndexOfScouter = _cursor.getColumnIndex("Scouter");
      final int _cursorIndexOfTeamColor = _cursor.getColumnIndex("TeamColor");
      final int _cursorIndexOfAutonHighScore = _cursor.getColumnIndex("AutonHighScore");
      final int _cursorIndexOfAutonLowScore = _cursor.getColumnIndex("AutonLowScore");
      final int _cursorIndexOfAutonPickUp = _cursor.getColumnIndex("AutonPickUp");
      final int _cursorIndexOfAutonStartLine = _cursor.getColumnIndex("AutonStartLine");
      final int _cursorIndexOfTeleopHighScore = _cursor.getColumnIndex("TeleopHighScore");
      final int _cursorIndexOfTeleopLowScore = _cursor.getColumnIndex("TeleopLowScore");
      final int _cursorIndexOfTeleopPickUp = _cursor.getColumnIndex("TeleopPickUp");
      final int _cursorIndexOfRotationControl = _cursor.getColumnIndex("RotationControl");
      final int _cursorIndexOfPositionControl = _cursor.getColumnIndex("PositionControl");
      final int _cursorIndexOfClimb = _cursor.getColumnIndex("Climb");
      final int _cursorIndexOfBrokeDown = _cursor.getColumnIndex("BrokeDown");
      final int _cursorIndexOfFinalStage = _cursor.getColumnIndex("FinalStage");
      final int _cursorIndexOfNotes = _cursor.getColumnIndex("Notes");
      final int _cursorIndexOfRateShooting = _cursor.getColumnIndex("RateShooting");
      final int _cursorIndexOfRateClimb = _cursor.getColumnIndex("RateClimb");
      final int _cursorIndexOfRateWheel = _cursor.getColumnIndex("RateWheel");
      final int _cursorIndexOfRateAuton = _cursor.getColumnIndex("RateAuton");
      final int _cursorIndexOfRateDriver = _cursor.getColumnIndex("RateDriver");
      final int _cursorIndexOfWouldPick = _cursor.getColumnIndex("WouldPick");
      final DaoTeamRoundData.TeamRoundDataReportData[] _result = new DaoTeamRoundData.TeamRoundDataReportData[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final DaoTeamRoundData.TeamRoundDataReportData _item;
        _item = new DaoTeamRoundData.TeamRoundDataReportData();
        if (_cursorIndexOfTeamNumber != -1) {
          _item.TeamNumber = _cursor.getInt(_cursorIndexOfTeamNumber);
        }
        if (_cursorIndexOfRoundNumber != -1) {
          _item.RoundNumber = _cursor.getInt(_cursorIndexOfRoundNumber);
        }
        if (_cursorIndexOfScouter != -1) {
          _item.Scouter = _cursor.getString(_cursorIndexOfScouter);
        }
        if (_cursorIndexOfTeamColor != -1) {
          _item.TeamColor = _cursor.getString(_cursorIndexOfTeamColor);
        }
        if (_cursorIndexOfAutonHighScore != -1) {
          _item.AutonHighScore = _cursor.getInt(_cursorIndexOfAutonHighScore);
        }
        if (_cursorIndexOfAutonLowScore != -1) {
          _item.AutonLowScore = _cursor.getInt(_cursorIndexOfAutonLowScore);
        }
        if (_cursorIndexOfAutonPickUp != -1) {
          _item.AutonPickUp = _cursor.getInt(_cursorIndexOfAutonPickUp);
        }
        if (_cursorIndexOfAutonStartLine != -1) {
          final int _tmp;
          _tmp = _cursor.getInt(_cursorIndexOfAutonStartLine);
          _item.AutonStartLine = _tmp != 0;
        }
        if (_cursorIndexOfTeleopHighScore != -1) {
          _item.TeleopHighScore = _cursor.getInt(_cursorIndexOfTeleopHighScore);
        }
        if (_cursorIndexOfTeleopLowScore != -1) {
          _item.TeleopLowScore = _cursor.getInt(_cursorIndexOfTeleopLowScore);
        }
        if (_cursorIndexOfTeleopPickUp != -1) {
          _item.TeleopPickUp = _cursor.getInt(_cursorIndexOfTeleopPickUp);
        }
        if (_cursorIndexOfRotationControl != -1) {
          final int _tmp_1;
          _tmp_1 = _cursor.getInt(_cursorIndexOfRotationControl);
          _item.RotationControl = _tmp_1 != 0;
        }
        if (_cursorIndexOfPositionControl != -1) {
          final int _tmp_2;
          _tmp_2 = _cursor.getInt(_cursorIndexOfPositionControl);
          _item.PositionControl = _tmp_2 != 0;
        }
        if (_cursorIndexOfClimb != -1) {
          _item.Climb = _cursor.getInt(_cursorIndexOfClimb);
        }
        if (_cursorIndexOfBrokeDown != -1) {
          final int _tmp_3;
          _tmp_3 = _cursor.getInt(_cursorIndexOfBrokeDown);
          _item.BrokeDown = _tmp_3 != 0;
        }
        if (_cursorIndexOfFinalStage != -1) {
          _item.FinalStage = _cursor.getInt(_cursorIndexOfFinalStage);
        }
        if (_cursorIndexOfNotes != -1) {
          _item.Notes = _cursor.getString(_cursorIndexOfNotes);
        }
        if (_cursorIndexOfRateShooting != -1) {
          _item.RateShooting = _cursor.getInt(_cursorIndexOfRateShooting);
        }
        if (_cursorIndexOfRateClimb != -1) {
          _item.RateClimb = _cursor.getInt(_cursorIndexOfRateClimb);
        }
        if (_cursorIndexOfRateWheel != -1) {
          _item.RateWheel = _cursor.getInt(_cursorIndexOfRateWheel);
        }
        if (_cursorIndexOfRateAuton != -1) {
          _item.RateAuton = _cursor.getInt(_cursorIndexOfRateAuton);
        }
        if (_cursorIndexOfRateDriver != -1) {
          _item.RateDriver = _cursor.getInt(_cursorIndexOfRateDriver);
        }
        if (_cursorIndexOfWouldPick != -1) {
          final int _tmp_4;
          _tmp_4 = _cursor.getInt(_cursorIndexOfWouldPick);
          _item.WouldPick = _tmp_4 != 0;
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
