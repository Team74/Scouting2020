package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AutonActivity extends BaseActivity {

    protected void DisplayHighGoalNumber(){
        TextView HighGoalNumberText = (TextView) findViewById(R.id.autonHighGoalNumber);
        HighGoalNumberText.setText("" + ((ScoutingApplication) this.getApplication()).getAutonHighScore());
    }

    protected void DisplayLowGoalNumber() {
        TextView LowGoalNumberText = (TextView) findViewById(R.id.autonLowGoalNumber);
        LowGoalNumberText.setText("" + ((ScoutingApplication) this.getApplication()).getAutonLowScore());
    }

    protected void DisplayPickUpNumber() {
        TextView PickUpNumberText = (TextView) findViewById(R.id.autonPickUpNumber);
        PickUpNumberText.setText("" + ((ScoutingApplication) this.getApplication()).getAutonPickUp());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);

        ((ScoutingApplication) this.getApplication()).StartUpDb();

        ((ScoutingApplication) this.getApplication()).refreshAutonData(74, 1);
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
        ((ScoutingApplication) this.getApplication()).saveAutonData(74,1);
    }
}
