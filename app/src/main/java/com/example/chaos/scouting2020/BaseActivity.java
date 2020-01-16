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

    protected int highGoalNumber = 0;

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
        //the layout on which you are working
        ConstraintLayout layout = (ConstraintLayout) findViewById(resourceIdLayout);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        // set the properties for button
        String[] buttonText = { "Login", "Auton", "Teleop", "Endgame", "Opinion" };
        Button[] menuButtons = new Button[buttonText.length];
// TBD:  skip button for current screen
        for(int i=0; i < buttonText.length; i++) {
            menuButtons[i] = new Button(this);
            menuButtons[i].setText(buttonText[i]);
            menuButtons[i].setId(View.generateViewId());
            layout.addView(menuButtons[i]);
            set.connect(menuButtons[i].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,0);
            if (i==0) {
                // attach to top of screen
                set.connect(menuButtons[i].getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            } else {
                // attach to bottom of previous button
                set.connect(menuButtons[i].getId(), ConstraintSet.TOP, menuButtons[i-1].getId(), ConstraintSet.BOTTOM, 0);
            }
            set.constrainHeight(menuButtons[i].getId(), 64);
            set.applyTo(layout);
            menuButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
// TBD:  this is pretty ugly, is there a better way?
                    Button b = (Button)v;
                    String buttonText = b.getText().toString();
                    switch(buttonText) {
                        default:
                        case "Login":
                            intent = new Intent(BaseActivity.this, LoginActivity.class);
                            break;
                        case "Auton":
                            intent = new Intent(BaseActivity.this, AutonActivity.class);
                            break;
                        case "Teleop":
                            intent = new Intent(BaseActivity.this, TeleopActivity.class);
                            break;
                        case "Endgame":
                            intent = new Intent(BaseActivity.this, EndgameActivity.class);
                            break;
                        case "Opinion":
                            intent = new Intent(BaseActivity.this, OpinionActivity.class);
                            break;
                    }
                    startActivity(intent);
                }
            });
        }
    }
}
