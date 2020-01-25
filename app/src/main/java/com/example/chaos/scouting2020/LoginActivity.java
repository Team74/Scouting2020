package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    protected int TNumber = -1;
    protected int QRNumber = -1;
    protected String ScouterName = "";
    protected String TColor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // make sure db connection active
        ((ScoutingApplication) this.getApplication()).StartUpDb();

        // refresh teamround data
        ((ScoutingApplication) this.getApplication()).refreshTeamRoundData();

        // update local member variables from db record
        //TNumber = ((ScoutingApplication) this.getApplication()).getTeamNumber();
        //QRNumber = ((ScoutingApplication) this.getApplication()).getRoundNumber();
        //ScouterName = ((ScoutingApplication) this.getApplication()).getScouter();
        //TColor = ((ScoutingApplication) this.getApplication()).getTeamColor();

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
                Log.d("Spinner Id", String.valueOf(loginTeamNumSpinner.getItemAtPosition(position)));
                TNumber = Integer.parseInt(String.valueOf(loginTeamNumSpinner.getItemAtPosition(position)));
            }
        });
    }

    public void startButtonPressed(View startButton) {
        // TNumber, ScouterName, and TColor should have been set on change above
        EditText QRNumberEditText = (EditText) findViewById(R.id.loginQRNumberEditText);
        try {
            QRNumber = Integer.parseInt(QRNumberEditText.getText().toString());
        } catch (Exception e) {
            // some sort of error converting QRNumber to int
            e.printStackTrace();
            Toast.makeText(this, "QRNumber should be positive integer", Toast.LENGTH_SHORT).show();
            QRNumber = -1;
        }

        // don't allow switching away if any invalid values
        if(    (TNumber>0)
            && (QRNumber>0)
            && ((TColor == "Blue") || (TColor == "Red"))
            && (!ScouterName.isEmpty())) {

            // we have valid login fields, switch to the first activity: auton
            Intent intent = new Intent(this, AutonActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "All login fields not set", Toast.LENGTH_SHORT).show();
        }
    }
    public void redRadioButtonPressed(View redRadioButton) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.loginConstraintLayout);
        layout.setBackgroundColor(Color.argb(255, 254, 59, 34));
        TColor = "Red";
    }
    public void blueRadioButtonPressed(View blueRadioButton) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.loginConstraintLayout);
        layout.setBackgroundColor(Color.argb(255, 119, 158, 203));
        TColor = "Blue";
    }

    protected void onPause() {
        super.onPause();
        // Update our team round data record and save it
        ((ScoutingApplication) this.getApplication()).setTeamNumber(TNumber);
        ((ScoutingApplication) this.getApplication()).setRoundNumber(QRNumber);
        ((ScoutingApplication) this.getApplication()).setScouter(ScouterName);
        ((ScoutingApplication) this.getApplication()).setTeamColor(TColor);
        ((ScoutingApplication) this.getApplication()).saveTeamRoundData();
    }
}
