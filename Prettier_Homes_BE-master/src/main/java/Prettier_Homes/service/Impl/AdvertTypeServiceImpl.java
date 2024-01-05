package Prettier_Homes.service.Impl;

import Prettier_Homes.converter.AdvertTypeMapper;
import Prettier_Homes.data.entity.AdvertsTypeEntity;
import Prettier_Homes.data.repository.AdvertTypeRepository;
import Prettier_Homes.dto.AdvertsTypeDto;
import Prettier_Homes.service.AdvertsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertTypeServiceImpl implements AdvertsTypeService {

    @Autowired
    AdvertTypeRepository repository;
    @Autowired
    AdvertTypeMapper mapper;


    @Override
    public ResponseEntity<List<AdvertsTypeDto>> getList() {
        List<AdvertsTypeEntity> entities =repository.findAll();
        List<AdvertsTypeDto> dtos = mapper.toDto(entities);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdvertsTypeDto> getById(Long id) {
        AdvertsTypeDto result = mapper.toDto(repository.findById(id).get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdvertsTypeDto> create(AdvertsTypeDto dto) {
        AdvertsTypeEntity entity = mapper.toEntity(dto);
        AdvertsTypeDto result = mapper.toDto(repository.save(entity));

        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AdvertsTypeDto> update(Long id, AdvertsTypeDto dto) {
        Optional<AdvertsTypeEntity> exists = repository.findById(id);
        if (exists.isEmpty()){
            throw new RuntimeException("Data could not be found: " + id);
        }
        mapper.toEntityForUpdate(dto,exists.get());
        AdvertsTypeEntity entity = repository.save(exists.get());
        AdvertsTypeDto result = mapper.toDto(entity);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AdvertsTypeDto> delete(Long id) {
        Optional<AdvertsTypeEntity> exists = repository.findById(id);
        if (exists.isEmpty()){
            throw new RuntimeException("Data could not be found: " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(mapper.toDto(exists.get()), HttpStatus.OK);
    }


}
