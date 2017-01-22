package com.cxb.accountbooklibrary.unit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;

import com.cxb.accountbooklibrary.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class ImageUtil {

	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.btn_red_library) // 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.btn_red_library) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.btn_red_library) // 设置图片加载或解码过程中发生错误显示的图片
		.displayer(new FadeInBitmapDisplayer(100))
		.resetViewBeforeLoading(false) // default 设置图片在加载前是否重置、复位
		.delayBeforeLoading(1000) // 下载前的延迟时间
		.cacheInMemory(false) // default 设置下载的图片是否缓存在内存中
		.cacheOnDisk(false) // default 设置下载的图片是否缓存在SD卡中
		//.preProcessor(...)
		//.postProcessor(...)
		//.extraForDownloader(...)
		.considerExifParams(false) // default
		.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
		.bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
		//.decodingOptions(...) // 图片的解码设置
		.displayer(new SimpleBitmapDisplayer()) // default 还可以设置圆角图片new RoundedBitmapDisplayer(20)
		//.displayer(new RoundedBitmapDisplayer(13)).handler(new Handler()) // default
		.build();
	
	public static Bitmap loadSimpleBitmap(String path, int imgWidth,
			int imgHeight) {
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int scaleX = (int) Math.ceil((float) options.outWidth / imgWidth);
		int scaleY = (int) Math.ceil((float) options.outHeight / imgHeight);
		options.inJustDecodeBounds = false;
		options.inSampleSize = scaleX > scaleY ? scaleX : scaleY;
		return BitmapFactory.decodeFile(path, options);
	}

	public static Bitmap loadSimpleBitmap(Bitmap bitmap, int imgWidth,
			int imgHeight) {
		int bmpWidth = bitmap.getWidth();
		int bmpHeight = bitmap.getHeight();
		float scaleWidth = (float) imgWidth / bmpWidth;
		float scaleHeight = (float) imgHeight / bmpHeight;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth,
				bmpHeight, matrix, false);
		bitmap.recycle();
		return resizeBitmap;
	}
	
	   public static Bitmap compressImageFromFile(File f)  {
	        BitmapFactory.Options newOpts = new BitmapFactory.Options();
	        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
	        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), newOpts);
	        newOpts.inJustDecodeBounds = false;
	        int w = newOpts.outWidth;
	        int h = newOpts.outHeight;
	        float hh = 1200f;//
	        float ww = 900f;//
	        int be = 1;
	        if (w >= h && w >= ww) {
	            be = (int) (newOpts.outWidth / ww);
	        } else if (w < h && h >= hh) {
	            be = (int) (newOpts.outHeight / hh);
	        }
	        if (be <= 0)
	            be = 1;
	        newOpts.inSampleSize = be;// 设置采样率
	        newOpts.inPreferredConfig = Config.RGB_565;// 该模式是默认的,可不设
	        bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), newOpts);
	        return bitmap;
	    }
	   
	   public static byte[] compressImage(Bitmap bmp) {
		
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中。
			bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
			int options = 100;
			// 循环判断压缩后图片是否大于1024kb，如果大于则继续压缩。
			while (baos.toByteArray().length / 1024 > 150) {
				// 重置baos。
				baos.reset();
				// 这里压缩options%，把压缩后的数据存放到baos中。
				bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
				if(options<=10){
					break;
				}	
				// 每次都减少10。
				if (options > 10) {
					options -= 10;
				}
				Log.e("baos.toByteArray().length / 1024", baos.toByteArray().length / 1024+"");
			}
			// 把压缩后的数据baos存放到ByteArrayInputStream中。
			// ByteArrayInputStream bais = new
			// ByteArrayInputStream(baos.toByteArray());
			// 生成图片。
			// Bitmap bitmap = BitmapFactory.decodeStream(bais, null, null);
			return baos.toByteArray();
		}


	/**
	 * 图片质量压缩
	 * 
	 * @param image
	 * @return
	 */
	public static byte[] compressImage(File f) {
		Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中。
		bmp.compress(Bitmap.CompressFormat.JPEG, 80, baos);
		int options = 100;
		// 循环判断压缩后图片是否大于1024kb，如果大于则继续压缩。
		while (baos.toByteArray().length / 1024 > 200) {
			// 重置baos。
			baos.reset();
			// 这里压缩options%，把压缩后的数据存放到baos中。
			bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
			if(options<=10){
				break;
			}	
			// 每次都减少10。
			if (options > 10) {
				options -= 10;
			}
			Log.e("baos.toByteArray().length / 1024", baos.toByteArray().length / 1024+"");
		}
		// 把压缩后的数据baos存放到ByteArrayInputStream中。
		// ByteArrayInputStream bais = new
		// ByteArrayInputStream(baos.toByteArray());
		// 生成图片。
		// Bitmap bitmap = BitmapFactory.decodeStream(bais, null, null);
		return baos.toByteArray();
	}

	/**
	 * 压缩图片
	 * 
	 * @param bitmap
	 *            源图片
	 * @param width
	 *            想要的宽度
	 * @param height
	 *            想要的高度
	 * @param isAdjust
	 *            是否自动调整尺寸, true图片就不会拉伸，false严格按照你的尺寸压缩
	 * @return Bitmap
	 */
	public static Bitmap reduceBitmp(Bitmap bitmap, int width, int height,
			boolean isAdjust) {
		// 如果想要的宽度和高度都比源图片小，就不压缩了，直接返回原图
		if (bitmap.getWidth() < width && bitmap.getHeight() < height) {
			return bitmap;
		}
		// 根据想要的尺寸精确计算压缩比例, 方法详解：public BigDecimal divide(BigDecimal divisor,
		// int scale, int roundingMode);
		// scale表示要保留的小数位, roundingMode表示如何处理多余的小数位，BigDecimal.ROUND_DOWN表示自动舍弃
		float sx = new BigDecimal(width).divide(
				new BigDecimal(bitmap.getWidth()), 4, BigDecimal.ROUND_DOWN)
				.floatValue();
		float sy = new BigDecimal(height).divide(
				new BigDecimal(bitmap.getHeight()), 4, BigDecimal.ROUND_DOWN)
				.floatValue();
		if (isAdjust) {// 如果想自动调整比例，不至于图片会拉伸
			sx = (sx < sy ? sx : sy);
			sy = sx;// 哪个比例小一点，就用哪个比例
		}
		Matrix matrix = new Matrix();
		matrix.postScale(sx, sy);// 调用api中的方法进行压缩，就大功告成了
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, false);
	}

	/**
	 * 将Bitmap保存到本地
	 * 
	 * @param bitmap
	 *            要保存的Bitmap
	 * @param filePath
	 *            文件存放路径
	 */
	public static void saveBitmap(Bitmap bitmap, String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 压缩后的图片集合
	public static List<File> listFile(List<File> fileList) {
		List<File> tmps = new ArrayList<File>();
		byte[] b = null;
		for (int i = 0; i < fileList.size(); i++) {
			//b = ImageUtil.compressImage(fileList.get(i));
			b = ImageUtil.compressImage(ImageUtil.compressImageFromFile(fileList.get(i)));
			File file = null;
			try {
				file = new File(Environment.getExternalStorageDirectory()
						+ "/6d_photos" + "/tmp/tmp_" + i + ".jpg");
				File dir = new File(Environment.getExternalStorageDirectory()
						+ "/6d_photos/tmp");
				if(!dir.exists()){
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(b);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			tmps.add(file);
		}
		return tmps;
	}

}
