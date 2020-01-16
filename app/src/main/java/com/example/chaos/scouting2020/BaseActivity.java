package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    protected void DisplayValue(int resourceIdTextView, int value) {
        TextView textView = (TextView) findViewById(resourceIdTextView);
        textView.setText("" + value);
    }

    protected void AddMenuButtons(int resourceIdLayout) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(resourceIdLayout);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        final String[] menuNames = { "Login", "Auton", "Teleop", "Endgame", "Opinion" };
        final Class[] menuClasses = { LoginActivity.class, AutonActivity.class, TeleopActivity.class, EndgameActivity.class, OpinionActivity.class };
        Button[] menuButtons = new Button[menuNames.length];
        for(int i=0; i < menuNames.length; i++) {
            final int b = i;
            // create the button and set the properties
            menuButtons[b] = new Button(this);
            menuButtons[b].setText(menuNames[b]);
            menuButtons[b].setId(View.generateViewId());
            // if button is for current activity, disable it
            menuButtons[b].setEnabled(!(menuClasses[b].getSimpleName().equals(this.getClass().getSimpleName())));
            layout.addView(menuButtons[b]);
            // attach to right of screen
            set.connect(menuButtons[b].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,0);
            if (i==0) {
                // attach to top of screen
                set.connect(menuButtons[b].getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            } else {
                // attach to bottom of previous button
                set.connect(menuButtons[b].getId(), ConstraintSet.TOP, menuButtons[i-1].getId(), ConstraintSet.BOTTOM, 0);
            }
            set.constrainHeight(menuButtons[b].getId(), 64);
            set.applyTo(layout);
            menuButtons[b].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BaseActivity.this, menuClasses[b]);
                    startActivity(intent);
                }
            });
        }
    }
}
