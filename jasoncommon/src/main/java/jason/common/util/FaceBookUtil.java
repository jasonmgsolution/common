package com.jasonmg.salepoison.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

/**
 * Created by jasonmg_0302 on 2016-03-21.
 */
public class FaceBookUtil {

    public static void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();
    }

    public static void logout(){
        LoginManager.getInstance().logOut();
    }

    public static void shareByIntent(Context context, String text){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);

        String facebookPackageName = "com.facebook.katana";

        Intent faceIntent = context.getPackageManager().getLaunchIntentForPackage(facebookPackageName);

        if (faceIntent != null) {
            intent.setPackage(facebookPackageName);
        } else {
            // 페이스북 웹을 통해 공유
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + text;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        context.startActivity(intent);
    }
}
