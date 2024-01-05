package Prettier_Homes.converter;


import Prettier_Homes.data.entity.CategoryPropertiesValuesEntity;
import Prettier_Homes.dto.CategoryPropertiesValuesDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryProValuesMapper extends HibernateAwareMapper {


    @InheritConfiguration
    void toEntityForUpdate(CategoryPropertiesValuesDto dto, @MappingTarget CategoryPropertiesValuesEntity entity);

    @Mapping(target = "keyName", source ="categoryPropertiesKey.name")
    @Mapping(target = "keyId", source ="categoryPropertiesKey.id")
    CategoryPropertiesValuesDto toDto(CategoryPropertiesValuesEntity entity);

    List<CategoryPropertiesValuesDto> toDto(List<CategoryPropertiesValuesEntity> entities);

    @Mapping(source = "keyName", target ="categoryPropertiesKey.name")
    @Mapping(source = "keyId", target ="categoryPropertiesKey.id")
    CategoryPropertiesValuesEntity toEntity(CategoryPropertiesValuesDto dto);

    List<CategoryPropertiesValuesEntity> toEntity(List<CategoryPropertiesValuesDto> models);

}
