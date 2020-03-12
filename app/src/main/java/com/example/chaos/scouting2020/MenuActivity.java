package com.example.chaos.scouting2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends BaseActivity {

    protected ScoutingApplication App;
    public String TabletColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        TabletColor = App.getTabletColor();

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Menu - " + TabletColor);

        // make sure DB started
        App.startUpDb();

    }

    public void matchScoutingButtonPressed(View matchScoutingButton) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void adminButtonPressed(View pitScoutingButton) {
        Intent intent = new Intent(this, AdminPasswordActivity.class);
        startActivity(intent);
    }

    public void pitScoutingMainButtonPressed(View pitScoutingMainButton) {
        Intent intent = new Intent(this, PitScoutingMainActivity.class);
        startActivity(intent);
    }

    public void dataViewingButtonPressed(View dataViewingButton) {
        Intent intent = new Intent(this, DataViewingActivity.class);
        startActivity(intent);
    }


}
