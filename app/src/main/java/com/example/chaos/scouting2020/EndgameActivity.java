package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class EndgameActivity extends BaseActivity {

    // Android can suspend, terminate, destroy *any* activity at *any*
    // time for a lot reasons (triggering the OnPause), and a subsequent
    // OnCreate when the activity is restarted.  Member variables like
    // the ones below will be destroyed and lose any previously saved
    // values.  Thus, you need to make sure you reload them every time
    // during your OnCreate from the DB.
    protected String EndgameClimb = "";
    protected boolean EndgameBrokeDown = false;
    protected int EndgameFinalStage = 0;
    protected String EndgameNotes = "";
    protected int EndgameQRNumber = 0;
    protected int EndgameTNumber = 0;
    protected String EndgameScouterName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        // load any previously collected data for current team/round
        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();

        EndgameClimb = ((ScoutingApplication)this.getApplication()).getClimb();
        EndgameBrokeDown = ((ScoutingApplication) this.getApplication()).getBrokeDown();
        EndgameFinalStage = ((ScoutingApplication) this.getApplication()).getFinalStage();
        EndgameNotes = ((ScoutingApplication)this.getApplication()).getNotes();
        EndgameQRNumber = ((ScoutingApplication)this.getApplication()).getRoundNumber();
        EndgameTNumber = ((ScoutingApplication)this.getApplication()).getTeamNumber();
        EndgameScouterName = ((ScoutingApplication)this.getApplication()).getScouterName();


        RadioButton Climbed = (RadioButton) findViewById(R.id.endgameClimbRadioButton);
        Climbed.setChecked(EndgameClimb.equals("RobotClimbed") ? true : false);
        Climbed = (RadioButton) findViewById(R.id.endgameDidntRadioButton);
        Climbed.setChecked(EndgameClimb.equals("RobotDidn'tClimb") ? true : false);
        Climbed = (RadioButton) findViewById(R.id.endgameFailedRadioButton);
        Climbed.setChecked(EndgameClimb.equals("RobotFailedToClimb") ? true : false);

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

        TextView QRNumber = (TextView) findViewById(R.id.endgameQRNumberTextView);
        QRNumber.setText("Round " + Integer.toString(EndgameQRNumber));

        TextView TNumber = (TextView) findViewById(R.id.endgameTNumberTextView);
        TNumber.setText("Team " + Integer.toString(EndgameTNumber));

        TextView ScouterName = (TextView) findViewById(R.id.endgameScouterTextView);
        ScouterName.setText("Scouter: " + EndgameScouterName);

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
        EndgameClimb = "RobotClimbed";
    }

    public void didntClimbButtonPressed(View didntClimbButton) {
        EndgameClimb = "RobotDidn'tClimb";
    }

    public void failedClimbButtonPressed(View failedClimbButton) {
        EndgameClimb = "RobotFailedToClimb";
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
        ((ScoutingApplication) this.getApplication()).setClimb(EndgameClimb);
        ((ScoutingApplication) this.getApplication()).setBrokeDown(EndgameBrokeDown);
        ((ScoutingApplication) this.getApplication()).setFinalStage(EndgameFinalStage);
        ((ScoutingApplication) this.getApplication()).setNotes(EndgameNotes);
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }
}
