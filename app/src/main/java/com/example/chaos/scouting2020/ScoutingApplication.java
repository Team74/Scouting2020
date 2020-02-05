package com.example.chaos.scouting2020;

import android.app.Application;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Toast;
import android.arch.persistence.room.Room;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Random;

public class ScoutingApplication extends Application {

    // private sample data
    private String[] sampleScouters = { "Allen Z.", "Ben Y.", "Clara X.", "Dan W.", "Ed V." };
    private int[] sampleTeamNumbers = {1, 74, 56, 5565, 88};

    // database members. these are mostly created by startUpDb()
    ScoutingDatabase db = null;
    DaoTeamRoundData daoTeamRoundData = null;
    DaoScouterName daoScouterName = null;
    DaoTeamData daoTeamData = null;
    EntityTeamRoundData entityTeamRoundData = null;
    EntityScouterName entityScouterName = null;
    EntityTeamData entityTeamData = null;

    // primary key data
    private int TeamNumber = -1;
    private int RoundNumber = -1;

    // Get functions
    public int getTeamNumber(){
        return entityTeamRoundData.TeamNumber;
    }
    public int getRoundNumber(){
        return entityTeamRoundData.RoundNumber;
    }
    public String getScouterName(){
        return entityTeamRoundData.Scouter;
    }
    public String getTeamColor(){
        return entityTeamRoundData.TeamColor;
    }
    public int getAutonHighScore(){
        return entityTeamRoundData.AutonHighScore;
    }
    public int getAutonLowScore(){
        return entityTeamRoundData.AutonLowScore;
    }
    public int getAutonPickUp(){
        return entityTeamRoundData.AutonPickUp;
    }
    public boolean getAutonStartLine(){
        return entityTeamRoundData.AutonStartLine;
    }
    public int getTeleopHighScore(){
        return entityTeamRoundData.TeleopHighScore;
    }
    public int getTeleopLowScore(){
        return entityTeamRoundData.TeleopLowScore;
    }
    public int getTeleopPickUp(){
        return entityTeamRoundData.TeleopPickUp;
    }
    public boolean getRotationControl(){
        return entityTeamRoundData.RotationControl;
    }
    public boolean getPositionControl(){
        return entityTeamRoundData.PositionControl;
    }
    public int getClimb(){
        return entityTeamRoundData.Climb;
    }
    public boolean getBrokeDown(){
        return entityTeamRoundData.BrokeDown;
    }
    public int getFinalStage(){
        return entityTeamRoundData.FinalStage;
    }
    public String getNotes(){
        return entityTeamRoundData.Notes;
    }
    public int getRateShooting(){
        return entityTeamRoundData.RateShooting;
    }
    public int getRateClimb(){
        return entityTeamRoundData.RateClimb;
    }
    public int getRateWheel(){
        return entityTeamRoundData.RateWheel;
    }
    public int getRateAuton(){
        return entityTeamRoundData.RateAuton;
    }
    public int getRateDiver(){
        return entityTeamRoundData.RateDiver;
    }
    public boolean getWouldPick(){
        return entityTeamRoundData.WouldPick;
    }
    public boolean getShootingLocation1() { return entityTeamData.ShootingLocation1; }
    public boolean getShootingLocation2() { return entityTeamData.ShootingLocation2; }
    public boolean getShootingLocation3() { return entityTeamData.ShootingLocation3; }
    public boolean getStartLocationLeft() { return entityTeamData.StartLocationLeft; }
    public boolean getStartLocationCenter() { return entityTeamData.StartLocationCenter; }
    public boolean getStartLocationRight() { return entityTeamData.StartLocationRight; }
    // Get Functions End

