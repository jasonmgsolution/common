package jason.common.util;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pyo on 2017-11-15.
 */

public class PushLogCheckUtil {

    // 푸시 로그 텍스트 파일로 downloads 에 저장
    public static void logOutPut(String appName, Bundle data){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

        String pushLog = sdf.format(date) + "\n" + data.toString().substring(6);

        File file = new File(dirPath + "/androidPushLog_"+ appName +".txt");
        Log.e("file Path ::", file.getAbsolutePath());

        try {
            if (file.exists() == true) {
                String encodeLog = new String(pushLog.getBytes("UTF-8"), "8859_1");
                file.createNewFile();
                RandomAccessFile raf = new RandomAccessFile(file.getAbsoluteFile(), "rw");
                raf.seek(raf.length());
                raf.writeBytes("\r\n" + encodeLog);
                raf.close();

            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(pushLog.getBytes("UTF-8"));
                fileOutputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
