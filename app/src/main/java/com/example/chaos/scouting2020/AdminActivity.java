package com.example.chaos.scouting2020;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class AdminActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected String BaseDir;  // location when to export things
    public String scout;

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

    public void addScouterName(){
        App.addScouterName(scout);
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
}
