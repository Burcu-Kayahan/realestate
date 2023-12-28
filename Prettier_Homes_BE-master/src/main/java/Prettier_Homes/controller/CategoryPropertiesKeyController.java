package Prettier_Homes.controller;


import Prettier_Homes.dto.CategoryPropertiesKeyDto;
import Prettier_Homes.service.CategoryPropertiesKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propertieskey")
@Slf4j
public class CategoryPropertiesKeyController {

    @Autowired
    CategoryPropertiesKeyService service;

    @PostMapping
    public ResponseEntity<CategoryPropertiesKeyDto> create(@RequestBody CategoryPropertiesKeyDto dto) {
        return service.create( dto);
    }


    @GetMapping("/getAll") //bu endpoint adresine bakılacak
    public ResponseEntity<Page<CategoryPropertiesKeyDto>> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                                  @RequestParam(value = "sort", defaultValue = "name") String sort,
                                                  @RequestParam(value = "type", defaultValue = "desc") Sort.Direction direction ,
                                                  @RequestParam(name ="q", required = false) String search) {
        return service.getList(page,size,sort,direction,search);
    }
    @GetMapping("/getAllByCatgory/{id}") //bu endpoint adresine bakılacak
    public ResponseEntity<List<CategoryPropertiesKeyDto>> getListByCatgory(@PathVariable Long id) {
        return service.getListByCatgory(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryPropertiesKeyDto> getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryPropertiesKeyDto> update(@PathVariable Long id,
                                           @RequestBody CategoryPropertiesKeyDto dto) {
        return service.update(id, dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryPropertiesKeyDto> delete(@PathVariable Long id) {
        return service.delete(id);
   }
}
