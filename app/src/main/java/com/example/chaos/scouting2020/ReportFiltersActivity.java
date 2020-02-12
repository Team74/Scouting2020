package com.example.chaos.scouting2020;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class ReportFiltersActivity extends AppCompatActivity {

    protected ScoutingApplication App;

    protected List<String> TeamNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_filters);

        App = (ScoutingApplication) this.getApplication();

        // get the list of team numbers
        TeamNumberList = App.getAllTeamNumbersAsList();

        // get handle to table
        TableLayout table = (TableLayout)findViewById(R.id.reportFiltersTable);

        // create a common layout param group for all of our rows and items
        TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 2);
        TableRow.LayoutParams lpItem = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1);

        

        // loop over team numbers
        for(String teamNumber : TeamNumberList) {
            // create a new row to hold our data values
            TableRow row = new TableRow(this);
            row.setLayoutParams(lpRow);

            // alternate the color of each row
            int rowNumber = table.getChildCount();
            if ((rowNumber % 2) == 0) {
                row.setBackgroundResource(R.color.colorBlueBackground);
            } else {
                row.setBackgroundResource(R.color.colorRedBackground);
            }

            // add check box to row
            CheckBox dataBox = new CheckBox(this);
            dataBox.setLayoutParams(lpItem);
            dataBox.setChecked(false);
            dataBox.setPadding(2, 0, 2, 0);
            dataBox.setGravity(Gravity.CENTER);
            row.addView(dataBox);

            // add text view for team number to row
            TextView dataView = new TextView(this);
            dataView.setLayoutParams(lpItem);
            dataView.setText(teamNumber);
            dataView.setPadding(2, 0, 2, 0);
            dataView.setGravity(Gravity.CENTER);
            row.addView(dataView);

            // add the data row to the end of the table
            table.addView(row);
        }

    }
}
