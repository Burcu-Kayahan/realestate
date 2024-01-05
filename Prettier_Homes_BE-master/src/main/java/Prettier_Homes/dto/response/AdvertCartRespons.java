package Prettier_Homes.dto.response;

import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.dto.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AdvertCartRespons {

    private Long id;

    private String title;

    private String description;

    private String slug;

    private Double price;

    private String status;

    private Boolean isActive;

    private Integer viewCount;

    private String location;

    private AdvertsTypeDto advertType;
    private String advertTypeName;
    private Long advertTypeId;

    private CountriesDto country;
    private String countryName;
    private Long countryId;

    private CitiesDto city;
    private String cityName;
    private Long cityId;

    private DistrictsDto district;
    private String districtName;
    private Long districtId;

    private UserDto user;
    private String userEmail;
    private Long userId;

    private CategoriesDto category;
    private String categoryName;
    private Long categoryId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Long popularity;

    private String url;

      private byte[] imgData;
      private Long favoriId;

    public AdvertCartRespons(AdvertsEntity entity, String url , Long popularity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.popularity = popularity;
        this.url = url;
        this.price=entity.getPrice();
        this.location= entity.getLocation();
        this.countryName=entity.getCountry().getName();
        this.cityName=entity.getCity().getName();
        this.districtName=entity.getDistrict().getName();
        this.slug=entity.getSlug();
        // diğer alanları doldurun
    }

    public AdvertCartRespons(AdvertsEntity entity, String url){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.url=url;
        this.price= entity.getPrice();
        this.location= entity.getLocation();
        this.countryName=entity.getCountry().getName();
        this.countryId=entity.getCountry().getId();
        this.cityName= entity.getCity().getName();
        this.cityId= entity.getCity().getId();
        this.description= entity.getDescription();
        this.districtName=entity.getDistrict().getName();
        this.districtId=entity.getDistrict().getId();
        this.createAt=entity.getCreateAt();
        this.advertTypeName= entity.getAdvertType().getTitle();
        this.advertTypeId= entity.getAdvertType().getId();
        this.isActive=entity.getIsActive();
        this.slug=entity.getSlug();
        this.status=entity.getStatus().name();
        this.viewCount= entity.getViewCount();
        this.categoryName= entity.getCategory().getTitle();
        this.categoryId=entity.getCategory().getId();


    }

    public AdvertCartRespons(AdvertsEntity entity, Long favoriId, String url){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.url=url;
        this.price= entity.getPrice();
        this.location= entity.getLocation();
        this.countryName=entity.getCountry().getName();
        this.countryId=entity.getCountry().getId();
        this.cityName= entity.getCity().getName();
        this.cityId= entity.getCity().getId();
        this.description= entity.getDescription();
        this.districtName=entity.getDistrict().getName();
        this.districtId=entity.getDistrict().getId();
        this.createAt=entity.getCreateAt();
        this.advertTypeName= entity.getAdvertType().getTitle();
        this.advertTypeId= entity.getAdvertType().getId();
        this.isActive=entity.getIsActive();
        this.slug=entity.getSlug();
        this.status=entity.getStatus().name();
        this.viewCount= entity.getViewCount();
        this.categoryName= entity.getCategory().getTitle();
        this.categoryId=entity.getCategory().getId();
        this.favoriId=favoriId;

    }
    public AdvertCartRespons(AdvertsEntity entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.price=entity.getPrice();
        this.category=new CategoriesDto(entity.getCategory().getId());


    }
//    public AdvertCartRespons(AdvertsEntity entity, byte[] imgData){
//        this.id=entity.getId();
//        this.title=entity.getTitle();
//        // .......
//       this.imgData=imgData;
//        //getCartListFilter methodu icin yazildi
//        //getFilterByAuth methodu icin yazildi
//        //getFilterByAdmin methodunda kullaniliyor
//    }
}
