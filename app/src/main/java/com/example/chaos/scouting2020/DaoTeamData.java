package com.example.chaos.scouting2020;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface DaoTeamData {
    @Query("SELECT * FROM EntityTeamData WHERE TeamNumber = :teamNumber LIMIT 1")
    EntityTeamData getRecord(int teamNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityTeamData entityTeamData);

    // This returns the team numbers as an array of ints
    @Query("SELECT TeamNumber FROM EntityTeamData")
    int[] getAllTeamNumbers();
}
