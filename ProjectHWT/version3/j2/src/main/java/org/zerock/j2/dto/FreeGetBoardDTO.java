package org.zerock.j2.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FreeGetBoardDTO {

    private long freeBno;

    private String freeTitle;

    private String freeContent;

    private String nickname;

        // DB 처리용
    @Builder.Default
    private List<String> freeImages = new ArrayList<>();

    // 등록/수정 업로드된  파일 데이터를 수집하는 용도
    @Builder.Default
    private List<MultipartFile> freeFiles = new ArrayList<>();



    
    
}
