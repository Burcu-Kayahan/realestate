package Prettier_Homes.dto;

import Prettier_Homes.data.entity.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class AdvertsDto {


    private Long id;

    private String title;

    private String description;

    private String slug;

    private Double price;

    private String status;

    private Boolean builtIn;

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

    public AdvertsDto(Long id){
        this.id=id;
    }
}
