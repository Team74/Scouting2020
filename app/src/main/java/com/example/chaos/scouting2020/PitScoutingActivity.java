package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.List;

public class PitScoutingActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected String ScouterName = "";
    protected int TeamNumber = -1;
    protected int PitScoutingRobotWeight = -1;
    protected String RobotDriveBaseType = "";
    protected String PitScoutingNotes = "";
    protected boolean PitScoutingShootingLocation1 = false;
    protected boolean PitScoutingShootingLocation2 = false;
    protected boolean PitScoutingShootingLocation3 = false;
    protected boolean PitScoutingStartLocationLeft = false;
    protected boolean PitScoutingStartLocationCenter = false;
    protected boolean PitScoutingStartLocationRight = false;

    protected void UpdatePitScoutingFields() {
        // since the TeamNumber changed, we need to refresh the
        // team data record...
        App.setTeamNumber(TeamNumber);
        App.refreshTeamData();

        // update all the fields on the screen here
        RobotDriveBaseType = App.getRobotDriveBaseType();
        PitScoutingShootingLocation1 = App.getShootingLocation1();
        PitScoutingShootingLocation2 = App.getShootingLocation2();
        PitScoutingShootingLocation3 = App.getShootingLocation3();
        PitScoutingStartLocationLeft = App.getStartLocationLeft();
        PitScoutingStartLocationCenter = App.getStartLocationCenter();
        PitScoutingStartLocationRight = App.getStartLocationRight();
        PitScoutingRobotWeight = App.getRobotWeight();
        PitScoutingNotes = App.getPitScoutingNotes();

        // TBD update drive base type spinner
        SetSpinnerByValue(R.id.pitScoutingDriveBaseSpinner, RobotDriveBaseType);

        // Update weight textView
        TextView WeightEditText = (EditText) findViewById(R.id.pitScoutingWeightEditText);
        WeightEditText.setText("" + PitScoutingRobotWeight);


        //TBD update notes textView
        TextView NotesEditText = (EditText) findViewById(R.id.pitScoutingNotesEditText);
        NotesEditText.setText("" + PitScoutingNotes);


        // TBD update shooting position toggleButtons
        ToggleButton ShootingLocation1 = (ToggleButton) findViewById(R.id.pitScoutingSafeZoneShootingToggleButton);
        ShootingLocation1.setChecked(PitScoutingShootingLocation1);

        ToggleButton ShootingLocation2 = (ToggleButton) findViewById(R.id.pitScoutingAutonLineShootingToggleButton);
        ShootingLocation2.setChecked(PitScoutingShootingLocation2);

        ToggleButton ShootingLocation3 = (ToggleButton) findViewById(R.id.pitScoutingBehindGeneratorShootingToggleButton);
        ShootingLocation3.setChecked(PitScoutingShootingLocation3);

        // TBD update start position toggleButtons

        ToggleButton StartLocationLeft = (ToggleButton) findViewById(R.id.pitScoutingLeftStartToggleButton);
        StartLocationLeft.setChecked(PitScoutingStartLocationLeft);

        ToggleButton StartLocationCenter = (ToggleButton) findViewById(R.id.pitScoutingCenterStartToggleButton);
        StartLocationCenter.setChecked(PitScoutingStartLocationCenter);

        ToggleButton StartLocationRight = (ToggleButton) findViewById(R.id.pitScoutingRightStartToggleButton);
        StartLocationRight.setChecked(PitScoutingStartLocationRight);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // use DB to populate scouter name selection spinner
        List<String> scouters = App.getAllScouterNamesAsList();
        AddStringsToSpinner(R.id.pitScoutingScouterSpinner, scouters, 36);

        // use DB to populate team number selection spinner
        List<String> teamNumbers = App.getAllTeamNumbersAsList();
        AddStringsToSpinner(R.id.pitScoutingTeamNumberSpinner, teamNumbers, 36);

        List<String> driveTypes = Arrays.asList("Tank","Mecanum","Omni","Swerve","Other");
        AddStringsToSpinner(R.id.pitScoutingDriveBaseSpinner, driveTypes, 36);

        // create a handler to update the page when ever team number changes
        Spinner spinnerItems = (Spinner) findViewById(R.id.pitScoutingTeamNumberSpinner);
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent) { };
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextSize(36);
                // load any previously collected data for current team
                try {
                    Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingTeamNumberSpinner);
                    TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());
                } catch (Exception e) {
                    // some sort of error converting TeamNumber to int
                    e.printStackTrace();
                    TeamNumber = -1;
                }

                // ... and update display with specific items for this activity
                UpdatePitScoutingFields();
            }
        });

        // Update fields for first time
        UpdatePitScoutingFields();
    }

    public void menuButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void resetButtonPressed(View resetButton) {
        Intent intent = new Intent(this, PitScoutingActivity.class);
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

        EditText QRNumberEditText = (EditText) findViewById(R.id.pitScoutingWeightEditText);
        try {
            PitScoutingRobotWeight = Integer.parseInt(QRNumberEditText.getText().toString());
        } catch (Exception e) {
            // some sort of error converting RoundNumber to int
            e.printStackTrace();
            PitScoutingRobotWeight = -1;
        }

        Spinner spinnerScouter = (Spinner) findViewById(R.id.pitScoutingScouterSpinner);
        ScouterName = spinnerScouter.getSelectedItem().toString();

        Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingTeamNumberSpinner);
        TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());

        Spinner spinnerRobotDriveBaseType = (Spinner) findViewById(R.id.pitScoutingDriveBaseSpinner);
        RobotDriveBaseType = spinnerRobotDriveBaseType.getSelectedItem().toString();

        App.setRobotDriveBaseType(RobotDriveBaseType);
        App.setShootingLocation1(PitScoutingShootingLocation1);
        App.setShootingLocation2(PitScoutingShootingLocation2);
        App.setShootingLocation3(PitScoutingShootingLocation3);
        App.setStartLocationLeft(PitScoutingStartLocationLeft);
        App.setStartLocationCenter(PitScoutingStartLocationCenter);
        App.setStartLocationRight(PitScoutingStartLocationRight);
        App.setRobotWeight(PitScoutingRobotWeight);

        // save any updated data for current team/round
        App.saveTeamData();
    }
}

