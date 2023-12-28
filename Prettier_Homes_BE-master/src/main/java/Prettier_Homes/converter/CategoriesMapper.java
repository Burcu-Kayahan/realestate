package Prettier_Homes.converter;

import Prettier_Homes.data.entity.CategoriesEntity;

import Prettier_Homes.data.entity.ContactsEntity;
import Prettier_Homes.dto.CategoriesDto;

import Prettier_Homes.dto.ContactsDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesMapper extends HibernateAwareMapper {


   @Mapping(target = "createAt", ignore = true)
   @InheritConfiguration
    void toEntityForUpdate(CategoriesDto dto, @MappingTarget CategoriesEntity entity);
    CategoriesDto toDto(CategoriesEntity entity);
    List<CategoriesDto> toDto(List<CategoriesEntity> entities);

    CategoriesEntity toEntity(CategoriesDto dto);
    List<CategoriesEntity> toEntity(List<CategoriesDto> models);


}
