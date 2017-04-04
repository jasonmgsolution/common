package jason.common.util;

import android.text.TextUtils;

/**
 * Created by sjy2746 on 2016-09-23.
 */
public class TextUtil {

    public static String getTwoDigitNumber(long number) {
        if (number >= 0 && number < 10) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

    public static String setRateShare(String rate) {

        String rate_text = rate;

        if (TextUtils.isEmpty(rate_text))
            rate_text = "";

        if ("0".equals(rate))
            rate_text = "";

        rate_text = rate_text + "%할인";

        return rate_text;
    }


}
