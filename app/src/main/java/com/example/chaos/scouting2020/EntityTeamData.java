package com.example.chaos.scouting2020;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"TeamNumber"})
public class EntityTeamData {

    @ColumnInfo
    public int TeamNumber;

    @ColumnInfo
    @NonNull
    public String TeamName;

    @ColumnInfo
    public int RobotWeight;

    @ColumnInfo
    public String RobotDriveBaseType;

    @ColumnInfo
    public String RobotNotes;

}
