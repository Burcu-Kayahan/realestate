package Prettier_Homes.controller;


import Prettier_Homes.dto.CitiesDto;
import Prettier_Homes.dto.CountriesDto;
import Prettier_Homes.service.CitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@Slf4j
public class CitiesController {

    @Autowired
    CitiesService service;

    @GetMapping
    public ResponseEntity<List<CitiesDto>> getList(){
        return service.getList();
    }
   @GetMapping("/{id}")
    public ResponseEntity<List<CitiesDto>> getListByCountry(@PathVariable Long id){
        return service.getListByCountry(id);
    }

    //deneme endpointleri(post,put,delete)
    @PostMapping
    public ResponseEntity<CitiesDto> create(@RequestBody CitiesDto dto) {
        return service.create(dto) ;
    }

    @PutMapping("/id")
    public ResponseEntity<CitiesDto> update (@PathVariable Long id,@RequestBody CitiesDto dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/id")
    public ResponseEntity<CitiesDto> delete (@PathVariable Long id){
        return service.delete(id);
    }







}
