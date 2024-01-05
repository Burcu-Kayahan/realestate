package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.CategoryProValuesMapper;
import Prettier_Homes.data.entity.CategoryPropertiesValuesEntity;
import Prettier_Homes.data.repository.CategoryPropertiesValueRepository;
import Prettier_Homes.dto.CategoryPropertiesValuesDto;
import Prettier_Homes.service.CategoryPropertiesValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryPropertiesValuesServiceImpl implements CategoryPropertiesValuesService {

    @Autowired
    CategoryPropertiesValueRepository repository;

    @Autowired
    CategoryProValuesMapper mapper;

    @Override
    public ResponseEntity<CategoryPropertiesValuesDto> create(Long id, CategoryPropertiesValuesDto dto) {

        CategoryPropertiesValuesEntity entity = mapper.toEntity(dto);
        CategoryPropertiesValuesDto result = mapper.toDto(repository.save(entity));
        return new ResponseEntity<> (result, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<CategoryPropertiesValuesDto> delete(Long id) {
        Optional<CategoryPropertiesValuesEntity> exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(mapper.toDto(exists.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CategoryPropertiesValuesDto>> getList(int page, int size, String sort, Sort.Direction direction, String search) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
        Page<CategoryPropertiesValuesEntity> entities = repository.findAll(pageable);
        Page<CategoryPropertiesValuesDto> results = entities.map(entity -> mapper.toDto(entity));
        results = new PageImpl<>(results.getContent(), pageable, entities.getTotalElements());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public List<CategoryPropertiesValuesDto> createList(List<CategoryPropertiesValuesDto> dtoList) {
        List<CategoryPropertiesValuesEntity> entities = mapper.toEntity(dtoList);
        return mapper.toDto( repository.saveAll(entities));
    }
}
