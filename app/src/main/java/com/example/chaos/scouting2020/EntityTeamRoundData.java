package com.example.chaos.scouting2020;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(primaryKeys = {"TeamNumber", "RoundNumber"})
public class EntityTeamRoundData {

    @ColumnInfo
    public int TeamNumber;

    @ColumnInfo
    public int RoundNumber;

    // who was the scouter that entered this data
    @ColumnInfo
    public String Scouter;

    @ColumnInfo
    public String TeamColor;

    @ColumnInfo
    public int AutonHighScore;

    @ColumnInfo
    public int AutonLowScore;

    @ColumnInfo
    public int AutonPickUp;

    @ColumnInfo
    public boolean AutonStartLine;

    @ColumnInfo
    public int TeleopHighScore;

    @ColumnInfo
    public int TeleopLowScore;

    @ColumnInfo
    public int TeleopPickUp;

    @ColumnInfo
    public boolean RotationControl;

    @ColumnInfo
    public boolean PositionControl;

    @ColumnInfo
    public int Climb;  // 1=climb successful, 2=climb not attempted, 3=climb failed

    @ColumnInfo
    public boolean BrokeDown;

    @ColumnInfo
    public int FinalStage;

    @ColumnInfo
    public String Notes;

    @ColumnInfo
    public int RateShooting;

    @ColumnInfo
    public int RateClimb;

    @ColumnInfo
    public int RateWheel;

    @ColumnInfo
    public int RateAuton;

    @ColumnInfo
    public int RateDiver;

    @ColumnInfo
    public boolean WouldPick;
}
