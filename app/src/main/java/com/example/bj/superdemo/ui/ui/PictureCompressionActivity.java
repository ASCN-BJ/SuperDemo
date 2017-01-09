package com.example.bj.superdemo.ui.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;
import com.example.bj.superdemo.ui.utils.viewutil.fileutils.BitmapCompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PictureCompressionActivity extends BaseActivity {
    private ImageView iv_compressionf;
    private TextView tv_content;
    private Button btn_start_compress;
    private Handler mHandler;
    private String ss = Environment.getExternalStorageState() + File.separator + "jpegg";

    @Override
    public void initData() {
        setContentView(R.layout.activity_picture_compression);
        tv_content = (TextView) findViewById(R.id.tv_content);
        iv_compressionf = (ImageView) findViewById(R.id.iv_compressionf);
        btn_start_compress = (Button) findViewById(R.id.btn_start_compress);
        btn_start_compress.setOnClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_compress) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream iso = null;
                    try {
                        iso = context.getAssets().open("qm.jpg");
                        inputstreamtofile(iso);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Luban.get(context)
                            .load(new File(ss, "123.jpg"))                     //传入要压缩的图片
                            .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onSuccess(File file) {
                                    //回调返回压缩后图片
                                    try {
                                        File files = new File("345.jpg", ss);
                                        FileOutputStream out = new FileOutputStream(files);
                                        BitmapFactory.decodeStream(new FileInputStream(file)).compress(Bitmap.CompressFormat.JPEG, 100, out);
                                        out.flush();
                                        out.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).launch();//启动压缩
                }
            }).start();
        }
    }

    public void inputstreamtofile(InputStream ins) {
        try {
            File mFile = new File(ss);
            if (mFile.isDirectory()) {
                mFile.mkdirs();
            }
            File mPicFile = new File(mFile, "123.jpg");
            OutputStream os = new FileOutputStream(mPicFile);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getFileFromAss(String fileName) {
        try {

            InputStream in = getResources().getAssets().open(fileName);
            // \Test\assets\yan.txt这里有这样的文件存在
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }
}