    // Set Functions
    public void setTeamNumber(int teamNumber){
        // should only be set from login activity
        TeamNumber = teamNumber;
        entityTeamRoundData.TeamNumber = teamNumber;
    }
    public void setRoundNumber(int roundNumber){
        // should only be set from login activity
        RoundNumber = roundNumber;
        entityTeamRoundData.RoundNumber = roundNumber;
    }
    public void setScouter(String scouter){
        // should only be set from login activity
        entityTeamRoundData.Scouter = scouter;
    }
    public void setTeamColor(String color){
        // should only be set from login activity
        entityTeamRoundData.TeamColor = color;
    }
    public void setAutonHighScore(int autonHighScore){ entityTeamRoundData.AutonHighScore = autonHighScore; }
    public void setAutonLowScore(int autonLowScore){ entityTeamRoundData.AutonLowScore = autonLowScore; }
    public void setAutonPickUp(int autonPickUp){ entityTeamRoundData.AutonPickUp = autonPickUp; }
    public void setAutonStartLine(boolean autonStartLine){ entityTeamRoundData.AutonStartLine = autonStartLine; }
    public void setTeleopHighScore(int teleopHighScore){ entityTeamRoundData.TeleopHighScore = teleopHighScore; }
    public void setTeleopLowScore(int teleopLowScore){ entityTeamRoundData.TeleopLowScore = teleopLowScore; }
    public void setTeleopPickUp(int teleopPickUp){ entityTeamRoundData.TeleopPickUp = teleopPickUp; }
    public void setRotationControl(boolean rotationControl){ entityTeamRoundData.RotationControl = rotationControl; }
    public void setPositionControl(boolean positionControl){ entityTeamRoundData.PositionControl = positionControl; }
    public void setClimb(int climb){ entityTeamRoundData.Climb = climb; }
    public void setBrokeDown(boolean brokeDown){ entityTeamRoundData.BrokeDown = brokeDown; }
    public void setFinalStage(int finalStage){ entityTeamRoundData.FinalStage = finalStage; }
    public void setNotes(String notes){ entityTeamRoundData.Notes = notes; }
    public void setRateShooting(int rateShooting){ entityTeamRoundData.RateShooting = rateShooting; }
    public void setRateClimb(int rateClimb){ entityTeamRoundData.RateClimb = rateClimb; }
    public void setRateWheel(int rateWheel){ entityTeamRoundData.RateWheel = rateWheel; }
    public void setRateAuton(int rateAuton){ entityTeamRoundData.RateAuton = rateAuton; }
    public void setRateDiver(int rateDiver){ entityTeamRoundData.RateDiver = rateDiver; }
    public void setWouldPick(boolean wouldPick){ entityTeamRoundData.WouldPick = wouldPick; }
    public void setShootingLocation1(boolean shootingLocation1){ entityTeamData.ShootingLocation1 = shootingLocation1; }
    public void setShootingLocation2(boolean shootingLocation2){ entityTeamData.ShootingLocation2 = shootingLocation2; }
    public void setShootingLocation3(boolean shootingLocation3){ entityTeamData.ShootingLocation3 = shootingLocation3; }
    public void setStartLocationLeft(boolean startLocationLeft){ entityTeamData.StartLocationLeft = startLocationLeft; }
    public void setStartLocationCenter(boolean startLocationCenter){ entityTeamData.StartLocationCenter = startLocationCenter; }
    public void setStartLocationRight(boolean startLocationRight){ entityTeamData.StartLocationRight = startLocationRight; }
    // Set Functions End
    // This is a helper function to setup DB and DAOs.
    private void startUpDb(){
        // get room (db)
        if(db == null){
            db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDb")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
            // TBD: figure out how to allow for "Non-destructive Migrations" of the ROOM DB
            // for when the version changes
        }
        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = db.daoTeamRoundData();
        }
        if(daoScouterName == null){
            daoScouterName = db.daoScouterName();
            // TBD: this is an example call that should be removed in final app.
            // it's just here to make sure there's some sample scouter data in the DB.
            addSampleScouterNames();
        }
        if(daoTeamData == null){
            daoTeamData = db.daoTeamData();
            // TBD: this is an example call that should be removed in final app.
            // it's just here to make sure there's some sample team data in the DB.
            addSampleTeamNumbers();
            // TBD: this is an example call that should be removed in final app.
            // it's just here to make sure there's some sample team round data in the DB.
            addSampleTeamRoundData();
        }
    }

    // Create a new TeamRoundData entity structure.
    // This should only be called from the login activity.
    // And possibly some of the DB error handlers.
    protected void newTeamRoundData() {
        // make sure DB started
        startUpDb();

        // create a new empty record.
        entityTeamRoundData = new EntityTeamRoundData();
        entityTeamRoundData.TeamNumber = -1;
        entityTeamRoundData.RoundNumber = -1;
        entityTeamRoundData.Scouter = "Unknown";
        entityTeamRoundData.TeamColor = "Blue";
        entityTeamRoundData.AutonHighScore = 0;
        entityTeamRoundData.AutonLowScore = 0;
        entityTeamRoundData.AutonPickUp = 0;
        entityTeamRoundData.TeleopHighScore = 0;
        entityTeamRoundData.TeleopLowScore = 0;
        entityTeamRoundData.TeleopPickUp = 0;
        entityTeamRoundData.RotationControl = false;
        entityTeamRoundData.PositionControl = false;
        entityTeamRoundData.Climb = 0;
        entityTeamRoundData.BrokeDown = false;
        entityTeamRoundData.FinalStage = 0;
        entityTeamRoundData.Notes = "";
        entityTeamRoundData.RateShooting = 0;
        entityTeamRoundData.RateClimb = 0;
        entityTeamRoundData.RateWheel = 0;
        entityTeamRoundData.RateAuton = 0;
        entityTeamRoundData.RateDiver = 0;
        entityTeamRoundData.WouldPick = false;

        // reset member variables for primary key
        TeamNumber = -1;
        RoundNumber = -1;
    }

    // Create a new ScouterName entity structure.
    protected void newScouterName(){
        // make sure DB started
        startUpDb();

        // create a new empty record.
        entityScouterName = new EntityScouterName();
        entityScouterName.ScouterName = "";
    }

    // Create a new TeamData entity structure.
    protected void newTeamData() {
        // make sure DB started
        startUpDb();

        // create a new empty record.
        entityTeamData = new EntityTeamData();
        entityTeamData.TeamNumber = -1;
        entityTeamData.TeamName = "";
        entityTeamData.Scouter = "Unknown";
        entityTeamData.RobotWeight = -1;
        entityTeamData.ShootingLocation1 = false;
        entityTeamData.ShootingLocation2 = false;
        entityTeamData.ShootingLocation3 = false;
        entityTeamData.StartLocationLeft = false;
        entityTeamData.StartLocationCenter = false;
        entityTeamData.StartLocationRight = false;

        // reset member variable for primary key
        TeamNumber = -1;
    }

    // Based on the current TeamNumber and RoundNumber, load any previous team round data.
    // This should be called from the OnCreate of all match scouting activities except login.
    protected void refreshTeamRoundData(){
        // make sure DB started
        startUpDb();

        // TeamNumber, RoundNumber should be set to valid values from login screen!
        if((TeamNumber > 0) && (RoundNumber > 0)) {
            try {
                entityTeamRoundData = daoTeamRoundData.getRecord(TeamNumber, RoundNumber);
            } catch (Exception e) {
                e.printStackTrace();
                // The record for that TeamNumber/RoundNumber doesn't exist.
                // entityTeamRoundData will be null and the code below
                // will create a new empty record.
            }
        }
        if(entityTeamRoundData == null){
            // This shouldn't normally happen, as the record should be created during login,
            // but if it does, create an empty record and zero everything out
            // TBD:  should we send them back to the login activity if this does happen?
            newTeamRoundData();
        }
    }

    // Save the current team round data record to the DB.
    // This should be called from the OnPause of *all* match scouting activities.
    protected void saveTeamRoundData(){
        // make sure DB started
        startUpDb();

        // TeamNumber, RoundNumber should be set to valid values from login activity screen!
        if((entityTeamRoundData != null) && (TeamNumber > 0) && (RoundNumber > 0)) {
            // this will insert a new record, or replace the matching record
            daoTeamRoundData.insert(entityTeamRoundData);
        } else {
            // This shouldn't typically happen, as the record should be created during login.
            // However, as Android can suspend, terminate, destroy *any* activity at *any*
            // time for a lot reasons (triggering the OnPause), we need to be
            // prepared that TeamNumber or RoundNumber might not be valid.
            // TBD:  should we send them back to the login activity if this does happen?
        }
    }

    // based on the current TeamNumber, load any previous team data.
    protected void refreshTeamData(){
        // make sure DB started
        startUpDb();

        // TeamNumber should be set to valid values
        if(TeamNumber > 0) {
            try {
                entityTeamData = daoTeamData.getRecord(TeamNumber);
            } catch (Exception e) {
                e.printStackTrace();
                // The record for that TeamNumber doesn't exist.
                // entityTeamData will be null and the code below
                // will create a new empty record.
            }
        }
        if(entityTeamData == null){
            // This shouldn't EVER happen, as the team data record should be created
            // BEFORE the event.  If it does, create an empty record and zero everything out
            newTeamData();
        }
    }

    // Save the current team data record to the DB.
    protected void saveTeamData(){
        // make sure DB started
        startUpDb();

        if((entityTeamData != null) && (TeamNumber > 0)) {
            // this will insert a new record, or replace the matching record
            daoTeamData.insert(entityTeamData);
        } else {
            // This shouldn't typically happen, as the record should be created during login.
            // However, as Android can suspend, terminate, destroy *any* activity at *any*
            // time for a lot reasons (triggering the OnPause), we need to be
            // prepared that TeamNumber or RoundNumber might not be valid.
            // TBD:  should we send them back to the login activity if this does happen?
        }
    }

    public void addScouterName(String scouterName){
        // make sure DB started
        startUpDb();
        if(entityScouterName == null) {
            entityScouterName = new EntityScouterName();
        }
        entityScouterName.ScouterName = scouterName;
        daoScouterName.insert(entityScouterName);
    }

    // This returns the scouter names as a list which is needed for spinner
    public List<String> getAllScouterNamesAsList() {
        // make sure DB started
        startUpDb();
        return daoScouterName.getAllScouterNamesAsList();
    }

    public List<String> getAllTeamNumbersAsList() {
        // make sure DB started
        startUpDb();

        int[] teamNumbers = daoTeamData.getAllTeamNumbers();
        List<String> teamNumbersAsStrings = new ArrayList<String>();

        for( int teamNumber : teamNumbers)
        {
            String teamNumberAsString = Integer.toString(teamNumber);
            teamNumbersAsStrings.add(teamNumberAsString);
        }

        return teamNumbersAsStrings;
    }

    // TBD: example of adding round data records
    private void addSampleTeamRoundData() {
        if(entityTeamRoundData == null) {
            newTeamRoundData();
        }

        // we're going to generate some random ints
        Random r = new Random();

        // create a list of 40 random team numbers.
        // first get any team numbers that exist in the current round data
        List<Integer> teamNumbers = daoTeamRoundData.getAllTeamNumbersAsList();
        // then include any numbers in our sampleTeamNumbers array not already in list
        for (int teamNumber : sampleTeamNumbers) {
            if (!teamNumbers.contains(teamNumber)) {
                teamNumbers.add(teamNumber);
            }
        }
        // lastly, add random team numbers until we have 40
        while (teamNumbers.size() < 40) {
            int teamNumber = r.nextInt(8000) + 1; // 1-8000
            if (!teamNumbers.contains(teamNumber)) {
                teamNumbers.add(teamNumber);
            }
        }

        // a typical event has 60 rounds with 6 teams per round
        int j = 0; // index of team number
        for(int roundNumber = 1; roundNumber < 61; roundNumber++ ) {
            for(int i = 0; i < 6; i++ ) {
                // get team number for this loop
                int teamNumber = teamNumbers.get(j);
                // and go to the next team number for next loop
                j++;
                if (j>=teamNumbers.size()) j=0;

                entityTeamRoundData.TeamNumber = teamNumber;
                entityTeamRoundData.RoundNumber = roundNumber;
                entityTeamRoundData.Scouter = sampleScouters[r.nextInt(sampleScouters.length)];
                entityTeamRoundData.TeamColor = (i<3) ? "Blue" : "Red"; // 3 blue, 3 red
                entityTeamRoundData.AutonHighScore = r.nextInt(3) + 1; // 1-3
                entityTeamRoundData.AutonLowScore = r.nextInt(3) + 1; // 1-3
                entityTeamRoundData.AutonPickUp = r.nextInt(3) + 1; // 1-3
                entityTeamRoundData.TeleopHighScore = r.nextInt(15) + 1; // 1-15
                entityTeamRoundData.TeleopLowScore = r.nextInt(15) + 1; // 1-15
                entityTeamRoundData.TeleopPickUp = r.nextInt(15) + 1; // 1-15
                entityTeamRoundData.RotationControl = (r.nextInt(2)==0) ? false : true;
                entityTeamRoundData.PositionControl = (r.nextInt(2)==0) ? false : true;
                entityTeamRoundData.Climb = r.nextInt(3) + 1; // 1-3
                entityTeamRoundData.BrokeDown = (r.nextInt(2)==0) ? false : true;
                entityTeamRoundData.FinalStage = r.nextInt(3) + 1; // 1-3
                entityTeamRoundData.Notes = "";
                entityTeamRoundData.RateShooting = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateClimb = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateWheel = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateAuton = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateDiver = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.WouldPick = (r.nextInt(2)==0) ? false : true;
                daoTeamRoundData.insert(entityTeamRoundData);
            }
        }
    }

    // TBD: example of adding ScouterNames
    private void addSampleScouterNames() {
        if(entityScouterName == null) {
            entityScouterName = new EntityScouterName();
        }
        for (String scouter: sampleScouters) {
            entityScouterName.ScouterName = scouter;
            daoScouterName.insert(entityScouterName);
        }
    }

    // TBD: example of adding team data
    private void addSampleTeamNumbers() {
        if (entityTeamData == null) {
            entityTeamData = new EntityTeamData();
        }
        // we're going to generate some random ints
        Random r = new Random();
        for (int teamNumber : sampleTeamNumbers) {
            entityTeamData.TeamNumber = teamNumber;
            entityTeamData.TeamName = "foo";
            entityTeamData.Scouter = sampleScouters[r.nextInt(sampleScouters.length)];
            entityTeamData.RobotWeight = r.nextInt(90) + 30; // 30-120
            daoTeamData.insert(entityTeamData);
        }
    }

    // TBD: files created programatically in downloads folder may not
    // appear immediately to other system utilities.  This sends out
    // a notification to the system about it.
    private void makeCreatedFileVisibleInDownloads(String downloadFilename) {
        File downloadFile = new File(downloadFilename);
        String mimeType = "text/csv";
        if (false) { // (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // You can add more columns.. Complete list of columns can be found at
            // https://developer.android.com/reference/android/provider/MediaStore.Downloads
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.TITLE, downloadFile.getName());
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, downloadFile.getName());
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
            contentValues.put(MediaStore.MediaColumns.SIZE, downloadFile.length());
            // If you downloaded to a specific folder inside "Downloads" folder
            //contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + File.separator + "Temp");
            // Insert into the database
            ContentResolver database = getContentResolver();
            //database.insert(MediaStore.MediaColumns.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                downloadManager.addCompletedDownload(downloadFilename, downloadFilename, true,
                        mimeType, downloadFile.getAbsolutePath(), downloadFile.length(), true);
            }
        }
    }

    public void exportTeamRoundData(){
        try{
            // starts up the data base
            startUpDb();
            //Makes the filepath
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm");
            String curDate = simpleDateFormat.format(Calendar.getInstance().getTime());
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;
            String filePath =baseDir + File.separator + curDate + "TeamRoundData" + androidId + ".csv";
            //
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));
            //
            EntityTeamRoundData[] teamRoundDatas = daoTeamRoundData.getAllTeamRoundData();
            //
            for (EntityTeamRoundData teamRoundData: teamRoundDatas){
                String[] csvLine = {
                        Integer.toString(teamRoundData.TeamNumber),
                        Integer.toString(teamRoundData.RoundNumber),
                        teamRoundData.Scouter,
                        teamRoundData.TeamColor,
                        Integer.toString(teamRoundData.AutonHighScore),
                        Integer.toString(teamRoundData.AutonLowScore),
                        Integer.toString(teamRoundData.AutonPickUp),
                        Boolean.toString(teamRoundData.AutonStartLine),
                        Integer.toString(teamRoundData.TeleopHighScore),
                        Integer.toString(teamRoundData.TeleopLowScore),
                        Integer.toString(teamRoundData.TeleopPickUp),
                        Boolean.toString(teamRoundData.RotationControl),
                        Boolean.toString(teamRoundData.PositionControl),
                        Integer.toString(teamRoundData.Climb),
                        Boolean.toString(teamRoundData.BrokeDown),
                        Integer.toString(teamRoundData.FinalStage),
                        teamRoundData.Notes,
                        Integer.toString(teamRoundData.RateClimb),
                        Integer.toString(teamRoundData.RateWheel),
                        Integer.toString(teamRoundData.RateAuton),
                        Integer.toString(teamRoundData.RateDiver),
                        Boolean.toString(teamRoundData.WouldPick),
                };
                writer.writeNext(csvLine);
            }
            writer.close();
            makeCreatedFileVisibleInDownloads(filePath);
        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error creating CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    public void importTeamRoundData(){
        try{
            startUpDb();

            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;
            String filePath = baseDir + File.separator + "TeamRoundData-"+androidId+".csv";
            //
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] csvLine;
            entityTeamRoundData = new EntityTeamRoundData();
            while ((csvLine = reader.readNext()) != null){
                int teamNumber = Integer.valueOf(csvLine[0]);
                int roundNumber = Integer.valueOf(csvLine[1]);
                String scouter = csvLine[2];
                String teamColor = csvLine[3];
                int autonHighScore = Integer.valueOf(csvLine[4]);
                int autonLowScore = Integer.valueOf(csvLine[5]);
                int autonPickUp = Integer.valueOf(csvLine[6]);
                boolean autonStartLine = Boolean.valueOf(csvLine[7]);
                int teleopHighScore = Integer.valueOf(csvLine[8]);
                int teleopLowScore = Integer.valueOf(csvLine[9]);
                int teleopPickUp = Integer.valueOf(csvLine[10]);
                boolean rotationControl = Boolean.valueOf(csvLine[11]);
                boolean positionControl = Boolean.valueOf(csvLine[12]);
                int climb = Integer.valueOf(csvLine[13]);
                boolean brokeDown = Boolean.valueOf(csvLine[14]);
                int finalStage = Integer.valueOf(csvLine[15]);
                String notes = csvLine[16];
                int rateShooting = Integer.valueOf(csvLine[17]);
                int rateClimb = Integer.valueOf(csvLine[18]);
                int rateWheel = Integer.valueOf(csvLine[19]);
                int rateAuton = Integer.valueOf(csvLine[20]);
                int rateDiver = Integer.valueOf(csvLine[21]);
                boolean wouldPick = Boolean.valueOf(csvLine[22]);
                //
                entityTeamRoundData.TeamNumber = teamNumber;
                entityTeamRoundData.RoundNumber = roundNumber;
                entityTeamRoundData.Scouter = scouter;
                entityTeamRoundData.TeamColor = teamColor;
                entityTeamRoundData.AutonHighScore = autonHighScore;
                entityTeamRoundData.AutonLowScore = autonLowScore;
                entityTeamRoundData.AutonPickUp = autonPickUp;
                entityTeamRoundData.AutonStartLine = autonStartLine;
                entityTeamRoundData.TeleopHighScore = teleopHighScore;
                entityTeamRoundData.TeleopLowScore = teleopLowScore;
                entityTeamRoundData.TeleopPickUp = teleopPickUp;
                entityTeamRoundData.RotationControl = rotationControl;
                entityTeamRoundData.PositionControl = positionControl;
                entityTeamRoundData.Climb = climb;
                entityTeamRoundData.BrokeDown = brokeDown;
                entityTeamRoundData.FinalStage = finalStage;
                entityTeamRoundData.Notes = notes;
                entityTeamRoundData.RateShooting = rateShooting;
                entityTeamRoundData.RateClimb = rateClimb;
                entityTeamRoundData.RateWheel = rateWheel;
                entityTeamRoundData.RateAuton = rateAuton;
                entityTeamRoundData.RateDiver = rateDiver;
                entityTeamRoundData.WouldPick = wouldPick;
            }
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error reading CSV file", Toast.LENGTH_SHORT).show();
        }
    }


    // TBD: example writing to CSV
    public void exportScouterNames() {
        try {
            // make sure DB started
            startUpDb();

            // TBD: eventually we want to date/time stamp each export
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm");
            String curDate = simpleDateFormat.format(Calendar.getInstance().getTime());
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;
            String fileName = curDate + "-ScouterNames-"+androidId+".csv";
            String filePath = baseDir + File.separator + fileName;

            // we are exporting everything, so recreate each time
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));

            // get all scouters
            String[] scouters = daoScouterName.getAllScouterNames();
            // for each scouter name in the ScouterNames table returned via the select
            for (String scouter: scouters) {
                String[] csvLine = { scouter };
                writer.writeNext(csvLine);
            }
            writer.close();
            makeCreatedFileVisibleInDownloads(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    // TBD: example reading from CSV
    public void importScouterNames() {
        try {
            // make sure DB started
            startUpDb();

            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;
            String fileName = "ScouterNames-"+androidId+".csv";
            String filePath = baseDir + File.separator + fileName;

            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] csvLine;
            while ((csvLine = reader.readNext()) != null) {
                // csvLine[] is an array of values parsed from from the CSV line
                String scouter = csvLine[0];
                if (!scouter.isEmpty()) {
                    if(entityScouterName == null) {
                        entityScouterName = new EntityScouterName();
                    }
                    entityScouterName.ScouterName = scouter;
                    daoScouterName.insert(entityScouterName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading CSV file", Toast.LENGTH_SHORT).show();
        }
    }

}
