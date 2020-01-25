package com.example.chaos.scouting2020;

import android.app.Application;
import android.provider.Settings;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ScoutingApplication extends Application {

    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }


    public void exportScouterNames() {
        try {
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "ScouterNames-"+androidId+".csv";
            String filePath = baseDir + File.separator + fileName;

            // we are exporting everything, so recreate each time
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));

            // TBD: these names should come from the a select * on the ScouterNames table
            String[] scouters = { "Allen Z.", "Ben Y.", "Clara X." };
            // TBD: for each scouter name in the ScouterNames table
            for (String scouter: scouters) {
                String[] csvLine = { scouter };
                writer.writeNext(csvLine);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating CSV file", Toast.LENGTH_SHORT).show();
        }
    }

    public void importScouterNames() {
        try {
            String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "ScouterNames-"+androidId+".csv";
            String filePath = baseDir + File.separator + fileName;

            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] csvLine;
            while ((csvLine = reader.readNext()) != null) {
                // csvLine[] is an array of values parsed from from the CSV line
                String scouter = csvLine[0];
                if (!scouter.isEmpty()) {
                    // TBD: add the scouter to our ScouterNames table
                    //entityScouterName.ScouterName = scouter;
                    //daoScouterName.insert(entityScouterName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading CSV file", Toast.LENGTH_SHORT).show();
        }
    }

}
