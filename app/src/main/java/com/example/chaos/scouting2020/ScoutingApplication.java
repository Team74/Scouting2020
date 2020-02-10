package com.example.chaos.scouting2020;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
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
    private String[] sampleDriveBases = { "Tank", "Mecanum", "Omni", "Swerve", "Other" };
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

    // Get Functions
    // Get Functions for Team Round Data
    public int getTeamNumber() {
        return entityTeamRoundData.TeamNumber;
    }
    public int getRoundNumber() {
        return entityTeamRoundData.RoundNumber;
    }
    public String getScouterName() {
        return entityTeamRoundData.Scouter;
    }
    public String getTeamColor() {
        return entityTeamRoundData.TeamColor;
    }
    public int getAutonHighScore() {
        return entityTeamRoundData.AutonHighScore;
    }
    public int getAutonLowScore() {
        return entityTeamRoundData.AutonLowScore;
    }
    public int getAutonPickUp() {
        return entityTeamRoundData.AutonPickUp;
    }
    public boolean getAutonStartLine() {
        return entityTeamRoundData.AutonStartLine;
    }
    public int getTeleopHighScore() {
        return entityTeamRoundData.TeleopHighScore;
    }
    public int getTeleopLowScore() {
        return entityTeamRoundData.TeleopLowScore;
    }
    public int getTeleopPickUp() {
        return entityTeamRoundData.TeleopPickUp;
    }
    public boolean getRotationControl() {
        return entityTeamRoundData.RotationControl;
    }
    public boolean getPositionControl() {
        return entityTeamRoundData.PositionControl;
    }
    public int getClimb() {
        return entityTeamRoundData.Climb;
    }
    public boolean getBrokeDown() {
        return entityTeamRoundData.BrokeDown;
    }
    public int getFinalStage() {
        return entityTeamRoundData.FinalStage;
    }
    public String getNotes() {
        return entityTeamRoundData.Notes;
    }
    public int getRateShooting() {
        return entityTeamRoundData.RateShooting;
    }
    public int getRateClimb() {
        return entityTeamRoundData.RateClimb;
    }
    public int getRateWheel() {
        return entityTeamRoundData.RateWheel;
    }
    public int getRateAuton() {
        return entityTeamRoundData.RateAuton;
    }
    public int getRateDriver() {
        return entityTeamRoundData.RateDriver;
    }
    public boolean getWouldPick() {
        return entityTeamRoundData.WouldPick;
    }
    // Get Functions for Team Data
    public String getRobotDriveBaseType() { return entityTeamData.RobotDriveBaseType; }
    public boolean getShootingLocation1() { return entityTeamData.ShootingLocation1; }
    public boolean getShootingLocation2() { return entityTeamData.ShootingLocation2; }
    public boolean getShootingLocation3() { return entityTeamData.ShootingLocation3; }
    public boolean getStartLocationLeft() { return entityTeamData.StartLocationLeft; }
    public boolean getStartLocationCenter() { return entityTeamData.StartLocationCenter; }
    public boolean getStartLocationRight() { return entityTeamData.StartLocationRight; }
    public int getRobotWeight() { return entityTeamData.RobotWeight; }
    public String getPitScoutingNotes() { return entityTeamData.PitScoutingNotes; }
    public String getPitScouter() { return entityTeamData.PitScouter; }
    // Get Functions End

    // Set Functions
    // Set Functions for Team Round Data
    public void setTeamNumber(int teamNumber) {
        // should only be set from login activity
        TeamNumber = teamNumber;
        entityTeamRoundData.TeamNumber = teamNumber;
    }
    public void setRoundNumber(int roundNumber) {
        // should only be set from login activity
        RoundNumber = roundNumber;
        entityTeamRoundData.RoundNumber = roundNumber;
    }
    public void setScouter(String scouter) {
        // should only be set from login activity
        entityTeamRoundData.Scouter = scouter;
    }
    public void setTeamColor(String color) {
        // should only be set from login activity
        entityTeamRoundData.TeamColor = color;
    }
    public void setAutonHighScore(int autonHighScore) { entityTeamRoundData.AutonHighScore = autonHighScore; }
    public void setAutonLowScore(int autonLowScore) { entityTeamRoundData.AutonLowScore = autonLowScore; }
    public void setAutonPickUp(int autonPickUp) { entityTeamRoundData.AutonPickUp = autonPickUp; }
    public void setAutonStartLine(boolean autonStartLine) { entityTeamRoundData.AutonStartLine = autonStartLine; }
    public void setTeleopHighScore(int teleopHighScore) { entityTeamRoundData.TeleopHighScore = teleopHighScore; }
    public void setTeleopLowScore(int teleopLowScore) { entityTeamRoundData.TeleopLowScore = teleopLowScore; }
    public void setTeleopPickUp(int teleopPickUp) { entityTeamRoundData.TeleopPickUp = teleopPickUp; }
    public void setRotationControl(boolean rotationControl) { entityTeamRoundData.RotationControl = rotationControl; }
    public void setPositionControl(boolean positionControl) { entityTeamRoundData.PositionControl = positionControl; }
    public void setClimb(int climb) { entityTeamRoundData.Climb = climb; }
    public void setBrokeDown(boolean brokeDown) { entityTeamRoundData.BrokeDown = brokeDown; }
    public void setFinalStage(int finalStage) { entityTeamRoundData.FinalStage = finalStage; }
    public void setNotes(String notes) { entityTeamRoundData.Notes = notes; }
    public void setRateShooting(int rateShooting) { entityTeamRoundData.RateShooting = rateShooting; }
    public void setRateClimb(int rateClimb) { entityTeamRoundData.RateClimb = rateClimb; }
    public void setRateWheel(int rateWheel) { entityTeamRoundData.RateWheel = rateWheel; }
    public void setRateAuton(int rateAuton) { entityTeamRoundData.RateAuton = rateAuton; }
    public void setRateDriver(int rateDriver) { entityTeamRoundData.RateDriver = rateDriver; }
    public void setWouldPick(boolean wouldPick) { entityTeamRoundData.WouldPick = wouldPick; }
    // Set Functions for Team Data
    public void setRobotDriveBaseType(String robotDriveBaseType) { entityTeamData.RobotDriveBaseType = robotDriveBaseType; }
    public void setShootingLocation1(boolean shootingLocation1) { entityTeamData.ShootingLocation1 = shootingLocation1; }
    public void setShootingLocation2(boolean shootingLocation2) { entityTeamData.ShootingLocation2 = shootingLocation2; }
    public void setShootingLocation3(boolean shootingLocation3) { entityTeamData.ShootingLocation3 = shootingLocation3; }
    public void setStartLocationLeft(boolean startLocationLeft) { entityTeamData.StartLocationLeft = startLocationLeft; }
    public void setStartLocationCenter(boolean startLocationCenter) { entityTeamData.StartLocationCenter = startLocationCenter; }
    public void setStartLocationRight(boolean startLocationRight) { entityTeamData.StartLocationRight = startLocationRight; }
    public void setRobotWeight(int robotWeight) { entityTeamData.RobotWeight = robotWeight; }
    public void setPitScoutingNotes(String pitScoutingNotes) { entityTeamData.PitScoutingNotes = pitScoutingNotes; }
    public void setPitScouter(String pitScouter) { entityTeamData.PitScouter = pitScouter; }
    // Set Functions End

    // This is a helper function to setup DB and DAOs.
    public void startUpDb() {
        // get room (db)
        if(db == null) {
            db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDb")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
            // TBD: figure out how to allow for "Non-destructive Migrations" of the ROOM DB
            // for when the version changes
        }
        // get data access objects (tables)
        if(daoTeamRoundData == null) {
            daoTeamRoundData = db.daoTeamRoundData();
        }
        if(daoScouterName == null) {
            daoScouterName = db.daoScouterName();
            // TBD: this is an example call that should be removed in final app.
            // it's just here to make sure there's some sample scouter data in the DB.
            addSampleScouterNames();
        }
        if(daoTeamData == null) {
            daoTeamData = db.daoTeamData();
            // TBD: this is an example call that should be removed in final app.
            // it's just here to make sure there's some sample team data in the DB.
            addSampleTeamNumbers();
            // TBD: this is an example call that should be removed in final app.
            // it's just here to make sure there's some sample team round data in the DB.
            addSampleTeamRoundData();
        }
    }

    public DaoTeamRoundData getDaoTeamRoundData() {
        // make sure DB started
        startUpDb();

        return daoTeamRoundData;
    }

    public DaoTeamData getDaoTeamData() {
        // make sure DB started
        startUpDb();

        return daoTeamData;
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
        entityTeamRoundData.RateDriver = 0;
        entityTeamRoundData.WouldPick = false;

        // reset member variables for primary key
        TeamNumber = -1;
        RoundNumber = -1;
    }

    // Create a new ScouterName entity structure.
    protected void newScouterName() {
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
        entityTeamData.PitScouter = "";
        entityTeamData.RobotWeight = -1;
        entityTeamData.ShootingLocation1 = false;
        entityTeamData.ShootingLocation2 = false;
        entityTeamData.ShootingLocation3 = false;
        entityTeamData.StartLocationLeft = false;
        entityTeamData.StartLocationCenter = false;
        entityTeamData.StartLocationRight = false;
        entityTeamData.PitScoutingNotes = "";
        entityTeamData.RobotDriveBaseType = "Tank";

        // reset member variable for primary key
        TeamNumber = -1;
    }

    // Based on the current TeamNumber and RoundNumber, load any previous team round data.
    // This should be called from the OnCreate of all match scouting activities except login.
    protected void refreshTeamRoundData() {
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
        if(entityTeamRoundData == null) {
            // This shouldn't normally happen, as the record should be created during login,
            // but if it does, create an empty record and zero everything out
            // TBD:  should we send them back to the login activity if this does happen?
            newTeamRoundData();
        }
    }

    // Save the current team round data record to the DB.
    // This should be called from the OnPause of *all* match scouting activities.
    protected void saveTeamRoundData() {
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
    protected void refreshTeamData() {
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
        if(entityTeamData == null) {
            // This shouldn't EVER happen, as the team data record should be created
            // BEFORE the event.  If it does, create an empty record and zero everything out
            newTeamData();
        }
    }

    // Save the current team data record to the DB.
    protected void saveTeamData() {
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

    public void addScouterName(String scouterName) {
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
            if (!teamNumbers.contains(teamNumber)) { // no dupes!
                teamNumbers.add(teamNumber);
            }
        }
        // lastly, add random team numbers until we have 40
        while (teamNumbers.size() < 40) {
            int teamNumber = r.nextInt(8000) + 1; // 1-8000
            if (!teamNumbers.contains(teamNumber)) { // no dupes!
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
                entityTeamRoundData.TeleopLowScore = r.nextInt(10) + 1; // 1-15
                entityTeamRoundData.TeleopPickUp = r.nextInt(20) + 1; // 1-15
                entityTeamRoundData.RotationControl = (r.nextInt(2)==0) ? false : true;
                entityTeamRoundData.PositionControl = (r.nextInt(2)==0) ? false : true;
                entityTeamRoundData.Climb = r.nextInt(3) + 1; // 1-3
                entityTeamRoundData.BrokeDown = (r.nextInt(15)==0) ? true : false;  // 1 in 15
                entityTeamRoundData.FinalStage = r.nextInt(3) + 1; // 1-3
                entityTeamRoundData.Notes = "";
                entityTeamRoundData.RateShooting = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateClimb = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateWheel = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateAuton = r.nextInt(5) + 1; // 1-5
                entityTeamRoundData.RateDriver = r.nextInt(5) + 1; // 1-5
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

        // create a list of 40 random team numbers.
        // first get any team numbers that exist in the current round data
        List<Integer> teamNumbers = daoTeamRoundData.getAllTeamNumbersAsList();
        // then include any numbers in our sampleTeamNumbers array not already in list
        for (int teamNumber : sampleTeamNumbers) {
            if (!teamNumbers.contains(teamNumber)) { // no dupes!
                teamNumbers.add(teamNumber);
            }
        }
        // lastly, add random team numbers until we have 40
        while (teamNumbers.size() < 40) {
            int teamNumber = r.nextInt(8000) + 1; // 1-8000
            if (!teamNumbers.contains(teamNumber)) { // no dupes!
                teamNumbers.add(teamNumber);
            }
        }

        for (int teamNumber : teamNumbers) {
            entityTeamData.RobotWeight = r.nextInt(60) + 60;
            entityTeamData.TeamNumber = teamNumber;
            entityTeamData.TeamName = "foo";
            entityTeamData.PitScouter = sampleScouters[r.nextInt(sampleScouters.length)];
            entityTeamData.ShootingLocation1 = (r.nextInt(2)==0) ? false : true;
            entityTeamData.ShootingLocation2 = (r.nextInt(2)==0) ? false : true;
            entityTeamData.ShootingLocation3 = (r.nextInt(2)==0) ? false : true;
            entityTeamData.StartLocationLeft = (r.nextInt(2)==0) ? false : true;
            entityTeamData.StartLocationCenter = (r.nextInt(2)==0) ? false : true;
            entityTeamData.StartLocationRight = (r.nextInt(2)==0) ? false : true;
            entityTeamData.RobotDriveBaseType = sampleDriveBases[r.nextInt(sampleDriveBases.length)];
            daoTeamData.insert(entityTeamData);
        }
    }

    // files created programatically in downloads folder may not
    // appear immediately to other system utilities.  This sends out
    // a notification to the system about it.
    private void makeCreatedFileVisibleInDownloads(String downloadFilename) {
        File downloadFile = new File(downloadFilename);
        String mimeType = "text/csv";
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            downloadManager.addCompletedDownload(downloadFile.getName(), downloadFilename, true,
                    mimeType, downloadFile.getAbsolutePath(), downloadFile.length(), true);
        }
    }

    // export EntityTeamRoundData to CSV
    public void exportTeamRoundData(String baseDir) {
        try{
            // make sure DB started
            startUpDb();

            // makes the filepath
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
            String curDate = simpleDateFormat.format(Calendar.getInstance().getTime());
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String filePath = baseDir + File.separator + curDate + "-TeamRoundData-" + androidId + ".csv";

            // creates file and attaches CSV writer to it
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));

            // write a header record to the CSV file to aid in importing into Tableau
            String[] csvHeaderLine = {
                    "TeamNumber",
                    "RoundNumber",
                    "Scouter",
                    "TeamColor",
                    "AutonHighScore",
                    "AutonLowScore",
                    "AutonPickUp",
                    "AutonStartLine",
                    "TeleopHighScore",
                    "TeleopLowScore",
                    "TeleopPickUp",
                    "RotationControl",
                    "PositionControl",
                    "Climb",
                    "BrokeDown",
                    "FinalStage",
                    "Notes",
                    "RateShooting",
                    "RateClimb",
                    "RateWheel",
                    "RateAuton",
                    "RateDriver",
                    "WouldPick"
            };
            writer.writeNext(csvHeaderLine);

            // get all the team round data
            EntityTeamRoundData[] teamRoundDatas = daoTeamRoundData.getAllTeamRoundData();

            // for each record returned from the DB, write a line to the CSV file
            for (EntityTeamRoundData teamRoundData: teamRoundDatas) {
                String[] csvDataLine = {
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
                        Integer.toString(teamRoundData.RateShooting),
                        Integer.toString(teamRoundData.RateClimb),
                        Integer.toString(teamRoundData.RateWheel),
                        Integer.toString(teamRoundData.RateAuton),
                        Integer.toString(teamRoundData.RateDriver),
                        Boolean.toString(teamRoundData.WouldPick),
                };
                // write the CSV record to the file
                writer.writeNext(csvDataLine);
            }
            // close the CSV file and "publish" it to the system
            writer.close();
            makeCreatedFileVisibleInDownloads(filePath);
            Toast.makeText(this, "TeamRoundData CSV file successfully exported", Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating TeamRoundData CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    // import from CSV into EntityTeamRoundData
    public void importTeamRoundData(Uri teamRoundDataUri) {
        try{
            // make sure DB started
            startUpDb();

            // attach a file reader to the uri
            FileReader fileReader = new FileReader(
                this.getContentResolver()
                    .openFileDescriptor(teamRoundDataUri, "r")
                    .getFileDescriptor()
            );

            // open the file and attach a CSV reader to it
            CSVReader reader = new CSVReader(fileReader);

            // create a CSV and DB record that we will fill in
            String[] csvLine;
            entityTeamRoundData = new EntityTeamRoundData();

            // for each record returned from the CSV file, add a record to DB
            while ((csvLine = reader.readNext()) != null) {

                // check for the CSV header row and skip it
                if(csvLine[0].equals("TeamNumber")) {
                    continue;
                }

                // convert all the data strings to the appropriate DB type
                entityTeamRoundData.TeamNumber = Integer.valueOf(csvLine[0]);
                entityTeamRoundData.RoundNumber = Integer.valueOf(csvLine[1]);
                entityTeamRoundData.Scouter = csvLine[2];
                entityTeamRoundData.TeamColor = csvLine[3];
                entityTeamRoundData.AutonHighScore = Integer.valueOf(csvLine[4]);
                entityTeamRoundData.AutonLowScore = Integer.valueOf(csvLine[5]);
                entityTeamRoundData.AutonPickUp = Integer.valueOf(csvLine[6]);
                entityTeamRoundData.AutonStartLine = Boolean.valueOf(csvLine[7]);
                entityTeamRoundData.TeleopHighScore = Integer.valueOf(csvLine[8]);
                entityTeamRoundData.TeleopLowScore = Integer.valueOf(csvLine[9]);
                entityTeamRoundData.TeleopPickUp = Integer.valueOf(csvLine[10]);
                entityTeamRoundData.RotationControl = Boolean.valueOf(csvLine[11]);
                entityTeamRoundData.PositionControl = Boolean.valueOf(csvLine[12]);
                entityTeamRoundData.Climb = Integer.valueOf(csvLine[13]);
                entityTeamRoundData.BrokeDown = Boolean.valueOf(csvLine[14]);
                entityTeamRoundData.FinalStage = Integer.valueOf(csvLine[15]);
                entityTeamRoundData.Notes = csvLine[16];
                entityTeamRoundData.RateShooting = Integer.valueOf(csvLine[17]);
                entityTeamRoundData.RateClimb = Integer.valueOf(csvLine[18]);
                entityTeamRoundData.RateWheel = Integer.valueOf(csvLine[19]);
                entityTeamRoundData.RateAuton = Integer.valueOf(csvLine[20]);
                entityTeamRoundData.RateDriver = Integer.valueOf(csvLine[21]);
                entityTeamRoundData.WouldPick = Boolean.valueOf(csvLine[22]);

                // if it's a valid record...
                // are the other things would we NOT want in the DB?
                if(   (TeamNumber>0)
                        && ((RoundNumber>0) && (RoundNumber<100))) {
                    // ...add the team round data record to the DB
                    daoTeamRoundData.insert(entityTeamRoundData);
                }
            }
            // close the CSV file
            reader.close();
            Toast.makeText(this, "TeamRoundData CSV file successfully imported", Toast.LENGTH_SHORT).show();
        }catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading TeamRoundData CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    // export EntityScouterName to CSV
    public void exportScouterNames(String baseDir) {
        try {
            // make sure DB started
            startUpDb();

            // makes the filepath
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
            String curDate = simpleDateFormat.format(Calendar.getInstance().getTime());
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String filePath = baseDir + File.separator + curDate + "-ScouterNames-" + androidId + ".csv";

            // creates file and attaches CSV writer to it
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));

            // write a header record to the CSV file to aid in importing into Tableau
            String[] csvHeaderLine = {
                    "ScouterName"
            };
            writer.writeNext(csvHeaderLine);

            // get all scouters from the DB
            String[] scouters = daoScouterName.getAllScouterNames();

            // for each record returned from the DB, write a line to the CSV file
            for (String scouter: scouters) {
                String[] csvDataLine = { scouter };
                // write the CSV record to the file
                writer.writeNext(csvDataLine);
            }

            // close the CSV file and "publish" it to the system
            writer.close();
            makeCreatedFileVisibleInDownloads(filePath);
            Toast.makeText(this, "ScouterNames CSV file successfully exported", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating ScouterNames CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    // import from CSV into EntityScouterName
    public void importScouterNames(Uri scouterNamesUri) {
        try {
            // make sure DB started
            startUpDb();

            // attach a file reader to the uri
            FileReader fileReader = new FileReader(
                    this.getContentResolver()
                            .openFileDescriptor(scouterNamesUri, "r")
                            .getFileDescriptor()
            );

            // open the file and attach a CSV reader to it
            CSVReader reader = new CSVReader(fileReader);

            // create a CSV and DB record that we will fill in
            String[] csvLine;
            entityScouterName = new EntityScouterName();

            // for each record returned from the CSV file, add a record to DB
            while ((csvLine = reader.readNext()) != null) {

                // check for the CSV header row and skip it
                if(csvLine[0].equals("ScouterName")) {
                    continue;
                }

                // convert all the data strings to the appropriate DB type
                entityScouterName.ScouterName = csvLine[0];

                // if it's a valid record
                if (!entityScouterName.ScouterName.isEmpty()) {
                    // add the team round data record to the DB
                    daoScouterName.insert(entityScouterName);
                }
            }
            // close the CSV file
            reader.close();
            Toast.makeText(this, "ScouterNames CSV file successfully imported", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading ScouterNames CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    // export EntityTeamData to CSV
    public void exportTeamData(String baseDir) {
        try{
            // make sure DB started
            startUpDb();

            // makes the filepath
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
            String curDate = simpleDateFormat.format(Calendar.getInstance().getTime());
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String filePath = baseDir + File.separator + curDate + "-TeamData-" + androidId + ".csv";

            // creates file and attaches CSV writer to it
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));

            // write a header record to the CSV file to aid in importing into Tableau
            String[] csvHeaderLine = {
                "TeamNumber",
                "TeamName",
                "Scouter",
                "RobotWeight",
                "RobotDriveBaseType",
                "PitScoutingNotes",
                "ShootingLocation1",
                "ShootingLocation2",
                "ShootingLocation3",
                "StartLocationLeft",
                "StartLocationCenter",
                "StartLocationRight"
            };
            writer.writeNext(csvHeaderLine);

            // get all the team round data
            EntityTeamData[] TeamDatas = daoTeamData.getAllTeamData();

            // for each record returned from the DB, write a line to the CSV file
            for (EntityTeamData TeamData: TeamDatas) {
                String[] csvDataLine = {
                        Integer.toString(TeamData.TeamNumber),
                        TeamData.TeamName,
                        TeamData.PitScouter,
                        Integer.toString(TeamData.RobotWeight),
                        Boolean.toString(TeamData.ShootingLocation1),
                        Boolean.toString(TeamData.ShootingLocation2),
                        Boolean.toString(TeamData.ShootingLocation3),
                        Boolean.toString(TeamData.StartLocationLeft),
                        Boolean.toString(TeamData.StartLocationCenter),
                        Boolean.toString(TeamData.StartLocationRight),
                };
                // write the CSV record to the file
                writer.writeNext(csvDataLine);
            }
            // close the CSV file and "publish" it to the system
            writer.close();
            makeCreatedFileVisibleInDownloads(filePath);
            Toast.makeText(this, "TeamData CSV file successfully exported", Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating TeamData CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    // import from CSV into EntityTeamData
    public void importTeamData(Uri teamDataUri) {
        try{
            // make sure DB started
            startUpDb();

            // attach a file reader to the uri
            FileReader fileReader = new FileReader(
                    this.getContentResolver()
                            .openFileDescriptor(teamDataUri, "r")
                            .getFileDescriptor()
            );

            // open the file and attach a CSV reader to it
            CSVReader reader = new CSVReader(fileReader);

            // create a CSV and DB record that we will fill in
            String[] csvLine;
            entityTeamData = new EntityTeamData();

            // for each record returned from the CSV file, add a record to DB
            while ((csvLine = reader.readNext()) != null) {

                // check for the CSV header row and skip it
                if(csvLine[0].equals("TeamNumber")) {
                    continue;
                }

                // convert all the data strings to the appropriate DB type
                entityTeamData.TeamNumber = Integer.valueOf(csvLine[0]);
                entityTeamData.TeamName = csvLine[1];
                entityTeamData.PitScouter = csvLine[2];
                entityTeamData.RobotWeight = Integer.valueOf(csvLine[3]);
                entityTeamData.ShootingLocation1 = Boolean.valueOf(csvLine[4]);
                entityTeamData.ShootingLocation2 = Boolean.valueOf(csvLine[5]);
                entityTeamData.ShootingLocation3 = Boolean.valueOf(csvLine[6]);
                entityTeamData.StartLocationLeft = Boolean.valueOf(csvLine[7]);
                entityTeamData.StartLocationCenter = Boolean.valueOf(csvLine[8]);
                entityTeamData.StartLocationRight = Boolean.valueOf(csvLine[9]);

                // if it's a valid record...
                // are the other things would we NOT want in the DB?
                if(TeamNumber>0) {
                    // ...add the team round data record to the DB
                    daoTeamData.insert(entityTeamData);
                }
            }
            // close the CSV file
            reader.close();
            Toast.makeText(this, "TeamData CSV file successfully imported", Toast.LENGTH_SHORT).show();
        }catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading TeamData CSV file", Toast.LENGTH_SHORT).show();
        }
    }

}
