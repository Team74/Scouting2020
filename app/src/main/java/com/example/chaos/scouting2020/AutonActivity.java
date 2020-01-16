package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AutonActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);
        // add our common menu buttons
        AddMenuButtons(R.id.activity_auton);
        DisplayValue(R.id.HighGoalNumber, highGoalNumber);
    }

    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);
    }

    public void teleopHighGoalPlus(View teleopHighGoalPlus) {
        highGoalNumber = highGoalNumber + 1;
        DisplayValue(R.id.HighGoalNumber, highGoalNumber);
    }

    public void teleopHighGoalMinus(View teleopHighGoalMinus) {
        highGoalNumber = highGoalNumber - 1;
        DisplayValue(R.id.HighGoalNumber, highGoalNumber);
    }
}
