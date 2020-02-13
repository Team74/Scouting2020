package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PitScoutingMapActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected String AutonNotes = "";
    protected boolean PitScoutingShootingLocation1 = false;
    protected boolean PitScoutingShootingLocation2 = false;
    protected boolean PitScoutingShootingLocation3 = false;
    protected boolean PitScoutingStartLocationLeft = false;
    protected boolean PitScoutingStartLocationCenter = false;
    protected boolean PitScoutingStartLocationRight = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_map);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // load any previously collected data for current team/round
        App.refreshTeamData();

        AutonNotes = App.getPitScoutingAutonNotes();

        // update display with common items
        int teamNumber = App.getPitTeamNumber();
        TextView TNumber = (TextView) findViewById(R.id.pitScoutingMapTeamNumberTextView);
        TNumber.setText("Team: " + teamNumber);

        String scouterName = App.getPitScouter();
        TextView ScouterName = (TextView) findViewById(R.id.pitScoutingMapScouterTextView);
        ScouterName.setText("Scouter: " + scouterName);

        PitScoutingShootingLocation1 = App.getShootingLocation1();
        PitScoutingShootingLocation2 = App.getShootingLocation2();
        PitScoutingShootingLocation3 = App.getShootingLocation3();
        PitScoutingStartLocationLeft = App.getStartLocationLeft();
        PitScoutingStartLocationCenter = App.getStartLocationCenter();
        PitScoutingStartLocationRight = App.getStartLocationRight();

        // update notes text view
        TextView NotesEditText = (EditText) findViewById(R.id.pitScoutingMapAutonNotesEditText);
        NotesEditText.setText(AutonNotes);

        // Update shooting position toggleButtons
        ToggleButton ShootingLocation1 = (ToggleButton) findViewById(R.id.pitScoutingMapSafeZoneShootingToggleButton);
        ShootingLocation1.setChecked(PitScoutingShootingLocation1);

        ToggleButton ShootingLocation2 = (ToggleButton) findViewById(R.id.pitScoutingAutonLineShootingToggleButton);
        ShootingLocation2.setChecked(PitScoutingShootingLocation2);

        ToggleButton ShootingLocation3 = (ToggleButton) findViewById(R.id.pitScoutingBehindGeneratorShootingToggleButton);
        ShootingLocation3.setChecked(PitScoutingShootingLocation3);

        // Update start position toggleButtons

        ToggleButton StartLocationLeft = (ToggleButton) findViewById(R.id.pitScoutingLeftStartToggleButton);
        StartLocationLeft.setChecked(PitScoutingStartLocationLeft);

        ToggleButton StartLocationCenter = (ToggleButton) findViewById(R.id.pitScoutingCenterStartToggleButton);
        StartLocationCenter.setChecked(PitScoutingStartLocationCenter);

        ToggleButton StartLocationRight = (ToggleButton) findViewById(R.id.pitScoutingRightStartToggleButton);
        StartLocationRight.setChecked(PitScoutingStartLocationRight);
    }

    public void pitScoutingMainButtonPressed(View pitScoutingMainButton) {
        Intent intent = new Intent(this, PitScoutingMainActivity.class);
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

        EditText Notes = (EditText) findViewById(R.id.pitScoutingMapAutonNotesEditText);
        AutonNotes = Notes.getText().toString();

        App.setAutonNotes(AutonNotes);
        App.setShootingLocation1(PitScoutingShootingLocation1);
        App.setShootingLocation2(PitScoutingShootingLocation2);
        App.setShootingLocation3(PitScoutingShootingLocation3);
        App.setStartLocationLeft(PitScoutingStartLocationLeft);
        App.setStartLocationCenter(PitScoutingStartLocationCenter);
        App.setStartLocationRight(PitScoutingStartLocationRight);

        App.saveTeamData();
    }
}
