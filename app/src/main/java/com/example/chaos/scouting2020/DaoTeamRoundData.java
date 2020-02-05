package com.example.chaos.scouting2020;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;

import java.util.List;
import java.util.Objects;

@Dao
public interface DaoTeamRoundData {
    @Query("SELECT * FROM EntityTeamRoundData WHERE TeamNumber = :teamNumber AND RoundNumber = :roundNumber LIMIT 1")
    EntityTeamRoundData getRecord(int teamNumber, int roundNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityTeamRoundData entityTeamRoundData);

    // This returns the list of all unique team numbers in the round data
    @Query("SELECT DISTINCT TeamNumber FROM EntityTeamRoundData")
    List<Integer> getAllTeamNumbersAsList();

    @Query("SELECT * FROM EntityTeamRoundData")
    EntityTeamRoundData[] getAllTeamRoundData();

    class MatchReportData {
        int TeamNumber;
        int CountRounds;
        float AvgTeleopHighScore;
        float AvgTeleopLowScore;
        int NumSuccessfulClimbs;
        float PercentClimbs;
        float PercentBreakdowns;
        float PercentStage2;
        float PercentStage3;
    }
    @RawQuery
    MatchReportData[] getMatchReportDataRaw(SupportSQLiteQuery rawQuery);
}
