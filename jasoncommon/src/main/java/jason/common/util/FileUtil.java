package com.jasonmg.salepoison.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jasonmg_0302 on 2016-04-21.
 */
public class FileUtil {

    public static File getOutputPhotoFile(Context context) {
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getPackageName());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Log.e("error", "Failed to create storage directory.");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        return new File(directory.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    public static File getOutputPhotoFileInternal(Context context){
        File directory = new File(Environment.getDataDirectory(), context.getPackageName());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Log.e("error", "Failed to create storage directory.");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        return new File(directory.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }
}
