package org.zerock.j2.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageRequestDTO {

    // 가장 기본적이므로 Default 값필요

    private int page = 1;
    private int size = 10;

    private String type, keyword;

    public PageRequestDTO() {
        this(1, 10);
    }
    // 아무 것도 안들어오면 page1, size=10 나머지는 null

    public PageRequestDTO(int page, int size) {
        this(page, size, null, null);
    }
    // page와 size만 들어오면, 나머지 null

    public PageRequestDTO(int page, int size, String type, String keyword) {

        this.page = page <= 0 ? 1 : page;
        // 들어오는 페이지가 0,1이면 1을 반환하고 나머지는 들어오는 페이지를 반환한다.
        this.size = size < 0 || size >= 100 ? 10 : size;
        // 들어오는 사이즈가 둘 중에 하나라도 충족하면 10, 아니면 그대로 size반환

        this.type = type;
        this.keyword = keyword;
        // 둘 다 그대로 받는다.
    }

}
