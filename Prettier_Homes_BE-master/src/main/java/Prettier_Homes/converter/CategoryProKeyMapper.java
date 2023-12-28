package Prettier_Homes.converter;



import Prettier_Homes.data.entity.CategoryPropertiesKeyEntity;
import Prettier_Homes.dto.CategoryPropertiesKeyDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryProKeyMapper extends HibernateAwareMapper {


    // NOT: entity de creationDate degiskeni varsa ilk method a belirtildigi gibi yazilacak.
    // creationDate yoksa silmeli => =>  @Mapping(target = "creationDate", ignore = true)




    @InheritConfiguration
    void toEntityForUpdate(CategoryPropertiesKeyDto dto, @MappingTarget CategoryPropertiesKeyEntity entity);


    CategoryPropertiesKeyDto toDto(CategoryPropertiesKeyEntity entity);

    List<CategoryPropertiesKeyDto> toDto(List<CategoryPropertiesKeyEntity> entities);


    CategoryPropertiesKeyEntity toEntity(CategoryPropertiesKeyDto dto);

    List<CategoryPropertiesKeyEntity> toEntity(List<CategoryPropertiesKeyDto> models);

}
