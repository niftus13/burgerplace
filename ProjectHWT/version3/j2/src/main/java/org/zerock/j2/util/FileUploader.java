package org.zerock.j2.util;

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

    // 예외 정의 파일이 없을때 예외발생
    public static class UploadException extends RuntimeException {

        public UploadException(String msg) {
            super(msg);
        }
    }

    @Value("${org.zerock.upload.path}")
    private String path;
    // path 경로를 지정

    public List<String> uploadFiles(List<MultipartFile> files, boolean makeThumbnail) {
        // 들어오는 매개변수는 두 개, 리스트 형식 files, boolean형태의 썸네일 여부

        if (files == null || files.size() == 0) {
            throw new UploadException("Files do not exist");
        }

        List<String> uploadFileNames = new ArrayList<>();
        // 배열 형식의 리스트 uploadFileNames를 만든다.

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
                // 들어온 파일들을 처리하여 저장한다.

            } catch (Exception e) {
                throw new UploadException("Upload Fail: " + e.getMessage());
            }
        }

        return uploadFileNames;
        // 이후 반환한다.
    }


    public void removeFiles(List<String> fileNames){
        // list/ string  fileNames가 매개변수로 들어온다.

        if(fileNames== null ||fileNames.size()==0){
            return; // 만약 파일이름이 널이거나 사이즈가 0이면 아무것도 하지않고 반환한다.
        }
        // 만약 fileNames가 있다면 들어온 수만큼 반복문을 실행한다.
        for (String fname: fileNames)
        {

            File original = new File(path, fname);
            File thumb = new File(path, "s_"+fname);
            // 들어온 파일 하나 당 original, thumb를 만든다.

            if (thumb.exists()){
                thumb.delete();
            } // thumb가 있다면 지워버리고
            original.delete();
            // 오리지널이 있다면 지워버린다.
        }
    }

}
