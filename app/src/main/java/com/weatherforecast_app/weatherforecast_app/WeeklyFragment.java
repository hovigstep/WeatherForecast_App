package com.weatherforecast_app.weatherforecast_app;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeeklyFragment extends Fragment {
    private static final String ARG_CITY = "param1";

    private String mCity;

    @BindView(R.id.weekly_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.textCity)
    TextView textCity;

    @BindView(R.id.viewWeather)
    RecyclerView viewWeather;

    public WeeklyFragment() {
        // Required empty public constructor
    }
    public static WeeklyFragment newInstance(String location) {
        WeeklyFragment fragment = new WeeklyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, location);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_weekly, container, false);
        ButterKnife.bind(this, view);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        refreshData();
        return view;
    }
}
