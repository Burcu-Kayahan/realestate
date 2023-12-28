package Prettier_Homes.converter;

import Prettier_Homes.data.entity.CitiesEntity;
import Prettier_Homes.dto.CitiesDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitiesMapper extends HibernateAwareMapper {


    @InheritConfiguration
    void toEntityForUpdate(CitiesDto model, @MappingTarget CitiesEntity entity);

    CitiesDto toDto(CitiesEntity entity);

    List<CitiesDto> toDto(List<CitiesEntity> entities);

    CitiesEntity toEntity(CitiesDto dto);

    List<CitiesEntity> toEntity(List<CitiesDto> dtos);

}
