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

    @Query("UPDATE EntityTeamRoundData SET AutonHighScore = :autonHighScore, AutonLowScore = :autonLowScore, AutonPickUp = :autonPickUp Where TeamNumber = :teamNumber AND RoundNumber = :roundNumber")
    void updateAuton(int autonHighScore, int autonLowScore, int autonPickUp, int teamNumber, int roundNumber);
}
