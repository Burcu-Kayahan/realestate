package Prettier_Homes.dto.response;

import Prettier_Homes.converter.CategoryProValuesMapper;
import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.CategoryPropertiesValuesEntity;
import Prettier_Homes.data.entity.ImagesEntity;
import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.CategoryPropertiesValuesDto;
import Prettier_Homes.dto.ImagesDto;
import Prettier_Homes.dto.TourRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
//AllArgsConstructor
@NoArgsConstructor
public class AdvertDetailBySlugResponse {

    private AdvertsDto advert;
    private String url;
    private List<CategoryPropertiesValuesDto> values;
    private List<String> urls;
    private List<ImagesDto> imges;

    public AdvertDetailBySlugResponse(AdvertsEntity entity, String url) {
        this.advert.setId(entity.getId());
        this.advert.setTitle(entity.getTitle());
        this.url = url;

    }
//    AdvertsDto advert;
//    List<CategoryPropertiesValuesDto> properties;
//    List<ImagesDto> images;
//    List<TourRequestDto> tourRequest;
//
//    public AdvertDetailBySlugResponse(AdvertsEntity adverts, List<ImagesEntity> imagesEntities, List<CategoryPropertiesValuesEntity> valuesEntities){
//
//    }


}
