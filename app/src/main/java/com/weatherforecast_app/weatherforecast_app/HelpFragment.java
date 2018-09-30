package com.weatherforecast_app.weatherforecast_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HelpFragment extends Fragment {
    private WebView mWebView;
    private boolean mIsWebViewAvailable;

    public HelpFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mWebView != null) {
            mWebView.destroy();
        }
        mWebView = new WebView(getContext());
        mIsWebViewAvailable = true;
        mWebView.loadUrl("file:///android_asset/help.html");
        return mWebView;
    }
    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
    }
    @Override
    public void onResume() {
        mWebView.onResume();
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        mIsWebViewAvailable = false;
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }
}