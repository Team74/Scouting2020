package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    // set layout background color
    protected void SetLayoutBackgroundColor(int layoutId, String teamColor) {
        if(layoutId != 0) {
            ConstraintLayout layout = (ConstraintLayout) findViewById(layoutId);
            if (teamColor.equals("Blue")) {
                // layout.setBackgroundColor(Color.argb(64, 53, 121, 220));
                layout.setBackgroundColor(getResources().getColor(R.color.colorBlueBackground));
            } else if (teamColor.equals("Red")) {
                // layout.setBackgroundColor(Color.argb(64, 255, 50, 30));
                layout.setBackgroundColor(getResources().getColor(R.color.colorRedBackground));
            }
        }
    }

    // this sets team #, round #, scouter name, and layout background color
    protected void UpdateCommonLayoutItems(int teamNumberViewId, int roundNumberViewId, int scouterNameViewId, int layoutId) {
        if(teamNumberViewId != 0) {
            int teamNumber = ((ScoutingApplication) this.getApplication()).getTeamNumber();
            TextView TNumber = (TextView) findViewById(teamNumberViewId);
            TNumber.setText("Team: " + Integer.toString(teamNumber));
        }
        if(roundNumberViewId != 0) {
            int roundNumber = ((ScoutingApplication) this.getApplication()).getRoundNumber();
            TextView QRNumber = (TextView) findViewById(roundNumberViewId);
            QRNumber.setText("Round: " + Integer.toString(roundNumber));
        }
        if(scouterNameViewId != 0) {
            String scouterName = ((ScoutingApplication) this.getApplication()).getScouterName();
            TextView ScouterName = (TextView) findViewById(scouterNameViewId);
            ScouterName.setText("Scouter: " + scouterName);
        }
        String teamColor = ((ScoutingApplication) this.getApplication()).getTeamColor();
        SetLayoutBackgroundColor(layoutId, teamColor);
    }

    // add strings to spinner and set font size
    protected void AddStringsToSpinner(int spinnerViewId, List<String> stringList, final int fontSize) {
        if (spinnerViewId != 0) {
            ArrayAdapter<String> adapterScouter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, stringList);
            adapterScouter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            final Spinner spinnerItems = (Spinner) findViewById(spinnerViewId);
            spinnerItems.setAdapter(adapterScouter);

            // set the text size of the team number selection spinner
            spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onNothingSelected(AdapterView<?> parent) { }
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    ((TextView) parentView.getChildAt(0)).setTextSize(fontSize);
                }
            });
        }
    }
}