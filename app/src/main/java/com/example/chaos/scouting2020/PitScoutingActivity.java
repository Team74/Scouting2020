package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class PitScoutingActivity extends BaseActivity {

    protected String ScouterName = "";
    protected int TeamNumber = -1;
    protected String DriveType = "";
    protected boolean PitScoutingShootingLocation1 = false;
    protected boolean PitScoutingShootingLocation2 = false;
    protected boolean PitScoutingShootingLocation3 = false;
    protected boolean PitScoutingStartLocationLeft = false;
    protected boolean PitScoutingStartLocationCenter = false;
    protected boolean PitScoutingStartLocationRight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        // use DB to populate scouter name selection spinner
        List<String> scouters = ((ScoutingApplication) this.getApplication()).getAllScouterNamesAsList();
        AddStringsToSpinner(R.id.pitScoutingScouterSpinner, scouters, 36);

        // use DB to populate team number selection spinner
        List<String> teamNumbers = ((ScoutingApplication) this.getApplication()).getAllTeamNumbersAsList();
        AddStringsToSpinner(R.id.pitScoutingTeamNumberSpinner, teamNumbers, 36);

        List<String> driveTypes = Arrays.asList("Tank","Mecanum","Omni","Swerve","Other");
        AddStringsToSpinner(R.id.pitScoutingDriveBaseSpinner, driveTypes, 36);

        // create a handler to update the page when ever team number changes
        Spinner spinnerItems = (Spinner) findViewById(R.id.pitScoutingTeamNumberSpinner);
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent) { }
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextSize(36);

                // load any previously collected data for current team
                //((ScoutingApplication) this.getApplication()).refreshTeamData();

                // update display with specific items for this activity

            }
        });
    }

    public void MenuButtonPressed(View MenuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void pitScoutingShootingLocation1(View pitScoutingShootingLocation1) {
        PitScoutingShootingLocation1 = !PitScoutingShootingLocation1;
    }

    public void pitScoutingShootingLocation2(View pitScoutingShootingLocation2) {
        PitScoutingShootingLocation2 = !PitScoutingShootingLocation2;
    }

    public void pitScoutingShootingLocation3(View pitScoutingShootingLocation3) {
        PitScoutingShootingLocation3 = !PitScoutingShootingLocation3;
    }

    public void pitScoutingStartLocationLeft(View pitScoutingStartLocationLeft) {
        PitScoutingStartLocationLeft = !PitScoutingStartLocationLeft;
    }

    public void pitScoutingStartLocationCenter(View pitScoutingStartLocationCenter) {
        PitScoutingStartLocationCenter = !PitScoutingStartLocationCenter;
    }

    public void pitScoutingStartLocationRight(View pitScoutingStartLocationRight) {
        PitScoutingStartLocationRight = !PitScoutingStartLocationRight;
    }

    protected void onPause() {
        super.onPause();

        Spinner spinnerScouter = (Spinner) findViewById(R.id.pitScoutingScouterSpinner);
        ScouterName = spinnerScouter.getSelectedItem().toString();

        Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingTeamNumberSpinner);
        TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());

        ((ScoutingApplication) this.getApplication()).setShootingLocation1(PitScoutingShootingLocation1);
        ((ScoutingApplication) this.getApplication()).setShootingLocation2(PitScoutingShootingLocation2);
        ((ScoutingApplication) this.getApplication()).setShootingLocation3(PitScoutingShootingLocation3);
        ((ScoutingApplication) this.getApplication()).setStartLocationLeft(PitScoutingStartLocationLeft);
        ((ScoutingApplication) this.getApplication()).setStartLocationCenter(PitScoutingStartLocationCenter);
        ((ScoutingApplication) this.getApplication()).setStartLocationRight(PitScoutingStartLocationRight);

        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamData();
    }
}

