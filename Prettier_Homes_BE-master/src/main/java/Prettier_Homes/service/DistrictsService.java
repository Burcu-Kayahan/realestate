package Prettier_Homes.service;

import Prettier_Homes.dto.DistrictsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DistrictsService {
    ResponseEntity<List<DistrictsDto>> getList();

    ResponseEntity<DistrictsDto> create(DistrictsDto dto);

    ResponseEntity<DistrictsDto> update(Long id, DistrictsDto dto);


    ResponseEntity<DistrictsDto> delete(Long id);

    ResponseEntity<List<DistrictsDto>> getListByCityId(Long id);
}
