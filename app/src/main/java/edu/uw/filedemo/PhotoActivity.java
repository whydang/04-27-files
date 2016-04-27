package edu.uw.filedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoActivity extends AppCompatActivity {

    private static final String TAG = "Photo";

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //action bar "back"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Uri picUri;

    public void takePicture(View v){
        Log.v(TAG, "Taking picture...");

        File img = null;
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            img = new File(dir, "PIC_" + timestamp);
            img.createNewFile();
            picUri = Uri.fromFile(img);

        } catch (IOException e) {
            Log.v(TAG, Log.getStackTraceString(e));
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // mediastore extra_output tells us ... to take a picture and put it in picUri
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {



//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap)extras.get("data");
            ImageView imageView = (ImageView)findViewById(R.id.imgThumbnail);
            imageView.setImageURI(picUri);
//            imageView.setImageBitmap(imageBitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void sharePicture(View v){
        Log.v(TAG, "Sharing picture...");

    }
}
