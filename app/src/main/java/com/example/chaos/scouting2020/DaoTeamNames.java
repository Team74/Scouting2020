package com.example.chaos.scouting2020;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoTeamNames {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityTeamNames entityTeamNames);

    // This returns the scouter names as a list which is needed for spinner
    @Query("SELECT TeamNumber FROM EntityTeamNames")
    int[] getAllTeamNumbers();
}
