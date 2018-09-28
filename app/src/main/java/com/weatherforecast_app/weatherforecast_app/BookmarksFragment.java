package com.weatherforecast_app.weatherforecast_app;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarksFragment extends Fragment{
    private static final String ARG_CITY = "param1";
    private String mCity;

    @BindView(R.id.bookmarks_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = getArguments().getString(ARG_CITY);
        }
    }

    public BookmarksFragment(){

    }
    public static BookmarksFragment newInstance(String city) {
        BookmarksFragment fragment = new BookmarksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating layout for fragment
        View view = inflater.inflate(R.layout.bookmarks_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
