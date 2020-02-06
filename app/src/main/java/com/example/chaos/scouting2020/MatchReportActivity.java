package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.room.Room;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MatchReportActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected DaoTeamRoundData daoTeamRoundData = null;

    public class UpdateMatchReportTable implements ReportUpdateCommand {

        public void update() {
            // get all the data records from the DB
            // NOTE: columns should match those in the MatchReportData class in DaoTeamRoundData
            // as well as the names in the query SELECT below.
            // You also need to provide conversions for the columns to strings down
            // below where the data values are added to the table.
            String columns[] = {"TeamNumber", "NumRounds", "AvgHighScore", "AvgLowScore", "NumSuccessfulClimbs", "NumFailedClimbs", "PercentClimbs", "PercentBreakdowns", "PercentStage2", "PercentStage3"};
            String headings[] = {"Team #", "# Rounds", "HG Round Avg", "LG Round Avg", "# Succ Climbs", "# Failed Climbs", "Climb %", "Breakdown %", "Stage 2 %", "Stage 3 %"};
            String query = "SELECT "
                    + " TeamNumber,"
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
                    + " ORDER BY " + columns[ReportSortColumn] + (ReportSortAsc ? " ASC" : " DESC");
            DaoTeamRoundData.MatchReportData dataRecords[] = daoTeamRoundData.getMatchReportDataRaw(new SimpleSQLiteQuery(query));

            // add the header strings as a row to our table
            AddHeaderStringsAsRowToReportTable(R.id.matchReportTable, headings, this);

            // create a data row for each data record returned from DB
            for (DaoTeamRoundData.MatchReportData dataRecord : dataRecords) {
                // add each data value to an array of strings
                String[] values = {
                        Integer.toString(dataRecord.TeamNumber),
                        Integer.toString(dataRecord.NumRounds),
                        String.format("%.3f", dataRecord.AvgHighScore),
                        String.format("%.3f", dataRecord.AvgLowScore),
                        Integer.toString(dataRecord.NumSuccessfulClimbs),
                        Integer.toString(dataRecord.NumFailedClimbs),
                        String.format("%.3f", dataRecord.PercentClimbs),
                        String.format("%.3f", dataRecord.PercentBreakdowns),
                        String.format("%.3f", dataRecord.PercentStage2),
                        String.format("%.3f", dataRecord.PercentStage3)
                };

                // add the data strings as a row to our table
                AddDataStringsAsRowToReportTable(R.id.matchReportTable, values);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_report);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = App.getDaoTeamRoundData();
        }

        // display the report table for the first time
        UpdateMatchReportTable updateMatchReportTable = new UpdateMatchReportTable();
        updateMatchReportTable.update();
    }
}
