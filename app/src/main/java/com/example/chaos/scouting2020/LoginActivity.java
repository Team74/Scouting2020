package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends BaseActivity {

    protected int TeamNumber = -1;
    protected int RoundNumber = -1;
    protected String ScouterName = "";
    protected String TeamColor = "";

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // start a new team round data record
        ((ScoutingApplication) this.getApplication()).newTeamRoundData();

        // TBD: these are example calls that should be removed in final app
        ((ScoutingApplication) this.getApplication()).newScouterName();
        //((ScoutingApplication) this.getApplication()).AddScouterName("Gareau");
        ((ScoutingApplication) this.getApplication()).AddAllScouterNames();

        // use DB to populate scouter name selection spinner
        List<String> scouters = ((ScoutingApplication) this.getApplication()).GetAllScouterNamesAsList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, scouters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.loginScouterSpinner);
        sItems.setAdapter(adapter);

        // set the text size of the scouter name selection spinner
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

        // TBD: use DB to populate scouter name selection spinner

        // set the text size of the team number selection spinner
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
    }

    public void redRadioButtonPressed(View redRadioButton) {
        TeamColor = "Red";
        SetLayoutBackgroundColor(R.id.loginConstraintLayout, TeamColor);
    }

    public void blueRadioButtonPressed(View blueRadioButton) {
        TeamColor = "Blue";
        SetLayoutBackgroundColor(R.id.loginConstraintLayout, TeamColor);
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

            // all login fields are valid, switch to the first activity: auton
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
