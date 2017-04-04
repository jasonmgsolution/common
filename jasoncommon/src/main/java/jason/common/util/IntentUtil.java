package com.jasonmg.salepoison.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.jasonmg.salepoison.activity.MainActivity;

/**
 * Created by jasonmg_0302 on 2016-03-14.
 */
public class IntentUtil {

    public static void gotoMarket(Context context){
        if(!isPlayStoreInstalled(context)) {
            Toast.makeText(context, "Google Playstore is not installed.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName()));
        context.startActivity(intent);
    }

    public static boolean isPlayStoreInstalled(Context context){
        try {
            context.getPackageManager().getPackageInfo(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void openUrl(Context context, String url){
        if(!URLUtil.isValidUrl(url)) {
            Toast.makeText(context, "Not valid url address", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent =  new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void dial(Context context, String number){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
        context.startActivity(intent);
    }

    public static void moveToIntentTypgoList(Intent intent, Context context, String typego){

        intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("type", typego);
        context.startActivity(intent);
    }

    public static void moveToIntentSpecialList(Intent intent, Context context, String typego, String special_idx){

        intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("type", typego);
        if (!TextUtils.isEmpty(special_idx))
            intent.putExtra("special_idx", special_idx);
        context.startActivity(intent);
    }

    public static void moveToIntentProductList(Intent intent, Context context, String typego, String cate_no) {

        intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("type", typego);
        intent.putExtra("cate", cate_no);
        context.startActivity(intent);
    }

}
