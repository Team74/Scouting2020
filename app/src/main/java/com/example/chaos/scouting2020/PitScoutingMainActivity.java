package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.List;

public class PitScoutingMainActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected String PitScouterName = "";
    protected int TeamNumber = -1;
    protected int PitScoutingMainRobotWeight = -1;
    protected String RobotDriveBaseType = "";
    protected String PitScoutingMainNotes = "";

    protected void UpdatePitScoutingFields() {

        EditText Notes = (EditText) findViewById(R.id.pitScoutingMainNotesEditText);
        PitScoutingMainNotes = Notes.getText().toString();

        EditText QRNumberEditText = (EditText) findViewById(R.id.pitScoutingMainWeightEditText);
        try {
            PitScoutingMainRobotWeight = Integer.parseInt(QRNumberEditText.getText().toString());
        } catch (Exception e) {
            // some sort of error converting RoundNumber to int
            e.printStackTrace();
            PitScoutingMainRobotWeight = -1;
        }

        Spinner spinnerScouter = (Spinner) findViewById(R.id.pitScoutingMainScouterSpinner);
        PitScouterName = spinnerScouter.getSelectedItem().toString();

        Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingMainTeamNumberSpinner);
        TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());

        Spinner spinnerRobotDriveBaseType = (Spinner) findViewById(R.id.pitScoutingMainDriveBaseSpinner);
        RobotDriveBaseType = spinnerRobotDriveBaseType.getSelectedItem().toString();

        App.setRobotDriveBaseType(RobotDriveBaseType);
        App.setRobotWeight(PitScoutingMainRobotWeight);
        App.setPitScoutingNotes(PitScoutingMainNotes);
        App.setPitScouter(PitScouterName);

        // save any updated data for current team/round
        App.saveTeamData();

        // since the TeamNumber changed, we need to refresh the
        // team data record...
        App.setTeamNumber(TeamNumber);
        App.refreshTeamData();

        // update all the fields on the screen here
        RobotDriveBaseType = App.getRobotDriveBaseType();
        PitScoutingMainRobotWeight = App.getRobotWeight();
        PitScoutingMainNotes = App.getPitScoutingNotes();
        PitScouterName = App.getPitScouter();

        // Update drive base type spinner
        SetSpinnerByValue(R.id.pitScoutingMainDriveBaseSpinner, RobotDriveBaseType);

        // Update scouter spinner
        // Update drive base type spinner
        SetSpinnerByValue(R.id.pitScoutingMainScouterSpinner, PitScouterName);

        // Update weight textView
        TextView WeightEditText = (EditText) findViewById(R.id.pitScoutingMainWeightEditText);
        WeightEditText.setText("" + PitScoutingMainRobotWeight);

        // Update notes textView
        TextView NotesEditText = (EditText) findViewById(R.id.pitScoutingMainNotesEditText);
        NotesEditText.setText(""+PitScoutingMainNotes);
    }

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

        // create a handler to update the page when ever team number changes
        Spinner spinnerItems = (Spinner) findViewById(R.id.pitScoutingMainTeamNumberSpinner);
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent) { };
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextSize(36);
                // load any previously collected data for current team
                try {
                    Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingMainTeamNumberSpinner);
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

        EditText Notes = (EditText) findViewById(R.id.pitScoutingMainNotesEditText);
        PitScoutingMainNotes = Notes.getText().toString();

        EditText QRNumberEditText = (EditText) findViewById(R.id.pitScoutingMainWeightEditText);
        try {
            PitScoutingMainRobotWeight = Integer.parseInt(QRNumberEditText.getText().toString());
        } catch (Exception e) {
            // some sort of error converting RoundNumber to int
            e.printStackTrace();
            PitScoutingMainRobotWeight = -1;
        }

        Spinner spinnerScouter = (Spinner) findViewById(R.id.pitScoutingMainScouterSpinner);
        PitScouterName = spinnerScouter.getSelectedItem().toString();

        Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingMainTeamNumberSpinner);
        TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());

        Spinner spinnerRobotDriveBaseType = (Spinner) findViewById(R.id.pitScoutingMainDriveBaseSpinner);
        RobotDriveBaseType = spinnerRobotDriveBaseType.getSelectedItem().toString();

        App.setRobotDriveBaseType(RobotDriveBaseType);
        App.setRobotWeight(PitScoutingMainRobotWeight);
        App.setPitScoutingNotes(PitScoutingMainNotes);
        App.setPitScouter(PitScouterName);
        App.setTeamNumber(TeamNumber);

        // save any updated data for current team/round
        App.saveTeamData();
    }
}
