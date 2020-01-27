package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EndgameActivity extends BaseActivity {

    // Android can suspend, terminate, destroy *any* activity at *any*
    // time for a lot reasons (triggering the OnPause), and a subsequent
    // OnCreate when the activity is restarted.  Member variables like
    // the ones below will be destroyed and lose any previously saved
    // values.  Thus, you need to make sure you reload them every time
    // during your OnCreate from the DB.
    protected boolean endgameDidClimb = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        // load any previously collected data for current team/round
        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();
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

    protected void onPause() {
        super.onPause();
        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }
}
