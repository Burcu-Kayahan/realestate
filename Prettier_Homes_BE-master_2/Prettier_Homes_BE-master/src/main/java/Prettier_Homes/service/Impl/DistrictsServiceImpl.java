package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.DistrictsMapper;
import Prettier_Homes.data.entity.DistrictsEntity;
import Prettier_Homes.data.repository.DistrictsRepository;
import Prettier_Homes.dto.DistrictsDto;
import Prettier_Homes.service.DistrictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictsServiceImpl implements DistrictsService {

    @Autowired
    DistrictsRepository repository;

    @Autowired
    DistrictsMapper mapper;

    @Override
    public ResponseEntity<List<DistrictsDto>> getList() {

        List<DistrictsEntity> entities = repository.findAll();
        List<DistrictsDto> resultList = mapper.toDto(entities);

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DistrictsDto> create(DistrictsDto dto) {
        DistrictsEntity entity = mapper.toEntity(dto);
        DistrictsEntity savedEntity = repository.save(entity);
        DistrictsDto savedDto = mapper.toDto(savedEntity);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DistrictsDto> update(Long id, DistrictsDto dto) {
        Optional<DistrictsEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            DistrictsEntity existingEntity = entity.get();
            DistrictsEntity updatedEntity = mapper.toEntity(dto);
            existingEntity.setName(updatedEntity.getName());
            existingEntity.setCities(updatedEntity.getCities());
            DistrictsEntity savedEntity = repository.save(existingEntity);
            DistrictsDto savedDto = mapper.toDto(savedEntity);

            return new ResponseEntity<>(savedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<DistrictsDto> delete(Long id) {
        Optional<DistrictsEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<DistrictsDto>> getListByCityId(Long id) {
        List<DistrictsDto> resultList = mapper.toDto(repository.findByCitiesId(id));
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }


}
