package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class WeatherItem {
    private String baseDate;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
}
