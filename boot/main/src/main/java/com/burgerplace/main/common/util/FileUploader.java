package com.burgerplace.main.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;


@Component
@Log4j2
public class FileUploader {
    
    public static class UploadException extends RuntimeException {

        public UploadException(String msg) {
            super(msg);
        }
    }

    @Value("${com.burgerplace.upload.path}")
    private String path;

    public List<String> uploadFiles(List<MultipartFile> files, boolean makeThumbnail) {

        if (files == null || files.size() == 0) {
            throw new UploadException("Files do not exist");
        }

        List<String> uploadFileNames = new ArrayList<>();

        log.info("path: " + path);

        log.info(files);

        // loop
        for (MultipartFile mFile : files) {

            String originalFileName = mFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String saveFileName = uuid + "_" + originalFileName;
            File saveFile = new File(path, saveFileName);

            try (InputStream in = mFile.getInputStream();
                    OutputStream out = new FileOutputStream(saveFile);) {

                FileCopyUtils.copy(in, out);
                // 썸네일이 필요할때
                if (makeThumbnail) {
                    File thumbOutFile = new File(path, "s_"+saveFileName);

                    Thumbnailator.createThumbnail(saveFile, thumbOutFile, 200, 200);
                }

                uploadFileNames.add(saveFileName);

            } catch (Exception e) {
                throw new UploadException("Upload Fail: " + e.getMessage());
            }
        }

        return uploadFileNames;
    }

    public void removeFiles(List<String> fileNames){

        if(fileNames== null ||fileNames.size()==0){
            return;
        }
        for (String fname: fileNames)
        {

            File original = new File(path, fname);
            File thumb = new File(path, "s_"+fname);

            if (thumb.exists()){
                thumb.delete();
            }
            original.delete();
        }
    }

}
