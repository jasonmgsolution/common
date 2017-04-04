package com.jasonmg.salepoison.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.jasonmg.salepoison.constant.C;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jasonmg_0302 on 2016-07-04.
 */
public class BandUtil {

    public static void share(Context context, String text) {
        try {
            PackageManager manager = context.getPackageManager();
            Intent bandIntent = manager.getLaunchIntentForPackage("com.nhn.android.band");

            Intent intent = null;
            if (bandIntent != null) {
                String encode = URLEncoder.encode(text, "UTF-8");
                Uri uri = Uri.parse("bandapp://create/post?text=" + encode + "&route=" + C.URL.BASE);
                intent = new Intent(Intent.ACTION_VIEW, uri);
            } else {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.band"));
            }

            context.startActivity(intent);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
