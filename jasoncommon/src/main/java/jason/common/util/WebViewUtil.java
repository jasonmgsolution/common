package com.jasonmg.salepoison.util;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jasonmg.salepoison.constant.C;

import java.net.URISyntaxException;

/**
 * Created by jasonmg_0302 on 2016-03-24.
 */
public class WebViewUtil {

    public static void initWebview(WebView webView, boolean useWaybill ) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        if(useWaybill) {
            webSettings.setDisplayZoomControls(true);
            webSettings.setSupportMultipleWindows(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setSupportZoom(true);
            webSettings.setUseWideViewPort(true);
            webView.setInitialScale(1);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if ( view == null || url == null) {
                    // 처리하지 못함
                    return false;
                }

                if ( url.contains("play.google.com") ) {
                    // play.google.com 도메인이면서 App 링크인 경우에는 market:// 로 변경
                    String[] params = url.split("details");
                    if ( params.length > 1 ) {
                        url = "market://details" + params[1];
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url) ));
                        return true;
                    }
                }

                if ( url.startsWith("http:") || url.startsWith("https:") ) {

                    int extensionIndex = url.lastIndexOf(".");
                    String extension = url.substring(extensionIndex + 1, url.length());

                    for (String availableExtension : C.DownloadableExtension.EXTENSION){
                        if(availableExtension.equalsIgnoreCase(extension)) {
                            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                            return true;
                        }
                    }

                    view.loadUrl(url);
                    return false;

                } else {
                    Intent intent;

                    try {
                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    } catch (URISyntaxException e) {
                        // 처리하지 못함
                        return false;
                    }

                    try {
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        // Intent Scheme인 경우, 앱이 설치되어 있지 않으면 Market으로 연결
                        if ( url.startsWith("intent:") && intent.getPackage() != null) {
                            url = "market://details?id=" + intent.getPackage();
                            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url) ));
                            return true;
                        } else {
                            // 처리하지 못함
                            return false;
                        }
                    }
                }
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                super.onJsAlert(view, url, message, result);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setTitle("알림");
                alertDialogBuilder.setMessage(message);
                alertDialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        result.cancel();
                    }
                });
                alertDialogBuilder.show();

                return true;
            }
        });
    }
}
