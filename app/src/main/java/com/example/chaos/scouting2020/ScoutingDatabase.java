package com.example.chaos.scouting2020;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {EntityTeamRoundData.class, EntityScouterName.class},  version = 2)
public abstract class ScoutingDatabase extends RoomDatabase {
    public abstract DaoTeamRoundData daoTeamRoundData();
    public abstract DaoScouterName daoScouterName();
}