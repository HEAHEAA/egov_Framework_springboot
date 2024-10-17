package com.jh.www.imgmanage.weather.controller;

import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather/*")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("item")
    public ResultModel<Object> selectWeatherItems() {
        ResultModel<Object> resultModel = new ResultModel<>();
        resultModel.setData(weatherService.selectWeatherItems());
        resultModel.setMessage("기상 데이터");
        resultModel.setSuccess(true);
        return resultModel;
    }

    @GetMapping("data")
    public ResultModel<Object> selectWeatherData() {
        ResultModel<Object> resultModel = new ResultModel<>();
        resultModel.setData(weatherService.selectWeatherData());
        resultModel.setMessage("기상 데이터");
        resultModel.setSuccess(true);
        return resultModel;
    }

}
