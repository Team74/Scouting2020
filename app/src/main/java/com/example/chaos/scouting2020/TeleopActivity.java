
package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TeleopActivity extends AppCompatActivity {
    private int highGoalNumber = 0;
    protected void displayHighGoalNumber() {
        TextView teleopHighGoalNumberText = (TextView) findViewById(R.id.teleopHighGoalNumberText);
        teleopHighGoalNumberText.setText("" + highGoalNumber);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);
        displayHighGoalNumber();
    }
    public void endgameButtonPressed(View endgameButton) {
        Intent intent = new Intent(this, EndgameActivity.class);
        startActivity(intent);
    }
    public void teleopHighGoalPlusButtonPressed(View teleopHighGoalPlusButton) {
        highGoalNumber = highGoalNumber + 1;
        displayHighGoalNumber();
    }
    public void teleopHighGoalMinusButtonPressed(View teleopHighGoalMinusButton) {
        highGoalNumber = highGoalNumber - 1;
        displayHighGoalNumber();
    }
}
