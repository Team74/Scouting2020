package com.example.chaos.scouting2020;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class DataViewingActivity extends BaseActivity {

    protected ScoutingApplication App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewing);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();
    }

    public void menuButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    // TBD: temp button for testing csv code
    public void exportScouterNamesButtonPressed(View exportScouterNamesButton) {
        App.exportScouterNames();
    }

    public void exportTeamRoundDataButtonPressed(View exportTeamRoundDataButton) {
        App.exportTeamRoundData();
    }

    public void exportTeamDataButtonPressed(View exportTeamDataButton) {
        App.exportTeamData();
    }

    public void importButtonPressed(View importButton) {
        Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a CSV file"), 123);
    }

    // URI are wierd!  this code was copied from online example.
    // I don't understand it, but it works.
    private String getFileNameFromUri(Uri uri) {
        String result = null;
        String scheme = uri.getScheme();
        if (scheme.equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        } else if (scheme.equals("file")) {
            result = uri.getLastPathSegment();
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK) {
            // the uri with the location of the file
            Uri selectedfile = data.getData();
            String filePath = getFileNameFromUri(selectedfile);

            // check the file type and call the appropriate import function
            if(filePath.contains("-ScouterNames-")) {
                App.importScouterNames(filePath);
            } else if(filePath.contains("-TeamRoundData-")) {
                App.importTeamRoundData(filePath);
            } else if(filePath.contains("-TeamData-")) {
                App.importTeamData(filePath);
            } else {
                Toast.makeText(this, "Unsupported CSV file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void matchReportButtonPressed(View matchReportButton) {
        Intent intent = new Intent(this, MatchReportActivity.class);
        startActivity(intent);
    }

    public void opinionReportButtonPressed(View opinionReportButton) {
        Intent intent = new Intent(this, OpinionReportActivity.class);
        startActivity(intent);
    }

    public void teamRoundDataButtonPressed(View teamRoundDataReportButton) {
        Intent intent = new Intent(this, TeamRoundDataReportActivity.class);
        startActivity(intent);
    }

    public void pitScoutingReportButtonPressed(View pitScoutingReportButton) {
        Intent intent = new Intent(this, PitScoutingReportActivity.class);
        startActivity(intent);
    }
}
