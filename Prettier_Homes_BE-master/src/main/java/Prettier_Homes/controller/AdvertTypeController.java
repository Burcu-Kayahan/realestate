package Prettier_Homes.controller;



import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.dto.AdvertsTypeDto;
import Prettier_Homes.service.AdvertsTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advert-types")
@Slf4j
public class AdvertTypeController {

    @Autowired
    AdvertsTypeService service;

    @GetMapping
    public ResponseEntity<List<AdvertsTypeDto>> getList() {
        return service.getList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertsTypeDto> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<AdvertsTypeDto> create(@RequestBody AdvertsTypeDto dto){
        return service.create(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AdvertsTypeDto> update(@PathVariable Long id,
                                                 @RequestBody AdvertsTypeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdvertsTypeDto> delete(@PathVariable Long id) {
        return service.delete(id);
    }

}


//    findByAdvertTitleOrQuestUserEmailOrOwnerUserEmailOrStatus(search, pageable)
