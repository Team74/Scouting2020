
package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TeleopActivity extends BaseActivity {

    // Android can suspend, terminate, destroy *any* activity at *any*
    // time for a lot reasons (triggering the OnPause), and a subsequent
    // OnCreate when the activity is restarted.  Member variables like
    // the ones below will be destroyed and lose any previously saved
    // values.  Thus, you need to make sure you reload them every time
    // during your OnCreate from the DB.
    protected int TeleopHighGoalNumber = 0;
    protected int TeleopLowGoalNumber = 0;
    protected int TeleopPickUpNumber = 0;
    protected boolean TeleopRotationControl = false;
    protected boolean TeleopPositionControl = false;

    protected void DisplayHighGoalNumber() {
        TextView HighGoalNumberText = (TextView) findViewById(R.id.teleopHighGoalNumberTextView);
        HighGoalNumberText.setText("" + TeleopHighGoalNumber);
    }

    protected void DisplayLowGoalNumber() {
        TextView LowGoalNumberText = (TextView) findViewById(R.id.teleopLowGoalNumberTextView);
        LowGoalNumberText.setText("" + TeleopLowGoalNumber);
    }

    protected void DisplayPickUpNumber() {
        TextView PickUpNumberText = (TextView) findViewById(R.id.teleopPickUpNumberTextView);
        PickUpNumberText.setText("" + TeleopPickUpNumber);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);

        // load any previously collected data for current team/round
        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();

        TeleopHighGoalNumber = ((ScoutingApplication) this.getApplication()).getTeleopHighScore();
        TeleopLowGoalNumber = ((ScoutingApplication) this.getApplication()).getTeleopLowScore();
        TeleopPickUpNumber = ((ScoutingApplication) this.getApplication()).getTeleopPickUp();
        TeleopRotationControl = ((ScoutingApplication) this.getApplication()).getRotationControl();
        TeleopPositionControl = ((ScoutingApplication) this.getApplication()).getPositionControl();

        DisplayHighGoalNumber();
        DisplayLowGoalNumber();
        DisplayPickUpNumber();

        ToggleButton RotationControl = (ToggleButton) findViewById(R.id.teleopRotationControlToggleButton);
        RotationControl.setChecked(TeleopRotationControl);

        ToggleButton PositionControl = (ToggleButton) findViewById(R.id.teleopPositionControlToggleButton);
        PositionControl.setChecked(TeleopPositionControl);
    }

    public void autonButtonPressed(View autonButton) {
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
    }

    public void endgameButtonPressed(View endgameButton) {
        Intent intent = new Intent(this, EndgameActivity.class);
        startActivity(intent);
    }

    public void teleopHighGoalPlus(View teleopHighGoalPlus) {
        TeleopHighGoalNumber = TeleopHighGoalNumber + 1;
        DisplayHighGoalNumber();
    }

    public void teleopHighGoalMinus(View teleopHighGoalMinus) {
        if (TeleopHighGoalNumber > 0) {
            TeleopHighGoalNumber = TeleopHighGoalNumber - 1;
            DisplayHighGoalNumber();
        }
    }

    public void teleopLowGoalPlus(View teleopLowGoalPlus) {
        TeleopLowGoalNumber = TeleopLowGoalNumber + 1;
        DisplayLowGoalNumber();
    }

    public void teleopLowGoalMinus(View teleopLowGoalMinus) {
        if (TeleopLowGoalNumber > 0) {
            TeleopLowGoalNumber = TeleopLowGoalNumber - 1;
            DisplayLowGoalNumber();
        }
    }

    public void teleopPickUpPlus(View teleopPickUpPlus) {
        TeleopPickUpNumber = TeleopPickUpNumber + 1;
        DisplayPickUpNumber();
    }

    public void teleopPickUpMinus(View teleopPickUpMinus) {
        if (TeleopPickUpNumber > 0) {
            TeleopPickUpNumber = TeleopPickUpNumber - 1;
            DisplayPickUpNumber();
        }
    }

    public void teleopRotationControl(View teleopRotationControl) {
        TeleopRotationControl = !TeleopRotationControl;
    }

    public void teleopPositionControl(View teleopPositionControl) {
        TeleopPositionControl = !TeleopPositionControl;
    }

    protected void onPause() {
        super.onPause();
        ((ScoutingApplication) this.getApplication()).setTeleopHighScore(TeleopHighGoalNumber);
        ((ScoutingApplication) this.getApplication()).setTeleopLowScore(TeleopLowGoalNumber);
        ((ScoutingApplication) this.getApplication()).setTeleopPickUp(TeleopPickUpNumber);
        ((ScoutingApplication) this.getApplication()).setRotationControl(TeleopRotationControl);
        ((ScoutingApplication) this.getApplication()).setPositionControl(TeleopPositionControl);
        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }
}
