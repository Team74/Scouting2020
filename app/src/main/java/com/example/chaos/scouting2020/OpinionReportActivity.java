package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.os.Bundle;

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
                    + " GROUP BY TeamNumber"
                    + " ORDER BY " + columns[ReportSortColumn] + (ReportSortAsc ? " ASC" : " DESC");
            DaoTeamRoundData.OpinionReportData dataRecords[] = daoTeamRoundData.getOpinionReportDataRaw(new SimpleSQLiteQuery(query));

            // add the header strings as a row to our table
            AddHeaderStringsAsRowToReportTable(R.id.opinionReportTable, headings, this);

            // create a data row for each data record returned from DB
            for (DaoTeamRoundData.OpinionReportData dataRecord : dataRecords) {
                // add each data value to an array of strings
                String[] values = {
                        Integer.toString(dataRecord.TeamNumber),
                        String.format("%.3f", dataRecord.AvgShootingOpinion),
                        String.format("%.3f", dataRecord.AvgClimbingOpinion),
                        String.format("%.3f", dataRecord.AvgSpinningOpinion),
                        String.format("%.3f", dataRecord.AvgAutonOpinion),
                        String.format("%.3f", dataRecord.AvgDriverOpinion),
                        String.format("%.3f", dataRecord.AvgWouldPickOpinion),
                        String.format("%.3f", dataRecord.AvgStarOpinion)
                };

                // add the data strings as a row to our table
                AddDataStringsAsRowToReportTable(R.id.opinionReportTable, values);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_report);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = App.getDaoTeamRoundData();
        }

        // display the report table for the first time
        UpdateOpinionReportTable updateOpinionReportTable = new UpdateOpinionReportTable();
        updateOpinionReportTable.update();
    }
}
