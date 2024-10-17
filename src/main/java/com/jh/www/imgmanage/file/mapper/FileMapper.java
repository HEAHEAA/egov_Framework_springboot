package com.jh.www.imgmanage.file.mapper;

import com.jh.www.imgmanage.domain.FileList;
import com.jh.www.imgmanage.domain.FileManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    List<FileList> fileList();

    int fileInsert (FileManager fileManager);
    int fileDelete (int tfm_idx);

}
