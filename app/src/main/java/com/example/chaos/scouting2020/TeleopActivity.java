
package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeleopActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);
        // add our common menu buttons
        AddMenuButtons(R.id.activity_teleop);
        DisplayValue(R.id.teleopHighGoalNumberText, highGoalNumber);
    }
    public void endgameButtonPressed(View endgameButton) {
        Intent intent = new Intent(this, EndgameActivity.class);
        startActivity(intent);
    }
    public void teleopHighGoalPlusButtonPressed(View teleopHighGoalPlusButton) {
        highGoalNumber = highGoalNumber + 1;
        DisplayValue(R.id.teleopHighGoalNumberText, highGoalNumber);
    }
    public void teleopHighGoalMinusButtonPressed(View teleopHighGoalMinusButton) {
        highGoalNumber = highGoalNumber - 1;
        DisplayValue(R.id.teleopHighGoalNumberText, highGoalNumber);
    }
}
