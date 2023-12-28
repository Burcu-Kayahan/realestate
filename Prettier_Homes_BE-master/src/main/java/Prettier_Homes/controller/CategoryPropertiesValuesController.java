package Prettier_Homes.controller;

import Prettier_Homes.dto.CategoryPropertiesValuesDto;
import Prettier_Homes.service.CategoryPropertiesValuesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propertiesvalue")
@Slf4j
public class CategoryPropertiesValuesController {

  @Autowired
  CategoryPropertiesValuesService service;

  @PostMapping
  public ResponseEntity<CategoryPropertiesValuesDto> create(@RequestBody CategoryPropertiesValuesDto dto, Long id) {
    return service.create(id, dto);
  }


  @GetMapping("/getAll") //bu endpoint adresine bakılacak
  public ResponseEntity<Page<CategoryPropertiesValuesDto>> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                                @RequestParam(value = "sort", defaultValue = "value") String sort,
                                                                @RequestParam(value = "type", defaultValue = "desc") Sort.Direction direction ,
                                                                @RequestParam(name ="q", required = false) String search) {
    return service.getList(page,size,sort,direction,search);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CategoryPropertiesValuesDto> delete(@PathVariable Long id) {
    return service.delete(id);
  }

}
