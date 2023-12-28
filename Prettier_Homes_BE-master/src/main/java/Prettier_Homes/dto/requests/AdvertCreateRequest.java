package Prettier_Homes.dto.requests;

import Prettier_Homes.dto.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class AdvertCreateRequest {


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

    private Long advertType;

    private Long country;

    private Long city;

    private Long district;

    private UserDto user;

    private Long category;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    List<CategoryPropertiesValuesDto> values;
}
