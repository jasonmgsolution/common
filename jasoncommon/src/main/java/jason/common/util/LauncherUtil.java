package com.jasonmg.salepoison.util;

import android.content.Context;

import com.jasonmg.salepoison.helper.SharedPreferencesHelper;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by jasonmg_0302 on 2016-04-27.
 */
public class LauncherUtil {

    public static void updateIconBadge(Context context, int notiCnt) {
        ShortcutBadger.applyCount(context, notiCnt);
    }

    public static void updateCurrentState(Context context){

        SharedPreferencesHelper sph = new SharedPreferencesHelper(context);
        int unreadNew = sph.getInt("unread_new");
        int winnerCnt = sph.getInt("winner_cnt");
        int replyCnt = sph.getInt("reply_cnt");
        int eventCnt = sph.getInt("event_cnt");

        LauncherUtil.updateIconBadge(context, unreadNew + winnerCnt + replyCnt + eventCnt);

    }

}
