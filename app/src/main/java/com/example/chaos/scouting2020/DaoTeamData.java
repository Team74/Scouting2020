package com.example.chaos.scouting2020;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;

@Dao
public interface DaoTeamData {
    @Query("SELECT * FROM EntityTeamData WHERE TeamNumber = :teamNumber LIMIT 1")
    EntityTeamData getRecord(int teamNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityTeamData entityTeamData);

    // This returns the team numbers as an array of ints
    @Query("SELECT TeamNumber FROM EntityTeamData ORDER BY TeamNumber ASC")
    int[] getAllTeamNumbers();

    @Query("SELECT * FROM EntityTeamData")
    EntityTeamData[] getAllTeamData();

    class PitScoutingReportData {
        int TeamNumber;
        String PitScouter;
        int RobotWeight;
        boolean ShootingLocation1;
        boolean ShootingLocation2;
        boolean ShootingLocation3;
        boolean StartLocationLeft;
        boolean StartLocationCenter;
        boolean StartLocationRight;
        String RobotDriveBaseType;
    }
    // NOTE: a custom query string must be provided that returns the above class
    @RawQuery
    PitScoutingReportData[] getPitScoutingReportDataRaw(SupportSQLiteQuery rawQuery);
}
