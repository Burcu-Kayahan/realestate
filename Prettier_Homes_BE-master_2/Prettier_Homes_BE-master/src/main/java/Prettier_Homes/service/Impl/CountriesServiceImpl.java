package Prettier_Homes.service.Impl;

import Prettier_Homes.converter.CountriesMapper;
import Prettier_Homes.data.entity.CountriesEntity;
import Prettier_Homes.data.repository.CitiesRepository;
import Prettier_Homes.data.repository.CountriesRepository;
import Prettier_Homes.dto.CountriesDto;
import Prettier_Homes.dto.WordsDto;
import Prettier_Homes.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService {

  @Autowired
    CountriesRepository repository;

  @Autowired
    CountriesMapper mapper;

    @Autowired
    CitiesRepository citiesRepository;
    @Override
    public ResponseEntity<List<CountriesDto>> getList() {

        List<CountriesEntity> entities = repository.findAll();

        List<CountriesDto> resultList = mapper.toDto(entities);

       return new ResponseEntity<>(resultList, HttpStatus.OK);

    }
    @Transactional
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        citiesRepository.deleteByCountryId(id);
        repository.deleteById(id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
