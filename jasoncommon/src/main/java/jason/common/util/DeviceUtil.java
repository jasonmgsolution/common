package jason.common.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by jasonmg_0302 on 2016-03-17.
 */
public class DeviceUtil {
    public static String getNetworkOperatorName(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName();
    }

    public static String getBrand(){
        return Build.BRAND;
    }

    public static String getModel(){
        return Build.MODEL;
    }

    public static String getAndroidVersion(){
        return Build.VERSION.RELEASE;
    }
}
