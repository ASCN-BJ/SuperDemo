package com.example.bj.superdemo.ui.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.hardware.Camera;
import android.media.CameraProfile;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.utils.viewutil.fileutils.BitmapCompressor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author bj
 *         一个android的相机类
 */
public class MyCameraActivity extends BaseActivity implements SurfaceHolder.Callback {
    private ImageButton ib_take_picture;
    private SurfaceView sv_camera;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private Camera.Parameters mCameraParamters;
    private RelativeLayout fl_load_image;
    private ImageView iv_load_image;
    private final int imageWidth = 800;
    private Button btn_again;
    private Button btn_confirm;
    private Bitmap mComBitmp;
    private File mFile;

    @Override
    public void initData() {
        setContentView(R.layout.content_my_camera);
        mFile = new File(Environment.getExternalStorageDirectory() + File.separator + "mycamera");
        mFile.mkdirs();
        btn_again = (Button) findViewById(R.id.btn_again);
        btn_again.setOnClickListener(this);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
        fl_load_image = (RelativeLayout) findViewById(R.id.fl_load_image);
        iv_load_image = (ImageView) findViewById(R.id.iv_load_image);
        ib_take_picture = (ImageButton) findViewById(R.id.ib_take_picture);
        ib_take_picture.setOnClickListener(this);
        sv_camera = (SurfaceView) findViewById(R.id.sv_camera);
        sv_camera.setOnClickListener(this);
        mSurfaceHolder = sv_camera.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.addCallback(this);
    }

    int mViewWidth = 0;
    int mViewHeight = 0;

    private void initCamera() {
        mCamera = Camera.open();
        if (mCamera != null) {
            mCamera.setDisplayOrientation(90);
            mCamera.lock();//暂时锁定防止其他应用程序使用
            mCameraParamters = mCamera.getParameters();
            List<Camera.Size> supportedPictureSizes =
                    mCameraParamters.getSupportedPictureSizes();// 获取支持保存图片的尺寸
            if (supportedPictureSizes.get(0).width >= supportedPictureSizes.get(supportedPictureSizes.size() - 1).width) {
                for (int i = supportedPictureSizes.size() - 1; i > 0; i--) {
                    if (supportedPictureSizes.get(i).width > imageWidth) {
                        mViewWidth = supportedPictureSizes.get(i).width;
                        mViewHeight = supportedPictureSizes.get(i).height;
                        break;
                    }
                }

            } else {
                for (int i = 0; i < supportedPictureSizes.size(); i++) {
                    if (supportedPictureSizes.get(i).width > imageWidth) {
                        mViewWidth = supportedPictureSizes.get(i).width;
                        mViewHeight = supportedPictureSizes.get(i).height;
                        break;
                    }
                }
            }
            //大小，不同相机的支持的分辨率种类不一样
            mCameraParamters.setPictureSize(mViewWidth, mViewHeight);
            mCameraParamters.setPictureFormat(ImageFormat.JPEG);
            mCameraParamters.setJpegQuality(CameraProfile.QUALITY_HIGH);
            mCameraParamters.set("jpeg-quality", 100);
            try {
                mCamera.setParameters(mCameraParamters);//setParameters需要放在setPreviewDisplay()和startPreview()之前执行
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_take_picture) {
            if (mCamera != null) {
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        sv_camera.setClickable(false);//防止surfaceView误触
                        mCamera.takePicture(new MyShutterCallBack(), new MyRawPictureCallBack(), new MyPictureCallBack());
                    }
                });
            }
        }
        if (v.getId() == R.id.sv_camera) {
            if (mCamera != null) {
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {

                    }
                });
            }
        }
        if (v.getId() == R.id.btn_confirm) {
            if (mComBitmp != null) {
                if (mFile.isDirectory()) {
                    File imageFile = new File(mFile, "helloworld.jpg");
                    if (imageFile.exists()) {
                        imageFile.delete();
                    }
                    FileOutputStream fos;
                    try {
                        fos = new FileOutputStream(imageFile);
                        mComBitmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();//todo 可以选择返回文件路径
                        if (imageFile.exists()) {
                            System.out.println("camera_file_path" + imageFile.getAbsolutePath());
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        this.finish();
                    }
                }
            }
        }
        if (v.getId() == R.id.btn_again) {
            mCamera.startPreview();
            sv_camera.setClickable(true);
            fl_load_image.setVisibility(View.GONE);
            mComBitmp.recycle();
        }
    }

    //相机拍摄的时候的回调
    private class MyRawPictureCallBack implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    }

    //相机拍摄的时候的回调
    private class MyPictureCallBack implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
//            sv_camera.setClickable(true);//// TODO: 2016-12-20 surfaceView恢复点击
            Bitmap mRawBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);//图片旋转
            mComBitmp = Bitmap.createBitmap(mRawBitmap, 0, 0, mRawBitmap.getWidth(), mRawBitmap.getHeight(), matrix, true);
            //拍照完成
            fl_load_image.setVisibility(View.VISIBLE);
            iv_load_image.setImageBitmap(mComBitmp);
        }
    }

    //相机按下快门时候的回调
    private class MyShutterCallBack implements Camera.ShutterCallback {

        @Override
        public void onShutter() {

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.unlock();//相机解锁
            mCamera.stopPreview();
            mCamera.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mCamera != null) {
//            mCamera.stopPreview();
//            mCamera.release();
//        }
    }
}
