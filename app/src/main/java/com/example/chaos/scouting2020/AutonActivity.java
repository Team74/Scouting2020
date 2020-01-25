package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AutonActivity extends BaseActivity {

    //Auton data members
    protected int AutonHighGoalNumber = 0;
    protected int AutonLowGoalNumber = 0;
    protected int AutonPickUpNumber = 0;

    protected void DisplayHighGoalNumber(){
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

        ((ScoutingApplication) this.getApplication()).StartUpDb();

        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();

        AutonHighGoalNumber = ((ScoutingApplication) this.getApplication()).getAutonHighScore();
        AutonLowGoalNumber = ((ScoutingApplication) this.getApplication()).getAutonLowScore();
        AutonPickUpNumber = ((ScoutingApplication) this.getApplication()).getAutonPickUp();

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
        if(AutonPickUpNumber > 0) {
            AutonPickUpNumber = AutonPickUpNumber - 1;
            DisplayPickUpNumber();
        }
    }

    protected void onPause() {
        super.onPause();
        ((ScoutingApplication) this.getApplication()).setAutonHighScore(AutonHighGoalNumber);
        ((ScoutingApplication) this.getApplication()).setAutonLowScore(AutonLowGoalNumber);
        ((ScoutingApplication) this.getApplication()).setAutonPickUp(AutonPickUpNumber);
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }
}
