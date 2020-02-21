package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class DataViewingActivity extends BaseActivity {

    protected ScoutingApplication App;
    List<Integer> filteredTeamNumberList = null;
    protected DaoTeamData daoTeamData = null;
    protected int[] TeamArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewing);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Data Viewing");

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();
        daoTeamData = App.getDaoTeamData();
        filteredTeamNumberList = App.getFilteredTeamNumberList();
        TeamArray = daoTeamData.getAllTeamNumbers();
    }

    public void dataViewingReportFiltersButtonPressed(View reportFiltersButton) {
        Intent intent = new Intent(this, ReportFiltersActivity.class);
        startActivity(intent);
    }

    public void dataViewingMatchReportButtonPressed(View matchReportButton) {
        if(TeamArray.length == filteredTeamNumberList.size()){
            Toast.makeText(this, "Please deselect at least one team.", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, MatchReportActivity.class);
            startActivity(intent);
        }
    }

    public void dataViewingOpinionReportButtonPressed(View opinionReportButton) {
        if(TeamArray.length == filteredTeamNumberList.size()){
            Toast.makeText(this, "Please deselect at least one team.", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, OpinionReportActivity.class);
            startActivity(intent);
        }
    }

    public void dataViewingTeamRoundDataReportButtonPressed(View teamRoundDataReportButton) {
        if(TeamArray.length == filteredTeamNumberList.size()){
            Toast.makeText(this, "Please deselect at least one team.", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, TeamRoundDataReportActivity.class);
            startActivity(intent);
        }
    }

    public void dataViewingPitScoutingReportButtonPressed(View pitScoutingReportButton) {
        if(TeamArray.length == filteredTeamNumberList.size()){
            Toast.makeText(this, "Please deselect at least one team.", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, PitScoutingReportActivity.class);
            startActivity(intent);
        }
    }

    public void dataViewingScouterScheduleButtonPressed(View scouterScheduleReportButton) {
        Intent intent = new Intent(this, ScouterScheduleReportActivity.class);
        startActivity(intent);
    }

    public void dataViewingMenuButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
