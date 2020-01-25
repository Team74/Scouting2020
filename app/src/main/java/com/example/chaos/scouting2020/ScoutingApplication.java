package com.example.chaos.scouting2020;

import android.app.Application;
import android.arch.persistence.room.Room;

public class ScoutingApplication extends Application {

    private String someVariable;
    // database members
    ScoutingDatabase db = null;
    EntityTeamRoundData entityTeamRoundData = null;
    EntityScouterName entityScouterName = null;
    DaoTeamRoundData daoTeamRoundData = null;
    DaoScouterName daoScouterName = null;

    // scouting data members
    // Login data memberes
    protected String TColor = null;
    protected String Scout = null;
    protected int TNumber = -1;
    protected int QRNumber = 0;
    //Auton data members
    protected int AutonHighGoalNumber = 0;
    protected int AutonLowGoalNumber = 0;
    protected int AutonPickUpNumber = 0;

    // Example
    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }
    // Example end

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
    // Set Funstions
    public void setTeamNumber(int teamNumber){
        entityTeamRoundData.TeamNumber = teamNumber;
    }
    public void setRoundNumber(int roundNumber){
        entityTeamRoundData.RoundNumber = roundNumber;
    }
    public void setScouter(String scouter){
        entityTeamRoundData.Scouter = scouter;
    }
    public void setAutonHighScore(int autonHighScore){
        entityTeamRoundData.AutonHighScore = autonHighScore;
    }
    public void setAutonLowScore(int autonLowScore){
        entityTeamRoundData.AutonLowScore = autonLowScore;
    }
    public void setAutonPickUp(int autonPickUp){
        entityTeamRoundData.AutonPickUp = autonPickUp;
    }
    public void setTeleopHighScore(int teleopHighScore){
        entityTeamRoundData.TeleopHighScore = teleopHighScore;
    }
    public void setTeleopLowScore(int teleopLowScore){
        entityTeamRoundData.TeleopLowScore = teleopLowScore;
    }
    public void setTeleopPickUp(int teleopPickUp){
        entityTeamRoundData.TeleopPickUp = teleopPickUp;
    }
    public void setRotationControl(boolean rotationControl){
        entityTeamRoundData.RotationControl = rotationControl;
    }
    public void setPositionControl(boolean positionControl){
        entityTeamRoundData.PositionControl = positionControl;
    }
    public void setClimb(String climb){
        entityTeamRoundData.Climb = climb;
    }
    public void setBrokeDown(boolean brokeDown){
        entityTeamRoundData.BrokeDown = brokeDown;
    }
    public void setFinalStage(int finalStage){
        entityTeamRoundData.FinalStage = finalStage;
    }
    public void setNotes(String notes){
        entityTeamRoundData.Notes = notes;
    }
    public void setRateShooting(int rateShooting){
        entityTeamRoundData.RateShooting = rateShooting;
    }
    public void setRateClimb(int rateClimb){
        entityTeamRoundData.RateClimb = rateClimb;
    }
    public void setRateWheel(int rateWheel){
        entityTeamRoundData.RateWheel = rateWheel;
    }
    public void setRateAuton(int rateAuton){
        entityTeamRoundData.RateAuton = rateAuton;
    }
    public void setRateDiver(int rateDiver){
        entityTeamRoundData.RateDiver = rateDiver;
    }
    public void setWouldPick(boolean wouldPick){
        entityTeamRoundData.WouldPick = wouldPick;
    }
    // Set Functions End
    public void StartUpDb(){
        // get room (db)
        if(db == null){
            db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDb")
                    .allowMainThreadQueries().build();
        }

        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = db.daoTeamRoundData();
        }
        if(daoScouterName == null){
            daoScouterName = db.daoScouterName();
        }

        // create entity (record) objects for each table
        if(entityTeamRoundData == null){
            entityTeamRoundData = new EntityTeamRoundData();
        }
        if(entityScouterName == null){
            entityScouterName = new EntityScouterName();
        }
    }
    protected void refreshAutonData(int teamNumber, int roundNumber){
        if(entityTeamRoundData != null){
            AutonHighGoalNumber = entityTeamRoundData.AutonHighScore;
            AutonLowGoalNumber = entityTeamRoundData.AutonLowScore;
            AutonPickUpNumber = entityTeamRoundData.AutonPickUp;
        }
    }
    protected void saveAutonData(int teamNumber, int roundNumber){
        daoTeamRoundData.updateAuton(entityTeamRoundData.AutonHighScore, entityTeamRoundData.AutonLowScore, entityTeamRoundData.AutonPickUp, teamNumber, roundNumber);
    }
}
