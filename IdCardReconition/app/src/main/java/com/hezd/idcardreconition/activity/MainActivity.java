package com.hezd.idcardreconition.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hezd.idcardreconition.R;
import com.hezd.idcardreconition.view.MySurfaceView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FrameLayout mSurfaceViewContainer;
    private Camera mCameraInst;
    private Button mTakePicBtn;

    private Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            Log.d("hezd","onShutter");
        }
    };

    private Camera.PictureCallback rawCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.d("hezd","rawCallBack");
        }
    };

    private Camera.PictureCallback postViewCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.d("hezd","postViewCallBack");
        }
    };

    private Camera.PictureCallback jpegCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.d("hezd","jpegCallBack");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testImage();
        initCamera();
        getViews();
        setViews();
        setListeners();

    }

    private void testImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
        Bitmap home = BitmapFactory.decodeResource(getResources(),R.mipmap.home,options);
    }

    private void initCamera() {
        if(!checkCameraHardWare()) {
            showMessage("this device has no camera");
            return;
        }

        mCameraInst = getCameraInst();

    }

    private void showMessage(String s) {
        Toast.makeText(this, s ,Toast.LENGTH_SHORT).show();
    }

    private void getViews() {
        mSurfaceViewContainer = (FrameLayout) findViewById(R.id.fl_container);
        mTakePicBtn = (Button) findViewById(R.id.btn_take);
    }

    /**
     * check if the device has a camera
     * @return
     */
    private boolean checkCameraHardWare() {
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        }
        return false;
    }

    /**
     * a safe way to get an instance of Camera Object
     * @return
     */
    private Camera getCameraInst() {
        Camera cameraInst = null;
        try {
            cameraInst = cameraInst.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cameraInst;
    }

    private void setViews() {
        if(mCameraInst ==null) {
            showMessage("retrive camera instance failed!");
            return;
        }
        MySurfaceView mySurfaceView = new MySurfaceView(this,mCameraInst);
//        mySurfaceView.setFocusable(true);
//        mySurfaceView.setBackgroundColor(TRIM_MEMORY_BACKGROUND);
//        mySurfaceView.getHolder().setKeepScreenOn(true);
        mSurfaceViewContainer.addView(mySurfaceView);
    }

    private void setListeners() {
        mTakePicBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()) {
            case R.id.btn_take:
                mCameraInst.takePicture(shutterCallback,rawCallBack,postViewCallBack,jpegCallBack);
                break;
        }
    }
}
