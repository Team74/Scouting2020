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
