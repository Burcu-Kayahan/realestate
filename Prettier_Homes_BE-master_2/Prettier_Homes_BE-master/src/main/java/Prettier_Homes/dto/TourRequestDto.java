package Prettier_Homes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class TourRequestDto {

    private Long id;

    private UserDto guestUser;

    private String status;

    private LocalDateTime createAt;

    private AdvertsDto advert;

    private LocalDateTime tourTime;
    private LocalDateTime updateAt;


    private UserDto ownerUser;

}
