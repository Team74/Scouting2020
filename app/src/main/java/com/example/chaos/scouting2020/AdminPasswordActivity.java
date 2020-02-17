package com.example.chaos.scouting2020;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_password);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Admin Password");
    }

    public void cancelButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void confirmButtonPressed(View pitScoutingButton) {
        EditText passwordEditText = (EditText) findViewById(R.id.adminPasswordEditText);
        String inputtedPassword = passwordEditText.getText().toString();
        if (inputtedPassword.equals("")) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
            passwordEditText.setText("");
        }
    }
}
