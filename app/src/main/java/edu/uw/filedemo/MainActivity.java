package edu.uw.filedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    private RadioButton externalButton; //save reference for quick access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        externalButton = (RadioButton)findViewById(R.id.radio_external);
    }


    public void saveFile(View v){
        Log.v(TAG, "Saving file...");
        EditText textEntry = (EditText)findViewById(R.id.textEntry); //what we're going to save

        if(externalButton.isChecked()){ //external storage

        }
        else { //internal storage

        }
    }


    public void loadFile(View v){
        Log.v(TAG, "Loading file...");
        TextView textDisplay = (TextView)findViewById(R.id.txtDisplay); //where we're going to show
        textDisplay.setText(""); //clear initially

        if(externalButton.isChecked()){ //external storage

        }
        else { //internal storage

        }
    }


    public void shareFile(View v) {
        Log.v(TAG, "Sharing file...");

        if(externalButton.isChecked()){ //external storage

        }
        else { //internal storage

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
