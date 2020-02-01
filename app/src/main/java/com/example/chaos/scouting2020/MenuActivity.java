package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void MatchScoutingButtonPressed(View MatchScoutingButton) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void PitScoutingButtonPressed(View PitScoutingButton) {
        Intent intent = new Intent(this, PitScoutingActivity.class);
        startActivity(intent);
    }

    public void DataViewingButtonPressed(View DataViewingButton) {
        //Intent intent = new Intent(this, DataViewingActivity.class);
        //startActivity(intent);
    }
}
