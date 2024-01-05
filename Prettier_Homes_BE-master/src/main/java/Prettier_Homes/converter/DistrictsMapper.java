package Prettier_Homes.converter;

import Prettier_Homes.data.entity.CitiesEntity;
import Prettier_Homes.data.entity.DistrictsEntity;
import Prettier_Homes.dto.CitiesDto;
import Prettier_Homes.dto.DistrictsDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictsMapper extends HibernateAwareMapper {

    @InheritConfiguration
    void toEntityForUpdate(DistrictsDto model, @MappingTarget DistrictsEntity entity);

   DistrictsDto toDto(DistrictsEntity entity);

    List<DistrictsDto> toDto(List<DistrictsEntity> entities);

    DistrictsEntity toEntity(DistrictsDto dto);

    List<DistrictsEntity> toEntity(List<DistrictsDto> dtos);
}
