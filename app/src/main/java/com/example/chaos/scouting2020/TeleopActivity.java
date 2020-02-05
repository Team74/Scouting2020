
package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TeleopActivity extends BaseActivity {

    protected ScoutingApplication App;

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

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // load any previously collected data for current team/round
        App.refreshTeamRoundData();

        // update display with common items
        UpdateCommonLayoutItems(R.id.teleopTNumberTextView, R.id.teleopQRNumberTextView, R.id.teleopScouterTextView, R.id.teleopConstraintLayout);

        // update display with specific items for this activity
        TeleopHighGoalNumber = App.getTeleopHighScore();
        TeleopLowGoalNumber = App.getTeleopLowScore();
        TeleopPickUpNumber = App.getTeleopPickUp();
        TeleopRotationControl = App.getRotationControl();
        TeleopPositionControl = App.getPositionControl();

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
        App.setTeleopHighScore(TeleopHighGoalNumber);
        App.setTeleopLowScore(TeleopLowGoalNumber);
        App.setTeleopPickUp(TeleopPickUpNumber);
        App.setRotationControl(TeleopRotationControl);
        App.setPositionControl(TeleopPositionControl);
        // save any updated data for current team/round
        App.saveTeamRoundData();
    }
}
