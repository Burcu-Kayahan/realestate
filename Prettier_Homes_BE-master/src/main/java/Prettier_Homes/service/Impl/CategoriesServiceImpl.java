package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.CategoriesMapper;
import Prettier_Homes.data.entity.CategoriesEntity;
import Prettier_Homes.data.repository.CategoriesRepository;
import Prettier_Homes.dto.CategoriesDto;
import Prettier_Homes.service.CategoriesService;
import Prettier_Homes.service.CategoryPropertiesKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoriesRepository repository;

    @Autowired
    CategoriesMapper mapper;

    @Autowired
    CategoryPropertiesKeyService keyService;
    @Override
    public ResponseEntity<CategoriesDto> create(CategoriesDto dto) {
        CategoriesEntity entity=mapper.toEntity(dto);
        entity.setCreateAt(LocalDateTime.now());
        CategoriesDto resultDto =mapper.toDto(repository.save(entity));
        CategoriesDto result= keyService.savePropertiesKeyList(dto, resultDto);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<CategoriesDto>> getList(int page, int size, String sort, Sort.Direction direction, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<CategoriesEntity> entities = repository.findAll(pageable);
        Page<CategoriesDto> results = entities.map(entity -> mapper.toDto(entity));
        results = new PageImpl<>(results.getContent(), pageable, entities.getTotalElements());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriesDto> getById(Long id) {
        CategoriesDto result= mapper.toDto(repository.findById(id).get());

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriesDto> update(Long id, CategoriesDto dto) {
        Optional<CategoriesEntity> exists = repository.findById(id);
        if (exists.isEmpty()){
            throw new RuntimeException("Data could not be found:" + id);
        }
        // todo slug koctrolu yazilacak. Slug benzersiz mi, ayni slug daha once kaydedilmissse, yeni slug olusturulacak ve aralardaki bosluklAR SILINECEK
        mapper.toEntityForUpdate(dto, exists.get());
        CategoriesEntity entity = repository.save(exists.get());
        CategoriesDto result = mapper.toDto(entity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<CategoriesDto> delete(Long id) {
        Optional<CategoriesEntity> exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(mapper.toDto(exists.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CategoriesDto>> getListByCategories(int page, int size, String sort, Sort.Direction direction, String search) {
//        Page<CategoriesEntity> resultList = repository.findAll();
        return getList(page, size,sort,direction,search);
    }


}
