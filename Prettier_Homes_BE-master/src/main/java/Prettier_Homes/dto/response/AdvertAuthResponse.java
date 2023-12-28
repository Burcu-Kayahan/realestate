package Prettier_Homes.dto.response;

import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertAuthResponse {

    private Long id;

    private String title;

    private String desc;

    private String slug;

    private Double price;

    private String status;

    private Boolean isActive;

    private Integer viewCount;

    private String location;

    private AdvertsTypeDto advertType;

    private CountriesDto country;

    private CitiesDto city;

    private DistrictsDto district;

    private UserDto user;

    private CategoriesDto category;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private String url;


    public AdvertAuthResponse(AdvertsEntity entity, String url){
        this.id=entity.getId();
        this.title=entity.getTitle();
        // .......
        this.url=url;

    }
}

