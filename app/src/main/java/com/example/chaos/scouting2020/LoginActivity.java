package com.example.chaos.scouting2020;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Spinner loginTeamNumSpinner = (Spinner) this.findViewById(R.id.teamNumSelector);
        loginTeamNumSpinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent){
            }
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextSize(70);
            }
        });
        Spinner loginScouterSpinner = (Spinner) this.findViewById(R.id.loginScouterSpinner);
        loginScouterSpinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> parent){
            }
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextSize(70);
            }
        });
    }

    public void autonButtonPressed(View autonButton) {
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
    }
    public void redRadioButtonPressed(View redRadioButton) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.loginConstraintLayout);
        layout.setBackgroundColor(Color.argb(255, 237, 28, 36));
    }
    public void blueRadioButtonPressed(View blueRadioButton) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.loginConstraintLayout);
        layout.setBackgroundColor(Color.argb(255, 0, 101, 179));
    }
}
