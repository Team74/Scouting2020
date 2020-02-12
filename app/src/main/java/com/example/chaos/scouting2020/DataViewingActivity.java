package com.example.chaos.scouting2020;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.OpenableColumns;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class DataViewingActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected String BaseDir;  // location when to export things

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewing);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // Get default CSV export location (the system download directory)
        BaseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;

        // check to see if a USB OTG device is mounted.
        // if it is, use it for CSV exports.
        try {
            // get handle to storage manager
            StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
            // get a list of all the mount points (volumes)
            String[] volumes = (String[]) storageManager.getClass()
                                                        .getMethod("getVolumePaths")
                                                        .invoke(storageManager);
            // These are some of the possible volumes we've seen on the Fire tablets:
            // "/storage/emulated/0" -- this is the built in one emulated from flash
            // "/storage/sdcard1" -- this is the one if you use an actual SD card
            // "/storage/usbotg" -- this is the one if you use an USB OTG drive
            for(String volume : volumes) {
                // if it's a USB OTG volume
                if(   volume.toUpperCase().contains("USB")
                   && volume.toUpperCase().contains("OTG")) {
                    // check to see if it's mounted
                    Boolean mounted = (Boolean) storageManager.getClass()
                                                              .getMethod("getVolumeState", String.class)
                                                              .invoke(storageManager, volume)
                                                              .toString()
                                                              .equalsIgnoreCase(Environment.MEDIA_MOUNTED);
                    // if it's mounted...
                    if (mounted) {
                        // ... use it as the base directory for our CSV exports
                        BaseDir = volume;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error checking mounted volumes", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Exports will be written to " + BaseDir, Toast.LENGTH_SHORT).show();
    }

    public void menuButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 123) && (resultCode == RESULT_OK)) {
            // the uri with the location of the file
            Uri selectedFileUri = data.getData();

            // as an Android security feature, URIs limit the scope of information you
            // can retrieve from the file, and all "path" information is abstracted
            // so we have to jump through some hoops to get things like the file name.
            Cursor cursor = getContentResolver().query(selectedFileUri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    // note the display name is not a path to the file
                    // and can not be used for file operations
                    String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    // check the file type and call the appropriate import function
                    if (displayName.contains("ScouterNames-")) {
                        App.importScouterNames(selectedFileUri);
                    } else if (displayName.contains("TeamRoundData-")) {
                        App.importTeamRoundData(selectedFileUri);
                    } else if (displayName.contains("TeamData-")) {
                        App.importTeamData(selectedFileUri);
                    } else {
                        Toast.makeText(this, "Unsupported CSV file", Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.close();
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

    public void reportFiltersButtonPressed(View pitScoutingReportButton) {
        Intent intent = new Intent(this, ReportFiltersActivity.class);
        startActivity(intent);
    }
}
