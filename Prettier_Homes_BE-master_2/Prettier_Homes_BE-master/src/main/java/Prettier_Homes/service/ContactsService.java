package Prettier_Homes.service;

import Prettier_Homes.dto.ContactsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface ContactsService {

    ResponseEntity<ContactsDto> create(ContactsDto dto);
    ResponseEntity<Page<ContactsDto>> getList(String search, int page, int size, String sort, Sort.Direction direction);



}
