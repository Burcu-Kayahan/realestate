package Prettier_Homes.service;

import Prettier_Homes.dto.CitiesDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CitiesService {
    ResponseEntity<List<CitiesDto>> getList();

    ResponseEntity<CitiesDto> create(CitiesDto dto);

    ResponseEntity<CitiesDto> update(Long id, CitiesDto dto);

    ResponseEntity<CitiesDto> delete(Long id);

    ResponseEntity<List<CitiesDto>> getListByCountry(Long id);
}
