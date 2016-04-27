package edu.uw.filedemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    private RadioButton externalButton; //save reference for quick access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        externalButton = (RadioButton)findViewById(R.id.radio_external);
    }

    // test to see if the storage stage is mounted such that we can write inside it
    // returns T/F
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }


    public void saveFile(View v){
        Log.v(TAG, "Saving file...");
        EditText textEntry = (EditText)findViewById(R.id.textEntry); //what we're going to save

        if(externalButton.isChecked()){ //external storage
            if (isExternalStorageWritable()) {
                //File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(dir, "notes.txt");

                try {
                    PrintWriter out = new PrintWriter(file);
                    out.println(textEntry.getText().toString());
                    out.close();
                } catch (IOException e) {
                    Log.v(TAG, Log.getStackTraceString(e));
                }
            }
        }
        else { //internal storage
//            File dir = getFilesDir();
//            File file = new File(dir, "notes.txt");
//
//            try {
//                PrintWriter out = new PrintWriter(file);
//                out.println(textEntry.getText().toString());
//                out.close();
//            } catch (IOException e) {
//                Log.v(TAG, Log.getStackTraceString(e));
//            }

            // gives a files outputstream
            try {
                FileOutputStream fos = openFileOutput("noes.txt", MODE_PRIVATE); // takes in integer constant representing MODE

            } catch (IOException e) {

            }

        }
    }


    public void loadFile(View v){
        Log.v(TAG, "Loading file...");
        TextView textDisplay = (TextView)findViewById(R.id.txtDisplay); //where we're going to show
        textDisplay.setText(""); //clear initially

        if(externalButton.isChecked()){ //external storage
            if (isExternalStorageWritable()) {
                //File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(dir, "notes.txt");

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    StringBuilder builder = new StringBuilder();

                    String line = reader.readLine();
                    while (line != null) {
                        builder.append(line + "\n");
                        line = reader.readLine();

                    }

                } catch (IOException e) {
                    Log.v(TAG, Log.getStackTraceString(e));
                }
            }
        }
        else { //internal storage

        }
    }


    public void shareFile(View v) {
        Log.v(TAG, "Sharing file...");
        Uri fileUri = null;
        if(externalButton.isChecked()){ //external storage
            File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(dir, "notes.txt");
            fileUri = Uri.fromFile(file);
        }
        else { //internal storage

        }
        // send to share, lets other application handle it
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);

        // sets the chooser and a label for when it pops up
        // only happens in the case there are 2+ that listens to the intent
        Intent chooser = Intent.createChooser(intent, "Share File");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_photo:
                startActivity(new Intent(MainActivity.this, PhotoActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
