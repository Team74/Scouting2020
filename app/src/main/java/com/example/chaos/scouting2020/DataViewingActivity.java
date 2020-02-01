package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DataViewingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewing);
    }

    public void MenuButtonPressed(View MenuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    // TBD: temp button for testing csv code
    public void CSVNamesButton(View CSVNamesButton) {
        Log.d("CSV", "Starts");
        ((ScoutingApplication) this.getApplication()).exportScouterNames();
        Log.d("CSV", "works");
    }
}
