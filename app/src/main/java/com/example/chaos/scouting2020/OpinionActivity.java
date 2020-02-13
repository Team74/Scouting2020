package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class OpinionActivity extends BaseActivity {

    protected ScoutingApplication App;

    // Android can suspend, terminate, destroy *any* activity at *any*
    // time for a lot reasons (triggering the OnPause), and a subsequent
    // OnCreate when the activity is restarted.  Member variables like
    // the ones below will be destroyed and lose any previously saved
    // values.  Thus, you need to make sure you reload them every time
    // during your OnCreate from the DB.
    protected int OpinionShootingRating = 0;
    protected int OpinionClimbingRating = 0;
    protected int OpinionWheelSpinningRating = 0;
    protected int OpinionAutonStageRating = 0;
    protected int OpinionDriverRating = 0;
    protected boolean OpinionWouldPickRobot = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // load any previously collected data for current team/round
        App.refreshTeamRoundData();

        // update display with common items
        UpdateCommonLayoutItems(R.id.opinionTNumberTextView, R.id.opinionQRNumberTextView, R.id.opinionScouterTextView, R.id.opinionConstraintLayout);

        // update display with specific items for this activity
        OpinionShootingRating = App.getRateShooting();
        OpinionClimbingRating = App.getRateClimb();
        OpinionWheelSpinningRating = App.getRateWheel();
        OpinionAutonStageRating = App.getRateAuton();
        OpinionDriverRating = App.getRateDriver();
        OpinionWouldPickRobot = App.getWouldPick();

        RadioButton Shooting = (RadioButton) findViewById(R.id.opinionShootingRatingRadioButton1);
        Shooting.setChecked(OpinionShootingRating == 1);
        Shooting = (RadioButton) findViewById(R.id.opinionShootingRatingRadioButton2);
        Shooting.setChecked(OpinionShootingRating == 2);
        Shooting = (RadioButton) findViewById(R.id.opinionShootingRatingRadioButton3);
        Shooting.setChecked(OpinionShootingRating == 3);
        Shooting = (RadioButton) findViewById(R.id.opinionShootingRatingRadioButton4);
        Shooting.setChecked(OpinionShootingRating == 4);
        Shooting = (RadioButton) findViewById(R.id.opinionShootingRatingRadioButton5);
        Shooting.setChecked(OpinionShootingRating == 5);

        RadioButton Climbing = (RadioButton) findViewById(R.id.opinionClimbingRatingRadioButton1);
        Climbing.setChecked(OpinionClimbingRating == 1);
        Climbing = (RadioButton) findViewById(R.id.opinionClimbingRatingRadioButton2);
        Climbing.setChecked(OpinionClimbingRating == 2);
        Climbing = (RadioButton) findViewById(R.id.opinionClimbingRatingRadioButton3);
        Climbing.setChecked(OpinionClimbingRating == 3);
        Climbing = (RadioButton) findViewById(R.id.opinionClimbingRatingRadioButton4);
        Climbing.setChecked(OpinionClimbingRating == 4);
        Climbing = (RadioButton) findViewById(R.id.opinionClimbingRatingRadioButton5);
        Climbing.setChecked(OpinionClimbingRating == 5);

        RadioButton WheelSpinning = (RadioButton) findViewById(R.id.opinionWheelRatingRadioButton1);
        WheelSpinning.setChecked(OpinionWheelSpinningRating == 1);
        WheelSpinning = (RadioButton) findViewById(R.id.opinionWheelRatingRadioButton2);
        WheelSpinning.setChecked(OpinionWheelSpinningRating == 2);
        WheelSpinning = (RadioButton) findViewById(R.id.opinionWheelRatingRadioButton3);
        WheelSpinning.setChecked(OpinionWheelSpinningRating == 3);
        WheelSpinning = (RadioButton) findViewById(R.id.opinionWheelRatingRadioButton4);
        WheelSpinning.setChecked(OpinionWheelSpinningRating == 4);
        WheelSpinning = (RadioButton) findViewById(R.id.opinionWheelRatingRadioButton5);
        WheelSpinning.setChecked(OpinionWheelSpinningRating == 5);

        RadioButton AutonStage = (RadioButton) findViewById(R.id.opinionAutonRatingRadioButton1);
        AutonStage.setChecked(OpinionAutonStageRating == 1);
        AutonStage = (RadioButton) findViewById(R.id.opinionAutonRatingRadioButton2);
        AutonStage.setChecked(OpinionAutonStageRating == 2);
        AutonStage = (RadioButton) findViewById(R.id.opinionAutonRatingRadioButton3);
        AutonStage.setChecked(OpinionAutonStageRating == 3);
        AutonStage = (RadioButton) findViewById(R.id.opinionAutonRatingRadioButton4);
        AutonStage.setChecked(OpinionAutonStageRating == 4);
        AutonStage = (RadioButton) findViewById(R.id.opinionAutonRatingRadioButton5);
        AutonStage.setChecked(OpinionAutonStageRating == 5);

        RadioButton Driver = (RadioButton) findViewById(R.id.opinionDriverRatingRadioButton1);
        Driver.setChecked(OpinionDriverRating == 1);
        Driver = (RadioButton) findViewById(R.id.opinionDriverRatingRadioButton2);
        Driver.setChecked(OpinionDriverRating == 2);
        Driver = (RadioButton) findViewById(R.id.opinionDriverRatingRadioButton3);
        Driver.setChecked(OpinionDriverRating == 3);
        Driver = (RadioButton) findViewById(R.id.opinionDriverRatingRadioButton4);
        Driver.setChecked(OpinionDriverRating == 4);
        Driver = (RadioButton) findViewById(R.id.opinionDriverRatingRadioButton5);
        Driver.setChecked(OpinionDriverRating == 5);

        RadioButton WouldPick = (RadioButton) findViewById(R.id.opinionPickYesRadioButton);
        WouldPick.setChecked(OpinionWouldPickRobot == true);
        WouldPick = (RadioButton) findViewById(R.id.opinionPickNoRadioButton);
        WouldPick.setChecked(OpinionWouldPickRobot == false);
    }

    public void autonButtonPressed(View autonButton) {
        Intent intent = new Intent(this, AutonActivity.class);
        startActivity(intent);
    }
    public void teleopButtonPressed(View teleopButton) {
        Intent intent = new Intent(this, TeleopActivity.class);
        startActivity(intent);
    }
    public void endgameButtonPressed(View engameButton) {
        Intent intent = new Intent(this, EndgameActivity.class);
        startActivity(intent);
    }
    public void exitButtonPressed(View engameButton) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //Shooting Rating
    public void shootingRating1StarPressed(View shootingRating1Star) {
        OpinionShootingRating = 1;
    }

    public void shootingRating2StarPressed(View shootingRating2Star) {
        OpinionShootingRating= 2;
    }

    public void shootingRating3StarPressed(View shootingRating3Star) {
        OpinionShootingRating = 3;
    }

    public void shootingRating4StarPressed(View shootingRating4Star) {
        OpinionShootingRating = 4;
    }

    public void shootingRating5StarPressed(View shootingRating5Star) {
        OpinionShootingRating = 5;
    }

    //Climbing Rating
    public void climbingRating1StarPressed(View climbingRating1Star) {
        OpinionClimbingRating = 1;
    }

    public void climbingRating2StarPressed(View climbingRating2Star) {
        OpinionClimbingRating = 2;
    }

    public void climbingRating3StarPressed(View climbingRating3Star) {
        OpinionClimbingRating = 3;
    }

    public void climbingRating4StarPressed(View climbingRating4Star) {
        OpinionClimbingRating = 4;
    }

    public void climbingRating5StarPressed(View climbingRating5Star) {
        OpinionClimbingRating = 5;
    }

    //Wheel Spinning Rating
    public void wheelSpinningRating1StarPressed(View wheelSpinningRating1Star) {
        OpinionWheelSpinningRating = 1;
    }

    public void wheelSpinningRating2StarPressed(View wheelSpinningRating2Star) {
        OpinionWheelSpinningRating = 2;
    }

    public void wheelSpinningRating3StarPressed(View wheelSpinningRating3Star) {
        OpinionWheelSpinningRating = 3;
    }

    public void wheelSpinningRating4StarPressed(View wheelSpinningRating4Star) {
        OpinionWheelSpinningRating = 4;
    }

    public void wheelSpinningRating5StarPressed(View wheelSpinningRating5Star) {
        OpinionWheelSpinningRating = 5;
    }

    //Auton Stage Rating
    public void autonStageRating1StarPressed(View autonStageRating1Star) {
        OpinionAutonStageRating = 1;
    }

    public void autonStageRating2StarPressed(View autonStageRating2Star) {
        OpinionAutonStageRating = 2;
    }

    public void autonStageRating3StarPressed(View autonStageRating3Star) {
        OpinionAutonStageRating = 3;
    }

    public void autonStageRating4StarPressed(View autonStageRating4Star) {
        OpinionAutonStageRating = 4;
    }

    public void autonStageRating5StarPressed(View autonStageRating5Star) {
        OpinionAutonStageRating = 5;
    }

    //Driver Rating
    public void driverRating1StarPressed(View driverRating1Star) {
        OpinionDriverRating = 1;
    }

    public void driverRating2StarPressed(View driverRating2Star) {
        OpinionDriverRating = 2;
    }

    public void driverRating3StarPressed(View driverRating3Star) {
        OpinionDriverRating = 3;
    }

    public void driverRating4StarPressed(View driverRating4Star) {
        OpinionDriverRating = 4;
    }

    public void driverRating5StarPressed(View driverRating5Star) {
        OpinionDriverRating = 5;
    }

    //Pick Boolean
    public void wouldPickRobotPressed(View wouldPickRobot) {
        OpinionWouldPickRobot = true;
    }

    public void wouldNotPickRobotPressed(View wouldNotPickRobot) {
        OpinionWouldPickRobot = false;
    }

    protected void onPause() {
        super.onPause();
        // save any updated data for current team/round
        App.setRateShooting(OpinionShootingRating);
        App.setRateClimb(OpinionClimbingRating);
        App.setRateWheel(OpinionWheelSpinningRating);
        App.setRateAuton(OpinionAutonStageRating);
        App.setRateDriver(OpinionDriverRating);
        App.setWouldPick(OpinionWouldPickRobot);
        App.saveTeamRoundData();
    }

}
