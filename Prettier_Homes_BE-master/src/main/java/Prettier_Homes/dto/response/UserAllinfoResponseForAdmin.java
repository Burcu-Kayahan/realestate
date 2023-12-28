package Prettier_Homes.dto.response;


import Prettier_Homes.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAllinfoResponseForAdmin {

    private UserDto user;
    private List<AdvertsDto> adverts;
    private List<TourRequestDto> tourRequests;
    private List<FavoritesDto> favorites;
    private List<LogsDto> logs;
    private String  message;




}
