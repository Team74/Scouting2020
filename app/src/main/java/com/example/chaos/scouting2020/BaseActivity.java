package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    // These are used for sorting report tables
    protected boolean ReportSortAsc = false;
    protected int ReportSortColumn = 0;
    protected String Activity = "";
    public interface ReportUpdateCommand
    {
        public void update();
    }

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

    // add heading strings to specified table.  used for reports.
    protected void AddHeaderStringsAsRowToReportTable(int tableId, String[] headerStrings, final ReportUpdateCommand reportUpdateCommand) {
        // get handle to table
        TableLayout table = (TableLayout)findViewById(tableId);
        table.removeAllViews();

        // create a common layout param group for all of our rows
        TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, headerStrings.length);
        TableRow.LayoutParams lpItem = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1);

        // create a header row
        TableRow hdr = new TableRow(this);
        hdr.setLayoutParams(lpRow);
        hdr.setBackgroundResource(R.color.colorWhiteBackground);

        // add each heading string to our header row
        for(int i = 0; i < headerStrings.length; i++) {
            final int headingIndex = i; // needs to be final for onClick
            String heading = headerStrings[headingIndex];
            TextView hdrView = new TextView(this);
            hdrView.setLayoutParams(lpItem);
            hdrView.setText(heading);
            hdrView.setTextSize(0,10);
            // make the column heading we are sorting on italic
            hdrView.setTypeface(null, (ReportSortColumn == headingIndex) ? Typeface.BOLD_ITALIC : Typeface.BOLD);
            hdrView.setPadding(2, 0, 2, 0);
            hdrView.setGravity(Gravity.CENTER);
            // set an onclick handler for each header so we can update the sort when clicked
            hdrView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(ReportSortColumn == headingIndex) {
                        // reverse the current sort order
                        ReportSortAsc = !ReportSortAsc;
                    } else {
                        // set sort column to new index
                        ReportSortColumn = headingIndex;
                        ReportSortAsc = false;
                    }
                    // redisplay the entire table
                    reportUpdateCommand.update();
                }
            });
            hdr.addView(hdrView);
        }
        // add the data row to the table
        table.addView(hdr);
    }

    // add data strings to specified table.  used for reports.
    protected void AddDataStringsAsRowToReportTable(int tableId, String[] dataStrings) {
        // get handle to table
        TableLayout table = (TableLayout)findViewById(tableId);
        int rowNumber = table.getChildCount();

        // create a common layout param group for all of our rows and items
        TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, dataStrings.length);
        TableRow.LayoutParams lpItem = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1);

        // create a new row to hold our data values
        TableRow row = new TableRow(this);
        row.setLayoutParams(lpRow);

        // alternate the color of each row
        if((rowNumber % 2) == 0) {
            row.setBackgroundResource(R.color.colorBlueBackground);
        } else {
            row.setBackgroundResource(R.color.colorRedBackground);
        }

        // add each data string as an item to our row
        for(String dataString : dataStrings) {
            TextView dataView = new TextView(this);
            dataView.setLayoutParams(lpItem);
            dataView.setText(dataString);
            dataView.setPadding(2, 0, 2, 0);
            dataView.setGravity(Gravity.CENTER);
            row.addView(dataView);
        }

        // add the data row to the end of the table
        table.addView(row);
    }

    //set a spinners value
    protected void SetSpinnerByValue(int spinnerId, String value) {
        Spinner spinner = (Spinner) findViewById(spinnerId);
        for (int i=0; i<spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}