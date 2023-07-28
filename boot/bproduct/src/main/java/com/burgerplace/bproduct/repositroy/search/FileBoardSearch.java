package com.burgerplace.bproduct.repositroy.search;

import com.burgerplace.bproduct.dto.FileBoardListDTO;
import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;

public interface FileBoardSearch {

    PageResponseDTO<FileBoardListDTO> list(PageRequestDTO pageRequestDTO);
}
