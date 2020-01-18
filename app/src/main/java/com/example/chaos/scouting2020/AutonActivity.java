package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AutonActivity extends BaseActivity {
    protected int HighGoalNumber = 0;
    protected int LowGoalNumber = 0;
    protected int PickUpNumber = 0;
    protected void DisplayHighGoalNumber(){
        TextView HighGoalNumberText = (TextView) findViewById(R.id.HighGoalNumber);
        HighGoalNumberText.setText("" + HighGoalNumber);
    }

    protected void DisplayLowGoalNumber() {
        TextView LowGoalNumberText = (TextView) findViewById(R.id.LowGoalNumber);
        LowGoalNumberText.setText("" + LowGoalNumber);
    }

    protected void DisplayPickUpNumber() {
        TextView PickUpNumberText = (TextView) findViewById(R.id.PickUpNumber);
        PickUpNumberText.setText("" + PickUpNumber);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);
        DisplayHighGoalNumber();
        DisplayLowGoalNumber();
        DisplayPickUpNumber();
    }

    public void autonButtonPressed(View autonButton) {
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
    }

    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);

    }

    public void endgameButtonPressed(View endgameButton) {
        Intent intent = new Intent(this, EndgameActivity.class);
        startActivity(intent);
    }

    public void teleopHighGoalPlus(View teleopHighGoalPlus) {
     HighGoalNumber = HighGoalNumber + 1;
     DisplayHighGoalNumber();
    }

    public void teleopHighGoalMinus(View teleopHighGoalMinus) {
        if (HighGoalNumber != 0) {
            HighGoalNumber = HighGoalNumber - 1;
        }
     DisplayHighGoalNumber();
    }

    public void teleopLowGoalPlus(View teleopLowGoalPlus) {
        LowGoalNumber = LowGoalNumber + 1;
        DisplayLowGoalNumber();
    }

    public void teleopLowGoalMinus(View teleopLowGoalMinus) {
        if (LowGoalNumber != 0) {
            LowGoalNumber = LowGoalNumber - 1;
        }
        DisplayLowGoalNumber();
    }

    public void teleopPickUpPlus(View teleopPickUpPlus) {
        PickUpNumber = PickUpNumber + 1;
        DisplayPickUpNumber();
    }

    public void teleopPickUpMinus(View teleopPickUpMinus) {
        if (PickUpNumber != 0) {
            PickUpNumber = PickUpNumber - 1;
        }
        DisplayPickUpNumber();
    }
}
