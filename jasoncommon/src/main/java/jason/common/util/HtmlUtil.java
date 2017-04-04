package jason.common.util;

import android.text.Html;

/**
 * Created by sjy2746 on 2016-07-19.
 */
public class HtmlUtil {
    public static String generateString(String contents) {

        String genString = "";

        if (contents == null) {
            return genString;
        }

        String regex1 = "\\<.*?\\>";
        String regex2 = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
        String regex3 = "(^\\p{Space}+|\\p{Space}+$)";
        String regex4 = System.getProperty("line.separator");

        contents = contents.replaceAll(regex1, "").replaceAll(regex2, "")
                .replaceAll(regex3, "").replaceAll(regex4, "");

        genString =  Html.fromHtml(contents).toString();

        return genString;
    }

}