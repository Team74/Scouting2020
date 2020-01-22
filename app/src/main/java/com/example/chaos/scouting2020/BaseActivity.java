package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {
    ScoutingDatabase db;
    EntityTeamRoundData entityTeamRoundData;
    EntityScouterName entityScouterName;
    DaoTeamRoundData teamRound;
    public void StartUp(int teamNumber, int roundNumber){
        db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDatabase")
                .allowMainThreadQueries().build();
        if(teamRound.getRecord(teamNumber,roundNumber) == null){
            teamRound.insert(entityTeamRoundData);
        }
    }


}
