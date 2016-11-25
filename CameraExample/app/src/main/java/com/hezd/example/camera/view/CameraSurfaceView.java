package com.hezd.example.camera.view;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by hezd on 2016/11/25.
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = CameraSurfaceView.class.getSimpleName();
    private Camera mCameraInst;
    private SurfaceHolder mHolder;

    public CameraSurfaceView(Context context, Camera camera) {
        super(context);
        this.mCameraInst = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(mCameraInst==null) {
            Log.d(TAG,"the camera is unavalible!");
            return;
        }
        try {
            mCameraInst.setPreviewDisplay(holder);
            // set camera parameters
            initCamera();

            mCameraInst.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCamera() {
        Camera.Parameters parameters = mCameraInst.getParameters();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }else {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        mCameraInst.setDisplayOrientation(90);// 竖屏
        mCameraInst.setParameters(parameters);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mCameraInst!=null){
            mCameraInst.stopPreview();
            mCameraInst.release();
            mCameraInst = null;
        }
    }
}
