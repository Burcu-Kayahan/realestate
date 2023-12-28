package Prettier_Homes.service;


import Prettier_Homes.dto.LogsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LogsService {

    ResponseEntity<List<LogsDto>> getLogsByUserId(Long userId);

}
