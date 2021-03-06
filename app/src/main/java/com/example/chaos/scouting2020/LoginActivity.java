package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected int TeamNumber = -1;
    protected int RoundNumber = -1;
    protected String ScouterName = "";
    protected String TeamColor = "";
    protected String TabletNumber = "";

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Login");

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // start a new team round data record
        App.newTeamRoundData();

        // use DB to populate scouter name selection spinner
        List<String> scouters = App.getAllScouterNamesAsList();
        AddStringsToSpinner(R.id.loginScouterSpinner, scouters, 64);

        // use DB to populate team number selection spinner
        List<String> teamNumbers = App.getAllTeamNumbersAsList();
        AddStringsToSpinner(R.id.loginTeamNumberSpinner, teamNumbers, 64);

        EditText editText = (EditText)findViewById(R.id.loginRoundNumberEditText);
        int LastRoundNumber = App.getLastRoundNumber();
        editText.setText(Integer.toString(LastRoundNumber), TextView.BufferType.EDITABLE);

        // set color based on tablet color
        TabletNumber = App.getTabletColor();
        if (TabletNumber.contains("Red")) {
            TeamColor = "Red";
            RadioButton Red = (RadioButton) findViewById(R.id.loginRedRadioButton);
            Red.setChecked(true);
        }
        if (TabletNumber.contains("Blue")) {
            TeamColor = "Blue";
            RadioButton Blue = (RadioButton) findViewById(R.id.loginBlueRadioButton);
            Blue.setChecked(true);
        }
        SetLayoutBackgroundColor(R.id.loginConstraintLayout, TeamColor);
    }

    public void menuButtonPressed(View menuButton) {
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
        try {
            TeamNumber = Integer.parseInt(spinnerTeamNumber.getSelectedItem().toString());
        } catch (Exception e) {
            // some sort of error converting TeamNumber to int
            e.printStackTrace();
            TeamNumber = -1;
        }

        EditText RoundNumberEditText = (EditText) findViewById(R.id.loginRoundNumberEditText);
        try {
            RoundNumber = Integer.parseInt(RoundNumberEditText.getText().toString());
        } catch (Exception e) {
            // some sort of error converting RoundNumber to int
            e.printStackTrace();
            RoundNumber = -1;
        }

        // don't allow switching away if any invalid values
        if (    (TeamNumber>0)
            && ((RoundNumber>0) && (RoundNumber<100))
            && ((TeamColor.equals("Blue")) || (TeamColor.equals("Red")))
            && (!ScouterName.isEmpty())) {

            int LastRoundNumber = RoundNumber + 1;
            App.setLastRoundNumber(LastRoundNumber);


            // all login fields are valid, switch to the first activity: auton
            Intent intent = new Intent(this, AutonActivity.class);
            startActivity(intent);

        } else {
            if ((RoundNumber<1) || (RoundNumber>99)) {
                Toast.makeText(this, "RoundNumber should be positive integer less than 100", Toast.LENGTH_SHORT).show();
            } else if ((!TeamColor.equals("Blue")) && (!TeamColor.equals("Red"))) {
                Toast.makeText(this, "TeamColor should be red or blue", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "All login fields not set", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onPause() {
        super.onPause();

        // only save team round on exit if valid
        if (    (TeamNumber>0)
                && ((RoundNumber>0) && (RoundNumber<100))
                && ((TeamColor.equals("Blue")) || (TeamColor.equals("Red")))
                && (!ScouterName.isEmpty())) {
            // load any previously collected data for current team/round
            App.setTeamNumber(TeamNumber);
            App.setRoundNumber(RoundNumber);
            App.refreshTeamRoundData();
            // update data for current team round
            App.setTeamNumber(TeamNumber);
            App.setRoundNumber(RoundNumber);
            App.setScouter(ScouterName);
            App.setTeamColor(TeamColor);
            // save any updated data for current team/round
            App.saveTeamRoundData();
        }
    }
}
