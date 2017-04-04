package com.jasonmg.salepoison.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;

/**
 * Created by jasonmg_0302 on 2016-03-22.
 */
public class UriUtil {

    /**
     * Uri를 File로 변환
     * @param uri
     * @return
     */
    public static File toFile(Uri uri){
        return new File(uri.getPath());
    }

    public static Uri getLastCaptureImageUri(Context context){
        Uri uri =null;
        String[] IMAGE_PROJECTION = {
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns._ID,
        };

        try {
            Cursor cursorImages = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    IMAGE_PROJECTION, null, null,null);
            if (cursorImages != null && cursorImages.moveToLast()) {
                uri = Uri.parse(cursorImages.getString(0)); //경로
                int id = cursorImages.getInt(1); //아이디
                cursorImages.close(); // 커서 사용이 끝나면 꼭 닫아준다.
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return uri;
    }

    public static JSONObject uriQueryParameterToJsonObject(Uri data){

        JSONObject jsonObject = new JSONObject();

        try {
            Iterator<String> names = data.getQueryParameterNames().iterator();

            while (names.hasNext()){
                String key = names.next();
                String value = data.getQueryParameter(key);
                jsonObject.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

}
