package com.weatherforecast_app.weatherforecast_app.model;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class WeatherAdapter extends ArrayAdapter<WeatherModel> {
    public WeatherAdapter(Context context, List<WeatherModel> objects) {
        super(context, 0, objects);
    }
}
