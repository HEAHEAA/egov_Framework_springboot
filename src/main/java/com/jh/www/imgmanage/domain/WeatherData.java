package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class WeatherData {
    private int so2Grade;
    private int o3Grade;
    private int no2Grade;
    private int pm25Grade;
    private int pm10Grade;
    private int coGrade;
    private int khaiGrade;
    private String date_time;
}
