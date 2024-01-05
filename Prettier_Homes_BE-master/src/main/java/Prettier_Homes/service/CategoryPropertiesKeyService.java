package Prettier_Homes.service;

import Prettier_Homes.dto.CategoriesDto;
import Prettier_Homes.dto.CategoryPropertiesKeyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryPropertiesKeyService {

    ResponseEntity<CategoryPropertiesKeyDto> create( CategoryPropertiesKeyDto dto);

    ResponseEntity<CategoryPropertiesKeyDto> getById(Long id);

    ResponseEntity<CategoryPropertiesKeyDto> update(Long id, CategoryPropertiesKeyDto dto);

    ResponseEntity<CategoryPropertiesKeyDto> delete(Long id);

    ResponseEntity<Page<CategoryPropertiesKeyDto> > getList(int page, int size, String sort, Sort.Direction direction, String search);

    ResponseEntity<List<CategoryPropertiesKeyDto>> getListByCatgory(Long id);

    CategoriesDto savePropertiesKeyList(CategoriesDto dto, CategoriesDto result);
}