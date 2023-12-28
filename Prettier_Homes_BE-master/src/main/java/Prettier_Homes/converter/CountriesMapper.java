package Prettier_Homes.converter;

import Prettier_Homes.data.entity.CountriesEntity;
import Prettier_Homes.data.entity.WordsEntity;
import Prettier_Homes.dto.CountriesDto;
import Prettier_Homes.dto.WordsDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountriesMapper extends HibernateAwareMapper {


    @InheritConfiguration
    void toEntityForUpdate(CountriesDto model, @MappingTarget CountriesEntity user);


    CountriesDto toDto(CountriesEntity entity);

    List<CountriesDto> toDto(List<CountriesEntity> entities);


    CountriesEntity toEntity(CountriesDto dto);

    List<CountriesEntity> toEntity(List<CountriesDto> dtos);




}
