package com.jh.www.imgmanage.play.service;

import com.jh.www.imgmanage.domain.Play;
import com.jh.www.imgmanage.play.mapper.PlayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayServiceImpl implements PlayService{

    @Autowired
    private PlayMapper playMapper;


    @Override
    public List<Play> playList(int node_id) {
        return playMapper.playList(node_id);
    }
}
