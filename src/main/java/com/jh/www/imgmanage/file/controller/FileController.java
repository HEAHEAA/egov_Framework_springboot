package com.jh.www.imgmanage.file.controller;

import com.jh.www.imgmanage.domain.FileList;
import com.jh.www.imgmanage.domain.FileManager;
import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file/*")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("list")
    public List<FileList> fileList() {
        return fileService.fileList();
    }

    @PostMapping("/insert")
    public ResultModel<Object> fileInsert(@RequestParam("files") MultipartFile[] files) {
        ResultModel<Object> resultModel = new ResultModel<>();
        List<String> fileNames = new ArrayList<>();
        String currentDirectory = System.getProperty("user.dir");
        for (MultipartFile file : files) {
            try {
                String filepath = currentDirectory + "/files/playlist/";
                UUID uuid = UUID.randomUUID();
                String filename = ("" + uuid + "_" + file.getOriginalFilename()).toLowerCase();
                fileNames.add(filename);
                FileManager fileManager = new FileManager();
                fileManager.setOrigin_file_name(file.getOriginalFilename());
                fileManager.setUpload_file_name(filename);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(filepath, new String[0]);
                Files.write(path, bytes, new java.nio.file.OpenOption[0]);
                fileService.fileInsert(fileManager);
                resultModel.setMessage("파일 업로드 성공");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        resultModel.setData(fileNames);
        resultModel.setSuccess(true);
        return resultModel;
    }

    @PostMapping("/delete")
    public ResultModel<Object> fileDelete(@RequestBody Map<String, Integer> map) {
        ResultModel<Object> resultModel = new ResultModel<>();
        int tfmIdx = map.get("tfm_idx");
        resultModel.setData(fileService.fileDelete(tfmIdx));
        resultModel.setSuccess(true);
        resultModel.setMessage(map.get("tfm_idx") + "번 삭제 완료");
        return resultModel;
    }


}
