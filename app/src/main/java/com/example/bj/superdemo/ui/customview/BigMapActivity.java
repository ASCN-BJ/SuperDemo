package com.example.bj.superdemo.ui.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.example.bj.superdemo.R;
import com.example.bj.superdemo.ui.BaseActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * 加载大图的activity
 */
public class BigMapActivity extends BaseActivity {
    private ImageView iv_big_img;
    private ImageView iv_normal_img;

    @Override
    public void initData() {
        setContentView(R.layout.activity_big_map);
        iv_big_img = (ImageView) findViewById(R.id.iv_big_img);
        iv_normal_img = (ImageView) findViewById(R.id.iv_normal_img);
        try {
            iv_normal_img.setImageBitmap(BitmapFactory.decodeStream(getAssets().open("qm.jpg")));
        } catch (IOException io) {
            io.printStackTrace();
        }
        decodeImg();
    }

    private InputStream is;

    /**
     * 解析图片
     */
    private void decodeImg() {
        try {
            is = getAssets().open("qm.jpg");
            //获取图片的宽和高
            BitmapFactory.Options bfOpt = new BitmapFactory.Options();
            bfOpt.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, bfOpt);
            int width = bfOpt.outWidth;
            int height = bfOpt.outHeight;
            //将图片显示在中心区域
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = decoder.decodeRegion(new  Rect(width / 2 - 100, height / 2 - 100, width / 2 + 100, height / 2 + 100), options);
            iv_big_img.setImageBitmap(bitmap);
        } catch (Exception io) {
            io.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                is = null;
            }
        }
    }


    @Override
    public void onClick(View v) {

    }
}
