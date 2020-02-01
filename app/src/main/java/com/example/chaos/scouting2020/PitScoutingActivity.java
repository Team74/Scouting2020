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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        // load any previously collected data for current team/round
        //((ScoutingApplication) this.getApplication()).refreshTeamPitData();

        // update display with common items

        // update display with specific items for this activity

        // use DB to populate scouter name selection spinner
        List<String> scouters = ((ScoutingApplication) this.getApplication()).GetAllScouterNamesAsList();
        AddStringsToSpinner(R.id.pitScoutingScouterSpinner, scouters, 36);

        // use DB to populate team number selection spinner
        List<String> teamNumbers = ((ScoutingApplication) this.getApplication()).GetAllTeamNumbersAsList();
        AddStringsToSpinner(R.id.pitScoutingTeamNumberSpinner, teamNumbers, 36);

        List<String> driveTypes = Arrays.asList("Tank","Mecanum","Omni","Swerve","Other");
        AddStringsToSpinner(R.id.pitScoutingDriveBaseSpinner, driveTypes, 36);

    }

    public void MenuButtonPressed(View MenuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    protected void onPause() {
        super.onPause();

        Spinner spinnerScouter = (Spinner) findViewById(R.id.pitScoutingScouterSpinner);
        ScouterName = spinnerScouter.getSelectedItem().toString();

        Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.pitScoutingTeamNumberSpinner);
        TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());

        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamData();
    }
}

