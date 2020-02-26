package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;


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
                    + " WHERE TeamNumber NOT IN (" + ReportFilteredTeamNumberStringList + ")"
                    + " GROUP BY TeamNumber"
                    + " ORDER BY " + columns[ReportSortColumn] + (ReportSortAsc ? " ASC" : " DESC");
            DaoTeamRoundData.MatchReportData dataRecords[] = daoTeamRoundData.getMatchReportDataRaw(new SimpleSQLiteQuery(query));

            // add the header strings as a row to our table
            AddHeaderStringsAsRowToReportTable(R.id.matchReportTable, headings, this, 15);

            // create a data row for each data record returned from DB
            for(DaoTeamRoundData.MatchReportData dataRecord : dataRecords) {
                // add each data value to an array of strings
                String[] values = {
                        Integer.toString(dataRecord.TeamNumber),//0
                        Integer.toString(dataRecord.NumRounds),//1
                        String.format(Locale.US, "%.3f", dataRecord.AvgHighScore),//2
                        String.format(Locale.US, "%.3f", dataRecord.AvgLowScore),//3
                        Integer.toString(dataRecord.NumSuccessfulClimbs),//4
                        Integer.toString(dataRecord.NumFailedClimbs),//5
                        String.format(Locale.US, "%.3f", dataRecord.PercentClimbs),//6
                        String.format(Locale.US, "%.3f", dataRecord.PercentBreakdowns),//7
                        String.format(Locale.US, "%.3f", dataRecord.PercentStage2),//8
                        String.format(Locale.US, "%.3f", dataRecord.PercentStage3)//9
                };

                // add the data strings as a row to our table
                TableRow row = AddDataStringsAsRowToReportTable(R.id.matchReportTable, values);

                if(dataRecord.AvgHighScore < 5){
                    TextView textView = (TextView) row.getChildAt(2);
                    setHighLightedText(textView, values[2]);
                }
                if(dataRecord.PercentClimbs < .333){
                    TextView textView = (TextView) row.getChildAt(6);
                    setHighLightedText(textView, values[6]);
                }
                if(dataRecord.PercentBreakdowns > .2){
                    TextView textView = (TextView) row.getChildAt(7);
                    setHighLightedText(textView, values[7]);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_report);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Match Report");

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // get data access objects (tables)
        daoTeamRoundData = App.getDaoTeamRoundData();

        // get the list of filtered team numbers
        List<Integer> filteredTeamNumberList = App.getFilteredTeamNumberList();
        ReportFilteredTeamNumberStringList = "0";
        for(Integer filteredTeamNumber : filteredTeamNumberList) {
            ReportFilteredTeamNumberStringList = ReportFilteredTeamNumberStringList + "," + filteredTeamNumber;
        }

        // display the report table for the first time
        UpdateMatchReportTable updateMatchReportTable = new UpdateMatchReportTable();
        updateMatchReportTable.update();
    }
    //highlights text
    public void setHighLightedText(TextView tv, String textToHighlight) {
        String tvt = tv.getText().toString();
        int ofe = tvt.indexOf(textToHighlight, 0);
        Spannable wordToSpan = new SpannableString(tv.getText());
        for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {
            ofe = tvt.indexOf(textToHighlight, ofs);
            if (ofe == -1)
                break;
            else {
                // set color here
                wordToSpan.setSpan(new BackgroundColorSpan(0xFFFF3838), ofe, ofe + textToHighlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE);
            }
        }
    }
}
