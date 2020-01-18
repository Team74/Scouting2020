package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AutonActivity extends AppCompatActivity {
    ScoutingDatabase db;
    EntityTeamRoundData entityTeamRoundData;
    EntityScouterName entityScouterName;
    DaoTeamRoundData teamRound;
    protected int HighGoalNumber = 0;
    protected int LowGoalNumber = 0;
    protected int PickUpNumber = 0;

    protected void refresh(int teamNumber, int roundNumber){
        entityTeamRoundData = teamRound.getRecord(teamNumber, roundNumber);
        if (entityTeamRoundData != null) {
            HighGoalNumber = entityTeamRoundData.AutonHighScore;
            LowGoalNumber = entityTeamRoundData.AutonLowScore;
            PickUpNumber = entityTeamRoundData.AutonPickUp;
        }
    }

    protected void DisplayHighGoalNumber(){
        TextView HighGoalNumberText = (TextView) findViewById(R.id.HighGoalNumber);
        HighGoalNumberText.setText("" + HighGoalNumber);
    }
    protected void DisplayLowGoalNumber() {
        TextView LowGoalNumberText = (TextView) findViewById(R.id.LowGoalNumber);
        LowGoalNumberText.setText("" + LowGoalNumber);
    }
    protected void DisplayPickUpNumber() {
        TextView PickUpNumberText = (TextView) findViewById(R.id.PickUpNumber);
        PickUpNumberText.setText("" + PickUpNumber);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDatabase")
                .allowMainThreadQueries().build();
        teamRound = db.daoTeamRoundData();
        DaoScouterName name = db.daoScouterName();
        entityTeamRoundData = teamRound.getRecord(74,1);
        entityScouterName = new EntityScouterName();
        //entityTeamRoundData.TeamNumber = 74;
        //entityTeamRoundData.RoundNumber = 1;
        //teamRound.insert(entityTeamRoundData);
        //EntityTeamRoundData entityData = teamRound.getRecord(74, 1);
        //Log.d("testing", Integer.toString(entityData.TeamNumber));
        setContentView(R.layout.activity_auton);
        refresh(74, 1);
        DisplayHighGoalNumber();
        DisplayLowGoalNumber();
        DisplayPickUpNumber();
    }

    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);
    }

    public void teleopHighGoalPlus(View teleopHighGoalPlus) {
     HighGoalNumber = HighGoalNumber + 1;
     DisplayHighGoalNumber();
    }

    public void teleopHighGoalMinus(View teleopHighGoalMinus) {
        if (HighGoalNumber != 0) {
            HighGoalNumber = HighGoalNumber - 1;
            DisplayHighGoalNumber();
        }
    }
    public void teleopLowGoalPlus(View teleopLowGoalPlus) {
        LowGoalNumber = LowGoalNumber + 1;
        DisplayLowGoalNumber();
    }

    public void teleopLowGoalMinus(View teleopLowGoalMinus) {
        if (LowGoalNumber != 0) {
            LowGoalNumber = LowGoalNumber - 1;
            DisplayLowGoalNumber();
        }
    }
    public void teleopPickUpPlus(View teleopPickUpPlus) {
        PickUpNumber = PickUpNumber + 1;
        DisplayPickUpNumber();
    }

    public void teleopPickUpMinus(View teleopPickUpMinus) {
        if(PickUpNumber != 0) {
            PickUpNumber = PickUpNumber - 1;
            DisplayPickUpNumber();
        }
    }

    protected void onPause() {
        super.onPause();
        teamRound.updateAuton(HighGoalNumber, LowGoalNumber, PickUpNumber, 74, 1);
    }

    protected void onResume() {
        super.onResume();
        refresh(74, 1);
    }
}
