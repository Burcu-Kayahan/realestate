package Prettier_Homes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertsCountriesResponse {
    private String countryName;
    private Long countryId;
    private Long amount;

    public AdvertsCountriesResponse(Long cityId,String cityName,Long amount){
        this.countryName=countryName;
        this.countryId=countryId;
        this.amount=amount;
    }
}
