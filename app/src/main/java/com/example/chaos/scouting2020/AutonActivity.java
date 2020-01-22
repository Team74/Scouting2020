package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class AutonActivity extends BaseActivity {
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
        else{
            HighGoalNumber = 0;
            LowGoalNumber = 0;
            PickUpNumber = 0;
        }
    }

    protected void DisplayHighGoalNumber(){
        TextView HighGoalNumberText = (TextView) findViewById(R.id.autonHighGoalNumber);
        HighGoalNumberText.setText("" + HighGoalNumber);
    }

    protected void DisplayLowGoalNumber() {
        TextView LowGoalNumberText = (TextView) findViewById(R.id.autonLowGoalNumber);
        LowGoalNumberText.setText("" + LowGoalNumber);
    }

    protected void DisplayPickUpNumber() {
        TextView PickUpNumberText = (TextView) findViewById(R.id.autonPickUpNumber);
        PickUpNumberText.setText("" + PickUpNumber);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDatabase")
                .allowMainThreadQueries().build();
        teamRound = db.daoTeamRoundData();
        refresh(1,1);
        Log.d("testing", Integer.toString(HighGoalNumber));
        DaoScouterName name = db.daoScouterName();
        entityTeamRoundData = teamRound.getRecord(1,1);
        entityScouterName = new EntityScouterName();
        //teamRound.insert(entityTeamRoundData);
        EntityTeamRoundData entityData = teamRound.getRecord(1, 1);
        //Log.d("testing", Integer.toString(entityData.TeamNumber));
        setContentView(R.layout.activity_auton);
        //refresh(74, 1);
        DisplayHighGoalNumber();
        DisplayLowGoalNumber();
        DisplayPickUpNumber();
    }

    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);

    }

    public void endgameButtonPressed(View endgameButton) {
        Intent intent = new Intent(this, EndgameActivity.class);
        startActivity(intent);
    }

    public void autonHighGoalPlus(View autonHighGoalPlus) {
     HighGoalNumber = HighGoalNumber + 1;
     DisplayHighGoalNumber();
    }

    public void autonHighGoalMinus(View autonHighGoalMinus) {
        if (HighGoalNumber != 0) {
            HighGoalNumber = HighGoalNumber - 1;
            DisplayHighGoalNumber();
        }
    }

    public void autonLowGoalPlus(View autonLowGoalPlus) {
        LowGoalNumber = LowGoalNumber + 1;
        DisplayLowGoalNumber();
    }

    public void autonLowGoalMinus(View autonLowGoalMinus) {
        if (LowGoalNumber != 0) {
            LowGoalNumber = LowGoalNumber - 1;
            DisplayLowGoalNumber();
        }
    }

    public void autonPickUpPlus(View autonPickUpPlus) {
        PickUpNumber = PickUpNumber + 1;
        DisplayPickUpNumber();
    }

    public void autonPickUpMinus(View autonPickUpMinus) {
        if(PickUpNumber != 0) {
            PickUpNumber = PickUpNumber - 1;
            DisplayPickUpNumber();
        }
    }

    protected void onPause() {
        super.onPause();
        entityTeamRoundData = new EntityTeamRoundData();
        entityTeamRoundData.TeamNumber = 1;
        entityTeamRoundData.RoundNumber = 1;
        teamRound.insert(entityTeamRoundData);
        teamRound.updateAuton(HighGoalNumber, LowGoalNumber, PickUpNumber, 1, 1);
        Log.d("testing pause", Integer.toString(teamRound.getRecord(1,1).AutonHighScore));
    }
}
