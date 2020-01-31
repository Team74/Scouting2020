package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OpinionActivity extends BaseActivity {

    // Android can suspend, terminate, destroy *any* activity at *any*
    // time for a lot reasons (triggering the OnPause), and a subsequent
    // OnCreate when the activity is restarted.  Member variables like
    // the ones below will be destroyed and lose any previously saved
    // values.  Thus, you need to make sure you reload them every time
    // during your OnCreate from the DB.
    protected int opinionSomeValue = 0;
    protected int OpinionQRNumber = 0;
    protected int OpinionTNumber = 0;
    protected String OpinionScouterName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);

        // load any previously collected data for current team/round
        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();

        OpinionQRNumber = ((ScoutingApplication) this.getApplication()).getRoundNumber();
        OpinionTNumber = ((ScoutingApplication) this.getApplication()).getTeamNumber();
        OpinionScouterName = ((ScoutingApplication) this.getApplication()).getScouterName();


        TextView QrNumber = (TextView) findViewById(R.id.opinionQRNumberTextView);
        QrNumber.setText("Round " + Integer.toString(OpinionQRNumber));

        TextView TNumber = (TextView) findViewById(R.id.opinionTNumberTextView);
        TNumber.setText("Team " + Integer.toString(OpinionTNumber));

        TextView ScouterName = (TextView) findViewById(R.id.opinionScouterTextView);
        ScouterName.setText("Scouter: " + OpinionScouterName);

        if (((ScoutingApplication) this.getApplication()).getTeamColor().equals("Blue")) {
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.opinionConstraintLayout);
            layout.setBackgroundColor(Color.argb(64,53, 121, 220));
        }

        if (((ScoutingApplication) this.getApplication()).getTeamColor().equals("Red")) {
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.opinionConstraintLayout);
            layout.setBackgroundColor(Color.argb(64, 231, 20, 0 ));
        }
    }

    public void autonButtonPressed(View autonButton) {
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
    }
    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);
    }
    public void endgameButtonPressed(View engameButton) {
        Intent intent = new Intent(this, EndgameActivity.class);
        startActivity(intent);
    }
    public void exitButtonPressed(View engameButton) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    protected void onPause() {
        super.onPause();
        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }

}
