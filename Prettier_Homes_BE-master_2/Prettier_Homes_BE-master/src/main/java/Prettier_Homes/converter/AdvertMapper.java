package Prettier_Homes.converter;

import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.enums.EnmAdvertStatus;
import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.requests.AdvertCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertMapper extends HibernateAwareMapper {

    @Named("convertStatus")
    static String convertStatus(EnmAdvertStatus status) {
        return status.name();
    }

    @Named("convertStatus")
    static EnmAdvertStatus convertStatus(String status) {
        return EnmAdvertStatus.valueOf(status);
    }



   @Mapping(target = "createAt", ignore = true)
    void toEntityForUpdate(AdvertsDto model, @MappingTarget AdvertsEntity entity);

    @Mapping(target = "status", qualifiedByName = "convertStatus")
      AdvertsDto toDto(AdvertsEntity entity);

    List<AdvertsDto> toDto(List<AdvertsEntity> entities);

    @Mapping(target = "status", qualifiedByName = "convertStatus")
    AdvertsEntity toEntity(AdvertsDto dto);

    List<AdvertsEntity> toEntity(List<AdvertsDto> dtos);
    @Mapping(target = "advertType.id", source ="advertType")
    @Mapping(target = "country.id", source ="country")
    @Mapping(target = "city.id", source ="city")
    @Mapping(target = "district.id", source ="district")
    @Mapping(target = "category.id", source ="category")
    AdvertsEntity toEntity( AdvertCreateRequest dto);

    @Mapping(source = "advertType.id", target ="advertType")
    @Mapping(source = "country.id", target ="country")
    @Mapping(source = "city.id", target ="city")
    @Mapping(source = "district.id", target ="district")
    @Mapping(source = "category.id", target ="category")
    AdvertCreateRequest toCreateDto(AdvertsEntity model);

}
