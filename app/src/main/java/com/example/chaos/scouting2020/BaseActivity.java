package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    // scouting data members
    protected int HighGoalNumber = 0;
    protected int LowGoalNumber = 0;
    protected int PickUpNumber = 0;
    protected String TColor = null;
    protected String Scout = null;
    protected int TNumber = -1;

}
