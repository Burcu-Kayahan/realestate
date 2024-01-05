package Prettier_Homes.controller;


import Prettier_Homes.dto.CitiesDto;
import Prettier_Homes.dto.DistrictsDto;
import Prettier_Homes.service.DistrictsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/districts")
@Slf4j
public class DistrictsController {


    @Autowired
    DistrictsService service;

    @GetMapping
    public ResponseEntity<List<DistrictsDto>> getList() {
        return service.getList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<DistrictsDto>> getListByCityId(@PathVariable Long id) {
        return service.getListByCityId(id);
    }

    //deneme endpointleri(post,put,delete)

    @PostMapping("/districts")
    public ResponseEntity<DistrictsDto> create(@RequestBody DistrictsDto dto) {
        return service.create(dto);
    }

    @PutMapping("/id")
    public ResponseEntity<DistrictsDto> update(@PathVariable Long id,@RequestBody DistrictsDto dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/id")
    public ResponseEntity<DistrictsDto>delete(@PathVariable Long id){
        return service.delete(id);
    }


}
