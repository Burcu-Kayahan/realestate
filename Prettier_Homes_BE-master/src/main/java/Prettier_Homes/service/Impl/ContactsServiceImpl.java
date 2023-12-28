package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.CategoriesMapper;
import Prettier_Homes.converter.ContactsMapper;
import Prettier_Homes.data.entity.ContactsEntity;
import Prettier_Homes.data.repository.ContactsRepository;
import Prettier_Homes.dto.ContactsDto;
import Prettier_Homes.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactsServiceImpl implements ContactsService {

  @Autowired
  ContactsRepository repository;

    @Autowired
    ContactsMapper mapper;
    @Override
    public ResponseEntity<ContactsDto> create(ContactsDto dto) {
      ContactsEntity entity = mapper.toEntity(dto);
      ContactsDto result = mapper.toDto(repository.save(entity));

      return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<ContactsDto>> getList(String search, int page, int size, String sort, Sort.Direction direction) {
      Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
      Page<ContactsEntity> entities = repository.findAll(pageable);
      Page<ContactsDto> result = entities.map(entity -> mapper.toDto(entity));
      result = new PageImpl<>(result.getContent(),pageable,entities.getTotalElements());

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
