package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PitScoutingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        // load any previously collected data for current team/round
        //((ScoutingApplication) this.getApplication()).refreshTeamPitData();

        // update display with common items

        // update display with specific items for this activity
    }

    public void MenuButtonPressed(View MenuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    protected void onPause() {
        super.onPause();

        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamData();
    }
}

