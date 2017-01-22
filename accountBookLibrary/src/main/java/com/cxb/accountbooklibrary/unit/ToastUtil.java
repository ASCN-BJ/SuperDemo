package com.cxb.accountbooklibrary.unit;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cxb.accountbooklibrary.R;

public class ToastUtil
{
    private static Toast toast;

    /**
     * 短时间显示Toast
     * 
     * @param context
     * @param message
     */
    public static void showToastShort(Context context, CharSequence message)
    {
        if (null == toast)
        {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        }
        else
        {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     * 
     * @param context
     * @param message
     */
    public static void showToastShort(Context context, int message)
    {
        if (null == toast)
        {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        }
        else
        {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     * 
     * @param context
     * @param message
     */
    public static void showToastLong(Context context, CharSequence message)
    {
        if (null == toast)
        {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        }
        else
        {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     * 
     * @param context
     * @param message
     */
    public static void showToastLong(Context context, int message)
    {
        if (null == toast)
        {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        }
        else
        {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     * 
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration)
    {
        if (null == toast)
        {
            toast = Toast.makeText(context, message, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        }
        else
        {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     * 
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration)
    {
        if (null == toast)
        {
            toast = Toast.makeText(context, message, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        }
        else
        {
            toast.setText(message);
        }
        toast.show();
    }

    /** Hide the toast, if any. */
    public static void hideToast()
    {
        if (null != toast)
        {
            toast.cancel();
        }
    }
    
    /**
     * 自定义布局结构的toast
     * @param context
     * @param message
     * @param time
     */
    public static void showCustomView(Context context, String message, int time){
    	toast = new Toast(context);
    	View view = View.inflate(context, R.layout.toast_library_view, null);
    	TextView messageInfo = (TextView) view.findViewById(R.id.messageInfo);
    	messageInfo.setText(message);
    	toast.setDuration(time);
    	toast.setView(view);
    	toast.show();
    }
}
