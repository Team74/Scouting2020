package com.example.chaos.scouting2020;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoScouterName {
    @Query("SELECT * FROM EntityScouterName LIMIT 1")
    EntityScouterName getScouters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityScouterName entityScouterName);

    @Query("SELECT ScouterName FROM EntityScouterName")
    String[] getAllScouterNames();

    // This returns the scouter names as a list which is needed for spinner
    @Query("SELECT ScouterName FROM EntityScouterName")
    List<String> getAllScouterNamesAsList();
}
