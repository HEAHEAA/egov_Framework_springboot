package com.jh.www.imgmanage.weather.service;

import com.jh.www.imgmanage.domain.WeatherData;
import com.jh.www.imgmanage.domain.WeatherItem;
import com.jh.www.imgmanage.weather.mapper.WeatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{
    @Autowired
    private WeatherMapper weatherMapper;

    @Override
    public List<WeatherData> selectWeatherData() {
        return weatherMapper.selectWeatherData();
    }

    @Override
    public List<WeatherItem> selectWeatherItems() {
        return weatherMapper.selectWeatherItems();
    }
}
