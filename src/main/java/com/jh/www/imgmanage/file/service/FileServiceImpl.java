package com.jh.www.imgmanage.file.service;

import com.jh.www.imgmanage.domain.FileList;
import com.jh.www.imgmanage.domain.FileManager;
import com.jh.www.imgmanage.file.mapper.FileMapper;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FileService")
public class FileServiceImpl extends EgovAbstractServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileList> fileList() {
        return fileMapper.fileList();
    }

    @Override
    public int fileInsert(FileManager fileManager) {
        return fileMapper.fileInsert(fileManager);
    }


}
