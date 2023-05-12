package com.kt.coffee.cat.utils;

import android.content.Context;
import android.widget.Toast;

import com.kt.coffee.cat.BaseApplication;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

public class Tool {

    /**
     * 通过状态栏资源id来获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarByResId(Context context) {
        int height = 0;
        //获取状态栏资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            try {
                height = context.getResources().getDimensionPixelSize(resourceId);
            } catch (Exception e) {
            }
        }
        return height;
    }


    /**
     * read input stream data convert to string.
     *
     * @param inputStream
     * @return
     */
    public static String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void KToast(Context context,String msg){
        Toast.makeText(context, msg+"", Toast.LENGTH_SHORT).show();
    }


}
