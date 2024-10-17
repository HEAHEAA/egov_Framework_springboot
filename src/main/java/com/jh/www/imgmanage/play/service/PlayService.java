package com.jh.www.imgmanage.play.service;

import com.jh.www.imgmanage.domain.Play;

import java.util.List;

public interface PlayService {
    List<Play> playList(int node_id);
    int playInsert(Play play);
}
