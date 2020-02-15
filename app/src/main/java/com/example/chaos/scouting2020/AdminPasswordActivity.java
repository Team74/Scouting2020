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

    protected String InputtedPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Scouting2020 - Admin Password");

        setContentView(R.layout.activity_admin_password);
    }

    public void cancelButtonPressed(View menuButton) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void confirmButtonPressed(View pitScoutingButton) {
        EditText Password = (EditText) findViewById(R.id.AdminPasswordEditText);
        InputtedPassword = Password.getText().toString();
        if (InputtedPassword.equals("")) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
            EditText Clear = (EditText) findViewById(R.id.AdminPasswordEditText);
            Clear.setText("");
        }
    }
}
