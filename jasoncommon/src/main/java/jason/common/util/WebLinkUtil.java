package com.jasonmg.salepoison.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.jasonmg.salepoison.R;
import com.jasonmg.salepoison.activity.WebviewActivity;
import com.jasonmg.salepoison.constant.C;
import com.jasonmg.salepoison.helper.SharedPreferencesHelper;

/**
 * Created by sjy2746 on 2016-12-02.
 */
public class WebLinkUtil {

    public static void moveToDeliveryWeblink(Context context){

        Intent intent;

        SharedPreferencesHelper sp = new SharedPreferencesHelper(context);

        String orderUrlViewType     = sp.getString(C.Key.ORDER_URL_VIEW_TYPE);
        String checkOrderUrl        = sp.getString(C.Key.CHECK_ORDER_URL);

        switch (orderUrlViewType) {

            case C.LinkOpenType.INNER:
                intent = new Intent(context, WebviewActivity.class);
                intent.putExtra("url", checkOrderUrl);
                intent.putExtra("title", "주문배송조회");
                context.startActivity(intent);
                break;
            case C.LinkOpenType.OUTER:
                if (TextUtils.isEmpty(checkOrderUrl)) {
                    if("null".equals(checkOrderUrl)){
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(checkOrderUrl));
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, context.getResources().getString(R.string.toast_not_link), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.toast_not_link), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public static void moveToCartWeblink(Context context){

        Intent intent;

        SharedPreferencesHelper sp = new SharedPreferencesHelper(context);

        String orderUrlViewType     = sp.getString(C.Key.ORDER_URL_VIEW_TYPE);
        String checkCartUrl        = sp.getString(C.Key.CHECK_CART_URL);

        switch (orderUrlViewType) {

            case C.LinkOpenType.INNER:
                intent = new Intent(context, WebviewActivity.class);
                intent.putExtra("url", checkCartUrl);
                intent.putExtra("title", "장바구니 확인");
                context.startActivity(intent);

                break;
            case C.LinkOpenType.OUTER:
                if (TextUtils.isEmpty(checkCartUrl)) {
                    if("null".equals(checkCartUrl)){
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(checkCartUrl));
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, context.getResources().getString(R.string.toast_not_link), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.toast_not_link), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
