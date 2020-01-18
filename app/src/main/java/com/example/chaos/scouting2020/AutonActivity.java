package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AutonActivity extends AppCompatActivity {
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

    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);

    }

    public void teleopHighGoalPlus(View teleopHighGoalPlus) {
     HighGoalNumber = HighGoalNumber + 1;
     DisplayHighGoalNumber();
    }

    public void teleopHighGoalMinus(View teleopHighGoalMinus) {
     HighGoalNumber = HighGoalNumber - 1;
     DisplayHighGoalNumber();
    }
    public void teleopLowGoalPlus(View teleopLowGoalPlus) {
        LowGoalNumber = LowGoalNumber + 1;
        DisplayLowGoalNumber();
    }

    public void teleopLowGoalMinus(View teleopLowGoalMinus) {
        LowGoalNumber = LowGoalNumber - 1;
        DisplayLowGoalNumber();
    }
    public void teleopPickUpPlus(View teleopPickUpPlus) {
        PickUpNumber = PickUpNumber + 1;
        DisplayPickUpNumber();
    }

    public void teleopPickUpMinus(View teleopPickUpMinus) {
        PickUpNumber = PickUpNumber - 1;
        DisplayPickUpNumber();
    }
}
