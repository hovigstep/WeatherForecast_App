package com.weatherforecast_app.weatherforecast_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WelcomeFragment extends Fragment {
    private WebView welcomeWebView;
    private boolean mIsWebViewAvailable;

    public WelcomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (welcomeWebView != null) {
            welcomeWebView.destroy();
        }
        welcomeWebView = new WebView(getContext());
        mIsWebViewAvailable = true;
        welcomeWebView.loadUrl("file:///android_asset/animation.html");
        return welcomeWebView;
    }
    @Override
    public void onPause() {
        super.onPause();
        welcomeWebView.onPause();
    }
    @Override
    public void onResume() {
        welcomeWebView.onResume();
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        mIsWebViewAvailable = false;
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        if (welcomeWebView != null) {
            welcomeWebView.destroy();
            welcomeWebView = null;
        }
        super.onDestroy();
    }
    public WebView getWebView() {
        return mIsWebViewAvailable ? welcomeWebView : null;
    }
}