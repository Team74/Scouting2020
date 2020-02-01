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
        ((ScoutingApplication) this.getApplication()).AddAllScouterNames();
        ((ScoutingApplication) this.getApplication()).AddAllTeamNumbers();

        // use DB to populate scouter name selection spinner
        List<String> scouters = ((ScoutingApplication) this.getApplication()).GetAllScouterNamesAsList();
        AddStringsToSpinner(R.id.loginScouterSpinner, scouters, 70);

        // use DB to populate team number selection spinner
        List<String> teamNumbers = ((ScoutingApplication) this.getApplication()).GetAllTeamNumbersAsList();
        AddStringsToSpinner(R.id.loginTeamNumberSpinner, teamNumbers, 70);
    }

    public void MenuButtonPressed(View MenuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
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
        Spinner spinnerScouter = (Spinner) findViewById(R.id.loginScouterSpinner);
        ScouterName = spinnerScouter.getSelectedItem().toString();

        Spinner spinnerTeamNumber = (Spinner) findViewById(R.id.loginTeamNumberSpinner);
        TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());

        EditText QRNumberEditText = (EditText) findViewById(R.id.loginQRNumberEditText);
        try {
            RoundNumber = Integer.parseInt(QRNumberEditText.getText().toString());
        } catch (Exception e) {
            // some sort of error converting RoundNumber to int
            e.printStackTrace();
            RoundNumber = -1;
        }

        if ((RoundNumber<1) && (RoundNumber>99)) {
            Toast.makeText(this, "RoundNumber should be positive integer less than 100", Toast.LENGTH_SHORT).show();
            RoundNumber = -1;
        }

        // don't allow switching away if any invalid values
        if(    (TeamNumber>0)
            && ((RoundNumber>0) && (RoundNumber<100))
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
