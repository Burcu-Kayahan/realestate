package Prettier_Homes.dto.response;

import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.TourRequestDto;
import Prettier_Homes.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DeleteRespons {

    private String message;
    private Boolean delete;
    private List<AdvertsDto> adverts;
    private List<TourRequestDto> tourRequests;
    private UserDto user;
}
