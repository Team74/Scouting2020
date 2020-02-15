package com.example.chaos.scouting2020;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class ScoutingDatabase_Impl extends ScoutingDatabase {
  private volatile DaoTeamRoundData _daoTeamRoundData;

  private volatile DaoScouterName _daoScouterName;

  private volatile DaoTeamData _daoTeamData;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(18) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `EntityTeamRoundData` (`TeamNumber` INTEGER NOT NULL, `RoundNumber` INTEGER NOT NULL, `Scouter` TEXT, `TeamColor` TEXT, `AutonHighScore` INTEGER NOT NULL, `AutonLowScore` INTEGER NOT NULL, `AutonPickUp` INTEGER NOT NULL, `AutonStartLine` INTEGER NOT NULL, `TeleopHighScore` INTEGER NOT NULL, `TeleopLowScore` INTEGER NOT NULL, `TeleopPickUp` INTEGER NOT NULL, `RotationControl` INTEGER NOT NULL, `PositionControl` INTEGER NOT NULL, `Climb` INTEGER NOT NULL, `BrokeDown` INTEGER NOT NULL, `FinalStage` INTEGER NOT NULL, `Notes` TEXT, `RateShooting` INTEGER NOT NULL, `RateClimb` INTEGER NOT NULL, `RateWheel` INTEGER NOT NULL, `RateAuton` INTEGER NOT NULL, `RateDriver` INTEGER NOT NULL, `WouldPick` INTEGER NOT NULL, PRIMARY KEY(`TeamNumber`, `RoundNumber`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `EntityScouterName` (`ScouterName` TEXT NOT NULL, PRIMARY KEY(`ScouterName`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `EntityTeamData` (`TeamNumber` INTEGER NOT NULL, `TeamName` TEXT NOT NULL, `PitScouter` TEXT, `RobotWeight` INTEGER NOT NULL, `RobotDriveBaseType` TEXT, `PitScoutingNotes` TEXT, `ShootingLocation1` INTEGER NOT NULL, `ShootingLocation2` INTEGER NOT NULL, `ShootingLocation3` INTEGER NOT NULL, `StartLocationLeft` INTEGER NOT NULL, `StartLocationCenter` INTEGER NOT NULL, `StartLocationRight` INTEGER NOT NULL, `AutonNotes` TEXT, PRIMARY KEY(`TeamNumber`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"f2f14f28c4022763519b38f71ca8406a\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `EntityTeamRoundData`");
        _db.execSQL("DROP TABLE IF EXISTS `EntityScouterName`");
        _db.execSQL("DROP TABLE IF EXISTS `EntityTeamData`");
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
        final HashMap<String, TableInfo.Column> _columnsEntityTeamRoundData = new HashMap<String, TableInfo.Column>(23);
        _columnsEntityTeamRoundData.put("TeamNumber", new TableInfo.Column("TeamNumber", "INTEGER", true, 1));
        _columnsEntityTeamRoundData.put("RoundNumber", new TableInfo.Column("RoundNumber", "INTEGER", true, 2));
        _columnsEntityTeamRoundData.put("Scouter", new TableInfo.Column("Scouter", "TEXT", false, 0));
        _columnsEntityTeamRoundData.put("TeamColor", new TableInfo.Column("TeamColor", "TEXT", false, 0));
        _columnsEntityTeamRoundData.put("AutonHighScore", new TableInfo.Column("AutonHighScore", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("AutonLowScore", new TableInfo.Column("AutonLowScore", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("AutonPickUp", new TableInfo.Column("AutonPickUp", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("AutonStartLine", new TableInfo.Column("AutonStartLine", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("TeleopHighScore", new TableInfo.Column("TeleopHighScore", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("TeleopLowScore", new TableInfo.Column("TeleopLowScore", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("TeleopPickUp", new TableInfo.Column("TeleopPickUp", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("RotationControl", new TableInfo.Column("RotationControl", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("PositionControl", new TableInfo.Column("PositionControl", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("Climb", new TableInfo.Column("Climb", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("BrokeDown", new TableInfo.Column("BrokeDown", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("FinalStage", new TableInfo.Column("FinalStage", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("Notes", new TableInfo.Column("Notes", "TEXT", false, 0));
        _columnsEntityTeamRoundData.put("RateShooting", new TableInfo.Column("RateShooting", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("RateClimb", new TableInfo.Column("RateClimb", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("RateWheel", new TableInfo.Column("RateWheel", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("RateAuton", new TableInfo.Column("RateAuton", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("RateDriver", new TableInfo.Column("RateDriver", "INTEGER", true, 0));
        _columnsEntityTeamRoundData.put("WouldPick", new TableInfo.Column("WouldPick", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEntityTeamRoundData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEntityTeamRoundData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEntityTeamRoundData = new TableInfo("EntityTeamRoundData", _columnsEntityTeamRoundData, _foreignKeysEntityTeamRoundData, _indicesEntityTeamRoundData);
        final TableInfo _existingEntityTeamRoundData = TableInfo.read(_db, "EntityTeamRoundData");
        if (! _infoEntityTeamRoundData.equals(_existingEntityTeamRoundData)) {
          throw new IllegalStateException("Migration didn't properly handle EntityTeamRoundData(com.example.chaos.scouting2020.EntityTeamRoundData).\n"
                  + " Expected:\n" + _infoEntityTeamRoundData + "\n"
                  + " Found:\n" + _existingEntityTeamRoundData);
        }
        final HashMap<String, TableInfo.Column> _columnsEntityScouterName = new HashMap<String, TableInfo.Column>(1);
        _columnsEntityScouterName.put("ScouterName", new TableInfo.Column("ScouterName", "TEXT", true, 1));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEntityScouterName = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEntityScouterName = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEntityScouterName = new TableInfo("EntityScouterName", _columnsEntityScouterName, _foreignKeysEntityScouterName, _indicesEntityScouterName);
        final TableInfo _existingEntityScouterName = TableInfo.read(_db, "EntityScouterName");
        if (! _infoEntityScouterName.equals(_existingEntityScouterName)) {
          throw new IllegalStateException("Migration didn't properly handle EntityScouterName(com.example.chaos.scouting2020.EntityScouterName).\n"
                  + " Expected:\n" + _infoEntityScouterName + "\n"
                  + " Found:\n" + _existingEntityScouterName);
        }
        final HashMap<String, TableInfo.Column> _columnsEntityTeamData = new HashMap<String, TableInfo.Column>(13);
        _columnsEntityTeamData.put("TeamNumber", new TableInfo.Column("TeamNumber", "INTEGER", true, 1));
        _columnsEntityTeamData.put("TeamName", new TableInfo.Column("TeamName", "TEXT", true, 0));
        _columnsEntityTeamData.put("PitScouter", new TableInfo.Column("PitScouter", "TEXT", false, 0));
        _columnsEntityTeamData.put("RobotWeight", new TableInfo.Column("RobotWeight", "INTEGER", true, 0));
        _columnsEntityTeamData.put("RobotDriveBaseType", new TableInfo.Column("RobotDriveBaseType", "TEXT", false, 0));
        _columnsEntityTeamData.put("PitScoutingNotes", new TableInfo.Column("PitScoutingNotes", "TEXT", false, 0));
        _columnsEntityTeamData.put("ShootingLocation1", new TableInfo.Column("ShootingLocation1", "INTEGER", true, 0));
        _columnsEntityTeamData.put("ShootingLocation2", new TableInfo.Column("ShootingLocation2", "INTEGER", true, 0));
        _columnsEntityTeamData.put("ShootingLocation3", new TableInfo.Column("ShootingLocation3", "INTEGER", true, 0));
        _columnsEntityTeamData.put("StartLocationLeft", new TableInfo.Column("StartLocationLeft", "INTEGER", true, 0));
        _columnsEntityTeamData.put("StartLocationCenter", new TableInfo.Column("StartLocationCenter", "INTEGER", true, 0));
        _columnsEntityTeamData.put("StartLocationRight", new TableInfo.Column("StartLocationRight", "INTEGER", true, 0));
        _columnsEntityTeamData.put("AutonNotes", new TableInfo.Column("AutonNotes", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEntityTeamData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEntityTeamData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEntityTeamData = new TableInfo("EntityTeamData", _columnsEntityTeamData, _foreignKeysEntityTeamData, _indicesEntityTeamData);
        final TableInfo _existingEntityTeamData = TableInfo.read(_db, "EntityTeamData");
        if (! _infoEntityTeamData.equals(_existingEntityTeamData)) {
          throw new IllegalStateException("Migration didn't properly handle EntityTeamData(com.example.chaos.scouting2020.EntityTeamData).\n"
                  + " Expected:\n" + _infoEntityTeamData + "\n"
                  + " Found:\n" + _existingEntityTeamData);
        }
      }
    }, "f2f14f28c4022763519b38f71ca8406a", "6e8b9834c08e79f05fa9fcd589927bee");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "EntityTeamRoundData","EntityScouterName","EntityTeamData");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `EntityTeamRoundData`");
      _db.execSQL("DELETE FROM `EntityScouterName`");
      _db.execSQL("DELETE FROM `EntityTeamData`");
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
  public DaoTeamRoundData daoTeamRoundData() {
    if (_daoTeamRoundData != null) {
      return _daoTeamRoundData;
    } else {
      synchronized(this) {
        if(_daoTeamRoundData == null) {
          _daoTeamRoundData = new DaoTeamRoundData_Impl(this);
        }
        return _daoTeamRoundData;
      }
    }
  }

  @Override
  public DaoScouterName daoScouterName() {
    if (_daoScouterName != null) {
      return _daoScouterName;
    } else {
      synchronized(this) {
        if(_daoScouterName == null) {
          _daoScouterName = new DaoScouterName_Impl(this);
        }
        return _daoScouterName;
      }
    }
  }

  @Override
  public DaoTeamData daoTeamData() {
    if (_daoTeamData != null) {
      return _daoTeamData;
    } else {
      synchronized(this) {
        if(_daoTeamData == null) {
          _daoTeamData = new DaoTeamData_Impl(this);
        }
        return _daoTeamData;
      }
    }
  }
}
