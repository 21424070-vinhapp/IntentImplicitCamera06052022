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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mBtnCamera, mBtnGallery;
    ImageView mImg;
    int REQUEST_CODE_CAMERA=123;
    boolean isFirstOpenRequest=true;
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
                    //TRUY CAP VAO SETTING TREN APP CHU KHONG PHAI LA TREN DIEN THOAI
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
                                Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri=Uri.fromParts("package",getPackageName(),null);
                                intent.setData(uri);
                                startActivity(intent);
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
                            isFirstOpenRequest=false;
                    }
                }
                else
                {
                    Intent intent=new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraLauncher.launch(intent);
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
                Intent intent=new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(intent);
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