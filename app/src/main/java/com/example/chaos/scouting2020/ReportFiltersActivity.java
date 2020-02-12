package com.example.chaos.scouting2020;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReportFiltersActivity extends AppCompatActivity {

    protected ScoutingApplication App;
    protected DaoTeamData daoTeamData = null;

    protected List<Integer> FilteredTeamNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_filters);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // intialize list to an empty list
        FilteredTeamNumberList = App.getFilteredTeamNumberList();
        if (FilteredTeamNumberList == null) {
            FilteredTeamNumberList = new ArrayList<Integer>();
        }

        // get data access objects (tables)
        if(daoTeamData == null){
            daoTeamData = App.getDaoTeamData();
        }

        // get all the data for all the teams
        EntityTeamData[] teamData = daoTeamData.getAllTeamData();

        // get handle to table
        TableLayout table = (TableLayout)findViewById(R.id.reportFiltersTable);

        // create a common layout param group for all of our rows and items
        TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 3);
        TableRow.LayoutParams lpItem = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1);

        // create a new row for headers
        TableRow hdr = new TableRow(this);
        hdr.setLayoutParams(lpRow);

        // alternate the color of each row
        hdr.setBackgroundResource(R.color.colorWhiteBackground);

        // add text view for team number header to row
        TextView hdrNameView = new TextView(this);
        hdrNameView.setLayoutParams(lpItem);
        hdrNameView.setText("Team Name");
        hdrNameView.setPadding(2, 0, 2, 0);
        hdrNameView.setGravity(Gravity.CENTER);
        hdr.addView(hdrNameView);

        // add text view for team number header to row
        TextView hdrNumberView = new TextView(this);
        hdrNumberView.setLayoutParams(lpItem);
        hdrNumberView.setText("Team Number");
        hdrNumberView.setPadding(2, 0, 2, 0);
        hdrNumberView.setGravity(Gravity.CENTER);
        hdr.addView(hdrNumberView);

        // add check box header to row
        TextView hdrBoxview = new TextView(this);
        hdrBoxview.setLayoutParams(lpItem);
        hdrBoxview.setText("Filter Out");
        hdrBoxview.setPadding(2, 0, 2, 0);
        hdrBoxview.setGravity(Gravity.CENTER);
        hdr.addView(hdrBoxview);

        // add the data row to the end of the table
        table.addView(hdr);

        // loop over team numbers
        for(EntityTeamData team : teamData) {

            final Integer TeamNumber = new Integer(team.TeamNumber);

            // create a new row to hold our data values
            TableRow row = new TableRow(this);
            row.setLayoutParams(lpRow);

            // alternate the color of each row
            int rowNumber = table.getChildCount();
            if ((rowNumber % 2) == 0) {
                row.setBackgroundResource(R.color.colorBlueBackground);
            } else {
                row.setBackgroundResource(R.color.colorRedBackground);
            }

            // add text view for team name to row
            TextView dataNameView = new TextView(this);
            dataNameView.setLayoutParams(lpItem);
            dataNameView.setText(team.TeamName);
            dataNameView.setPadding(2, 0, 2, 0);
            dataNameView.setGravity(Gravity.CENTER);
            row.addView(dataNameView);

            // add text view for team number to row
            TextView dataNumberView = new TextView(this);
            dataNumberView.setLayoutParams(lpItem);
            dataNumberView.setText(""+team.TeamNumber);
            dataNumberView.setPadding(2, 0, 2, 0);
            dataNumberView.setGravity(Gravity.CENTER);
            row.addView(dataNumberView);

            // add check box to row
            CheckBox dataBox = new CheckBox(this);
            dataBox.setLayoutParams(lpItem);
            dataBox.setChecked(FilteredTeamNumberList.contains(TeamNumber));
            dataBox.setPadding(2, 0, 2, 0);
            dataBox.setGravity(Gravity.END);
            dataBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox dataBox = (CheckBox) v;
                    if (dataBox.isChecked()) {
                        // add int TeamNumber to list of filtered numbers
                        if (!FilteredTeamNumberList.contains(TeamNumber)) { // no dupes!
                            FilteredTeamNumberList.add(TeamNumber);
                        }
                    } else {
                        // remove int TeamNumber from list of filtered numbers
                        while (FilteredTeamNumberList.contains(TeamNumber)) { // no dupes!
                            FilteredTeamNumberList.remove(TeamNumber);
                        }
                    }
                }
            });
            row.addView(dataBox);

            // add the data row to the end of the table
            table.addView(row);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        App.setFilteredTeamNumberList(FilteredTeamNumberList);
    }
}
