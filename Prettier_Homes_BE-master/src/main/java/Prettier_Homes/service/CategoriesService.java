package Prettier_Homes.service;

import Prettier_Homes.dto.CategoriesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface CategoriesService {
    ResponseEntity<CategoriesDto> create(CategoriesDto dto);

    ResponseEntity<Page<CategoriesDto>> getList(int page, int size, String sort, Sort.Direction direction, String search);

    ResponseEntity<CategoriesDto> getById(Long id);

    ResponseEntity<CategoriesDto> update(Long id, CategoriesDto dto);

    ResponseEntity<CategoriesDto> delete(Long id);


    ResponseEntity<Page<CategoriesDto>> getListByCategories(int page, int size, String sort, Sort.Direction direction, String search);
}
