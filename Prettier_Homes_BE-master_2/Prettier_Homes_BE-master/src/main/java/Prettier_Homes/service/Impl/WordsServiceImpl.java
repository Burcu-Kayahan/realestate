package Prettier_Homes.service.Impl;

import Prettier_Homes.converter.WordsMapper;
import Prettier_Homes.data.entity.WordsEntity;
import Prettier_Homes.data.repository.WordsRepository;
import Prettier_Homes.dto.WordsDto;
import Prettier_Homes.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordsServiceImpl implements WordsService {

    @Autowired
    WordsRepository repository;

    @Autowired
    WordsMapper mapper;

    @Override
    public ResponseEntity<WordsDto> create(WordsDto dto) {
        WordsEntity entity = mapper.toEntity(dto).setCreationdate();
        WordsDto result = mapper.toDto(repository.save(entity));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<WordsDto>> getList(int page, int size, String sort, Sort.Direction direction, String search) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
        Page<WordsEntity> entities = repository.findAll(pageable);
        Page<WordsDto> results = entities.map(entity -> mapper.toDto(entity));
        results = new PageImpl<>(results.getContent(), pageable, entities.getTotalElements());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WordsDto> getById(Long id) {
        WordsDto result = mapper.toDto(repository.findById(id).get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WordsDto> update(Long id, WordsDto dto) {
        Optional<WordsEntity> exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        mapper.toEntityForUpdate(dto, exists.get());
        WordsEntity entity = repository.save(exists.get());
        WordsDto result = mapper.toDto(entity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<WordsDto> delete(Long id) {
        Optional<WordsEntity> exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(mapper.toDto(exists.get()), HttpStatus.OK);
    }
}
