package com.example.intentimplicitcamera06052022;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button mBtnCamera, mBtnGallery;
    ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //checkSelfPermission KEIM TRA XEM nguoi dung co chap nhan quyen truy cap vao camera hay chua
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED){

                }
            }
        });

    }

    private void init()
    {
        mBtnCamera=findViewById(R.id.buttonCamera);
        mBtnGallery=findViewById(R.id.buttonGallery);
        mImg=findViewById(R.id.imageView);
    }

    private ActivityResultLauncher<Intent> cameraLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            });
}