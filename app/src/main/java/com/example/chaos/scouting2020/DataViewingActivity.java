package com.example.chaos.scouting2020;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.OpenableColumns;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class DataViewingActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected String BaseDir;  // location when to export things

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewing);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // Get default export location
        BaseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;

        // check to see if a USB OTG device is mounted.
        // if it is, use it for exports.
        StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
        try {
            String[] volumes = (String[]) storageManager.getClass()
                    .getMethod("getVolumePaths", new Class[0])
                    .invoke(storageManager, new Object[0]);
            for(int i=0; i<volumes.length; i++) {
                // These are some of the possible volumes we've seen on the Fire tablets:
                // "/storage/emulated/0" -- this is the built in one from emulated from flash
                // "/storage/sdcard1" -- this is the one if you an actual SD card
                // "/storage/usbotg" -- this is the if you use on USB OTG drive
                if(   volumes[i].toUpperCase().contains("USB")
                   && volumes[i].toUpperCase().contains("OTG")) {
                    // Using a HACK to see if it's mounted -- checking if it has space
                    File externalFile = new File(volumes[i]);
                    if (externalFile.getTotalSpace() > 0) {
                        BaseDir = volumes[i];
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
        Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a CSV file"), 123);
    }

    // URI are weird!  this code was copied from online example.
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
