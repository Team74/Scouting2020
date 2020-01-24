package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AutonActivity extends BaseActivity {

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
        setContentView(R.layout.activity_auton);

        StartUpDb();

        refreshAutonData(74, 1);
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
        if (HighGoalNumber > 0) {
            HighGoalNumber = HighGoalNumber - 1;
            DisplayHighGoalNumber();
        }
    }

    public void autonLowGoalPlus(View autonLowGoalPlus) {
        LowGoalNumber = LowGoalNumber + 1;
        DisplayLowGoalNumber();
    }

    public void autonLowGoalMinus(View autonLowGoalMinus) {
        if (LowGoalNumber > 0) {
            LowGoalNumber = LowGoalNumber - 1;
            DisplayLowGoalNumber();
        }
    }

    public void autonPickUpPlus(View autonPickUpPlus) {
        PickUpNumber = PickUpNumber + 1;
        DisplayPickUpNumber();
    }

    public void autonPickUpMinus(View autonPickUpMinus) {
        if(PickUpNumber > 0) {
            PickUpNumber = PickUpNumber - 1;
            DisplayPickUpNumber();
        }
    }

    protected void onPause() {
        super.onPause();
        saveAutonData(74, 1);
    }
}
