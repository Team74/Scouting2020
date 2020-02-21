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

public class OpinionReportActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected DaoTeamRoundData daoTeamRoundData = null;

    public class UpdateOpinionReportTable implements ReportUpdateCommand {

        public void update() {
            // get all the data records from the DB
            // NOTE: columns should match those in the OpinionReportData class in DaoTeamRoundData
            // as well as the names in the query SELECT below.
            // You also need to provide conversions for the columns to strings down
            // below where the data values are added to the table.
            String columns[] = {"TeamNumber", "AvgShootingOpinion", "AvgClimbingOpinion", "AvgSpinningOpinion", "AvgAutonOpinion", "AvgDriverOpinion", "AvgWouldPickOpinion", "AvgStarOpinion"};
            String headings[] = {"Team #", "Avg Shooting Opinion", "Avg Climbing Opinion", "Avg Spinning Opinion", "Avg Auton Opinion", "Avg Driver Opinion", "Avg Would Pick Opinion", "Avg Star Opinion"};
            String query = "SELECT "
                    + " TeamNumber,"
                    + " AVG(RateShooting) AS AvgShootingOpinion,"
                    + " AVG(RateClimb) AS AvgClimbingOpinion,"
                    + " AVG(RateWheel) AS AvgSpinningOpinion,"
                    + " AVG(RateAuton) AS AvgAutonOpinion,"
                    + " AVG(RateDriver) AS AvgDriverOpinion,"
                    + " AVG(WouldPick) AS AvgWouldPickOpinion,"
                    + " (AVG(RateShooting)+AVG(RateClimb)+AVG(RateWheel)+AVG(RateAuton)+AVG(RateDriver)+(AVG(WouldPick)*5.0))/6.0 AS AvgStarOpinion"
                    + " FROM EntityTeamRoundData"
                    + " WHERE TeamNumber NOT IN (" + ReportFilteredTeamNumberStringList + ")"
                    + " GROUP BY TeamNumber"
                    + " ORDER BY " + columns[ReportSortColumn] + (ReportSortAsc ? " ASC" : " DESC");
            DaoTeamRoundData.OpinionReportData dataRecords[] = daoTeamRoundData.getOpinionReportDataRaw(new SimpleSQLiteQuery(query));

            // add the header strings as a row to our table
            AddHeaderStringsAsRowToReportTable(R.id.opinionReportTable, headings, this, 15);

            // create a data row for each data record returned from DB
            for(DaoTeamRoundData.OpinionReportData dataRecord : dataRecords) {
                // add each data value to an array of strings
                String[] values = {
                        Integer.toString(dataRecord.TeamNumber),
                        String.format(Locale.US, "%.3f", dataRecord.AvgShootingOpinion),
                        String.format(Locale.US, "%.3f", dataRecord.AvgClimbingOpinion),
                        String.format(Locale.US, "%.3f", dataRecord.AvgSpinningOpinion),
                        String.format(Locale.US, "%.3f", dataRecord.AvgAutonOpinion),
                        String.format(Locale.US, "%.3f", dataRecord.AvgDriverOpinion),
                        String.format(Locale.US, "%.3f", dataRecord.AvgWouldPickOpinion),
                        String.format(Locale.US, "%.3f", dataRecord.AvgStarOpinion)
                };


                // add the data strings as a row to our table
                TableRow row = AddDataStringsAsRowToReportTable(R.id.opinionReportTable, values);
                //25% = 1.25
                //33% = 1.65
                if(dataRecord.AvgShootingOpinion < 1.65){
                    TextView textView = (TextView) row.getChildAt(1);
                    setHighLightedText(textView, values[1]);
                }
                if(dataRecord.AvgClimbingOpinion < 1.65){
                    TextView textView = (TextView) row.getChildAt(2);
                    setHighLightedText(textView, values[2]);
                }
                if(dataRecord.AvgSpinningOpinion < 1.65){
                    TextView textView = (TextView) row.getChildAt(3);
                    setHighLightedText(textView, values[3]);
                }
                if(dataRecord.AvgAutonOpinion < 1.65){
                    TextView textView = (TextView) row.getChildAt(4);
                    setHighLightedText(textView, values[4]);
                }
                if(dataRecord.AvgDriverOpinion < 1.65){
                    TextView textView = (TextView) row.getChildAt(5);
                    setHighLightedText(textView, values[5]);
                }
                if(dataRecord.AvgWouldPickOpinion < .333){
                    TextView textView = (TextView) row.getChildAt(6);
                    setHighLightedText(textView, values[6]);
                }
                if(dataRecord.AvgStarOpinion < 1.65){
                    TextView textView = (TextView) row.getChildAt(7);
                    setHighLightedText(textView, values[7]);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_report);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Opinion Report");

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
        UpdateOpinionReportTable updateOpinionReportTable = new UpdateOpinionReportTable();
        updateOpinionReportTable.update();
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
