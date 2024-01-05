package Prettier_Homes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertsCitiesResponse {
    private String cityName;
    private Long cityId;
    private Long amount;

    public AdvertsCitiesResponse(Long cityId,String cityName,Long amount){
        this.cityName=cityName;
        this.cityId=cityId;
        this.amount=amount;
    }
}
