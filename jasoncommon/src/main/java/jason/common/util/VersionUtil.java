package jason.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by jasonmg_0302 on 2016-03-17.
 */
public class VersionUtil {

    /**
     * Version Name을 반환한다.
     * @param context
     * @return
     */
    public static String getVersionName(Context context){

        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Version Code를 반환한다.
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){

        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
