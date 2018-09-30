package com.weatherforecast_app.weatherforecast_app.util;

import com.weatherforecast_app.weatherforecast_app.R;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OpenWeatherSingleton {
    //Api Key
    private String appid = "c6e381d8c7ff98f0fee43775817cf6ad";
    private String api_server = "http://api.openweathermap.org/data/2.5/";
    private OkHttpClient client = new OkHttpClient();
    private static OpenWeatherSingleton instance = null;

    public OpenWeatherSingleton() {
        //Empty Constructor
    }

    public static OpenWeatherSingleton getInstance(){
        if(instance == null){
            instance = new OpenWeatherSingleton();
        }
        return instance;
    }

    public Call getCurrentWeatherByCity(String cityName){
        return getCurrentWeatherByCity(cityName, "metric");
    }

    public Call getCurrentWeatherByCity(String cityName, String unit){
        Request request = new Request.Builder()
                .url(api_server + String.format("weather?q=%s&units=%s&appid=%s", cityName, unit, appid))
                .build();

        return client.newCall(request);
    }

    public Call getForecastByCity(String cityName){
        return getForecastByCity(cityName, "metric", 5);
    }

    public Call getForecastByCity(String cityName, String unit, int cnt){
        Request request = new Request.Builder()
                .url(api_server + String.format("forecast/daily?q=%s&units=%s&cnt=%d&appid=%s", cityName, unit, cnt, appid))
                .build();

        return client.newCall(request);
    }
    public int getIconFromWeather(String weather){
        weather = weather.toLowerCase();
        if(weather.contains("cloud")) return R.drawable.zzz_weather_cloudy;
        else if(weather.contains("snow")) return R.drawable.zzz_weather_snowy;
        else if(weather.contains("rain") || weather.contains("drizzle")) return R.drawable.zzz_weather_rainy;
        else if(weather.contains("fog")) return R.drawable.zzz_weather_fog;
        else if(weather.contains("lightning")) return R.drawable.zzz_weather_lightning;
        return R.drawable.zzz_weather_sunny;
    }
}
