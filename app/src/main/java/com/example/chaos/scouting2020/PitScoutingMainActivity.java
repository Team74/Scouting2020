package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PitScoutingMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_main);
    }

    public void pitScoutingMapButtonPressed(View pitScoutingMapButton) {
        Intent intent = new Intent(this, PitScoutingMapActivity.class);
        startActivity(intent);
    }
}
