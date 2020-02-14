package com.example.chaos.scouting2020;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class TeamRoundDataReportActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected DaoTeamRoundData daoTeamRoundData = null;
    protected DaoTeamData daoTeamData = null;
    protected int TeamNumberKey = 0;
    protected int[] TeamArray;

    public class UpdateTeamRoundDataReportTable implements ReportUpdateCommand {

        public void update() {
            // get all the data records from the DB
            // NOTE: columns should match those in the TeamRoundDataReportData class in DaoTeamRoundData
            // as well as the names in the query SELECT below.
            // You also need to provide conversions for the columns to strings down
            // below where the data values are added to the table.
            String columns[] = {"TeamNumber", "RoundNumber", "Scouter", "TeamColor", "AutonHighScore", "AutonLowScore", "AutonPickUp", "AutonStartLine", "TeleopHighScore", "TeleopLowScore", "TeleopPickUp", "RotationControl", "PositionControl", "Climb", "BrokeDown", "FinalStage", "Notes", "RateShooting", "RateClimb", "RateWheel", "RateAuton", "RateDriver", "WouldPick"};
            String headings[] = {"Team #", "Round Number", "Scouter", "Team Color", "Auton High Score", "Auton Low Score", "Auton Pick Up", "Auton Start Line", "Teleop High Score", "Teleop Low Score", "Teleop Pick Up", "Rotation Control", "Position Control", "Climbed", "Broke Down", "Final Stage", "Notes", "Rate Shooting", "Rate Climb", "Rate Wheel", "Rate Auton", "Rate Driver", "Would Pick"};
            String query = "SELECT "
                    + " TeamNumber,"
                    + " RoundNumber,"
                    + " Scouter,"
                    + " TeamColor,"
                    + " AutonHighScore,"
                    + " AutonLowScore,"
                    + " AutonPickUp,"
                    + " AutonStartLine,"
                    + " TeleopHighScore,"
                    + " TeleopLowScore,"
                    + " TeleopPickUp,"
                    + " RotationControl,"
                    + " PositionControl,"
                    + " Climb,"
                    + " BrokeDown,"
                    + " FinalStage,"
                    + " Notes,"
                    + " RateShooting,"
                    + " RateClimb,"
                    + " RateWheel,"
                    + " RateAuton,"
                    + " RateDriver,"
                    + " WouldPick"
                    + " FROM EntityTeamRoundData"
                    + " WHERE TeamNumber IN (" + TeamArray[TeamNumberKey] + ") AND TeamNumber NOT IN (" + ReportFilteredTeamNumberStringList + ")"
                    + " ORDER BY " + columns[ReportSortColumn] + (ReportSortAsc ? " ASC" : " DESC");
            DaoTeamRoundData.TeamRoundDataReportData dataRecords[] = daoTeamRoundData.getTeamRoundDataReportDataRaw(new SimpleSQLiteQuery(query));

            // add the header strings as a row to our table
            AddHeaderStringsAsRowToReportTable(R.id.teamRoundDataReportTable, headings, this);

            // create a data row for each data record returned from DB
            for(DaoTeamRoundData.TeamRoundDataReportData dataRecord : dataRecords) {

                // add each data value to an array of strings
                String[] values = {
                        Integer.toString(dataRecord.TeamNumber),
                        Integer.toString(dataRecord.RoundNumber),
                        dataRecord.Scouter,
                        dataRecord.TeamColor,
                        Integer.toString(dataRecord.AutonHighScore),
                        Integer.toString(dataRecord.AutonLowScore),
                        Integer.toString(dataRecord.AutonPickUp),
                        Boolean.toString(dataRecord.AutonStartLine),
                        Integer.toString(dataRecord.TeleopHighScore),
                        Integer.toString(dataRecord.TeleopLowScore),
                        Integer.toString(dataRecord.TeleopPickUp),
                        Boolean.toString(dataRecord.RotationControl),
                        Boolean.toString(dataRecord.PositionControl),
                        Integer.toString(dataRecord.Climb),
                        Boolean.toString(dataRecord.BrokeDown),
                        Integer.toString(dataRecord.FinalStage),
                        dataRecord.Notes,
                        Integer.toString(dataRecord.RateShooting),
                        Integer.toString(dataRecord.RateClimb),
                        Integer.toString(dataRecord.RateWheel),
                        Integer.toString(dataRecord.RateAuton),
                        Integer.toString(dataRecord.RateDriver),
                        Boolean.toString(dataRecord.WouldPick),
                };

                // add the data strings as a row to our table
                AddDataStringsAsRowToReportTable(R.id.teamRoundDataReportTable, values);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_round_data_report);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // get data access objects (tables)
        daoTeamRoundData = App.getDaoTeamRoundData();
        daoTeamData = App.getDaoTeamData();

        // get the list of filtered team numbers
        List<Integer> filteredTeamNumberList = App.getFilteredTeamNumberList();
        ReportFilteredTeamNumberStringList = "0";
        for(Integer filteredTeamNumber : filteredTeamNumberList) {
            ReportFilteredTeamNumberStringList = ReportFilteredTeamNumberStringList + "," + filteredTeamNumber;
        }

        // get the list of all team numbers
        TeamArray = daoTeamData.getAllTeamNumbers();

        // display the report table for the first time
        UpdateTeamRoundDataReportTable updateTeamRoundDataReportTable = new UpdateTeamRoundDataReportTable();
        updateTeamRoundDataReportTable.update();
    }

    // needs to be fixed so that it can't go over.
    // need to add a for loop that loops through all the filtered teams to skip them.
    public void nextButtonPressed(View nextButton){
        if (TeamNumberKey < (TeamArray.length - 1)) {
            TeamNumberKey++;
            UpdateTeamRoundDataReportTable updateTeamRoundDataReportTable = new UpdateTeamRoundDataReportTable();
            updateTeamRoundDataReportTable.update();
        }
    }

    public void previousButtonPressed(View previosButton){
        if (TeamNumberKey > 0){
            TeamNumberKey--;
            UpdateTeamRoundDataReportTable updateTeamRoundDataReportTable = new UpdateTeamRoundDataReportTable();
            updateTeamRoundDataReportTable.update();
        }
    }
}
