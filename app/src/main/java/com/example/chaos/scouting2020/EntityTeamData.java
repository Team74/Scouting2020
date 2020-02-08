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

    // who was the scouter that entered this data
    @ColumnInfo
    public String PitScouter;

    @ColumnInfo
    public int RobotWeight;

    @ColumnInfo
    public String RobotDriveBaseType;

    @ColumnInfo
    public String PitScoutingNotes;

    @ColumnInfo
    public boolean ShootingLocation1;

    @ColumnInfo
    public boolean ShootingLocation2;

    @ColumnInfo
    public boolean ShootingLocation3;

    @ColumnInfo
    public boolean StartLocationLeft;

    @ColumnInfo
    public boolean StartLocationCenter;

    @ColumnInfo
    public boolean StartLocationRight;

}
