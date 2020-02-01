package com.example.chaos.scouting2020;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {EntityTeamRoundData.class, EntityScouterName.class, EntityTeamNames.class},  version = 4, exportSchema = false)
public abstract class ScoutingDatabase extends RoomDatabase {
    public abstract DaoTeamRoundData daoTeamRoundData();
    public abstract DaoScouterName daoScouterName();
    public abstract DaoTeamNames daoTeamNames();

}