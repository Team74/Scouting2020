package com.example.chaos.scouting2020;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;

public class ScouterScheduleReportActivity extends BaseActivity {

    protected ScoutingApplication App;
    protected Context Ctx;
    protected String FilePath;  // location where to import from

    public class UpdateScouterScheduleReportTable implements ReportUpdateCommand {

        public void update() {
            try {
                // open file and attach a CSV reader to file reader
                CSVReader reader = new CSVReader(new FileReader(FilePath));

                // create a CSV record that we will fill in
                String[] csvLine;

                // for each record returned from the CSV file, add a record to DB
                int line = 0;
                while ((csvLine = reader.readNext()) != null) {
                    if (line == 0) {
                        // add the header strings as a row to our table
                        AddHeaderStringsAsRowToReportTable(R.id.scouterScheduleReportTable, csvLine, this, 12);
                    } else {
                        // add the data strings as a row to our table
                        AddDataStringsAsRowToReportTable(R.id.scouterScheduleReportTable, csvLine);
                    }
                    line = line + 1;
                }
                // close the CSV file
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Ctx, "Error reading ScouterSchedule CSV file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouter_schedule_report);

        // set a more descriptive title for this screen
        setTitle("Scouting2020 - Scouter Schedule Report");

        // get a handle to our global app state
        App = (ScoutingApplication) this.getApplication();

        // get handle to app context
        Ctx  = this;

        // Get default CSV export location (the system download directory)
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Environment.DIRECTORY_DOWNLOADS;

        // makes the filepath
        FilePath = baseDir + File.separator + "ScouterSchedule.csv";

        // display the report table for the first time
        UpdateScouterScheduleReportTable updateScouterScheduleReportTable = new UpdateScouterScheduleReportTable();
        updateScouterScheduleReportTable.update();
    }
}
