package com.jh.www.imgmanage.file.service;

import com.jh.www.imgmanage.domain.FileList;
import com.jh.www.imgmanage.domain.FileManager;

import java.util.List;

public interface FileService {
    List<FileList> fileList();
    int fileInsert (FileManager fileManager);

    int fileDelete (int tfm_idx);
}
