package com.example.chaos.scouting2020;

import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.TextViewCompat;
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

    // These are used for sorting and filtering report tables
    protected String ReportFilteredTeamNumberStringList = "0";
    protected boolean ReportSortAsc = true;
    protected int ReportSortColumn = 0;
    // This interface is used for updating the sorting of a report when a heading is clicked
    public interface ReportUpdateCommand
    {
        void update();
    }

    // set layout background color
    protected void SetLayoutBackgroundColor(int layoutId, String teamColor) {
        if (layoutId != 0) {
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
        if (teamNumberViewId != 0) {
            int teamNumber = ((ScoutingApplication) this.getApplication()).getTeamNumber();
            TextView TNumber = (TextView) findViewById(teamNumberViewId);
            TNumber.setText("Team: " + teamNumber);
        }
        if (roundNumberViewId != 0) {
            int roundNumber = ((ScoutingApplication) this.getApplication()).getRoundNumber();
            TextView RoundNumber = (TextView) findViewById(roundNumberViewId);
            RoundNumber.setText("Round: " + roundNumber);
        }
        if (scouterNameViewId != 0) {
            String scouterName = ((ScoutingApplication) this.getApplication()).getScouterName();
            TextView ScouterName = (TextView) findViewById(scouterNameViewId);
            ScouterName.setText("Scouter: " + scouterName);
        }
        if (layoutId != 0) {
            String teamColor = ((ScoutingApplication) this.getApplication()).getTeamColor();
            SetLayoutBackgroundColor(layoutId, teamColor);
        }
    }

    // add strings to spinner and set font size
    protected void AddStringsToSpinner(int spinnerViewId, List<String> stringList, final int fontSize) {
        if ((spinnerViewId != 0) && (stringList != null)) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, stringList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            final Spinner spinnerItems = (Spinner) findViewById(spinnerViewId);
            spinnerItems.setAdapter(adapter);

            // set the text size of the spinner's selected view
            spinnerItems.setSelection(0, true);
            View selectedItemView = spinnerItems.getSelectedView();
            if (selectedItemView != null) {
                ((TextView) selectedItemView).setTextSize(fontSize);
            }

            // when changed, set the text size of the team number selection spinner
            spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onNothingSelected(AdapterView<?> parent) { }
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // TBD: this can throw an exception on screen rotates, so we put
                    // the null check in, but that means in those cases the text size
                    // reverts back to the default.  Need to figure that out!
                    if (selectedItemView != null) {
                        // set the text size of the spinner's selected view
                        ((TextView) selectedItemView).setTextSize(fontSize);
                    }
                }
            });
        }
    }

    // add heading strings to specified table.  used for reports.
    protected void AddHeaderStringsAsRowToReportTable(int tableId, String[] headerStrings, final ReportUpdateCommand reportUpdateCommand, int textSize) {
        // get handle to display table (TableLayout)
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
            hdrView.setTextSize(textSize);
            // make the column heading we are sorting on italic
            hdrView.setTypeface(null, (ReportSortColumn == headingIndex) ? Typeface.BOLD_ITALIC : Typeface.BOLD);
            hdrView.setPadding(2, 0, 2, 0);
            hdrView.setGravity(Gravity.CENTER);
            // set an onclick handler for each header so we can update the sort when clicked
            hdrView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (ReportSortColumn == headingIndex) {
                        // reverse the current sort order
                        ReportSortAsc = !ReportSortAsc;
                    } else {
                        // set sort column to new index
                        ReportSortColumn = headingIndex;
                        // the first time a heading is clicked, default to descending
                        // except for index 0, which tends to be TeamNumber
                        ReportSortAsc = (headingIndex == 0);
                    }
                    // redisplay the entire table
                    reportUpdateCommand.update();
                }
            });
            hdr.addView(hdrView);
        }
        // add the header row to the table (it will be the first row)
        table.addView(hdr);
    }

    // add data strings to specified table.  used for reports.
    protected void AddDataStringsAsRowToReportTable(int tableId, String[] dataStrings) {
        // get handle to display table (TableLayout)
        TableLayout table = (TableLayout)findViewById(tableId);
        int rowNumber = table.getChildCount();

        // create a common layout param group for all of our rows and items
        TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, dataStrings.length);
        TableRow.LayoutParams lpItem = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1);

        // create a new row to hold our data values
        TableRow row = new TableRow(this);
        row.setLayoutParams(lpRow);

        // alternate the background color of each row
        if ((rowNumber % 2) == 0) {
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

    // set a spinners value
    protected void SetSpinnerByValue(int spinnerId, String value) {
        Spinner spinner = (Spinner) findViewById(spinnerId);
        for(int i=0; i<spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}