package com.example.chaos.scouting2020;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OpinionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        // add our common menu buttons
        AddMenuButtons(R.id.activity_opinion);
    }
}
