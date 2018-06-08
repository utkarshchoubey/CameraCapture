package com.example.utkarsh.ucamera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE=1;
    ImageView imageView;
    Button buckyButton;
    private File imagefile;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buckyButton=(Button)findViewById(R.id.buckyButton);
        imageView=(ImageView)findViewById(R.id.imageView);
        if(hasCamera()==false){
            buckyButton.setEnabled(false);
        }
    }

    boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    public void launchCamera(View view){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagefile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
        Uri tempuri=Uri.fromFile(imagefile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE&&requestCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap photo= (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
        }
    }


}
