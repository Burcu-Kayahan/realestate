package Prettier_Homes.controller;

import Prettier_Homes.dto.AdvertsTypeDto;
import Prettier_Homes.dto.LogsDto;
import Prettier_Homes.service.LogsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/logs")
@Slf4j
public class LogsController {

    @Autowired
    LogsService service;


  //  public static Logger logInfo = LoggerFactory.getLogger(LogsController.class);


    @GetMapping("/{userId}")
    public ResponseEntity<List<LogsDto>> getLogsByUserId(@PathVariable Long userId) {
        return service.getLogsByUserId(userId);

    }

      /*
    - Loglama Asenkron olmalidir
    - printStackTrace ve System.out.println() kullanilmamalidir (format)
    - Sensitive data olmamalidir
    - Tum loglar merkezi toplanmalidir, belli formatta
    - Level dikkatli kullanilmalidir
    - Farkli levellar farkli appendarlar kullanilabilir
     */
}
