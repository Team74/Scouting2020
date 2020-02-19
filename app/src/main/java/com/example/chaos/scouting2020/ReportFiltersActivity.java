package com.example.chaos.scouting2020;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class ReportFiltersActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected DaoTeamData daoTeamData = null;
    protected EntityTeamData[] TeamData;

    protected boolean boxesChecked = false;
    protected List<Integer> FilteredTeamNumberList;
    protected Context Ctx;
    protected UpdateReportFiltersTable updateReportFiltersTable;

    public class UpdateReportFiltersTable implements ReportUpdateCommand {

        public void update() {

            // get handle to display table (TableLayout)
            TableLayout table = (TableLayout)findViewById(R.id.reportFiltersTable);
            table.removeAllViews();

            // create a common layout param group for all of our rows and items
            TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 3);
            TableRow.LayoutParams lpItem = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1);

            // create a new row for headers
            TableRow hdr = new TableRow(Ctx);
            hdr.setLayoutParams(lpRow);

            // set the background color of the header row
            hdr.setBackgroundResource(R.color.colorWhiteBackground);

            // add text view for team name header to row
            TextView hdrNameView = new TextView(Ctx);
            hdrNameView.setLayoutParams(lpItem);
            hdrNameView.setText("Team Name");
            hdrNameView.setPadding(2, 0, 2, 0);
            hdrNameView.setGravity(Gravity.CENTER);
            hdr.addView(hdrNameView);

            // add text view for team number header to row
            TextView hdrNumberView = new TextView(Ctx);
            hdrNumberView.setLayoutParams(lpItem);
            hdrNumberView.setText("Team Number");
            hdrNumberView.setPadding(2, 0, 2, 0);
            hdrNumberView.setGravity(Gravity.CENTER);
            hdr.addView(hdrNumberView);

            // add check box header to row
            TextView hdrBoxview = new TextView(Ctx);
            hdrBoxview.setLayoutParams(lpItem);
            hdrBoxview.setText("Filter Out");
            hdrBoxview.setPadding(2, 0, 2, 0);
            hdrBoxview.setGravity(Gravity.CENTER);
            hdrBoxview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                if (boxesChecked == false) {
                    for (EntityTeamData team : TeamData) {
                        Integer teamNumber = Integer.valueOf(team.TeamNumber);
                        // add Integer TeamNumber to list of filtered numbers
                        if (!FilteredTeamNumberList.contains(teamNumber)) { // no dupes!
                            FilteredTeamNumberList.add(teamNumber);
                        }
                    }
                }
                else {
                    for (EntityTeamData team : TeamData) {
                        Integer teamNumber = Integer.valueOf(team.TeamNumber);
                        // remove Integer TeamNumber from list of filtered numbers
                        while (FilteredTeamNumberList.contains(teamNumber)) { // no dupes!
                            FilteredTeamNumberList.remove(teamNumber);
                        }
                    }
                }
                boxesChecked = !boxesChecked;
                updateReportFiltersTable.update();
                }
            });

            hdr.addView(hdrBoxview);

            // add the header row to the table (it will be the first row)
            table.addView(hdr);

            // loop over team numbers
            for(EntityTeamData team : TeamData) {
                // create a new row to hold our data values
                TableRow row = new TableRow(Ctx);
                row.setLayoutParams(lpRow);

                // alternate the background color of each row
                int rowNumber = table.getChildCount();
                if ((rowNumber % 2) == 0) {
                    row.setBackgroundResource(R.color.colorBlueBackground);
                } else {
                    row.setBackgroundResource(R.color.colorRedBackground);
                }

                // add text view for team name to row
                TextView dataNameView = new TextView(Ctx);
                dataNameView.setLayoutParams(lpItem);
                dataNameView.setText(team.TeamName);
                dataNameView.setPadding(2, 0, 2, 0);
                dataNameView.setGravity(Gravity.CENTER);
                row.addView(dataNameView);

                // add text view for team number to row
                TextView dataNumberView = new TextView(Ctx);
                dataNumberView.setLayoutParams(lpItem);
                dataNumberView.setText(""+team.TeamNumber);
                dataNumberView.setPadding(2, 0, 2, 0);
                dataNumberView.setGravity(Gravity.CENTER);
                row.addView(dataNumberView);

                // add check box to row
                final Integer TeamNumber = Integer.valueOf(team.TeamNumber);
                CheckBox dataBox = new CheckBox(Ctx);
                dataBox.setLayoutParams(lpItem);
                dataBox.setChecked(FilteredTeamNumberList.contains(TeamNumber));
                dataBox.setPadding(2, 0, 2, 0);
                dataBox.setGravity(Gravity.END);
                dataBox.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                    CheckBox dataBox = (CheckBox) view;
                    if (dataBox.isChecked()) {
                        // add Integer TeamNumber to list of filtered numbers
                        if (!FilteredTeamNumberList.contains(TeamNumber)) { // no dupes!
                            FilteredTeamNumberList.add(TeamNumber);
                        }
                    } else {
                        // remove Integer TeamNumber from list of filtered numbers
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_filters);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Report Filters");

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // get a handle to the current activity context
        Ctx  = this;

        // get a handle to the current list of filtered team numbers
        FilteredTeamNumberList = App.getFilteredTeamNumberList();

        // get data access objects (tables)
        daoTeamData = App.getDaoTeamData();

        // get all the data for all the teams
        TeamData = daoTeamData.getAllTeamData();

        // display the report table for the first time
        updateReportFiltersTable = new UpdateReportFiltersTable();
        updateReportFiltersTable.update();
    }
}
