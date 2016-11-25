package com.hezd.example.camera.activity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hezd.example.camera.R;
import com.hezd.example.camera.view.CameraSurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RelativeLayout mScanRl;
    private Button mTakePicBtn;
    private Camera mCameraInstance;
    private View mScanV;

    // 扫描区域属性
    private int mScaLeft;
    private int mScanTop;
    private int mScanRight;
    private int mScanBottom;
    private int mScanWidth;
    private int mScanHeight;
    private ImageView mPicIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setViews();
        setListeners();
    }

    private void getViews() {
        FrameLayout surfaceContainerFl = (FrameLayout) findViewById(R.id.fl_surfaceview_container);
        mPicIv = (ImageView) findViewById(R.id.iv_pic);
        mScanRl = (RelativeLayout) findViewById(R.id.rl_scan);
        mScanV = findViewById(R.id.v_scan);
        mTakePicBtn = (Button) findViewById(R.id.btn_take_pic);
        mCameraInstance = getCameraInstance();
        CameraSurfaceView mSurfaceView = new CameraSurfaceView(this, mCameraInstance);
        surfaceContainerFl.addView(mSurfaceView);
    }

    private Camera getCameraInstance() {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            showMessage("this device has no camera!");
            return null;
        }
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return camera;
    }

    private void showMessage(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    private void setViews() {
        mScanV.post(new Runnable() {
            @Override
            public void run() {
                mScaLeft = mScanV.getLeft();
                mScanTop = mScanV.getTop();
                mScanRight = mScanV.getRight();
                mScanBottom = mScanV.getBottom();
                mScanWidth = mScanV.getWidth();
                mScanHeight = mScanV.getHeight();
                Log.d(TAG,"getleft value:"+mScaLeft);
                Log.d(TAG,"gettop value:"+mScanTop);
                Log.d(TAG,"getright value:"+mScanRight);
                Log.d(TAG,"getbottom value:"+mScanBottom);
                Log.d(TAG,"getwidth value:"+mScanWidth);
                Log.d(TAG,"getheight value:"+ mScanHeight);
            }
        });
    }

    private void setListeners() {
        mTakePicBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_pic:
                takePic();
                break;
        }
    }

    private void takePic() {

        mCameraInstance.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                /**
                 * 图片截取的原理，我们预览surfaceview是全屏的，扫描区域是屏幕中的一部分
                 * */
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(data,0,data.length,options);
                options.inJustDecodeBounds = false;

//                int sampleSize = (int)((float)outHeight/mScanHeight);
//                options.inSampleSize = sampleSize;
//                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                Rect rect = new Rect(mScaLeft,mScanTop,mScanRight,mScanBottom);
                try {
                    BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(data,0,data.length,false);
                    Bitmap cropBtimap = decoder.decodeRegion(rect, options);
                    // 现在图片拍摄以后是横着稍后处理
//                    Matrix matrix = new Matrix();
//                    matrix.setRotate(90,mScanWidth/2,mScanHeight/2);
//                    Bitmap realBitmap = Bitmap.createBitmap(cropBtimap,0,0,mScanWidth,mScanHeight,matrix,true);
//                    if(cropBtimap!=null&&!cropBtimap.isRecycled()) {
//                        cropBtimap.recycle();
//                    }
                    mPicIv.setImageBitmap(cropBtimap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mCameraInstance.startPreview();

            }
        });
    }
}
