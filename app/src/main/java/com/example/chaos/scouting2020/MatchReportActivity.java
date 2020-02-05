package com.example.chaos.scouting2020;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MatchReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_report);
        ScoutingDatabase db = null;
        DaoTeamRoundData daoTeamRoundData = null;

        // start a new team round data record
        ((ScoutingApplication) this.getApplication()).newTeamRoundData();

        if(db == null){
            db = Room.databaseBuilder(getApplicationContext(), ScoutingDatabase.class, "scoutDb")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
            // TBD: figure out how to allow for "Non-destructive Migrations" of the ROOM DB
            // for when the version changes
        }
        // get data access objects (tables)
        if(daoTeamRoundData == null){
            daoTeamRoundData = db.daoTeamRoundData();
        }

        TableLayout tl = (TableLayout)findViewById(R.id.matchReportTable);

        DaoTeamRoundData.MatchReportData dataRows[] = daoTeamRoundData.getMatchReportData();

        for(DaoTeamRoundData.MatchReportData dataRow : dataRows) {
            TableRow tr1 = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tr1.setLayoutParams(lp);
            TextView textview1 = new TextView(this);
            textview1.setText(""+dataRow.TeamNumber);
            TextView textview2 = new TextView(this);
            textview2.setText(""+dataRow.AvgTeleopHighScore);
            TextView textview3 = new TextView(this);
            textview3.setText("col 3");
            tr1.addView(textview1);
            tr1.addView(textview2);
            tr1.addView(textview3);
            tl.addView(tr1, 1);
        }
    }
}
