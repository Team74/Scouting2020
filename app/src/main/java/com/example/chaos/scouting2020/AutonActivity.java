package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class AutonActivity extends BaseActivity {

    protected ScoutingApplication App;

    // Android can suspend, terminate, destroy *any* activity at *any*
    // time for a lot reasons (triggering the OnPause), and a subsequent
    // OnCreate when the activity is restarted.  Member variables like
    // the ones below will be destroyed and lose any previously saved
    // values.  Thus, you need to make sure you reload them every time
    // during your OnCreate from the DB.
    protected int AutonHighGoalNumber = 0;
    protected int AutonLowGoalNumber = 0;
    protected int AutonPickUpNumber = 0;
    protected boolean AutonStartLineMove = false;

    protected void DisplayHighGoalNumber() {
        TextView HighGoalNumberText = (TextView) findViewById(R.id.autonHighGoalNumberTextView);
        HighGoalNumberText.setText("" + AutonHighGoalNumber);
    }

    protected void DisplayLowGoalNumber() {
        TextView LowGoalNumberText = (TextView) findViewById(R.id.autonLowGoalNumberTextView);
        LowGoalNumberText.setText("" + AutonLowGoalNumber);
    }

    protected void DisplayPickUpNumber() {
        TextView PickUpNumberText = (TextView) findViewById(R.id.autonPickUpNumberTextView);
        PickUpNumberText.setText("" + AutonPickUpNumber);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // load any previously collected data for current team/round
        App.refreshTeamRoundData();

        // update display with common items
        UpdateCommonLayoutItems(R.id.autonTNumberTextView, R.id.autonQRNumberTextView, R.id.autonScouterTextView, R.id.autonConstraintLayout);

        // update display with specific items for this activity
        AutonHighGoalNumber = App.getAutonHighScore();
        AutonLowGoalNumber = App.getAutonLowScore();
        AutonPickUpNumber = App.getAutonPickUp();
        AutonStartLineMove = App.getAutonStartLine();

        DisplayHighGoalNumber();
        DisplayLowGoalNumber();
        DisplayPickUpNumber();

        RadioButton Moved = (RadioButton) findViewById(R.id.autonYesRadioButton);
        Moved.setChecked(AutonStartLineMove);
        Moved = (RadioButton) findViewById(R.id.autonNoRadioButton);
        Moved.setChecked(!AutonStartLineMove);
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
        AutonHighGoalNumber = AutonHighGoalNumber + 1;
        DisplayHighGoalNumber();
    }

    public void autonHighGoalMinus(View autonHighGoalMinus) {
        if (AutonHighGoalNumber > 0) {
            AutonHighGoalNumber = AutonHighGoalNumber - 1;
            DisplayHighGoalNumber();
        }
    }

    public void autonLowGoalPlus(View autonLowGoalPlus) {
        AutonLowGoalNumber = AutonLowGoalNumber + 1;
        DisplayLowGoalNumber();
    }

    public void autonLowGoalMinus(View autonLowGoalMinus) {
        if (AutonLowGoalNumber > 0) {
            AutonLowGoalNumber = AutonLowGoalNumber - 1;
            DisplayLowGoalNumber();
        }
    }

    public void autonPickUpPlus(View autonPickUpPlus) {
        AutonPickUpNumber = AutonPickUpNumber + 1;
        DisplayPickUpNumber();
    }

    public void autonPickUpMinus(View autonPickUpMinus) {
        if (AutonPickUpNumber > 0) {
            AutonPickUpNumber = AutonPickUpNumber - 1;
            DisplayPickUpNumber();
        }
    }

    public void autonStartLineYes(View autonStartLineYes) {
        AutonStartLineMove = true;
    }

    public void autonStartLineNo(View autonStartLineNo) {
        AutonStartLineMove = false;
    }

    protected void onPause() {
        super.onPause();
        App.setAutonHighScore(AutonHighGoalNumber);
        App.setAutonLowScore(AutonLowGoalNumber);
        App.setAutonPickUp(AutonPickUpNumber);
        App.setAutonStartLine(AutonStartLineMove);
        // save any updated data for current team/round
        App.saveTeamRoundData();
    }
}
