package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.CategoryProKeyMapper;
import Prettier_Homes.data.entity.CategoryPropertiesKeyEntity;
import Prettier_Homes.data.repository.CategoryPropertiesKeyRepository;
import Prettier_Homes.dto.CategoriesDto;
import Prettier_Homes.dto.CategoryPropertiesKeyDto;
import Prettier_Homes.service.CategoryPropertiesKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryPropertiesKeyServiceImpl implements CategoryPropertiesKeyService {

    @Autowired
    CategoryPropertiesKeyRepository repository;
    @Autowired
    CategoryProKeyMapper mapper;


    @Override
    public ResponseEntity<CategoryPropertiesKeyDto> create( CategoryPropertiesKeyDto dto) {
        CategoryPropertiesKeyEntity entity = mapper.toEntity(dto);
        entity.setBuiltIn(false);
        CategoryPropertiesKeyDto result = mapper.toDto(repository.save(entity));
        return new ResponseEntity<> (result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryPropertiesKeyDto> getById(Long id) {
        CategoryPropertiesKeyDto result = mapper.toDto(repository.findById(id).get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryPropertiesKeyDto> update(Long id, CategoryPropertiesKeyDto dto) {
        Optional<CategoryPropertiesKeyEntity> exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        mapper.toEntityForUpdate(dto, exists.get());
        CategoryPropertiesKeyEntity entity = repository.save(exists.get());
        CategoryPropertiesKeyDto result = mapper.toDto(entity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryPropertiesKeyDto> delete(Long id) {
        Optional<CategoryPropertiesKeyEntity> exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(mapper.toDto(exists.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CategoryPropertiesKeyDto>> getList(int page, int size, String sort, Sort.Direction direction, String search) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
        Page<CategoryPropertiesKeyEntity> entities = repository.findAll(pageable);
        Page<CategoryPropertiesKeyDto> results = entities.map(entity -> mapper.toDto(entity));
        results = new PageImpl<>(results.getContent(), pageable, entities.getTotalElements());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryPropertiesKeyDto>> getListByCatgory(Long id) {
        List<CategoryPropertiesKeyDto> resultList= mapper.toDto(repository.findByCategoryId(id));
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public CategoriesDto savePropertiesKeyList(CategoriesDto dto, CategoriesDto result) {
        dto.getProperties().forEach(x -> {
            x.setCategory( new CategoriesDto(result.getId()));
            x.setBuiltIn(false);
        });
        System.out.println(dto.getProperties());
        List<CategoryPropertiesKeyEntity> entities = mapper.toEntity(dto.getProperties());
        result.setProperties(mapper.toDto( repository.saveAll(entities)));
        return result;

    }
}
