package com.jh.www.imgmanage.weather.service;

import com.jh.www.imgmanage.domain.WeatherData;
import com.jh.www.imgmanage.domain.WeatherItem;

import java.util.List;

public interface WeatherService {
    List<WeatherData> selectWeatherData();
    List<WeatherItem> selectWeatherItems();
}
