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

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // set
        ((ScoutingApplication) this.getApplication()).setSomeVariable("foo");

        // get
        String s = ((ScoutingApplication) this.getApplication()).getSomeVariable();

        StartUpDb();
        //AddAllRounds();
        //AddAllScouters();


        final Spinner loginScouterSpinner = (Spinner) this.findViewById(R.id.loginScouterSpinner);
        loginScouterSpinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent){
            }
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextSize(70);
                Scout = String.valueOf(loginScouterSpinner.getItemAtPosition(position));
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

    public void autonButtonPressed(View autonButton) {
//        StartUp(1,1);
        EditText QRNumber;
        QRNumber = (EditText)findViewById(R.id.loginQRNumberEditText);
        Log.d("QRNumber", String.valueOf(QRNumber));
        Log.d("QRNumber.getText()-----", QRNumber.getText().toString());
        //int QR = Integer.parseInt(String.valueOf(QRNumber.getText()));
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
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
}
