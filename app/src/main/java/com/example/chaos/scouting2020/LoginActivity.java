package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    protected int TeamNumber = -1;
    protected int RoundNumber = -1;
    protected String ScouterName = "";
    protected String TeamColor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // start a new team round data record
        ((ScoutingApplication) this.getApplication()).newTeamRoundData();
        ((ScoutingApplication) this.getApplication()).newScouterName();

        final Spinner loginScouterSpinner = (Spinner) this.findViewById(R.id.loginScouterSpinner);
        loginScouterSpinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent){
            }
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextSize(70);
                ScouterName = String.valueOf(loginScouterSpinner.getItemAtPosition(position));
            }
        });
        final Spinner loginTeamNumSpinner = (Spinner) this.findViewById(R.id.loginTeamNumberSpinner);
        loginTeamNumSpinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent){
            }
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextSize(70);
                TeamNumber = Integer.parseInt(String.valueOf(loginTeamNumSpinner.getItemAtPosition(position)));
            }
        });
        ((ScoutingApplication) this.getApplication()).AddScouterName("Gareau");
    }

    public void redRadioButtonPressed(View redRadioButton) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.loginConstraintLayout);
        layout.setBackgroundColor(Color.argb(255, 254, 59, 34));
        TeamColor = "Red";
    }

    public void blueRadioButtonPressed(View blueRadioButton) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.loginConstraintLayout);
        layout.setBackgroundColor(Color.argb(255, 119, 158, 203));
        TeamColor = "Blue";
    }

    public void startButtonPressed(View startButton) {
        // TeamNumber, ScouterName, and TeamColor should have been set on change above
        EditText QRNumberEditText = (EditText) findViewById(R.id.loginQRNumberEditText);
        try {
            RoundNumber = Integer.parseInt(QRNumberEditText.getText().toString());
        } catch (Exception e) {
            // some sort of error converting RoundNumber to int
            e.printStackTrace();
            Toast.makeText(this, "RoundNumber should be positive integer", Toast.LENGTH_SHORT).show();
            RoundNumber = -1;
        }

        // don't allow switching away if any invalid values
        if(    (TeamNumber>0)
            && (RoundNumber>0)
            && ((TeamColor == "Blue") || (TeamColor == "Red"))
            && (!ScouterName.isEmpty())) {

            // we have valid login fields, switch to the first activity: auton
            Intent intent = new Intent(this, AutonActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "All login fields not set", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onPause() {
        super.onPause();
        ((ScoutingApplication) this.getApplication()).AddScouterName("Gareau");
        // load any previously collected data for current team/round
        ((ScoutingApplication) this.getApplication()).setTeamNumber(TeamNumber);
        ((ScoutingApplication) this.getApplication()).setRoundNumber(RoundNumber);
        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();
        // update data for current team round
        ((ScoutingApplication) this.getApplication()).setTeamNumber(TeamNumber);
        ((ScoutingApplication) this.getApplication()).setRoundNumber(RoundNumber);
        ((ScoutingApplication) this.getApplication()).setScouter(ScouterName);
        ((ScoutingApplication) this.getApplication()).setTeamColor(TeamColor);
        // save any updated data for current team/round
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }
}
