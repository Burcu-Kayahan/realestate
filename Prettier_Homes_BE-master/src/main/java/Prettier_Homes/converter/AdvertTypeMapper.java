package Prettier_Homes.converter;

import Prettier_Homes.data.entity.AdvertsTypeEntity;
import Prettier_Homes.dto.AdvertsTypeDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertTypeMapper extends HibernateAwareMapper {
    @InheritConfiguration
    void toEntityForUpdate(AdvertsTypeDto model, @MappingTarget AdvertsTypeEntity user);

    AdvertsTypeDto toDto(AdvertsTypeEntity entity);

    List<AdvertsTypeDto> toDto(List<AdvertsTypeEntity> entities);

    AdvertsTypeEntity toEntity(AdvertsTypeDto dto);

    List<AdvertsTypeEntity> toEntity(List<AdvertsTypeDto> dtos);




}
