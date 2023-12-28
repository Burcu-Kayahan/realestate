package Prettier_Homes.service;


import Prettier_Homes.dto.CategoryPropertiesValuesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryPropertiesValuesService {

    ResponseEntity<CategoryPropertiesValuesDto> create(Long id, CategoryPropertiesValuesDto dto);

    ResponseEntity<CategoryPropertiesValuesDto> delete(Long id);

    ResponseEntity<Page<CategoryPropertiesValuesDto>> getList(int page, int size, String sort, Sort.Direction direction, String search);

    List<CategoryPropertiesValuesDto> createList(List<CategoryPropertiesValuesDto> dtoList);
}
