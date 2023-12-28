package Prettier_Homes.dto;

import lombok.Data;

@Data
public class CategoryPropertiesValuesDto {

    private Long id;

    private String value;

    private AdvertsDto advert;

    private CategoryPropertiesKeyDto categoryPropertiesKey;

    private String keyName;
    private Long keyId;


}
