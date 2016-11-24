package com.hezd.idcardreconition.view;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * 自定义surfaceView
 * Created by hezd on 2016/11/23.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private Camera mCamera;
    private SurfaceHolder mHolder;

    public MySurfaceView(Context context, Camera camera) {
        super(context);
        this.mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("hezd","surfaceCreated");
        try {

            mCamera.setPreviewDisplay(holder);
            initCamera();
            mCamera.startPreview();
//            mCamera.autoFocus(new Camera.AutoFocusCallback() {
//                @Override
//                public void onAutoFocus(boolean success, Camera camera) {
//                    Log.d("hezd","onAutoFocus");
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCamera() {
        Camera.Parameters parameters = mCamera.getParameters();
        // ......设置Camera参数
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }else {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }

//        mCamera.setDisplayOrientation(90);
        mCamera.setParameters(parameters);
        mCamera.cancelAutoFocus();//只有加上了这一句，才会自动对焦。

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("hezd","surfaceChanged");
        autoFocus();
    }

    private void autoFocus() {
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(100);
//                    if(mCamera==null)
//                        return;
//                    mCamera.autoFocus(new Camera.AutoFocusCallback() {
//                        @Override
//                        public void onAutoFocus(boolean success, Camera camera) {
//                            Log.d("hezd","onAutofocus");
//                            if(success) {
//                                initCamera();
////                                mCamera.startPreview();
//                            }
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("hezd","surfaceDestroyed");
        Log.d("hezd","surface release,camera release");
        mCamera.stopPreview();// 关闭预览
        mCamera.release();// 释放相机资源
        mCamera = null;
    }
}
