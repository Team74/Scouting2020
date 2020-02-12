package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class AdminActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected String BaseDir;  // location when to export things

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // Get default CSV export location (the system download directory)
        BaseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;

    }
    public void menuButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    // ----------------------------------------------------------------------------------------
    // TBD: temp button for testing csv code
    public void exportScouterNamesButtonPressed(View exportScouterNamesButton) {
        App.exportScouterNames(BaseDir);
    }

    public void exportTeamRoundDataButtonPressed(View exportTeamRoundDataButton) {
        App.exportTeamRoundData(BaseDir);
    }

    public void exportTeamDataButtonPressed(View exportTeamDataButton) {
        App.exportTeamData(BaseDir);
    }

    public void importButtonPressed(View importButton) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select a CSV file"), 123);
    }
}
