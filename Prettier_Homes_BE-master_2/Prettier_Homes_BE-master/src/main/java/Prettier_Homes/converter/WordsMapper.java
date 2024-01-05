package Prettier_Homes.converter;

import Prettier_Homes.data.entity.WordsEntity;
import Prettier_Homes.dto.WordsDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WordsMapper extends HibernateAwareMapper {


    @Mapping(target = "creationDate", ignore = true)//creationDate e dokunma
    @InheritConfiguration
    void toEntityForUpdate(WordsDto model, @MappingTarget WordsEntity user);//entity vercem cevir


    WordsDto toDto(WordsEntity brand);

    List<WordsDto> toDto(List<WordsEntity> brand);


    WordsEntity toEntity(WordsDto model);

    List<WordsEntity> toEntity(List<WordsDto> models);




}
