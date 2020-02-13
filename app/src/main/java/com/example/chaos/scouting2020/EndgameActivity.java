package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class EndgameActivity extends BaseActivity {

    protected ScoutingApplication App;

    // Android can suspend, terminate, destroy *any* activity at *any*
    // time for a lot reasons (triggering the OnPause), and a subsequent
    // OnCreate when the activity is restarted.  Member variables like
    // the ones below will be destroyed and lose any previously saved
    // values.  Thus, you need to make sure you reload them every time
    // during your OnCreate from the DB.
    protected int EndgameClimb = 0;
    protected boolean EndgameBrokeDown = false;
    protected int EndgameFinalStage = 0;
    protected String EndgameNotes = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // load any previously collected data for current team/round
        App.refreshTeamRoundData();

        // update display with common items
        UpdateCommonLayoutItems(R.id.endgameTNumberTextView, R.id.endgameQRNumberTextView, R.id.endgameScouterTextView, R.id.endgameConstraintLayout);

        // update display with specific items for this activity
        EndgameClimb = App.getClimb();
        EndgameBrokeDown = App.getBrokeDown();
        EndgameFinalStage = App.getFinalStage();
        EndgameNotes = App.getNotes();

        RadioButton Climbed = (RadioButton) findViewById(R.id.endgameClimbRadioButton);
        Climbed.setChecked(EndgameClimb == 1);  // 1=climb successful
        Climbed = (RadioButton) findViewById(R.id.endgameDidntRadioButton);
        Climbed.setChecked(EndgameClimb == 2);  // 2=climb not attempted
        Climbed = (RadioButton) findViewById(R.id.endgameFailedRadioButton);
        Climbed.setChecked(EndgameClimb == 3);  // 3=climb failed

        RadioButton BrokeDown = (RadioButton) findViewById(R.id.endgameBrokeDownRadioButton);
        BrokeDown.setChecked(EndgameBrokeDown == true);
        BrokeDown = (RadioButton) findViewById(R.id.endgameDidntBreakDownRadioButton);
        BrokeDown.setChecked(EndgameBrokeDown == false);

        RadioButton FinalStage = (RadioButton) findViewById(R.id.endgameStage1RadioButton);
        FinalStage.setChecked(EndgameFinalStage == 1);
        FinalStage = (RadioButton) findViewById(R.id.endgameStage2RadioButton);
        FinalStage.setChecked(EndgameFinalStage == 2);
        FinalStage = (RadioButton) findViewById(R.id.endgameStage3RadioButton);
        FinalStage.setChecked(EndgameFinalStage == 3);

        EditText Notes = (EditText) findViewById(R.id.endgameNotesEditText);
        Notes.setText(EndgameNotes);
    }

    public void opinionButtonPressed(View opinionButton) {
        Intent intent = new Intent(this, OpinionActivity.class);
        startActivity(intent);
    }

    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);
    }

    public void autonButtonPressed(View autonButton) {
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
    }

    //Climbing Buttons
    public void  didClimbButtonPressed(View didClimbButton) {
        EndgameClimb = 1; // 1=climb successful
    }

    public void didntClimbButtonPressed(View didntClimbButton) {
        EndgameClimb = 2; // 2=climb not attempted
    }

    public void failedClimbButtonPressed(View failedClimbButton) {
        EndgameClimb = 3; // 3=climb failed
    }
    //Robot Broke Buttons
    public void robotBrokeDownButtonPressed(View robotBrokeDownButton) {
        EndgameBrokeDown = true;
    }

    public void robotDidntBreakDownButtonPressed(View robotDidntBreakDownButton) {
        EndgameBrokeDown = false;
    }

    //Stage Buttons
    public void stageOnePressed(View stageOneButton) {
        EndgameFinalStage = 1;
    }

    public void stageTwoPressed(View stageTwoButton) {
        EndgameFinalStage = 2;
    }

    public void stageThreePressed(View stageThreeButton) {
        EndgameFinalStage = 3;
    }

    protected void onPause() {
        super.onPause();
        EditText Notes = (EditText) findViewById(R.id.endgameNotesEditText);
        EndgameNotes = Notes.getText().toString();
        // save any updated data for current team/round
        App.setClimb(EndgameClimb);
        App.setBrokeDown(EndgameBrokeDown);
        App.setFinalStage(EndgameFinalStage);
        App.setNotes(EndgameNotes);
        App.saveTeamRoundData();
    }
}
