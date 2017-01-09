package com.example.bj.superdemo.ui.utils.viewutil;

import android.graphics.Paint;
import android.text.TextPaint;

/**
 * Created by bj on 2017-1-4.
 * Description：
 */

public class TextUtil {
    /**
     * 计算字符串的高度
     *
     * @param paint
     * @return
     */
    public static float getStringHeight(TextPaint paint) {
        Paint.FontMetrics metrics = paint.getFontMetrics();
        return metrics.bottom - metrics.top;
    }

    /**
     * 计算字符串的宽度
     *
     * @param paint
     * @param text
     * @return
     */
    public static float getStringWidth(TextPaint paint, String text) {
        return paint.measureText(text);
    }
}
