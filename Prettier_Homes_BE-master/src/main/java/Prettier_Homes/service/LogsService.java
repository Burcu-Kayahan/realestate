package Prettier_Homes.service;


import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.enums.EnmLog;
import Prettier_Homes.dto.LogsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LogsService {

    ResponseEntity<List<LogsDto>> getLogsByUserId(Long userId);

    void createLog(AdvertsEntity entity, UserEntity user, EnmLog created);

}
