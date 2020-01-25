package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    // database members
    ScoutingDatabase db = null;
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
