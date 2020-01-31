package com.example.chaos.scouting2020;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;
import android.arch.persistence.room.Room;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

public class ScoutingApplication extends Application {

    // database members
    ScoutingDatabase db = null;
    DaoTeamRoundData daoTeamRoundData = null;
    DaoScouterName daoScouterName = null;
    EntityTeamRoundData entityTeamRoundData = null;
    EntityScouterName entityScouterName = null;

    // primary key data
    private int TNumber = -1;
    private int QRNumber = -1;

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
    public String getClimb(){
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
    // Get Functions End

    // Set Functions
    public void setTeamNumber(int teamNumber){
        // should only be set from login activity
        TNumber = teamNumber;
        entityTeamRoundData.TeamNumber = teamNumber;
    }
    public void setRoundNumber(int roundNumber){
        // should only be set from login activity
        QRNumber = roundNumber;
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
    public void setClimb(String climb){ entityTeamRoundData.Climb = climb; }
    public void setBrokeDown(boolean brokeDown){ entityTeamRoundData.BrokeDown = brokeDown; }
    public void setFinalStage(int finalStage){ entityTeamRoundData.FinalStage = finalStage; }
    public void setNotes(String notes){ entityTeamRoundData.Notes = notes; }
    public void setRateShooting(int rateShooting){ entityTeamRoundData.RateShooting = rateShooting; }
    public void setRateClimb(int rateClimb){ entityTeamRoundData.RateClimb = rateClimb; }
    public void setRateWheel(int rateWheel){ entityTeamRoundData.RateWheel = rateWheel; }
    public void setRateAuton(int rateAuton){ entityTeamRoundData.RateAuton = rateAuton; }
    public void setRateDiver(int rateDiver){ entityTeamRoundData.RateDiver = rateDiver; }
    public void setWouldPick(boolean wouldPick){ entityTeamRoundData.WouldPick = wouldPick; }
    // Set Functions End

    // This is a helper function to setup DB and DAOs.
    private void StartUpDb(){
        // get room (db)
        if(db == null){
            db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDb")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
            // TBD: figure out how to allow for "Destructive Migrations" of the ROOM DB
            // for when the version changes
        }
        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = db.daoTeamRoundData();
        }
        if(daoScouterName == null){
            daoScouterName = db.daoScouterName();
        }
    }

    // Create a new TeamRoundData entity structure.
    // This should only be called from the login activity.
    // And possibly some of the DB error handlers.
    protected void newTeamRoundData() {
        // make sure DB started
        StartUpDb();

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
        entityTeamRoundData.Climb = "";
        entityTeamRoundData.BrokeDown = false;
        entityTeamRoundData.FinalStage = 0;
        entityTeamRoundData.Notes = "";
        entityTeamRoundData.RateShooting = 0;
        entityTeamRoundData.RateClimb = 0;
        entityTeamRoundData.RateWheel = 0;
        entityTeamRoundData.RateAuton = 0;
        entityTeamRoundData.RateDiver = 0;
        entityTeamRoundData.WouldPick = false;

        // reset member variables
        TNumber = -1;
        QRNumber = -1;
    }

    protected void newScouterName(){
        StartUpDb();
        entityScouterName = new EntityScouterName();
        entityScouterName.ScouterName = "";
    }

    // Based on the current TNumber and QRNumber, load any previous team round data.
    // This should be called from the OnCreate of all activities except login.
    protected void refreshTeamRoundData(){
        // make sure DB started
        StartUpDb();

        // TNumber, QRNumber should be set to valid values from login screen!
        if((TNumber > 0) && (QRNumber > 0)) {
            try {
                entityTeamRoundData = daoTeamRoundData.getRecord(TNumber, QRNumber);
            } catch (Exception e) {
                e.printStackTrace();
                // The record for that TNumber/QRNumber doesn't exist.
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
    // This should be called from the OnPause of *all* activities.
    protected void saveTeamRoundData(){
        // make sure DB started
        StartUpDb();

        // TNumber, QRNumber should be set to valid values from login screen!
        if((entityTeamRoundData != null) && (TNumber > 0) && (QRNumber > 0)) {
            // this will insert a new record, or replace the matching record
            daoTeamRoundData.insert(entityTeamRoundData);
        } else {
            // This shouldn't typically happen, as the record should be created during login.
            // However, as Android can suspend, terminate, destroy *any* activity at *any*
            // time for a lot reasons (triggering the OnPause), we need to be
            // prepared that TNumber or QRNumber might not be valid.
            // TBD:  should we send them back to the login activity if this does happen?
        }
    }

    // TBD: example of adding round data records
    public void AddAllRounds() {
        if(entityScouterName == null) {
            newTeamRoundData();
        }
        for(int teamNumber = 1; teamNumber < 75; teamNumber++ ) {
            for(int roundNumber = 1; roundNumber < 61; roundNumber++ ) {
                entityTeamRoundData.TeamNumber = teamNumber;
                entityTeamRoundData.RoundNumber = roundNumber;
                daoTeamRoundData.insert(entityTeamRoundData);
            }
        }
    }

    public void AddScouterName(String scouterName){
        if(entityScouterName == null) {
            entityScouterName = new EntityScouterName();
        }
        entityScouterName.ScouterName = scouterName;
        daoScouterName.insert(entityScouterName);
    }

    // TBD: example of adding ScouterNames
    public void AddAllScouterNames() {
        String[] scouters = { "Allen Z.", "Ben Y.", "Clara X.", "Dan W.", "Ed V." };
        if(entityScouterName == null) {
            entityScouterName = new EntityScouterName();
        }
        for (String scouter: scouters) {
            entityScouterName.ScouterName = scouter;
            daoScouterName.insert(entityScouterName);
        }
    }

    // TBD: example writing to CSV
    public void exportScouterNames() {
        try {
            // TBD: eventually we want to date/time stamp each export
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm");
            //String curDate = simpleDateFormat.format(Calendar.getInstance().getTime());
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;
            String fileName = "ScouterNames-"+androidId+".csv";
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
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    // TBD: example reading from CSV
    public void importScouterNames() {
        try {
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
