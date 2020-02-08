package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class PitScoutingMainActivity extends BaseActivity {

    protected ScoutingApplication App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_main);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // make sure db is inited
        App.newTeamRoundData();
        App.newTeamData();

        // use DB to populate scouter name selection spinner
        List<String> scouters = App.getAllScouterNamesAsList();
        AddStringsToSpinner(R.id.pitScoutingMainScouterSpinner, scouters, 36);

        // use DB to populate team number selection spinner
        List<String> teamNumbers = App.getAllTeamNumbersAsList();
        AddStringsToSpinner(R.id.pitScoutingMainTeamNumberSpinner, teamNumbers, 36);

        List<String> driveTypes = Arrays.asList("Tank","Mecanum","Omni","Swerve","Other");
        AddStringsToSpinner(R.id.pitScoutingMainDriveBaseSpinner, driveTypes, 36);
    }

    public void pitScoutingMapButtonPressed(View pitScoutingMapButton) {
        Intent intent = new Intent(this, PitScoutingMapActivity.class);
        startActivity(intent);
    }

    public void resetButtonPressed(View resetButton) {
        Intent intent = new Intent(this, PitScoutingMainActivity.class);
        startActivity(intent);
    }

    public void menuButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
