package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.os.Bundle;

import java.util.List;

public class PitScoutingReportActivity extends BaseActivity {

    protected ScoutingApplication App;

    protected DaoTeamData daoTeamData = null;

    public class UpdatePitScoutingReportTable implements ReportUpdateCommand {

        public void update() {
            // get all the data records from the DB
            // NOTE: columns should match those in the PitScoutingReportData class in DaoTeamData
            // as well as the names in the query SELECT below.
            // You also need to provide conversions for the columns to strings down
            // below where the data values are added to the table.
            String columns[] = {"TeamNumber", "PitScouter", "RobotWeight", "ShootingLocation1", "ShootingLocation2", "ShootingLocation3", "StartLocationLeft", "StartLocationCenter", "StartLocationRight", "RobotDriveBaseType"};
            String headings[] = {"Team #", "Scouter", "Robot Weight", "Shooting Location 1", "Shooting Location 2", "Shooting Location 3", "Auton Start Left", "Auton Start Center", "Auton Start Right", "Drive Base Type"};
            String query = "SELECT "
                    + " TeamNumber,"
                    + " PitScouter,"
                    + " RobotWeight,"
                    + " ShootingLocation1,"
                    + " ShootingLocation2,"
                    + " ShootingLocation3,"
                    + " StartLocationLeft,"
                    + " StartLocationCenter,"
                    + " StartLocationRight,"
                    + " RobotDriveBaseType"
                    + " FROM EntityTeamData"
                    + " WHERE TeamNumber NOT IN (" + ReportFilteredTeamNumberStringList + ")"
                    + " ORDER BY " + columns[ReportSortColumn] + (ReportSortAsc ? " ASC" : " DESC");
            DaoTeamData.PitScoutingReportData dataRecords[] = daoTeamData.getPitScoutingReportDataRaw(new SimpleSQLiteQuery(query));

            // add the header strings as a row to our table
            AddHeaderStringsAsRowToReportTable(R.id.pitScoutingReportTable, headings, this);

            // create a data row for each data record returned from DB
            for (DaoTeamData.PitScoutingReportData dataRecord : dataRecords) {
                // add each data value to an array of strings
                String[] values = {
                        Integer.toString(dataRecord.TeamNumber),
                        dataRecord.PitScouter,
                        Integer.toString(dataRecord.RobotWeight),
                        dataRecord.ShootingLocation1 ? "Yes" : "No",
                        dataRecord.ShootingLocation2 ? "Yes" : "No",
                        dataRecord.ShootingLocation3 ? "Yes" : "No",
                        dataRecord.StartLocationLeft ? "Yes" : "No",
                        dataRecord.StartLocationCenter ? "Yes" : "No",
                        dataRecord.StartLocationRight ? "Yes" : "No",
                        dataRecord.RobotDriveBaseType
                };

                // add the data strings as a row to our table
                AddDataStringsAsRowToReportTable(R.id.pitScoutingReportTable, values);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_report);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // get data access objects (tables)
        daoTeamData = App.getDaoTeamData();

        // get the list of filtered team numbers
        List<Integer> filteredTeamNumberList = App.getFilteredTeamNumberList();
        ReportFilteredTeamNumberStringList = "0";
        for(Integer filteredTeamNumber : filteredTeamNumberList) {
            ReportFilteredTeamNumberStringList = ReportFilteredTeamNumberStringList + "," + filteredTeamNumber;
        }

        // display the report table for the first time
        UpdatePitScoutingReportTable updatePitScoutingReportTable = new UpdatePitScoutingReportTable();
        updatePitScoutingReportTable.update();
    }
}
