package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PitScoutingMapActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected String AutonNotes = "";


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
        int teamNumber = ((ScoutingApplication) this.getApplication()).getTeamNumber();
        TextView TNumber = (TextView) findViewById(R.id.pitScoutingMapTeamNumberTextView);
        TNumber.setText("Team: " + Integer.toString(teamNumber));

        String scouterName = ((ScoutingApplication) this.getApplication()).getPitScouter();
        TextView ScouterName = (TextView) findViewById(R.id.pitScoutingMapScouterTextView);
        ScouterName.setText("Scouter: " + scouterName);

        // update notes text view
        TextView NotesEditText = (EditText) findViewById(R.id.pitScoutingMapAutonNotesEditText);
        NotesEditText.setText(""+AutonNotes);


        //
    }

    public void pitScoutingMainButtonPressed(View pitScoutingMainButton) {
        Intent intent = new Intent(this, PitScoutingMainActivity.class);
        startActivity(intent);
    }

    protected void onPause() {
        super.onPause();

        EditText Notes = (EditText) findViewById(R.id.pitScoutingMapAutonNotesEditText);
        AutonNotes = Notes.getText().toString();

        App.setAutonNotes(AutonNotes);

        App.saveTeamData();

    }
}
