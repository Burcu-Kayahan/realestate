package Prettier_Homes.controller;

import Prettier_Homes.dto.ImagesDto;
import Prettier_Homes.service.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@Slf4j
public class ImagesController {

    @Autowired
    ImagesService service;

    @GetMapping("/{id}")
 // @PreAuthorize("hasAuthority('ANONYMOUS')")   ==> daha sonra eklenecek
    public ResponseEntity<ImagesDto> get(@PathVariable Long id  ){ // null olmaması eklenecek
        return service.get(id);
    }

    @PostMapping("/{advertId}")
 // @PreAuthorize("hasAuthority('CUSTOMER','MANAGER','ADMIN')")    ==> daha sonra eklenecek
    public ResponseEntity<Boolean> create(@PathVariable Long advertId, @RequestBody List <ImagesDto> imagesDto) {
        System.out.println("img controller");
        return service.create(advertId,imagesDto);
    }



    @PutMapping("/{imagesId}")
    public ResponseEntity<ImagesDto> update(@PathVariable Long imagesId,
                                           @RequestBody ImagesDto images) {
        return service.update(imagesId, images);
    }

    @DeleteMapping("/{imagesId}")
    public ResponseEntity<ImagesDto> delete(@PathVariable Long imagesId) {
        return service.delete(imagesId);
    }


}
