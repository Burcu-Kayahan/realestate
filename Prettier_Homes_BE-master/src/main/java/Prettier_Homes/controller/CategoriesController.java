package Prettier_Homes.controller;

import Prettier_Homes.dto.CategoriesDto;
import Prettier_Homes.dto.WordsDto;
import Prettier_Homes.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Slf4j
public class CategoriesController {
    @Autowired
    CategoriesService service;

    @PostMapping
    public ResponseEntity<CategoriesDto> create(@RequestBody CategoriesDto dto){
        return service.create(dto);
    }
    @GetMapping()
    public ResponseEntity<Page<CategoriesDto>> getListByCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "20") int size,
                                                       @RequestParam(value = "sort", defaultValue = "title") String sort,
                                                       @RequestParam(value = "type", defaultValue = "desc") Sort.Direction direction ,
                                                       @RequestParam(name ="q", required = false) String search)
    {
        return service.getListByCategories(page,size,sort,direction,search) ;
    }
    @GetMapping("/admin")
    public ResponseEntity<Page<CategoriesDto>> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "20") int size,
                                                       @RequestParam(value = "sort", defaultValue = "title") String sort,
                                                       @RequestParam(value = "type", defaultValue = "desc") Sort.Direction direction ,
                                                       @RequestParam(name ="q", required = false) String search)
    {
       return service.getList(page,size,sort,direction,search) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDto> getById(@PathVariable Long id){
        return service.getById(id);
    }
 @PutMapping("/{id}")
    public ResponseEntity<CategoriesDto> update(@PathVariable Long id,
                                                @RequestBody CategoriesDto dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriesDto> delete(@PathVariable Long id){return  service.delete(id);}




}
