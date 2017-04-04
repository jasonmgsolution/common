package com.jasonmg.salepoison.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jasonmg.salepoison.R;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;
import com.kakao.util.helper.StoryProtocol;
import com.kakao.util.helper.TalkProtocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jasonmg_0302 on 2016-03-21.
 */
public class KakaoUtil {

    public static void shareKakaoTalk(Context context, String message, String buttonText, String linkUrl, String imageUrl){
        try {
            KakaoLink kakaoLink = KakaoLink.getKakaoLink(context);

            KakaoTalkLinkMessageBuilder klmb = kakaoLink.createKakaoTalkLinkMessageBuilder();
            klmb.addText(message);
            klmb.addWebButton(buttonText, linkUrl);
            if (!TextUtils.isEmpty(imageUrl)) {
                klmb.addImage(imageUrl, 480, 300);
            }
            kakaoLink.sendMessage(klmb, context);
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }


    public static void shareKakaoTalkApp(Context context, String message, String buttonText, String linkUrl, String excuteParam, String imageUrl){
        try {
            KakaoLink kakaoLink = KakaoLink.getKakaoLink(context);
            KakaoTalkLinkMessageBuilder klmb = kakaoLink.createKakaoTalkLinkMessageBuilder();

            klmb.addText(message);

            klmb.addAppButton("자세히보기",  new AppActionBuilder()
                    .addActionInfo(AppActionInfoBuilder
                            .createAndroidActionInfoBuilder()
                            .setExecuteParam(excuteParam)
                            .setMarketParam("referrer=")
                            .build())
                    .setUrl(linkUrl) // PC 카카오톡 에서 사용하게 될 웹사이트 주소
                    .build());

            kakaoLink.sendMessage(klmb, context);
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }



    public static void shareKakaoStroy(Context context, String title, String link){
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.putExtra(Intent.EXTRA_SUBJECT, shareTitle); - 으로 내용이랑 연결됨
            intent.putExtra(Intent.EXTRA_TEXT, "[ 오늘의 " +context.getString(R.string.app_name)+ " ]\n" + title + "\n" + link);
            intent.putExtra(Intent.EXTRA_TITLE, title);
            intent.setType("text/plain");
            intent.setPackage("com.kakao.story");
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "카카오 스토리가 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void showKakaoLoginDialog(Context context){
        List<AuthType> availableAuthTypes = getAvailableAuthTypes(context);
        Dialog kaKaoLoginDialog = makeKakaoLoginDialog(context, availableAuthTypes);
        kaKaoLoginDialog.show();
    }

    /**
     * 카카오 프로그램(카톡 또는 카스)의 존재 여부를 리스트로 반환
     * @param context
     * @return
     */
    public static List<AuthType> getAvailableAuthTypes(Context context){
        List<AuthType> availableAuthTypes = new ArrayList<AuthType>();
        if(TalkProtocol.existCapriLoginActivityInTalk(context, Session.getCurrentSession().isProjectLogin())){
            availableAuthTypes.add(AuthType.KAKAO_TALK);
            availableAuthTypes.add(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN);
        }
        if(StoryProtocol.existCapriLoginActivityInStory(context, Session.getCurrentSession().isProjectLogin())){
            availableAuthTypes.add(AuthType.KAKAO_STORY);
        }
        availableAuthTypes.add(AuthType.KAKAO_ACCOUNT);

        final AuthType[] selectedAuthTypes = Session.getCurrentSession().getAuthTypes();
        availableAuthTypes.retainAll(Arrays.asList(selectedAuthTypes));

        // 개발자가 설정한 것과 available 한 타입이 없다면 직접계정 입력이 뜨도록 한다.
        if(availableAuthTypes.size() == 0){
            availableAuthTypes.add(AuthType.KAKAO_ACCOUNT);
        }
        return availableAuthTypes;
    }

    public static Dialog makeKakaoLoginDialog(final Context context, List<AuthType> list){

        // 다이얼로그에 띄워줄 아이템 판별
        final List<Item> itemList = new ArrayList<Item>();
        if(list.contains(AuthType.KAKAO_TALK)) {
            itemList.add(new Item(R.string.com_kakao_kakaotalk_account, R.drawable.kakaotalk_icon, AuthType.KAKAO_TALK));
        }
        if(list.contains(AuthType.KAKAO_STORY)) {
            itemList.add(new Item(R.string.com_kakao_kakaostory_account, R.drawable.kakaostory_icon, AuthType.KAKAO_STORY));
        }
        if(list.contains(AuthType.KAKAO_ACCOUNT)){
            itemList.add(new Item(R.string.com_kakao_other_kakaoaccount, R.drawable.kakaoaccount_icon, AuthType.KAKAO_ACCOUNT));
        }
        itemList.add(new Item(R.string.com_kakao_account_cancel, 0, null)); //no icon for this one

        final Item[] items = itemList.toArray(new Item[itemList.size()]);

        // 다이얼로그 어댑터
        final ListAdapter adapter = new ArrayAdapter<Item>(context, android.R.layout.select_dialog_item, android.R.id.text1, items){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView)v.findViewById(android.R.id.text1);

                tv.setText(items[position].textId);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(15);
                tv.setGravity(Gravity.CENTER);
                if(position == itemList.size() -1) {
                    tv.setBackgroundResource(R.drawable.kakao_cancel_button_background);
                } else {
                    tv.setBackgroundResource(R.drawable.kakao_account_button_background);
                }
                tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);

                int dp5 = (int) (5 * context.getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);

                return v;
            }
        };

        // 로그인 선택 다이얼로그
        Dialog dialog = new AlertDialog.Builder(context).setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int position) {
                final AuthType authType = items[position].authType;
                if (authType != null) {
                    Session.getCurrentSession().open(authType, (Activity) context);
                }

                dialog.dismiss();
            }
        }).create();

        return dialog;
    }

    /**
     * 다이얼로그에 띄워줄 아이템 정보를 담고있는 모델
     */
    private static class Item {
        public final int textId;
        public final int icon;
        public final AuthType authType;
        public Item(final int textId, final Integer icon, final AuthType authType) {
            this.textId = textId;
            this.icon = icon;
            this.authType = authType;
        }
    }
}
