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
import android.provider.DocumentsContract;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        intit();

        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Neu ung dung chua duoc cap quyen truy cap camera
                if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)
                !=PackageManager.PERMISSION_GRANTED)
                {
                    //Neu nguoi dung bam vao deny va khong hien lai
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.CAMERA))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Xac thuc quyen Camera");
                        builder.setMessage("Di vao cai dat cho app");
                        //positive danh cho nut co y nghia tich cuc nhu OK, yes,...
                        builder.setPositiveButton("Dong y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //di vao setting cua app de chap nhan quyen truy cap camera
                                Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri=Uri.fromParts("package",getPackageName(),null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        });

                        //nagative danh cho cac nut co y nghia tieu cuc nhu NO, khong dong y
                        builder.setNegativeButton("Khong dong y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        //xu li cac nut trong alertDialog thi them dong cho thong bao xuat hien
                        builder.show();
                    }

                    // Neu nguoi dung bam vao cho phep truy cap camera
                    else
                    {
                        //gui request ve ham onRequest kiem tra co phai chap nhan quyen truy cap khong
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[] {Manifest.permission.CAMERA},
                                REQUEST_CODE_CAMERA);
                    }
                }
                //neu ung dung da duoc cung cap quyen truy cap camera
                else
                {
                    Intent intent=new Intent();
                    //CHI CHUP DuOC 1 TAM
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
                //CHI CHUP DuOC 1 TAM
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