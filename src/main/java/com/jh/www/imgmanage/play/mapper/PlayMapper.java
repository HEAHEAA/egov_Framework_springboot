package com.jh.www.imgmanage.play.mapper;

import com.jh.www.imgmanage.domain.Play;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayMapper {
    List<Play> playList(int node_id);
}
