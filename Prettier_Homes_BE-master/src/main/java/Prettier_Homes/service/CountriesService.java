package Prettier_Homes.service;

import Prettier_Homes.dto.CountriesDto;
import Prettier_Homes.dto.WordsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CountriesService {
    ResponseEntity<List<CountriesDto>> getList();

    ResponseEntity<Boolean> delete(Long id);
}
