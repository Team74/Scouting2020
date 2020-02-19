package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;

import java.util.List;

@Dao
public interface DaoTeamRoundData {
    @Query("SELECT * FROM EntityTeamRoundData WHERE TeamNumber = :teamNumber AND RoundNumber = :roundNumber LIMIT 1")
    EntityTeamRoundData getRecord(int teamNumber, int roundNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityTeamRoundData entityTeamRoundData);

    @Query("SELECT * FROM EntityTeamRoundData")
    EntityTeamRoundData[] getAllTeamRoundData();

    class MatchReportData {
        int TeamNumber;
        int NumRounds;
        float AvgHighScore;
        float AvgLowScore;
        int NumSuccessfulClimbs;
        int NumFailedClimbs;
        float PercentClimbs;
        float PercentBreakdowns;
        float PercentStage2;
        float PercentStage3;
    }
    // NOTE: a custom query string must be provided that returns the above class
    @RawQuery
    MatchReportData[] getMatchReportDataRaw(SupportSQLiteQuery rawQuery);

    class OpinionReportData {
        int TeamNumber;
        float AvgShootingOpinion;
        float AvgClimbingOpinion;
        float AvgSpinningOpinion;
        float AvgAutonOpinion;
        float AvgDriverOpinion;
        float AvgWouldPickOpinion;
        float AvgStarOpinion;
    }
    // NOTE: a custom query string must be provided that returns the above class
    @RawQuery
    OpinionReportData[] getOpinionReportDataRaw(SupportSQLiteQuery rawQuery);

    class TeamRoundDataReportData {
        int TeamNumber;
        int RoundNumber;
        String Scouter;
        String TeamColor;
        int AutonHighScore;
        int AutonLowScore;
        int AutonPickUp;
        boolean AutonStartLine;
        int TeleopHighScore;
        int TeleopLowScore;
        int TeleopPickUp;
        boolean RotationControl;
        boolean PositionControl;
        int Climb;
        boolean BrokeDown;
        int FinalStage;
        int RateShooting;
        int RateClimb;
        int RateWheel;
        int RateAuton;
        int RateDriver;
        boolean WouldPick;
    }
    // NOTE: a custom query string must be provided that returns the above class
    @RawQuery
    TeamRoundDataReportData[] getTeamRoundDataReportDataRaw(SimpleSQLiteQuery simpleSQLiteQuery);
}
