package com.example.chaos.scouting2020;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // add our common menu buttons
        AddMenuButtons(R.id.activity_login);
    }
}
