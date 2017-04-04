package jason.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by jasonmg_0302 on 2016-05-24.
 */
public class TimeUtil {

    public static long getRemainTime(String hdDate) {
        try {

            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = transFormat.parse(hdDate);

            SimpleDateFormat sdf_curr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            sdf_curr.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String currentString = sdf_curr.format(new Date());
            Date date_curr = null;
            date_curr = sdf_curr.parse(currentString);
            return date.getTime() - date_curr.getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}
