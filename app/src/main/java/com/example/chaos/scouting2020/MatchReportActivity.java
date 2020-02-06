package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MatchReportActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected ScoutingDatabase db = null;
    protected DaoTeamRoundData daoTeamRoundData = null;

    protected boolean ReportSortAsc = true;
    protected int ReportSortColumn = 0;

    private void UpdateMatchReportTable() {
        // remove any rows from the current table
        TableLayout table = (TableLayout)findViewById(R.id.matchReportTable);
        table.removeAllViews();

        // get all the data records from the DB
        // NOTE: columns should match those in the MatchReportData class in DaoTeamRoundData
        // as well as the names in the query SELECT below.
        // You also need to provide conversions for the columns to strings down
        // below where the data values are added to the table.
        String columns[] = { "TeamNumber", "NumRounds", "AvgHighScore", "AvgLowScore", "NumSuccessfulClimbs", "NumFailedClimbs", "PercentClimbs", "PercentBreakdowns", "PercentStage2", "PercentStage3" };
        String headings[] = { "Team #", "# Rounds", "HG Round Avg" , "LG Round Avg", "# Succ Climbs", "# Failed Climbs", "Climb %", "Breakdown %", "Stage 2 %", "Stage 3 %" };
        String query = "SELECT TeamNumber,"
                + " COUNT(RoundNumber) AS NumRounds,"
                + " (AVG(AutonHighScore) + AVG(TeleopHighScore)) / 2.0 AS AvgHighScore,"
                + " (AVG(AutonLowScore) + AVG(TeleopLowScore)) / 2.0 AS AvgLowScore,"
                // num successful climbs = SUM(Climb = 1)
                + " SUM(CASE WHEN Climb = 1 THEN 1 ELSE 0 END) AS NumSuccessfulClimbs,"
                // num failed climbs = SUM(Climb = 3)
                + " SUM(CASE WHEN Climb = 3 THEN 1 ELSE 0 END) AS NumFailedClimbs,"
                // PercentClimbs = num successful climbs / num attempted climbs
                // num attempted climbs = num successful climbs + num failed climbs
                // note the use of decimal values to get a float result
                + " SUM(CASE WHEN Climb = 1 THEN 1.0 ELSE 0.0 END) / SUM(CASE WHEN Climb = 1 OR Climb = 3 THEN 1.0 ELSE 0.0 END) AS PercentClimbs,"
                + " AVG(BrokeDown) AS PercentBreakdowns," // true=1, false=0
                + " AVG(CASE WHEN FinalStage = 2 THEN 1 ELSE 0 END) AS PercentStage2,"
                + " AVG(CASE WHEN FinalStage = 3 THEN 1 ELSE 0 END) AS PercentStage3"
                + " FROM EntityTeamRoundData"
                + " GROUP BY TeamNumber"
                + " ORDER BY " + columns[ReportSortColumn] + " " + (ReportSortAsc ? "ASC" : "DESC");
        DaoTeamRoundData.MatchReportData dataRows[] = daoTeamRoundData.getMatchReportDataRaw(new SimpleSQLiteQuery(query));

        // create a common layout param group for all of our rows
        TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, headings.length);
        TableRow.LayoutParams lpItem = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1);

        // create a header row
        TableRow hdr = new TableRow(this);
        hdr.setLayoutParams(lpRow);

        // add each heading string to our header row
        for(int i = 0; i < headings.length; i++) {
            final int headingIndex = i; // needs to be final for onClick
            String heading = headings[headingIndex];
            TextView hdrView = new TextView(this);
            hdrView.setLayoutParams(lpItem);
            hdrView.setText(heading);
            hdrView.setPadding(4, 0, 4, 0);
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
                        ReportSortAsc = true;
                    }
                    // redisplay the entire table
                    UpdateMatchReportTable();
                }
            });
            hdr.addView(hdrView);
        }
        // add the data row to the table
        table.addView(hdr, 0);

        // create a data row for each record returned from DB
        for(int i = 0; i < dataRows.length; i++) {
            // get a single record of data from the returned set
            DaoTeamRoundData.MatchReportData dataRow = dataRows[i];

            // create a new row to hold our data values
            TableRow row = new TableRow(this);
            row.setLayoutParams(lpRow);
            // add table row
            if((i % 2) == 0) {
                row.setBackgroundResource(R.color.colorBlueBackground);
            } else {
                row.setBackgroundResource(R.color.colorRedBackground);
            }

            // add each data value to an array of strings
            String[] values = {
                    Integer.toString(dataRow.TeamNumber),
                    Integer.toString(dataRow.NumRounds),
                    String.format ("%.3f", dataRow.AvgHighScore),
                    String.format ("%.3f", dataRow.AvgLowScore),
                    Integer.toString(dataRow.NumSuccessfulClimbs),
                    Integer.toString(dataRow.NumFailedClimbs),
                    String.format ("%.3f", dataRow.PercentClimbs),
                    String.format ("%.3f", dataRow.PercentBreakdowns),
                    String.format ("%.3f", dataRow.PercentStage2),
                    String.format ("%.3f", dataRow.PercentStage3)
            };

            // add each data string to our row
            for(String value : values) {
                TextView dataView = new TextView(this);
                dataView.setLayoutParams(lpItem);
                dataView.setText(value);
                dataView.setPadding(4, 0, 4, 0);
                dataView.setGravity(Gravity.CENTER);
                row.addView(dataView);
            }

            // add the data row to the table
            table.addView(row, i+1); // +1 to be after hdr
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_report);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // start a new team round data record
        App.newTeamRoundData();

        if(db == null){
            db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDb")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
            // TBD: figure out how to allow for "Non-destructive Migrations" of the ROOM DB
            // for when the version changes
        }
        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = db.daoTeamRoundData();
        }

        // display the report table for the first time
        UpdateMatchReportTable();
    }
}
