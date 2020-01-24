package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    // database members
    ScoutingDatabase db;
    EntityTeamRoundData entityTeamRoundData = null;
    EntityScouterName entityScouterName = null;
    DaoTeamRoundData daoTeamRoundData = null;
    DaoScouterName daoScouterName = null;

    // scouting data members
    protected int HighGoalNumber = 0;
    protected int LowGoalNumber = 0;
    protected int PickUpNumber = 0;
    protected String TColor = null;
    protected String Scout = null;
    protected int TNumber = -1;


    public void StartUpDb(){
        // get room (db)
        db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDatabase")
                .allowMainThreadQueries().build();

        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = db.daoTeamRoundData();
            daoScouterName = db.daoScouterName();
        }

        // create entity (record) objects for each table
        entityTeamRoundData = new EntityTeamRoundData();
        entityScouterName = new EntityScouterName();
    }

    protected void refreshAutonData(int teamNumber, int roundNumber){
        entityTeamRoundData = daoTeamRoundData.getRecord(teamNumber, roundNumber);
        if (entityTeamRoundData != null) {
            HighGoalNumber = entityTeamRoundData.AutonHighScore;
            LowGoalNumber = entityTeamRoundData.AutonLowScore;
            PickUpNumber = entityTeamRoundData.AutonPickUp;
        }else{
            HighGoalNumber = 0;
            LowGoalNumber = 0;
            PickUpNumber = 0;
        }
    }

    protected void saveAutonData(int teamNumber, int roundNumber){
        daoTeamRoundData.updateAuton(HighGoalNumber, LowGoalNumber, PickUpNumber, 74, 1);
    }

    public void AddAllRounds() {
        for(int teamNumber = 1; teamNumber < 75; teamNumber++ ) {
            for(int roundNumber = 1; roundNumber < 61; roundNumber++ ) {
                entityTeamRoundData.TeamNumber = teamNumber;
                entityTeamRoundData.RoundNumber = roundNumber;
                daoTeamRoundData.insert(entityTeamRoundData);
            }
        }
    }

    public void AddAllScouters() {
        String[] scouters = { "Allen Z.", "Ben Y.", "Clara X." };
        for (String scouter: scouters) {
            entityScouterName.ScouterName = scouter;
            daoScouterName.insert(entityScouterName);
        }
    }

}
