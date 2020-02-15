package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class PitScoutingMainActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected String PitScouterName = "";
    protected int TeamNumber = -1;
    protected int PitScoutingMainRobotWeight = -1;
    protected String RobotDriveBaseType = "";
    protected String PitScoutingMainNotes = "";

    // this should be called onPause and when ever the team number has changed
    protected void SavePitScoutingFields() {

        // get all fields from screen EXCEPT team number
        Spinner spinnerScouter = (Spinner) findViewById(R.id.pitScoutingMainScouterSpinner);
        PitScouterName = spinnerScouter.getSelectedItem().toString();

        Spinner spinnerRobotDriveBaseType = (Spinner) findViewById(R.id.pitScoutingMainDriveBaseSpinner);
        RobotDriveBaseType = spinnerRobotDriveBaseType.getSelectedItem().toString();

        EditText Notes = (EditText) findViewById(R.id.pitScoutingMainNotesEditText);
        PitScoutingMainNotes = Notes.getText().toString();

        EditText editTextWeight = (EditText) findViewById(R.id.pitScoutingMainWeightEditText);
        try {
            PitScoutingMainRobotWeight = Integer.parseInt(editTextWeight.getText().toString());
        } catch (Exception e) {
            // some sort of error converting weight to int
            e.printStackTrace();
            PitScoutingMainRobotWeight = -1;
        }

        // update the current DB record
        // note that if team number spinner just changed, we are using the
        // previous team number value here
        App.setPitTeamNumber(TeamNumber);
        App.setPitScouter(PitScouterName);
        App.setRobotDriveBaseType(RobotDriveBaseType);
        App.setRobotWeight(PitScoutingMainRobotWeight);
        App.setPitScoutingNotes(PitScoutingMainNotes);

        // save any updated data for previous team
        App.saveTeamData();
    }

    // this should be called onCreate and when ever the team number has changed
    protected void UpdatePitScoutingFields() {
        // get the new team number from the spinner
        Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingMainTeamNumberSpinner);
        try {
            TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());

            // since the TeamNumber changed, we need to refresh the
            // team data record...
            App.setPitTeamNumber(TeamNumber);
            App.refreshTeamData();

            // update all the fields on the screen here
            PitScouterName = App.getPitScouter();
            RobotDriveBaseType = App.getRobotDriveBaseType();
            PitScoutingMainRobotWeight = App.getRobotWeight();
            PitScoutingMainNotes = App.getPitScoutingNotes();

            // Update scouter spinner
            SetSpinnerByValue(R.id.pitScoutingMainScouterSpinner, PitScouterName);

            // Update drive base type spinner
            SetSpinnerByValue(R.id.pitScoutingMainDriveBaseSpinner, RobotDriveBaseType);

            // Update weight textView
            TextView WeightEditText = (EditText) findViewById(R.id.pitScoutingMainWeightEditText);
            WeightEditText.setText("" + PitScoutingMainRobotWeight);

            // Update notes textView
            TextView NotesEditText = (EditText) findViewById(R.id.pitScoutingMainNotesEditText);
            NotesEditText.setText(PitScoutingMainNotes);
        } catch (Exception e) {
            // some sort of error
            // this is usually caused by not having any team data or scouters in DB
            // which shouldn't happen at a real event.
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_main);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // make sure db is inited
        App.refreshTeamData();

        // use DB to populate team number selection spinner
        List<String> teamNumbers = App.getAllTeamNumbersAsList();
        AddStringsToSpinner(R.id.pitScoutingMainTeamNumberSpinner, teamNumbers, 32);

        TeamNumber = App.getPitTeamNumber();
        if (TeamNumber > 0) {
            SetSpinnerByValue(R.id.pitScoutingMainTeamNumberSpinner, "" + TeamNumber);
        }

        // use DB to populate scouter name selection spinner
        List<String> scouters = App.getAllScouterNamesAsList();
        AddStringsToSpinner(R.id.pitScoutingMainScouterSpinner, scouters, 32);

        // drive base types are not in DB, they are hard coded
        List<String> driveTypes = Arrays.asList(App.getSampleDriveBases());
        AddStringsToSpinner(R.id.pitScoutingMainDriveBaseSpinner, driveTypes, 32);

        // create a handler to update the page when ever team number changes
        Spinner spinnerItems = (Spinner) findViewById(R.id.pitScoutingMainTeamNumberSpinner);
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent) { }
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                try {
                    // set text size
                    ((TextView) parentView.getChildAt(0)).setTextSize(32);
                    // save the record for the current team number
                    SavePitScoutingFields();
                    // ... and update display with specific items for this activity for new team number
                    UpdatePitScoutingFields();
                } catch (Exception e) {
                    // some sort of error
                    e.printStackTrace();
                }
            }
        });

        // Update fields for first time
        UpdatePitScoutingFields();
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

    protected void onPause() {
        super.onPause();

        // exiting activity, save the record under the current team number
        SavePitScoutingFields();
    }
}
