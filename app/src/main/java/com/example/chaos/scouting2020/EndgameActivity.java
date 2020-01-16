package com.example.chaos.scouting2020;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EndgameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);
        // add our common menu buttons
        AddMenuButtons(R.id.activity_endgame);
    }
    public void opinionButtonPressed(View opinionButton) {
        Intent intent = new Intent(this, OpinionActivity.class);
        startActivity(intent);
    }
}
