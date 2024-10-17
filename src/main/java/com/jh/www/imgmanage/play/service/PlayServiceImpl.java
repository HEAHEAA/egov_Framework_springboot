package com.jh.www.imgmanage.play.service;

import com.jh.www.imgmanage.domain.Play;
import com.jh.www.imgmanage.play.mapper.PlayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PlayServiceImpl implements PlayService{

    @Autowired
    private PlayMapper playMapper;


    @Override
    public List<Play> playList(int node_id) {
        return playMapper.playList(node_id);
    }

    @Override
    public int playInsert(@RequestBody Play play) {
        play.setRunning_time(convertListIntegerToString(play.getTime())); //시간 배열
        play.setFile_list(convertListIntegerToString(play.getFile_idx())); //파일 배열
        int result = playMapper.playInsert(play);
        return result;
    }

    //여러 개의 데이터를 배열 형태로 표현
    public static String convertListIntegerToString(List<Integer> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Integer value : data)
            sb.append(value).append(",");
        if (!data.isEmpty())
            sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
}
