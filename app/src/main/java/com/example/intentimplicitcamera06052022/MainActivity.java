package com.example.intentimplicitcamera06052022;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mBtnCamera, mBtnGallery;
    ImageView mImg;
    int REQUEST_CODE_CAMERA=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        intit();

        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED)
                {
                    //Kiem tra xem nguoi dung co bam deny chua
                    //show thong bao va nhay vao setting
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA))
                    {
                        //Log.d("BBB", "vao day");
                        AlertDialog.Builder buidler=new AlertDialog.Builder(MainActivity.this);
                        buidler.setTitle("Xac thuc quyen Camera");
                        buidler.setMessage("Di vao quyen cai dat");

                        //setPositiveButton danh cho cac nut tich cuc nhu OK, dong y
                        buidler.setPositiveButton("Dong y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        //setNegativeButton danh cho cac nut tieu cuc nhu khong dong y, khong cho phep
                        buidler.setNegativeButton("Khong dong y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });


                        //cuoi cung can co buidler.show(); de hien ra buidler
                        buidler.show();

                    }

                    //neu khong thi hien ra bang thong bao yeu cau nguoi dung cung cap quyen truy cap camera
                    else
                    {
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                REQUEST_CODE_CAMERA);
                    }
                }
            }
        });


    }

    private void intit() {
        mBtnCamera=findViewById(R.id.buttonCamera);
        mBtnGallery=findViewById(R.id.buttonGallery);
        mImg=findViewById(R.id.imageView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_CAMERA)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "cho PHEP", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Khong cho phep", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private ActivityResultLauncher<Intent> cameraLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            }
    );
}