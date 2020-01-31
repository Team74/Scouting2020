package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class AutonActivity extends BaseActivity {

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

        // load any previously collected data for current team/round
        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();

        AutonHighGoalNumber = ((ScoutingApplication) this.getApplication()).getAutonHighScore();
        AutonLowGoalNumber = ((ScoutingApplication) this.getApplication()).getAutonLowScore();
        AutonPickUpNumber = ((ScoutingApplication) this.getApplication()).getAutonPickUp();
        AutonStartLineMove = ((ScoutingApplication) this.getApplication()).getAutonStartLine();

        if (((ScoutingApplication) this.getApplication()).getTeamColor().equals("Blue")) {
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.CSVNames);
            layout.setBackgroundColor(Color.argb(255,53, 121, 220));
        }

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

    // TBD: temp button for testing csv code
    public void CSVNamesButton(View CSVNamesButton) {
        Log.d("CSV", "Starts");
        ((ScoutingApplication) this.getApplication()).exportScouterNames();
        Log.d("CSV", "works");
    }

    public void autonStartLineYes(View autonStartLineYes) {
        AutonStartLineMove = true;
    }

    public void autonStartLineNo(View autonStartLineNo) {
        AutonStartLineMove = false;
    }

    protected void onPause() {
        super.onPause();
        ((ScoutingApplication) this.getApplication()).setAutonHighScore(AutonHighGoalNumber);
        ((ScoutingApplication) this.getApplication()).setAutonLowScore(AutonLowGoalNumber);
        ((ScoutingApplication) this.getApplication()).setAutonPickUp(AutonPickUpNumber);
        ((ScoutingApplication) this.getApplication()).setAutonStartLine(AutonStartLineMove);
        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }
}
