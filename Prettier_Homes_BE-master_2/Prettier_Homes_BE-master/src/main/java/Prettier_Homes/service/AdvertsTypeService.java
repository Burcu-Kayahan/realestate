package Prettier_Homes.service;

import Prettier_Homes.dto.AdvertsTypeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdvertsTypeService {

    ResponseEntity<List<AdvertsTypeDto>> getList();

    ResponseEntity<AdvertsTypeDto> getById(Long id);

    ResponseEntity<AdvertsTypeDto> create(AdvertsTypeDto dto);

    ResponseEntity<AdvertsTypeDto> update(Long id, AdvertsTypeDto dto);

    ResponseEntity<AdvertsTypeDto> delete(Long id);
}

