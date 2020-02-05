package com.example.chaos.scouting2020;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoTeamRoundData {
    @Query("SELECT * FROM EntityTeamRoundData WHERE TeamNumber = :teamNumber AND RoundNumber = :roundNumber LIMIT 1")
    EntityTeamRoundData getRecord(int teamNumber, int roundNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityTeamRoundData entityTeamRoundData);

    // This returns the list of all unique team numbers in the round data
    @Query("SELECT DISTINCT TeamNumber FROM EntityTeamRoundData")
    List<Integer> getAllTeamNumbersAsList();

    static class MatchReportData {
        int TeamNumber;
        float AvgTeleopHighScore;
    }

    @Query("SELECT TeamNumber, AVG(TeleopHighScore) AS AvgTeleopHighScore FROM EntityTeamRoundData GROUP BY TeamNumber")
    MatchReportData[] getMatchReportData();
}
