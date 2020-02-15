package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DataViewingActivity extends BaseActivity {

    protected ScoutingApplication App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Scouting2020 - Data Viewing");

        setContentView(R.layout.activity_data_viewing);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();
    }

    public void dataViewingReportFiltersButtonPressed(View reportFiltersButton) {
        Intent intent = new Intent(this, ReportFiltersActivity.class);
        startActivity(intent);
    }

    public void dataViewingMatchReportButtonPressed(View matchReportButton) {
        Intent intent = new Intent(this, MatchReportActivity.class);
        startActivity(intent);
    }

    public void dataViewingOpinionReportButtonPressed(View opinionReportButton) {
        Intent intent = new Intent(this, OpinionReportActivity.class);
        startActivity(intent);
    }

    public void dataViewingTeamRoundDataReportButtonPressed(View teamRoundDataReportButton) {
        Intent intent = new Intent(this, TeamRoundDataReportActivity.class);
        startActivity(intent);
    }

    public void dataViewingPitScoutingReportButtonPressed(View pitScoutingReportButton) {
        Intent intent = new Intent(this, PitScoutingReportActivity.class);
        startActivity(intent);
    }

    public void dataViewingMenuButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
