package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.CitiesMapper;
import Prettier_Homes.data.entity.CitiesEntity;
import Prettier_Homes.data.repository.CitiesRepository;
import Prettier_Homes.dto.CitiesDto;
import Prettier_Homes.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    CitiesRepository repository;

    @Autowired
    CitiesMapper mapper;

    @Override
    public ResponseEntity<List<CitiesDto>> getList() {

        List<CitiesEntity> entities = repository.findAll();
        List<CitiesDto> resultList = mapper.toDto(entities);
        return new ResponseEntity<>(resultList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CitiesDto> create(CitiesDto dto) {
        CitiesEntity entity = mapper.toEntity(dto);
        CitiesEntity savedEntity = repository.save(entity);
        CitiesDto savedDto = mapper.toDto(savedEntity);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CitiesDto> update(Long id, CitiesDto dto) {
        Optional<CitiesEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            CitiesEntity existingEntity = entity.get();
            CitiesEntity updatedEntity = mapper.toEntity(dto);
            existingEntity.setName(updatedEntity.getName());
            existingEntity.setCountry(updatedEntity.getCountry());
            CitiesEntity savedEntity = repository.save(existingEntity);
            CitiesDto savedDto = mapper.toDto(savedEntity);

            return new ResponseEntity<>(savedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<CitiesDto> delete(Long id) {
        Optional<CitiesEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
          repository.deleteById(id);
          return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<List<CitiesDto>> getListByCountry(Long id) {
        List<CitiesDto> resultList = mapper.toDto(repository.findByCountryId(id));
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

}
