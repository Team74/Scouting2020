package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EndgameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        ((ScoutingApplication) this.getApplication()).StartUpDb();
    }

    public void opinionButtonPressed(View opinionButton) {
        Intent intent = new Intent(this, OpinionActivity.class);
        startActivity(intent);
    }

    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);
    }

    public void autonButtonPressed(View autonButton) {
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
    }
    //Climbing Buttons
    public void  didClimbButtonPressed(View didClimbButton) {

    }

    public void didntClimbButtonPressed(View didntClimbButton) {

    }

    public void failedClimbButtonPressed(View failedClimbButton) {

    }
    //Robot Broke Buttons
    public void robotBrokeDownButtonPressed(View robotBrokeDownButton) {

    }

    public void robotDidntBreakDownButtonPressed(View robotDidntBreakDownButton) {

    }
    //Stage Buttons
    public void stageOnePressed(View stageOneButton) {

    }

    public void stageTwoPressed(View stageTwoButton) {

    }

    public void stageThreePressed(View stageThreeButton) {

    }
}
