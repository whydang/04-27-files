package edu.uw.filedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void saveFile(View v){
        Log.v(TAG, "Saving file...");

    }


    public void loadFile(View v){
        Log.v(TAG, "Loading file...");

    }


    public void shareFile(View v) {
        Log.v(TAG, "Sharing file...");

    }
}
