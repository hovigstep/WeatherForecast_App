package com.weatherforecast_app.weatherforecast_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyFragment extends Fragment {
    private static final String ARG_CITY = "param1";

    private String mCity;

    @BindView(R.id.daily_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.textCity)
    TextView textCity;

    @BindView(R.id.ivWeather)
    ImageView ivWeather;

    @BindView(R.id.textTemp)
    TextView textTemp;

    @BindView(R.id.textTempMin)
    TextView textTempMin;

    @BindView(R.id.textTempMax)
    TextView textTempMax;

    @BindView(R.id.textTempSeparator)
    TextView textTempSeparator;

    public DailyFragment() {
        // Empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = getArguments().getString(ARG_CITY);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating layout for fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        ButterKnife.bind(this, view);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        refreshData();

        return view;
    }
    public void changeCity(String city){
        mCity = city;
        refreshData();
    }



}
