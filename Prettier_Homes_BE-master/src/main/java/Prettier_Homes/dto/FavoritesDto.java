package Prettier_Homes.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
public class FavoritesDto {


    private Long id;

    private UserDto user;

    private AdvertsDto advert;

    private LocalDateTime createAt;


}
