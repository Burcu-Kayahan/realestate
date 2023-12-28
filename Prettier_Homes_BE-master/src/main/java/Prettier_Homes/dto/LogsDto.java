package Prettier_Homes.dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class LogsDto {


    private Long id;

    private String log;

    private UserDto user;

    private AdvertsDto advert;

    private LocalDateTime createAt;





}
