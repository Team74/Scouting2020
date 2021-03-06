package com.example.chaos.scouting2020;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.OpenableColumns;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class AdminActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected String BaseDir;  // location where to export things

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 456:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // granted - not much to do here
                } else {
                    // denied
                    Toast.makeText(this, "Permissions for exporting denied.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Admin");

        // Newer versions of Android expect permissions to be requested at runtime instead
        // of via the manifest file.  The manifest file will be used on API < 23, and this
        // code wil do nothing.
        // if you don't have required permissions ask for it (only required for API 23+)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ) {
            String[] permissions = { Manifest.permission.WRITE_EXTERNAL_STORAGE };
            // Note: when the user responds to the permission request, this will generate
            // a result response which will be handled by onRequestPermissionsResult() above.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 456);
            }
        }

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
                if (   volume.toUpperCase().contains("USB")
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

    public void adminExportScouterNamesButtonPressed(View adminExportScouterNamesButton) {
        App.exportScouterNames(BaseDir);
    }

    public void adminExportTeamRoundDataButtonPressed(View adminExportTeamRoundDataButton) {
        App.exportTeamRoundData(BaseDir);
    }

    public void adminExportTeamDataButtonPressed(View adminExportTeamDataButton) {
        App.exportTeamData(BaseDir);
    }

    public void adminAddScouterNameButtonPressed(View adminAddScouterNameButton){
        try {
            EditText adminAddScouterNameText = (EditText) findViewById(R.id.adminAddScouterNameEditText);
            String scouterName = adminAddScouterNameText.getText().toString();
            if ((scouterName != null) && (!scouterName.equals(""))) {
                App.addScouterName(scouterName);
                Toast.makeText(this, "Name added successfully.", Toast.LENGTH_SHORT).show();
                adminAddScouterNameText.setText("");
            }else{
                Toast.makeText(this, "Name can not be blank.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error adding scouter name.", Toast.LENGTH_SHORT).show();
        }
    }

    public void adminAddTeamNumberButtonPressed(View adminAddScouterNameButton){
        try {
            EditText adminAddTeamNumberText = (EditText) findViewById(R.id.adminAddTeamNumberEditText);
            int teamNumber = Integer.parseInt(adminAddTeamNumberText.getText().toString());
            App.newTeamData();
            App.setPitTeamNumber(teamNumber);
            App.saveTeamData();
            Toast.makeText(this, "Team added successfully.", Toast.LENGTH_SHORT).show();
            adminAddTeamNumberText.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error adding team number.", Toast.LENGTH_SHORT).show();
        }
    }

    public void adminMenuButtonPressed(View adminMenuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void adminImportButtonPressed(View adminImportButton) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select a CSV file"), 123);
        // NOTE: When the "file chooser" finishes, it will send the URI of
        // the selected file back to the application as an "ActivityResult"
        // with the requestCode of 123 (the magic number we passed with the intent.)
        // The onActivityResult() method below will catch this and
        // import the CSV file specified by the URI.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 123) && (resultCode == RESULT_OK)) {
            // the uri with the location of the file
            Uri selectedFileUri = data.getData();
            if (selectedFileUri != null) {
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
    }
}
