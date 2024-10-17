package com.jh.www.imgmanage.weather.mapper;

import com.jh.www.imgmanage.domain.WeatherData;
import com.jh.www.imgmanage.domain.WeatherItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WeatherMapper {
    List<WeatherData> selectWeatherData();
    List<WeatherItem> selectWeatherItems();
}
